#!/usr/bin/env python
# jsidl2jaus.py
#
# This script is not perfect.  Review the outputs before you rely on them.
#
# Examples:
# 1. Convert an sjsidl file:
#       jsidl2jaus.py --in svc.xml --out svc.jaus --ns '{urn:jaus:sjsidl:1.0}'
#
# 2. Convert a jsidl file:
#       jsidl2jaus.py --in svc.xml --out svc.jaus
#
#
import sys
from lxml import etree
import optparse
import traceback
import unicodedata

DEBUG = 0
# DEBUG = 7
RIGHT_MARGIN = 80
MIN_COMMENT_BLOCK_WIDTH = 40  # Minimum width of a comment block.
# JSIDL_NS = '{urn:jaus:jsidl:1.1}'
JSIDL_NS = '{urn:jaus:jsidl:1.0}'
JSIDL_NS_1 = '{urn:jaus:jsidl:1.1}'
SJSIDL_NS = '{urn:jaus:sjsidl:1.0}'
JSIDL_PLUS_NS = '{urn:jaus:jsidl:plus}'

# Defaults
indent_g=2
ns_g = JSIDL_NS     # override with --ns command line arg.
is_jsidl_g = False
current_ns_scope_g = [] # current hierarchical namespace
current_ns_alias_g = {}  # key by alias: current_ns_alias_g['basicTypes'] = 'urn:jaus:jss:core:MessageSet:BasicTypes' TODO: this needs to be scoped.
collecting_types_g=None
service_def_name_g = ''
current_column_g = 0  # Pos in current line where next output char will go.

# Hierarchical namespace lookup.
type_declarations_g = {}

fsm_start_map = {}
field_format_map = {
    'JAUS Message':'AppMessage',
    'JAUS MESSAGE':'AppMessage'
    }
scalar_type_map = {
    'byte':'uint8',
    'unsigned byte':'uint8',
    'short integer':'int16',
    'unsigned short integer':'uint16',
    'integer':'int32',
    'unsigned integer':'uint32',
    'long integer':'int64',
    'unsigned long integer':'uint64',
    'float':'float',
    'long float':'double',
    'RGB':'uint24'
    }
scalar_max = {
    'int8':127,
    'uint8':255,
    'int16':pow(2,15)-1,
    'uint16':pow(2,16)-1,
    'int32':pow(2,31)-1,
    'uint32':pow(2,32)-1
    }
msg_type_map = {
    'input':'query',  # but overridden to 'command' if is-command="true"
    'output':'inform',
    'internal':'internal'
    }
tag_map = {
    'array':'array',
    # 'client_of':'requires',
    'client_of':'client_of',
    'declared_const_set':'constants',
    'declared_type_set':'types',
    'dimension':'dim',
    'event_def':'event',
    'fixed_field':'field',
    'declared_fixed_field':'field',
    'fixed_length_string':'string',
    'format_enum':'format_enum',  # needs emit_
    'format_field':'format_field',  # needs emit_
    #'inherits_from':'extends',
    'inherits_from':'inherits_from',
    'internal_events_set':'events',
    'list':'list',
    'message_def':'message',
    'message_set':'messages',
    'pseudo_start_state':'pseudo_start_state', # new in JSIDL_PLUS
    'record':'record',
    'references':'references',
    'scale_range':'scale_range',
    'sequence':'sequence',
    'service_def':'service',
    'type_and_units_enum':'type_and_units_enum',
    'type_and_units_field':'type_and_units_field',
    'variable_field':'variant_field',
    'variable_format_field':'variable_format_field', # needs emit_
    'variable_length_string':'vstring',
    'null_terminated_string':'cstring',
    'variant':'variant',
    'vtag_field':'vtag_field'
    }
value_map = {}

# Converting to ASCII, preserve and convert some subset
# of unicode chars typically found in MS Word text.
#
unicode_map = {
  u'\N{EN SPACE}':'  ',
  u'\N{EM SPACE}':' ',
  u'\N{HYPHEN}':'-',
  u'\N{NON-BREAKING HYPHEN}':'-',
  u'\N{FIGURE DASH}':'--',
  u'\N{EN DASH}':'-',
  u'\N{EM DASH}':'--',
  u'\N{DOUBLE VERTICAL LINE}':'||',
  u'\N{LEFT SINGLE QUOTATION MARK}':"'",
  u'\N{RIGHT SINGLE QUOTATION MARK}':"'",
  u'\N{LEFT DOUBLE QUOTATION MARK}':'"',
  u'\N{RIGHT DOUBLE QUOTATION MARK}':'"',
    }

def debug(level,msg):
    global DEBUG
    if level <= DEBUG:
        sys.stderr.write(msg)

def unquote(s):
    s = s.strip()
    if ( (s[0] == "'" and s[-1] == "'")
         or (s[0] == '"' and s[-1] == '"')):
        s = s[1:-1]
    return s

def normalize_whitespace(s):
    """strip leading and trailing whitespace,
    replace all internal whitespace with a single space."""
    return ' '.join( s.strip().split() )

def normalize_comment_block(s):
    """strip leading and trailing whitespace for each line,
    but keep line breaks"""
    lines = s.split('\n')
    newlines = []
    for l in lines:
        newlines.append(l.strip())
    return '\n'.join( newlines )

def normalize_name(name):
    s = ' '.join(name.strip().split()).replace(' ','_').replace('-','_')
    s = s.replace('(','')
    s = s.replace(')','')
    return s

def unicode_to_ascii(s):
    # Unicode. Translate some chars we want to save and ignore the rest.
    news = []
    for c in s:
        if c in unicode_map:
            debug(4,"mapping unicode \\N{%s} to ascii '%s'\n"%(unicodedata.name(c),unicode_map[c]))
            c = unicode_map[c]
        news.append(c)
    s = ''.join(news)    
    return unicodedata.normalize('NFKD', s).encode('ASCII', 'ignore')
    
def normalize_interpretation(c):
    s = c.attrib['interpretation']
    if type(s) == type(u''):
        debug(4,"cleaning unicode interpretation for %s:%s\n"%(c.tag,c.attrib['name']))
        t = unicode_to_ascii(s)
    else:
        t = s
    return ' '.join( t.strip().split() )

def size_to_type(s):
    """Convert a numeric size to the smallest unsigned integer that can represent the value."""
    size = int(s,10)
    if size < pow(2,8):
        return 'uint8'
    elif size < pow(2,16):
        return 'uint16'
    elif size < pow(2,32):
        return 'uint32'
    else:
        raise ValueError

def fill(s,width,prefix=''):
    """Take a possibly multiline string and fill into width chars wide text block."""
    if len(prefix) >= width:
        raise ValueError
    if s == '':
        return prefix

    text_width = RIGHT_MARGIN - len(prefix)
    # Insert <newline,prefix> at every space positioned just left of the RIGHT_MARGIN col mark.
    words = s.strip().split()
    filled = []
    col = 0
    n = 0
    while n < len(words):
        if col == 0:
            filled.append(prefix)
            col += len(prefix)
        word = words[n]
        if word == '':
            # Ignore
            n += 1
        elif len(word) > text_width:
            # Have to break the word in half and try again.
            w1 = words[:len(word)/2]
            w2 = words[len(word)/2:]
            words = words[:n-1]+[w1,w2]+words[n:]
        elif len(words[n]) < width - col:
            filled.extend( [ word, ' ' ] )
            col += len(word)+1
            n += 1
        else:
            # Don't need trailing blanks on lines.
            if filled[-1] == ' ':
                filled[-1] = '\n'
            else:
                filled.append('\n')
            col = 0  # Next line
    if filled[-1] == ' ':
        filled.pop()
    return ''.join(filled)

def fill_comment(s,level,prefix=None):
    """Take a possibly multiline string and fill into width chars wide
    text block, then make each line but first a comment."""

    global current_column_g # Used to handle the first line.
    if prefix is None:
        prefix = ''

    leader = '//'  # first leader has no indent.
    line_leader = ' '*level*indent_g + '//'
    left_margin = len(line_leader)  # first pos on each line were we add words.
    right_margin = RIGHT_MARGIN
    if len(prefix) >= right_margin:
        raise ValueError('fill_comment: prefix wider than block.  prefix=%s'%prefix)
    if s == '':
        return prefix

    # Tokenize comment.
    words = s.strip().split()
    lines = []
    filled = [ leader, ]
    col = max(current_column_g,left_margin)
    if current_column_g < left_margin:
        emit(outf,' '*(left_margin-current_column_g))
    while words:
        # Handle start of new line.
        if col >= right_margin:
            lines.append( ' '.join(filled))
            filled = [ line_leader, ]
            col = left_margin
        # Fit this word in line if we can.
        word = words.pop(0)
        if  (col < left_margin or col > right_margin):
            debug(0, 'col == %d, not in [%d, %d]\n'%(col,left_margin,right_margin))
            raise Exception('Logic error.')
        elif word == '':
            continue
        elif (col + len(word)) > right_margin:
            try:
                words.insert(0,word)  # push word back on, start new line.
                col = right_margin
            except:
                print "prev_word=%s, col=%d, right_margin=%d, filled[-1]=%s, words[0]=%s"% \
                      (prev_word,col,right_margin,filled[-1],words[0])
                raise Exception("Looping")
        elif (col + len(word)) < right_margin:
            filled.append(word)
            col += len(word)+1 # Add one pos for space.
        elif (col + len(word)) == right_margin:
            filled.append(word)
            col = right_margin
        else:
            raise Exception('Fill error')
    if col != left_margin:
        # Pick up remaining filled words.
        lines.append( ' '.join(filled))
    return  '\n'.join(lines)+'\n'

def get_count_field(c):
    count_type = scalar_type_map[normalize_whitespace(c.attrib['field_type_unsigned'])]
    count_name = 'count'
    if 'min_count' in c.attrib:
        count_min = c.attrib['min_count']
    else:
        count_min = "0"
    if 'max_count' in c.attrib:
        count_max = c.attrib['max_count']
    else:
        count_max = str(scalar_max[count_type])
    return count_type, count_name, count_min, count_max    

def emit(outf,s):
    global current_column_g   # refers to the next column position that we will output to.
    eol = s.rfind('\n')  # pos of rightmost '\n', if any.
    # Resets current column to one char past the end of the last string output.
    oldccg = current_column_g
    if eol >= 0:
        current_column_g = len(s)-eol-1
    else:
        current_column_g += len(s)
    debug(7, 'current_column_g %d -> %d\n'%(oldccg,current_column_g))
    outf.write(s.encode('utf-8'))

def emit_comment(node, level, outf):
    global indent_g
    comment_indent = level*indent_g*' '
    if type(node.text) == type(u''):
        t = unicode_to_ascii(node.text)
    else:
        t = node.text
    t = normalize_comment_block(t)
    t = t.replace('\n','\n'+comment_indent+'// ')
    emit(outf,comment_indent+'// %s\n'%t)

def emit_interpretation(node,level,outf):
    global indent_g
    if ( 'interpretation' not in node.attrib
         or node.attrib['interpretation'] == '' ):
        emit(outf,'\n')
    else:
        interp = normalize_interpretation(node)
        if DEBUG > 0:
            for c in interp:
                if ord(c) > 127:
                    raise Exception("Bad ASCII char ord='\u%x'(%d) in normalized interp."%(ord(c),ord(c)))
        if interp != '' and interp.upper() != 'N/A':
            emit(outf,' '+fill_comment(interp, level+1))

def escape_quotes(s):
    try:
        t = unicodedata.normalize('NFKD', s).encode('ASCII', 'ignore')
    except:
        # Some strings we get from lxml are not unicode
        t = s
    return '\\"'.join(t.split('"'))

def emit_description(node,level,outf):
    global indent_g
    prefix = ' '*(level+1)*indent_g
    if node.text is not None:
        if type(node.text) == type(u''):
            t = unicode_to_ascii(node.text)
        else:
            t = node.text
        # Fill the description block, but remove the initial prefix.
        desc = fill(escape_quotes(t),RIGHT_MARGIN,prefix)[len(prefix):]
        emit(outf,' '*(level)*indent_g
                         +'description "'+desc+'";\n')

def emit_explicit_interpretation(node,level,outf):
    global indent_g
    if 'interpretation' in node.attrib:
        interp = normalize_interpretation(node)
        if interp != '' and interp.upper() != 'N/A':
            emit(outf,' '*(level)*indent_g
                 +'interpretation "'+interp+'";\n')

def emit_assumptions(node,level,outf):
    global indent_g
    prefix = (level+1)*indent_g*' '
    if node.text is not None:
        if type(node.text) == type(u''):
            t = unicode_to_ascii(node.text)
        else:
            t = node.text
        desc = fill(escape_quotes(t),RIGHT_MARGIN,prefix)[len(prefix):]
        emit(outf,' '*(level)*indent_g
                         +'assumptions "'+desc+'";\n')

def emit_attrs(attrs, attr_indent,outf):
    attr_indent = attr_indent+'  '
    attr_list = [ (a,unquote(v)) for a,v in attrs.items()
                  if ( a not in ['interpretation', 'name', 'optional', 'item_index']
                       and not a.endswith('vtag')
                       and v != ''
                       and v != 'false') ]
    emit(outf,'( ')
    if len(attr_list) == 0:
        emit(outf,')')
    elif len(attr_list) == 1:
        emit(outf,'%s="%s" )'%attr_list[0])
    elif len(attr_list) > 1:
        emit(outf,'%s="%s",'%attr_list[0])
        for a,v in attr_list[1:-1]:
            emit(outf,'\n'+attr_indent+'%s="%s",'%(a,v))
        emit(outf,'\n'+attr_indent+'%s="%s" )'%attr_list[-1])

def emit_params(node,level,outf):
    global indent_g
    param_list = []
    # emit(outf,'\n')
    indent = ' '*(level+1)*indent_g
    emit(outf,'(')
    indent += ' '
    for c in node:
        cstag = c.tag.replace(ns_g,'')
        if cstag == 'parameter':
            type = c.attrib['type'].strip()
            value = c.attrib['value'].strip()
            param_list.append( (value, type) )
    if len(param_list) == 0:
        emit(outf,')')
    elif len(param_list) == 1:
        emit(outf,' %s : %s )'%param_list[0])
    elif len(param_list) > 1:
        emit(outf,'\n'+indent+'%s : %s,'%param_list[0])
        for a,v in param_list[1:-1]:
            emit(outf,'\n'+indent+'%s : %s,'%(a,v))
        emit(outf,'\n'+indent+'%s : %s )'%param_list[-1])

def emit_args(node,level,outf):
    global indent_g
    arg_list = []
    # emit(outf,'\n')
    indent = ' '*(level+1)*indent_g
    # emit(outf,indent+'(')
    emit(outf,'(')
    indent += ' '
    for c in node:
        cstag = c.tag.replace(ns_g,'')
        if cstag == 'argument':
            # TODO: special case!!!!
            arg_name = c.attrib['value'].strip()
            if arg_name == 'transportData':
                arg_name = 'msg.src_id'
            arg_list.append( arg_name )
    if len(arg_list) == 0:
        emit(outf,')')
    elif len(arg_list) == 1:
        emit(outf,' %s )'%arg_list[0])
    elif len(arg_list) > 1:
        emit(outf,'\n'+indent+'%s,'%arg_list[0])
        for v in arg_list[1:-1]:
            emit(outf,'\n'+indent+'%s,'%(v))
        emit(outf,'\n'+indent+'%s )'%arg_list[-1])

def emit_message(e,level,context,outf):
    global indent_g
    name = id = '?'
    triggers = None
    type = None
    if 'name' in e.attrib:
        name = e.attrib['name']
    cstag = e.tag.replace(ns_g,'')
    if cstag == 'declared_message_def':
        emit(outf,' '*level*indent_g+'%s %s;\n'%(e.attrib['declared_type_ref'],e.attrib['name']))
    else:
        if 'is_command' in e.attrib and e.attrib['is_command'] == 'true':
            type = 'command'
        elif context == 'input_set':
            type = 'query'
        elif context == 'output_set':
            type = 'inform'
        elif context == 'type_def':
            type = 'message'
        if 'message_id' in e.attrib:
            id = e.attrib['message_id']
        # if 'triggers' in e.attrib:
        #     triggers = 'returns %s;\n'%e.attrib['triggers']
        block_open = ' '*level*indent_g+"%s 0x%s %s {\n"%(type,id,name)
        emit(outf,block_open)
        for c in e:
            stag = c.tag.replace(ns_g,'')
            if stag == 'description':
                emit_description(c,level+1,outf)
            elif stag in ['header', 'footer', 'declared_header', 'declared_footer']:
                pass # ignore these tags for now
            elif is_jsidl_g and stag == 'body':
                emit_children(c, level+1, outf, parent_type=cstag)
            else:
                emit_type(c, level+1 ,outf,  parent_type=cstag)
        if triggers:
            emit(outf,' '*(level+1)*indent_g+triggers)
        emit(outf,' '*level*indent_g+'}\n')

def emit_variable(e,level,outf,prefix='',suffix='',parent_type=''):
    global indent_g
    global current_ns_scope_g
    #if stag in tag_map:
    #    sect = tag_map[stag]
    #else:
    #    stag = e.tag.replace(ns_g,'')
    #else:
    #    sect = '?'
    name = typename = '?'
    opt = ''
    if 'name' in e.attrib:
        name = e.attrib['name']
    if is_jsidl_g:
        if 'declared_type_ref' in e.attrib:
            typename = e.attrib['declared_type_ref']
            # Can we remove unneeded scope prefix on our type?
            type_elements = typename.split('.')
            if len(type_elements) > 1 and type_elements[:-1] == current_ns_scope_g:
                typename = type_elements[-1]
    else:
        if 'declared_type_name' in e.attrib:
            typename = e.attrib['declared_type_name']
        if 'declared_type_set_ref_name' in e.attrib:
            typename = e.attrib['declared_type_set_ref_name']+'.'+typename

    if parent_type == 'list':
        opt = 'repeated'
    elif parent_type in ['record', 'sequence']:
        if 'optional' in e.attrib and e.attrib['optional'] == 'true':
            opt = 'optional'
        else:
            opt = 'required'
    info = '%s %s%s %s'% \
                     (opt,
                      prefix,
                      typename,
                      name)
    var_indent = ' '*level*indent_g
    emit(outf,var_indent+info)
    if 'item_index' in e.attrib:
        suffix = suffix + ' = %s'%e.attrib['item_index']
    emit(outf,'%s;'%suffix)
    emit_interpretation(e,level,outf)

def emit_const(e,level,outf):
    global indent_g
    type = name = value = units = '?'
    if 'const_type' in e.attrib:
        type = scalar_type_map[normalize_whitespace(e.attrib['const_type'])]
    if 'name' in e.attrib:
        name = e.attrib['name']
    if 'const_value' in e.attrib:
        value = e.attrib['const_value']
    if 'field_units' in e.attrib:
        units = e.attrib['field_units'].replace(' ','_')
    const_indent = ' '*level*indent_g
    emit(outf,const_indent+'%s %s = %s %s;'% \
                     (type,
                      name,
                      value,
                      units))
    emit_interpretation(e,level,outf)

def emit_pv(e,level,outf,suffix=''):
    global indent_g
    # NOTE: We are no longer explicitly generating presence vectors.
    # Presence vector encoding for records and sequences is based on
    # 'optional' and 'required' keywords.
    return
    type = '?'
    if 'field_type_unsigned' in e.attrib:
        type = scalar_type_map[normalize_whitespace(e.attrib['field_type_unsigned'])]
    leader = ' '*level*indent_g
    if 'item_index' in e.attrib:
        suffix = suffix + ' = %s'%e.attrib['item_index']
    emit(outf,leader+'required %s presence_vector %s;\n'%(type,suffix))

def emit_field(e,level,outf,prefix='',suffix='',parent_type=''):
    global indent_g
    global collecting_types_g
    global current_ns_scope_g
    type = name = units = '?'
    scale = value_set = ''
    value_set_node = None
    # See what subnodes we have:
    for c in e:
        if c.tag == ns_g+'value_set':
            value_set_node = c  # Have to call its special emit_fn later
        elif c.tag == ns_g+'scale_range':
            scale = '<%s,%s>' % \
                    (c.attrib['real_lower_limit'],
                     c.attrib['real_upper_limit'])
    if 'field_type' in e.attrib:
        type = scalar_type_map[normalize_whitespace(e.attrib['field_type'])]
    if 'name' in e.attrib:
        name = e.attrib['name']
    if 'field_units' in e.attrib:
        units = e.attrib['field_units'].replace(' ','_')
    info = '%s %s %s %s'% \
           (type,
            name,
            units,
            scale)
    if parent_type == 'declared_type_set':
        info = 'field %s'%info
    elif parent_type != 'variant':
        if 'optional' in e.attrib and e.attrib['optional'] == 'true':
            info = 'optional '+info
        else:
            info = 'required '+info
    leader = ' '*level*indent_g
    emit(outf,leader+info)
    if 'item_index' in e.attrib:
        suffix = suffix + ' = %s'%e.attrib['item_index']
    if value_set_node is not None:
        emit_value_set(c,level+1,outf,parent_type='field')
    emit(outf,'%s;'%suffix)  # Always need a semicolon on a field.
    emit_interpretation(e,level,outf)

def emit_tagged_field(e,level,outf,prefix='',suffix=''):
    """ Tagged fields have no name.  Name is that of the parent variable_field."""
    global indent_g
    global collecting_types_g
    type = units = '?'
    scale = ''
    if 'field_type' in e.attrib:
        type = scalar_type_map[normalize_whitespace(e.attrib['field_type'])]
    if 'field_units' in e.attrib:
        units = e.attrib['field_units'].replace(' ','_')
    for c in e:
        if c.tag == ns_g+'scale_range':
            scale = '<%s,%s>' % \
                    (c.attrib['real_lower_limit'],
                     c.attrib['real_upper_limit'])
    info = 'tag %s: %s %s %s'% \
           (e.attrib['index'],
            type,
            units,
            scale)
    leader = ' '*level*indent_g
    emit(outf,leader+info)
    emit(outf,'%s;'%suffix)
    emit_interpretation(e,level,outf)

def emit_count_field(e,level,outf,prefix='',suffix=''):
    global indent_g
    type = '?'
    if 'field_type_unsigned' in e.attrib:
        type = scalar_type_map[normalize_whitespace(e.attrib['field_type_unsigned'])]
    info = '%s count'%type
    leader = ' '*level*indent_g
    emit(outf,leader+info)
    emit(outf,'%s;'%suffix)
    emit_interpretation(e,level,outf)

def emit_variable_length_field(e,level,outf,prefix='',suffix='',parent_type=''):
    global indent_g
    type = name = '?'
    if 'field_format' in e.attrib:
        type = field_format_map[e.attrib['field_format']]
    if 'name' in e.attrib:
        name = e.attrib['name']
    info = '%s %s'% \
           (type,
            name)
    if parent_type == 'list':
        info = 'repeated %s'%info
    elif parent_type != 'variant':
        if 'optional' in e.attrib and e.attrib['optional'] == 'true':
            info = 'optional %s'%info
        else:
            info = 'required %s'%info
    leader = ' '*level*indent_g
    emit(outf,leader+info)
    if 'item_index' in e.attrib:
        suffix = suffix + ' = %s'%e.attrib['item_index']
    emit(outf,'%s;'%suffix)
    emit_interpretation(e,level,outf)

def emit_string(e,level,outf,prefix='',suffix='',parent_type=''):
    global indent_g
    type = name = length_spec = '?'
    if 'name' in e.attrib:
        name = e.attrib['name']
    if e.tag == ns_g+'fixed_length_string':
        type = 'string'
        length_spec = e.attrib['string_length']
    elif e.tag == ns_g+'variable_length_string':
        type = 'vstring'
        for c in e:
            stag = c.tag.replace(ns_g,'')
            if stag == 'count_field':
                count_type, count_name, count_min, count_max = get_count_field(c)
                length_spec = "%s,%s"%(count_min,count_max)
    else:
        raise ValueError("string is neither fixed_length nor variable_length")
    info = '%s %s [ %s ]'% \
           (type,
            name,
            length_spec)
    if parent_type == 'list':
        info = 'repeated %s'%info
    elif parent_type != 'variant':
        if 'optional' in e.attrib and e.attrib['optional'] == 'true':
            info = 'optional %s'%info
        else:
            info = 'required %s'%info
    leader = ' '*level*indent_g
    emit(outf,leader+info)
    if 'item_index' in e.attrib:
        suffix = suffix + ' = %s'%e.attrib['item_index']
    emit(outf,'%s;'%suffix)
    emit_interpretation(e,level,outf)

def emit_bit_field(e,level,outf,prefix='',suffix='',parent_type=''):
    global indent_g
    type = name = '?'
    # Pick up attributes
    if 'field_type_unsigned' in e.attrib:
        type = scalar_type_map[normalize_whitespace(e.attrib['field_type_unsigned'])]
    if 'name' in e.attrib:
        name = e.attrib['name']
    if parent_type == 'list':
        opt = 'repeated '
    elif parent_type == 'declared_type_set':
        opt = ''
    elif parent_type != 'variant':
        if 'optional' in e.attrib and e.attrib['optional'] == 'true':
            opt = 'optional '
        else:
            opt = 'required '
    info = '%sbit_field %s %s'%(opt, type, name)
    leader = ' '*level*indent_g
    emit(outf,leader+info)
    emit(outf,leader+'{\n')

    # subfields here.
    for c in e:
        if c.tag == ns_g+'sub_field':
            emit_sub_field(c,level+1,outf)

    emit(outf,leader+'}')
    if 'item_index' in e.attrib:
        suffix = suffix + ' = %s;'%e.attrib['item_index']
    emit(outf,'%s'%suffix)
    emit_interpretation(e,level,outf)

def get_value_range(e):
    s = ''
    if e.attrib['lower_limit_type'] == 'inclusive':
        s += '['
    elif e.attrib['lower_limit_type'] == 'exclusive':
        s += '('
    s += '%s,%s'%(e.attrib['lower_limit'],e.attrib['upper_limit'])
    if e.attrib['upper_limit_type'] == 'inclusive':
        s += ']'
    elif e.attrib['upper_limit_type'] == 'exclusive':
        s += ')'
    return s

def get_value_enum():
    name =  normalize_name(e.attrib['enum_const'])
    value = e.attrib['enum_index']
    return '%s = %s'%(name, value)
def emit_sub_field(e,level,outf,prefix='',suffix=''):
    global indent_g
    type = name = value = units = '?'
    if 'name' in e.attrib:
        name = e.attrib['name']
    if 'field_units' in e.attrib:
        units = e.attrib['field_units'].replace(' ','_')
    else:
        units = 'one'
    for c in e:
        if c.tag == ns_g+'bit_range':
            bit_range = '[%s:%s]' % \
                    (c.attrib['from_index'],
                     c.attrib['to_index'])
        if c.tag == ns_g+'value_set':
            if 'name' in c.attrib and c.attrib['name'] in value_map:
                # We've seen this before in a type def.
                value = c.attrib['name']
            else:
                for d in c:
                    if d.tag == ns_g+'value_enum':
                        value = get_value_enum(d,level+1)
                    elif d.tag == ns_g+'value_range':
                        value = get_value_range(d)

    info = '%s %s %s %s'%(name, bit_range, value, units)
    leader = ' '*level*indent_g
    emit(outf,leader+info)
    emit(outf,'%s;'%suffix)
    emit_interpretation(e,level,outf)

def emit_value_set(e,level,outf,prefix='',suffix='', parent_type=''):
    global indent_g
    global value_map
    type = name = '?'
    if 'name' in e.attrib:
        name = e.attrib['name']
        value_map[name] = e
    leader = ' '*level*indent_g
    if parent_type == 'field':
        emit(outf,'{\n')
    else:
        # This is a type enum definition.
        emit(outf,leader+'enum %s {\n'%name)
    for c in e:
        name = value = comment = ''
        if c.tag == ns_g+'value_enum':
            if 'interpretation' in c.attrib:
                comment = ' // '+normalize_whitespace(c.attrib['interpretation'])
            else:
                comment = ''
            name =  normalize_name(c.attrib['enum_const'])
            value = c.attrib['enum_index']
        elif c.tag == ns_g+'value_range':
            name = 'RESERVED'
            value = get_value_range(c)
        info = '%s = %s;\n%s'%(name, value, comment)
        emit(outf,leader+' '+info)

    emit(outf,leader+'}%s'%suffix)
    # emit_interpretation(e,level,outf)   parent will emit '\n'

def emit_enum(e,level,outf,prefix='',suffix=''):
    global indent_g
    global value_map
    name = type = units = '?'
    name = e.attrib['name']
    if 'field_type_unsigned' in e.attrib:
        type = scalar_type_map[normalize_whitespace(e.attrib['field_type_unsigned'])]
    if 'field_units' in e.attrib:
        units = e.attrib['field_units'].replace(' ','_')
    value_map[name] = e
    leader = ' '*level*indent_g
    info = 'enum %s %s %s {\n'%(type, name, units)
    emit(outf,leader+info)
    for c in e:
        name = value = comment = ''
        if c.tag == ns_g+'value_enum':
            if 'interpretation' in c.attrib:
                comment = ' // '+normalize_whitespace(c.attrib['interpretation'])
            else:
                comment = ''
            name =  c.attrib['enum_const']
            value = c.attrib['enum_index']
        elif c.tag == ns_g+'value_range':
            name = 'RESERVED'
            value = get_value_range(c)
        info = '%s = %s;%s\n'%(name, value, comment)
        emit(outf,leader+' '+info)

    emit(outf,leader+'}%s'%suffix)
    emit_interpretation(e,level,outf)

def emit_vtag_field(e,level,outf):    
    type = scalar_type_map[normalize_whitespace(e.attrib['field_type_unsigned'])]
    # min_count and max_count are optional in JSIDL.
    try:
        min_tag = e.attrib['min_count']
    except:
        debug(0, "Warning, setting optional min_vtag = 1\n")
        min_tag = 1
    try:
        max_tag = e.attrib['max_count']
    except:
        num_variants = len(e.getparent().getchildren()) - 1
        debug(0, "Warning, optional max_vtag == count of variant items: %d.\n"%num_variants)
        max_tag = num_variants
    emit(outf,' '*level*indent_g+'%s vtag[%s,%s];'%(type,min_tag,max_tag))
    emit_interpretation(e,level,outf)

def emit_tag_field(e,level,outf):
    '''Count field for variable_format_field and variable_field don not have min/max'''
    type = scalar_type_map[normalize_whitespace(e.attrib['field_type_unsigned'])]
    emit(outf,' '*level*indent_g+'%s tag;'%(type))
    emit_interpretation(e,level,outf)

def emit_variant(e,level,outf,prefix='',suffix='',parent_type=''):
    global indent_g
    name = '?'
    if 'name' in e.attrib:
        name = e.attrib['name']
    block_open = ' '*level*indent_g+'%svariant %s '%(prefix, name)
    emit(outf,block_open)
    attr_indent = ' '*len(block_open)
    emit(outf,' {\n')
    implied_vtag = 0
    idx = ''
    for c in e:
        stag = c.tag.replace(ns_g,'')
        if stag ==  'description':
            emit_description(c,level+1,outf)
        elif stag == 'vtag_field':
            emit_vtag_field(c,level+1,outf)
        else:
            if 'vtag' in c.attrib:
                vtag = c.attrib['vtag']
            else:
                vtag = str(implied_vtag)
                implied_vtag += 1
            if stag.find('declared_') == 0:
                emit_variable(c, level+1, outf, prefix='vtag %s: '%vtag, parent_type='variant')
            else:
                emit_type(c, level+1, outf, prefix='vtag %s: '%vtag)
    if 'item_index' in e.attrib:
        idx = ' = %s;'%e.attrib['item_index']
    emit(outf,' '*level*indent_g+'}%s // variant %s %s\n'%(idx,name,suffix))

def emit_variant_field(e,level,outf,prefix='',suffix='',parent_type=''):
    global indent_g
    name = '?'
    if 'name' in e.attrib:
        name = e.attrib['name']
    opt = ''
    if parent_type == 'list':
        opt = 'repeated '
    elif parent_type in ['record', 'sequence']:
        if 'optional' in e.attrib and e.attrib['optional'] == 'true':
            opt = 'optional '
        else:
            opt = 'required '
    block_open = ' '*level*indent_g+'%s%svariant_field %s '%(prefix,opt,name)
    emit(outf,block_open)
    attr_indent = ' '*len(block_open)
    emit(outf,' {\n')
    # index is always uint8?  Check.  variable_field should have a count field
    # for consistincy w/ variant and variable_format_field?
    for c in e:
        cstag = c.tag.replace(ns_g,'')
        if cstag == 'type_and_units_field':
            for c1 in c:
                c1stag = c1.tag.replace(ns_g,'')
                if c1stag == 'type_and_units_enum':
                    emit_tagged_field(c1, level+1, outf)
    if 'item_index' in e.attrib:
        idx = ' = %s;'%e.attrib['item_index']
    emit(outf,' '*level*indent_g+'}%s // variant_field %s %s\n'%(idx,name,suffix))

def emit_variable_format_field(e,level,outf,prefix='',suffix='',parent_type=''):
    global indent_g
    name = '?'
    if 'name' in e.attrib:
        name = e.attrib['name']
    opt = ''
    if parent_type == 'list':
        opt = 'repeated '
    elif parent_type in ['record', 'sequence']:
        if 'optional' in e.attrib and e.attrib['optional'] == 'true':
            opt = 'optional '
        else:
            opt = 'required '
    block_open = ' '*level*indent_g+'%s%svariable_format_field %s '%(prefix,opt,name)
    emit(outf,block_open)
    attr_indent = ' '*len(block_open)
    emit(outf,' {\n')
    # count_field comes after format_field
    count_type = ''
    format_list = []
    for c in e:
        cstag = c.tag.replace(ns_g,'')
        if cstag == 'count_field':
            emit_tag_field(c,level+1,outf)
        if cstag == 'format_field':
            for c1 in c:
                c1stag = c1.tag.replace(ns_g,'')
                if c1stag == 'format_enum':
                    format_list.append( (c1.attrib['index'],c1.attrib['field_format']) )

    for i,f in format_list:
        emit(outf,' '*(level+1)*indent_g+'tag %s: %s;\n'%(i,normalize_name(f)))
    if 'item_index' in e.attrib:
        idx = ' = %s;'%e.attrib['item_index']
    emit(outf,' '*level*indent_g+'}%s // variable_format_field %s %s\n'%(idx,name,suffix))

def emit_list(e,level,outf,prefix='',suffix='', parent_type=''):
    global indent_g
    name = '?'
    if 'name' in e.attrib:
        name = e.attrib['name']
    opt = ''
    if parent_type == 'list':
        opt = 'repeated '
    elif parent_type in ['record', 'sequence']:
        if 'optional' in e.attrib and e.attrib['optional'] == 'true':
            opt = 'optional '
        else:
            opt = 'required '
    block_open = ' '*level*indent_g+"%s%slist %s "%(prefix,opt,name)
    emit(outf,block_open)
    attr_indent = ' '*len(block_open)
    emit(outf,' {\n')
    list_item = None
    for c in e:
        stag = c.tag.replace(ns_g,'')
        if stag ==  'description':
            emit_description(c,level+1,outf)
        elif stag == 'count_field':
            count_type, count_name, count_min, count_max = get_count_field(c)
        else:
            list_item = c
    # Now output the dim variable and the list item
    emit(outf,' '*(level+1)*indent_g+'%s %s [%s,%s];\n'%(count_type,
                                                         count_name,
                                                         count_min,
                                                         count_max))
    if list_item is None:
        print "Warning, No list item defined in:\n"
        print etree.tostring(e.getparent(),pretty_print=True)
        emit(outf,"// NO LIST ITEM DEFINED.\n")
    else:
        stag = list_item.tag.replace(ns_g,'')
        if stag.find('declared_') != -1:
            emit_variable(list_item, level+1, outf, parent_type='list')
        elif stag == 'fixed_field':
            emit_field(list_item, level+1, outf, parent_type='list')
        elif stag == 'sequence':
            emit_sequence(list_item, level+1, outf, parent_type='list')
        elif stag == 'variant':
            emit_variant(list_item, level+1, outf, parent_type='list')
        elif stag == 'list':
            emit_list(list_item, level+1, outf, parent_type='list')
        else:
            emit_type(list_item, level+1, outf, parent_type='list')
    idx = ''
    if 'item_index' in e.attrib:
        idx = ' = %s;'%e.attrib['item_index']
    emit(outf,' '*level*indent_g+'}%s // list %s %s\n'%(idx,name,suffix))

def emit_sequence(e,level,outf,prefix='',suffix='',parent_type=''):
    global indent_g
    name = '?'
    if 'name' in e.attrib:
        name = e.attrib['name']
    opt = ''
    if parent_type == 'list':
        opt = 'repeated '
    elif parent_type in ['record', 'sequence']:
        if 'optional' in e.attrib and e.attrib['optional'] == 'true':
            opt = 'optional '
        else:
            opt = 'required '
    block_open = ' '*level*indent_g+"%s%ssequence %s "%(prefix,opt,name)
    emit(outf,block_open)
    attr_indent = ' '*len(block_open)
    emit(outf,' {\n')
    item_index = 1
    for c in e:
        if c.tag.find(JSIDL_PLUS_NS) == 0:
            continue
        try:
            stag = c.tag.replace(ns_g,'')
        except:
            # Ignore elements like <!-- --> comments with no tag.
            continue
        # We no longer emit explicit presence_vector
        # if stag ==  'presence_vector':
        #     emit_pv(c,level+1,outf)
        if stag ==  'description':
            emit_description(c,level+1,outf, parent_type='sequence')
        else:
            # Regular item.
            c.attrib['item_index'] = str(item_index)
            item_index += 1
            if stag.find('declared_') == 0:
                emit_variable(c, level+1, outf, parent_type='sequence')
            elif stag == 'sequence':
                emit_sequence(c, level+1, outf, parent_type='sequence')
            elif stag == 'variant':
                emit_variant(c, level+1, outf, parent_type='sequence')
            elif stag == 'list':
                emit_list(c, level+1, outf, parent_type='sequence')
            else:
                emit_type(c, level+1, outf, parent_type='sequence')
    idx = ''
    if 'item_index' in e.attrib:
        idx = ' = %s;'%e.attrib['item_index']
    emit(outf,' '*level*indent_g+'}%s // sequence %s %s\n'%(idx, name,suffix))

def emit_children(e,level,outf,prefix='',suffix='', parent_type=''):
    """Use this emitter when we want to ignore an enclosing
    element like 'body'"""
    global indent_g
    debug(7,'children of %s, parent_type=%s\n'%(e.attrib['name'],parent_type))
    for c in e:
        stag = c.tag.replace(ns_g,'')
        if stag ==  'presence_vector':
            emit_pv(c,level,outf)
        elif stag ==  'description':
            emit_description(c,level,outf)
        elif stag.find('declared_') == 0:
            emit_variable(c, level, outf, parent_type=parent_type)
        elif stag == 'sequence':
            emit_sequence(c, level, outf, parent_type=parent_type)
        elif stag == 'variant':
            emit_variant(c, level, outf, parent_type=parent_type)
        elif stag == 'list':
            emit_list(c, level, outf, parent_type=parent_type)
        else:
            emit_type(c, level, outf, parent_type=parent_type)

def emit_protocol_behavior(e,level,outf,prefix='',suffix=''):
    # Don't produce an enclosing 'protocol_behavior' wrapper.
    debug(7,'emit_protocol_behavior\n')
    emit(outf,' '*level*indent_g+'protocol {\n')
    interp = ''
    for c in e:
        cstag = c.tag.replace(ns_g,'')
        if cstag == 'start':
            name = c.attrib['state_name']
            if 'interpretation' in c.attrib:
                interp = ' // '+normalize_interpretation(c)
            emit(outf,' '*(level+1)*indent_g+'start %s;%s\n'%(name,interp))
        elif cstag == 'state_machine':
            emit_state_machine(c,level+1,outf)
    emit(outf,' '*level*indent_g+'}\n')

def emit_state_machine(e,level,outf,prefix='',suffix=''):
    global indent_g
    debug(7,'emit_state_machine\n')
    name = '?'
    if 'name' in e.attrib:
        name = e.attrib['name']
    block_open = ' '*level*indent_g+'state_machine %s '%(name)
    emit(outf,block_open)
    attr_indent = ' '*len(block_open)
    emit(outf,' {\n')
    emit_interpretation(e,level+1,outf)
    for c in e:
        cstag = c.tag.replace(ns_g,'')
        if cstag in ['default_state','state']:
            emit_state(c, level+1, outf)
        else:
            emit_type(c, level+1, outf)
    emit(outf,' '*level*indent_g+'} // state_machine %s %s\n'%(name,suffix))

def emit_state(e,level,outf,prefix='',suffix=''):
    global indent_g
    debug(7,'emit_state\n')
    name = ''
    if 'name' in e.attrib:
        name = e.attrib['name']
    state_type = e.tag.replace(ns_g,'')
    if state_type == 'default_state':
        state_type = 'state'
        name='Default'
    block_open = ' '*level*indent_g+'%s%s %s '%(prefix,state_type,name)
    emit(outf,block_open)
    attr_indent = ' '*len(block_open)
    try:
        initial_state = e.attrib['initial_state']
    except:
        initial_state = ''
    emit(outf,' {\n')
    emit_interpretation(e,level+1,outf)
    for c in e:
        if isinstance(c,etree._Comment):
            emit_comment(c, level+1, outf)
        else:
            cstag = c.tag.replace(ns_g,'')
            if cstag in ['entry', 'exit', 'transition']:
                emit_transition(c, level+1, outf)
            elif cstag == 'state':
                if c.attrib['name'] == initial_state:
                    pre = 'initial '
                else:
                    pre = ''
                emit_state(c, level+1, outf, prefix=pre)
            elif cstag == 'default_state':
                emit_state(c, level+1, outf)
            else:
                emit_type(c, level+1, outf)
    suffix = ' // %s'%block_open.strip() + suffix
    emit(outf,' '*level*indent_g+'}%s\n'%suffix)

def emit_transition(e,level,outf,prefix='',suffix=''):
    global indent_g
    debug(7,'emit_transition\n')
    indent = ' '*level*indent_g
    name = ''
    cstag = e.tag.replace(ns_g,'')
    if cstag == 'transition':
        if 'name' in e.attrib:
            name = e.attrib['name']
        if name == 'accessControl.events.transport.Receive':
            # This is a receive message transition.
            # first parameter is the message name, rest are message params
            msg_name = None
            msg_comment = ''
            sub_indent = ' '*(level+1)*indent_g
            for c in e:
                cstag = c.tag.replace(ns_g,'')
                if cstag == 'parameter':
                    msg_param = c.attrib['value']
                    if 'interpretation' in c.attrib:
                        s = normalize_interpretation(c)
                        if len(s) > 0:
                            msg_comment = ' /* %s */'%s
                    if msg_name is None:
                        msg_name = c.attrib['type']
                        emit(outf,indent
                             +'%s ('%(msg_name)
                             +'Message : %s %s'%(msg_param,msg_comment))
                    elif c.attrib['type'] == 'Receive.Body.ReceiveRec':
                        emit(outf,',\n'+sub_indent+'Transport : %s %s'%(msg_param,msg_comment))
                    else:
                        emit(outf,',\n'+sub_indent+'%s : data %s'%(msg_param,msg_comment))
            emit(outf,') ')
            emit_end_state(e,level+1,outf)
            emit(outf,' {\n')
        else:
            emit(outf,indent+name)
            emit_params(e,level+1,outf)
            emit_guard(e,level+1,outf)
            emit_end_state(e,level+1,outf)
            emit(outf,' {\n')
    elif cstag in ['entry', 'exit']:
        block_open = indent+'%s {\n'%(cstag)
        emit(outf,block_open)
    else:
        raise ValueError('Unknown tag in emit_transition: %s'%cstag)
    emit_interpretation(e,level+1,outf)
    for c in e:
        cstag = c.tag.replace(ns_g,'')
        if cstag == 'action':
            emit_action(c, level+1, outf)
        elif cstag == 'send_action':
            emit_send_action(c, level+1, outf)
        else:
            pass # TODO: all other children already processed
    emit(outf,' '*level*indent_g+'}%s\n'%suffix)

def emit_guard(node,level,outf):
    global indent_g
    indent = ' '*level*indent_g
    for c in node:
        cstag = c.tag.replace(ns_g,'')
        if cstag == 'guard':
            guard = normalize_whitespace(c.attrib['condition'])
            emit(outf,'\n'+indent+'[ '+guard+' ]')

def emit_end_state(node,level,outf):
    global indent_g
    indent = ' '*level*indent_g
    debug(7, "emit_end_state for %s\n"%node.attrib['name'])
    for c in node:
        cstag = c.tag.replace(ns_g,'')
        debug(7, "cstag = %s\n"%cstag)
        if cstag == 'simple':
            for subc in c:
                subcstag = subc.tag.replace(ns_g,'')
                if subcstag == 'end_state':
                    emit(outf,'\n'+indent+'next(%s)'%subc.attrib['state'])
        elif cstag == 'push':
            for subc in c:
                subcstag = subc.tag.replace(ns_g,'')
                if subcstag == 'end_state':
                    emit(outf,'\n'+indent+'push(%s)'%subc.attrib['state'])
        elif cstag == 'pop':
            debug(7, "next state pop() for %s\n"%node.attrib['name'])
            emit(outf,'\n'+indent+'pop()')
        elif cstag == 'internal':
            debug(7, "next state internal() for %s\n"%node.attrib['name'])
            emit(outf,'\n'+indent+'internal()')
        elif cstag == 'end_state':
            emit(outf,'\n'+indent+c.attrib['state'])

def emit_action(e,level,outf,prefix='',suffix=''):
    global indent_g
    debug(7,'emit_action\n')
    name = '?'
    indent = ' '*level*indent_g
    if 'name' in e.attrib:
        name = e.attrib['name']
    if name == 'accessControl.events.transport.Send':
        # TODO: Reconcile with latest AS5684.
        # This is a send message action.
        # first parameter is the message name, rest are message params
        msg_name = None
        msg_comment = ''
        sub_indent = ' '*(level+1)*indent_g
        for c in e:
            cstag = c.tag.replace(ns_g,'')
            if cstag == 'argument':
                msg_param = unquote(c.attrib['value'])
                if 'interpretation' in c.attrib:
                    s = normalize_interpretation(c)
                    if len(s) > 0:
                        msg_comment = ' /* '+s+' */'
                if msg_name is None:
                    msg_name = msg_param
                    emit(outf,indent
                         +'%s( %s\n'%(msg_name,msg_comment)
                         +sub_indent+'msg.src_id')
                elif c.attrib['value'] == 'transportData':
                    pass
                else:
                    emit(outf,',\n'+sub_indent+'%s %s'%(msg_param,msg_comment))
        emit(outf,'\n'+sub_indent+');')
    else:
        emit(outf,indent+'%s'%(name))
        emit_args(e,level+1,outf)
        emit(outf,'%s;'%suffix)
    emit_interpretation(e,level,outf)

def emit_send_action(e,level,outf,prefix='',suffix=''):
    global indent_g
    debug(7,'emit_send_action\n')
    name = '?'
    indent = ' '*level*indent_g
    if 'name' in e.attrib:
        name = e.attrib['name']
    if name == 'accessControl.events.transport.Send':
        # TODO: Reconcile with latest AS5684.
        # This is a send message action.
        # first parameter is the message name, rest are message params
        msg_name = None
        msg_comment = ''
        sub_indent = ' '*(level+1)*indent_g
        for c in e:
            cstag = c.tag.replace(ns_g,'')
            if cstag == 'argument':
                msg_param = unquote(c.attrib['value'])
                if 'interpretation' in c.attrib:
                    s = normalize_interpretation(c)
                    if len(s) > 0:
                        msg_comment = ' /* '+s+' */'
                if msg_name is None:
                    msg_name = msg_param
                    emit(outf,indent
                         +'%s( %s\n'%(msg_name,msg_comment)
                         +sub_indent+'msg.src_id')
                elif c.attrib['value'] == 'transportData':
                    pass
                else:
                    emit(outf,',\n'+sub_indent+'%s %s'%(msg_param,msg_comment))
        emit(outf,'\n'+sub_indent+');')
    else:
        emit(outf,indent+'%s'%(name))
        emit_args(e,level+1,outf)
        emit(outf,'%s;'%suffix)
    emit_interpretation(e,level,outf)

def emit_references(e, level, outf):
    global indent_g
    emit(outf,' '*level*indent_g+'references {\n')
    for c in e:
       cstag = c.tag.replace(ns_g,'')
       if cstag == 'client_of':
           emit(outf,' '*(level+1)*indent_g+'%s %s "%s" "%s";\n'% \
                (tag_map[cstag],
                 c.attrib['name'],
                 c.attrib['id'],
                 c.attrib['version']))
       elif cstag == 'inherits_from':
           emit(outf,' '*(level+1)*indent_g+'%s %s "%s" "%s";\n'% \
                (tag_map[cstag],
                 c.attrib['name'],
                 c.attrib['id'],
                 c.attrib['version']))
    emit(outf,' '*level*indent_g+'}\n')           

def emit_type(e,level,outf,prefix='',suffix='', parent_type=''):
    global indent_g
    global collecting_types_g
    global current_ns_alias_g
    global current_ns_scope_g
    global service_def_name_g

    # Ignore jsidl_plus
    if e.tag.find(JSIDL_PLUS_NS) == 0:
        # Don't support JSIDL_PLUS_NS tags like mxCell
        return
    # Handle comments, which don't have a tag.
    if isinstance(e,etree._Comment):
        emit_comment(e, level, outf)
        return
    stag = e.tag.replace(ns_g,'')
    #
    if stag == 'pseudo_start_state':
        emit(outf, ' '*level*indent_g+"// removed pseudo_start_state element\n")
        return
    try:
        sect = tag_map[stag]
    except:
        sect = stag
    try:
        name = e.attrib['name']
    except:
        name = ''

    typeset_uri = None
    typeset_version = None
    attrs = ''
    # Enable collection of defined types in hierarchical namespace.
    if stag == 'declared_type_set':
        if name == 'types':
            # Can't use a keyword for the name, so use the service
            # name w/ types appended.  Using 'types' is a habit in
            # AS-4 JSIDL
            name = service_def_name_g+'Types'
        if name not in type_declarations_g:
            type_declarations_g[name] = {}
        collecting_types_g = type_declarations_g[name]
        current_ns_scope_g.append(e.attrib['name'])  # push current ns
        if 'id' in e.attrib:
            typeset_uri = e.attrib['id']
        if 'version' in e.attrib:    
            typeset_version = e.attrib['version']
        if typeset_uri and typeset_version:    
            attrs = '( id="%s", version="%s" )'%(typeset_uri, typeset_version)

    if ( stag.find('declared_') == 0 and
         stag not in ['declared_const_set', 'declared_type_set',
                      'declared_header','declared_footer']):
        if stag == 'declared_type_set_ref':
            emit(outf,' '*level*indent_g+'using "%s" "%s" as %s;\n'% \
                 (e.attrib['id'],
                  e.attrib['version'],
                  e.attrib['name']))
            current_ns_alias_g[e.attrib['name']] = e.attrib['id']
        else:
            # If declared item, don't recurse.
            emit_variable(e, level ,outf, parent_type=parent_type)
    else:
        # Use generic emitter.
        opt = ''
        if parent_type == 'list':
            opt = 'repeated '
        elif parent_type in ['record', 'sequence']:
            if 'optional' in e.attrib and e.attrib['optional'] == 'true':
                opt = 'optional '
            else:
                opt = 'required '
        block_open = ' '*level*indent_g+"%s%s%s %s%s"%(prefix,opt,sect,name,attrs)
        emit(outf,block_open)
        attr_indent = ' '*len(block_open)
        # These elements no long need catch-all dump of elements attributes.
        if stag not in ['record','list','sequence','variant',
                        'message_def','message_set',
                        'references','declared_type_set',
                        'internal_events_set','event_def',
                        'state']:
            emit_attrs(e.attrib,attr_indent,outf)
        emit(outf,' {')
        emit_interpretation(e,level+1,outf)
        # Handle indexed child items in containers.
        if stag in ['record','sequence']:
            item_index = 1;   # for containers with fixed, indexed items.
        else:
            item_index = -1;
        for c in e:
            try:
                cstag = ''
                try:
                    cstag = c.tag.replace(ns_g,'')
                except:
                    pass  # Some elements like _Comment don't have tags.
                if cstag == 'service_def':
                    service_def_name_g = c.attrib['name']
                    emit_type(c,level+1,outf)
                elif cstag ==  'description':
                    emit_description(c,level+1,outf)
                elif cstag == 'assumptions':
                    emit_assumptions(c,level+1,outf)
                elif cstag == 'references':
                    emit_references(c, level+1, outf)
                elif cstag == 'input_set':
                    for message_def in c:
                        emit_message(message_def,level+1,'input_set',outf)
                elif cstag == 'output_set':
                    for message_def in c:
                        emit_message(message_def,level+1,'output_set',outf)
                elif cstag == 'message_def':
                    if 'type' in c.attrib:
                        # Only for older versions of JSIDL
                        if c.attrib['type'] == 'input':
                            emit_message(c,level+1,'input_set',outf)
                        elif c.attrib['type'] == 'output':
                            emit_message(c,level+1,'output_set',outf)
                    else:        
                        emit_message(c,level+1,'type_def',outf)
                elif cstag == 'presence_vector':
                    continue
                    # See comment for def emit_pv()
                    # if item_index >= 0:
                    #     c.attrib['item_index'] = str(item_index)
                    # emit_pv(c,level+1,outf)
                    # if item_index >= 0:
                    #    item_index += 1;
                elif cstag == 'fixed_field':
                    if item_index >= 0:
                        c.attrib['item_index'] = str(item_index)
                    emit_field(c,level+1,outf,parent_type=stag)
                    if item_index >= 0:
                        item_index += 1;
                elif cstag == 'variable_field':
                    if item_index >= 0:
                        c.attrib['item_index'] = str(item_index)
                    emit_variant_field(c,level+1,outf,parent_type=stag)
                    if item_index >= 0:
                        item_index += 1;
                elif cstag in ['variable_length_string','fixed_length_string']:
                    if item_index >= 0:
                        c.attrib['item_index'] = str(item_index)
                    emit_string(c,level+1,outf,parent_type=stag)
                    if item_index >= 0:
                        item_index += 1;
                elif cstag == 'count_field':
                    emit_count_field(c,level+1,outf)
                elif cstag == 'bit_field':
                    if item_index >= 0:
                        c.attrib['item_index'] = str(item_index)
                    emit_bit_field(c,level+1,outf,parent_type=stag)
                    if item_index >= 0:
                        item_index += 1;
                elif cstag == 'variable_length_field':
                    if item_index >= 0:
                        c.attrib['item_index'] = str(item_index)
                    emit_variable_length_field(c,level+1,outf,parent_type=stag)
                    if item_index >= 0:
                        item_index += 1;
                elif cstag == 'variable_format_field':
                    if item_index >= 0:
                        c.attrib['item_index'] = str(item_index)
                    emit_variable_format_field(c,level+1,outf,parent_type=stag)
                    if item_index >= 0:
                        item_index += 1;
                elif cstag == 'value_set':
                    emit_value_set(c,level+1,outf)
                elif cstag == 'enum':
                    # 'enum' is a synonym for a value_set that contains only value_enums.
                    emit_enum(c,level+1,outf)
                elif cstag == 'vtag_field':
                    emit_vtag_field(c,level+1,outf)
                elif cstag == 'variant':
                    if item_index >= 0:
                        c.attrib['item_index'] = str(item_index)
                    emit_variant(c,level+1,outf,parent_type=stag)
                    if item_index >= 0:
                        item_index += 1;
                elif cstag == 'list':
                    if item_index >= 0:
                        c.attrib['item_index'] = str(item_index)
                    emit_list(c,level+1,outf,parent_type=stag)
                    if item_index >= 0:
                        item_index += 1;
                elif cstag == 'sequence':
                    if item_index >= 0:
                        c.attrib['item_index'] = str(item_index)
                    emit_sequence(c,level+1,outf,parent_type=stag)
                    if item_index >= 0:
                        item_index += 1;
                elif cstag == 'const_def':
                    emit_const(c,level+1,outf)
                elif cstag.find('_set') != -1:
                    emit_type(c, level+1 ,outf, parent_type=stag)
                elif cstag in ['header', 'declared_header', 'footer', 'status']:
                    pass # ignore these tags for now
                elif cstag == 'body':
                    emit_children(c,level+1,outf,parent_type=stag)
                elif cstag == 'protocol_behavior':
                    emit_protocol_behavior(c,level+1,outf)
                elif cstag == 'state':
                    emit_state(c,level+1,outf)
                else:
                    # Increment items index for non-comment nodes only.
                    if item_index >= 0 and not isinstance(c,etree._Comment):
                        c.attrib['item_index'] = str(item_index)
                    emit_type(c, level+1 ,outf, parent_type=stag)
                    if item_index >= 0 and not isinstance(c,etree._Comment):
                        item_index += 1;
            except Exception, ex:
                sys.stderr.write(etree.tostring(c,pretty_print=True))
                sys.stderr.write(traceback.format_exc())
        # Close generic type.
        if 'item_index' in e.attrib:
            suffix = suffix + ' = %s;'%e.attrib['item_index']
        emit(outf,' '*level*indent_g+'}%s// %s %s\n'%(suffix,tag_map[stag],name))

    # Disable collecting types and pop current ns
    if stag == 'declared_type_set':
        current_ns_scope_g.pop()
        collecting_types_g = None

def process(t,outf):
    global service_def_name_g
    root = t.getroot()
    service_def_name_g = root.attrib['name']
    emit_type(root,0,outf)

def print_usage(f):
    f.write("python jsidl2jaus.py OPTIONS\n")
    f.write("   -i file \n")
    f.write("   --in=file    file containing XML conforming to JSIDL grammar, defaults to stdin.\n")
    f.write("   -o file\n")
    f.write("   --out=file   file for jdl output, defaults to stdout.\n")

if __name__=='__main__':
    p = optparse.OptionParser()
    p.add_option("-i", action="store", dest="infile")
    p.add_option("--in", action="store", dest="infile")
    p.add_option("-o", action="store", dest="outfile")
    p.add_option("--out", action="store", dest="outfile")
    p.add_option("-d", action="store", dest="debug")
    p.add_option("--debug", action="store", dest="debug")
    p.add_option("-n", action="store", dest="ns")
    p.add_option("--ns", action="store", dest="ns")

    # Get args and input/output files set up.
    opts, args = p.parse_args()
    if opts.ns:
        ns_g = opts.ns
    if opts.debug:
        try:
            DEBUG = int(opts.debug,10)
        except:
            debug(7,"Can't set debug level to %s"%opts.debug)
    if opts.infile:
        tree = etree.parse(opts.infile)
    else:
        root = etree.fromstring(sys.stdin.read())
        tree = etree.ElementTree(root)
    if opts.outfile:
        try:
            outf = file(opts.outfile, 'wb')
        except IOError, e:
            print "Can't open %s for write, %s"%(e.filename, e.strerror)
            raise SystemExit
    else:
        outf = sys.stdout
    # Do it
    is_jsidl_g = (ns_g == JSIDL_NS)
    process(tree,outf)
    outf.close()
