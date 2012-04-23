# $ANTLR 3.1.1 jaus2jsidl.g 2011-03-15 17:19:49

import sys
from antlr3 import *
from antlr3.compat import set, frozenset
         
from lxml import etree
from copy import deepcopy
from pprint import pformat
import sys
import string
DEBUG_LEVEL=1
def debug(n,s):
    global DEBUG_LEVEL
    if n <= DEBUG_LEVEL:
        sys.stderr.write(s)

def compress_ws(s):
    rets=[]
    inws = False
    for c in s:
        if c in string.whitespace:
            if not inws:
                rets.append(' ')
                inws = True
        else:
            rets.append(c)
            inws = False
    return ''.join(rets)

def get_pv_unsigned_field(count,map):
    if count <= 8:
        ftu = map['uint8']
    elif count <= 16:
        ftu = map['uint16']
    elif count <= 32:
        ftu = map['uint32']
    elif count <= 64:
        ftu = map['uint64']
    return ftu




# for convenience in actions
HIDDEN = BaseRecognizer.HIDDEN

# token types
EOF=-1
T__93=93
T__94=94
T__91=91
T__92=92
T__90=90
FloatSuffix=25
NonIntegerNumber=24
T__99=99
T__98=98
T__97=97
T__96=96
HexPrefix=22
T__95=95
T__80=80
T__81=81
T__82=82
T__83=83
DOUBLELITERAL=14
RELATIONAL_OP=9
INTLITERAL=13
T__85=85
T__84=84
T__87=87
T__86=86
T__89=89
T__88=88
LONGLITERAL=21
LongSuffix=20
URI=6
UNIT=10
WS=30
T__71=71
T__72=72
T__70=70
CHARLITERAL=29
SL_COMMENT=17
T__76=76
T__75=75
T__74=74
EscapeSequence=28
T__73=73
T__79=79
T__78=78
T__77=77
T__68=68
T__69=69
T__66=66
T__67=67
T__64=64
T__65=65
T__62=62
T__63=63
IntegerNumber=19
Exponent=23
VERSION=7
T__61=61
ID=5
T__60=60
HexDigit=18
MESSAGE_CODE=12
T__55=55
T__56=56
ML_COMMENT=4
T__57=57
T__58=58
T__51=51
T__52=52
T__53=53
T__54=54
T__107=107
T__108=108
T__109=109
T__59=59
T__103=103
T__104=104
T__105=105
T__106=106
T__50=50
T__42=42
T__43=43
T__40=40
T__41=41
T__46=46
T__47=47
T__44=44
T__45=45
T__48=48
T__49=49
T__102=102
T__101=101
T__100=100
ITEM_CARDINALITY=15
T__31=31
T__32=32
T__33=33
T__34=34
T__35=35
DoubleSuffix=26
FIELD_FORMAT=16
T__36=36
T__37=37
T__38=38
T__39=39
STRINGLITERAL=8
MESSAGE_CLASS=11
FLOATLITERAL=27

# token names
tokenNames = [
    "<invalid>", "<EOR>", "<DOWN>", "<UP>", 
    "ML_COMMENT", "ID", "URI", "VERSION", "STRINGLITERAL", "RELATIONAL_OP", 
    "UNIT", "MESSAGE_CLASS", "MESSAGE_CODE", "INTLITERAL", "DOUBLELITERAL", 
    "ITEM_CARDINALITY", "FIELD_FORMAT", "SL_COMMENT", "HexDigit", "IntegerNumber", 
    "LongSuffix", "LONGLITERAL", "HexPrefix", "Exponent", "NonIntegerNumber", 
    "FloatSuffix", "DoubleSuffix", "FLOATLITERAL", "EscapeSequence", "CHARLITERAL", 
    "WS", "'service'", "'('", "'id'", "'='", "','", "'version'", "')'", 
    "'{'", "'}'", "';'", "'description'", "'assumptions'", "'references'", 
    "'inherits_from'", "'client_of'", "'constants'", "'using'", "'types'", 
    "'messages'", "'events'", "'protocol'", "'start'", "'state_machine'", 
    "'initial'", "'state'", "'Default'", "'entry'", "'exit'", "'nil'", "'['", 
    "']'", "'next'", "'/'", "'push'", "'pop'", "'internal'", "'||'", "'or'", 
    "'&&'", "'and'", "'+'", "'-'", "'*'", "'%'", "'!'", "'.'", "':'", "'True'", 
    "'False'", "'as'", "'event'", "'uint8'", "'uint16'", "'uint24'", "'uint32'", 
    "'uint64'", "'int8'", "'int16'", "'int32'", "'int64'", "'float'", "'double'", 
    "'enum'", "'string'", "'vstring'", "'field'", "'variant_field'", "'tag'", 
    "'variable_format_field'", "'bit_field'", "'<'", "'>'", "'list'", "'repeated'", 
    "'variant'", "'vtag'", "'sequence'", "'record'", "'returns'"
]




class jaus2jsidlParser(Parser):
    grammarFileName = "jaus2jsidl.g"
    antlr_version = version_str_to_tuple("3.1.1")
    antlr_version_str = "3.1.1"
    tokenNames = tokenNames

    def __init__(self, input, state=None):
        if state is None:
            state = RecognizerSharedState()

        Parser.__init__(self, input, state)


        self.dfa82 = self.DFA82(
            self, 82,
            eot = self.DFA82_eot,
            eof = self.DFA82_eof,
            min = self.DFA82_min,
            max = self.DFA82_max,
            accept = self.DFA82_accept,
            special = self.DFA82_special,
            transition = self.DFA82_transition
            )

        self.dfa83 = self.DFA83(
            self, 83,
            eot = self.DFA83_eot,
            eof = self.DFA83_eof,
            min = self.DFA83_min,
            max = self.DFA83_max,
            accept = self.DFA83_accept,
            special = self.DFA83_special,
            transition = self.DFA83_transition
            )

        self.dfa84 = self.DFA84(
            self, 84,
            eot = self.DFA84_eot,
            eof = self.DFA84_eof,
            min = self.DFA84_min,
            max = self.DFA84_max,
            accept = self.DFA84_accept,
            special = self.DFA84_special,
            transition = self.DFA84_transition
            )



               
        # Global vars to pass state between rules.  Klugey, but works until we find better way.
        self.comment = ''
        self.item_index = None
        self.vtag_stack = []
        self.jsidl_ns = "urn:jaus:jsidl:1.0"
        # self.jsidl_ns = "urn:jaus:jsidl:exp"

        # Init the output tree.
        self.tree = None
        self.current_node = None
        self.second_pass_tag_resolution = {}  # name:tag
        self.optional_count = 0 # used for presence_vector construction
        self.const_map = {}
        self.value_set_map = {}
        self.scalar_map = {
            'uint16':'unsigned short integer',
            'uint24':'RGB',
            'uint32':'unsigned integer',
            'uint64':'unsigned long integer',
            'uint8':'unsigned byte',
            'int8':'byte',
            'int16':'short integer',
            'int32':'integer',
            'int64':'long integer',
            'float':'float',
            'double':'long float',
            'string':'fixed_length_string',
            'vstring':'variable_length_string',
            # 'timestamp':'TimeStamp'
        }
        # Hack some common types, like Timestamp
        ds = etree.Element('bit_field')
        ds.attrib['name'] = 'DateStamp'
        self.type_map = { ds.attrib['name']:ds }
        ts = etree.Element('bit_field')
        ts.attrib['name'] = 'TimeStamp'
        self.type_map.update({ ts.attrib['name']:ts })




                


        



    # $ANTLR start "jaus"
    # jaus2jsidl.g:121:1: jaus : ( service_set | type_set ) ;
    def jaus(self, ):

        try:
            try:
                # jaus2jsidl.g:122:5: ( ( service_set | type_set ) )
                # jaus2jsidl.g:123:5: ( service_set | type_set )
                pass 
                # jaus2jsidl.g:123:5: ( service_set | type_set )
                alt1 = 2
                LA1_0 = self.input.LA(1)

                if (LA1_0 == ML_COMMENT or LA1_0 == SL_COMMENT or LA1_0 == 31) :
                    alt1 = 1
                elif (LA1_0 == 48) :
                    alt1 = 2
                else:
                    nvae = NoViableAltException("", 1, 0, self.input)

                    raise nvae

                if alt1 == 1:
                    # jaus2jsidl.g:123:7: service_set
                    pass 
                    self._state.following.append(self.FOLLOW_service_set_in_jaus82)
                    self.service_set()

                    self._state.following.pop()


                elif alt1 == 2:
                    # jaus2jsidl.g:123:21: type_set
                    pass 
                    self._state.following.append(self.FOLLOW_type_set_in_jaus86)
                    self.type_set()

                    self._state.following.pop()



                #action start
                     
                for k,v in self.second_pass_tag_resolution.items():
                    if v in self.type_map:
                        k.tag='declared_'+self.type_map[ v ].tag
                        if self.jsidl_ns == "urn:jaus:jsidl:1.0" and k.tag == 'declared_variant':
                            # Quirk in JSIDL, need to fix.
                            # debug(4, "Removing disallowed 'optional' attr in declared_variant %s\n"%(k.attrib['name']))
                            #if 'optional' in k.attrib:
                            #    del k.attrib['optional']
                            pass
                    else:
                        debug(0,"Can't resolve the real tag type for declared_type %s\n"%v)
                svc = self.tree.getroot()
                tree = etree.ElementTree(svc)
                sys.stdout.write("<?xml version='1.0' encoding='UTF-8'?>\n")
                sys.stdout.write(etree.tostring(tree,pretty_print=True))
                sys.stdout.write('\n')
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "jaus"


    # $ANTLR start "service_set"
    # jaus2jsidl.g:145:1: service_set : ( ml_comment | ML_COMMENT )? 'service' ID '(' 'id' '=' URI ',' 'version' '=' VERSION ')' '{' ( ml_comment | ML_COMMENT )? ( description ( ml_comment | ML_COMMENT )? )? ( assumptions ( ml_comment | ML_COMMENT )? )? (refs= references ( ml_comment | ML_COMMENT )? )? ( constant_set ( ml_comment | ML_COMMENT )? )* ( type_set ( ml_comment | ML_COMMENT )? )* ( message_set ( ml_comment | ML_COMMENT )? )? ( internal_event_set ( ml_comment | ML_COMMENT )? )? ( protocol_behavior ( ml_comment | ML_COMMENT )? )? '}' ( ';' )? ;
    def service_set(self, ):

        ID1 = None
        URI2 = None
        VERSION3 = None

        try:
            try:
                # jaus2jsidl.g:146:5: ( ( ml_comment | ML_COMMENT )? 'service' ID '(' 'id' '=' URI ',' 'version' '=' VERSION ')' '{' ( ml_comment | ML_COMMENT )? ( description ( ml_comment | ML_COMMENT )? )? ( assumptions ( ml_comment | ML_COMMENT )? )? (refs= references ( ml_comment | ML_COMMENT )? )? ( constant_set ( ml_comment | ML_COMMENT )? )* ( type_set ( ml_comment | ML_COMMENT )? )* ( message_set ( ml_comment | ML_COMMENT )? )? ( internal_event_set ( ml_comment | ML_COMMENT )? )? ( protocol_behavior ( ml_comment | ML_COMMENT )? )? '}' ( ';' )? )
                # jaus2jsidl.g:147:9: ( ml_comment | ML_COMMENT )? 'service' ID '(' 'id' '=' URI ',' 'version' '=' VERSION ')' '{' ( ml_comment | ML_COMMENT )? ( description ( ml_comment | ML_COMMENT )? )? ( assumptions ( ml_comment | ML_COMMENT )? )? (refs= references ( ml_comment | ML_COMMENT )? )? ( constant_set ( ml_comment | ML_COMMENT )? )* ( type_set ( ml_comment | ML_COMMENT )? )* ( message_set ( ml_comment | ML_COMMENT )? )? ( internal_event_set ( ml_comment | ML_COMMENT )? )? ( protocol_behavior ( ml_comment | ML_COMMENT )? )? '}' ( ';' )?
                pass 
                # jaus2jsidl.g:147:9: ( ml_comment | ML_COMMENT )?
                alt2 = 3
                LA2_0 = self.input.LA(1)

                if (LA2_0 == SL_COMMENT) :
                    alt2 = 1
                elif (LA2_0 == ML_COMMENT) :
                    alt2 = 2
                if alt2 == 1:
                    # jaus2jsidl.g:147:10: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_service_set121)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt2 == 2:
                    # jaus2jsidl.g:147:23: ML_COMMENT
                    pass 
                    self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_service_set125)



                self.match(self.input, 31, self.FOLLOW_31_in_service_set137)
                ID1=self.match(self.input, ID, self.FOLLOW_ID_in_service_set139)
                self.match(self.input, 32, self.FOLLOW_32_in_service_set149)
                self.match(self.input, 33, self.FOLLOW_33_in_service_set151)
                self.match(self.input, 34, self.FOLLOW_34_in_service_set153)
                URI2=self.match(self.input, URI, self.FOLLOW_URI_in_service_set155)
                self.match(self.input, 35, self.FOLLOW_35_in_service_set157)
                self.match(self.input, 36, self.FOLLOW_36_in_service_set159)
                self.match(self.input, 34, self.FOLLOW_34_in_service_set161)
                VERSION3=self.match(self.input, VERSION, self.FOLLOW_VERSION_in_service_set163)
                self.match(self.input, 37, self.FOLLOW_37_in_service_set165)
                #action start
                         
                self.tree = etree.ElementTree()
                self.tree._setroot(etree.Element('service_def'))
                self.current_node = self.tree.getroot()
                n = self.current_node
                n.attrib['name'] = ID1.text
                n.attrib['id'] = URI2.text[1:-1]  # strip quotes
                n.attrib['version'] = VERSION3.text[1:-1]  # strip quotes
                n.attrib['xmlns'] = self.jsidl_ns
                    
                #action end
                self.match(self.input, 38, self.FOLLOW_38_in_service_set185)
                # jaus2jsidl.g:161:9: ( ml_comment | ML_COMMENT )?
                alt3 = 3
                LA3_0 = self.input.LA(1)

                if (LA3_0 == SL_COMMENT) :
                    alt3 = 1
                elif (LA3_0 == ML_COMMENT) :
                    alt3 = 2
                if alt3 == 1:
                    # jaus2jsidl.g:161:10: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_service_set196)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt3 == 2:
                    # jaus2jsidl.g:161:23: ML_COMMENT
                    pass 
                    self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_service_set200)



                # jaus2jsidl.g:162:9: ( description ( ml_comment | ML_COMMENT )? )?
                alt5 = 2
                LA5_0 = self.input.LA(1)

                if (LA5_0 == 41) :
                    alt5 = 1
                if alt5 == 1:
                    # jaus2jsidl.g:162:10: description ( ml_comment | ML_COMMENT )?
                    pass 
                    self._state.following.append(self.FOLLOW_description_in_service_set213)
                    self.description()

                    self._state.following.pop()
                    # jaus2jsidl.g:162:22: ( ml_comment | ML_COMMENT )?
                    alt4 = 3
                    LA4_0 = self.input.LA(1)

                    if (LA4_0 == SL_COMMENT) :
                        alt4 = 1
                    elif (LA4_0 == ML_COMMENT) :
                        alt4 = 2
                    if alt4 == 1:
                        # jaus2jsidl.g:162:23: ml_comment
                        pass 
                        self._state.following.append(self.FOLLOW_ml_comment_in_service_set216)
                        self.ml_comment()

                        self._state.following.pop()


                    elif alt4 == 2:
                        # jaus2jsidl.g:162:36: ML_COMMENT
                        pass 
                        self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_service_set220)






                # jaus2jsidl.g:163:9: ( assumptions ( ml_comment | ML_COMMENT )? )?
                alt7 = 2
                LA7_0 = self.input.LA(1)

                if (LA7_0 == 42) :
                    alt7 = 1
                if alt7 == 1:
                    # jaus2jsidl.g:163:10: assumptions ( ml_comment | ML_COMMENT )?
                    pass 
                    self._state.following.append(self.FOLLOW_assumptions_in_service_set235)
                    self.assumptions()

                    self._state.following.pop()
                    # jaus2jsidl.g:163:22: ( ml_comment | ML_COMMENT )?
                    alt6 = 3
                    LA6_0 = self.input.LA(1)

                    if (LA6_0 == SL_COMMENT) :
                        alt6 = 1
                    elif (LA6_0 == ML_COMMENT) :
                        alt6 = 2
                    if alt6 == 1:
                        # jaus2jsidl.g:163:23: ml_comment
                        pass 
                        self._state.following.append(self.FOLLOW_ml_comment_in_service_set238)
                        self.ml_comment()

                        self._state.following.pop()


                    elif alt6 == 2:
                        # jaus2jsidl.g:163:36: ML_COMMENT
                        pass 
                        self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_service_set242)






                # jaus2jsidl.g:164:9: (refs= references ( ml_comment | ML_COMMENT )? )?
                alt9 = 2
                LA9_0 = self.input.LA(1)

                if (LA9_0 == 43) :
                    alt9 = 1
                if alt9 == 1:
                    # jaus2jsidl.g:164:10: refs= references ( ml_comment | ML_COMMENT )?
                    pass 
                    self._state.following.append(self.FOLLOW_references_in_service_set259)
                    self.references()

                    self._state.following.pop()
                    # jaus2jsidl.g:164:26: ( ml_comment | ML_COMMENT )?
                    alt8 = 3
                    LA8_0 = self.input.LA(1)

                    if (LA8_0 == SL_COMMENT) :
                        alt8 = 1
                    elif (LA8_0 == ML_COMMENT) :
                        alt8 = 2
                    if alt8 == 1:
                        # jaus2jsidl.g:164:27: ml_comment
                        pass 
                        self._state.following.append(self.FOLLOW_ml_comment_in_service_set262)
                        self.ml_comment()

                        self._state.following.pop()


                    elif alt8 == 2:
                        # jaus2jsidl.g:164:40: ML_COMMENT
                        pass 
                        self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_service_set266)






                # jaus2jsidl.g:167:9: ( constant_set ( ml_comment | ML_COMMENT )? )*
                while True: #loop11
                    alt11 = 2
                    LA11_0 = self.input.LA(1)

                    if (LA11_0 == 46) :
                        alt11 = 1


                    if alt11 == 1:
                        # jaus2jsidl.g:167:10: constant_set ( ml_comment | ML_COMMENT )?
                        pass 
                        self._state.following.append(self.FOLLOW_constant_set_in_service_set283)
                        self.constant_set()

                        self._state.following.pop()
                        # jaus2jsidl.g:167:23: ( ml_comment | ML_COMMENT )?
                        alt10 = 3
                        LA10_0 = self.input.LA(1)

                        if (LA10_0 == SL_COMMENT) :
                            alt10 = 1
                        elif (LA10_0 == ML_COMMENT) :
                            alt10 = 2
                        if alt10 == 1:
                            # jaus2jsidl.g:167:24: ml_comment
                            pass 
                            self._state.following.append(self.FOLLOW_ml_comment_in_service_set286)
                            self.ml_comment()

                            self._state.following.pop()


                        elif alt10 == 2:
                            # jaus2jsidl.g:167:37: ML_COMMENT
                            pass 
                            self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_service_set290)





                    else:
                        break #loop11


                # jaus2jsidl.g:168:9: ( type_set ( ml_comment | ML_COMMENT )? )*
                while True: #loop13
                    alt13 = 2
                    LA13_0 = self.input.LA(1)

                    if (LA13_0 == 48) :
                        alt13 = 1


                    if alt13 == 1:
                        # jaus2jsidl.g:168:10: type_set ( ml_comment | ML_COMMENT )?
                        pass 
                        self._state.following.append(self.FOLLOW_type_set_in_service_set305)
                        self.type_set()

                        self._state.following.pop()
                        # jaus2jsidl.g:168:19: ( ml_comment | ML_COMMENT )?
                        alt12 = 3
                        LA12_0 = self.input.LA(1)

                        if (LA12_0 == SL_COMMENT) :
                            alt12 = 1
                        elif (LA12_0 == ML_COMMENT) :
                            alt12 = 2
                        if alt12 == 1:
                            # jaus2jsidl.g:168:20: ml_comment
                            pass 
                            self._state.following.append(self.FOLLOW_ml_comment_in_service_set308)
                            self.ml_comment()

                            self._state.following.pop()


                        elif alt12 == 2:
                            # jaus2jsidl.g:168:33: ML_COMMENT
                            pass 
                            self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_service_set312)





                    else:
                        break #loop13


                # jaus2jsidl.g:169:9: ( message_set ( ml_comment | ML_COMMENT )? )?
                alt15 = 2
                LA15_0 = self.input.LA(1)

                if (LA15_0 == 49) :
                    alt15 = 1
                if alt15 == 1:
                    # jaus2jsidl.g:169:10: message_set ( ml_comment | ML_COMMENT )?
                    pass 
                    self._state.following.append(self.FOLLOW_message_set_in_service_set327)
                    self.message_set()

                    self._state.following.pop()
                    # jaus2jsidl.g:169:22: ( ml_comment | ML_COMMENT )?
                    alt14 = 3
                    LA14_0 = self.input.LA(1)

                    if (LA14_0 == SL_COMMENT) :
                        alt14 = 1
                    elif (LA14_0 == ML_COMMENT) :
                        alt14 = 2
                    if alt14 == 1:
                        # jaus2jsidl.g:169:23: ml_comment
                        pass 
                        self._state.following.append(self.FOLLOW_ml_comment_in_service_set330)
                        self.ml_comment()

                        self._state.following.pop()


                    elif alt14 == 2:
                        # jaus2jsidl.g:169:36: ML_COMMENT
                        pass 
                        self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_service_set334)






                # jaus2jsidl.g:170:9: ( internal_event_set ( ml_comment | ML_COMMENT )? )?
                alt17 = 2
                LA17_0 = self.input.LA(1)

                if (LA17_0 == 50) :
                    alt17 = 1
                if alt17 == 1:
                    # jaus2jsidl.g:170:10: internal_event_set ( ml_comment | ML_COMMENT )?
                    pass 
                    self._state.following.append(self.FOLLOW_internal_event_set_in_service_set349)
                    self.internal_event_set()

                    self._state.following.pop()
                    # jaus2jsidl.g:170:29: ( ml_comment | ML_COMMENT )?
                    alt16 = 3
                    LA16_0 = self.input.LA(1)

                    if (LA16_0 == SL_COMMENT) :
                        alt16 = 1
                    elif (LA16_0 == ML_COMMENT) :
                        alt16 = 2
                    if alt16 == 1:
                        # jaus2jsidl.g:170:30: ml_comment
                        pass 
                        self._state.following.append(self.FOLLOW_ml_comment_in_service_set352)
                        self.ml_comment()

                        self._state.following.pop()


                    elif alt16 == 2:
                        # jaus2jsidl.g:170:43: ML_COMMENT
                        pass 
                        self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_service_set356)






                # jaus2jsidl.g:171:9: ( protocol_behavior ( ml_comment | ML_COMMENT )? )?
                alt19 = 2
                LA19_0 = self.input.LA(1)

                if (LA19_0 == 51) :
                    alt19 = 1
                if alt19 == 1:
                    # jaus2jsidl.g:171:10: protocol_behavior ( ml_comment | ML_COMMENT )?
                    pass 
                    self._state.following.append(self.FOLLOW_protocol_behavior_in_service_set371)
                    self.protocol_behavior()

                    self._state.following.pop()
                    # jaus2jsidl.g:171:28: ( ml_comment | ML_COMMENT )?
                    alt18 = 3
                    LA18_0 = self.input.LA(1)

                    if (LA18_0 == SL_COMMENT) :
                        alt18 = 1
                    elif (LA18_0 == ML_COMMENT) :
                        alt18 = 2
                    if alt18 == 1:
                        # jaus2jsidl.g:171:29: ml_comment
                        pass 
                        self._state.following.append(self.FOLLOW_ml_comment_in_service_set374)
                        self.ml_comment()

                        self._state.following.pop()


                    elif alt18 == 2:
                        # jaus2jsidl.g:171:42: ML_COMMENT
                        pass 
                        self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_service_set378)






                self.match(self.input, 39, self.FOLLOW_39_in_service_set392)
                # jaus2jsidl.g:172:13: ( ';' )?
                alt20 = 2
                LA20_0 = self.input.LA(1)

                if (LA20_0 == 40) :
                    alt20 = 1
                if alt20 == 1:
                    # jaus2jsidl.g:172:13: ';'
                    pass 
                    self.match(self.input, 40, self.FOLLOW_40_in_service_set394)







            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "service_set"


    # $ANTLR start "description"
    # jaus2jsidl.g:177:1: description : 'description' STRINGLITERAL ';' ;
    def description(self, ):

        STRINGLITERAL4 = None

        try:
            try:
                # jaus2jsidl.g:178:5: ( 'description' STRINGLITERAL ';' )
                # jaus2jsidl.g:178:7: 'description' STRINGLITERAL ';'
                pass 
                self.match(self.input, 41, self.FOLLOW_41_in_description414)
                STRINGLITERAL4=self.match(self.input, STRINGLITERAL, self.FOLLOW_STRINGLITERAL_in_description416)
                self.match(self.input, 40, self.FOLLOW_40_in_description418)
                #action start
                         
                n = etree.SubElement(self.current_node,'description')
                n.text = STRINGLITERAL4.text[1:-1].strip() # strip quotation marks & <CR>
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "description"


    # $ANTLR start "assumptions"
    # jaus2jsidl.g:186:1: assumptions : 'assumptions' STRINGLITERAL ';' ;
    def assumptions(self, ):

        STRINGLITERAL5 = None

        try:
            try:
                # jaus2jsidl.g:186:13: ( 'assumptions' STRINGLITERAL ';' )
                # jaus2jsidl.g:186:15: 'assumptions' STRINGLITERAL ';'
                pass 
                self.match(self.input, 42, self.FOLLOW_42_in_assumptions443)
                STRINGLITERAL5=self.match(self.input, STRINGLITERAL, self.FOLLOW_STRINGLITERAL_in_assumptions445)
                self.match(self.input, 40, self.FOLLOW_40_in_assumptions447)
                #action start
                         
                n = etree.SubElement(self.current_node, 'assumptions')
                n.text = STRINGLITERAL5.text[1:-1] # strip quotation marks.
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "assumptions"


    # $ANTLR start "references"
    # jaus2jsidl.g:195:1: references : 'references' '{' ( 'inherits_from' ref_attr ';' )? ( 'client_of' ref_attr ';' )* '}' ( ';' )? ;
    def references(self, ):

        try:
            try:
                # jaus2jsidl.g:195:11: ( 'references' '{' ( 'inherits_from' ref_attr ';' )? ( 'client_of' ref_attr ';' )* '}' ( ';' )? )
                # jaus2jsidl.g:196:5: 'references' '{' ( 'inherits_from' ref_attr ';' )? ( 'client_of' ref_attr ';' )* '}' ( ';' )?
                pass 
                self.match(self.input, 43, self.FOLLOW_43_in_references475)
                self.match(self.input, 38, self.FOLLOW_38_in_references477)
                #action start
                     
                p = self.current_node
                n = etree.SubElement(p,'references')
                    
                #action end
                # jaus2jsidl.g:201:5: ( 'inherits_from' ref_attr ';' )?
                alt21 = 2
                LA21_0 = self.input.LA(1)

                if (LA21_0 == 44) :
                    alt21 = 1
                if alt21 == 1:
                    # jaus2jsidl.g:201:6: 'inherits_from' ref_attr ';'
                    pass 
                    self.match(self.input, 44, self.FOLLOW_44_in_references490)
                    #action start
                                 
                    nn = etree.SubElement(n,'inherits_from')
                    self.current_node=nn
                                
                    #action end
                    self._state.following.append(self.FOLLOW_ref_attr_in_references518)
                    self.ref_attr()

                    self._state.following.pop()
                    self.match(self.input, 40, self.FOLLOW_40_in_references520)



                # jaus2jsidl.g:208:5: ( 'client_of' ref_attr ';' )*
                while True: #loop22
                    alt22 = 2
                    LA22_0 = self.input.LA(1)

                    if (LA22_0 == 45) :
                        alt22 = 1


                    if alt22 == 1:
                        # jaus2jsidl.g:208:6: 'client_of' ref_attr ';'
                        pass 
                        self.match(self.input, 45, self.FOLLOW_45_in_references538)
                        #action start
                                     
                        nn = etree.SubElement(n,'client_of')
                        self.current_node = nn
                                    
                        #action end
                        self._state.following.append(self.FOLLOW_ref_attr_in_references566)
                        self.ref_attr()

                        self._state.following.pop()
                        self.match(self.input, 40, self.FOLLOW_40_in_references568)


                    else:
                        break #loop22


                #action start
                         
                self.current_node=p
                        
                #action end
                self.match(self.input, 39, self.FOLLOW_39_in_references595)
                # jaus2jsidl.g:218:9: ( ';' )?
                alt23 = 2
                LA23_0 = self.input.LA(1)

                if (LA23_0 == 40) :
                    alt23 = 1
                if alt23 == 1:
                    # jaus2jsidl.g:218:9: ';'
                    pass 
                    self.match(self.input, 40, self.FOLLOW_40_in_references597)







            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "references"


    # $ANTLR start "ref_attr"
    # jaus2jsidl.g:221:1: ref_attr : ID URI VERSION ( ml_comment )? ;
    def ref_attr(self, ):

        ID6 = None
        URI7 = None
        VERSION8 = None

        try:
            try:
                # jaus2jsidl.g:221:9: ( ID URI VERSION ( ml_comment )? )
                # jaus2jsidl.g:222:5: ID URI VERSION ( ml_comment )?
                pass 
                ID6=self.match(self.input, ID, self.FOLLOW_ID_in_ref_attr614)
                URI7=self.match(self.input, URI, self.FOLLOW_URI_in_ref_attr616)
                VERSION8=self.match(self.input, VERSION, self.FOLLOW_VERSION_in_ref_attr618)
                #action start
                             
                n = self.current_node
                n.attrib['name'] = ID6.text
                n.attrib['id'] = URI7.text[1:-1]  # strip quotes
                n.attrib['version'] = VERSION8.text[1:-1]  # strip quotes
                self.comment = ''
                            
                #action end
                # jaus2jsidl.g:230:9: ( ml_comment )?
                alt24 = 2
                LA24_0 = self.input.LA(1)

                if (LA24_0 == SL_COMMENT) :
                    alt24 = 1
                if alt24 == 1:
                    # jaus2jsidl.g:230:9: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_ref_attr642)
                    self.ml_comment()

                    self._state.following.pop()



                #action start
                         
                if self.comment != '':
                    comment = self.comment.replace('//',' ').strip()
                    n.attrib['interpretation'] = compress_ws(comment)
                    self.comment = ''
                        
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "ref_attr"


    # $ANTLR start "constant_set"
    # jaus2jsidl.g:240:1: constant_set : ( 'constants' ID ) ( '(' 'id' '=' URI ',' 'version' '=' VERSION ')' )? '{' ( declared_const_set_ref )* ( constant_def )* '}' ( ';' )? ;
    def constant_set(self, ):

        ID9 = None
        URI10 = None
        VERSION11 = None

        try:
            try:
                # jaus2jsidl.g:241:5: ( ( 'constants' ID ) ( '(' 'id' '=' URI ',' 'version' '=' VERSION ')' )? '{' ( declared_const_set_ref )* ( constant_def )* '}' ( ';' )? )
                # jaus2jsidl.g:242:9: ( 'constants' ID ) ( '(' 'id' '=' URI ',' 'version' '=' VERSION ')' )? '{' ( declared_const_set_ref )* ( constant_def )* '}' ( ';' )?
                pass 
                # jaus2jsidl.g:242:9: ( 'constants' ID )
                # jaus2jsidl.g:242:10: 'constants' ID
                pass 
                self.match(self.input, 46, self.FOLLOW_46_in_constant_set676)
                ID9=self.match(self.input, ID, self.FOLLOW_ID_in_constant_set678)



                #action start
                         
                p = self.current_node
                n = etree.SubElement(p,'declared_const_set')
                n.attrib['name'] = ID9.text
                        
                #action end
                # jaus2jsidl.g:248:9: ( '(' 'id' '=' URI ',' 'version' '=' VERSION ')' )?
                alt25 = 2
                LA25_0 = self.input.LA(1)

                if (LA25_0 == 32) :
                    alt25 = 1
                if alt25 == 1:
                    # jaus2jsidl.g:248:11: '(' 'id' '=' URI ',' 'version' '=' VERSION ')'
                    pass 
                    self.match(self.input, 32, self.FOLLOW_32_in_constant_set701)
                    self.match(self.input, 33, self.FOLLOW_33_in_constant_set703)
                    self.match(self.input, 34, self.FOLLOW_34_in_constant_set705)
                    URI10=self.match(self.input, URI, self.FOLLOW_URI_in_constant_set707)
                    self.match(self.input, 35, self.FOLLOW_35_in_constant_set709)
                    self.match(self.input, 36, self.FOLLOW_36_in_constant_set711)
                    self.match(self.input, 34, self.FOLLOW_34_in_constant_set713)
                    VERSION11=self.match(self.input, VERSION, self.FOLLOW_VERSION_in_constant_set715)
                    self.match(self.input, 37, self.FOLLOW_37_in_constant_set717)



                #action start
                         
                if URI10:
                    n.attrib['id'] = URI10.text[1:-1]  # strip quotes
                    n.attrib['version'] = VERSION11.text[1:-1]  # strip quotes
                self.current_node = n
                    
                #action end
                self.match(self.input, 38, self.FOLLOW_38_in_constant_set740)
                # jaus2jsidl.g:256:9: ( declared_const_set_ref )*
                while True: #loop26
                    alt26 = 2
                    LA26_0 = self.input.LA(1)

                    if (LA26_0 == 47) :
                        alt26 = 1


                    if alt26 == 1:
                        # jaus2jsidl.g:256:9: declared_const_set_ref
                        pass 
                        self._state.following.append(self.FOLLOW_declared_const_set_ref_in_constant_set751)
                        self.declared_const_set_ref()

                        self._state.following.pop()


                    else:
                        break #loop26


                # jaus2jsidl.g:257:9: ( constant_def )*
                while True: #loop27
                    alt27 = 2
                    LA27_0 = self.input.LA(1)

                    if ((82 <= LA27_0 <= 92)) :
                        alt27 = 1


                    if alt27 == 1:
                        # jaus2jsidl.g:257:9: constant_def
                        pass 
                        self._state.following.append(self.FOLLOW_constant_def_in_constant_set762)
                        self.constant_def()

                        self._state.following.pop()


                    else:
                        break #loop27


                self.match(self.input, 39, self.FOLLOW_39_in_constant_set773)
                # jaus2jsidl.g:258:13: ( ';' )?
                alt28 = 2
                LA28_0 = self.input.LA(1)

                if (LA28_0 == 40) :
                    alt28 = 1
                if alt28 == 1:
                    # jaus2jsidl.g:258:13: ';'
                    pass 
                    self.match(self.input, 40, self.FOLLOW_40_in_constant_set775)



                #action start
                         
                self.current_node = p
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "constant_set"


    # $ANTLR start "declared_const_set_ref"
    # jaus2jsidl.g:265:1: declared_const_set_ref : 'using' ID URI VERSION ';' ;
    def declared_const_set_ref(self, ):

        ID12 = None
        URI13 = None
        VERSION14 = None

        try:
            try:
                # jaus2jsidl.g:265:23: ( 'using' ID URI VERSION ';' )
                # jaus2jsidl.g:266:5: 'using' ID URI VERSION ';'
                pass 
                self.match(self.input, 47, self.FOLLOW_47_in_declared_const_set_ref803)
                ID12=self.match(self.input, ID, self.FOLLOW_ID_in_declared_const_set_ref805)
                URI13=self.match(self.input, URI, self.FOLLOW_URI_in_declared_const_set_ref807)
                VERSION14=self.match(self.input, VERSION, self.FOLLOW_VERSION_in_declared_const_set_ref809)
                self.match(self.input, 40, self.FOLLOW_40_in_declared_const_set_ref811)
                #action start
                         
                n = etree.SubElement(self.current_node,'declared_const_set_ref')
                n.attrib['name'] = ID12.text
                n.attrib['id'] = URI13.text[1:-1]  # strip quotes
                n.attrib['version'] = VERSION14.text[1:-1]  # strip quotes
                        
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "declared_const_set_ref"


    # $ANTLR start "type_set"
    # jaus2jsidl.g:276:1: type_set : 'types' ID ( '(' 'id' '=' URI ',' 'version' '=' VERSION ')' )? '{' ( ml_comment | ML_COMMENT )? ( declared_type_set_ref )* ( type_def )* '}' ( ';' )? ;
    def type_set(self, ):

        ID15 = None
        URI16 = None
        VERSION17 = None

        try:
            try:
                # jaus2jsidl.g:277:5: ( 'types' ID ( '(' 'id' '=' URI ',' 'version' '=' VERSION ')' )? '{' ( ml_comment | ML_COMMENT )? ( declared_type_set_ref )* ( type_def )* '}' ( ';' )? )
                # jaus2jsidl.g:277:7: 'types' ID ( '(' 'id' '=' URI ',' 'version' '=' VERSION ')' )? '{' ( ml_comment | ML_COMMENT )? ( declared_type_set_ref )* ( type_def )* '}' ( ';' )?
                pass 
                self.match(self.input, 48, self.FOLLOW_48_in_type_set835)
                ID15=self.match(self.input, ID, self.FOLLOW_ID_in_type_set837)
                # jaus2jsidl.g:278:9: ( '(' 'id' '=' URI ',' 'version' '=' VERSION ')' )?
                alt29 = 2
                LA29_0 = self.input.LA(1)

                if (LA29_0 == 32) :
                    alt29 = 1
                if alt29 == 1:
                    # jaus2jsidl.g:278:11: '(' 'id' '=' URI ',' 'version' '=' VERSION ')'
                    pass 
                    self.match(self.input, 32, self.FOLLOW_32_in_type_set849)
                    self.match(self.input, 33, self.FOLLOW_33_in_type_set851)
                    self.match(self.input, 34, self.FOLLOW_34_in_type_set853)
                    URI16=self.match(self.input, URI, self.FOLLOW_URI_in_type_set855)
                    self.match(self.input, 35, self.FOLLOW_35_in_type_set857)
                    self.match(self.input, 36, self.FOLLOW_36_in_type_set859)
                    self.match(self.input, 34, self.FOLLOW_34_in_type_set861)
                    VERSION17=self.match(self.input, VERSION, self.FOLLOW_VERSION_in_type_set863)
                    self.match(self.input, 37, self.FOLLOW_37_in_type_set865)



                #action start
                         
                if self.tree is None:
                    # This file is a declared_type_set only.
                    p = None
                    self.tree = etree.ElementTree()
                    n = etree.Element('declared_type_set')
                    n.attrib['xmlns'] = 'urn:jaus:jsidl:1.0'
                    self.tree._setroot(n)
                else:
                    p = self.current_node
                    n = etree.SubElement(p,'declared_type_set')
                self.current_node = n
                n.attrib['name'] = ID15.text
                if URI16:
                    n.attrib['id'] = URI16.text[1:-1]  # strip quotes
                    n.attrib['version'] = VERSION17.text[1:-1]  # strip quotes
                self.current_node = n
                        
                #action end
                self.match(self.input, 38, self.FOLLOW_38_in_type_set888)
                # jaus2jsidl.g:298:9: ( ml_comment | ML_COMMENT )?
                alt30 = 3
                LA30_0 = self.input.LA(1)

                if (LA30_0 == SL_COMMENT) :
                    alt30 = 1
                elif (LA30_0 == ML_COMMENT) :
                    alt30 = 2
                if alt30 == 1:
                    # jaus2jsidl.g:298:10: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_type_set899)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt30 == 2:
                    # jaus2jsidl.g:298:23: ML_COMMENT
                    pass 
                    self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_type_set903)



                # jaus2jsidl.g:299:9: ( declared_type_set_ref )*
                while True: #loop31
                    alt31 = 2
                    LA31_0 = self.input.LA(1)

                    if (LA31_0 == 47) :
                        alt31 = 1


                    if alt31 == 1:
                        # jaus2jsidl.g:299:9: declared_type_set_ref
                        pass 
                        self._state.following.append(self.FOLLOW_declared_type_set_ref_in_type_set915)
                        self.declared_type_set_ref()

                        self._state.following.pop()


                    else:
                        break #loop31


                # jaus2jsidl.g:300:9: ( type_def )*
                while True: #loop32
                    alt32 = 2
                    LA32_0 = self.input.LA(1)

                    if (LA32_0 == MESSAGE_CLASS or LA32_0 == ITEM_CARDINALITY or LA32_0 == 93 or (96 <= LA32_0 <= 97) or (99 <= LA32_0 <= 100) or LA32_0 == 103 or LA32_0 == 105 or (107 <= LA32_0 <= 108)) :
                        alt32 = 1


                    if alt32 == 1:
                        # jaus2jsidl.g:300:9: type_def
                        pass 
                        self._state.following.append(self.FOLLOW_type_def_in_type_set926)
                        self.type_def()

                        self._state.following.pop()


                    else:
                        break #loop32


                self.match(self.input, 39, self.FOLLOW_39_in_type_set937)
                # jaus2jsidl.g:301:13: ( ';' )?
                alt33 = 2
                LA33_0 = self.input.LA(1)

                if (LA33_0 == 40) :
                    alt33 = 1
                if alt33 == 1:
                    # jaus2jsidl.g:301:13: ';'
                    pass 
                    self.match(self.input, 40, self.FOLLOW_40_in_type_set939)



                #action start
                         
                if p is not None:
                    self.current_node = p
                        
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "type_set"


    # $ANTLR start "message_set"
    # jaus2jsidl.g:308:1: message_set : 'messages' '{' ( ml_comment | ML_COMMENT )? ( message_def | type_reference )* '}' ( ';' )? ;
    def message_set(self, ):

        try:
            try:
                # jaus2jsidl.g:309:5: ( 'messages' '{' ( ml_comment | ML_COMMENT )? ( message_def | type_reference )* '}' ( ';' )? )
                # jaus2jsidl.g:309:7: 'messages' '{' ( ml_comment | ML_COMMENT )? ( message_def | type_reference )* '}' ( ';' )?
                pass 
                self.match(self.input, 49, self.FOLLOW_49_in_message_set967)
                #action start
                         
                p = self.current_node
                n = etree.SubElement(p,'message_set')
                self.input_set = etree.SubElement(n,'input_set')
                self.output_set = etree.SubElement(n,'output_set')
                self.current_node = n
                    
                #action end
                self.match(self.input, 38, self.FOLLOW_38_in_message_set987)
                # jaus2jsidl.g:318:9: ( ml_comment | ML_COMMENT )?
                alt34 = 3
                LA34_0 = self.input.LA(1)

                if (LA34_0 == SL_COMMENT) :
                    alt34 = 1
                elif (LA34_0 == ML_COMMENT) :
                    alt34 = 2
                if alt34 == 1:
                    # jaus2jsidl.g:318:11: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_message_set999)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt34 == 2:
                    # jaus2jsidl.g:318:24: ML_COMMENT
                    pass 
                    self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_message_set1003)



                # jaus2jsidl.g:320:9: ( message_def | type_reference )*
                while True: #loop35
                    alt35 = 3
                    LA35_0 = self.input.LA(1)

                    if (LA35_0 == MESSAGE_CLASS) :
                        alt35 = 1
                    elif (LA35_0 == ID or LA35_0 == 50 or LA35_0 == 81) :
                        alt35 = 2


                    if alt35 == 1:
                        # jaus2jsidl.g:320:11: message_def
                        pass 
                        self._state.following.append(self.FOLLOW_message_def_in_message_set1027)
                        self.message_def()

                        self._state.following.pop()


                    elif alt35 == 2:
                        # jaus2jsidl.g:320:25: type_reference
                        pass 
                        self._state.following.append(self.FOLLOW_type_reference_in_message_set1031)
                        self.type_reference()

                        self._state.following.pop()


                    else:
                        break #loop35


                self.match(self.input, 39, self.FOLLOW_39_in_message_set1044)
                # jaus2jsidl.g:321:13: ( ';' )?
                alt36 = 2
                LA36_0 = self.input.LA(1)

                if (LA36_0 == 40) :
                    alt36 = 1
                if alt36 == 1:
                    # jaus2jsidl.g:321:13: ';'
                    pass 
                    self.match(self.input, 40, self.FOLLOW_40_in_message_set1046)



                #action start
                     
                self.current_node = p
                debug(4, "Current node is a %s\n"%self.current_node.tag)
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "message_set"


    # $ANTLR start "internal_event_set"
    # jaus2jsidl.g:328:1: internal_event_set : 'events' ( ID )? '{' ( event_def )* '}' ( ';' )? ;
    def internal_event_set(self, ):

        ID18 = None

        try:
            try:
                # jaus2jsidl.g:329:6: ( 'events' ( ID )? '{' ( event_def )* '}' ( ';' )? )
                # jaus2jsidl.g:329:8: 'events' ( ID )? '{' ( event_def )* '}' ( ';' )?
                pass 
                self.match(self.input, 50, self.FOLLOW_50_in_internal_event_set1068)
                # jaus2jsidl.g:329:17: ( ID )?
                alt37 = 2
                LA37_0 = self.input.LA(1)

                if (LA37_0 == ID) :
                    alt37 = 1
                if alt37 == 1:
                    # jaus2jsidl.g:329:17: ID
                    pass 
                    ID18=self.match(self.input, ID, self.FOLLOW_ID_in_internal_event_set1070)



                #action start
                         
                p = self.current_node
                n = etree.SubElement(p,'internal_events_set')
                if ID18 and self.jsidl_ns == "urn:jaus:jsidl:exp":
                    n.attrib['name'] = ID18.text  # JSIDL does not have a name attr for internal_event_set
                self.current_node = n
                debug(4, "internal_event_set: Current node is a %s\n"%self.current_node.tag)
                        
                #action end
                self.match(self.input, 38, self.FOLLOW_38_in_internal_event_set1091)
                # jaus2jsidl.g:340:9: ( event_def )*
                while True: #loop38
                    alt38 = 2
                    LA38_0 = self.input.LA(1)

                    if (LA38_0 == 81) :
                        alt38 = 1


                    if alt38 == 1:
                        # jaus2jsidl.g:340:9: event_def
                        pass 
                        self._state.following.append(self.FOLLOW_event_def_in_internal_event_set1110)
                        self.event_def()

                        self._state.following.pop()


                    else:
                        break #loop38


                self.match(self.input, 39, self.FOLLOW_39_in_internal_event_set1121)
                # jaus2jsidl.g:341:13: ( ';' )?
                alt39 = 2
                LA39_0 = self.input.LA(1)

                if (LA39_0 == 40) :
                    alt39 = 1
                if alt39 == 1:
                    # jaus2jsidl.g:341:13: ';'
                    pass 
                    self.match(self.input, 40, self.FOLLOW_40_in_internal_event_set1123)



                #action start
                         
                self.current_node = p
                debug(4, "Leaving internal_event_set: Current node is a %s\n"%self.current_node.tag)
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "internal_event_set"


    # $ANTLR start "protocol_behavior"
    # jaus2jsidl.g:349:1: protocol_behavior : 'protocol' '{' ( ml_comment | ML_COMMENT )? start_state state_machine '}' ( ';' )? ;
    def protocol_behavior(self, ):

        ML_COMMENT19 = None

        try:
            try:
                # jaus2jsidl.g:350:5: ( 'protocol' '{' ( ml_comment | ML_COMMENT )? start_state state_machine '}' ( ';' )? )
                # jaus2jsidl.g:351:5: 'protocol' '{' ( ml_comment | ML_COMMENT )? start_state state_machine '}' ( ';' )?
                pass 
                self.match(self.input, 51, self.FOLLOW_51_in_protocol_behavior1156)
                self.match(self.input, 38, self.FOLLOW_38_in_protocol_behavior1158)
                #action start
                     
                p = self.current_node
                n = etree.SubElement(p,'protocol_behavior')
                self.current_node = n
                self.comment = ''
                    
                #action end
                # jaus2jsidl.g:358:5: ( ml_comment | ML_COMMENT )?
                alt40 = 3
                LA40_0 = self.input.LA(1)

                if (LA40_0 == SL_COMMENT) :
                    alt40 = 1
                elif (LA40_0 == ML_COMMENT) :
                    alt40 = 2
                if alt40 == 1:
                    # jaus2jsidl.g:358:6: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_protocol_behavior1171)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt40 == 2:
                    # jaus2jsidl.g:358:19: ML_COMMENT
                    pass 
                    ML_COMMENT19=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_protocol_behavior1175)



                self._state.following.append(self.FOLLOW_start_state_in_protocol_behavior1184)
                self.start_state()

                self._state.following.pop()
                self._state.following.append(self.FOLLOW_state_machine_in_protocol_behavior1186)
                self.state_machine()

                self._state.following.pop()
                self.match(self.input, 39, self.FOLLOW_39_in_protocol_behavior1191)
                # jaus2jsidl.g:360:8: ( ';' )?
                alt41 = 2
                LA41_0 = self.input.LA(1)

                if (LA41_0 == 40) :
                    alt41 = 1
                if alt41 == 1:
                    # jaus2jsidl.g:360:8: ';'
                    pass 
                    self.match(self.input, 40, self.FOLLOW_40_in_protocol_behavior1193)



                #action start
                     
                if ML_COMMENT19:
                    self.comment = ML_COMMENT19.text[2:-2].strip()  # strip the '/*' '*/' delims.
                # This element does not have an interpretation.
                # n.attrib['interpretation'] = compress_ws(self.comment)  

                self.current_node = p
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "protocol_behavior"


    # $ANTLR start "start_state"
    # jaus2jsidl.g:373:1: start_state : 'start' scoped_id ';' ( ml_comment | ML_COMMENT )? ;
    def start_state(self, ):

        ML_COMMENT21 = None
        scoped_id20 = None


        try:
            try:
                # jaus2jsidl.g:374:5: ( 'start' scoped_id ';' ( ml_comment | ML_COMMENT )? )
                # jaus2jsidl.g:375:5: 'start' scoped_id ';' ( ml_comment | ML_COMMENT )?
                pass 
                self.match(self.input, 52, self.FOLLOW_52_in_start_state1223)
                self._state.following.append(self.FOLLOW_scoped_id_in_start_state1225)
                scoped_id20 = self.scoped_id()

                self._state.following.pop()
                self.match(self.input, 40, self.FOLLOW_40_in_start_state1227)
                #action start
                     
                p = self.current_node
                n = etree.SubElement(p,'start')
                tokens = ((scoped_id20 is not None) and [self.input.toString(scoped_id20.start,scoped_id20.stop)] or [None])[0].split('.')
                n.attrib['state_name'] = tokens[-1]
                if len(tokens) > 1:
                    n.attrib['state_machine_name'] = '.'.join(tokens[:-1])
                else:
                    n.attrib['state_machine_name'] = 'this'
                self.current_node = n
                    
                #action end
                # jaus2jsidl.g:387:5: ( ml_comment | ML_COMMENT )?
                alt42 = 3
                LA42_0 = self.input.LA(1)

                if (LA42_0 == SL_COMMENT) :
                    alt42 = 1
                elif (LA42_0 == ML_COMMENT) :
                    alt42 = 2
                if alt42 == 1:
                    # jaus2jsidl.g:387:6: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_start_state1240)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt42 == 2:
                    # jaus2jsidl.g:387:19: ML_COMMENT
                    pass 
                    ML_COMMENT21=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_start_state1244)



                #action start
                     
                if ML_COMMENT21:
                    s = ML_COMMENT21.text[2:-2].strip()
                    n.attrib['interpretation'] = ws_compress(s)
                self.current_node = p
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "start_state"


    # $ANTLR start "state_machine"
    # jaus2jsidl.g:396:1: state_machine : 'state_machine' scoped_id '{' ( ml_comment | mlc1= ML_COMMENT )? ( state )+ '}' ( ';' )? ( ml_comment | mlc2= ML_COMMENT )? ;
    def state_machine(self, ):

        mlc1 = None
        mlc2 = None
        scoped_id22 = None


        try:
            try:
                # jaus2jsidl.g:397:5: ( 'state_machine' scoped_id '{' ( ml_comment | mlc1= ML_COMMENT )? ( state )+ '}' ( ';' )? ( ml_comment | mlc2= ML_COMMENT )? )
                # jaus2jsidl.g:398:5: 'state_machine' scoped_id '{' ( ml_comment | mlc1= ML_COMMENT )? ( state )+ '}' ( ';' )? ( ml_comment | mlc2= ML_COMMENT )?
                pass 
                self.match(self.input, 53, self.FOLLOW_53_in_state_machine1273)
                self._state.following.append(self.FOLLOW_scoped_id_in_state_machine1275)
                scoped_id22 = self.scoped_id()

                self._state.following.pop()
                self.match(self.input, 38, self.FOLLOW_38_in_state_machine1277)
                #action start
                     
                p = self.current_node
                n = etree.SubElement(p,'state_machine')
                n.attrib['name'] = ((scoped_id22 is not None) and [self.input.toString(scoped_id22.start,scoped_id22.stop)] or [None])[0]
                self.current_node = n
                    
                #action end
                # jaus2jsidl.g:405:5: ( ml_comment | mlc1= ML_COMMENT )?
                alt43 = 3
                LA43_0 = self.input.LA(1)

                if (LA43_0 == SL_COMMENT) :
                    alt43 = 1
                elif (LA43_0 == ML_COMMENT) :
                    alt43 = 2
                if alt43 == 1:
                    # jaus2jsidl.g:405:7: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_state_machine1291)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt43 == 2:
                    # jaus2jsidl.g:405:20: mlc1= ML_COMMENT
                    pass 
                    mlc1=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_state_machine1297)



                # jaus2jsidl.g:406:6: ( state )+
                cnt44 = 0
                while True: #loop44
                    alt44 = 2
                    LA44_0 = self.input.LA(1)

                    if ((54 <= LA44_0 <= 55)) :
                        alt44 = 1


                    if alt44 == 1:
                        # jaus2jsidl.g:406:6: state
                        pass 
                        self._state.following.append(self.FOLLOW_state_in_state_machine1307)
                        self.state()

                        self._state.following.pop()


                    else:
                        if cnt44 >= 1:
                            break #loop44

                        eee = EarlyExitException(44, self.input)
                        raise eee

                    cnt44 += 1


                self.match(self.input, 39, self.FOLLOW_39_in_state_machine1314)
                # jaus2jsidl.g:407:10: ( ';' )?
                alt45 = 2
                LA45_0 = self.input.LA(1)

                if (LA45_0 == 40) :
                    alt45 = 1
                if alt45 == 1:
                    # jaus2jsidl.g:407:10: ';'
                    pass 
                    self.match(self.input, 40, self.FOLLOW_40_in_state_machine1317)



                # jaus2jsidl.g:407:16: ( ml_comment | mlc2= ML_COMMENT )?
                alt46 = 3
                LA46_0 = self.input.LA(1)

                if (LA46_0 == SL_COMMENT) :
                    alt46 = 1
                elif (LA46_0 == ML_COMMENT) :
                    alt46 = 2
                if alt46 == 1:
                    # jaus2jsidl.g:407:18: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_state_machine1323)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt46 == 2:
                    # jaus2jsidl.g:407:31: mlc2= ML_COMMENT
                    pass 
                    mlc2=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_state_machine1329)



                #action start
                     
                if mlc1:
                    s = mlc1.text[2:-2].strip()
                    n.attrib['interpretation'] = ws_compress(s)
                self.current_node = p
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "state_machine"


    # $ANTLR start "state"
    # jaus2jsidl.g:417:1: state : ( 'initial' )? 'state' ID '{' ( ml_comment | mlc1= ML_COMMENT )? ( entry )? ( exit )? ( transition )* ( default_transition )? ( state )* ( default_state )? '}' ( ';' )? ( ml_comment | mlc2= ML_COMMENT )? ;
    def state(self, ):

        mlc1 = None
        mlc2 = None
        ID23 = None

        try:
            try:
                # jaus2jsidl.g:418:5: ( ( 'initial' )? 'state' ID '{' ( ml_comment | mlc1= ML_COMMENT )? ( entry )? ( exit )? ( transition )* ( default_transition )? ( state )* ( default_state )? '}' ( ';' )? ( ml_comment | mlc2= ML_COMMENT )? )
                # jaus2jsidl.g:419:5: ( 'initial' )? 'state' ID '{' ( ml_comment | mlc1= ML_COMMENT )? ( entry )? ( exit )? ( transition )* ( default_transition )? ( state )* ( default_state )? '}' ( ';' )? ( ml_comment | mlc2= ML_COMMENT )?
                pass 
                # jaus2jsidl.g:419:5: ( 'initial' )?
                alt47 = 2
                LA47_0 = self.input.LA(1)

                if (LA47_0 == 54) :
                    alt47 = 1
                if alt47 == 1:
                    # jaus2jsidl.g:419:5: 'initial'
                    pass 
                    self.match(self.input, 54, self.FOLLOW_54_in_state1363)



                self.match(self.input, 55, self.FOLLOW_55_in_state1366)
                ID23=self.match(self.input, ID, self.FOLLOW_ID_in_state1368)
                self.match(self.input, 38, self.FOLLOW_38_in_state1370)
                #action start
                     
                p = self.current_node
                n = etree.SubElement(p,'state')
                n.attrib['name'] = ID23.text
                self.current_node = n
                p_comment = self.comment
                self.comment = ''
                    
                #action end
                # jaus2jsidl.g:428:5: ( ml_comment | mlc1= ML_COMMENT )?
                alt48 = 3
                LA48_0 = self.input.LA(1)

                if (LA48_0 == SL_COMMENT) :
                    alt48 = 1
                elif (LA48_0 == ML_COMMENT) :
                    alt48 = 2
                if alt48 == 1:
                    # jaus2jsidl.g:428:7: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_state1384)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt48 == 2:
                    # jaus2jsidl.g:428:20: mlc1= ML_COMMENT
                    pass 
                    mlc1=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_state1390)



                # jaus2jsidl.g:429:5: ( entry )?
                alt49 = 2
                LA49_0 = self.input.LA(1)

                if (LA49_0 == 57) :
                    alt49 = 1
                if alt49 == 1:
                    # jaus2jsidl.g:429:5: entry
                    pass 
                    self._state.following.append(self.FOLLOW_entry_in_state1399)
                    self.entry()

                    self._state.following.pop()



                # jaus2jsidl.g:430:5: ( exit )?
                alt50 = 2
                LA50_0 = self.input.LA(1)

                if (LA50_0 == 58) :
                    alt50 = 1
                if alt50 == 1:
                    # jaus2jsidl.g:430:5: exit
                    pass 
                    self._state.following.append(self.FOLLOW_exit_in_state1406)
                    self.exit()

                    self._state.following.pop()



                # jaus2jsidl.g:431:5: ( transition )*
                while True: #loop51
                    alt51 = 2
                    LA51_0 = self.input.LA(1)

                    if (LA51_0 == ID or LA51_0 == 50 or LA51_0 == 81) :
                        alt51 = 1


                    if alt51 == 1:
                        # jaus2jsidl.g:431:5: transition
                        pass 
                        self._state.following.append(self.FOLLOW_transition_in_state1413)
                        self.transition()

                        self._state.following.pop()


                    else:
                        break #loop51


                # jaus2jsidl.g:432:5: ( default_transition )?
                alt52 = 2
                LA52_0 = self.input.LA(1)

                if (LA52_0 == 56) :
                    alt52 = 1
                if alt52 == 1:
                    # jaus2jsidl.g:432:5: default_transition
                    pass 
                    self._state.following.append(self.FOLLOW_default_transition_in_state1420)
                    self.default_transition()

                    self._state.following.pop()



                # jaus2jsidl.g:433:5: ( state )*
                while True: #loop53
                    alt53 = 2
                    LA53_0 = self.input.LA(1)

                    if (LA53_0 == 55) :
                        LA53_1 = self.input.LA(2)

                        if (LA53_1 == ID) :
                            alt53 = 1


                    elif (LA53_0 == 54) :
                        alt53 = 1


                    if alt53 == 1:
                        # jaus2jsidl.g:433:5: state
                        pass 
                        self._state.following.append(self.FOLLOW_state_in_state1427)
                        self.state()

                        self._state.following.pop()


                    else:
                        break #loop53


                # jaus2jsidl.g:434:5: ( default_state )?
                alt54 = 2
                LA54_0 = self.input.LA(1)

                if (LA54_0 == 55) :
                    alt54 = 1
                if alt54 == 1:
                    # jaus2jsidl.g:434:5: default_state
                    pass 
                    self._state.following.append(self.FOLLOW_default_state_in_state1436)
                    self.default_state()

                    self._state.following.pop()



                self.match(self.input, 39, self.FOLLOW_39_in_state1443)
                # jaus2jsidl.g:435:9: ( ';' )?
                alt55 = 2
                LA55_0 = self.input.LA(1)

                if (LA55_0 == 40) :
                    alt55 = 1
                if alt55 == 1:
                    # jaus2jsidl.g:435:9: ';'
                    pass 
                    self.match(self.input, 40, self.FOLLOW_40_in_state1445)



                # jaus2jsidl.g:435:15: ( ml_comment | mlc2= ML_COMMENT )?
                alt56 = 3
                LA56_0 = self.input.LA(1)

                if (LA56_0 == SL_COMMENT) :
                    alt56 = 1
                elif (LA56_0 == ML_COMMENT) :
                    alt56 = 2
                if alt56 == 1:
                    # jaus2jsidl.g:435:17: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_state1451)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt56 == 2:
                    # jaus2jsidl.g:435:30: mlc2= ML_COMMENT
                    pass 
                    mlc2=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_state1457)



                #action start
                     
                if mlc1:
                    s = mlc1.text[2:-2].strip()
                    self.comment = ws_compress(s)
                n.attrib['interpretation'] = self.comment
                self.current_node = p
                self.comment = p_comment
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "state"


    # $ANTLR start "default_state"
    # jaus2jsidl.g:448:1: default_state : 'state' 'Default' '{' ( ml_comment | mlc1= ML_COMMENT )? ( transition )* ( default_transition )? '}' ( ';' )? ( ml_comment | mlc2= ML_COMMENT )? ;
    def default_state(self, ):

        mlc1 = None
        mlc2 = None

        try:
            try:
                # jaus2jsidl.g:449:5: ( 'state' 'Default' '{' ( ml_comment | mlc1= ML_COMMENT )? ( transition )* ( default_transition )? '}' ( ';' )? ( ml_comment | mlc2= ML_COMMENT )? )
                # jaus2jsidl.g:450:2: 'state' 'Default' '{' ( ml_comment | mlc1= ML_COMMENT )? ( transition )* ( default_transition )? '}' ( ';' )? ( ml_comment | mlc2= ML_COMMENT )?
                pass 
                self.match(self.input, 55, self.FOLLOW_55_in_default_state1486)
                self.match(self.input, 56, self.FOLLOW_56_in_default_state1488)
                self.match(self.input, 38, self.FOLLOW_38_in_default_state1490)
                #action start
                     
                p = self.current_node
                n = etree.SubElement(p,'default_state')
                self.current_node = n
                p_comment = self.comment
                self.comment = ''
                    
                #action end
                # jaus2jsidl.g:458:5: ( ml_comment | mlc1= ML_COMMENT )?
                alt57 = 3
                LA57_0 = self.input.LA(1)

                if (LA57_0 == SL_COMMENT) :
                    alt57 = 1
                elif (LA57_0 == ML_COMMENT) :
                    alt57 = 2
                if alt57 == 1:
                    # jaus2jsidl.g:458:7: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_default_state1504)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt57 == 2:
                    # jaus2jsidl.g:458:20: mlc1= ML_COMMENT
                    pass 
                    mlc1=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_default_state1510)



                #action start
                     
                if mlc1:
                    s = mlc1.text[2:-2].strip()
                    self.comment = ws_compress(s)
                n.attrib['interpretation'] = self.comment
                    
                #action end
                # jaus2jsidl.g:465:2: ( transition )*
                while True: #loop58
                    alt58 = 2
                    LA58_0 = self.input.LA(1)

                    if (LA58_0 == ID or LA58_0 == 50 or LA58_0 == 81) :
                        alt58 = 1


                    if alt58 == 1:
                        # jaus2jsidl.g:465:2: transition
                        pass 
                        self._state.following.append(self.FOLLOW_transition_in_default_state1522)
                        self.transition()

                        self._state.following.pop()


                    else:
                        break #loop58


                # jaus2jsidl.g:466:2: ( default_transition )?
                alt59 = 2
                LA59_0 = self.input.LA(1)

                if (LA59_0 == 56) :
                    alt59 = 1
                if alt59 == 1:
                    # jaus2jsidl.g:466:2: default_transition
                    pass 
                    self._state.following.append(self.FOLLOW_default_transition_in_default_state1526)
                    self.default_transition()

                    self._state.following.pop()



                #action start
                     
                self.current_node = p
                    
                #action end
                self.match(self.input, 39, self.FOLLOW_39_in_default_state1536)
                # jaus2jsidl.g:470:6: ( ';' )?
                alt60 = 2
                LA60_0 = self.input.LA(1)

                if (LA60_0 == 40) :
                    alt60 = 1
                if alt60 == 1:
                    # jaus2jsidl.g:470:6: ';'
                    pass 
                    self.match(self.input, 40, self.FOLLOW_40_in_default_state1538)



                # jaus2jsidl.g:470:11: ( ml_comment | mlc2= ML_COMMENT )?
                alt61 = 3
                LA61_0 = self.input.LA(1)

                if (LA61_0 == SL_COMMENT) :
                    alt61 = 1
                elif (LA61_0 == ML_COMMENT) :
                    alt61 = 2
                if alt61 == 1:
                    # jaus2jsidl.g:470:13: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_default_state1543)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt61 == 2:
                    # jaus2jsidl.g:470:26: mlc2= ML_COMMENT
                    pass 
                    mlc2=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_default_state1549)



                #action start
                     
                self.comment = p_comment
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "default_state"


    # $ANTLR start "entry"
    # jaus2jsidl.g:478:1: entry : 'entry' '{' ( action )* '}' ( ';' )? ( ml_comment | ML_COMMENT )? ;
    def entry(self, ):

        try:
            try:
                # jaus2jsidl.g:479:5: ( 'entry' '{' ( action )* '}' ( ';' )? ( ml_comment | ML_COMMENT )? )
                # jaus2jsidl.g:480:2: 'entry' '{' ( action )* '}' ( ';' )? ( ml_comment | ML_COMMENT )?
                pass 
                self.match(self.input, 57, self.FOLLOW_57_in_entry1581)
                self.match(self.input, 38, self.FOLLOW_38_in_entry1583)
                #action start
                     
                p_comment = self.comment
                self.comment = ''
                    
                #action end
                # jaus2jsidl.g:485:5: ( action )*
                while True: #loop62
                    alt62 = 2
                    LA62_0 = self.input.LA(1)

                    if (LA62_0 == ID or LA62_0 == 50 or LA62_0 == 81) :
                        alt62 = 1


                    if alt62 == 1:
                        # jaus2jsidl.g:485:5: action
                        pass 
                        self._state.following.append(self.FOLLOW_action_in_entry1595)
                        self.action()

                        self._state.following.pop()


                    else:
                        break #loop62


                #action start
                     
                    
                #action end
                self.match(self.input, 39, self.FOLLOW_39_in_entry1608)
                # jaus2jsidl.g:488:9: ( ';' )?
                alt63 = 2
                LA63_0 = self.input.LA(1)

                if (LA63_0 == 40) :
                    alt63 = 1
                if alt63 == 1:
                    # jaus2jsidl.g:488:9: ';'
                    pass 
                    self.match(self.input, 40, self.FOLLOW_40_in_entry1610)



                # jaus2jsidl.g:488:15: ( ml_comment | ML_COMMENT )?
                alt64 = 3
                LA64_0 = self.input.LA(1)

                if (LA64_0 == SL_COMMENT) :
                    alt64 = 1
                elif (LA64_0 == ML_COMMENT) :
                    alt64 = 2
                if alt64 == 1:
                    # jaus2jsidl.g:488:17: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_entry1616)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt64 == 2:
                    # jaus2jsidl.g:488:30: ML_COMMENT
                    pass 
                    self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_entry1620)



                #action start
                     
                self.comment = p_comment
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "entry"


    # $ANTLR start "exit"
    # jaus2jsidl.g:496:1: exit : 'exit' '{' ( action )* '}' ( ';' )? ( ml_comment | ML_COMMENT )? ;
    def exit(self, ):

        try:
            try:
                # jaus2jsidl.g:497:5: ( 'exit' '{' ( action )* '}' ( ';' )? ( ml_comment | ML_COMMENT )? )
                # jaus2jsidl.g:498:2: 'exit' '{' ( action )* '}' ( ';' )? ( ml_comment | ML_COMMENT )?
                pass 
                self.match(self.input, 58, self.FOLLOW_58_in_exit1650)
                self.match(self.input, 38, self.FOLLOW_38_in_exit1652)
                #action start
                     
                p_comment = self.comment
                self.comment = ''
                    
                #action end
                # jaus2jsidl.g:503:5: ( action )*
                while True: #loop65
                    alt65 = 2
                    LA65_0 = self.input.LA(1)

                    if (LA65_0 == ID or LA65_0 == 50 or LA65_0 == 81) :
                        alt65 = 1


                    if alt65 == 1:
                        # jaus2jsidl.g:503:5: action
                        pass 
                        self._state.following.append(self.FOLLOW_action_in_exit1664)
                        self.action()

                        self._state.following.pop()


                    else:
                        break #loop65


                #action start
                     
                    
                #action end
                self.match(self.input, 39, self.FOLLOW_39_in_exit1677)
                # jaus2jsidl.g:506:9: ( ';' )?
                alt66 = 2
                LA66_0 = self.input.LA(1)

                if (LA66_0 == 40) :
                    alt66 = 1
                if alt66 == 1:
                    # jaus2jsidl.g:506:9: ';'
                    pass 
                    self.match(self.input, 40, self.FOLLOW_40_in_exit1679)



                # jaus2jsidl.g:506:15: ( ml_comment | ML_COMMENT )?
                alt67 = 3
                LA67_0 = self.input.LA(1)

                if (LA67_0 == SL_COMMENT) :
                    alt67 = 1
                elif (LA67_0 == ML_COMMENT) :
                    alt67 = 2
                if alt67 == 1:
                    # jaus2jsidl.g:506:17: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_exit1685)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt67 == 2:
                    # jaus2jsidl.g:506:30: ML_COMMENT
                    pass 
                    self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_exit1689)



                #action start
                     
                self.comment = p_comment
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "exit"


    # $ANTLR start "transition"
    # jaus2jsidl.g:516:1: transition : scoped_id ( parameters )? ( guard )? ( 'nil' | next= next_state )? '{' ( action )* '}' ( ';' )? ( ml_comment | ML_COMMENT )? ;
    def transition(self, ):

        try:
            try:
                # jaus2jsidl.g:517:5: ( scoped_id ( parameters )? ( guard )? ( 'nil' | next= next_state )? '{' ( action )* '}' ( ';' )? ( ml_comment | ML_COMMENT )? )
                # jaus2jsidl.g:518:2: scoped_id ( parameters )? ( guard )? ( 'nil' | next= next_state )? '{' ( action )* '}' ( ';' )? ( ml_comment | ML_COMMENT )?
                pass 
                self._state.following.append(self.FOLLOW_scoped_id_in_transition1721)
                self.scoped_id()

                self._state.following.pop()
                # jaus2jsidl.g:518:12: ( parameters )?
                alt68 = 2
                LA68_0 = self.input.LA(1)

                if (LA68_0 == 32) :
                    alt68 = 1
                if alt68 == 1:
                    # jaus2jsidl.g:518:12: parameters
                    pass 
                    self._state.following.append(self.FOLLOW_parameters_in_transition1723)
                    self.parameters()

                    self._state.following.pop()



                # jaus2jsidl.g:518:24: ( guard )?
                alt69 = 2
                LA69_0 = self.input.LA(1)

                if (LA69_0 == 60) :
                    alt69 = 1
                if alt69 == 1:
                    # jaus2jsidl.g:518:24: guard
                    pass 
                    self._state.following.append(self.FOLLOW_guard_in_transition1726)
                    self.guard()

                    self._state.following.pop()



                # jaus2jsidl.g:518:31: ( 'nil' | next= next_state )?
                alt70 = 3
                LA70_0 = self.input.LA(1)

                if (LA70_0 == 59) :
                    LA70_1 = self.input.LA(2)

                    if (LA70_1 == 63) :
                        alt70 = 2
                    elif (LA70_1 == 38) :
                        alt70 = 1
                elif (LA70_0 == ID or LA70_0 == 50 or LA70_0 == 62 or (64 <= LA70_0 <= 66) or LA70_0 == 81) :
                    alt70 = 2
                if alt70 == 1:
                    # jaus2jsidl.g:518:33: 'nil'
                    pass 
                    self.match(self.input, 59, self.FOLLOW_59_in_transition1731)


                elif alt70 == 2:
                    # jaus2jsidl.g:518:41: next= next_state
                    pass 
                    self._state.following.append(self.FOLLOW_next_state_in_transition1737)
                    self.next_state()

                    self._state.following.pop()



                self.match(self.input, 38, self.FOLLOW_38_in_transition1742)
                #action start
                     
                p_comment = self.comment
                self.comment = ''
                    
                #action end
                # jaus2jsidl.g:523:5: ( action )*
                while True: #loop71
                    alt71 = 2
                    LA71_0 = self.input.LA(1)

                    if (LA71_0 == ID or LA71_0 == 50 or LA71_0 == 81) :
                        alt71 = 1


                    if alt71 == 1:
                        # jaus2jsidl.g:523:5: action
                        pass 
                        self._state.following.append(self.FOLLOW_action_in_transition1754)
                        self.action()

                        self._state.following.pop()


                    else:
                        break #loop71


                #action start
                     
                    
                #action end
                self.match(self.input, 39, self.FOLLOW_39_in_transition1764)
                # jaus2jsidl.g:526:6: ( ';' )?
                alt72 = 2
                LA72_0 = self.input.LA(1)

                if (LA72_0 == 40) :
                    alt72 = 1
                if alt72 == 1:
                    # jaus2jsidl.g:526:6: ';'
                    pass 
                    self.match(self.input, 40, self.FOLLOW_40_in_transition1766)



                # jaus2jsidl.g:526:12: ( ml_comment | ML_COMMENT )?
                alt73 = 3
                LA73_0 = self.input.LA(1)

                if (LA73_0 == SL_COMMENT) :
                    alt73 = 1
                elif (LA73_0 == ML_COMMENT) :
                    alt73 = 2
                if alt73 == 1:
                    # jaus2jsidl.g:526:14: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_transition1772)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt73 == 2:
                    # jaus2jsidl.g:526:27: ML_COMMENT
                    pass 
                    self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_transition1776)



                #action start
                     
                self.comment = p_comment
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "transition"


    # $ANTLR start "default_transition"
    # jaus2jsidl.g:533:1: default_transition : 'Default' ( parameters )? ( guard )? next= next_state '{' ( action )* '}' ( ';' )? ( ml_comment | ML_COMMENT )? ;
    def default_transition(self, ):

        try:
            try:
                # jaus2jsidl.g:534:5: ( 'Default' ( parameters )? ( guard )? next= next_state '{' ( action )* '}' ( ';' )? ( ml_comment | ML_COMMENT )? )
                # jaus2jsidl.g:535:2: 'Default' ( parameters )? ( guard )? next= next_state '{' ( action )* '}' ( ';' )? ( ml_comment | ML_COMMENT )?
                pass 
                self.match(self.input, 56, self.FOLLOW_56_in_default_transition1804)
                # jaus2jsidl.g:535:12: ( parameters )?
                alt74 = 2
                LA74_0 = self.input.LA(1)

                if (LA74_0 == 32) :
                    alt74 = 1
                if alt74 == 1:
                    # jaus2jsidl.g:535:12: parameters
                    pass 
                    self._state.following.append(self.FOLLOW_parameters_in_default_transition1806)
                    self.parameters()

                    self._state.following.pop()



                # jaus2jsidl.g:535:24: ( guard )?
                alt75 = 2
                LA75_0 = self.input.LA(1)

                if (LA75_0 == 60) :
                    alt75 = 1
                if alt75 == 1:
                    # jaus2jsidl.g:535:24: guard
                    pass 
                    self._state.following.append(self.FOLLOW_guard_in_default_transition1809)
                    self.guard()

                    self._state.following.pop()



                self._state.following.append(self.FOLLOW_next_state_in_default_transition1814)
                self.next_state()

                self._state.following.pop()
                self.match(self.input, 38, self.FOLLOW_38_in_default_transition1816)
                #action start
                     
                p_comment = self.comment
                self.comment = ''
                    
                #action end
                # jaus2jsidl.g:540:5: ( action )*
                while True: #loop76
                    alt76 = 2
                    LA76_0 = self.input.LA(1)

                    if (LA76_0 == ID or LA76_0 == 50 or LA76_0 == 81) :
                        alt76 = 1


                    if alt76 == 1:
                        # jaus2jsidl.g:540:5: action
                        pass 
                        self._state.following.append(self.FOLLOW_action_in_default_transition1828)
                        self.action()

                        self._state.following.pop()


                    else:
                        break #loop76


                #action start
                     
                    
                #action end
                self.match(self.input, 39, self.FOLLOW_39_in_default_transition1838)
                # jaus2jsidl.g:543:6: ( ';' )?
                alt77 = 2
                LA77_0 = self.input.LA(1)

                if (LA77_0 == 40) :
                    alt77 = 1
                if alt77 == 1:
                    # jaus2jsidl.g:543:6: ';'
                    pass 
                    self.match(self.input, 40, self.FOLLOW_40_in_default_transition1840)



                # jaus2jsidl.g:543:12: ( ml_comment | ML_COMMENT )?
                alt78 = 3
                LA78_0 = self.input.LA(1)

                if (LA78_0 == SL_COMMENT) :
                    alt78 = 1
                elif (LA78_0 == ML_COMMENT) :
                    alt78 = 2
                if alt78 == 1:
                    # jaus2jsidl.g:543:14: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_default_transition1846)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt78 == 2:
                    # jaus2jsidl.g:543:27: ML_COMMENT
                    pass 
                    self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_default_transition1850)



                #action start
                     
                self.comment = p_comment
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "default_transition"


    # $ANTLR start "guard"
    # jaus2jsidl.g:551:1: guard : '[' expression ']' ;
    def guard(self, ):

        try:
            try:
                # jaus2jsidl.g:552:5: ( '[' expression ']' )
                # jaus2jsidl.g:553:2: '[' expression ']'
                pass 
                self.match(self.input, 60, self.FOLLOW_60_in_guard1880)
                self._state.following.append(self.FOLLOW_expression_in_guard1882)
                self.expression()

                self._state.following.pop()
                self.match(self.input, 61, self.FOLLOW_61_in_guard1884)




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "guard"


    # $ANTLR start "next_state"
    # jaus2jsidl.g:558:1: next_state : (simple= simple_transition | push= push_transition | pop= pop_transition | internal= internal_transition );
    def next_state(self, ):

        try:
            try:
                # jaus2jsidl.g:559:5: (simple= simple_transition | push= push_transition | pop= pop_transition | internal= internal_transition )
                alt79 = 4
                LA79 = self.input.LA(1)
                if LA79 == 62:
                    alt79 = 1
                elif LA79 == ID or LA79 == 50 or LA79 == 59 or LA79 == 64 or LA79 == 81:
                    alt79 = 2
                elif LA79 == 65:
                    alt79 = 3
                elif LA79 == 66:
                    alt79 = 4
                else:
                    nvae = NoViableAltException("", 79, 0, self.input)

                    raise nvae

                if alt79 == 1:
                    # jaus2jsidl.g:560:5: simple= simple_transition
                    pass 
                    self._state.following.append(self.FOLLOW_simple_transition_in_next_state1910)
                    self.simple_transition()

                    self._state.following.pop()


                elif alt79 == 2:
                    # jaus2jsidl.g:561:7: push= push_transition
                    pass 
                    self._state.following.append(self.FOLLOW_push_transition_in_next_state1920)
                    self.push_transition()

                    self._state.following.pop()


                elif alt79 == 3:
                    # jaus2jsidl.g:562:7: pop= pop_transition
                    pass 
                    self._state.following.append(self.FOLLOW_pop_transition_in_next_state1930)
                    self.pop_transition()

                    self._state.following.pop()


                elif alt79 == 4:
                    # jaus2jsidl.g:563:7: internal= internal_transition
                    pass 
                    self._state.following.append(self.FOLLOW_internal_transition_in_next_state1940)
                    self.internal_transition()

                    self._state.following.pop()



            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "next_state"


    # $ANTLR start "simple_transition"
    # jaus2jsidl.g:565:1: simple_transition : 'next' '(' name= scoped_id ')' ;
    def simple_transition(self, ):

        name = None


        try:
            try:
                # jaus2jsidl.g:566:5: ( 'next' '(' name= scoped_id ')' )
                # jaus2jsidl.g:569:2: 'next' '(' name= scoped_id ')'
                pass 
                self.match(self.input, 62, self.FOLLOW_62_in_simple_transition1963)
                self.match(self.input, 32, self.FOLLOW_32_in_simple_transition1965)
                self._state.following.append(self.FOLLOW_scoped_id_in_simple_transition1969)
                name = self.scoped_id()

                self._state.following.pop()
                self.match(self.input, 37, self.FOLLOW_37_in_simple_transition1971)




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "simple_transition"


    # $ANTLR start "push_transition"
    # jaus2jsidl.g:576:1: push_transition : ( (name= scoped_id | 'nil' ) '/' )? 'push' '(' push_to= scoped_id ')' ;
    def push_transition(self, ):

        name = None

        push_to = None


        try:
            try:
                # jaus2jsidl.g:577:5: ( ( (name= scoped_id | 'nil' ) '/' )? 'push' '(' push_to= scoped_id ')' )
                # jaus2jsidl.g:578:2: ( (name= scoped_id | 'nil' ) '/' )? 'push' '(' push_to= scoped_id ')'
                pass 
                # jaus2jsidl.g:578:2: ( (name= scoped_id | 'nil' ) '/' )?
                alt81 = 2
                LA81_0 = self.input.LA(1)

                if (LA81_0 == ID or LA81_0 == 50 or LA81_0 == 59 or LA81_0 == 81) :
                    alt81 = 1
                if alt81 == 1:
                    # jaus2jsidl.g:578:4: (name= scoped_id | 'nil' ) '/'
                    pass 
                    # jaus2jsidl.g:578:4: (name= scoped_id | 'nil' )
                    alt80 = 2
                    LA80_0 = self.input.LA(1)

                    if (LA80_0 == ID or LA80_0 == 50 or LA80_0 == 81) :
                        alt80 = 1
                    elif (LA80_0 == 59) :
                        alt80 = 2
                    else:
                        nvae = NoViableAltException("", 80, 0, self.input)

                        raise nvae

                    if alt80 == 1:
                        # jaus2jsidl.g:578:6: name= scoped_id
                        pass 
                        self._state.following.append(self.FOLLOW_scoped_id_in_push_transition2000)
                        name = self.scoped_id()

                        self._state.following.pop()


                    elif alt80 == 2:
                        # jaus2jsidl.g:578:23: 'nil'
                        pass 
                        self.match(self.input, 59, self.FOLLOW_59_in_push_transition2004)



                    self.match(self.input, 63, self.FOLLOW_63_in_push_transition2007)



                self.match(self.input, 64, self.FOLLOW_64_in_push_transition2011)
                self.match(self.input, 32, self.FOLLOW_32_in_push_transition2013)
                self._state.following.append(self.FOLLOW_scoped_id_in_push_transition2017)
                push_to = self.scoped_id()

                self._state.following.pop()
                self.match(self.input, 37, self.FOLLOW_37_in_push_transition2019)




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "push_transition"


    # $ANTLR start "pop_transition"
    # jaus2jsidl.g:583:1: pop_transition : 'pop' '(' (pop_to= scoped_id | (pop_to= scoped_id ',' parameters ) )? ')' ;
    def pop_transition(self, ):

        pop_to = None


        try:
            try:
                # jaus2jsidl.g:584:5: ( 'pop' '(' (pop_to= scoped_id | (pop_to= scoped_id ',' parameters ) )? ')' )
                # jaus2jsidl.g:585:2: 'pop' '(' (pop_to= scoped_id | (pop_to= scoped_id ',' parameters ) )? ')'
                pass 
                self.match(self.input, 65, self.FOLLOW_65_in_pop_transition2041)
                self.match(self.input, 32, self.FOLLOW_32_in_pop_transition2043)
                # jaus2jsidl.g:585:12: (pop_to= scoped_id | (pop_to= scoped_id ',' parameters ) )?
                alt82 = 3
                alt82 = self.dfa82.predict(self.input)
                if alt82 == 1:
                    # jaus2jsidl.g:585:13: pop_to= scoped_id
                    pass 
                    self._state.following.append(self.FOLLOW_scoped_id_in_pop_transition2048)
                    pop_to = self.scoped_id()

                    self._state.following.pop()


                elif alt82 == 2:
                    # jaus2jsidl.g:585:32: (pop_to= scoped_id ',' parameters )
                    pass 
                    # jaus2jsidl.g:585:32: (pop_to= scoped_id ',' parameters )
                    # jaus2jsidl.g:585:34: pop_to= scoped_id ',' parameters
                    pass 
                    self._state.following.append(self.FOLLOW_scoped_id_in_pop_transition2056)
                    pop_to = self.scoped_id()

                    self._state.following.pop()
                    self.match(self.input, 35, self.FOLLOW_35_in_pop_transition2058)
                    self._state.following.append(self.FOLLOW_parameters_in_pop_transition2060)
                    self.parameters()

                    self._state.following.pop()






                self.match(self.input, 37, self.FOLLOW_37_in_pop_transition2066)




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "pop_transition"


    # $ANTLR start "internal_transition"
    # jaus2jsidl.g:588:1: internal_transition : 'internal' '(' (pop_to= scoped_id | (pop_to= scoped_id ',' parameters ) )? ')' ;
    def internal_transition(self, ):

        pop_to = None


        try:
            try:
                # jaus2jsidl.g:589:5: ( 'internal' '(' (pop_to= scoped_id | (pop_to= scoped_id ',' parameters ) )? ')' )
                # jaus2jsidl.g:590:2: 'internal' '(' (pop_to= scoped_id | (pop_to= scoped_id ',' parameters ) )? ')'
                pass 
                self.match(self.input, 66, self.FOLLOW_66_in_internal_transition2084)
                self.match(self.input, 32, self.FOLLOW_32_in_internal_transition2086)
                # jaus2jsidl.g:590:17: (pop_to= scoped_id | (pop_to= scoped_id ',' parameters ) )?
                alt83 = 3
                alt83 = self.dfa83.predict(self.input)
                if alt83 == 1:
                    # jaus2jsidl.g:590:18: pop_to= scoped_id
                    pass 
                    self._state.following.append(self.FOLLOW_scoped_id_in_internal_transition2091)
                    pop_to = self.scoped_id()

                    self._state.following.pop()


                elif alt83 == 2:
                    # jaus2jsidl.g:590:37: (pop_to= scoped_id ',' parameters )
                    pass 
                    # jaus2jsidl.g:590:37: (pop_to= scoped_id ',' parameters )
                    # jaus2jsidl.g:590:39: pop_to= scoped_id ',' parameters
                    pass 
                    self._state.following.append(self.FOLLOW_scoped_id_in_internal_transition2099)
                    pop_to = self.scoped_id()

                    self._state.following.pop()
                    self.match(self.input, 35, self.FOLLOW_35_in_internal_transition2101)
                    self._state.following.append(self.FOLLOW_parameters_in_internal_transition2103)
                    self.parameters()

                    self._state.following.pop()






                self.match(self.input, 37, self.FOLLOW_37_in_internal_transition2109)




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "internal_transition"


    # $ANTLR start "action"
    # jaus2jsidl.g:593:1: action : ( dotnet_assignment | reference_expression ) ';' ( ml_comment | ML_COMMENT )? ;
    def action(self, ):

        try:
            try:
                # jaus2jsidl.g:594:5: ( ( dotnet_assignment | reference_expression ) ';' ( ml_comment | ML_COMMENT )? )
                # jaus2jsidl.g:595:3: ( dotnet_assignment | reference_expression ) ';' ( ml_comment | ML_COMMENT )?
                pass 
                # jaus2jsidl.g:595:3: ( dotnet_assignment | reference_expression )
                alt84 = 2
                alt84 = self.dfa84.predict(self.input)
                if alt84 == 1:
                    # jaus2jsidl.g:595:5: dotnet_assignment
                    pass 
                    self._state.following.append(self.FOLLOW_dotnet_assignment_in_action2130)
                    self.dotnet_assignment()

                    self._state.following.pop()


                elif alt84 == 2:
                    # jaus2jsidl.g:595:25: reference_expression
                    pass 
                    self._state.following.append(self.FOLLOW_reference_expression_in_action2134)
                    self.reference_expression()

                    self._state.following.pop()



                self.match(self.input, 40, self.FOLLOW_40_in_action2138)
                # jaus2jsidl.g:595:52: ( ml_comment | ML_COMMENT )?
                alt85 = 3
                LA85_0 = self.input.LA(1)

                if (LA85_0 == SL_COMMENT) :
                    alt85 = 1
                elif (LA85_0 == ML_COMMENT) :
                    alt85 = 2
                if alt85 == 1:
                    # jaus2jsidl.g:595:54: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_action2142)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt85 == 2:
                    # jaus2jsidl.g:595:67: ML_COMMENT
                    pass 
                    self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_action2146)







            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "action"


    # $ANTLR start "dotnet_assignment"
    # jaus2jsidl.g:598:1: dotnet_assignment : name= scoped_id '=' expr= expression ';' ;
    def dotnet_assignment(self, ):

        name = None


        try:
            try:
                # jaus2jsidl.g:599:5: (name= scoped_id '=' expr= expression ';' )
                # jaus2jsidl.g:600:2: name= scoped_id '=' expr= expression ';'
                pass 
                self._state.following.append(self.FOLLOW_scoped_id_in_dotnet_assignment2169)
                name = self.scoped_id()

                self._state.following.pop()
                self.match(self.input, 34, self.FOLLOW_34_in_dotnet_assignment2171)
                self._state.following.append(self.FOLLOW_expression_in_dotnet_assignment2175)
                self.expression()

                self._state.following.pop()
                self.match(self.input, 40, self.FOLLOW_40_in_dotnet_assignment2177)




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "dotnet_assignment"


    # $ANTLR start "expression"
    # jaus2jsidl.g:613:1: expression : (expr= or_expression | ( '(' expr= or_expression ')' ) );
    def expression(self, ):

        try:
            try:
                # jaus2jsidl.g:614:5: (expr= or_expression | ( '(' expr= or_expression ')' ) )
                alt86 = 2
                LA86_0 = self.input.LA(1)

                if (LA86_0 == ID or LA86_0 == STRINGLITERAL or (INTLITERAL <= LA86_0 <= DOUBLELITERAL) or LA86_0 == 50 or LA86_0 == 72 or LA86_0 == 75 or (78 <= LA86_0 <= 79) or LA86_0 == 81) :
                    alt86 = 1
                elif (LA86_0 == 32) :
                    alt86 = 2
                else:
                    nvae = NoViableAltException("", 86, 0, self.input)

                    raise nvae

                if alt86 == 1:
                    # jaus2jsidl.g:615:2: expr= or_expression
                    pass 
                    self._state.following.append(self.FOLLOW_or_expression_in_expression2208)
                    self.or_expression()

                    self._state.following.pop()


                elif alt86 == 2:
                    # jaus2jsidl.g:615:23: ( '(' expr= or_expression ')' )
                    pass 
                    # jaus2jsidl.g:615:23: ( '(' expr= or_expression ')' )
                    # jaus2jsidl.g:615:24: '(' expr= or_expression ')'
                    pass 
                    self.match(self.input, 32, self.FOLLOW_32_in_expression2213)
                    self._state.following.append(self.FOLLOW_or_expression_in_expression2217)
                    self.or_expression()

                    self._state.following.pop()
                    self.match(self.input, 37, self.FOLLOW_37_in_expression2219)






            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "expression"


    # $ANTLR start "or_expression"
    # jaus2jsidl.g:618:1: or_expression : and_expression ( ( '||' | 'or' ) and_expression )* ;
    def or_expression(self, ):

        try:
            try:
                # jaus2jsidl.g:619:5: ( and_expression ( ( '||' | 'or' ) and_expression )* )
                # jaus2jsidl.g:620:2: and_expression ( ( '||' | 'or' ) and_expression )*
                pass 
                self._state.following.append(self.FOLLOW_and_expression_in_or_expression2238)
                self.and_expression()

                self._state.following.pop()
                # jaus2jsidl.g:620:17: ( ( '||' | 'or' ) and_expression )*
                while True: #loop87
                    alt87 = 2
                    LA87_0 = self.input.LA(1)

                    if ((67 <= LA87_0 <= 68)) :
                        alt87 = 1


                    if alt87 == 1:
                        # jaus2jsidl.g:620:18: ( '||' | 'or' ) and_expression
                        pass 
                        if (67 <= self.input.LA(1) <= 68):
                            self.input.consume()
                            self._state.errorRecovery = False

                        else:
                            mse = MismatchedSetException(None, self.input)
                            raise mse


                        self._state.following.append(self.FOLLOW_and_expression_in_or_expression2249)
                        self.and_expression()

                        self._state.following.pop()


                    else:
                        break #loop87






            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "or_expression"


    # $ANTLR start "and_expression"
    # jaus2jsidl.g:623:1: and_expression : relational_expression ( ( '&&' | 'and' ) relational_expression )* ;
    def and_expression(self, ):

        try:
            try:
                # jaus2jsidl.g:624:5: ( relational_expression ( ( '&&' | 'and' ) relational_expression )* )
                # jaus2jsidl.g:625:2: relational_expression ( ( '&&' | 'and' ) relational_expression )*
                pass 
                self._state.following.append(self.FOLLOW_relational_expression_in_and_expression2269)
                self.relational_expression()

                self._state.following.pop()
                # jaus2jsidl.g:625:24: ( ( '&&' | 'and' ) relational_expression )*
                while True: #loop88
                    alt88 = 2
                    LA88_0 = self.input.LA(1)

                    if ((69 <= LA88_0 <= 70)) :
                        alt88 = 1


                    if alt88 == 1:
                        # jaus2jsidl.g:625:25: ( '&&' | 'and' ) relational_expression
                        pass 
                        if (69 <= self.input.LA(1) <= 70):
                            self.input.consume()
                            self._state.errorRecovery = False

                        else:
                            mse = MismatchedSetException(None, self.input)
                            raise mse


                        self._state.following.append(self.FOLLOW_relational_expression_in_and_expression2280)
                        self.relational_expression()

                        self._state.following.pop()


                    else:
                        break #loop88






            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "and_expression"


    # $ANTLR start "relational_expression"
    # jaus2jsidl.g:628:1: relational_expression : add_expression ( RELATIONAL_OP add_expression )* ;
    def relational_expression(self, ):

        try:
            try:
                # jaus2jsidl.g:629:5: ( add_expression ( RELATIONAL_OP add_expression )* )
                # jaus2jsidl.g:630:2: add_expression ( RELATIONAL_OP add_expression )*
                pass 
                self._state.following.append(self.FOLLOW_add_expression_in_relational_expression2300)
                self.add_expression()

                self._state.following.pop()
                # jaus2jsidl.g:630:17: ( RELATIONAL_OP add_expression )*
                while True: #loop89
                    alt89 = 2
                    LA89_0 = self.input.LA(1)

                    if (LA89_0 == RELATIONAL_OP) :
                        alt89 = 1


                    if alt89 == 1:
                        # jaus2jsidl.g:630:18: RELATIONAL_OP add_expression
                        pass 
                        self.match(self.input, RELATIONAL_OP, self.FOLLOW_RELATIONAL_OP_in_relational_expression2303)
                        self._state.following.append(self.FOLLOW_add_expression_in_relational_expression2305)
                        self.add_expression()

                        self._state.following.pop()


                    else:
                        break #loop89






            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "relational_expression"


    # $ANTLR start "add_expression"
    # jaus2jsidl.g:633:1: add_expression : mult_expression ( ( '+' | '-' ) mult_expression )* ;
    def add_expression(self, ):

        try:
            try:
                # jaus2jsidl.g:634:5: ( mult_expression ( ( '+' | '-' ) mult_expression )* )
                # jaus2jsidl.g:635:2: mult_expression ( ( '+' | '-' ) mult_expression )*
                pass 
                self._state.following.append(self.FOLLOW_mult_expression_in_add_expression2325)
                self.mult_expression()

                self._state.following.pop()
                # jaus2jsidl.g:635:18: ( ( '+' | '-' ) mult_expression )*
                while True: #loop90
                    alt90 = 2
                    LA90_0 = self.input.LA(1)

                    if ((71 <= LA90_0 <= 72)) :
                        alt90 = 1


                    if alt90 == 1:
                        # jaus2jsidl.g:635:19: ( '+' | '-' ) mult_expression
                        pass 
                        if (71 <= self.input.LA(1) <= 72):
                            self.input.consume()
                            self._state.errorRecovery = False

                        else:
                            mse = MismatchedSetException(None, self.input)
                            raise mse


                        self._state.following.append(self.FOLLOW_mult_expression_in_add_expression2336)
                        self.mult_expression()

                        self._state.following.pop()


                    else:
                        break #loop90






            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "add_expression"


    # $ANTLR start "mult_expression"
    # jaus2jsidl.g:640:1: mult_expression : unary_expression ( ( '*' | '/' | '%' ) unary_expression )* ;
    def mult_expression(self, ):

        try:
            try:
                # jaus2jsidl.g:641:5: ( unary_expression ( ( '*' | '/' | '%' ) unary_expression )* )
                # jaus2jsidl.g:642:2: unary_expression ( ( '*' | '/' | '%' ) unary_expression )*
                pass 
                self._state.following.append(self.FOLLOW_unary_expression_in_mult_expression2359)
                self.unary_expression()

                self._state.following.pop()
                # jaus2jsidl.g:642:19: ( ( '*' | '/' | '%' ) unary_expression )*
                while True: #loop91
                    alt91 = 2
                    LA91_0 = self.input.LA(1)

                    if (LA91_0 == 63 or (73 <= LA91_0 <= 74)) :
                        alt91 = 1


                    if alt91 == 1:
                        # jaus2jsidl.g:642:20: ( '*' | '/' | '%' ) unary_expression
                        pass 
                        if self.input.LA(1) == 63 or (73 <= self.input.LA(1) <= 74):
                            self.input.consume()
                            self._state.errorRecovery = False

                        else:
                            mse = MismatchedSetException(None, self.input)
                            raise mse


                        self._state.following.append(self.FOLLOW_unary_expression_in_mult_expression2374)
                        self.unary_expression()

                        self._state.following.pop()


                    else:
                        break #loop91






            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "mult_expression"


    # $ANTLR start "unary_expression"
    # jaus2jsidl.g:646:1: unary_expression : (negate= '!' )? (ref= reference_expression | const= constant ) ;
    def unary_expression(self, ):

        negate = None

        try:
            try:
                # jaus2jsidl.g:647:5: ( (negate= '!' )? (ref= reference_expression | const= constant ) )
                # jaus2jsidl.g:648:2: (negate= '!' )? (ref= reference_expression | const= constant )
                pass 
                # jaus2jsidl.g:648:8: (negate= '!' )?
                alt92 = 2
                LA92_0 = self.input.LA(1)

                if (LA92_0 == 75) :
                    alt92 = 1
                if alt92 == 1:
                    # jaus2jsidl.g:648:8: negate= '!'
                    pass 
                    negate=self.match(self.input, 75, self.FOLLOW_75_in_unary_expression2398)



                # jaus2jsidl.g:648:14: (ref= reference_expression | const= constant )
                alt93 = 2
                LA93_0 = self.input.LA(1)

                if (LA93_0 == ID or LA93_0 == 50 or LA93_0 == 81) :
                    alt93 = 1
                elif (LA93_0 == STRINGLITERAL or (INTLITERAL <= LA93_0 <= DOUBLELITERAL) or LA93_0 == 72 or (78 <= LA93_0 <= 79)) :
                    alt93 = 2
                else:
                    nvae = NoViableAltException("", 93, 0, self.input)

                    raise nvae

                if alt93 == 1:
                    # jaus2jsidl.g:648:15: ref= reference_expression
                    pass 
                    self._state.following.append(self.FOLLOW_reference_expression_in_unary_expression2404)
                    self.reference_expression()

                    self._state.following.pop()


                elif alt93 == 2:
                    # jaus2jsidl.g:648:42: const= constant
                    pass 
                    self._state.following.append(self.FOLLOW_constant_in_unary_expression2410)
                    self.constant()

                    self._state.following.pop()







            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "unary_expression"


    # $ANTLR start "reference_expression"
    # jaus2jsidl.g:651:1: reference_expression : single_ref ( '.' single_ref )* ;
    def reference_expression(self, ):

        try:
            try:
                # jaus2jsidl.g:652:5: ( single_ref ( '.' single_ref )* )
                # jaus2jsidl.g:653:2: single_ref ( '.' single_ref )*
                pass 
                self._state.following.append(self.FOLLOW_single_ref_in_reference_expression2429)
                self.single_ref()

                self._state.following.pop()
                # jaus2jsidl.g:653:13: ( '.' single_ref )*
                while True: #loop94
                    alt94 = 2
                    LA94_0 = self.input.LA(1)

                    if (LA94_0 == 76) :
                        alt94 = 1


                    if alt94 == 1:
                        # jaus2jsidl.g:653:14: '.' single_ref
                        pass 
                        self.match(self.input, 76, self.FOLLOW_76_in_reference_expression2432)
                        self._state.following.append(self.FOLLOW_single_ref_in_reference_expression2434)
                        self.single_ref()

                        self._state.following.pop()


                    else:
                        break #loop94






            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "reference_expression"


    # $ANTLR start "single_ref"
    # jaus2jsidl.g:656:1: single_ref : name= ( keyword | ID ) (args= arguments )? ;
    def single_ref(self, ):

        name = None

        try:
            try:
                # jaus2jsidl.g:657:5: (name= ( keyword | ID ) (args= arguments )? )
                # jaus2jsidl.g:658:2: name= ( keyword | ID ) (args= arguments )?
                pass 
                # jaus2jsidl.g:658:7: ( keyword | ID )
                alt95 = 2
                LA95_0 = self.input.LA(1)

                if (LA95_0 == 50 or LA95_0 == 81) :
                    alt95 = 1
                elif (LA95_0 == ID) :
                    alt95 = 2
                else:
                    nvae = NoViableAltException("", 95, 0, self.input)

                    raise nvae

                if alt95 == 1:
                    # jaus2jsidl.g:658:9: keyword
                    pass 
                    self._state.following.append(self.FOLLOW_keyword_in_single_ref2459)
                    self.keyword()

                    self._state.following.pop()


                elif alt95 == 2:
                    # jaus2jsidl.g:658:19: ID
                    pass 
                    self.match(self.input, ID, self.FOLLOW_ID_in_single_ref2463)



                # jaus2jsidl.g:658:28: (args= arguments )?
                alt96 = 2
                LA96_0 = self.input.LA(1)

                if (LA96_0 == 32) :
                    alt96 = 1
                if alt96 == 1:
                    # jaus2jsidl.g:658:28: args= arguments
                    pass 
                    self._state.following.append(self.FOLLOW_arguments_in_single_ref2469)
                    self.arguments()

                    self._state.following.pop()







            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "single_ref"


    # $ANTLR start "parameters"
    # jaus2jsidl.g:663:1: parameters : '(' ( parameter ( ',' parameter )* )? ')' ;
    def parameters(self, ):

        try:
            try:
                # jaus2jsidl.g:664:5: ( '(' ( parameter ( ',' parameter )* )? ')' )
                # jaus2jsidl.g:665:2: '(' ( parameter ( ',' parameter )* )? ')'
                pass 
                self.match(self.input, 32, self.FOLLOW_32_in_parameters2491)
                # jaus2jsidl.g:665:6: ( parameter ( ',' parameter )* )?
                alt98 = 2
                LA98_0 = self.input.LA(1)

                if (LA98_0 == ID) :
                    alt98 = 1
                if alt98 == 1:
                    # jaus2jsidl.g:665:7: parameter ( ',' parameter )*
                    pass 
                    self._state.following.append(self.FOLLOW_parameter_in_parameters2494)
                    self.parameter()

                    self._state.following.pop()
                    # jaus2jsidl.g:665:17: ( ',' parameter )*
                    while True: #loop97
                        alt97 = 2
                        LA97_0 = self.input.LA(1)

                        if (LA97_0 == 35) :
                            alt97 = 1


                        if alt97 == 1:
                            # jaus2jsidl.g:665:18: ',' parameter
                            pass 
                            self.match(self.input, 35, self.FOLLOW_35_in_parameters2497)
                            self._state.following.append(self.FOLLOW_parameter_in_parameters2499)
                            self.parameter()

                            self._state.following.pop()


                        else:
                            break #loop97





                self.match(self.input, 37, self.FOLLOW_37_in_parameters2505)




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "parameters"


    # $ANTLR start "parameter"
    # jaus2jsidl.g:668:1: parameter : type= ID ':' name= ID ( ML_COMMENT )? ;
    def parameter(self, ):

        type = None
        name = None

        try:
            try:
                # jaus2jsidl.g:669:5: (type= ID ':' name= ID ( ML_COMMENT )? )
                # jaus2jsidl.g:670:2: type= ID ':' name= ID ( ML_COMMENT )?
                pass 
                type=self.match(self.input, ID, self.FOLLOW_ID_in_parameter2525)
                self.match(self.input, 77, self.FOLLOW_77_in_parameter2527)
                name=self.match(self.input, ID, self.FOLLOW_ID_in_parameter2531)
                # jaus2jsidl.g:670:23: ( ML_COMMENT )?
                alt99 = 2
                LA99_0 = self.input.LA(1)

                if (LA99_0 == ML_COMMENT) :
                    alt99 = 1
                if alt99 == 1:
                    # jaus2jsidl.g:670:23: ML_COMMENT
                    pass 
                    self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_parameter2534)







            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "parameter"


    # $ANTLR start "arguments"
    # jaus2jsidl.g:675:1: arguments : '(' ( expression ( ',' expression )* )? ')' ;
    def arguments(self, ):

        try:
            try:
                # jaus2jsidl.g:676:5: ( '(' ( expression ( ',' expression )* )? ')' )
                # jaus2jsidl.g:677:2: '(' ( expression ( ',' expression )* )? ')'
                pass 
                self.match(self.input, 32, self.FOLLOW_32_in_arguments2556)
                # jaus2jsidl.g:677:6: ( expression ( ',' expression )* )?
                alt101 = 2
                LA101_0 = self.input.LA(1)

                if (LA101_0 == ID or LA101_0 == STRINGLITERAL or (INTLITERAL <= LA101_0 <= DOUBLELITERAL) or LA101_0 == 32 or LA101_0 == 50 or LA101_0 == 72 or LA101_0 == 75 or (78 <= LA101_0 <= 79) or LA101_0 == 81) :
                    alt101 = 1
                if alt101 == 1:
                    # jaus2jsidl.g:677:7: expression ( ',' expression )*
                    pass 
                    self._state.following.append(self.FOLLOW_expression_in_arguments2559)
                    self.expression()

                    self._state.following.pop()
                    # jaus2jsidl.g:677:18: ( ',' expression )*
                    while True: #loop100
                        alt100 = 2
                        LA100_0 = self.input.LA(1)

                        if (LA100_0 == 35) :
                            alt100 = 1


                        if alt100 == 1:
                            # jaus2jsidl.g:677:19: ',' expression
                            pass 
                            self.match(self.input, 35, self.FOLLOW_35_in_arguments2562)
                            self._state.following.append(self.FOLLOW_expression_in_arguments2564)
                            self.expression()

                            self._state.following.pop()


                        else:
                            break #loop100





                self.match(self.input, 37, self.FOLLOW_37_in_arguments2570)




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "arguments"


    # $ANTLR start "constant"
    # jaus2jsidl.g:680:1: constant : ( numeric_literal | STRINGLITERAL | 'True' | 'False' );
    def constant(self, ):

        try:
            try:
                # jaus2jsidl.g:681:5: ( numeric_literal | STRINGLITERAL | 'True' | 'False' )
                alt102 = 4
                LA102 = self.input.LA(1)
                if LA102 == INTLITERAL or LA102 == DOUBLELITERAL or LA102 == 72:
                    alt102 = 1
                elif LA102 == STRINGLITERAL:
                    alt102 = 2
                elif LA102 == 78:
                    alt102 = 3
                elif LA102 == 79:
                    alt102 = 4
                else:
                    nvae = NoViableAltException("", 102, 0, self.input)

                    raise nvae

                if alt102 == 1:
                    # jaus2jsidl.g:682:2: numeric_literal
                    pass 
                    self._state.following.append(self.FOLLOW_numeric_literal_in_constant2588)
                    self.numeric_literal()

                    self._state.following.pop()


                elif alt102 == 2:
                    # jaus2jsidl.g:682:20: STRINGLITERAL
                    pass 
                    self.match(self.input, STRINGLITERAL, self.FOLLOW_STRINGLITERAL_in_constant2592)


                elif alt102 == 3:
                    # jaus2jsidl.g:682:36: 'True'
                    pass 
                    self.match(self.input, 78, self.FOLLOW_78_in_constant2596)


                elif alt102 == 4:
                    # jaus2jsidl.g:682:45: 'False'
                    pass 
                    self.match(self.input, 79, self.FOLLOW_79_in_constant2600)



            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "constant"


    # $ANTLR start "constant_def"
    # jaus2jsidl.g:686:1: constant_def : simple_numeric_type ID '=' numeric_literal UNIT ';' ( ml_comment )? ;
    def constant_def(self, ):

        ID24 = None
        UNIT26 = None
        numeric_literal25 = None


        try:
            try:
                # jaus2jsidl.g:687:5: ( simple_numeric_type ID '=' numeric_literal UNIT ';' ( ml_comment )? )
                # jaus2jsidl.g:688:9: simple_numeric_type ID '=' numeric_literal UNIT ';' ( ml_comment )?
                pass 
                #action start
                         
                p = self.current_node
                n = etree.SubElement(p,'const_def')
                self.current_node=n
                        
                #action end
                self._state.following.append(self.FOLLOW_simple_numeric_type_in_constant_def2636)
                self.simple_numeric_type()

                self._state.following.pop()
                ID24=self.match(self.input, ID, self.FOLLOW_ID_in_constant_def2638)
                self.match(self.input, 34, self.FOLLOW_34_in_constant_def2640)
                self._state.following.append(self.FOLLOW_numeric_literal_in_constant_def2651)
                numeric_literal25 = self.numeric_literal()

                self._state.following.pop()
                UNIT26=self.match(self.input, UNIT, self.FOLLOW_UNIT_in_constant_def2653)
                self.match(self.input, 40, self.FOLLOW_40_in_constant_def2655)
                #action start
                         
                n.attrib['name'] = ID24.text
                self.const_map[ID24.text] = n
                n.attrib['const_type'] = n.attrib['field_type'] # Set in simple_numeric_type rule.
                del n.attrib['field_type']
                n.attrib['const_value'] = ((numeric_literal25 is not None) and [self.input.toString(numeric_literal25.start,numeric_literal25.stop)] or [None])[0]
                n.attrib['field_units'] = UNIT26.text.replace('_',' ')
                self.current_node=n
                self.comment = ''
                        
                #action end
                # jaus2jsidl.g:705:9: ( ml_comment )?
                alt103 = 2
                LA103_0 = self.input.LA(1)

                if (LA103_0 == SL_COMMENT) :
                    alt103 = 1
                if alt103 == 1:
                    # jaus2jsidl.g:705:9: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_constant_def2675)
                    self.ml_comment()

                    self._state.following.pop()



                #action start
                         
                if self.comment != '':
                    comment = self.comment.replace('//',' ').strip()
                    n.attrib['interpretation'] = compress_ws(comment)
                    self.comment = ''
                self.current_node=p
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "constant_def"


    # $ANTLR start "declared_type_set_ref"
    # jaus2jsidl.g:716:1: declared_type_set_ref : 'using' URI VERSION ( 'as' ID )? ';' ( ml_comment | ML_COMMENT )* ;
    def declared_type_set_ref(self, ):

        ID27 = None
        URI28 = None
        VERSION29 = None

        try:
            try:
                # jaus2jsidl.g:717:5: ( 'using' URI VERSION ( 'as' ID )? ';' ( ml_comment | ML_COMMENT )* )
                # jaus2jsidl.g:718:5: 'using' URI VERSION ( 'as' ID )? ';' ( ml_comment | ML_COMMENT )*
                pass 
                self.match(self.input, 47, self.FOLLOW_47_in_declared_type_set_ref2708)
                URI28=self.match(self.input, URI, self.FOLLOW_URI_in_declared_type_set_ref2710)
                VERSION29=self.match(self.input, VERSION, self.FOLLOW_VERSION_in_declared_type_set_ref2712)
                # jaus2jsidl.g:718:25: ( 'as' ID )?
                alt104 = 2
                LA104_0 = self.input.LA(1)

                if (LA104_0 == 80) :
                    alt104 = 1
                if alt104 == 1:
                    # jaus2jsidl.g:718:26: 'as' ID
                    pass 
                    self.match(self.input, 80, self.FOLLOW_80_in_declared_type_set_ref2715)
                    ID27=self.match(self.input, ID, self.FOLLOW_ID_in_declared_type_set_ref2717)



                self.match(self.input, 40, self.FOLLOW_40_in_declared_type_set_ref2721)
                # jaus2jsidl.g:718:40: ( ml_comment | ML_COMMENT )*
                while True: #loop105
                    alt105 = 3
                    LA105_0 = self.input.LA(1)

                    if (LA105_0 == SL_COMMENT) :
                        alt105 = 1
                    elif (LA105_0 == ML_COMMENT) :
                        alt105 = 2


                    if alt105 == 1:
                        # jaus2jsidl.g:718:41: ml_comment
                        pass 
                        self._state.following.append(self.FOLLOW_ml_comment_in_declared_type_set_ref2724)
                        self.ml_comment()

                        self._state.following.pop()


                    elif alt105 == 2:
                        # jaus2jsidl.g:718:54: ML_COMMENT
                        pass 
                        self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_declared_type_set_ref2728)


                    else:
                        break #loop105


                #action start
                     
                p=self.current_node
                n = etree.SubElement(p, 'declared_type_set_ref')
                n.attrib['name'] = ID27.text
                n.attrib['id'] = URI28.text[1:-1]  # strip quotes
                n.attrib['version'] = VERSION29.text[1:-1]  # strip quotes
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "declared_type_set_ref"


    # $ANTLR start "type_def"
    # jaus2jsidl.g:729:1: type_def : ( field_type_def | container_type_def | variant_field_type_def | variable_format_field_type_def | enum_type_def | bitfield_type_def | message_type_def );
    def type_def(self, ):

        try:
            try:
                # jaus2jsidl.g:730:5: ( field_type_def | container_type_def | variant_field_type_def | variable_format_field_type_def | enum_type_def | bitfield_type_def | message_type_def )
                alt106 = 7
                LA106 = self.input.LA(1)
                if LA106 == 96:
                    alt106 = 1
                elif LA106 == ITEM_CARDINALITY:
                    LA106 = self.input.LA(2)
                    if LA106 == 97:
                        alt106 = 3
                    elif LA106 == 103 or LA106 == 105 or LA106 == 107 or LA106 == 108:
                        alt106 = 2
                    elif LA106 == 99:
                        alt106 = 4
                    elif LA106 == 100:
                        alt106 = 6
                    else:
                        nvae = NoViableAltException("", 106, 2, self.input)

                        raise nvae

                elif LA106 == 103 or LA106 == 105 or LA106 == 107 or LA106 == 108:
                    alt106 = 2
                elif LA106 == 97:
                    alt106 = 3
                elif LA106 == 99:
                    alt106 = 4
                elif LA106 == 93:
                    alt106 = 5
                elif LA106 == 100:
                    alt106 = 6
                elif LA106 == MESSAGE_CLASS:
                    alt106 = 7
                else:
                    nvae = NoViableAltException("", 106, 0, self.input)

                    raise nvae

                if alt106 == 1:
                    # jaus2jsidl.g:732:5: field_type_def
                    pass 
                    self._state.following.append(self.FOLLOW_field_type_def_in_type_def2767)
                    self.field_type_def()

                    self._state.following.pop()


                elif alt106 == 2:
                    # jaus2jsidl.g:733:7: container_type_def
                    pass 
                    self._state.following.append(self.FOLLOW_container_type_def_in_type_def2775)
                    self.container_type_def()

                    self._state.following.pop()


                elif alt106 == 3:
                    # jaus2jsidl.g:734:7: variant_field_type_def
                    pass 
                    self._state.following.append(self.FOLLOW_variant_field_type_def_in_type_def2783)
                    self.variant_field_type_def()

                    self._state.following.pop()


                elif alt106 == 4:
                    # jaus2jsidl.g:735:7: variable_format_field_type_def
                    pass 
                    self._state.following.append(self.FOLLOW_variable_format_field_type_def_in_type_def2791)
                    self.variable_format_field_type_def()

                    self._state.following.pop()


                elif alt106 == 5:
                    # jaus2jsidl.g:736:7: enum_type_def
                    pass 
                    self._state.following.append(self.FOLLOW_enum_type_def_in_type_def2799)
                    self.enum_type_def()

                    self._state.following.pop()


                elif alt106 == 6:
                    # jaus2jsidl.g:737:7: bitfield_type_def
                    pass 
                    self._state.following.append(self.FOLLOW_bitfield_type_def_in_type_def2807)
                    self.bitfield_type_def()

                    self._state.following.pop()


                elif alt106 == 7:
                    # jaus2jsidl.g:738:7: message_type_def
                    pass 
                    self._state.following.append(self.FOLLOW_message_type_def_in_type_def2815)
                    self.message_type_def()

                    self._state.following.pop()



            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "type_def"


    # $ANTLR start "message_type_def"
    # jaus2jsidl.g:743:1: message_type_def : MESSAGE_CLASS MESSAGE_CODE ID '{' description ( ml_comment | ML_COMMENT )? ( container_type_def | type_reference )? ( return_spec )? '}' ( ';' )? ;
    def message_type_def(self, ):

        MESSAGE_CLASS30 = None
        ID31 = None
        MESSAGE_CODE32 = None

        try:
            try:
                # jaus2jsidl.g:744:5: ( MESSAGE_CLASS MESSAGE_CODE ID '{' description ( ml_comment | ML_COMMENT )? ( container_type_def | type_reference )? ( return_spec )? '}' ( ';' )? )
                # jaus2jsidl.g:745:9: MESSAGE_CLASS MESSAGE_CODE ID '{' description ( ml_comment | ML_COMMENT )? ( container_type_def | type_reference )? ( return_spec )? '}' ( ';' )?
                pass 
                MESSAGE_CLASS30=self.match(self.input, MESSAGE_CLASS, self.FOLLOW_MESSAGE_CLASS_in_message_type_def2842)
                MESSAGE_CODE32=self.match(self.input, MESSAGE_CODE, self.FOLLOW_MESSAGE_CODE_in_message_type_def2844)
                ID31=self.match(self.input, ID, self.FOLLOW_ID_in_message_type_def2846)
                self.match(self.input, 38, self.FOLLOW_38_in_message_type_def2848)
                #action start
                         
                p = self.current_node
                if MESSAGE_CLASS30.text in ['query','inform']:
                    debug(1,"Warning: In a type {} section, message %s has class %s\n"%(ID31.text,MESSAGE_CLASS30.text))
                n = etree.SubElement(self.current_node,'message_def')
                n.attrib['name'] = ID31.text
                self.type_map[ID31.text] = n
                n.attrib['message_id'] = MESSAGE_CODE32.text[2:]  # remove '0x'
                if MESSAGE_CLASS30.text == 'command':
                    n.attrib['is_command'] = 'true'
                else:
                    n.attrib['is_command'] = 'false'
                self.current_node = n
                        
                #action end
                self._state.following.append(self.FOLLOW_description_in_message_type_def2869)
                self.description()

                self._state.following.pop()
                # jaus2jsidl.g:761:9: ( ml_comment | ML_COMMENT )?
                alt107 = 3
                LA107_0 = self.input.LA(1)

                if (LA107_0 == SL_COMMENT) :
                    alt107 = 1
                elif (LA107_0 == ML_COMMENT) :
                    alt107 = 2
                if alt107 == 1:
                    # jaus2jsidl.g:761:11: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_message_type_def2885)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt107 == 2:
                    # jaus2jsidl.g:761:24: ML_COMMENT
                    pass 
                    self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_message_type_def2889)



                #action start
                         
                h = etree.SubElement(n,'header',attrib={'name':'header'})
                rec = etree.SubElement(h,'record', attrib={'name':'HeaderRec',
                                                           'optional':'false'});
                nn = etree.SubElement(rec,'fixed_field',attrib={'name':'MessageID',
                                                         'field_type':'unsigned short integer',
                                                         'field_units':'one',
                                                         'optional':'false',
                                                         'interpretation':'Two byte field to hold message ID'});
                b = etree.SubElement(n,'body')
                b.attrib['name'] = 'body'
                f = etree.SubElement(n,'footer')
                f.attrib['name'] = 'footer'
                self.current_node = b
                    
                #action end
                # jaus2jsidl.g:777:9: ( container_type_def | type_reference )?
                alt108 = 3
                LA108_0 = self.input.LA(1)

                if (LA108_0 == ITEM_CARDINALITY or LA108_0 == 103 or LA108_0 == 105 or (107 <= LA108_0 <= 108)) :
                    alt108 = 1
                elif (LA108_0 == ID or LA108_0 == 50 or LA108_0 == 81) :
                    alt108 = 2
                if alt108 == 1:
                    # jaus2jsidl.g:777:10: container_type_def
                    pass 
                    self._state.following.append(self.FOLLOW_container_type_def_in_message_type_def2912)
                    self.container_type_def()

                    self._state.following.pop()


                elif alt108 == 2:
                    # jaus2jsidl.g:777:31: type_reference
                    pass 
                    self._state.following.append(self.FOLLOW_type_reference_in_message_type_def2916)
                    self.type_reference()

                    self._state.following.pop()



                # jaus2jsidl.g:778:9: ( return_spec )?
                alt109 = 2
                LA109_0 = self.input.LA(1)

                if (LA109_0 == 109) :
                    alt109 = 1
                if alt109 == 1:
                    # jaus2jsidl.g:778:9: return_spec
                    pass 
                    self._state.following.append(self.FOLLOW_return_spec_in_message_type_def2928)
                    self.return_spec()

                    self._state.following.pop()



                self.match(self.input, 39, self.FOLLOW_39_in_message_type_def2944)
                # jaus2jsidl.g:779:13: ( ';' )?
                alt110 = 2
                LA110_0 = self.input.LA(1)

                if (LA110_0 == 40) :
                    alt110 = 1
                if alt110 == 1:
                    # jaus2jsidl.g:779:13: ';'
                    pass 
                    self.match(self.input, 40, self.FOLLOW_40_in_message_type_def2946)



                #action start
                         
                self.current_node = p
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "message_type_def"


    # $ANTLR start "message_def"
    # jaus2jsidl.g:785:1: message_def : MESSAGE_CLASS MESSAGE_CODE ID ( attrs )? '{' description ( ml_comment | ML_COMMENT )? ( type_reference )? ( return_spec )? '}' ( ';' )? ;
    def message_def(self, ):

        MESSAGE_CLASS33 = None
        ID34 = None
        MESSAGE_CODE35 = None

        try:
            try:
                # jaus2jsidl.g:786:5: ( MESSAGE_CLASS MESSAGE_CODE ID ( attrs )? '{' description ( ml_comment | ML_COMMENT )? ( type_reference )? ( return_spec )? '}' ( ';' )? )
                # jaus2jsidl.g:787:9: MESSAGE_CLASS MESSAGE_CODE ID ( attrs )? '{' description ( ml_comment | ML_COMMENT )? ( type_reference )? ( return_spec )? '}' ( ';' )?
                pass 
                MESSAGE_CLASS33=self.match(self.input, MESSAGE_CLASS, self.FOLLOW_MESSAGE_CLASS_in_message_def2982)
                MESSAGE_CODE35=self.match(self.input, MESSAGE_CODE, self.FOLLOW_MESSAGE_CODE_in_message_def2984)
                ID34=self.match(self.input, ID, self.FOLLOW_ID_in_message_def2986)
                # jaus2jsidl.g:787:39: ( attrs )?
                alt111 = 2
                LA111_0 = self.input.LA(1)

                if (LA111_0 == 32) :
                    alt111 = 1
                if alt111 == 1:
                    # jaus2jsidl.g:787:39: attrs
                    pass 
                    self._state.following.append(self.FOLLOW_attrs_in_message_def2988)
                    self.attrs()

                    self._state.following.pop()



                self.match(self.input, 38, self.FOLLOW_38_in_message_def2991)
                #action start
                         
                p = self.current_node
                # TODO: later use namespaces to figure out message class?
                if MESSAGE_CLASS33.text in ['command', 'query']:
                    n = etree.SubElement(self.input_set,'message_def')
                elif MESSAGE_CLASS33.text == 'inform':
                    # This is an 'inform'
                    n = etree.SubElement(self.output_set,'message_def')
                else:
                    raise Exception("In a message_set, message %s has unrecognized class %s\n"%(ID34.text,MESSAGE_CLASS33.text))
                n.attrib['name'] = ID34.text
                self.type_map[ID34.text] = n
                n.attrib['message_id'] = MESSAGE_CODE35.text[2:]  # remove '0x'
                if MESSAGE_CLASS33.text == 'command':
                    n.attrib['is_command'] = 'true'
                else:
                    n.attrib['is_command'] = 'false'
                self.current_node = n
                        
                #action end
                self._state.following.append(self.FOLLOW_description_in_message_def3012)
                self.description()

                self._state.following.pop()
                # jaus2jsidl.g:808:9: ( ml_comment | ML_COMMENT )?
                alt112 = 3
                LA112_0 = self.input.LA(1)

                if (LA112_0 == SL_COMMENT) :
                    alt112 = 1
                elif (LA112_0 == ML_COMMENT) :
                    alt112 = 2
                if alt112 == 1:
                    # jaus2jsidl.g:808:11: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_message_def3028)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt112 == 2:
                    # jaus2jsidl.g:808:24: ML_COMMENT
                    pass 
                    self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_message_def3032)



                #action start
                         
                h = etree.SubElement(n,'header',attrib={'name':'header'})
                rec = etree.SubElement(h,'record', attrib={'name':'HeaderRec',
                                                           'optional':'false'});
                nn = etree.SubElement(rec,'fixed_field',attrib={'name':'MessageID',
                                                         'field_type':'unsigned short integer',
                                                         'field_units':'one',
                                                         'optional':'false',
                                                         'interpretation':'Two byte field to hold message ID'});
                b = etree.SubElement(n,'body')
                b.attrib['name'] = 'body'
                f = etree.SubElement(n,'footer')
                f.attrib['name'] = 'footer'
                self.current_node = b
                    
                #action end
                # jaus2jsidl.g:824:9: ( type_reference )?
                alt113 = 2
                LA113_0 = self.input.LA(1)

                if (LA113_0 == ID or LA113_0 == 50 or LA113_0 == 81) :
                    alt113 = 1
                if alt113 == 1:
                    # jaus2jsidl.g:824:10: type_reference
                    pass 
                    self._state.following.append(self.FOLLOW_type_reference_in_message_def3055)
                    self.type_reference()

                    self._state.following.pop()



                # jaus2jsidl.g:825:9: ( return_spec )?
                alt114 = 2
                LA114_0 = self.input.LA(1)

                if (LA114_0 == 109) :
                    alt114 = 1
                if alt114 == 1:
                    # jaus2jsidl.g:825:9: return_spec
                    pass 
                    self._state.following.append(self.FOLLOW_return_spec_in_message_def3067)
                    self.return_spec()

                    self._state.following.pop()



                self.match(self.input, 39, self.FOLLOW_39_in_message_def3083)
                # jaus2jsidl.g:826:13: ( ';' )?
                alt115 = 2
                LA115_0 = self.input.LA(1)

                if (LA115_0 == 40) :
                    alt115 = 1
                if alt115 == 1:
                    # jaus2jsidl.g:826:13: ';'
                    pass 
                    self.match(self.input, 40, self.FOLLOW_40_in_message_def3085)



                #action start
                         
                self.current_node = p
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "message_def"


    # $ANTLR start "event_def"
    # jaus2jsidl.g:833:1: event_def : 'event' ID '{' ( description )? ( type_reference )? ( return_spec )? '}' ( ';' )? ( ml_comment | ML_COMMENT )? ;
    def event_def(self, ):

        ID36 = None

        try:
            try:
                # jaus2jsidl.g:834:5: ( 'event' ID '{' ( description )? ( type_reference )? ( return_spec )? '}' ( ';' )? ( ml_comment | ML_COMMENT )? )
                # jaus2jsidl.g:835:9: 'event' ID '{' ( description )? ( type_reference )? ( return_spec )? '}' ( ';' )? ( ml_comment | ML_COMMENT )?
                pass 
                self.match(self.input, 81, self.FOLLOW_81_in_event_def3122)
                ID36=self.match(self.input, ID, self.FOLLOW_ID_in_event_def3124)
                self.match(self.input, 38, self.FOLLOW_38_in_event_def3126)
                #action start
                         
                p = self.current_node
                n = etree.SubElement(self.current_node,'event_def')
                n.attrib['name'] = ID36.text
                self.type_map[ID36.text] = n
                self.current_node = n
                        
                #action end
                # jaus2jsidl.g:843:9: ( description )?
                alt116 = 2
                LA116_0 = self.input.LA(1)

                if (LA116_0 == 41) :
                    alt116 = 1
                if alt116 == 1:
                    # jaus2jsidl.g:843:9: description
                    pass 
                    self._state.following.append(self.FOLLOW_description_in_event_def3147)
                    self.description()

                    self._state.following.pop()



                #action start
                         
                h = etree.SubElement(n,'header')
                h.attrib['name'] = 'header'
                b = etree.SubElement(n,'body')
                b.attrib['name'] = 'body'
                f = etree.SubElement(n,'footer')
                f.attrib['name'] = 'footer'
                self.current_node = b
                p_comment = self.comment
                self.comment = ''
                    
                #action end
                # jaus2jsidl.g:855:9: ( type_reference )?
                alt117 = 2
                LA117_0 = self.input.LA(1)

                if (LA117_0 == ID or LA117_0 == 50 or LA117_0 == 81) :
                    alt117 = 1
                if alt117 == 1:
                    # jaus2jsidl.g:855:9: type_reference
                    pass 
                    self._state.following.append(self.FOLLOW_type_reference_in_event_def3168)
                    self.type_reference()

                    self._state.following.pop()



                # jaus2jsidl.g:856:9: ( return_spec )?
                alt118 = 2
                LA118_0 = self.input.LA(1)

                if (LA118_0 == 109) :
                    alt118 = 1
                if alt118 == 1:
                    # jaus2jsidl.g:856:9: return_spec
                    pass 
                    self._state.following.append(self.FOLLOW_return_spec_in_event_def3179)
                    self.return_spec()

                    self._state.following.pop()



                self.match(self.input, 39, self.FOLLOW_39_in_event_def3195)
                # jaus2jsidl.g:857:13: ( ';' )?
                alt119 = 2
                LA119_0 = self.input.LA(1)

                if (LA119_0 == 40) :
                    alt119 = 1
                if alt119 == 1:
                    # jaus2jsidl.g:857:13: ';'
                    pass 
                    self.match(self.input, 40, self.FOLLOW_40_in_event_def3197)



                # jaus2jsidl.g:857:19: ( ml_comment | ML_COMMENT )?
                alt120 = 3
                LA120_0 = self.input.LA(1)

                if (LA120_0 == SL_COMMENT) :
                    alt120 = 1
                elif (LA120_0 == ML_COMMENT) :
                    alt120 = 2
                if alt120 == 1:
                    # jaus2jsidl.g:857:21: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_event_def3203)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt120 == 2:
                    # jaus2jsidl.g:857:34: ML_COMMENT
                    pass 
                    self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_event_def3207)



                #action start
                     
                self.comment = p_comment
                self.current_node = p
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "event_def"


    # $ANTLR start "simple_numeric_type"
    # jaus2jsidl.g:866:1: simple_numeric_type : v1= ( 'uint8' | 'uint16' | 'uint24' | 'uint32' | 'uint64' | 'int8' | 'int16' | 'int32' | 'int64' | 'float' | 'double' ) ;
    def simple_numeric_type(self, ):

        v1 = None

        try:
            try:
                # jaus2jsidl.g:867:5: (v1= ( 'uint8' | 'uint16' | 'uint24' | 'uint32' | 'uint64' | 'int8' | 'int16' | 'int32' | 'int64' | 'float' | 'double' ) )
                # jaus2jsidl.g:867:7: v1= ( 'uint8' | 'uint16' | 'uint24' | 'uint32' | 'uint64' | 'int8' | 'int16' | 'int32' | 'int64' | 'float' | 'double' )
                pass 
                v1 = self.input.LT(1)
                if (82 <= self.input.LA(1) <= 92):
                    self.input.consume()
                    self._state.errorRecovery = False

                else:
                    mse = MismatchedSetException(None, self.input)
                    raise mse


                #action start
                         
                self.current_node.attrib['field_type'] = self.scalar_map[v1.text]
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "simple_numeric_type"

    class numeric_literal_return(ParserRuleReturnScope):
        def __init__(self):
            ParserRuleReturnScope.__init__(self)





    # $ANTLR start "numeric_literal"
    # jaus2jsidl.g:875:1: numeric_literal : ( '-' )? ( INTLITERAL | DOUBLELITERAL ) ;
    def numeric_literal(self, ):

        retval = self.numeric_literal_return()
        retval.start = self.input.LT(1)

        try:
            try:
                # jaus2jsidl.g:876:5: ( ( '-' )? ( INTLITERAL | DOUBLELITERAL ) )
                # jaus2jsidl.g:877:9: ( '-' )? ( INTLITERAL | DOUBLELITERAL )
                pass 
                # jaus2jsidl.g:877:9: ( '-' )?
                alt121 = 2
                LA121_0 = self.input.LA(1)

                if (LA121_0 == 72) :
                    alt121 = 1
                if alt121 == 1:
                    # jaus2jsidl.g:877:9: '-'
                    pass 
                    self.match(self.input, 72, self.FOLLOW_72_in_numeric_literal3340)



                if (INTLITERAL <= self.input.LA(1) <= DOUBLELITERAL):
                    self.input.consume()
                    self._state.errorRecovery = False

                else:
                    mse = MismatchedSetException(None, self.input)
                    raise mse





                retval.stop = self.input.LT(-1)


            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return retval

    # $ANTLR end "numeric_literal"


    # $ANTLR start "value_set_type_def"
    # jaus2jsidl.g:880:1: value_set_type_def : 'enum' ID ( attrs )? '{' ( value_spec )* '}' ( ';' )? ( ml_comment )? ;
    def value_set_type_def(self, ):

        ID37 = None

        try:
            try:
                # jaus2jsidl.g:881:5: ( 'enum' ID ( attrs )? '{' ( value_spec )* '}' ( ';' )? ( ml_comment )? )
                # jaus2jsidl.g:881:7: 'enum' ID ( attrs )? '{' ( value_spec )* '}' ( ';' )? ( ml_comment )?
                pass 
                self.match(self.input, 93, self.FOLLOW_93_in_value_set_type_def3367)
                ID37=self.match(self.input, ID, self.FOLLOW_ID_in_value_set_type_def3369)
                #action start
                     
                p = self.current_node
                n = etree.SubElement(p,'value_set')
                name = ID37.text
                n.attrib['name'] = name
                self.type_map[ID37.text] = n
                self.value_set_map[ID37.text] = {}
                n.attrib['offset_to_lower_limit'] = "false"
                self.current_node = n
                self.comment = ''
                debug(4,"Processing value_set_type_def %s\n"%name)
                    
                #action end
                # jaus2jsidl.g:894:5: ( attrs )?
                alt122 = 2
                LA122_0 = self.input.LA(1)

                if (LA122_0 == 32) :
                    alt122 = 1
                if alt122 == 1:
                    # jaus2jsidl.g:894:5: attrs
                    pass 
                    self._state.following.append(self.FOLLOW_attrs_in_value_set_type_def3381)
                    self.attrs()

                    self._state.following.pop()



                self.match(self.input, 38, self.FOLLOW_38_in_value_set_type_def3384)
                # jaus2jsidl.g:895:5: ( value_spec )*
                while True: #loop123
                    alt123 = 2
                    LA123_0 = self.input.LA(1)

                    if (LA123_0 == ID) :
                        alt123 = 1


                    if alt123 == 1:
                        # jaus2jsidl.g:895:5: value_spec
                        pass 
                        self._state.following.append(self.FOLLOW_value_spec_in_value_set_type_def3390)
                        self.value_spec()

                        self._state.following.pop()


                    else:
                        break #loop123


                self.match(self.input, 39, self.FOLLOW_39_in_value_set_type_def3397)
                # jaus2jsidl.g:896:9: ( ';' )?
                alt124 = 2
                LA124_0 = self.input.LA(1)

                if (LA124_0 == 40) :
                    alt124 = 1
                if alt124 == 1:
                    # jaus2jsidl.g:896:9: ';'
                    pass 
                    self.match(self.input, 40, self.FOLLOW_40_in_value_set_type_def3399)



                # jaus2jsidl.g:896:14: ( ml_comment )?
                alt125 = 2
                LA125_0 = self.input.LA(1)

                if (LA125_0 == SL_COMMENT) :
                    alt125 = 1
                if alt125 == 1:
                    # jaus2jsidl.g:896:14: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_value_set_type_def3402)
                    self.ml_comment()

                    self._state.following.pop()



                #action start
                     
                if self.comment != '':
                    comment = self.comment.replace('//',' ').strip()
                    n.attrib['interpretation'] = compress_ws(comment)
                    self.comment = ''
                self.value_set_map[name] = n
                self.current_node = p
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "value_set_type_def"


    # $ANTLR start "field_def"
    # jaus2jsidl.g:908:1: field_def : ITEM_CARDINALITY ( string_def | ( simple_numeric_type v2= ID UNIT ( value_range_set | scaled_range_def | value_set_def )? ) | ( scoped_id v3= ID ) ) '=' INTLITERAL ';' ( ml_comment | ML_COMMENT )? ;
    def field_def(self, ):

        v2 = None
        v3 = None
        ITEM_CARDINALITY39 = None
        UNIT40 = None
        INTLITERAL41 = None
        scoped_id38 = None


        try:
            try:
                # jaus2jsidl.g:909:5: ( ITEM_CARDINALITY ( string_def | ( simple_numeric_type v2= ID UNIT ( value_range_set | scaled_range_def | value_set_def )? ) | ( scoped_id v3= ID ) ) '=' INTLITERAL ';' ( ml_comment | ML_COMMENT )? )
                # jaus2jsidl.g:909:7: ITEM_CARDINALITY ( string_def | ( simple_numeric_type v2= ID UNIT ( value_range_set | scaled_range_def | value_set_def )? ) | ( scoped_id v3= ID ) ) '=' INTLITERAL ';' ( ml_comment | ML_COMMENT )?
                pass 
                ITEM_CARDINALITY39=self.match(self.input, ITEM_CARDINALITY, self.FOLLOW_ITEM_CARDINALITY_in_field_def3424)
                #action start
                     
                p = self.current_node
                n = etree.SubElement(p,'tag_tbd')
                self.current_node = n
                self.comment = ''
                    
                #action end
                # jaus2jsidl.g:916:5: ( string_def | ( simple_numeric_type v2= ID UNIT ( value_range_set | scaled_range_def | value_set_def )? ) | ( scoped_id v3= ID ) )
                alt127 = 3
                LA127 = self.input.LA(1)
                if LA127 == 94 or LA127 == 95:
                    alt127 = 1
                elif LA127 == 82 or LA127 == 83 or LA127 == 84 or LA127 == 85 or LA127 == 86 or LA127 == 87 or LA127 == 88 or LA127 == 89 or LA127 == 90 or LA127 == 91 or LA127 == 92:
                    alt127 = 2
                elif LA127 == ID or LA127 == 50 or LA127 == 81:
                    alt127 = 3
                else:
                    nvae = NoViableAltException("", 127, 0, self.input)

                    raise nvae

                if alt127 == 1:
                    # jaus2jsidl.g:916:7: string_def
                    pass 
                    self._state.following.append(self.FOLLOW_string_def_in_field_def3438)
                    self.string_def()

                    self._state.following.pop()


                elif alt127 == 2:
                    # jaus2jsidl.g:917:7: ( simple_numeric_type v2= ID UNIT ( value_range_set | scaled_range_def | value_set_def )? )
                    pass 
                    # jaus2jsidl.g:917:7: ( simple_numeric_type v2= ID UNIT ( value_range_set | scaled_range_def | value_set_def )? )
                    # jaus2jsidl.g:917:9: simple_numeric_type v2= ID UNIT ( value_range_set | scaled_range_def | value_set_def )?
                    pass 
                    self._state.following.append(self.FOLLOW_simple_numeric_type_in_field_def3448)
                    self.simple_numeric_type()

                    self._state.following.pop()
                    v2=self.match(self.input, ID, self.FOLLOW_ID_in_field_def3453)
                    UNIT40=self.match(self.input, UNIT, self.FOLLOW_UNIT_in_field_def3455)
                    # jaus2jsidl.g:917:41: ( value_range_set | scaled_range_def | value_set_def )?
                    alt126 = 4
                    LA126 = self.input.LA(1)
                    if LA126 == 32 or LA126 == 60:
                        alt126 = 1
                    elif LA126 == 101:
                        alt126 = 2
                    elif LA126 == 38:
                        alt126 = 3
                    if alt126 == 1:
                        # jaus2jsidl.g:917:43: value_range_set
                        pass 
                        self._state.following.append(self.FOLLOW_value_range_set_in_field_def3459)
                        self.value_range_set()

                        self._state.following.pop()


                    elif alt126 == 2:
                        # jaus2jsidl.g:917:61: scaled_range_def
                        pass 
                        self._state.following.append(self.FOLLOW_scaled_range_def_in_field_def3463)
                        self.scaled_range_def()

                        self._state.following.pop()


                    elif alt126 == 3:
                        # jaus2jsidl.g:917:80: value_set_def
                        pass 
                        self._state.following.append(self.FOLLOW_value_set_def_in_field_def3467)
                        self.value_set_def()

                        self._state.following.pop()



                    #action start
                         
                    n.attrib['name'] = v2.text
                        
                    #action end





                elif alt127 == 3:
                    # jaus2jsidl.g:922:7: ( scoped_id v3= ID )
                    pass 
                    # jaus2jsidl.g:922:7: ( scoped_id v3= ID )
                    # jaus2jsidl.g:922:9: scoped_id v3= ID
                    pass 
                    self._state.following.append(self.FOLLOW_scoped_id_in_field_def3495)
                    scoped_id38 = self.scoped_id()

                    self._state.following.pop()
                    v3=self.match(self.input, ID, self.FOLLOW_ID_in_field_def3500)
                    #action start
                         
                    n.attrib['name'] = v3.text
                    self.second_pass_tag_resolution[n] = ((scoped_id38 is not None) and [self.input.toString(scoped_id38.start,scoped_id38.stop)] or [None])[0]
                    n.attrib['declared_type_ref'] = ((scoped_id38 is not None) and [self.input.toString(scoped_id38.start,scoped_id38.stop)] or [None])[0]
                        
                    #action end






                self.match(self.input, 34, self.FOLLOW_34_in_field_def3524)
                INTLITERAL41=self.match(self.input, INTLITERAL, self.FOLLOW_INTLITERAL_in_field_def3526)
                self.match(self.input, 40, self.FOLLOW_40_in_field_def3528)
                # jaus2jsidl.g:929:24: ( ml_comment | ML_COMMENT )?
                alt128 = 3
                LA128_0 = self.input.LA(1)

                if (LA128_0 == SL_COMMENT) :
                    alt128 = 1
                elif (LA128_0 == ML_COMMENT) :
                    alt128 = 2
                if alt128 == 1:
                    # jaus2jsidl.g:929:26: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_field_def3532)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt128 == 2:
                    # jaus2jsidl.g:929:39: ML_COMMENT
                    pass 
                    self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_field_def3536)



                #action start
                     
                name = n.attrib['name']
                if ITEM_CARDINALITY39.text == 'optional':
                    n.attrib['optional'] = 'true'
                    self.optional_count += 1
                    debug(7, "Found optional field %s\n"%(name))
                else:
                    n.attrib['optional'] = 'false'  # 'required' or 'repeated'
                if 'declared_type_ref' not in n.attrib:
                    # Don not save info about declared type refs.
                    self.type_map[name] = n
                if n.tag == 'tag_tbd':
                    # By process of elimination, this is a fixed_field.
                    n.tag = 'fixed_field'   # Sure way to tell this is a fixed_field.
                    if UNIT40:
                            n.attrib['field_units'] = UNIT40.text.replace('_',' ')
                elif n.tag == 'bit_field':
                    # This is a bit_field, so have to update the field type accordingly.
                    # TODO: catch signed or float type used for a bit_field.
                    if 'field_type' in n.attrib:
                        n.attrib['field_type_unsigned'] = n.attrib['field_type']
                        del n.attrib['field_type']
                    else:
                        debug(4,"No unsigned numeric type for bit_field %s\n"%name)
                else:
                    # This is identified as a *_length_string
                    pass
                if INTLITERAL41 and self.jsidl_ns == "urn:jaus:jsidl:exp":
                    n.attrib['item_index'] = INTLITERAL41.text
                self.current_node = p
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "field_def"


    # $ANTLR start "string_def"
    # jaus2jsidl.g:964:1: string_def : ( ( 'string' id1= ID '[' len1= ( INTLITERAL | ID ) ']' ) | ( 'vstring' id2= ID '[' len1= ( INTLITERAL | ID ) ',' len2= ( INTLITERAL | ID ) ']' ) ) ;
    def string_def(self, ):

        id1 = None
        len1 = None
        id2 = None
        len2 = None

        try:
            try:
                # jaus2jsidl.g:965:5: ( ( ( 'string' id1= ID '[' len1= ( INTLITERAL | ID ) ']' ) | ( 'vstring' id2= ID '[' len1= ( INTLITERAL | ID ) ',' len2= ( INTLITERAL | ID ) ']' ) ) )
                # jaus2jsidl.g:965:7: ( ( 'string' id1= ID '[' len1= ( INTLITERAL | ID ) ']' ) | ( 'vstring' id2= ID '[' len1= ( INTLITERAL | ID ) ',' len2= ( INTLITERAL | ID ) ']' ) )
                pass 
                # jaus2jsidl.g:965:7: ( ( 'string' id1= ID '[' len1= ( INTLITERAL | ID ) ']' ) | ( 'vstring' id2= ID '[' len1= ( INTLITERAL | ID ) ',' len2= ( INTLITERAL | ID ) ']' ) )
                alt129 = 2
                LA129_0 = self.input.LA(1)

                if (LA129_0 == 94) :
                    alt129 = 1
                elif (LA129_0 == 95) :
                    alt129 = 2
                else:
                    nvae = NoViableAltException("", 129, 0, self.input)

                    raise nvae

                if alt129 == 1:
                    # jaus2jsidl.g:965:9: ( 'string' id1= ID '[' len1= ( INTLITERAL | ID ) ']' )
                    pass 
                    # jaus2jsidl.g:965:9: ( 'string' id1= ID '[' len1= ( INTLITERAL | ID ) ']' )
                    # jaus2jsidl.g:965:11: 'string' id1= ID '[' len1= ( INTLITERAL | ID ) ']'
                    pass 
                    self.match(self.input, 94, self.FOLLOW_94_in_string_def3567)
                    id1=self.match(self.input, ID, self.FOLLOW_ID_in_string_def3571)
                    self.match(self.input, 60, self.FOLLOW_60_in_string_def3573)
                    len1 = self.input.LT(1)
                    if self.input.LA(1) == ID or self.input.LA(1) == INTLITERAL:
                        self.input.consume()
                        self._state.errorRecovery = False

                    else:
                        mse = MismatchedSetException(None, self.input)
                        raise mse


                    self.match(self.input, 61, self.FOLLOW_61_in_string_def3587)



                    #action start
                         
                    debug(4,"In string_def\n")
                    self.current_node.tag = 'fixed_length_string'
                    self.current_node.attrib['name'] = name = id1.text
                    self.current_node.attrib['string_length'] = len1.text
                        
                    #action end


                elif alt129 == 2:
                    # jaus2jsidl.g:972:7: ( 'vstring' id2= ID '[' len1= ( INTLITERAL | ID ) ',' len2= ( INTLITERAL | ID ) ']' )
                    pass 
                    # jaus2jsidl.g:972:7: ( 'vstring' id2= ID '[' len1= ( INTLITERAL | ID ) ',' len2= ( INTLITERAL | ID ) ']' )
                    # jaus2jsidl.g:972:9: 'vstring' id2= ID '[' len1= ( INTLITERAL | ID ) ',' len2= ( INTLITERAL | ID ) ']'
                    pass 
                    self.match(self.input, 95, self.FOLLOW_95_in_string_def3605)
                    id2=self.match(self.input, ID, self.FOLLOW_ID_in_string_def3609)
                    self.match(self.input, 60, self.FOLLOW_60_in_string_def3611)
                    len1 = self.input.LT(1)
                    if self.input.LA(1) == ID or self.input.LA(1) == INTLITERAL:
                        self.input.consume()
                        self._state.errorRecovery = False

                    else:
                        mse = MismatchedSetException(None, self.input)
                        raise mse


                    self.match(self.input, 35, self.FOLLOW_35_in_string_def3625)
                    len2 = self.input.LT(1)
                    if self.input.LA(1) == ID or self.input.LA(1) == INTLITERAL:
                        self.input.consume()
                        self._state.errorRecovery = False

                    else:
                        mse = MismatchedSetException(None, self.input)
                        raise mse


                    self.match(self.input, 61, self.FOLLOW_61_in_string_def3639)



                    #action start
                         
                    debug(4,"In vstring_def\n")
                    self.current_node.tag = 'variable_length_string'
                    self.current_node.attrib['name'] = name = id2.text
                    self.type_map[name] = self.current_node
                    nn = etree.SubElement(self.current_node,'count_field')
                    nn.attrib['min_count'] = len1.text
                    nn.attrib['max_count'] = len2.text
                    nn.attrib['field_type_unsigned'] = self.scalar_map['uint32'] # TODO: fix
                    try:
                        debug(4,"Looking at vstring %s\n"%(name))
                        self.current_node.tag = self.scalar_map['vstring']
                    except Exception, e:
                        debug(1,"string_def: %s\n"%repr(e))
                        raise Exception('Bad string_def: %s %s %s %s %s %s %s'%(
                         self.input.LB(7).getText(),
                         self.input.LB(6).getText(),
                         self.input.LB(5).getText(),
                         self.input.LB(4).getText(),
                         self.input.LB(3).getText(),
                         self.input.LB(2).getText(),
                         self.input.LB(1).getText()))
                        
                    #action end







            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "string_def"


    # $ANTLR start "constraint_ref"
    # jaus2jsidl.g:1001:1: constraint_ref : ( value_range_set | scaled_range_def | value_set_def );
    def constraint_ref(self, ):

        try:
            try:
                # jaus2jsidl.g:1002:5: ( value_range_set | scaled_range_def | value_set_def )
                alt130 = 3
                LA130 = self.input.LA(1)
                if LA130 == 32 or LA130 == 60:
                    alt130 = 1
                elif LA130 == 101:
                    alt130 = 2
                elif LA130 == 38:
                    alt130 = 3
                else:
                    nvae = NoViableAltException("", 130, 0, self.input)

                    raise nvae

                if alt130 == 1:
                    # jaus2jsidl.g:1003:5: value_range_set
                    pass 
                    self._state.following.append(self.FOLLOW_value_range_set_in_constraint_ref3678)
                    self.value_range_set()

                    self._state.following.pop()


                elif alt130 == 2:
                    # jaus2jsidl.g:1004:7: scaled_range_def
                    pass 
                    self._state.following.append(self.FOLLOW_scaled_range_def_in_constraint_ref3686)
                    self.scaled_range_def()

                    self._state.following.pop()


                elif alt130 == 3:
                    # jaus2jsidl.g:1005:8: value_set_def
                    pass 
                    self._state.following.append(self.FOLLOW_value_set_def_in_constraint_ref3695)
                    self.value_set_def()

                    self._state.following.pop()



            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "constraint_ref"


    # $ANTLR start "field_type_def"
    # jaus2jsidl.g:1009:1: field_type_def : 'field' ( string_def | ( simple_numeric_type v2= ID UNIT ( value_range_set | scaled_range_def | value_set_def )? ) ) ';' ( ml_comment | ML_COMMENT )? ;
    def field_type_def(self, ):

        v2 = None
        UNIT42 = None

        try:
            try:
                # jaus2jsidl.g:1010:5: ( 'field' ( string_def | ( simple_numeric_type v2= ID UNIT ( value_range_set | scaled_range_def | value_set_def )? ) ) ';' ( ml_comment | ML_COMMENT )? )
                # jaus2jsidl.g:1011:9: 'field' ( string_def | ( simple_numeric_type v2= ID UNIT ( value_range_set | scaled_range_def | value_set_def )? ) ) ';' ( ml_comment | ML_COMMENT )?
                pass 
                self.match(self.input, 96, self.FOLLOW_96_in_field_type_def3722)
                #action start
                         
                p = self.current_node
                n = etree.SubElement(p,'tag_tbd')
                self.current_node = n
                p_comment = self.comment
                self.comment = ''
                        
                #action end
                # jaus2jsidl.g:1019:5: ( string_def | ( simple_numeric_type v2= ID UNIT ( value_range_set | scaled_range_def | value_set_def )? ) )
                alt132 = 2
                LA132_0 = self.input.LA(1)

                if ((94 <= LA132_0 <= 95)) :
                    alt132 = 1
                elif ((82 <= LA132_0 <= 92)) :
                    alt132 = 2
                else:
                    nvae = NoViableAltException("", 132, 0, self.input)

                    raise nvae

                if alt132 == 1:
                    # jaus2jsidl.g:1019:7: string_def
                    pass 
                    self._state.following.append(self.FOLLOW_string_def_in_field_type_def3740)
                    self.string_def()

                    self._state.following.pop()


                elif alt132 == 2:
                    # jaus2jsidl.g:1020:8: ( simple_numeric_type v2= ID UNIT ( value_range_set | scaled_range_def | value_set_def )? )
                    pass 
                    # jaus2jsidl.g:1020:8: ( simple_numeric_type v2= ID UNIT ( value_range_set | scaled_range_def | value_set_def )? )
                    # jaus2jsidl.g:1020:10: simple_numeric_type v2= ID UNIT ( value_range_set | scaled_range_def | value_set_def )?
                    pass 
                    self._state.following.append(self.FOLLOW_simple_numeric_type_in_field_type_def3751)
                    self.simple_numeric_type()

                    self._state.following.pop()
                    v2=self.match(self.input, ID, self.FOLLOW_ID_in_field_type_def3755)
                    UNIT42=self.match(self.input, UNIT, self.FOLLOW_UNIT_in_field_type_def3757)
                    # jaus2jsidl.g:1021:10: ( value_range_set | scaled_range_def | value_set_def )?
                    alt131 = 4
                    LA131 = self.input.LA(1)
                    if LA131 == 32 or LA131 == 60:
                        alt131 = 1
                    elif LA131 == 101:
                        alt131 = 2
                    elif LA131 == 38:
                        alt131 = 3
                    if alt131 == 1:
                        # jaus2jsidl.g:1021:12: value_range_set
                        pass 
                        self._state.following.append(self.FOLLOW_value_range_set_in_field_type_def3770)
                        self.value_range_set()

                        self._state.following.pop()


                    elif alt131 == 2:
                        # jaus2jsidl.g:1021:30: scaled_range_def
                        pass 
                        self._state.following.append(self.FOLLOW_scaled_range_def_in_field_type_def3774)
                        self.scaled_range_def()

                        self._state.following.pop()


                    elif alt131 == 3:
                        # jaus2jsidl.g:1021:50: value_set_def
                        pass 
                        self._state.following.append(self.FOLLOW_value_set_def_in_field_type_def3779)
                        self.value_set_def()

                        self._state.following.pop()






                    #action start
                           
                    n.attrib['name'] = name = v2.text
                    self.type_map[name] = n
                         
                    #action end



                self.match(self.input, 40, self.FOLLOW_40_in_field_type_def3810)
                # jaus2jsidl.g:1027:11: ( ml_comment | ML_COMMENT )?
                alt133 = 3
                LA133_0 = self.input.LA(1)

                if (LA133_0 == SL_COMMENT) :
                    alt133 = 1
                elif (LA133_0 == ML_COMMENT) :
                    alt133 = 2
                if alt133 == 1:
                    # jaus2jsidl.g:1027:13: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_field_type_def3814)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt133 == 2:
                    # jaus2jsidl.g:1027:26: ML_COMMENT
                    pass 
                    self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_field_type_def3818)



                #action start
                      
                if n.tag == 'tag_tbd':
                    # By process of elimination, this is a fixed_field.
                    n.tag = 'fixed_field'   # Sure way to tell this is a fixed_field.
                    n.attrib['field_units'] = UNIT42.text.replace('_',' ')
                elif n.tag == 'bit_field':
                    # This is a bit_field, so have to update the field type accordingly.
                    # TODO: catch signed or float type used for a bit_field.
                    if 'field_type' in n.attrib:
                        n.attrib['field_type_unsigned'] = n.attrib['field_type']
                        del n.attrib['field_type']
                    else:
                        debug(4,"No unsigned numeric type for bit_field %s\n"%name)
                else:
                    # This is identified as a *_length_string
                    pass
                if self.jsidl_ns == "urn:jaus:jsidl:1.0":
                   n.attrib['optional'] = 'false'  # Required by JSIDL 1.0 even for unenclosed field typedef.
                self.comment = p_comment
                self.current_node = p
                        
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "field_type_def"


    # $ANTLR start "variant_field_type_def"
    # jaus2jsidl.g:1055:1: variant_field_type_def : ( ITEM_CARDINALITY )? 'variant_field' ID '{' ( ml_comment | mlc1= ML_COMMENT )? ( tagged_type_units_enum_def )+ '}' ( '=' INTLITERAL )? ';' ( ml_comment | mlc2= ML_COMMENT )? ;
    def variant_field_type_def(self, ):

        mlc1 = None
        mlc2 = None
        ID43 = None
        ITEM_CARDINALITY44 = None
        INTLITERAL45 = None

        try:
            try:
                # jaus2jsidl.g:1056:5: ( ( ITEM_CARDINALITY )? 'variant_field' ID '{' ( ml_comment | mlc1= ML_COMMENT )? ( tagged_type_units_enum_def )+ '}' ( '=' INTLITERAL )? ';' ( ml_comment | mlc2= ML_COMMENT )? )
                # jaus2jsidl.g:1057:5: ( ITEM_CARDINALITY )? 'variant_field' ID '{' ( ml_comment | mlc1= ML_COMMENT )? ( tagged_type_units_enum_def )+ '}' ( '=' INTLITERAL )? ';' ( ml_comment | mlc2= ML_COMMENT )?
                pass 
                # jaus2jsidl.g:1057:5: ( ITEM_CARDINALITY )?
                alt134 = 2
                LA134_0 = self.input.LA(1)

                if (LA134_0 == ITEM_CARDINALITY) :
                    alt134 = 1
                if alt134 == 1:
                    # jaus2jsidl.g:1057:7: ITEM_CARDINALITY
                    pass 
                    ITEM_CARDINALITY44=self.match(self.input, ITEM_CARDINALITY, self.FOLLOW_ITEM_CARDINALITY_in_variant_field_type_def3855)



                self.match(self.input, 97, self.FOLLOW_97_in_variant_field_type_def3860)
                ID43=self.match(self.input, ID, self.FOLLOW_ID_in_variant_field_type_def3862)
                self.match(self.input, 38, self.FOLLOW_38_in_variant_field_type_def3864)
                #action start
                     
                p = self.current_node
                n = etree.SubElement(p,'variable_field')
                n.attrib['name'] = name = ID43.text
                self.type_map[name] = n
                if ITEM_CARDINALITY44:
                    if ITEM_CARDINALITY44.text == 'required':
                        n.attrib['optional'] = 'false'
                    else:
                        n.attrib['optional'] = 'true'
                        self.optional_count += 1
                else:
                        n.attrib['optional'] = 'false'
                nn = etree.SubElement(n,'type_and_units_field')
                self.current_node = nn
                p_comment = self.comment
                self.comment = ''
                    
                #action end
                # jaus2jsidl.g:1076:5: ( ml_comment | mlc1= ML_COMMENT )?
                alt135 = 3
                LA135_0 = self.input.LA(1)

                if (LA135_0 == SL_COMMENT) :
                    alt135 = 1
                elif (LA135_0 == ML_COMMENT) :
                    alt135 = 2
                if alt135 == 1:
                    # jaus2jsidl.g:1076:7: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_variant_field_type_def3878)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt135 == 2:
                    # jaus2jsidl.g:1076:20: mlc1= ML_COMMENT
                    pass 
                    mlc1=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_variant_field_type_def3885)



                # jaus2jsidl.g:1077:5: ( tagged_type_units_enum_def )+
                cnt136 = 0
                while True: #loop136
                    alt136 = 2
                    LA136_0 = self.input.LA(1)

                    if (LA136_0 == 98) :
                        alt136 = 1


                    if alt136 == 1:
                        # jaus2jsidl.g:1077:7: tagged_type_units_enum_def
                        pass 
                        self._state.following.append(self.FOLLOW_tagged_type_units_enum_def_in_variant_field_type_def3896)
                        self.tagged_type_units_enum_def()

                        self._state.following.pop()


                    else:
                        if cnt136 >= 1:
                            break #loop136

                        eee = EarlyExitException(136, self.input)
                        raise eee

                    cnt136 += 1


                self.match(self.input, 39, self.FOLLOW_39_in_variant_field_type_def3905)
                # jaus2jsidl.g:1079:5: ( '=' INTLITERAL )?
                alt137 = 2
                LA137_0 = self.input.LA(1)

                if (LA137_0 == 34) :
                    alt137 = 1
                if alt137 == 1:
                    # jaus2jsidl.g:1079:7: '=' INTLITERAL
                    pass 
                    self.match(self.input, 34, self.FOLLOW_34_in_variant_field_type_def3913)
                    INTLITERAL45=self.match(self.input, INTLITERAL, self.FOLLOW_INTLITERAL_in_variant_field_type_def3915)



                self.match(self.input, 40, self.FOLLOW_40_in_variant_field_type_def3920)
                # jaus2jsidl.g:1079:29: ( ml_comment | mlc2= ML_COMMENT )?
                alt138 = 3
                LA138_0 = self.input.LA(1)

                if (LA138_0 == SL_COMMENT) :
                    alt138 = 1
                elif (LA138_0 == ML_COMMENT) :
                    alt138 = 2
                if alt138 == 1:
                    # jaus2jsidl.g:1079:31: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_variant_field_type_def3924)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt138 == 2:
                    # jaus2jsidl.g:1079:44: mlc2= ML_COMMENT
                    pass 
                    mlc2=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_variant_field_type_def3931)



                #action start
                         
                if len(self.comment) > 0:
                    comment = self.comment.replace('//',' ').strip()
                    n.attrib['interpretation'] = compress_ws(comment)
                if mlc1:
                    s = mlc1.text[2:-2].strip()
                    n.attrib['interpretation'] += compress_ws(s)
                if mlc2:
                    s = mlc2.text[2:-2].strip()
                    n.attrib['interpretation'] += compress_ws(s)
                if INTLITERAL45 and self.jsidl_ns == "urn:jaus:jsidl:exp":
                    n.attrib['item_index'] = INTLITERAL45.text
                self.current_node = p
                self.comment = p_comment
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "variant_field_type_def"


    # $ANTLR start "tagged_type_units_enum_def"
    # jaus2jsidl.g:1098:1: tagged_type_units_enum_def : 'tag' (const_tag= INTLITERAL | tag= ID ) ':' id= ID simple_numeric_type UNIT ( value_set_def | declared_value_set_def | scaled_range_def )? ';' ( ml_comment | ML_COMMENT )? ;
    def tagged_type_units_enum_def(self, ):

        const_tag = None
        tag = None
        id = None
        UNIT46 = None

        try:
            try:
                # jaus2jsidl.g:1099:5: ( 'tag' (const_tag= INTLITERAL | tag= ID ) ':' id= ID simple_numeric_type UNIT ( value_set_def | declared_value_set_def | scaled_range_def )? ';' ( ml_comment | ML_COMMENT )? )
                # jaus2jsidl.g:1099:7: 'tag' (const_tag= INTLITERAL | tag= ID ) ':' id= ID simple_numeric_type UNIT ( value_set_def | declared_value_set_def | scaled_range_def )? ';' ( ml_comment | ML_COMMENT )?
                pass 
                self.match(self.input, 98, self.FOLLOW_98_in_tagged_type_units_enum_def3958)
                # jaus2jsidl.g:1099:13: (const_tag= INTLITERAL | tag= ID )
                alt139 = 2
                LA139_0 = self.input.LA(1)

                if (LA139_0 == INTLITERAL) :
                    alt139 = 1
                elif (LA139_0 == ID) :
                    alt139 = 2
                else:
                    nvae = NoViableAltException("", 139, 0, self.input)

                    raise nvae

                if alt139 == 1:
                    # jaus2jsidl.g:1099:14: const_tag= INTLITERAL
                    pass 
                    const_tag=self.match(self.input, INTLITERAL, self.FOLLOW_INTLITERAL_in_tagged_type_units_enum_def3964)


                elif alt139 == 2:
                    # jaus2jsidl.g:1099:38: tag= ID
                    pass 
                    tag=self.match(self.input, ID, self.FOLLOW_ID_in_tagged_type_units_enum_def3971)



                #action start
                     
                p = self.current_node
                n = etree.SubElement(p,'type_and_units_enum')
                if const_tag:
                    n.attrib['index'] =  const_tag.text
                elif tag:
                    n.attrib['index'] =  tag.text
                self.current_node = n  # so the following rule updates the right node.
                    
                #action end
                self.match(self.input, 77, self.FOLLOW_77_in_tagged_type_units_enum_def3990)
                id=self.match(self.input, ID, self.FOLLOW_ID_in_tagged_type_units_enum_def3994)
                self._state.following.append(self.FOLLOW_simple_numeric_type_in_tagged_type_units_enum_def3996)
                self.simple_numeric_type()

                self._state.following.pop()
                UNIT46=self.match(self.input, UNIT, self.FOLLOW_UNIT_in_tagged_type_units_enum_def3998)
                #action start
                     
                n.attrib['name'] = id.text
                n.attrib['field_units'] = UNIT46.text
                    
                #action end
                # jaus2jsidl.g:1114:5: ( value_set_def | declared_value_set_def | scaled_range_def )?
                alt140 = 4
                LA140 = self.input.LA(1)
                if LA140 == 38:
                    alt140 = 1
                elif LA140 == ID or LA140 == 50 or LA140 == 81:
                    alt140 = 2
                elif LA140 == 101:
                    alt140 = 3
                if alt140 == 1:
                    # jaus2jsidl.g:1114:7: value_set_def
                    pass 
                    self._state.following.append(self.FOLLOW_value_set_def_in_tagged_type_units_enum_def4012)
                    self.value_set_def()

                    self._state.following.pop()


                elif alt140 == 2:
                    # jaus2jsidl.g:1114:23: declared_value_set_def
                    pass 
                    self._state.following.append(self.FOLLOW_declared_value_set_def_in_tagged_type_units_enum_def4016)
                    self.declared_value_set_def()

                    self._state.following.pop()


                elif alt140 == 3:
                    # jaus2jsidl.g:1114:48: scaled_range_def
                    pass 
                    self._state.following.append(self.FOLLOW_scaled_range_def_in_tagged_type_units_enum_def4020)
                    self.scaled_range_def()

                    self._state.following.pop()



                #action start
                     
                p_comment = self.comment
                self.comment = ''
                    
                #action end
                self.match(self.input, 40, self.FOLLOW_40_in_tagged_type_units_enum_def4035)
                # jaus2jsidl.g:1119:9: ( ml_comment | ML_COMMENT )?
                alt141 = 3
                LA141_0 = self.input.LA(1)

                if (LA141_0 == SL_COMMENT) :
                    alt141 = 1
                elif (LA141_0 == ML_COMMENT) :
                    alt141 = 2
                if alt141 == 1:
                    # jaus2jsidl.g:1119:10: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_tagged_type_units_enum_def4038)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt141 == 2:
                    # jaus2jsidl.g:1119:23: ML_COMMENT
                    pass 
                    self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_tagged_type_units_enum_def4042)



                #action start
                     
                if self.comment:
                    comment = self.comment.replace('//',' ').strip()
                    n.attrib['interpretation'] = compress_ws(comment)
                # TODO: get ML_COMMENT
                self.comment = p_comment
                self.current_node = p
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "tagged_type_units_enum_def"


    # $ANTLR start "declared_value_set_def"
    # jaus2jsidl.g:1131:1: declared_value_set_def : scoped_id ID ';' ( ml_comment | ML_COMMENT )? ;
    def declared_value_set_def(self, ):

        try:
            try:
                # jaus2jsidl.g:1132:5: ( scoped_id ID ';' ( ml_comment | ML_COMMENT )? )
                # jaus2jsidl.g:1132:7: scoped_id ID ';' ( ml_comment | ML_COMMENT )?
                pass 
                self._state.following.append(self.FOLLOW_scoped_id_in_declared_value_set_def4068)
                self.scoped_id()

                self._state.following.pop()
                self.match(self.input, ID, self.FOLLOW_ID_in_declared_value_set_def4070)
                self.match(self.input, 40, self.FOLLOW_40_in_declared_value_set_def4072)
                #action start
                     
                n = etree.SubElement(self.current_node,'declared_value_set')
                p_comment = self.comment
                self_comment = ''
                    
                #action end
                # jaus2jsidl.g:1138:5: ( ml_comment | ML_COMMENT )?
                alt142 = 3
                LA142_0 = self.input.LA(1)

                if (LA142_0 == SL_COMMENT) :
                    alt142 = 1
                elif (LA142_0 == ML_COMMENT) :
                    alt142 = 2
                if alt142 == 1:
                    # jaus2jsidl.g:1138:7: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_declared_value_set_def4086)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt142 == 2:
                    # jaus2jsidl.g:1138:20: ML_COMMENT
                    pass 
                    self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_declared_value_set_def4090)



                #action start
                     
                if self.comment:
                    comment = self.comment.replace('//',' ').strip()
                    n.attrib['interpretation'] = compress_ws(comment)
                self.comment = p_comment
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "declared_value_set_def"


    # $ANTLR start "variable_format_field_type_def"
    # jaus2jsidl.g:1147:1: variable_format_field_type_def : ( ITEM_CARDINALITY )? 'variable_format_field' ID '{' ( ml_comment | mlc1= ML_COMMENT )? count_type= ( 'uint8' | 'uint16' | 'uint32' ) 'tag' ';' ( format_enum_def )+ '}' ( ( '=' INTLITERAL ';' ) | ';' )? ( ml_comment | mlc2= ML_COMMENT )? ;
    def variable_format_field_type_def(self, ):

        mlc1 = None
        count_type = None
        mlc2 = None
        ID47 = None
        ITEM_CARDINALITY48 = None
        INTLITERAL49 = None

        try:
            try:
                # jaus2jsidl.g:1148:6: ( ( ITEM_CARDINALITY )? 'variable_format_field' ID '{' ( ml_comment | mlc1= ML_COMMENT )? count_type= ( 'uint8' | 'uint16' | 'uint32' ) 'tag' ';' ( format_enum_def )+ '}' ( ( '=' INTLITERAL ';' ) | ';' )? ( ml_comment | mlc2= ML_COMMENT )? )
                # jaus2jsidl.g:1148:8: ( ITEM_CARDINALITY )? 'variable_format_field' ID '{' ( ml_comment | mlc1= ML_COMMENT )? count_type= ( 'uint8' | 'uint16' | 'uint32' ) 'tag' ';' ( format_enum_def )+ '}' ( ( '=' INTLITERAL ';' ) | ';' )? ( ml_comment | mlc2= ML_COMMENT )?
                pass 
                # jaus2jsidl.g:1148:8: ( ITEM_CARDINALITY )?
                alt143 = 2
                LA143_0 = self.input.LA(1)

                if (LA143_0 == ITEM_CARDINALITY) :
                    alt143 = 1
                if alt143 == 1:
                    # jaus2jsidl.g:1148:10: ITEM_CARDINALITY
                    pass 
                    ITEM_CARDINALITY48=self.match(self.input, ITEM_CARDINALITY, self.FOLLOW_ITEM_CARDINALITY_in_variable_format_field_type_def4120)



                self.match(self.input, 99, self.FOLLOW_99_in_variable_format_field_type_def4125)
                ID47=self.match(self.input, ID, self.FOLLOW_ID_in_variable_format_field_type_def4127)
                self.match(self.input, 38, self.FOLLOW_38_in_variable_format_field_type_def4129)
                # jaus2jsidl.g:1149:8: ( ml_comment | mlc1= ML_COMMENT )?
                alt144 = 3
                LA144_0 = self.input.LA(1)

                if (LA144_0 == SL_COMMENT) :
                    alt144 = 1
                elif (LA144_0 == ML_COMMENT) :
                    alt144 = 2
                if alt144 == 1:
                    # jaus2jsidl.g:1149:10: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_variable_format_field_type_def4140)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt144 == 2:
                    # jaus2jsidl.g:1149:23: mlc1= ML_COMMENT
                    pass 
                    mlc1=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_variable_format_field_type_def4147)



                count_type = self.input.LT(1)
                if (82 <= self.input.LA(1) <= 83) or self.input.LA(1) == 85:
                    self.input.consume()
                    self._state.errorRecovery = False

                else:
                    mse = MismatchedSetException(None, self.input)
                    raise mse


                self.match(self.input, 98, self.FOLLOW_98_in_variable_format_field_type_def4177)
                self.match(self.input, 40, self.FOLLOW_40_in_variable_format_field_type_def4179)
                #action start
                     
                p = self.current_node
                n = etree.SubElement(p,'variable_format_field')
                n.attrib['name'] = name = ID47.text
                self.type_map[name] = n
                if ITEM_CARDINALITY48:
                    if ITEM_CARDINALITY48.text == 'required':
                        n.attrib['optional'] = 'false'
                    else:
                        n.attrib['optional'] = 'true'
                        self.optional_count += 1
                else:
                        n.attrib['optional'] = 'false'
                n1 = etree.SubElement(n,'format_field')
                n2 = etree.SubElement(n, 'count_field')
                n2.attrib['field_type_unsigned'] = self.scalar_map[count_type.text]
                # No max/min for JSIDL at this time.
                p_comment = self.comment
                self.comment = ''
                self.current_node = n1
                    
                #action end
                # jaus2jsidl.g:1172:8: ( format_enum_def )+
                cnt145 = 0
                while True: #loop145
                    alt145 = 2
                    LA145_0 = self.input.LA(1)

                    if (LA145_0 == 98) :
                        alt145 = 1


                    if alt145 == 1:
                        # jaus2jsidl.g:1172:10: format_enum_def
                        pass 
                        self._state.following.append(self.FOLLOW_format_enum_def_in_variable_format_field_type_def4201)
                        self.format_enum_def()

                        self._state.following.pop()


                    else:
                        if cnt145 >= 1:
                            break #loop145

                        eee = EarlyExitException(145, self.input)
                        raise eee

                    cnt145 += 1


                self.match(self.input, 39, self.FOLLOW_39_in_variable_format_field_type_def4213)
                # jaus2jsidl.g:1173:12: ( ( '=' INTLITERAL ';' ) | ';' )?
                alt146 = 3
                LA146_0 = self.input.LA(1)

                if (LA146_0 == 34) :
                    alt146 = 1
                elif (LA146_0 == 40) :
                    alt146 = 2
                if alt146 == 1:
                    # jaus2jsidl.g:1173:13: ( '=' INTLITERAL ';' )
                    pass 
                    # jaus2jsidl.g:1173:13: ( '=' INTLITERAL ';' )
                    # jaus2jsidl.g:1173:15: '=' INTLITERAL ';'
                    pass 
                    self.match(self.input, 34, self.FOLLOW_34_in_variable_format_field_type_def4218)
                    INTLITERAL49=self.match(self.input, INTLITERAL, self.FOLLOW_INTLITERAL_in_variable_format_field_type_def4220)
                    self.match(self.input, 40, self.FOLLOW_40_in_variable_format_field_type_def4222)





                elif alt146 == 2:
                    # jaus2jsidl.g:1173:37: ';'
                    pass 
                    self.match(self.input, 40, self.FOLLOW_40_in_variable_format_field_type_def4227)



                # jaus2jsidl.g:1174:8: ( ml_comment | mlc2= ML_COMMENT )?
                alt147 = 3
                LA147_0 = self.input.LA(1)

                if (LA147_0 == SL_COMMENT) :
                    alt147 = 1
                elif (LA147_0 == ML_COMMENT) :
                    alt147 = 2
                if alt147 == 1:
                    # jaus2jsidl.g:1174:10: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_variable_format_field_type_def4240)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt147 == 2:
                    # jaus2jsidl.g:1174:23: mlc2= ML_COMMENT
                    pass 
                    mlc2=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_variable_format_field_type_def4247)



                #action start
                     
                if len(self.comment) > 0:
                    comment = self.comment.replace('//',' ').strip()
                    n.attrib['interpretation'] = compress_ws(comment)
                if mlc1:
                    s = mlc1.text[2:-2].strip()
                    n.attrib['interpretation'] += compress_ws(s)
                if mlc2:
                    s = mlc2.text[2:-2].strip()
                    n.attrib['interpretation'] += compress_ws(s)
                if INTLITERAL49 and self.jsidl_ns == "urn:jaus:jsidl:exp":
                    n.attrib['item_index'] = INTLITERAL49.text
                self.comment = p_comment
                self.current_node = p
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "variable_format_field_type_def"


    # $ANTLR start "format_enum_def"
    # jaus2jsidl.g:1192:1: format_enum_def : 'tag' (const_tag= INTLITERAL | tag= ID ) ':' (ff= FIELD_FORMAT | txt= STRINGLITERAL ) ';' ( ml_comment | ML_COMMENT )? ;
    def format_enum_def(self, ):

        const_tag = None
        tag = None
        ff = None
        txt = None

        try:
            try:
                # jaus2jsidl.g:1193:5: ( 'tag' (const_tag= INTLITERAL | tag= ID ) ':' (ff= FIELD_FORMAT | txt= STRINGLITERAL ) ';' ( ml_comment | ML_COMMENT )? )
                # jaus2jsidl.g:1194:5: 'tag' (const_tag= INTLITERAL | tag= ID ) ':' (ff= FIELD_FORMAT | txt= STRINGLITERAL ) ';' ( ml_comment | ML_COMMENT )?
                pass 
                self.match(self.input, 98, self.FOLLOW_98_in_format_enum_def4278)
                # jaus2jsidl.g:1194:11: (const_tag= INTLITERAL | tag= ID )
                alt148 = 2
                LA148_0 = self.input.LA(1)

                if (LA148_0 == INTLITERAL) :
                    alt148 = 1
                elif (LA148_0 == ID) :
                    alt148 = 2
                else:
                    nvae = NoViableAltException("", 148, 0, self.input)

                    raise nvae

                if alt148 == 1:
                    # jaus2jsidl.g:1194:12: const_tag= INTLITERAL
                    pass 
                    const_tag=self.match(self.input, INTLITERAL, self.FOLLOW_INTLITERAL_in_format_enum_def4284)


                elif alt148 == 2:
                    # jaus2jsidl.g:1194:36: tag= ID
                    pass 
                    tag=self.match(self.input, ID, self.FOLLOW_ID_in_format_enum_def4291)



                self.match(self.input, 77, self.FOLLOW_77_in_format_enum_def4295)
                # jaus2jsidl.g:1194:50: (ff= FIELD_FORMAT | txt= STRINGLITERAL )
                alt149 = 2
                LA149_0 = self.input.LA(1)

                if (LA149_0 == FIELD_FORMAT) :
                    alt149 = 1
                elif (LA149_0 == STRINGLITERAL) :
                    alt149 = 2
                else:
                    nvae = NoViableAltException("", 149, 0, self.input)

                    raise nvae

                if alt149 == 1:
                    # jaus2jsidl.g:1194:51: ff= FIELD_FORMAT
                    pass 
                    ff=self.match(self.input, FIELD_FORMAT, self.FOLLOW_FIELD_FORMAT_in_format_enum_def4301)


                elif alt149 == 2:
                    # jaus2jsidl.g:1194:70: txt= STRINGLITERAL
                    pass 
                    txt=self.match(self.input, STRINGLITERAL, self.FOLLOW_STRINGLITERAL_in_format_enum_def4308)



                self.match(self.input, 40, self.FOLLOW_40_in_format_enum_def4312)
                # jaus2jsidl.g:1195:5: ( ml_comment | ML_COMMENT )?
                alt150 = 3
                LA150_0 = self.input.LA(1)

                if (LA150_0 == SL_COMMENT) :
                    alt150 = 1
                elif (LA150_0 == ML_COMMENT) :
                    alt150 = 2
                if alt150 == 1:
                    # jaus2jsidl.g:1195:7: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_format_enum_def4320)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt150 == 2:
                    # jaus2jsidl.g:1195:20: ML_COMMENT
                    pass 
                    self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_format_enum_def4324)



                #action start
                     
                p = self.current_node
                n = etree.SubElement(p,'format_enum')
                if const_tag:
                    n.attrib['index'] =  const_tag.text
                elif tag:
                    n.attrib['index'] =  tag.text
                if ff:
                    n.attrib['field_format'] = ff.text
                else:
                    n.attrib['field_format'] = txt.text
                self.current_node = p
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "format_enum_def"


    # $ANTLR start "enum_type_def"
    # jaus2jsidl.g:1216:1: enum_type_def : 'enum' type= ( 'uint8' | 'uint16' | 'uint32' ) ID UNIT '{' ( ml_comment | ml1= ML_COMMENT )? ( value_spec )+ '}' ( ';' )? ( ml_comment | ml2= ML_COMMENT )? ;
    def enum_type_def(self, ):

        type = None
        ml1 = None
        ml2 = None
        ID50 = None
        UNIT51 = None

        try:
            try:
                # jaus2jsidl.g:1217:5: ( 'enum' type= ( 'uint8' | 'uint16' | 'uint32' ) ID UNIT '{' ( ml_comment | ml1= ML_COMMENT )? ( value_spec )+ '}' ( ';' )? ( ml_comment | ml2= ML_COMMENT )? )
                # jaus2jsidl.g:1218:9: 'enum' type= ( 'uint8' | 'uint16' | 'uint32' ) ID UNIT '{' ( ml_comment | ml1= ML_COMMENT )? ( value_spec )+ '}' ( ';' )? ( ml_comment | ml2= ML_COMMENT )?
                pass 
                self.match(self.input, 93, self.FOLLOW_93_in_enum_type_def4364)
                type = self.input.LT(1)
                if (82 <= self.input.LA(1) <= 83) or self.input.LA(1) == 85:
                    self.input.consume()
                    self._state.errorRecovery = False

                else:
                    mse = MismatchedSetException(None, self.input)
                    raise mse


                ID50=self.match(self.input, ID, self.FOLLOW_ID_in_enum_type_def4382)
                UNIT51=self.match(self.input, UNIT, self.FOLLOW_UNIT_in_enum_type_def4384)
                #action start
                         
                p = self.current_node
                if self.jsidl_ns == "urn:jaus:jsidl:1.0":
                   n = etree.SubElement(p,'fixed_field')
                   n.attrib['field_type'] = self.scalar_map[type.text]
                   n.attrib['optional'] = 'false' # required by JSIDL even for typedefs
                   nn = etree.SubElement(n,'value_set')
                   nn.attrib['offset_to_lower_limit'] = 'false'
                else:
                   n = etree.SubElement(p,'enum')
                   n.attrib['field_type_unsigned'] = self.scalar_map[type.text]
                   nn = n  # No value_set wrapper for 'enum'
                name = ID50.text
                n.attrib['name'] = name
                self.type_map[name] = n
                n.attrib['field_units'] = UNIT51.text.replace('_',' ')
                self.current_node = nn
                debug(4,"Processing enum_type_def %s\n"%name)
                        
                #action end
                self.match(self.input, 38, self.FOLLOW_38_in_enum_type_def4405)
                # jaus2jsidl.g:1238:13: ( ml_comment | ml1= ML_COMMENT )?
                alt151 = 3
                LA151_0 = self.input.LA(1)

                if (LA151_0 == SL_COMMENT) :
                    alt151 = 1
                elif (LA151_0 == ML_COMMENT) :
                    alt151 = 2
                if alt151 == 1:
                    # jaus2jsidl.g:1238:15: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_enum_type_def4409)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt151 == 2:
                    # jaus2jsidl.g:1238:28: ml1= ML_COMMENT
                    pass 
                    ml1=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_enum_type_def4415)



                # jaus2jsidl.g:1239:11: ( value_spec )+
                cnt152 = 0
                while True: #loop152
                    alt152 = 2
                    LA152_0 = self.input.LA(1)

                    if (LA152_0 == ID) :
                        alt152 = 1


                    if alt152 == 1:
                        # jaus2jsidl.g:1239:11: value_spec
                        pass 
                        self._state.following.append(self.FOLLOW_value_spec_in_enum_type_def4429)
                        self.value_spec()

                        self._state.following.pop()


                    else:
                        if cnt152 >= 1:
                            break #loop152

                        eee = EarlyExitException(152, self.input)
                        raise eee

                    cnt152 += 1


                self.match(self.input, 39, self.FOLLOW_39_in_enum_type_def4440)
                # jaus2jsidl.g:1240:13: ( ';' )?
                alt153 = 2
                LA153_0 = self.input.LA(1)

                if (LA153_0 == 40) :
                    alt153 = 1
                if alt153 == 1:
                    # jaus2jsidl.g:1240:13: ';'
                    pass 
                    self.match(self.input, 40, self.FOLLOW_40_in_enum_type_def4442)



                # jaus2jsidl.g:1240:18: ( ml_comment | ml2= ML_COMMENT )?
                alt154 = 3
                LA154_0 = self.input.LA(1)

                if (LA154_0 == SL_COMMENT) :
                    alt154 = 1
                elif (LA154_0 == ML_COMMENT) :
                    alt154 = 2
                if alt154 == 1:
                    # jaus2jsidl.g:1240:20: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_enum_type_def4447)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt154 == 2:
                    # jaus2jsidl.g:1240:33: ml2= ML_COMMENT
                    pass 
                    ml2=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_enum_type_def4453)



                #action start
                       
                if ml2:
                    s=ml2.text[2:-2].strip()
                    n.attrib['interpretation'] = compress_ws(s)
                self.current_node = p
                        
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "enum_type_def"


    # $ANTLR start "value_set_def"
    # jaus2jsidl.g:1250:1: value_set_def : '{' ( value_spec )* '}' ( ML_COMMENT | ml_comment )? ;
    def value_set_def(self, ):

        ML_COMMENT52 = None

        try:
            try:
                # jaus2jsidl.g:1251:5: ( '{' ( value_spec )* '}' ( ML_COMMENT | ml_comment )? )
                # jaus2jsidl.g:1252:9: '{' ( value_spec )* '}' ( ML_COMMENT | ml_comment )?
                pass 
                #action start
                         
                debug(7, "about to parse value_set_def\n")
                n = etree.SubElement(self.current_node, 'value_set')
                n.attrib['offset_to_lower_limit'] = 'false'
                p = self.current_node
                self.current_node = n
                        
                #action end
                self.match(self.input, 38, self.FOLLOW_38_in_value_set_def4495)
                # jaus2jsidl.g:1260:5: ( value_spec )*
                while True: #loop155
                    alt155 = 2
                    LA155_0 = self.input.LA(1)

                    if (LA155_0 == ID) :
                        alt155 = 1


                    if alt155 == 1:
                        # jaus2jsidl.g:1260:5: value_spec
                        pass 
                        self._state.following.append(self.FOLLOW_value_spec_in_value_set_def4501)
                        self.value_spec()

                        self._state.following.pop()


                    else:
                        break #loop155


                self.match(self.input, 39, self.FOLLOW_39_in_value_set_def4508)
                # jaus2jsidl.g:1261:9: ( ML_COMMENT | ml_comment )?
                alt156 = 3
                LA156_0 = self.input.LA(1)

                if (LA156_0 == ML_COMMENT) :
                    alt156 = 1
                elif (LA156_0 == SL_COMMENT) :
                    alt156 = 2
                if alt156 == 1:
                    # jaus2jsidl.g:1261:11: ML_COMMENT
                    pass 
                    ML_COMMENT52=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_value_set_def4512)


                elif alt156 == 2:
                    # jaus2jsidl.g:1261:24: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_value_set_def4516)
                    self.ml_comment()

                    self._state.following.pop()



                #action start
                     
                if ML_COMMENT52:
                    s=ML_COMMENT52.text[2:-2].strip()
                    n.attrib['interpretation'] = compress_ws(s)
                self.current_node = p
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "value_set_def"


    # $ANTLR start "bitfield_type_def"
    # jaus2jsidl.g:1271:1: bitfield_type_def : ( ITEM_CARDINALITY )? 'bit_field' type= ( 'uint8' | 'uint16' | 'uint32' | 'uint64' ) ID ( ml_comment | mlc1= ML_COMMENT )? '{' ( sub_field )* '}' ( ( '=' INTLITERAL ';' ) | ';' )? ( ml_comment | mlc2= ML_COMMENT )? ;
    def bitfield_type_def(self, ):

        type = None
        mlc1 = None
        mlc2 = None
        ID53 = None
        ITEM_CARDINALITY54 = None
        INTLITERAL55 = None

        try:
            try:
                # jaus2jsidl.g:1271:18: ( ( ITEM_CARDINALITY )? 'bit_field' type= ( 'uint8' | 'uint16' | 'uint32' | 'uint64' ) ID ( ml_comment | mlc1= ML_COMMENT )? '{' ( sub_field )* '}' ( ( '=' INTLITERAL ';' ) | ';' )? ( ml_comment | mlc2= ML_COMMENT )? )
                # jaus2jsidl.g:1272:5: ( ITEM_CARDINALITY )? 'bit_field' type= ( 'uint8' | 'uint16' | 'uint32' | 'uint64' ) ID ( ml_comment | mlc1= ML_COMMENT )? '{' ( sub_field )* '}' ( ( '=' INTLITERAL ';' ) | ';' )? ( ml_comment | mlc2= ML_COMMENT )?
                pass 
                # jaus2jsidl.g:1272:5: ( ITEM_CARDINALITY )?
                alt157 = 2
                LA157_0 = self.input.LA(1)

                if (LA157_0 == ITEM_CARDINALITY) :
                    alt157 = 1
                if alt157 == 1:
                    # jaus2jsidl.g:1272:5: ITEM_CARDINALITY
                    pass 
                    ITEM_CARDINALITY54=self.match(self.input, ITEM_CARDINALITY, self.FOLLOW_ITEM_CARDINALITY_in_bitfield_type_def4542)



                self.match(self.input, 100, self.FOLLOW_100_in_bitfield_type_def4545)
                type = self.input.LT(1)
                if (82 <= self.input.LA(1) <= 83) or (85 <= self.input.LA(1) <= 86):
                    self.input.consume()
                    self._state.errorRecovery = False

                else:
                    mse = MismatchedSetException(None, self.input)
                    raise mse


                ID53=self.match(self.input, ID, self.FOLLOW_ID_in_bitfield_type_def4571)
                #action start
                         
                n = etree.SubElement(self.current_node,'bit_field')
                n.attrib['name'] = name = ID53.text
                n.attrib['field_type_unsigned'] = self.scalar_map[type.text]
                if ITEM_CARDINALITY54:
                    if ITEM_CARDINALITY54.text == 'required':
                        n.attrib['optional'] = 'false'
                    else:
                        n.attrib['optional'] = 'true'
                        self.optional_count += 1
                else:
                        n.attrib['optional'] = 'false'  # in JSIDL required in all contexts.
                p = self.current_node
                self.type_map[name] = n
                self.current_node = n
                        
                #action end
                # jaus2jsidl.g:1290:10: ( ml_comment | mlc1= ML_COMMENT )?
                alt158 = 3
                LA158_0 = self.input.LA(1)

                if (LA158_0 == SL_COMMENT) :
                    alt158 = 1
                elif (LA158_0 == ML_COMMENT) :
                    alt158 = 2
                if alt158 == 1:
                    # jaus2jsidl.g:1290:11: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_bitfield_type_def4593)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt158 == 2:
                    # jaus2jsidl.g:1290:24: mlc1= ML_COMMENT
                    pass 
                    mlc1=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_bitfield_type_def4599)



                self.match(self.input, 38, self.FOLLOW_38_in_bitfield_type_def4612)
                # jaus2jsidl.g:1293:11: ( sub_field )*
                while True: #loop159
                    alt159 = 2
                    LA159_0 = self.input.LA(1)

                    if (LA159_0 == ID) :
                        alt159 = 1


                    if alt159 == 1:
                        # jaus2jsidl.g:1293:11: sub_field
                        pass 
                        self._state.following.append(self.FOLLOW_sub_field_in_bitfield_type_def4624)
                        self.sub_field()

                        self._state.following.pop()


                    else:
                        break #loop159


                self.match(self.input, 39, self.FOLLOW_39_in_bitfield_type_def4636)
                # jaus2jsidl.g:1294:14: ( ( '=' INTLITERAL ';' ) | ';' )?
                alt160 = 3
                LA160_0 = self.input.LA(1)

                if (LA160_0 == 34) :
                    alt160 = 1
                elif (LA160_0 == 40) :
                    alt160 = 2
                if alt160 == 1:
                    # jaus2jsidl.g:1294:16: ( '=' INTLITERAL ';' )
                    pass 
                    # jaus2jsidl.g:1294:16: ( '=' INTLITERAL ';' )
                    # jaus2jsidl.g:1294:18: '=' INTLITERAL ';'
                    pass 
                    self.match(self.input, 34, self.FOLLOW_34_in_bitfield_type_def4642)
                    INTLITERAL55=self.match(self.input, INTLITERAL, self.FOLLOW_INTLITERAL_in_bitfield_type_def4644)
                    self.match(self.input, 40, self.FOLLOW_40_in_bitfield_type_def4646)





                elif alt160 == 2:
                    # jaus2jsidl.g:1294:41: ';'
                    pass 
                    self.match(self.input, 40, self.FOLLOW_40_in_bitfield_type_def4652)



                # jaus2jsidl.g:1295:10: ( ml_comment | mlc2= ML_COMMENT )?
                alt161 = 3
                LA161_0 = self.input.LA(1)

                if (LA161_0 == SL_COMMENT) :
                    alt161 = 1
                elif (LA161_0 == ML_COMMENT) :
                    alt161 = 2
                if alt161 == 1:
                    # jaus2jsidl.g:1295:11: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_bitfield_type_def4667)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt161 == 2:
                    # jaus2jsidl.g:1295:24: mlc2= ML_COMMENT
                    pass 
                    mlc2=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_bitfield_type_def4673)



                #action start
                         
                if mlc1:
                    if not 'interpretation' in n.attrib:
                        n.attrib['interpretation'] = ''
                    s=mlc1.text[2:-2].strip()
                    n.attrib['interpretation'] += compress_ws(s)
                if mlc2:
                    if not 'interpretation' in n.attrib:
                        n.attrib['interpretation'] = ''
                    s=mlc2.text[2:-2].strip()
                    n.attrib['interpretation'] += compress_ws(s)
                if INTLITERAL55 and self.jsidl_ns == "urn:jaus:jsidl:exp":
                    n.attrib['item_index'] = INTLITERAL55.text
                self.current_node = p
                        
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "bitfield_type_def"


    # $ANTLR start "value_range_set"
    # jaus2jsidl.g:1314:1: value_range_set : value_range ;
    def value_range_set(self, ):

        try:
            try:
                # jaus2jsidl.g:1315:5: ( value_range )
                # jaus2jsidl.g:1316:5: value_range
                pass 
                #action start
                     
                n = etree.SubElement(self.current_node, 'value_set')
                n.attrib['offset_to_lower_limit'] = 'false'
                p = self.current_node
                self.current_node = n
                    
                #action end
                self._state.following.append(self.FOLLOW_value_range_in_value_range_set4713)
                self.value_range()

                self._state.following.pop()
                #action start
                     
                self.current_node = p
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "value_range_set"


    # $ANTLR start "value_range"
    # jaus2jsidl.g:1329:1: value_range : v5= ( ( '(' | '[' ) (v1= numeric_literal | v2= scoped_id ) ',' (v3= numeric_literal | v4= scoped_id ) ( ')' | ']' ) ) ;
    def value_range(self, ):

        v5 = None
        v1 = None

        v2 = None

        v3 = None

        v4 = None


        try:
            try:
                # jaus2jsidl.g:1330:5: (v5= ( ( '(' | '[' ) (v1= numeric_literal | v2= scoped_id ) ',' (v3= numeric_literal | v4= scoped_id ) ( ')' | ']' ) ) )
                # jaus2jsidl.g:1331:9: v5= ( ( '(' | '[' ) (v1= numeric_literal | v2= scoped_id ) ',' (v3= numeric_literal | v4= scoped_id ) ( ')' | ']' ) )
                pass 
                # jaus2jsidl.g:1331:14: ( ( '(' | '[' ) (v1= numeric_literal | v2= scoped_id ) ',' (v3= numeric_literal | v4= scoped_id ) ( ')' | ']' ) )
                # jaus2jsidl.g:1331:15: ( '(' | '[' ) (v1= numeric_literal | v2= scoped_id ) ',' (v3= numeric_literal | v4= scoped_id ) ( ')' | ']' )
                pass 
                # jaus2jsidl.g:1331:15: ( '(' | '[' )
                alt162 = 2
                LA162_0 = self.input.LA(1)

                if (LA162_0 == 32) :
                    alt162 = 1
                elif (LA162_0 == 60) :
                    alt162 = 2
                else:
                    nvae = NoViableAltException("", 162, 0, self.input)

                    raise nvae

                if alt162 == 1:
                    # jaus2jsidl.g:1331:17: '('
                    pass 
                    self.match(self.input, 32, self.FOLLOW_32_in_value_range4752)
                    #action start
                    lower_limit_type = 'exclusive' 
                    #action end


                elif alt162 == 2:
                    # jaus2jsidl.g:1331:58: '['
                    pass 
                    self.match(self.input, 60, self.FOLLOW_60_in_value_range4758)
                    #action start
                    lower_limit_type = 'inclusive' 
                    #action end



                # jaus2jsidl.g:1332:9: (v1= numeric_literal | v2= scoped_id )
                alt163 = 2
                LA163_0 = self.input.LA(1)

                if ((INTLITERAL <= LA163_0 <= DOUBLELITERAL) or LA163_0 == 72) :
                    alt163 = 1
                elif (LA163_0 == ID or LA163_0 == 50 or LA163_0 == 81) :
                    alt163 = 2
                else:
                    nvae = NoViableAltException("", 163, 0, self.input)

                    raise nvae

                if alt163 == 1:
                    # jaus2jsidl.g:1332:10: v1= numeric_literal
                    pass 
                    self._state.following.append(self.FOLLOW_numeric_literal_in_value_range4775)
                    v1 = self.numeric_literal()

                    self._state.following.pop()
                    #action start
                    lower_limit = ((v1 is not None) and [self.input.toString(v1.start,v1.stop)] or [None])[0] 
                    #action end


                elif alt163 == 2:
                    # jaus2jsidl.g:1333:12: v2= scoped_id
                    pass 
                    self._state.following.append(self.FOLLOW_scoped_id_in_value_range4792)
                    v2 = self.scoped_id()

                    self._state.following.pop()
                    #action start
                    lower_limit = ((v2 is not None) and [self.input.toString(v2.start,v2.stop)] or [None])[0] 
                    #action end



                self.match(self.input, 35, self.FOLLOW_35_in_value_range4806)
                # jaus2jsidl.g:1335:9: (v3= numeric_literal | v4= scoped_id )
                alt164 = 2
                LA164_0 = self.input.LA(1)

                if ((INTLITERAL <= LA164_0 <= DOUBLELITERAL) or LA164_0 == 72) :
                    alt164 = 1
                elif (LA164_0 == ID or LA164_0 == 50 or LA164_0 == 81) :
                    alt164 = 2
                else:
                    nvae = NoViableAltException("", 164, 0, self.input)

                    raise nvae

                if alt164 == 1:
                    # jaus2jsidl.g:1335:10: v3= numeric_literal
                    pass 
                    self._state.following.append(self.FOLLOW_numeric_literal_in_value_range4819)
                    v3 = self.numeric_literal()

                    self._state.following.pop()
                    #action start
                    upper_limit = ((v3 is not None) and [self.input.toString(v3.start,v3.stop)] or [None])[0] 
                    #action end


                elif alt164 == 2:
                    # jaus2jsidl.g:1336:12: v4= scoped_id
                    pass 
                    self._state.following.append(self.FOLLOW_scoped_id_in_value_range4836)
                    v4 = self.scoped_id()

                    self._state.following.pop()
                    #action start
                    upper_limit = ((v4 is not None) and [self.input.toString(v4.start,v4.stop)] or [None])[0] 
                    #action end



                # jaus2jsidl.g:1337:9: ( ')' | ']' )
                alt165 = 2
                LA165_0 = self.input.LA(1)

                if (LA165_0 == 37) :
                    alt165 = 1
                elif (LA165_0 == 61) :
                    alt165 = 2
                else:
                    nvae = NoViableAltException("", 165, 0, self.input)

                    raise nvae

                if alt165 == 1:
                    # jaus2jsidl.g:1337:11: ')'
                    pass 
                    self.match(self.input, 37, self.FOLLOW_37_in_value_range4852)
                    #action start
                    upper_limit_type = 'exclusive' 
                    #action end


                elif alt165 == 2:
                    # jaus2jsidl.g:1337:52: ']'
                    pass 
                    self.match(self.input, 61, self.FOLLOW_61_in_value_range4858)
                    #action start
                    upper_limit_type = 'inclusive' 
                    #action end






                #action start
                         
                n = etree.SubElement(self.current_node, 'value_range')
                n.attrib['lower_limit'] =  lower_limit
                n.attrib['lower_limit_type'] = lower_limit_type
                n.attrib['upper_limit'] =  upper_limit
                n.attrib['upper_limit_type'] = upper_limit_type
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "value_range"


    # $ANTLR start "value_spec"
    # jaus2jsidl.g:1347:1: value_spec : ID '=' ( INTLITERAL | value_range ) ';' ( ml_comment | ML_COMMENT )? ;
    def value_spec(self, ):

        ID56 = None
        INTLITERAL57 = None
        ML_COMMENT58 = None

        try:
            try:
                # jaus2jsidl.g:1348:5: ( ID '=' ( INTLITERAL | value_range ) ';' ( ml_comment | ML_COMMENT )? )
                # jaus2jsidl.g:1349:5: ID '=' ( INTLITERAL | value_range ) ';' ( ml_comment | ML_COMMENT )?
                pass 
                ID56=self.match(self.input, ID, self.FOLLOW_ID_in_value_spec4894)
                self.match(self.input, 34, self.FOLLOW_34_in_value_spec4896)
                #action start
                     
                name = ID56.text
                    
                #action end
                # jaus2jsidl.g:1353:5: ( INTLITERAL | value_range )
                alt166 = 2
                LA166_0 = self.input.LA(1)

                if (LA166_0 == INTLITERAL) :
                    alt166 = 1
                elif (LA166_0 == 32 or LA166_0 == 60) :
                    alt166 = 2
                else:
                    nvae = NoViableAltException("", 166, 0, self.input)

                    raise nvae

                if alt166 == 1:
                    # jaus2jsidl.g:1353:7: INTLITERAL
                    pass 
                    INTLITERAL57=self.match(self.input, INTLITERAL, self.FOLLOW_INTLITERAL_in_value_spec4910)


                elif alt166 == 2:
                    # jaus2jsidl.g:1353:20: value_range
                    pass 
                    self._state.following.append(self.FOLLOW_value_range_in_value_spec4914)
                    self.value_range()

                    self._state.following.pop()



                #action start
                     
                # value_range does not use the name field.
                if INTLITERAL57:
                    n = etree.SubElement(self.current_node, 'value_enum')
                    value = INTLITERAL57.text
                    n.attrib['enum_const'] =  name
                    n.attrib['enum_index'] = value
                        
                #action end
                self.match(self.input, 40, self.FOLLOW_40_in_value_spec4928)
                # jaus2jsidl.g:1362:9: ( ml_comment | ML_COMMENT )?
                alt167 = 3
                LA167_0 = self.input.LA(1)

                if (LA167_0 == SL_COMMENT) :
                    alt167 = 1
                elif (LA167_0 == ML_COMMENT) :
                    alt167 = 2
                if alt167 == 1:
                    # jaus2jsidl.g:1362:10: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_value_spec4931)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt167 == 2:
                    # jaus2jsidl.g:1362:23: ML_COMMENT
                    pass 
                    ML_COMMENT58=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_value_spec4935)



                #action start
                     
                if ML_COMMENT58:
                    s=ML_COMMENT58.text[2:-2].strip()
                    n.attrib['interpretation'] = compress_ws(s)
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "value_spec"


    # $ANTLR start "scaled_range_def"
    # jaus2jsidl.g:1371:1: scaled_range_def : '<' (v1= numeric_literal | v2= scoped_id ) ',' (v3= numeric_literal | v4= scoped_id ) '>' ;
    def scaled_range_def(self, ):

        v1 = None

        v2 = None

        v3 = None

        v4 = None


        try:
            try:
                # jaus2jsidl.g:1372:5: ( '<' (v1= numeric_literal | v2= scoped_id ) ',' (v3= numeric_literal | v4= scoped_id ) '>' )
                # jaus2jsidl.g:1373:9: '<' (v1= numeric_literal | v2= scoped_id ) ',' (v3= numeric_literal | v4= scoped_id ) '>'
                pass 
                self.match(self.input, 101, self.FOLLOW_101_in_scaled_range_def4970)
                # jaus2jsidl.g:1374:9: (v1= numeric_literal | v2= scoped_id )
                alt168 = 2
                LA168_0 = self.input.LA(1)

                if ((INTLITERAL <= LA168_0 <= DOUBLELITERAL) or LA168_0 == 72) :
                    alt168 = 1
                elif (LA168_0 == ID or LA168_0 == 50 or LA168_0 == 81) :
                    alt168 = 2
                else:
                    nvae = NoViableAltException("", 168, 0, self.input)

                    raise nvae

                if alt168 == 1:
                    # jaus2jsidl.g:1374:10: v1= numeric_literal
                    pass 
                    self._state.following.append(self.FOLLOW_numeric_literal_in_scaled_range_def4983)
                    v1 = self.numeric_literal()

                    self._state.following.pop()
                    #action start
                    rll = ((v1 is not None) and [self.input.toString(v1.start,v1.stop)] or [None])[0] 
                    #action end


                elif alt168 == 2:
                    # jaus2jsidl.g:1375:12: v2= scoped_id
                    pass 
                    self._state.following.append(self.FOLLOW_scoped_id_in_scaled_range_def5000)
                    v2 = self.scoped_id()

                    self._state.following.pop()
                    #action start
                    rll = ((v2 is not None) and [self.input.toString(v2.start,v2.stop)] or [None])[0] 
                    #action end



                self.match(self.input, 35, self.FOLLOW_35_in_scaled_range_def5014)
                # jaus2jsidl.g:1377:9: (v3= numeric_literal | v4= scoped_id )
                alt169 = 2
                LA169_0 = self.input.LA(1)

                if ((INTLITERAL <= LA169_0 <= DOUBLELITERAL) or LA169_0 == 72) :
                    alt169 = 1
                elif (LA169_0 == ID or LA169_0 == 50 or LA169_0 == 81) :
                    alt169 = 2
                else:
                    nvae = NoViableAltException("", 169, 0, self.input)

                    raise nvae

                if alt169 == 1:
                    # jaus2jsidl.g:1377:10: v3= numeric_literal
                    pass 
                    self._state.following.append(self.FOLLOW_numeric_literal_in_scaled_range_def5027)
                    v3 = self.numeric_literal()

                    self._state.following.pop()
                    #action start
                    rul = ((v3 is not None) and [self.input.toString(v3.start,v3.stop)] or [None])[0] 
                    #action end


                elif alt169 == 2:
                    # jaus2jsidl.g:1378:12: v4= scoped_id
                    pass 
                    self._state.following.append(self.FOLLOW_scoped_id_in_scaled_range_def5044)
                    v4 = self.scoped_id()

                    self._state.following.pop()
                    #action start
                    rul = ((v4 is not None) and [self.input.toString(v4.start,v4.stop)] or [None])[0] 
                    #action end



                self.match(self.input, 102, self.FOLLOW_102_in_scaled_range_def5058)
                #action start
                         
                # debug(4,"<%s, %s>\n"%(rll, rul))
                n = etree.SubElement(self.current_node,'scale_range')
                n.attrib['real_lower_limit'] = rll
                n.attrib['real_upper_limit'] = rul
                n.attrib['integer_function'] = 'round'

                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "scaled_range_def"


    # $ANTLR start "sub_field"
    # jaus2jsidl.g:1389:1: sub_field : v1= ID '[' v2= INTLITERAL ':' v3= INTLITERAL ']' ( value_range_set | v4= ID )? UNIT ';' v5= ( ( ml_comment | ML_COMMENT )? ) ;
    def sub_field(self, ):

        v1 = None
        v2 = None
        v3 = None
        v4 = None
        v5 = None

        try:
            try:
                # jaus2jsidl.g:1390:5: (v1= ID '[' v2= INTLITERAL ':' v3= INTLITERAL ']' ( value_range_set | v4= ID )? UNIT ';' v5= ( ( ml_comment | ML_COMMENT )? ) )
                # jaus2jsidl.g:1391:5: v1= ID '[' v2= INTLITERAL ':' v3= INTLITERAL ']' ( value_range_set | v4= ID )? UNIT ';' v5= ( ( ml_comment | ML_COMMENT )? )
                pass 
                v1=self.match(self.input, ID, self.FOLLOW_ID_in_sub_field5091)
                self.match(self.input, 60, self.FOLLOW_60_in_sub_field5093)
                v2=self.match(self.input, INTLITERAL, self.FOLLOW_INTLITERAL_in_sub_field5097)
                self.match(self.input, 77, self.FOLLOW_77_in_sub_field5099)
                v3=self.match(self.input, INTLITERAL, self.FOLLOW_INTLITERAL_in_sub_field5103)
                self.match(self.input, 61, self.FOLLOW_61_in_sub_field5105)
                #action start
                         
                p = self.current_node
                n = etree.SubElement(p,'sub_field')
                name = v1.text
                n.attrib['name'] = name
                m = etree.SubElement(n,'bit_range')
                m.attrib['from_index'] = v2.text
                m.attrib['to_index'] = v3.text
                self.current_node = n
                        
                #action end
                # jaus2jsidl.g:1402:5: ( value_range_set | v4= ID )?
                alt170 = 3
                LA170_0 = self.input.LA(1)

                if (LA170_0 == 32 or LA170_0 == 60) :
                    alt170 = 1
                elif (LA170_0 == ID) :
                    alt170 = 2
                if alt170 == 1:
                    # jaus2jsidl.g:1402:6: value_range_set
                    pass 
                    self._state.following.append(self.FOLLOW_value_range_set_in_sub_field5122)
                    self.value_range_set()

                    self._state.following.pop()


                elif alt170 == 2:
                    # jaus2jsidl.g:1402:24: v4= ID
                    pass 
                    v4=self.match(self.input, ID, self.FOLLOW_ID_in_sub_field5128)



                self.match(self.input, UNIT, self.FOLLOW_UNIT_in_sub_field5137)
                self.match(self.input, 40, self.FOLLOW_40_in_sub_field5139)
                # jaus2jsidl.g:1403:17: ( ( ml_comment | ML_COMMENT )? )
                # jaus2jsidl.g:1403:18: ( ml_comment | ML_COMMENT )?
                pass 
                # jaus2jsidl.g:1403:18: ( ml_comment | ML_COMMENT )?
                alt171 = 3
                LA171_0 = self.input.LA(1)

                if (LA171_0 == SL_COMMENT) :
                    alt171 = 1
                elif (LA171_0 == ML_COMMENT) :
                    alt171 = 2
                if alt171 == 1:
                    # jaus2jsidl.g:1403:19: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_sub_field5145)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt171 == 2:
                    # jaus2jsidl.g:1403:32: ML_COMMENT
                    pass 
                    self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_sub_field5149)






                #action start
                         
                if v4:
                    # JSIDL cannot reference value_sets, so copy what we have seen in the type_set.
                    n.remove(v)
                    n.append(deepcopy(self.value_set_map[v4.text]))
                if v5:
                    comment = v5.text.replace('//',' ').strip()
                    n.attrib['interpretation'] = compress_ws(comment)
                self.current_node = p
                        
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "sub_field"


    # $ANTLR start "container_type_def"
    # jaus2jsidl.g:1417:1: container_type_def : ( list_type_def | variant_type_def | sequence_type_def | record_type_def );
    def container_type_def(self, ):

        try:
            try:
                # jaus2jsidl.g:1418:5: ( list_type_def | variant_type_def | sequence_type_def | record_type_def )
                alt172 = 4
                LA172 = self.input.LA(1)
                if LA172 == ITEM_CARDINALITY:
                    LA172 = self.input.LA(2)
                    if LA172 == 103:
                        alt172 = 1
                    elif LA172 == 105:
                        alt172 = 2
                    elif LA172 == 107:
                        alt172 = 3
                    elif LA172 == 108:
                        alt172 = 4
                    else:
                        nvae = NoViableAltException("", 172, 1, self.input)

                        raise nvae

                elif LA172 == 103:
                    alt172 = 1
                elif LA172 == 105:
                    alt172 = 2
                elif LA172 == 107:
                    alt172 = 3
                elif LA172 == 108:
                    alt172 = 4
                else:
                    nvae = NoViableAltException("", 172, 0, self.input)

                    raise nvae

                if alt172 == 1:
                    # jaus2jsidl.g:1419:7: list_type_def
                    pass 
                    self._state.following.append(self.FOLLOW_list_type_def_in_container_type_def5186)
                    self.list_type_def()

                    self._state.following.pop()


                elif alt172 == 2:
                    # jaus2jsidl.g:1420:7: variant_type_def
                    pass 
                    self._state.following.append(self.FOLLOW_variant_type_def_in_container_type_def5194)
                    self.variant_type_def()

                    self._state.following.pop()


                elif alt172 == 3:
                    # jaus2jsidl.g:1421:7: sequence_type_def
                    pass 
                    self._state.following.append(self.FOLLOW_sequence_type_def_in_container_type_def5202)
                    self.sequence_type_def()

                    self._state.following.pop()


                elif alt172 == 4:
                    # jaus2jsidl.g:1422:7: record_type_def
                    pass 
                    self._state.following.append(self.FOLLOW_record_type_def_in_container_type_def5210)
                    self.record_type_def()

                    self._state.following.pop()
                    #action start
                             
                    self.item_index = None
                        
                    #action end



            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "container_type_def"


    # $ANTLR start "indexed_container_type_def"
    # jaus2jsidl.g:1429:1: indexed_container_type_def : ( list_type_def | variant_type_def | sequence_type_def | record_type_def ) '=' INTLITERAL ';' ( SL_COMMENT )? ;
    def indexed_container_type_def(self, ):

        INTLITERAL59 = None

        try:
            try:
                # jaus2jsidl.g:1430:5: ( ( list_type_def | variant_type_def | sequence_type_def | record_type_def ) '=' INTLITERAL ';' ( SL_COMMENT )? )
                # jaus2jsidl.g:1431:8: ( list_type_def | variant_type_def | sequence_type_def | record_type_def ) '=' INTLITERAL ';' ( SL_COMMENT )?
                pass 
                # jaus2jsidl.g:1431:8: ( list_type_def | variant_type_def | sequence_type_def | record_type_def )
                alt173 = 4
                LA173 = self.input.LA(1)
                if LA173 == ITEM_CARDINALITY:
                    LA173 = self.input.LA(2)
                    if LA173 == 103:
                        alt173 = 1
                    elif LA173 == 107:
                        alt173 = 3
                    elif LA173 == 108:
                        alt173 = 4
                    elif LA173 == 105:
                        alt173 = 2
                    else:
                        nvae = NoViableAltException("", 173, 1, self.input)

                        raise nvae

                elif LA173 == 103:
                    alt173 = 1
                elif LA173 == 105:
                    alt173 = 2
                elif LA173 == 107:
                    alt173 = 3
                elif LA173 == 108:
                    alt173 = 4
                else:
                    nvae = NoViableAltException("", 173, 0, self.input)

                    raise nvae

                if alt173 == 1:
                    # jaus2jsidl.g:1431:9: list_type_def
                    pass 
                    self._state.following.append(self.FOLLOW_list_type_def_in_indexed_container_type_def5246)
                    self.list_type_def()

                    self._state.following.pop()


                elif alt173 == 2:
                    # jaus2jsidl.g:1432:11: variant_type_def
                    pass 
                    self._state.following.append(self.FOLLOW_variant_type_def_in_indexed_container_type_def5258)
                    self.variant_type_def()

                    self._state.following.pop()


                elif alt173 == 3:
                    # jaus2jsidl.g:1433:11: sequence_type_def
                    pass 
                    self._state.following.append(self.FOLLOW_sequence_type_def_in_indexed_container_type_def5270)
                    self.sequence_type_def()

                    self._state.following.pop()


                elif alt173 == 4:
                    # jaus2jsidl.g:1434:11: record_type_def
                    pass 
                    self._state.following.append(self.FOLLOW_record_type_def_in_indexed_container_type_def5282)
                    self.record_type_def()

                    self._state.following.pop()



                self.match(self.input, 34, self.FOLLOW_34_in_indexed_container_type_def5285)
                INTLITERAL59=self.match(self.input, INTLITERAL, self.FOLLOW_INTLITERAL_in_indexed_container_type_def5287)
                self.match(self.input, 40, self.FOLLOW_40_in_indexed_container_type_def5289)
                # jaus2jsidl.g:1434:47: ( SL_COMMENT )?
                alt174 = 2
                LA174_0 = self.input.LA(1)

                if (LA174_0 == SL_COMMENT) :
                    alt174 = 1
                if alt174 == 1:
                    # jaus2jsidl.g:1434:47: SL_COMMENT
                    pass 
                    self.match(self.input, SL_COMMENT, self.FOLLOW_SL_COMMENT_in_indexed_container_type_def5291)



                #action start
                         
                self.item_index = INTLITERAL59.text
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "indexed_container_type_def"


    # $ANTLR start "list_type_def"
    # jaus2jsidl.g:1441:1: list_type_def : ( ITEM_CARDINALITY )? 'list' v1= ID '{' ( ml_comment | mlc1= ML_COMMENT )? v2= ( 'uint8' | 'uint16' | 'uint32' ) v3= ID '[' v4= numeric_literal ',' v5= numeric_literal ']' ';' ( ml_comment | mlc1= ML_COMMENT )? 'repeated' ( container_type_def | type_reference ) '}' ( ml_comment | mlc2= ML_COMMENT )? ;
    def list_type_def(self, ):

        v1 = None
        mlc1 = None
        v2 = None
        v3 = None
        mlc2 = None
        ITEM_CARDINALITY60 = None
        v4 = None

        v5 = None


        try:
            try:
                # jaus2jsidl.g:1442:5: ( ( ITEM_CARDINALITY )? 'list' v1= ID '{' ( ml_comment | mlc1= ML_COMMENT )? v2= ( 'uint8' | 'uint16' | 'uint32' ) v3= ID '[' v4= numeric_literal ',' v5= numeric_literal ']' ';' ( ml_comment | mlc1= ML_COMMENT )? 'repeated' ( container_type_def | type_reference ) '}' ( ml_comment | mlc2= ML_COMMENT )? )
                # jaus2jsidl.g:1443:9: ( ITEM_CARDINALITY )? 'list' v1= ID '{' ( ml_comment | mlc1= ML_COMMENT )? v2= ( 'uint8' | 'uint16' | 'uint32' ) v3= ID '[' v4= numeric_literal ',' v5= numeric_literal ']' ';' ( ml_comment | mlc1= ML_COMMENT )? 'repeated' ( container_type_def | type_reference ) '}' ( ml_comment | mlc2= ML_COMMENT )?
                pass 
                # jaus2jsidl.g:1443:9: ( ITEM_CARDINALITY )?
                alt175 = 2
                LA175_0 = self.input.LA(1)

                if (LA175_0 == ITEM_CARDINALITY) :
                    alt175 = 1
                if alt175 == 1:
                    # jaus2jsidl.g:1443:9: ITEM_CARDINALITY
                    pass 
                    ITEM_CARDINALITY60=self.match(self.input, ITEM_CARDINALITY, self.FOLLOW_ITEM_CARDINALITY_in_list_type_def5328)



                self.match(self.input, 103, self.FOLLOW_103_in_list_type_def5331)
                v1=self.match(self.input, ID, self.FOLLOW_ID_in_list_type_def5335)
                self.match(self.input, 38, self.FOLLOW_38_in_list_type_def5337)
                # jaus2jsidl.g:1443:45: ( ml_comment | mlc1= ML_COMMENT )?
                alt176 = 3
                LA176_0 = self.input.LA(1)

                if (LA176_0 == SL_COMMENT) :
                    alt176 = 1
                elif (LA176_0 == ML_COMMENT) :
                    alt176 = 2
                if alt176 == 1:
                    # jaus2jsidl.g:1443:46: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_list_type_def5341)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt176 == 2:
                    # jaus2jsidl.g:1443:59: mlc1= ML_COMMENT
                    pass 
                    mlc1=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_list_type_def5347)



                #action start
                         
                p = self.current_node
                n = etree.SubElement(p,'list')
                if self.jsidl_ns == "urn:jaus:jsidl:exp" and self.item_index:
                    # item indices are implicit in JSIDL 1.0
                    n.attrib['item_index'] = self.item_index
                if self.jsidl_ns == "urn:jaus:jsidl:1.0":
                    if self.vtag_stack:
                        n.attrib['vtag'] = self.vtag_stack.pop()
                self.current_node = n
                n.attrib['name'] = v1.text
                if ITEM_CARDINALITY60:
                      if ITEM_CARDINALITY60.text == 'required':
                          n.attrib['optional'] = 'false'
                      else:
                          n.attrib['optional'] = 'true'
                          self.optional_count += 1
                elif self.jsidl_ns == "urn:jaus:jsidl:1.0":
                    n.attrib['optional'] = 'false'  # required by JSIDL.
                if mlc2:
                    s=mlc2.text[2:-2].strip()
                    self.comment = compress_ws(s)
                else:
                    self.comment = ''
                self.type_map[v1.text] = n
                    
                #action end
                v2 = self.input.LT(1)
                if (82 <= self.input.LA(1) <= 83) or self.input.LA(1) == 85:
                    self.input.consume()
                    self._state.errorRecovery = False

                else:
                    mse = MismatchedSetException(None, self.input)
                    raise mse


                v3=self.match(self.input, ID, self.FOLLOW_ID_in_list_type_def5383)
                self.match(self.input, 60, self.FOLLOW_60_in_list_type_def5389)
                self._state.following.append(self.FOLLOW_numeric_literal_in_list_type_def5397)
                v4 = self.numeric_literal()

                self._state.following.pop()
                self.match(self.input, 35, self.FOLLOW_35_in_list_type_def5403)
                self._state.following.append(self.FOLLOW_numeric_literal_in_list_type_def5411)
                v5 = self.numeric_literal()

                self._state.following.pop()
                self.match(self.input, 61, self.FOLLOW_61_in_list_type_def5417)
                self.match(self.input, 40, self.FOLLOW_40_in_list_type_def5419)
                # jaus2jsidl.g:1475:13: ( ml_comment | mlc1= ML_COMMENT )?
                alt177 = 3
                LA177_0 = self.input.LA(1)

                if (LA177_0 == SL_COMMENT) :
                    alt177 = 1
                elif (LA177_0 == ML_COMMENT) :
                    alt177 = 2
                if alt177 == 1:
                    # jaus2jsidl.g:1475:15: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_list_type_def5423)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt177 == 2:
                    # jaus2jsidl.g:1475:28: mlc1= ML_COMMENT
                    pass 
                    mlc1=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_list_type_def5429)



                #action start
                     
                m = etree.SubElement(n,'count_field')
                m.attrib['field_type_unsigned'] = self.scalar_map[v2.text]
                if self.jsidl_ns == "urn:jaus:jsidl:exp":
                   # JSIDL 1.0 does not provide a name by which we can reference
                   # the count field.
                   m.attrib['name'] = v3.text
                m.attrib['min_count'] =  ((v4 is not None) and [self.input.toString(v4.start,v4.stop)] or [None])[0]
                m.attrib['max_count'] = ((v5 is not None) and [self.input.toString(v5.start,v5.stop)] or [None])[0]
                    
                #action end
                self.match(self.input, 104, self.FOLLOW_104_in_list_type_def5446)
                # jaus2jsidl.g:1486:18: ( container_type_def | type_reference )
                alt178 = 2
                LA178_0 = self.input.LA(1)

                if (LA178_0 == ITEM_CARDINALITY or LA178_0 == 103 or LA178_0 == 105 or (107 <= LA178_0 <= 108)) :
                    alt178 = 1
                elif (LA178_0 == ID or LA178_0 == 50 or LA178_0 == 81) :
                    alt178 = 2
                else:
                    nvae = NoViableAltException("", 178, 0, self.input)

                    raise nvae

                if alt178 == 1:
                    # jaus2jsidl.g:1486:20: container_type_def
                    pass 
                    self._state.following.append(self.FOLLOW_container_type_def_in_list_type_def5450)
                    self.container_type_def()

                    self._state.following.pop()


                elif alt178 == 2:
                    # jaus2jsidl.g:1486:41: type_reference
                    pass 
                    self._state.following.append(self.FOLLOW_type_reference_in_list_type_def5454)
                    self.type_reference()

                    self._state.following.pop()



                self.match(self.input, 39, self.FOLLOW_39_in_list_type_def5464)
                # jaus2jsidl.g:1487:11: ( ml_comment | mlc2= ML_COMMENT )?
                alt179 = 3
                LA179_0 = self.input.LA(1)

                if (LA179_0 == SL_COMMENT) :
                    alt179 = 1
                elif (LA179_0 == ML_COMMENT) :
                    alt179 = 2
                if alt179 == 1:
                    # jaus2jsidl.g:1487:12: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_list_type_def5467)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt179 == 2:
                    # jaus2jsidl.g:1487:25: mlc2= ML_COMMENT
                    pass 
                    mlc2=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_list_type_def5473)



                #action start
                     
                if mlc2:
                    s=mlc2.text[2:-2].strip()
                    n.attrib['interpretation'] += compress_ws(s)
                self.current_node = p
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "list_type_def"


    # $ANTLR start "variant_type_def"
    # jaus2jsidl.g:1496:1: variant_type_def : ( ITEM_CARDINALITY )? 'variant' v1= ID '{' ( SL_COMMENT )? ( description )? type= ( 'uint8' | 'uint16' | 'uint32' ) 'vtag' '[' len1= ( numeric_literal | ID ) ',' len2= ( numeric_literal | ID ) ']' ';' ( ml_comment | mlc1= ML_COMMENT )? ( tagged_item_def )* '}' ( ml_comment | mlc2= ML_COMMENT )? ;
    def variant_type_def(self, ):

        v1 = None
        type = None
        len1 = None
        len2 = None
        mlc1 = None
        mlc2 = None
        ITEM_CARDINALITY61 = None

        try:
            try:
                # jaus2jsidl.g:1497:5: ( ( ITEM_CARDINALITY )? 'variant' v1= ID '{' ( SL_COMMENT )? ( description )? type= ( 'uint8' | 'uint16' | 'uint32' ) 'vtag' '[' len1= ( numeric_literal | ID ) ',' len2= ( numeric_literal | ID ) ']' ';' ( ml_comment | mlc1= ML_COMMENT )? ( tagged_item_def )* '}' ( ml_comment | mlc2= ML_COMMENT )? )
                # jaus2jsidl.g:1498:9: ( ITEM_CARDINALITY )? 'variant' v1= ID '{' ( SL_COMMENT )? ( description )? type= ( 'uint8' | 'uint16' | 'uint32' ) 'vtag' '[' len1= ( numeric_literal | ID ) ',' len2= ( numeric_literal | ID ) ']' ';' ( ml_comment | mlc1= ML_COMMENT )? ( tagged_item_def )* '}' ( ml_comment | mlc2= ML_COMMENT )?
                pass 
                # jaus2jsidl.g:1498:9: ( ITEM_CARDINALITY )?
                alt180 = 2
                LA180_0 = self.input.LA(1)

                if (LA180_0 == ITEM_CARDINALITY) :
                    alt180 = 1
                if alt180 == 1:
                    # jaus2jsidl.g:1498:9: ITEM_CARDINALITY
                    pass 
                    ITEM_CARDINALITY61=self.match(self.input, ITEM_CARDINALITY, self.FOLLOW_ITEM_CARDINALITY_in_variant_type_def5506)



                self.match(self.input, 105, self.FOLLOW_105_in_variant_type_def5509)
                v1=self.match(self.input, ID, self.FOLLOW_ID_in_variant_type_def5513)
                self.match(self.input, 38, self.FOLLOW_38_in_variant_type_def5515)
                #action start
                         
                p = self.current_node
                n = etree.SubElement(p,'variant')
                if self.jsidl_ns == "urn:jaus:jsidl:exp" and self.item_index:
                    # item indices are implicit in JSIDL 1.0
                    n.attrib['item_index'] = self.item_index
                if self.jsidl_ns == "urn:jaus:jsidl:1.0":
                    if self.vtag_stack:
                        n.attrib['vtag'] = self.vtag_stack.pop()
                self.current_node = n
                n.attrib['name'] = v1.text
                if ITEM_CARDINALITY61:
                      if ITEM_CARDINALITY61.text == 'required':
                          n.attrib['optional'] = 'false'
                      else:
                          n.attrib['optional'] = 'true'
                          self.optional_count += 1
                elif self.jsidl_ns == "urn:jaus:jsidl:1.0":
                    n.attrib['optional'] = 'false'  # required by JSIDL.
                self.type_map[v1.text] = n
                    
                #action end
                # jaus2jsidl.g:1520:9: ( SL_COMMENT )?
                alt181 = 2
                LA181_0 = self.input.LA(1)

                if (LA181_0 == SL_COMMENT) :
                    alt181 = 1
                if alt181 == 1:
                    # jaus2jsidl.g:1520:9: SL_COMMENT
                    pass 
                    self.match(self.input, SL_COMMENT, self.FOLLOW_SL_COMMENT_in_variant_type_def5535)



                # jaus2jsidl.g:1521:9: ( description )?
                alt182 = 2
                LA182_0 = self.input.LA(1)

                if (LA182_0 == 41) :
                    alt182 = 1
                if alt182 == 1:
                    # jaus2jsidl.g:1521:9: description
                    pass 
                    self._state.following.append(self.FOLLOW_description_in_variant_type_def5546)
                    self.description()

                    self._state.following.pop()



                type = self.input.LT(1)
                if (82 <= self.input.LA(1) <= 83) or self.input.LA(1) == 85:
                    self.input.consume()
                    self._state.errorRecovery = False

                else:
                    mse = MismatchedSetException(None, self.input)
                    raise mse


                self.match(self.input, 106, self.FOLLOW_106_in_variant_type_def5571)
                self.match(self.input, 60, self.FOLLOW_60_in_variant_type_def5573)
                # jaus2jsidl.g:1522:63: ( numeric_literal | ID )
                alt183 = 2
                LA183_0 = self.input.LA(1)

                if ((INTLITERAL <= LA183_0 <= DOUBLELITERAL) or LA183_0 == 72) :
                    alt183 = 1
                elif (LA183_0 == ID) :
                    alt183 = 2
                else:
                    nvae = NoViableAltException("", 183, 0, self.input)

                    raise nvae

                if alt183 == 1:
                    # jaus2jsidl.g:1522:65: numeric_literal
                    pass 
                    self._state.following.append(self.FOLLOW_numeric_literal_in_variant_type_def5580)
                    self.numeric_literal()

                    self._state.following.pop()


                elif alt183 == 2:
                    # jaus2jsidl.g:1522:83: ID
                    pass 
                    self.match(self.input, ID, self.FOLLOW_ID_in_variant_type_def5584)



                self.match(self.input, 35, self.FOLLOW_35_in_variant_type_def5617)
                # jaus2jsidl.g:1523:40: ( numeric_literal | ID )
                alt184 = 2
                LA184_0 = self.input.LA(1)

                if ((INTLITERAL <= LA184_0 <= DOUBLELITERAL) or LA184_0 == 72) :
                    alt184 = 1
                elif (LA184_0 == ID) :
                    alt184 = 2
                else:
                    nvae = NoViableAltException("", 184, 0, self.input)

                    raise nvae

                if alt184 == 1:
                    # jaus2jsidl.g:1523:42: numeric_literal
                    pass 
                    self._state.following.append(self.FOLLOW_numeric_literal_in_variant_type_def5624)
                    self.numeric_literal()

                    self._state.following.pop()


                elif alt184 == 2:
                    # jaus2jsidl.g:1523:60: ID
                    pass 
                    self.match(self.input, ID, self.FOLLOW_ID_in_variant_type_def5628)



                self.match(self.input, 61, self.FOLLOW_61_in_variant_type_def5632)
                self.match(self.input, 40, self.FOLLOW_40_in_variant_type_def5634)
                #action start
                     
                nn = etree.SubElement(n,'vtag_field')
                nn.attrib['field_type_unsigned'] = self.scalar_map[type.text]
                    
                #action end
                # jaus2jsidl.g:1528:4: ( ml_comment | mlc1= ML_COMMENT )?
                alt185 = 3
                LA185_0 = self.input.LA(1)

                if (LA185_0 == SL_COMMENT) :
                    alt185 = 1
                elif (LA185_0 == ML_COMMENT) :
                    alt185 = 2
                if alt185 == 1:
                    # jaus2jsidl.g:1528:5: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_variant_type_def5646)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt185 == 2:
                    # jaus2jsidl.g:1528:18: mlc1= ML_COMMENT
                    pass 
                    mlc1=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_variant_type_def5652)



                #action start
                     
                if mlc1:
                    # This is the vtag_field interpretation.
                    s=mlc1.text[2:-2].strip()
                    nn.attrib['interpretation'] = compress_ws(s)
                    
                #action end
                # jaus2jsidl.g:1535:9: ( tagged_item_def )*
                while True: #loop186
                    alt186 = 2
                    LA186_0 = self.input.LA(1)

                    if (LA186_0 == 106) :
                        alt186 = 1


                    if alt186 == 1:
                        # jaus2jsidl.g:1535:11: tagged_item_def
                        pass 
                        self._state.following.append(self.FOLLOW_tagged_item_def_in_variant_type_def5672)
                        self.tagged_item_def()

                        self._state.following.pop()


                    else:
                        break #loop186


                self.match(self.input, 39, self.FOLLOW_39_in_variant_type_def5685)
                # jaus2jsidl.g:1536:13: ( ml_comment | mlc2= ML_COMMENT )?
                alt187 = 3
                LA187_0 = self.input.LA(1)

                if (LA187_0 == SL_COMMENT) :
                    alt187 = 1
                elif (LA187_0 == ML_COMMENT) :
                    alt187 = 2
                if alt187 == 1:
                    # jaus2jsidl.g:1536:14: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_variant_type_def5688)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt187 == 2:
                    # jaus2jsidl.g:1536:27: mlc2= ML_COMMENT
                    pass 
                    mlc2=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_variant_type_def5694)



                #action start
                     
                if mlc2:
                    s=mlc2.text[2:-2].strip()
                    self.current_node.attrib['interpretation'] = compress_ws(s)
                self.current_node = p
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "variant_type_def"


    # $ANTLR start "tagged_item_def"
    # jaus2jsidl.g:1546:1: tagged_item_def : 'vtag' (tag= ( INTLITERAL | ID ) ) ':' ( container_type_def | type_reference ) ;
    def tagged_item_def(self, ):

        tag = None

        try:
            try:
                # jaus2jsidl.g:1546:16: ( 'vtag' (tag= ( INTLITERAL | ID ) ) ':' ( container_type_def | type_reference ) )
                # jaus2jsidl.g:1547:5: 'vtag' (tag= ( INTLITERAL | ID ) ) ':' ( container_type_def | type_reference )
                pass 
                self.match(self.input, 106, self.FOLLOW_106_in_tagged_item_def5719)
                # jaus2jsidl.g:1547:12: (tag= ( INTLITERAL | ID ) )
                # jaus2jsidl.g:1547:14: tag= ( INTLITERAL | ID )
                pass 
                tag = self.input.LT(1)
                if self.input.LA(1) == ID or self.input.LA(1) == INTLITERAL:
                    self.input.consume()
                    self._state.errorRecovery = False

                else:
                    mse = MismatchedSetException(None, self.input)
                    raise mse


                #action start
                     
                if self.jsidl_ns == "urn:jaus:jsidl:exp":
                    self.vtag_stack.append(tag.text)
                    
                #action end



                self.match(self.input, 77, self.FOLLOW_77_in_tagged_item_def5748)
                # jaus2jsidl.g:1552:13: ( container_type_def | type_reference )
                alt188 = 2
                LA188_0 = self.input.LA(1)

                if (LA188_0 == ITEM_CARDINALITY or LA188_0 == 103 or LA188_0 == 105 or (107 <= LA188_0 <= 108)) :
                    alt188 = 1
                elif (LA188_0 == ID or LA188_0 == 50 or LA188_0 == 81) :
                    alt188 = 2
                else:
                    nvae = NoViableAltException("", 188, 0, self.input)

                    raise nvae

                if alt188 == 1:
                    # jaus2jsidl.g:1552:14: container_type_def
                    pass 
                    self._state.following.append(self.FOLLOW_container_type_def_in_tagged_item_def5751)
                    self.container_type_def()

                    self._state.following.pop()


                elif alt188 == 2:
                    # jaus2jsidl.g:1552:35: type_reference
                    pass 
                    self._state.following.append(self.FOLLOW_type_reference_in_tagged_item_def5755)
                    self.type_reference()

                    self._state.following.pop()







            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "tagged_item_def"


    # $ANTLR start "sequence_type_def"
    # jaus2jsidl.g:1556:1: sequence_type_def : ( ITEM_CARDINALITY )? 'sequence' v1= ID '{' ( ml_comment | ml1= ML_COMMENT )? ( indexed_container_type_def | indexed_type_reference )+ '}' ( ml_comment | ml2= ML_COMMENT )? ;
    def sequence_type_def(self, ):

        v1 = None
        ml1 = None
        ml2 = None
        ITEM_CARDINALITY62 = None

        try:
            try:
                # jaus2jsidl.g:1557:5: ( ( ITEM_CARDINALITY )? 'sequence' v1= ID '{' ( ml_comment | ml1= ML_COMMENT )? ( indexed_container_type_def | indexed_type_reference )+ '}' ( ml_comment | ml2= ML_COMMENT )? )
                # jaus2jsidl.g:1558:9: ( ITEM_CARDINALITY )? 'sequence' v1= ID '{' ( ml_comment | ml1= ML_COMMENT )? ( indexed_container_type_def | indexed_type_reference )+ '}' ( ml_comment | ml2= ML_COMMENT )?
                pass 
                # jaus2jsidl.g:1558:9: ( ITEM_CARDINALITY )?
                alt189 = 2
                LA189_0 = self.input.LA(1)

                if (LA189_0 == ITEM_CARDINALITY) :
                    alt189 = 1
                if alt189 == 1:
                    # jaus2jsidl.g:1558:9: ITEM_CARDINALITY
                    pass 
                    ITEM_CARDINALITY62=self.match(self.input, ITEM_CARDINALITY, self.FOLLOW_ITEM_CARDINALITY_in_sequence_type_def5783)



                self.match(self.input, 107, self.FOLLOW_107_in_sequence_type_def5786)
                v1=self.match(self.input, ID, self.FOLLOW_ID_in_sequence_type_def5790)
                #action start
                         
                p = self.current_node
                n = etree.SubElement(p,'sequence')
                if self.jsidl_ns == "urn:jaus:jsidl:exp" and self.item_index:
                    # item indices are implicit in JSIDL 1.0
                    n.attrib['item_index'] = self.item_index
                if self.jsidl_ns == "urn:jaus:jsidl:exp":
                    if self.vtag_stack:
                        n.attrib['vtag'] = self.vtag_stack.pop()
                n.attrib['name'] = v1.text
                if ITEM_CARDINALITY62:
                      if ITEM_CARDINALITY62.text == 'required':
                          n.attrib['optional'] = 'false'
                      else:
                          n.attrib['optional'] = 'true'
                          self.optional_count += 1
                elif self.jsidl_ns == "urn:jaus:jsidl:1.0":
                    n.attrib['optional'] = 'false'  # required by JSIDL.
                self.type_map[v1.text] = n
                p_optional_count = self.optional_count
                self.optional_count = 0
                self.current_node = n
                    
                #action end
                self.match(self.input, 38, self.FOLLOW_38_in_sequence_type_def5806)
                # jaus2jsidl.g:1583:5: ( ml_comment | ml1= ML_COMMENT )?
                alt190 = 3
                LA190_0 = self.input.LA(1)

                if (LA190_0 == SL_COMMENT) :
                    alt190 = 1
                elif (LA190_0 == ML_COMMENT) :
                    alt190 = 2
                if alt190 == 1:
                    # jaus2jsidl.g:1583:6: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_sequence_type_def5813)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt190 == 2:
                    # jaus2jsidl.g:1583:19: ml1= ML_COMMENT
                    pass 
                    ml1=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_sequence_type_def5819)



                # jaus2jsidl.g:1584:5: ( indexed_container_type_def | indexed_type_reference )+
                cnt191 = 0
                while True: #loop191
                    alt191 = 3
                    LA191_0 = self.input.LA(1)

                    if (LA191_0 == ITEM_CARDINALITY) :
                        LA191_2 = self.input.LA(2)

                        if (LA191_2 == 103 or LA191_2 == 105 or (107 <= LA191_2 <= 108)) :
                            alt191 = 1
                        elif (LA191_2 == ID or LA191_2 == 50 or LA191_2 == 81) :
                            alt191 = 2


                    elif (LA191_0 == 103 or LA191_0 == 105 or (107 <= LA191_0 <= 108)) :
                        alt191 = 1


                    if alt191 == 1:
                        # jaus2jsidl.g:1584:6: indexed_container_type_def
                        pass 
                        self._state.following.append(self.FOLLOW_indexed_container_type_def_in_sequence_type_def5828)
                        self.indexed_container_type_def()

                        self._state.following.pop()


                    elif alt191 == 2:
                        # jaus2jsidl.g:1584:35: indexed_type_reference
                        pass 
                        self._state.following.append(self.FOLLOW_indexed_type_reference_in_sequence_type_def5832)
                        self.indexed_type_reference()

                        self._state.following.pop()


                    else:
                        if cnt191 >= 1:
                            break #loop191

                        eee = EarlyExitException(191, self.input)
                        raise eee

                    cnt191 += 1


                self.match(self.input, 39, self.FOLLOW_39_in_sequence_type_def5841)
                # jaus2jsidl.g:1585:9: ( ml_comment | ml2= ML_COMMENT )?
                alt192 = 3
                LA192_0 = self.input.LA(1)

                if (LA192_0 == SL_COMMENT) :
                    alt192 = 1
                elif (LA192_0 == ML_COMMENT) :
                    alt192 = 2
                if alt192 == 1:
                    # jaus2jsidl.g:1585:10: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_sequence_type_def5844)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt192 == 2:
                    # jaus2jsidl.g:1585:23: ml2= ML_COMMENT
                    pass 
                    ml2=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_sequence_type_def5850)



                #action start
                     
                # \todo - What interpretation does ml1 correspond to?
                if ml2:
                    s=ml2.COMMENT.text[2:-2].strip()
                    n.attrib['interpretation'] = compress_ws(s)
                if self.optional_count > 0:
                    debug(4,"Sequence %s has %d optional fields.\n"%(v1.text,self.optional_count))
                    ftu = get_pv_unsigned_field(self.optional_count,self.scalar_map)  
                    pv = etree.Element('presence_vector',attrib={'field_type_unsigned':ftu})
                    self.current_node.insert(0,pv)
                self.optional_count = p_optional_count
                self.current_node = p
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "sequence_type_def"


    # $ANTLR start "record_type_def"
    # jaus2jsidl.g:1602:1: record_type_def : ( ITEM_CARDINALITY )? 'record' ID '{' ( ml_comment | mlc1= ML_COMMENT )? ( field_def | bitfield_type_def | variant_field_type_def | variable_format_field_type_def )+ '}' ( ';' )? ( ml_comment | mlc2= ML_COMMENT )? ;
    def record_type_def(self, ):

        mlc1 = None
        mlc2 = None
        ID63 = None
        ITEM_CARDINALITY64 = None

        try:
            try:
                # jaus2jsidl.g:1603:5: ( ( ITEM_CARDINALITY )? 'record' ID '{' ( ml_comment | mlc1= ML_COMMENT )? ( field_def | bitfield_type_def | variant_field_type_def | variable_format_field_type_def )+ '}' ( ';' )? ( ml_comment | mlc2= ML_COMMENT )? )
                # jaus2jsidl.g:1603:7: ( ITEM_CARDINALITY )? 'record' ID '{' ( ml_comment | mlc1= ML_COMMENT )? ( field_def | bitfield_type_def | variant_field_type_def | variable_format_field_type_def )+ '}' ( ';' )? ( ml_comment | mlc2= ML_COMMENT )?
                pass 
                # jaus2jsidl.g:1603:7: ( ITEM_CARDINALITY )?
                alt193 = 2
                LA193_0 = self.input.LA(1)

                if (LA193_0 == ITEM_CARDINALITY) :
                    alt193 = 1
                if alt193 == 1:
                    # jaus2jsidl.g:1603:9: ITEM_CARDINALITY
                    pass 
                    ITEM_CARDINALITY64=self.match(self.input, ITEM_CARDINALITY, self.FOLLOW_ITEM_CARDINALITY_in_record_type_def5878)



                self.match(self.input, 108, self.FOLLOW_108_in_record_type_def5883)
                ID63=self.match(self.input, ID, self.FOLLOW_ID_in_record_type_def5885)
                #action start
                     
                p = self.current_node
                self.optional_count = 0
                n = etree.SubElement(p,'record')
                if self.jsidl_ns == "urn:jaus:jsidl:exp" and self.item_index:
                    # item indices are implicit in JSIDL 1.0
                    n.attrib['item_index'] = self.item_index
                if self.jsidl_ns == "urn:jaus:jsidl:exp":
                    if self.vtag_stack:
                        n.attrib['vtag'] = self.vtag_stack.pop()
                n.attrib['name'] = ID63.text
                if ITEM_CARDINALITY64:
                      if ITEM_CARDINALITY64.text == 'required':
                          n.attrib['optional'] = 'false'
                      else:
                          n.attrib['optional'] = 'true'
                          self.optional_count += 1
                elif self.jsidl_ns == "urn:jaus:jsidl:1.0":
                    n.attrib['optional'] = 'false'  # Required by JSIDL in all cases
                self.type_map[ID63.text] = n
                p_optional_count = self.optional_count
                self.optional_count = 0
                self.current_node = n
                # debug(4,"Looking at record "+ID63.text+"\n")
                    
                #action end
                self.match(self.input, 38, self.FOLLOW_38_in_record_type_def5898)
                # jaus2jsidl.g:1629:10: ( ml_comment | mlc1= ML_COMMENT )?
                alt194 = 3
                LA194_0 = self.input.LA(1)

                if (LA194_0 == SL_COMMENT) :
                    alt194 = 1
                elif (LA194_0 == ML_COMMENT) :
                    alt194 = 2
                if alt194 == 1:
                    # jaus2jsidl.g:1629:12: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_record_type_def5902)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt194 == 2:
                    # jaus2jsidl.g:1629:25: mlc1= ML_COMMENT
                    pass 
                    mlc1=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_record_type_def5909)



                # jaus2jsidl.g:1630:10: ( field_def | bitfield_type_def | variant_field_type_def | variable_format_field_type_def )+
                cnt195 = 0
                while True: #loop195
                    alt195 = 5
                    LA195 = self.input.LA(1)
                    if LA195 == ITEM_CARDINALITY:
                        LA195 = self.input.LA(2)
                        if LA195 == 97:
                            alt195 = 3
                        elif LA195 == 99:
                            alt195 = 4
                        elif LA195 == 100:
                            alt195 = 2
                        elif LA195 == ID or LA195 == 50 or LA195 == 81 or LA195 == 82 or LA195 == 83 or LA195 == 84 or LA195 == 85 or LA195 == 86 or LA195 == 87 or LA195 == 88 or LA195 == 89 or LA195 == 90 or LA195 == 91 or LA195 == 92 or LA195 == 94 or LA195 == 95:
                            alt195 = 1

                    elif LA195 == 100:
                        alt195 = 2
                    elif LA195 == 97:
                        alt195 = 3
                    elif LA195 == 99:
                        alt195 = 4

                    if alt195 == 1:
                        # jaus2jsidl.g:1630:12: field_def
                        pass 
                        self._state.following.append(self.FOLLOW_field_def_in_record_type_def5925)
                        self.field_def()

                        self._state.following.pop()


                    elif alt195 == 2:
                        # jaus2jsidl.g:1631:14: bitfield_type_def
                        pass 
                        self._state.following.append(self.FOLLOW_bitfield_type_def_in_record_type_def5940)
                        self.bitfield_type_def()

                        self._state.following.pop()


                    elif alt195 == 3:
                        # jaus2jsidl.g:1632:14: variant_field_type_def
                        pass 
                        self._state.following.append(self.FOLLOW_variant_field_type_def_in_record_type_def5955)
                        self.variant_field_type_def()

                        self._state.following.pop()


                    elif alt195 == 4:
                        # jaus2jsidl.g:1633:14: variable_format_field_type_def
                        pass 
                        self._state.following.append(self.FOLLOW_variable_format_field_type_def_in_record_type_def5970)
                        self.variable_format_field_type_def()

                        self._state.following.pop()


                    else:
                        if cnt195 >= 1:
                            break #loop195

                        eee = EarlyExitException(195, self.input)
                        raise eee

                    cnt195 += 1


                self.match(self.input, 39, self.FOLLOW_39_in_record_type_def5980)
                # jaus2jsidl.g:1634:10: ( ';' )?
                alt196 = 2
                LA196_0 = self.input.LA(1)

                if (LA196_0 == 40) :
                    alt196 = 1
                if alt196 == 1:
                    # jaus2jsidl.g:1634:10: ';'
                    pass 
                    self.match(self.input, 40, self.FOLLOW_40_in_record_type_def5982)



                # jaus2jsidl.g:1634:15: ( ml_comment | mlc2= ML_COMMENT )?
                alt197 = 3
                LA197_0 = self.input.LA(1)

                if (LA197_0 == SL_COMMENT) :
                    alt197 = 1
                elif (LA197_0 == ML_COMMENT) :
                    alt197 = 2
                if alt197 == 1:
                    # jaus2jsidl.g:1634:17: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_record_type_def5987)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt197 == 2:
                    # jaus2jsidl.g:1634:30: mlc2= ML_COMMENT
                    pass 
                    mlc2=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_record_type_def5994)



                #action start
                     
                if len(self.comment) > 0:
                    self.comment = self.comment.replace('//',' ')
                if mlc1:
                    comment = mlc1.text[2:-2].strip()
                    self.comment += compress_ws(comment)
                if mlc2:
                    comment = mlc2.text[2:-2].strip()
                    self.comment += compress_ws(comment)
                n.attrib['interpretation'] = self.comment
                if self.optional_count > 0:
                    debug(4,"Record %s has %d optional fields.\n"%(ID63.text,self.optional_count))
                    ftu = get_pv_unsigned_field(self.optional_count,self.scalar_map)  
                    pv = etree.Element('presence_vector',attrib={'field_type_unsigned':ftu})
                    self.current_node.insert(0,pv)
                self.optional_count = p_optional_count
                self.current_node = p
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "record_type_def"


    # $ANTLR start "type_reference"
    # jaus2jsidl.g:1656:1: type_reference : scoped_id ID ';' ( ml_comment | ML_COMMENT )? ;
    def type_reference(self, ):

        ID66 = None
        ML_COMMENT67 = None
        scoped_id65 = None


        try:
            try:
                # jaus2jsidl.g:1657:5: ( scoped_id ID ';' ( ml_comment | ML_COMMENT )? )
                # jaus2jsidl.g:1658:5: scoped_id ID ';' ( ml_comment | ML_COMMENT )?
                pass 
                self._state.following.append(self.FOLLOW_scoped_id_in_type_reference6025)
                scoped_id65 = self.scoped_id()

                self._state.following.pop()
                ID66=self.match(self.input, ID, self.FOLLOW_ID_in_type_reference6027)
                self.match(self.input, 40, self.FOLLOW_40_in_type_reference6029)
                #action start
                     
                p = self.current_node
                n = etree.SubElement(p, 'tag_tbd')
                self.current_node = n
                self.second_pass_tag_resolution[n] = ((scoped_id65 is not None) and [self.input.toString(scoped_id65.start,scoped_id65.stop)] or [None])[0]
                if self.jsidl_ns == "urn:jaus:jsidl:exp":
                    if self.vtag_stack:
                        n.attrib['vtag'] = self.vtag_stack.pop()
                n.attrib['name'] = ID66.text
                if self.jsidl_ns == "urn:jaus:jsidl:1.0":
                    if True:
                       n.attrib['optional'] = 'false'  # more recent JSIDL requires optional for all?
                    elif n.tag != 'declared_variant':
                        # Quirk in JSIDL... 'optional' required by some and not other declared types.
                        debug(4,"Adding dummy 'optional' var for %s %s\n"%(((scoped_id65 is not None) and [self.input.toString(scoped_id65.start,scoped_id65.stop)] or [None])[0],ID66.text))
                        n.attrib['optional'] = 'false'
                    elif 'optional' in n.attrib:
                        debug(4,"declared_variant has an 'optional' attr.\n")
                n.attrib['declared_type_ref'] = ((scoped_id65 is not None) and [self.input.toString(scoped_id65.start,scoped_id65.stop)] or [None])[0]
                self.type_map[ID66.text] = n
                    
                #action end
                # jaus2jsidl.g:1680:5: ( ml_comment | ML_COMMENT )?
                alt198 = 3
                LA198_0 = self.input.LA(1)

                if (LA198_0 == SL_COMMENT) :
                    alt198 = 1
                elif (LA198_0 == ML_COMMENT) :
                    alt198 = 2
                if alt198 == 1:
                    # jaus2jsidl.g:1680:6: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_type_reference6042)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt198 == 2:
                    # jaus2jsidl.g:1680:19: ML_COMMENT
                    pass 
                    ML_COMMENT67=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_type_reference6046)



                #action start
                     
                if ML_COMMENT67:
                    comment = ML_COMMENT67.text[2:-2].strip()  # strip the '//' prefix            
                    n.attrib['interpretation'] = compress_ws(comment)
                self.current_node = p
                        
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "type_reference"


    # $ANTLR start "indexed_type_reference"
    # jaus2jsidl.g:1689:1: indexed_type_reference : ITEM_CARDINALITY scoped_id ID '=' INTLITERAL ';' ( ml_comment | ML_COMMENT )? ;
    def indexed_type_reference(self, ):

        ID69 = None
        ITEM_CARDINALITY70 = None
        INTLITERAL71 = None
        ML_COMMENT72 = None
        scoped_id68 = None


        try:
            try:
                # jaus2jsidl.g:1690:5: ( ITEM_CARDINALITY scoped_id ID '=' INTLITERAL ';' ( ml_comment | ML_COMMENT )? )
                # jaus2jsidl.g:1691:9: ITEM_CARDINALITY scoped_id ID '=' INTLITERAL ';' ( ml_comment | ML_COMMENT )?
                pass 
                ITEM_CARDINALITY70=self.match(self.input, ITEM_CARDINALITY, self.FOLLOW_ITEM_CARDINALITY_in_indexed_type_reference6079)
                self._state.following.append(self.FOLLOW_scoped_id_in_indexed_type_reference6081)
                scoped_id68 = self.scoped_id()

                self._state.following.pop()
                ID69=self.match(self.input, ID, self.FOLLOW_ID_in_indexed_type_reference6083)
                self.match(self.input, 34, self.FOLLOW_34_in_indexed_type_reference6085)
                INTLITERAL71=self.match(self.input, INTLITERAL, self.FOLLOW_INTLITERAL_in_indexed_type_reference6087)
                self.match(self.input, 40, self.FOLLOW_40_in_indexed_type_reference6089)
                # jaus2jsidl.g:1691:58: ( ml_comment | ML_COMMENT )?
                alt199 = 3
                LA199_0 = self.input.LA(1)

                if (LA199_0 == SL_COMMENT) :
                    alt199 = 1
                elif (LA199_0 == ML_COMMENT) :
                    alt199 = 2
                if alt199 == 1:
                    # jaus2jsidl.g:1691:59: ml_comment
                    pass 
                    self._state.following.append(self.FOLLOW_ml_comment_in_indexed_type_reference6092)
                    self.ml_comment()

                    self._state.following.pop()


                elif alt199 == 2:
                    # jaus2jsidl.g:1691:72: ML_COMMENT
                    pass 
                    ML_COMMENT72=self.match(self.input, ML_COMMENT, self.FOLLOW_ML_COMMENT_in_indexed_type_reference6096)



                #action start
                         
                p = self.current_node
                n = etree.SubElement(p, 'tag_tbd')
                self.second_pass_tag_resolution[n] = ((scoped_id68 is not None) and [self.input.toString(scoped_id68.start,scoped_id68.stop)] or [None])[0]
                n.attrib['name'] = ID69.text
                n.attrib['declared_type_ref'] = ((scoped_id68 is not None) and [self.input.toString(scoped_id68.start,scoped_id68.stop)] or [None])[0]
                self.type_map[ID69.text] = n
                if ITEM_CARDINALITY70.text == 'optional':
                    n.attrib['optional'] = 'true'
                    self.optional_count += 1
                else:
                    n.attrib['optional'] = 'false'  # For repeated or required.
                if self.jsidl_ns == "urn:jaus:jsidl:exp":
                    n.attrib['item_index'] = INTLITERAL71.text
                if ML_COMMENT72:
                    comment = ML_COMMENT72.text[2:-2].strip()  # strip the '/*' '*/' delims.
                    n.attrib['interpretation'] = compress_ws(comment)
                        
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "indexed_type_reference"


    # $ANTLR start "field_type_reference"
    # jaus2jsidl.g:1712:1: field_type_reference : ITEM_CARDINALITY scoped_id ID '=' INTLITERAL ';' ( SL_COMMENT )? ;
    def field_type_reference(self, ):

        ID74 = None
        INTLITERAL75 = None
        ITEM_CARDINALITY76 = None
        SL_COMMENT77 = None
        scoped_id73 = None


        try:
            try:
                # jaus2jsidl.g:1713:5: ( ITEM_CARDINALITY scoped_id ID '=' INTLITERAL ';' ( SL_COMMENT )? )
                # jaus2jsidl.g:1714:9: ITEM_CARDINALITY scoped_id ID '=' INTLITERAL ';' ( SL_COMMENT )?
                pass 
                ITEM_CARDINALITY76=self.match(self.input, ITEM_CARDINALITY, self.FOLLOW_ITEM_CARDINALITY_in_field_type_reference6133)
                self._state.following.append(self.FOLLOW_scoped_id_in_field_type_reference6135)
                scoped_id73 = self.scoped_id()

                self._state.following.pop()
                ID74=self.match(self.input, ID, self.FOLLOW_ID_in_field_type_reference6137)
                self.match(self.input, 34, self.FOLLOW_34_in_field_type_reference6139)
                INTLITERAL75=self.match(self.input, INTLITERAL, self.FOLLOW_INTLITERAL_in_field_type_reference6141)
                self.match(self.input, 40, self.FOLLOW_40_in_field_type_reference6143)
                # jaus2jsidl.g:1714:58: ( SL_COMMENT )?
                alt200 = 2
                LA200_0 = self.input.LA(1)

                if (LA200_0 == SL_COMMENT) :
                    alt200 = 1
                if alt200 == 1:
                    # jaus2jsidl.g:1714:58: SL_COMMENT
                    pass 
                    SL_COMMENT77=self.match(self.input, SL_COMMENT, self.FOLLOW_SL_COMMENT_in_field_type_reference6145)



                #action start
                         
                p = self.current_node
                n = etree.SubElement(p, 'tag_tbd')
                self.second_pass_tag_resolution[n] = ((scoped_id73 is not None) and [self.input.toString(scoped_id73.start,scoped_id73.stop)] or [None])[0]
                n.attrib['name'] = ID74.text
                n.attrib['declared_type_ref'] = ((scoped_id73 is not None) and [self.input.toString(scoped_id73.start,scoped_id73.stop)] or [None])[0]
                self.type_map[ID74.text] = n
                if self.jsidl_ns == "urn:jaus:jsidl:exp":
                    n.attrib['item_index'] = INTLITERAL75.text
                if ITEM_CARDINALITY76.text == 'required':
                    n.attrib['optional'] = 'false'
                elif ITEM_CARDINALITY76.text == 'optional':
                    n.attrib['optional'] = 'true'
                    self.optional_count += 1
                if SL_COMMENT77:
                    comment = SL_COMMENT77.text[2:].strip()  # strip the '//' prefix            
                    n.attrib['interpretation'] = compress_ws(comment)
                        
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "field_type_reference"


    # $ANTLR start "return_spec"
    # jaus2jsidl.g:1735:1: return_spec : 'returns' scoped_id ID ';' ;
    def return_spec(self, ):

        try:
            try:
                # jaus2jsidl.g:1736:5: ( 'returns' scoped_id ID ';' )
                # jaus2jsidl.g:1737:9: 'returns' scoped_id ID ';'
                pass 
                self.match(self.input, 109, self.FOLLOW_109_in_return_spec6181)
                self._state.following.append(self.FOLLOW_scoped_id_in_return_spec6183)
                self.scoped_id()

                self._state.following.pop()
                self.match(self.input, ID, self.FOLLOW_ID_in_return_spec6185)
                self.match(self.input, 40, self.FOLLOW_40_in_return_spec6187)




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "return_spec"


    # $ANTLR start "array_type_reference"
    # jaus2jsidl.g:1740:1: array_type_reference : scoped_id ID '[' scoped_id ']' ';' ( SL_COMMENT )? ;
    def array_type_reference(self, ):

        try:
            try:
                # jaus2jsidl.g:1741:5: ( scoped_id ID '[' scoped_id ']' ';' ( SL_COMMENT )? )
                # jaus2jsidl.g:1742:10: scoped_id ID '[' scoped_id ']' ';' ( SL_COMMENT )?
                pass 
                self._state.following.append(self.FOLLOW_scoped_id_in_array_type_reference6213)
                self.scoped_id()

                self._state.following.pop()
                self.match(self.input, ID, self.FOLLOW_ID_in_array_type_reference6215)
                self.match(self.input, 60, self.FOLLOW_60_in_array_type_reference6217)
                self._state.following.append(self.FOLLOW_scoped_id_in_array_type_reference6219)
                self.scoped_id()

                self._state.following.pop()
                self.match(self.input, 61, self.FOLLOW_61_in_array_type_reference6221)
                self.match(self.input, 40, self.FOLLOW_40_in_array_type_reference6223)
                # jaus2jsidl.g:1742:45: ( SL_COMMENT )?
                alt201 = 2
                LA201_0 = self.input.LA(1)

                if (LA201_0 == SL_COMMENT) :
                    alt201 = 1
                if alt201 == 1:
                    # jaus2jsidl.g:1742:45: SL_COMMENT
                    pass 
                    self.match(self.input, SL_COMMENT, self.FOLLOW_SL_COMMENT_in_array_type_reference6225)







            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "array_type_reference"


    # $ANTLR start "attrs"
    # jaus2jsidl.g:1745:1: attrs : '(' ( attr_assignment_list )* ')' ;
    def attrs(self, ):

        try:
            try:
                # jaus2jsidl.g:1746:5: ( '(' ( attr_assignment_list )* ')' )
                # jaus2jsidl.g:1747:9: '(' ( attr_assignment_list )* ')'
                pass 
                self.match(self.input, 32, self.FOLLOW_32_in_attrs6251)
                # jaus2jsidl.g:1747:13: ( attr_assignment_list )*
                while True: #loop202
                    alt202 = 2
                    LA202_0 = self.input.LA(1)

                    if (LA202_0 == ID) :
                        alt202 = 1


                    if alt202 == 1:
                        # jaus2jsidl.g:1747:13: attr_assignment_list
                        pass 
                        self._state.following.append(self.FOLLOW_attr_assignment_list_in_attrs6253)
                        self.attr_assignment_list()

                        self._state.following.pop()


                    else:
                        break #loop202


                self.match(self.input, 37, self.FOLLOW_37_in_attrs6256)




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "attrs"


    # $ANTLR start "attr_assignment_list"
    # jaus2jsidl.g:1750:1: attr_assignment_list : attr_assignment ( ',' attr_assignment )* ;
    def attr_assignment_list(self, ):

        try:
            try:
                # jaus2jsidl.g:1751:5: ( attr_assignment ( ',' attr_assignment )* )
                # jaus2jsidl.g:1752:9: attr_assignment ( ',' attr_assignment )*
                pass 
                self._state.following.append(self.FOLLOW_attr_assignment_in_attr_assignment_list6281)
                self.attr_assignment()

                self._state.following.pop()
                # jaus2jsidl.g:1752:25: ( ',' attr_assignment )*
                while True: #loop203
                    alt203 = 2
                    LA203_0 = self.input.LA(1)

                    if (LA203_0 == 35) :
                        alt203 = 1


                    if alt203 == 1:
                        # jaus2jsidl.g:1752:26: ',' attr_assignment
                        pass 
                        self.match(self.input, 35, self.FOLLOW_35_in_attr_assignment_list6284)
                        self._state.following.append(self.FOLLOW_attr_assignment_in_attr_assignment_list6286)
                        self.attr_assignment()

                        self._state.following.pop()


                    else:
                        break #loop203






            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "attr_assignment_list"


    # $ANTLR start "attr_assignment"
    # jaus2jsidl.g:1755:1: attr_assignment : ID '=' STRINGLITERAL ;
    def attr_assignment(self, ):

        ID78 = None
        STRINGLITERAL79 = None

        try:
            try:
                # jaus2jsidl.g:1756:5: ( ID '=' STRINGLITERAL )
                # jaus2jsidl.g:1757:9: ID '=' STRINGLITERAL
                pass 
                ID78=self.match(self.input, ID, self.FOLLOW_ID_in_attr_assignment6314)
                self.match(self.input, 34, self.FOLLOW_34_in_attr_assignment6316)
                STRINGLITERAL79=self.match(self.input, STRINGLITERAL, self.FOLLOW_STRINGLITERAL_in_attr_assignment6318)
                #action start
                         
                # Strip the quote marks.
                self.current_node.attrib[ID78.text] = STRINGLITERAL79.text[1:-1]
                    
                #action end




            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "attr_assignment"


    # $ANTLR start "ml_comment"
    # jaus2jsidl.g:1764:1: ml_comment : ( SL_COMMENT )+ ;
    def ml_comment(self, ):

        SL_COMMENT80 = None

        try:
            try:
                # jaus2jsidl.g:1764:11: ( ( SL_COMMENT )+ )
                # jaus2jsidl.g:1765:5: ( SL_COMMENT )+
                pass 
                # jaus2jsidl.g:1765:5: ( SL_COMMENT )+
                cnt204 = 0
                while True: #loop204
                    alt204 = 2
                    LA204_0 = self.input.LA(1)

                    if (LA204_0 == SL_COMMENT) :
                        alt204 = 1


                    if alt204 == 1:
                        # jaus2jsidl.g:1765:6: SL_COMMENT
                        pass 
                        SL_COMMENT80=self.match(self.input, SL_COMMENT, self.FOLLOW_SL_COMMENT_in_ml_comment6345)
                        #action start
                        self.comment+=SL_COMMENT80.text.replace('//','')
                        #action end


                    else:
                        if cnt204 >= 1:
                            break #loop204

                        eee = EarlyExitException(204, self.input)
                        raise eee

                    cnt204 += 1






            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "ml_comment"


    # $ANTLR start "keyword"
    # jaus2jsidl.g:1772:1: keyword : ( 'event' | 'events' );
    def keyword(self, ):

        try:
            try:
                # jaus2jsidl.g:1773:5: ( 'event' | 'events' )
                # jaus2jsidl.g:
                pass 
                if self.input.LA(1) == 50 or self.input.LA(1) == 81:
                    self.input.consume()
                    self._state.errorRecovery = False

                else:
                    mse = MismatchedSetException(None, self.input)
                    raise mse






            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return 

    # $ANTLR end "keyword"

    class scoped_id_return(ParserRuleReturnScope):
        def __init__(self):
            ParserRuleReturnScope.__init__(self)





    # $ANTLR start "scoped_id"
    # jaus2jsidl.g:1777:1: scoped_id : ( keyword | ID ) ( '.' ( keyword | ID ) )* ;
    def scoped_id(self, ):

        retval = self.scoped_id_return()
        retval.start = self.input.LT(1)

        try:
            try:
                # jaus2jsidl.g:1778:5: ( ( keyword | ID ) ( '.' ( keyword | ID ) )* )
                # jaus2jsidl.g:1779:6: ( keyword | ID ) ( '.' ( keyword | ID ) )*
                pass 
                # jaus2jsidl.g:1779:6: ( keyword | ID )
                alt205 = 2
                LA205_0 = self.input.LA(1)

                if (LA205_0 == 50 or LA205_0 == 81) :
                    alt205 = 1
                elif (LA205_0 == ID) :
                    alt205 = 2
                else:
                    nvae = NoViableAltException("", 205, 0, self.input)

                    raise nvae

                if alt205 == 1:
                    # jaus2jsidl.g:1779:7: keyword
                    pass 
                    self._state.following.append(self.FOLLOW_keyword_in_scoped_id6403)
                    self.keyword()

                    self._state.following.pop()


                elif alt205 == 2:
                    # jaus2jsidl.g:1779:17: ID
                    pass 
                    self.match(self.input, ID, self.FOLLOW_ID_in_scoped_id6407)



                # jaus2jsidl.g:1779:21: ( '.' ( keyword | ID ) )*
                while True: #loop207
                    alt207 = 2
                    LA207_0 = self.input.LA(1)

                    if (LA207_0 == 76) :
                        alt207 = 1


                    if alt207 == 1:
                        # jaus2jsidl.g:1779:23: '.' ( keyword | ID )
                        pass 
                        self.match(self.input, 76, self.FOLLOW_76_in_scoped_id6412)
                        # jaus2jsidl.g:1779:27: ( keyword | ID )
                        alt206 = 2
                        LA206_0 = self.input.LA(1)

                        if (LA206_0 == 50 or LA206_0 == 81) :
                            alt206 = 1
                        elif (LA206_0 == ID) :
                            alt206 = 2
                        else:
                            nvae = NoViableAltException("", 206, 0, self.input)

                            raise nvae

                        if alt206 == 1:
                            # jaus2jsidl.g:1779:28: keyword
                            pass 
                            self._state.following.append(self.FOLLOW_keyword_in_scoped_id6415)
                            self.keyword()

                            self._state.following.pop()


                        elif alt206 == 2:
                            # jaus2jsidl.g:1779:38: ID
                            pass 
                            self.match(self.input, ID, self.FOLLOW_ID_in_scoped_id6419)





                    else:
                        break #loop207





                retval.stop = self.input.LT(-1)


            except RecognitionException, re:
                self.reportError(re)
                self.recover(self.input, re)
        finally:

            pass

        return retval

    # $ANTLR end "scoped_id"


    # Delegated rules


    # lookup tables for DFA #82

    DFA82_eot = DFA.unpack(
        u"\11\uffff"
        )

    DFA82_eof = DFA.unpack(
        u"\11\uffff"
        )

    DFA82_min = DFA.unpack(
        u"\1\5\2\43\1\uffff\1\5\2\uffff\2\43"
        )

    DFA82_max = DFA.unpack(
        u"\1\121\2\114\1\uffff\1\121\2\uffff\2\114"
        )

    DFA82_accept = DFA.unpack(
        u"\3\uffff\1\3\1\uffff\1\1\1\2\2\uffff"
        )

    DFA82_special = DFA.unpack(
        u"\11\uffff"
        )

            
    DFA82_transition = [
        DFA.unpack(u"\1\2\37\uffff\1\3\14\uffff\1\1\36\uffff\1\1"),
        DFA.unpack(u"\1\6\1\uffff\1\5\46\uffff\1\4"),
        DFA.unpack(u"\1\6\1\uffff\1\5\46\uffff\1\4"),
        DFA.unpack(u""),
        DFA.unpack(u"\1\10\54\uffff\1\7\36\uffff\1\7"),
        DFA.unpack(u""),
        DFA.unpack(u""),
        DFA.unpack(u"\1\6\1\uffff\1\5\46\uffff\1\4"),
        DFA.unpack(u"\1\6\1\uffff\1\5\46\uffff\1\4")
    ]

    # class definition for DFA #82

    DFA82 = DFA
    # lookup tables for DFA #83

    DFA83_eot = DFA.unpack(
        u"\11\uffff"
        )

    DFA83_eof = DFA.unpack(
        u"\11\uffff"
        )

    DFA83_min = DFA.unpack(
        u"\1\5\2\43\1\uffff\1\5\2\uffff\2\43"
        )

    DFA83_max = DFA.unpack(
        u"\1\121\2\114\1\uffff\1\121\2\uffff\2\114"
        )

    DFA83_accept = DFA.unpack(
        u"\3\uffff\1\3\1\uffff\1\1\1\2\2\uffff"
        )

    DFA83_special = DFA.unpack(
        u"\11\uffff"
        )

            
    DFA83_transition = [
        DFA.unpack(u"\1\2\37\uffff\1\3\14\uffff\1\1\36\uffff\1\1"),
        DFA.unpack(u"\1\6\1\uffff\1\5\46\uffff\1\4"),
        DFA.unpack(u"\1\6\1\uffff\1\5\46\uffff\1\4"),
        DFA.unpack(u""),
        DFA.unpack(u"\1\10\54\uffff\1\7\36\uffff\1\7"),
        DFA.unpack(u""),
        DFA.unpack(u""),
        DFA.unpack(u"\1\6\1\uffff\1\5\46\uffff\1\4"),
        DFA.unpack(u"\1\6\1\uffff\1\5\46\uffff\1\4")
    ]

    # class definition for DFA #83

    DFA83 = DFA
    # lookup tables for DFA #84

    DFA84_eot = DFA.unpack(
        u"\10\uffff"
        )

    DFA84_eof = DFA.unpack(
        u"\10\uffff"
        )

    DFA84_min = DFA.unpack(
        u"\1\5\2\40\1\5\2\uffff\2\40"
        )

    DFA84_max = DFA.unpack(
        u"\1\121\2\114\1\121\2\uffff\2\114"
        )

    DFA84_accept = DFA.unpack(
        u"\4\uffff\1\1\1\2\2\uffff"
        )

    DFA84_special = DFA.unpack(
        u"\10\uffff"
        )

            
    DFA84_transition = [
        DFA.unpack(u"\1\2\54\uffff\1\1\36\uffff\1\1"),
        DFA.unpack(u"\1\5\1\uffff\1\4\5\uffff\1\5\43\uffff\1\3"),
        DFA.unpack(u"\1\5\1\uffff\1\4\5\uffff\1\5\43\uffff\1\3"),
        DFA.unpack(u"\1\7\54\uffff\1\6\36\uffff\1\6"),
        DFA.unpack(u""),
        DFA.unpack(u""),
        DFA.unpack(u"\1\5\1\uffff\1\4\5\uffff\1\5\43\uffff\1\3"),
        DFA.unpack(u"\1\5\1\uffff\1\4\5\uffff\1\5\43\uffff\1\3")
    ]

    # class definition for DFA #84

    DFA84 = DFA
 

    FOLLOW_service_set_in_jaus82 = frozenset([1])
    FOLLOW_type_set_in_jaus86 = frozenset([1])
    FOLLOW_ml_comment_in_service_set121 = frozenset([31])
    FOLLOW_ML_COMMENT_in_service_set125 = frozenset([31])
    FOLLOW_31_in_service_set137 = frozenset([5])
    FOLLOW_ID_in_service_set139 = frozenset([32])
    FOLLOW_32_in_service_set149 = frozenset([33])
    FOLLOW_33_in_service_set151 = frozenset([34])
    FOLLOW_34_in_service_set153 = frozenset([6])
    FOLLOW_URI_in_service_set155 = frozenset([35])
    FOLLOW_35_in_service_set157 = frozenset([36])
    FOLLOW_36_in_service_set159 = frozenset([34])
    FOLLOW_34_in_service_set161 = frozenset([7])
    FOLLOW_VERSION_in_service_set163 = frozenset([37])
    FOLLOW_37_in_service_set165 = frozenset([38])
    FOLLOW_38_in_service_set185 = frozenset([4, 17, 39, 41, 42, 43, 46, 48, 49, 50, 51])
    FOLLOW_ml_comment_in_service_set196 = frozenset([39, 41, 42, 43, 46, 48, 49, 50, 51])
    FOLLOW_ML_COMMENT_in_service_set200 = frozenset([39, 41, 42, 43, 46, 48, 49, 50, 51])
    FOLLOW_description_in_service_set213 = frozenset([4, 17, 39, 42, 43, 46, 48, 49, 50, 51])
    FOLLOW_ml_comment_in_service_set216 = frozenset([39, 42, 43, 46, 48, 49, 50, 51])
    FOLLOW_ML_COMMENT_in_service_set220 = frozenset([39, 42, 43, 46, 48, 49, 50, 51])
    FOLLOW_assumptions_in_service_set235 = frozenset([4, 17, 39, 43, 46, 48, 49, 50, 51])
    FOLLOW_ml_comment_in_service_set238 = frozenset([39, 43, 46, 48, 49, 50, 51])
    FOLLOW_ML_COMMENT_in_service_set242 = frozenset([39, 43, 46, 48, 49, 50, 51])
    FOLLOW_references_in_service_set259 = frozenset([4, 17, 39, 46, 48, 49, 50, 51])
    FOLLOW_ml_comment_in_service_set262 = frozenset([39, 46, 48, 49, 50, 51])
    FOLLOW_ML_COMMENT_in_service_set266 = frozenset([39, 46, 48, 49, 50, 51])
    FOLLOW_constant_set_in_service_set283 = frozenset([4, 17, 39, 46, 48, 49, 50, 51])
    FOLLOW_ml_comment_in_service_set286 = frozenset([39, 46, 48, 49, 50, 51])
    FOLLOW_ML_COMMENT_in_service_set290 = frozenset([39, 46, 48, 49, 50, 51])
    FOLLOW_type_set_in_service_set305 = frozenset([4, 17, 39, 48, 49, 50, 51])
    FOLLOW_ml_comment_in_service_set308 = frozenset([39, 48, 49, 50, 51])
    FOLLOW_ML_COMMENT_in_service_set312 = frozenset([39, 48, 49, 50, 51])
    FOLLOW_message_set_in_service_set327 = frozenset([4, 17, 39, 50, 51])
    FOLLOW_ml_comment_in_service_set330 = frozenset([39, 50, 51])
    FOLLOW_ML_COMMENT_in_service_set334 = frozenset([39, 50, 51])
    FOLLOW_internal_event_set_in_service_set349 = frozenset([4, 17, 39, 51])
    FOLLOW_ml_comment_in_service_set352 = frozenset([39, 51])
    FOLLOW_ML_COMMENT_in_service_set356 = frozenset([39, 51])
    FOLLOW_protocol_behavior_in_service_set371 = frozenset([4, 17, 39])
    FOLLOW_ml_comment_in_service_set374 = frozenset([39])
    FOLLOW_ML_COMMENT_in_service_set378 = frozenset([39])
    FOLLOW_39_in_service_set392 = frozenset([1, 40])
    FOLLOW_40_in_service_set394 = frozenset([1])
    FOLLOW_41_in_description414 = frozenset([8])
    FOLLOW_STRINGLITERAL_in_description416 = frozenset([40])
    FOLLOW_40_in_description418 = frozenset([1])
    FOLLOW_42_in_assumptions443 = frozenset([8])
    FOLLOW_STRINGLITERAL_in_assumptions445 = frozenset([40])
    FOLLOW_40_in_assumptions447 = frozenset([1])
    FOLLOW_43_in_references475 = frozenset([38])
    FOLLOW_38_in_references477 = frozenset([39, 44, 45])
    FOLLOW_44_in_references490 = frozenset([5])
    FOLLOW_ref_attr_in_references518 = frozenset([40])
    FOLLOW_40_in_references520 = frozenset([39, 45])
    FOLLOW_45_in_references538 = frozenset([5])
    FOLLOW_ref_attr_in_references566 = frozenset([40])
    FOLLOW_40_in_references568 = frozenset([39, 45])
    FOLLOW_39_in_references595 = frozenset([1, 40])
    FOLLOW_40_in_references597 = frozenset([1])
    FOLLOW_ID_in_ref_attr614 = frozenset([6])
    FOLLOW_URI_in_ref_attr616 = frozenset([7])
    FOLLOW_VERSION_in_ref_attr618 = frozenset([1, 17])
    FOLLOW_ml_comment_in_ref_attr642 = frozenset([1])
    FOLLOW_46_in_constant_set676 = frozenset([5])
    FOLLOW_ID_in_constant_set678 = frozenset([32, 38])
    FOLLOW_32_in_constant_set701 = frozenset([33])
    FOLLOW_33_in_constant_set703 = frozenset([34])
    FOLLOW_34_in_constant_set705 = frozenset([6])
    FOLLOW_URI_in_constant_set707 = frozenset([35])
    FOLLOW_35_in_constant_set709 = frozenset([36])
    FOLLOW_36_in_constant_set711 = frozenset([34])
    FOLLOW_34_in_constant_set713 = frozenset([7])
    FOLLOW_VERSION_in_constant_set715 = frozenset([37])
    FOLLOW_37_in_constant_set717 = frozenset([38])
    FOLLOW_38_in_constant_set740 = frozenset([39, 47, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92])
    FOLLOW_declared_const_set_ref_in_constant_set751 = frozenset([39, 47, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92])
    FOLLOW_constant_def_in_constant_set762 = frozenset([39, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92])
    FOLLOW_39_in_constant_set773 = frozenset([1, 40])
    FOLLOW_40_in_constant_set775 = frozenset([1])
    FOLLOW_47_in_declared_const_set_ref803 = frozenset([5])
    FOLLOW_ID_in_declared_const_set_ref805 = frozenset([6])
    FOLLOW_URI_in_declared_const_set_ref807 = frozenset([7])
    FOLLOW_VERSION_in_declared_const_set_ref809 = frozenset([40])
    FOLLOW_40_in_declared_const_set_ref811 = frozenset([1])
    FOLLOW_48_in_type_set835 = frozenset([5])
    FOLLOW_ID_in_type_set837 = frozenset([32, 38])
    FOLLOW_32_in_type_set849 = frozenset([33])
    FOLLOW_33_in_type_set851 = frozenset([34])
    FOLLOW_34_in_type_set853 = frozenset([6])
    FOLLOW_URI_in_type_set855 = frozenset([35])
    FOLLOW_35_in_type_set857 = frozenset([36])
    FOLLOW_36_in_type_set859 = frozenset([34])
    FOLLOW_34_in_type_set861 = frozenset([7])
    FOLLOW_VERSION_in_type_set863 = frozenset([37])
    FOLLOW_37_in_type_set865 = frozenset([38])
    FOLLOW_38_in_type_set888 = frozenset([4, 11, 15, 17, 39, 47, 93, 96, 97, 99, 100, 103, 105, 107, 108])
    FOLLOW_ml_comment_in_type_set899 = frozenset([11, 15, 39, 47, 93, 96, 97, 99, 100, 103, 105, 107, 108])
    FOLLOW_ML_COMMENT_in_type_set903 = frozenset([11, 15, 39, 47, 93, 96, 97, 99, 100, 103, 105, 107, 108])
    FOLLOW_declared_type_set_ref_in_type_set915 = frozenset([11, 15, 39, 47, 93, 96, 97, 99, 100, 103, 105, 107, 108])
    FOLLOW_type_def_in_type_set926 = frozenset([11, 15, 39, 93, 96, 97, 99, 100, 103, 105, 107, 108])
    FOLLOW_39_in_type_set937 = frozenset([1, 40])
    FOLLOW_40_in_type_set939 = frozenset([1])
    FOLLOW_49_in_message_set967 = frozenset([38])
    FOLLOW_38_in_message_set987 = frozenset([4, 5, 11, 17, 39, 50, 81])
    FOLLOW_ml_comment_in_message_set999 = frozenset([5, 11, 39, 50, 81])
    FOLLOW_ML_COMMENT_in_message_set1003 = frozenset([5, 11, 39, 50, 81])
    FOLLOW_message_def_in_message_set1027 = frozenset([5, 11, 39, 50, 81])
    FOLLOW_type_reference_in_message_set1031 = frozenset([5, 11, 39, 50, 81])
    FOLLOW_39_in_message_set1044 = frozenset([1, 40])
    FOLLOW_40_in_message_set1046 = frozenset([1])
    FOLLOW_50_in_internal_event_set1068 = frozenset([5, 38])
    FOLLOW_ID_in_internal_event_set1070 = frozenset([38])
    FOLLOW_38_in_internal_event_set1091 = frozenset([39, 81])
    FOLLOW_event_def_in_internal_event_set1110 = frozenset([39, 81])
    FOLLOW_39_in_internal_event_set1121 = frozenset([1, 40])
    FOLLOW_40_in_internal_event_set1123 = frozenset([1])
    FOLLOW_51_in_protocol_behavior1156 = frozenset([38])
    FOLLOW_38_in_protocol_behavior1158 = frozenset([4, 17, 52])
    FOLLOW_ml_comment_in_protocol_behavior1171 = frozenset([4, 17, 52])
    FOLLOW_ML_COMMENT_in_protocol_behavior1175 = frozenset([4, 17, 52])
    FOLLOW_start_state_in_protocol_behavior1184 = frozenset([53])
    FOLLOW_state_machine_in_protocol_behavior1186 = frozenset([39])
    FOLLOW_39_in_protocol_behavior1191 = frozenset([1, 40])
    FOLLOW_40_in_protocol_behavior1193 = frozenset([1])
    FOLLOW_52_in_start_state1223 = frozenset([5, 11, 50, 81])
    FOLLOW_scoped_id_in_start_state1225 = frozenset([40])
    FOLLOW_40_in_start_state1227 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_start_state1240 = frozenset([1])
    FOLLOW_ML_COMMENT_in_start_state1244 = frozenset([1])
    FOLLOW_53_in_state_machine1273 = frozenset([5, 11, 50, 81])
    FOLLOW_scoped_id_in_state_machine1275 = frozenset([38])
    FOLLOW_38_in_state_machine1277 = frozenset([4, 17, 54, 55])
    FOLLOW_ml_comment_in_state_machine1291 = frozenset([4, 17, 54, 55])
    FOLLOW_ML_COMMENT_in_state_machine1297 = frozenset([4, 17, 54, 55])
    FOLLOW_state_in_state_machine1307 = frozenset([4, 17, 39, 54, 55])
    FOLLOW_39_in_state_machine1314 = frozenset([1, 4, 17, 40])
    FOLLOW_40_in_state_machine1317 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_state_machine1323 = frozenset([1])
    FOLLOW_ML_COMMENT_in_state_machine1329 = frozenset([1])
    FOLLOW_54_in_state1363 = frozenset([55])
    FOLLOW_55_in_state1366 = frozenset([5])
    FOLLOW_ID_in_state1368 = frozenset([38])
    FOLLOW_38_in_state1370 = frozenset([4, 5, 11, 17, 39, 50, 54, 55, 56, 57, 58, 81])
    FOLLOW_ml_comment_in_state1384 = frozenset([4, 5, 11, 17, 39, 50, 54, 55, 56, 57, 58, 81])
    FOLLOW_ML_COMMENT_in_state1390 = frozenset([4, 5, 11, 17, 39, 50, 54, 55, 56, 57, 58, 81])
    FOLLOW_entry_in_state1399 = frozenset([4, 5, 11, 17, 39, 50, 54, 55, 56, 58, 81])
    FOLLOW_exit_in_state1406 = frozenset([4, 5, 11, 17, 39, 50, 54, 55, 56, 81])
    FOLLOW_transition_in_state1413 = frozenset([4, 5, 11, 17, 39, 50, 54, 55, 56, 81])
    FOLLOW_default_transition_in_state1420 = frozenset([4, 17, 39, 54, 55])
    FOLLOW_state_in_state1427 = frozenset([4, 17, 39, 54, 55])
    FOLLOW_default_state_in_state1436 = frozenset([39])
    FOLLOW_39_in_state1443 = frozenset([1, 4, 17, 40])
    FOLLOW_40_in_state1445 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_state1451 = frozenset([1])
    FOLLOW_ML_COMMENT_in_state1457 = frozenset([1])
    FOLLOW_55_in_default_state1486 = frozenset([56])
    FOLLOW_56_in_default_state1488 = frozenset([38])
    FOLLOW_38_in_default_state1490 = frozenset([4, 5, 11, 17, 39, 50, 56, 81])
    FOLLOW_ml_comment_in_default_state1504 = frozenset([5, 11, 39, 50, 56, 81])
    FOLLOW_ML_COMMENT_in_default_state1510 = frozenset([5, 11, 39, 50, 56, 81])
    FOLLOW_transition_in_default_state1522 = frozenset([5, 11, 39, 50, 56, 81])
    FOLLOW_default_transition_in_default_state1526 = frozenset([39])
    FOLLOW_39_in_default_state1536 = frozenset([1, 4, 17, 40])
    FOLLOW_40_in_default_state1538 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_default_state1543 = frozenset([1])
    FOLLOW_ML_COMMENT_in_default_state1549 = frozenset([1])
    FOLLOW_57_in_entry1581 = frozenset([38])
    FOLLOW_38_in_entry1583 = frozenset([5, 11, 39, 50, 81])
    FOLLOW_action_in_entry1595 = frozenset([5, 11, 39, 50, 81])
    FOLLOW_39_in_entry1608 = frozenset([1, 4, 17, 40])
    FOLLOW_40_in_entry1610 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_entry1616 = frozenset([1])
    FOLLOW_ML_COMMENT_in_entry1620 = frozenset([1])
    FOLLOW_58_in_exit1650 = frozenset([38])
    FOLLOW_38_in_exit1652 = frozenset([5, 11, 39, 50, 81])
    FOLLOW_action_in_exit1664 = frozenset([5, 11, 39, 50, 81])
    FOLLOW_39_in_exit1677 = frozenset([1, 4, 17, 40])
    FOLLOW_40_in_exit1679 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_exit1685 = frozenset([1])
    FOLLOW_ML_COMMENT_in_exit1689 = frozenset([1])
    FOLLOW_scoped_id_in_transition1721 = frozenset([5, 11, 32, 38, 50, 59, 60, 62, 64, 65, 66, 81])
    FOLLOW_parameters_in_transition1723 = frozenset([5, 11, 38, 50, 59, 60, 62, 64, 65, 66, 81])
    FOLLOW_guard_in_transition1726 = frozenset([5, 11, 38, 50, 59, 62, 64, 65, 66, 81])
    FOLLOW_59_in_transition1731 = frozenset([38])
    FOLLOW_next_state_in_transition1737 = frozenset([38])
    FOLLOW_38_in_transition1742 = frozenset([5, 11, 39, 50, 81])
    FOLLOW_action_in_transition1754 = frozenset([5, 11, 39, 50, 81])
    FOLLOW_39_in_transition1764 = frozenset([1, 4, 17, 40])
    FOLLOW_40_in_transition1766 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_transition1772 = frozenset([1])
    FOLLOW_ML_COMMENT_in_transition1776 = frozenset([1])
    FOLLOW_56_in_default_transition1804 = frozenset([5, 11, 32, 50, 59, 60, 62, 64, 65, 66, 81])
    FOLLOW_parameters_in_default_transition1806 = frozenset([5, 11, 50, 59, 60, 62, 64, 65, 66, 81])
    FOLLOW_guard_in_default_transition1809 = frozenset([5, 11, 50, 59, 62, 64, 65, 66, 81])
    FOLLOW_next_state_in_default_transition1814 = frozenset([38])
    FOLLOW_38_in_default_transition1816 = frozenset([5, 11, 39, 50, 81])
    FOLLOW_action_in_default_transition1828 = frozenset([5, 11, 39, 50, 81])
    FOLLOW_39_in_default_transition1838 = frozenset([1, 4, 17, 40])
    FOLLOW_40_in_default_transition1840 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_default_transition1846 = frozenset([1])
    FOLLOW_ML_COMMENT_in_default_transition1850 = frozenset([1])
    FOLLOW_60_in_guard1880 = frozenset([5, 8, 11, 13, 14, 32, 50, 72, 75, 78, 79, 81])
    FOLLOW_expression_in_guard1882 = frozenset([61])
    FOLLOW_61_in_guard1884 = frozenset([1])
    FOLLOW_simple_transition_in_next_state1910 = frozenset([1])
    FOLLOW_push_transition_in_next_state1920 = frozenset([1])
    FOLLOW_pop_transition_in_next_state1930 = frozenset([1])
    FOLLOW_internal_transition_in_next_state1940 = frozenset([1])
    FOLLOW_62_in_simple_transition1963 = frozenset([32])
    FOLLOW_32_in_simple_transition1965 = frozenset([5, 11, 50, 81])
    FOLLOW_scoped_id_in_simple_transition1969 = frozenset([37])
    FOLLOW_37_in_simple_transition1971 = frozenset([1])
    FOLLOW_scoped_id_in_push_transition2000 = frozenset([63])
    FOLLOW_59_in_push_transition2004 = frozenset([63])
    FOLLOW_63_in_push_transition2007 = frozenset([64])
    FOLLOW_64_in_push_transition2011 = frozenset([32])
    FOLLOW_32_in_push_transition2013 = frozenset([5, 11, 50, 81])
    FOLLOW_scoped_id_in_push_transition2017 = frozenset([37])
    FOLLOW_37_in_push_transition2019 = frozenset([1])
    FOLLOW_65_in_pop_transition2041 = frozenset([32])
    FOLLOW_32_in_pop_transition2043 = frozenset([5, 11, 37, 50, 81])
    FOLLOW_scoped_id_in_pop_transition2048 = frozenset([37])
    FOLLOW_scoped_id_in_pop_transition2056 = frozenset([35])
    FOLLOW_35_in_pop_transition2058 = frozenset([32])
    FOLLOW_parameters_in_pop_transition2060 = frozenset([37])
    FOLLOW_37_in_pop_transition2066 = frozenset([1])
    FOLLOW_66_in_internal_transition2084 = frozenset([32])
    FOLLOW_32_in_internal_transition2086 = frozenset([5, 11, 37, 50, 81])
    FOLLOW_scoped_id_in_internal_transition2091 = frozenset([37])
    FOLLOW_scoped_id_in_internal_transition2099 = frozenset([35])
    FOLLOW_35_in_internal_transition2101 = frozenset([32])
    FOLLOW_parameters_in_internal_transition2103 = frozenset([37])
    FOLLOW_37_in_internal_transition2109 = frozenset([1])
    FOLLOW_dotnet_assignment_in_action2130 = frozenset([40])
    FOLLOW_reference_expression_in_action2134 = frozenset([40])
    FOLLOW_40_in_action2138 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_action2142 = frozenset([1])
    FOLLOW_ML_COMMENT_in_action2146 = frozenset([1])
    FOLLOW_scoped_id_in_dotnet_assignment2169 = frozenset([34])
    FOLLOW_34_in_dotnet_assignment2171 = frozenset([5, 8, 11, 13, 14, 32, 50, 72, 75, 78, 79, 81])
    FOLLOW_expression_in_dotnet_assignment2175 = frozenset([40])
    FOLLOW_40_in_dotnet_assignment2177 = frozenset([1])
    FOLLOW_or_expression_in_expression2208 = frozenset([1])
    FOLLOW_32_in_expression2213 = frozenset([5, 8, 11, 13, 14, 50, 72, 75, 78, 79, 81])
    FOLLOW_or_expression_in_expression2217 = frozenset([37])
    FOLLOW_37_in_expression2219 = frozenset([1])
    FOLLOW_and_expression_in_or_expression2238 = frozenset([1, 67, 68])
    FOLLOW_set_in_or_expression2241 = frozenset([5, 8, 11, 13, 14, 50, 72, 75, 78, 79, 81])
    FOLLOW_and_expression_in_or_expression2249 = frozenset([1, 67, 68])
    FOLLOW_relational_expression_in_and_expression2269 = frozenset([1, 69, 70])
    FOLLOW_set_in_and_expression2272 = frozenset([5, 8, 11, 13, 14, 50, 72, 75, 78, 79, 81])
    FOLLOW_relational_expression_in_and_expression2280 = frozenset([1, 69, 70])
    FOLLOW_add_expression_in_relational_expression2300 = frozenset([1, 9])
    FOLLOW_RELATIONAL_OP_in_relational_expression2303 = frozenset([5, 8, 11, 13, 14, 50, 72, 75, 78, 79, 81])
    FOLLOW_add_expression_in_relational_expression2305 = frozenset([1, 9])
    FOLLOW_mult_expression_in_add_expression2325 = frozenset([1, 71, 72])
    FOLLOW_set_in_add_expression2328 = frozenset([5, 8, 11, 13, 14, 50, 72, 75, 78, 79, 81])
    FOLLOW_mult_expression_in_add_expression2336 = frozenset([1, 71, 72])
    FOLLOW_unary_expression_in_mult_expression2359 = frozenset([1, 63, 73, 74])
    FOLLOW_set_in_mult_expression2362 = frozenset([5, 8, 11, 13, 14, 50, 72, 75, 78, 79, 81])
    FOLLOW_unary_expression_in_mult_expression2374 = frozenset([1, 63, 73, 74])
    FOLLOW_75_in_unary_expression2398 = frozenset([5, 8, 11, 13, 14, 50, 72, 75, 78, 79, 81])
    FOLLOW_reference_expression_in_unary_expression2404 = frozenset([1])
    FOLLOW_constant_in_unary_expression2410 = frozenset([1])
    FOLLOW_single_ref_in_reference_expression2429 = frozenset([1, 76])
    FOLLOW_76_in_reference_expression2432 = frozenset([5, 11, 50, 81])
    FOLLOW_single_ref_in_reference_expression2434 = frozenset([1, 76])
    FOLLOW_keyword_in_single_ref2459 = frozenset([1, 32])
    FOLLOW_ID_in_single_ref2463 = frozenset([1, 32])
    FOLLOW_arguments_in_single_ref2469 = frozenset([1])
    FOLLOW_32_in_parameters2491 = frozenset([5, 37])
    FOLLOW_parameter_in_parameters2494 = frozenset([35, 37])
    FOLLOW_35_in_parameters2497 = frozenset([5])
    FOLLOW_parameter_in_parameters2499 = frozenset([35, 37])
    FOLLOW_37_in_parameters2505 = frozenset([1])
    FOLLOW_ID_in_parameter2525 = frozenset([77])
    FOLLOW_77_in_parameter2527 = frozenset([5])
    FOLLOW_ID_in_parameter2531 = frozenset([1, 4])
    FOLLOW_ML_COMMENT_in_parameter2534 = frozenset([1])
    FOLLOW_32_in_arguments2556 = frozenset([5, 8, 11, 13, 14, 32, 37, 50, 72, 75, 78, 79, 81])
    FOLLOW_expression_in_arguments2559 = frozenset([35, 37])
    FOLLOW_35_in_arguments2562 = frozenset([5, 8, 11, 13, 14, 32, 50, 72, 75, 78, 79, 81])
    FOLLOW_expression_in_arguments2564 = frozenset([35, 37])
    FOLLOW_37_in_arguments2570 = frozenset([1])
    FOLLOW_numeric_literal_in_constant2588 = frozenset([1])
    FOLLOW_STRINGLITERAL_in_constant2592 = frozenset([1])
    FOLLOW_78_in_constant2596 = frozenset([1])
    FOLLOW_79_in_constant2600 = frozenset([1])
    FOLLOW_simple_numeric_type_in_constant_def2636 = frozenset([5])
    FOLLOW_ID_in_constant_def2638 = frozenset([34])
    FOLLOW_34_in_constant_def2640 = frozenset([13, 14, 72])
    FOLLOW_numeric_literal_in_constant_def2651 = frozenset([10])
    FOLLOW_UNIT_in_constant_def2653 = frozenset([40])
    FOLLOW_40_in_constant_def2655 = frozenset([1, 17])
    FOLLOW_ml_comment_in_constant_def2675 = frozenset([1])
    FOLLOW_47_in_declared_type_set_ref2708 = frozenset([6])
    FOLLOW_URI_in_declared_type_set_ref2710 = frozenset([7])
    FOLLOW_VERSION_in_declared_type_set_ref2712 = frozenset([40, 80])
    FOLLOW_80_in_declared_type_set_ref2715 = frozenset([5])
    FOLLOW_ID_in_declared_type_set_ref2717 = frozenset([40])
    FOLLOW_40_in_declared_type_set_ref2721 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_declared_type_set_ref2724 = frozenset([1, 4, 17])
    FOLLOW_ML_COMMENT_in_declared_type_set_ref2728 = frozenset([1, 4, 17])
    FOLLOW_field_type_def_in_type_def2767 = frozenset([1])
    FOLLOW_container_type_def_in_type_def2775 = frozenset([1])
    FOLLOW_variant_field_type_def_in_type_def2783 = frozenset([1])
    FOLLOW_variable_format_field_type_def_in_type_def2791 = frozenset([1])
    FOLLOW_enum_type_def_in_type_def2799 = frozenset([1])
    FOLLOW_bitfield_type_def_in_type_def2807 = frozenset([1])
    FOLLOW_message_type_def_in_type_def2815 = frozenset([1])
    FOLLOW_MESSAGE_CLASS_in_message_type_def2842 = frozenset([12])
    FOLLOW_MESSAGE_CODE_in_message_type_def2844 = frozenset([5])
    FOLLOW_ID_in_message_type_def2846 = frozenset([38])
    FOLLOW_38_in_message_type_def2848 = frozenset([41])
    FOLLOW_description_in_message_type_def2869 = frozenset([4, 5, 11, 15, 17, 39, 50, 81, 103, 105, 107, 108, 109])
    FOLLOW_ml_comment_in_message_type_def2885 = frozenset([5, 11, 15, 39, 50, 81, 103, 105, 107, 108, 109])
    FOLLOW_ML_COMMENT_in_message_type_def2889 = frozenset([5, 11, 15, 39, 50, 81, 103, 105, 107, 108, 109])
    FOLLOW_container_type_def_in_message_type_def2912 = frozenset([39, 109])
    FOLLOW_type_reference_in_message_type_def2916 = frozenset([39, 109])
    FOLLOW_return_spec_in_message_type_def2928 = frozenset([39])
    FOLLOW_39_in_message_type_def2944 = frozenset([1, 40])
    FOLLOW_40_in_message_type_def2946 = frozenset([1])
    FOLLOW_MESSAGE_CLASS_in_message_def2982 = frozenset([12])
    FOLLOW_MESSAGE_CODE_in_message_def2984 = frozenset([5])
    FOLLOW_ID_in_message_def2986 = frozenset([32, 38])
    FOLLOW_attrs_in_message_def2988 = frozenset([38])
    FOLLOW_38_in_message_def2991 = frozenset([41])
    FOLLOW_description_in_message_def3012 = frozenset([4, 5, 11, 17, 39, 50, 81, 109])
    FOLLOW_ml_comment_in_message_def3028 = frozenset([5, 11, 39, 50, 81, 109])
    FOLLOW_ML_COMMENT_in_message_def3032 = frozenset([5, 11, 39, 50, 81, 109])
    FOLLOW_type_reference_in_message_def3055 = frozenset([39, 109])
    FOLLOW_return_spec_in_message_def3067 = frozenset([39])
    FOLLOW_39_in_message_def3083 = frozenset([1, 40])
    FOLLOW_40_in_message_def3085 = frozenset([1])
    FOLLOW_81_in_event_def3122 = frozenset([5])
    FOLLOW_ID_in_event_def3124 = frozenset([38])
    FOLLOW_38_in_event_def3126 = frozenset([5, 11, 39, 41, 50, 81, 109])
    FOLLOW_description_in_event_def3147 = frozenset([5, 11, 39, 50, 81, 109])
    FOLLOW_type_reference_in_event_def3168 = frozenset([39, 109])
    FOLLOW_return_spec_in_event_def3179 = frozenset([39])
    FOLLOW_39_in_event_def3195 = frozenset([1, 4, 17, 40])
    FOLLOW_40_in_event_def3197 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_event_def3203 = frozenset([1])
    FOLLOW_ML_COMMENT_in_event_def3207 = frozenset([1])
    FOLLOW_set_in_simple_numeric_type3237 = frozenset([1])
    FOLLOW_72_in_numeric_literal3340 = frozenset([13, 14])
    FOLLOW_set_in_numeric_literal3343 = frozenset([1])
    FOLLOW_93_in_value_set_type_def3367 = frozenset([5])
    FOLLOW_ID_in_value_set_type_def3369 = frozenset([32, 38])
    FOLLOW_attrs_in_value_set_type_def3381 = frozenset([38])
    FOLLOW_38_in_value_set_type_def3384 = frozenset([5, 39])
    FOLLOW_value_spec_in_value_set_type_def3390 = frozenset([5, 39])
    FOLLOW_39_in_value_set_type_def3397 = frozenset([1, 17, 40])
    FOLLOW_40_in_value_set_type_def3399 = frozenset([1, 17])
    FOLLOW_ml_comment_in_value_set_type_def3402 = frozenset([1])
    FOLLOW_ITEM_CARDINALITY_in_field_def3424 = frozenset([5, 11, 50, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 94, 95])
    FOLLOW_string_def_in_field_def3438 = frozenset([34])
    FOLLOW_simple_numeric_type_in_field_def3448 = frozenset([5])
    FOLLOW_ID_in_field_def3453 = frozenset([10])
    FOLLOW_UNIT_in_field_def3455 = frozenset([32, 34, 38, 60, 101])
    FOLLOW_value_range_set_in_field_def3459 = frozenset([34])
    FOLLOW_scaled_range_def_in_field_def3463 = frozenset([34])
    FOLLOW_value_set_def_in_field_def3467 = frozenset([34])
    FOLLOW_scoped_id_in_field_def3495 = frozenset([5])
    FOLLOW_ID_in_field_def3500 = frozenset([34])
    FOLLOW_34_in_field_def3524 = frozenset([13])
    FOLLOW_INTLITERAL_in_field_def3526 = frozenset([40])
    FOLLOW_40_in_field_def3528 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_field_def3532 = frozenset([1])
    FOLLOW_ML_COMMENT_in_field_def3536 = frozenset([1])
    FOLLOW_94_in_string_def3567 = frozenset([5])
    FOLLOW_ID_in_string_def3571 = frozenset([60])
    FOLLOW_60_in_string_def3573 = frozenset([5, 13])
    FOLLOW_set_in_string_def3577 = frozenset([61])
    FOLLOW_61_in_string_def3587 = frozenset([1])
    FOLLOW_95_in_string_def3605 = frozenset([5])
    FOLLOW_ID_in_string_def3609 = frozenset([60])
    FOLLOW_60_in_string_def3611 = frozenset([5, 13])
    FOLLOW_set_in_string_def3615 = frozenset([35])
    FOLLOW_35_in_string_def3625 = frozenset([5, 13])
    FOLLOW_set_in_string_def3629 = frozenset([61])
    FOLLOW_61_in_string_def3639 = frozenset([1])
    FOLLOW_value_range_set_in_constraint_ref3678 = frozenset([1])
    FOLLOW_scaled_range_def_in_constraint_ref3686 = frozenset([1])
    FOLLOW_value_set_def_in_constraint_ref3695 = frozenset([1])
    FOLLOW_96_in_field_type_def3722 = frozenset([82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 94, 95])
    FOLLOW_string_def_in_field_type_def3740 = frozenset([40])
    FOLLOW_simple_numeric_type_in_field_type_def3751 = frozenset([5])
    FOLLOW_ID_in_field_type_def3755 = frozenset([10])
    FOLLOW_UNIT_in_field_type_def3757 = frozenset([32, 38, 40, 60, 101])
    FOLLOW_value_range_set_in_field_type_def3770 = frozenset([40])
    FOLLOW_scaled_range_def_in_field_type_def3774 = frozenset([40])
    FOLLOW_value_set_def_in_field_type_def3779 = frozenset([40])
    FOLLOW_40_in_field_type_def3810 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_field_type_def3814 = frozenset([1])
    FOLLOW_ML_COMMENT_in_field_type_def3818 = frozenset([1])
    FOLLOW_ITEM_CARDINALITY_in_variant_field_type_def3855 = frozenset([97])
    FOLLOW_97_in_variant_field_type_def3860 = frozenset([5])
    FOLLOW_ID_in_variant_field_type_def3862 = frozenset([38])
    FOLLOW_38_in_variant_field_type_def3864 = frozenset([4, 17, 98])
    FOLLOW_ml_comment_in_variant_field_type_def3878 = frozenset([4, 17, 98])
    FOLLOW_ML_COMMENT_in_variant_field_type_def3885 = frozenset([4, 17, 98])
    FOLLOW_tagged_type_units_enum_def_in_variant_field_type_def3896 = frozenset([4, 17, 39, 98])
    FOLLOW_39_in_variant_field_type_def3905 = frozenset([34, 40])
    FOLLOW_34_in_variant_field_type_def3913 = frozenset([13])
    FOLLOW_INTLITERAL_in_variant_field_type_def3915 = frozenset([40])
    FOLLOW_40_in_variant_field_type_def3920 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_variant_field_type_def3924 = frozenset([1])
    FOLLOW_ML_COMMENT_in_variant_field_type_def3931 = frozenset([1])
    FOLLOW_98_in_tagged_type_units_enum_def3958 = frozenset([5, 13])
    FOLLOW_INTLITERAL_in_tagged_type_units_enum_def3964 = frozenset([77])
    FOLLOW_ID_in_tagged_type_units_enum_def3971 = frozenset([77])
    FOLLOW_77_in_tagged_type_units_enum_def3990 = frozenset([5])
    FOLLOW_ID_in_tagged_type_units_enum_def3994 = frozenset([82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92])
    FOLLOW_simple_numeric_type_in_tagged_type_units_enum_def3996 = frozenset([10])
    FOLLOW_UNIT_in_tagged_type_units_enum_def3998 = frozenset([5, 11, 38, 40, 50, 81, 101])
    FOLLOW_value_set_def_in_tagged_type_units_enum_def4012 = frozenset([40])
    FOLLOW_declared_value_set_def_in_tagged_type_units_enum_def4016 = frozenset([40])
    FOLLOW_scaled_range_def_in_tagged_type_units_enum_def4020 = frozenset([40])
    FOLLOW_40_in_tagged_type_units_enum_def4035 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_tagged_type_units_enum_def4038 = frozenset([1])
    FOLLOW_ML_COMMENT_in_tagged_type_units_enum_def4042 = frozenset([1])
    FOLLOW_scoped_id_in_declared_value_set_def4068 = frozenset([5])
    FOLLOW_ID_in_declared_value_set_def4070 = frozenset([40])
    FOLLOW_40_in_declared_value_set_def4072 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_declared_value_set_def4086 = frozenset([1])
    FOLLOW_ML_COMMENT_in_declared_value_set_def4090 = frozenset([1])
    FOLLOW_ITEM_CARDINALITY_in_variable_format_field_type_def4120 = frozenset([99])
    FOLLOW_99_in_variable_format_field_type_def4125 = frozenset([5])
    FOLLOW_ID_in_variable_format_field_type_def4127 = frozenset([38])
    FOLLOW_38_in_variable_format_field_type_def4129 = frozenset([4, 17, 82, 83, 85])
    FOLLOW_ml_comment_in_variable_format_field_type_def4140 = frozenset([82, 83, 85])
    FOLLOW_ML_COMMENT_in_variable_format_field_type_def4147 = frozenset([82, 83, 85])
    FOLLOW_set_in_variable_format_field_type_def4163 = frozenset([98])
    FOLLOW_98_in_variable_format_field_type_def4177 = frozenset([40])
    FOLLOW_40_in_variable_format_field_type_def4179 = frozenset([98])
    FOLLOW_format_enum_def_in_variable_format_field_type_def4201 = frozenset([39, 98])
    FOLLOW_39_in_variable_format_field_type_def4213 = frozenset([1, 4, 17, 34, 40])
    FOLLOW_34_in_variable_format_field_type_def4218 = frozenset([13])
    FOLLOW_INTLITERAL_in_variable_format_field_type_def4220 = frozenset([40])
    FOLLOW_40_in_variable_format_field_type_def4222 = frozenset([1, 4, 17])
    FOLLOW_40_in_variable_format_field_type_def4227 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_variable_format_field_type_def4240 = frozenset([1])
    FOLLOW_ML_COMMENT_in_variable_format_field_type_def4247 = frozenset([1])
    FOLLOW_98_in_format_enum_def4278 = frozenset([5, 13])
    FOLLOW_INTLITERAL_in_format_enum_def4284 = frozenset([77])
    FOLLOW_ID_in_format_enum_def4291 = frozenset([77])
    FOLLOW_77_in_format_enum_def4295 = frozenset([8, 16])
    FOLLOW_FIELD_FORMAT_in_format_enum_def4301 = frozenset([40])
    FOLLOW_STRINGLITERAL_in_format_enum_def4308 = frozenset([40])
    FOLLOW_40_in_format_enum_def4312 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_format_enum_def4320 = frozenset([1])
    FOLLOW_ML_COMMENT_in_format_enum_def4324 = frozenset([1])
    FOLLOW_93_in_enum_type_def4364 = frozenset([82, 83, 85])
    FOLLOW_set_in_enum_type_def4368 = frozenset([5])
    FOLLOW_ID_in_enum_type_def4382 = frozenset([10])
    FOLLOW_UNIT_in_enum_type_def4384 = frozenset([38])
    FOLLOW_38_in_enum_type_def4405 = frozenset([4, 5, 17])
    FOLLOW_ml_comment_in_enum_type_def4409 = frozenset([5])
    FOLLOW_ML_COMMENT_in_enum_type_def4415 = frozenset([5])
    FOLLOW_value_spec_in_enum_type_def4429 = frozenset([5, 39])
    FOLLOW_39_in_enum_type_def4440 = frozenset([1, 4, 17, 40])
    FOLLOW_40_in_enum_type_def4442 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_enum_type_def4447 = frozenset([1])
    FOLLOW_ML_COMMENT_in_enum_type_def4453 = frozenset([1])
    FOLLOW_38_in_value_set_def4495 = frozenset([5, 39])
    FOLLOW_value_spec_in_value_set_def4501 = frozenset([5, 39])
    FOLLOW_39_in_value_set_def4508 = frozenset([1, 4, 17])
    FOLLOW_ML_COMMENT_in_value_set_def4512 = frozenset([1])
    FOLLOW_ml_comment_in_value_set_def4516 = frozenset([1])
    FOLLOW_ITEM_CARDINALITY_in_bitfield_type_def4542 = frozenset([100])
    FOLLOW_100_in_bitfield_type_def4545 = frozenset([82, 83, 85, 86])
    FOLLOW_set_in_bitfield_type_def4553 = frozenset([5])
    FOLLOW_ID_in_bitfield_type_def4571 = frozenset([4, 17, 38])
    FOLLOW_ml_comment_in_bitfield_type_def4593 = frozenset([38])
    FOLLOW_ML_COMMENT_in_bitfield_type_def4599 = frozenset([38])
    FOLLOW_38_in_bitfield_type_def4612 = frozenset([5, 39])
    FOLLOW_sub_field_in_bitfield_type_def4624 = frozenset([5, 39])
    FOLLOW_39_in_bitfield_type_def4636 = frozenset([1, 4, 17, 34, 40])
    FOLLOW_34_in_bitfield_type_def4642 = frozenset([13])
    FOLLOW_INTLITERAL_in_bitfield_type_def4644 = frozenset([40])
    FOLLOW_40_in_bitfield_type_def4646 = frozenset([1, 4, 17])
    FOLLOW_40_in_bitfield_type_def4652 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_bitfield_type_def4667 = frozenset([1])
    FOLLOW_ML_COMMENT_in_bitfield_type_def4673 = frozenset([1])
    FOLLOW_value_range_in_value_range_set4713 = frozenset([1])
    FOLLOW_32_in_value_range4752 = frozenset([5, 11, 13, 14, 50, 72, 81])
    FOLLOW_60_in_value_range4758 = frozenset([5, 11, 13, 14, 50, 72, 81])
    FOLLOW_numeric_literal_in_value_range4775 = frozenset([35])
    FOLLOW_scoped_id_in_value_range4792 = frozenset([35])
    FOLLOW_35_in_value_range4806 = frozenset([5, 11, 13, 14, 50, 72, 81])
    FOLLOW_numeric_literal_in_value_range4819 = frozenset([37, 61])
    FOLLOW_scoped_id_in_value_range4836 = frozenset([37, 61])
    FOLLOW_37_in_value_range4852 = frozenset([1])
    FOLLOW_61_in_value_range4858 = frozenset([1])
    FOLLOW_ID_in_value_spec4894 = frozenset([34])
    FOLLOW_34_in_value_spec4896 = frozenset([13, 32, 60])
    FOLLOW_INTLITERAL_in_value_spec4910 = frozenset([40])
    FOLLOW_value_range_in_value_spec4914 = frozenset([40])
    FOLLOW_40_in_value_spec4928 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_value_spec4931 = frozenset([1])
    FOLLOW_ML_COMMENT_in_value_spec4935 = frozenset([1])
    FOLLOW_101_in_scaled_range_def4970 = frozenset([5, 11, 13, 14, 50, 72, 81])
    FOLLOW_numeric_literal_in_scaled_range_def4983 = frozenset([35])
    FOLLOW_scoped_id_in_scaled_range_def5000 = frozenset([35])
    FOLLOW_35_in_scaled_range_def5014 = frozenset([5, 11, 13, 14, 50, 72, 81])
    FOLLOW_numeric_literal_in_scaled_range_def5027 = frozenset([102])
    FOLLOW_scoped_id_in_scaled_range_def5044 = frozenset([102])
    FOLLOW_102_in_scaled_range_def5058 = frozenset([1])
    FOLLOW_ID_in_sub_field5091 = frozenset([60])
    FOLLOW_60_in_sub_field5093 = frozenset([13])
    FOLLOW_INTLITERAL_in_sub_field5097 = frozenset([77])
    FOLLOW_77_in_sub_field5099 = frozenset([13])
    FOLLOW_INTLITERAL_in_sub_field5103 = frozenset([61])
    FOLLOW_61_in_sub_field5105 = frozenset([5, 10, 32, 60])
    FOLLOW_value_range_set_in_sub_field5122 = frozenset([10])
    FOLLOW_ID_in_sub_field5128 = frozenset([10])
    FOLLOW_UNIT_in_sub_field5137 = frozenset([40])
    FOLLOW_40_in_sub_field5139 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_sub_field5145 = frozenset([1])
    FOLLOW_ML_COMMENT_in_sub_field5149 = frozenset([1])
    FOLLOW_list_type_def_in_container_type_def5186 = frozenset([1])
    FOLLOW_variant_type_def_in_container_type_def5194 = frozenset([1])
    FOLLOW_sequence_type_def_in_container_type_def5202 = frozenset([1])
    FOLLOW_record_type_def_in_container_type_def5210 = frozenset([1])
    FOLLOW_list_type_def_in_indexed_container_type_def5246 = frozenset([34])
    FOLLOW_variant_type_def_in_indexed_container_type_def5258 = frozenset([34])
    FOLLOW_sequence_type_def_in_indexed_container_type_def5270 = frozenset([34])
    FOLLOW_record_type_def_in_indexed_container_type_def5282 = frozenset([34])
    FOLLOW_34_in_indexed_container_type_def5285 = frozenset([13])
    FOLLOW_INTLITERAL_in_indexed_container_type_def5287 = frozenset([40])
    FOLLOW_40_in_indexed_container_type_def5289 = frozenset([1, 17])
    FOLLOW_SL_COMMENT_in_indexed_container_type_def5291 = frozenset([1])
    FOLLOW_ITEM_CARDINALITY_in_list_type_def5328 = frozenset([103])
    FOLLOW_103_in_list_type_def5331 = frozenset([5])
    FOLLOW_ID_in_list_type_def5335 = frozenset([38])
    FOLLOW_38_in_list_type_def5337 = frozenset([4, 17, 82, 83, 85])
    FOLLOW_ml_comment_in_list_type_def5341 = frozenset([82, 83, 85])
    FOLLOW_ML_COMMENT_in_list_type_def5347 = frozenset([82, 83, 85])
    FOLLOW_set_in_list_type_def5367 = frozenset([5])
    FOLLOW_ID_in_list_type_def5383 = frozenset([60])
    FOLLOW_60_in_list_type_def5389 = frozenset([13, 14, 72])
    FOLLOW_numeric_literal_in_list_type_def5397 = frozenset([35])
    FOLLOW_35_in_list_type_def5403 = frozenset([13, 14, 72])
    FOLLOW_numeric_literal_in_list_type_def5411 = frozenset([61])
    FOLLOW_61_in_list_type_def5417 = frozenset([40])
    FOLLOW_40_in_list_type_def5419 = frozenset([4, 17, 104])
    FOLLOW_ml_comment_in_list_type_def5423 = frozenset([104])
    FOLLOW_ML_COMMENT_in_list_type_def5429 = frozenset([104])
    FOLLOW_104_in_list_type_def5446 = frozenset([5, 11, 15, 50, 81, 103, 105, 107, 108])
    FOLLOW_container_type_def_in_list_type_def5450 = frozenset([39])
    FOLLOW_type_reference_in_list_type_def5454 = frozenset([39])
    FOLLOW_39_in_list_type_def5464 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_list_type_def5467 = frozenset([1])
    FOLLOW_ML_COMMENT_in_list_type_def5473 = frozenset([1])
    FOLLOW_ITEM_CARDINALITY_in_variant_type_def5506 = frozenset([105])
    FOLLOW_105_in_variant_type_def5509 = frozenset([5])
    FOLLOW_ID_in_variant_type_def5513 = frozenset([38])
    FOLLOW_38_in_variant_type_def5515 = frozenset([17, 41, 82, 83, 85])
    FOLLOW_SL_COMMENT_in_variant_type_def5535 = frozenset([41, 82, 83, 85])
    FOLLOW_description_in_variant_type_def5546 = frozenset([82, 83, 85])
    FOLLOW_set_in_variant_type_def5559 = frozenset([106])
    FOLLOW_106_in_variant_type_def5571 = frozenset([60])
    FOLLOW_60_in_variant_type_def5573 = frozenset([5, 13, 14, 72])
    FOLLOW_numeric_literal_in_variant_type_def5580 = frozenset([35])
    FOLLOW_ID_in_variant_type_def5584 = frozenset([35])
    FOLLOW_35_in_variant_type_def5617 = frozenset([5, 13, 14, 72])
    FOLLOW_numeric_literal_in_variant_type_def5624 = frozenset([61])
    FOLLOW_ID_in_variant_type_def5628 = frozenset([61])
    FOLLOW_61_in_variant_type_def5632 = frozenset([40])
    FOLLOW_40_in_variant_type_def5634 = frozenset([4, 17, 39, 106])
    FOLLOW_ml_comment_in_variant_type_def5646 = frozenset([39, 106])
    FOLLOW_ML_COMMENT_in_variant_type_def5652 = frozenset([39, 106])
    FOLLOW_tagged_item_def_in_variant_type_def5672 = frozenset([39, 106])
    FOLLOW_39_in_variant_type_def5685 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_variant_type_def5688 = frozenset([1])
    FOLLOW_ML_COMMENT_in_variant_type_def5694 = frozenset([1])
    FOLLOW_106_in_tagged_item_def5719 = frozenset([5, 13])
    FOLLOW_set_in_tagged_item_def5725 = frozenset([77])
    FOLLOW_77_in_tagged_item_def5748 = frozenset([5, 11, 15, 50, 81, 103, 105, 107, 108])
    FOLLOW_container_type_def_in_tagged_item_def5751 = frozenset([1])
    FOLLOW_type_reference_in_tagged_item_def5755 = frozenset([1])
    FOLLOW_ITEM_CARDINALITY_in_sequence_type_def5783 = frozenset([107])
    FOLLOW_107_in_sequence_type_def5786 = frozenset([5])
    FOLLOW_ID_in_sequence_type_def5790 = frozenset([38])
    FOLLOW_38_in_sequence_type_def5806 = frozenset([4, 15, 17, 103, 105, 107, 108])
    FOLLOW_ml_comment_in_sequence_type_def5813 = frozenset([4, 15, 17, 103, 105, 107, 108])
    FOLLOW_ML_COMMENT_in_sequence_type_def5819 = frozenset([4, 15, 17, 103, 105, 107, 108])
    FOLLOW_indexed_container_type_def_in_sequence_type_def5828 = frozenset([4, 15, 17, 39, 103, 105, 107, 108])
    FOLLOW_indexed_type_reference_in_sequence_type_def5832 = frozenset([4, 15, 17, 39, 103, 105, 107, 108])
    FOLLOW_39_in_sequence_type_def5841 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_sequence_type_def5844 = frozenset([1])
    FOLLOW_ML_COMMENT_in_sequence_type_def5850 = frozenset([1])
    FOLLOW_ITEM_CARDINALITY_in_record_type_def5878 = frozenset([108])
    FOLLOW_108_in_record_type_def5883 = frozenset([5])
    FOLLOW_ID_in_record_type_def5885 = frozenset([38])
    FOLLOW_38_in_record_type_def5898 = frozenset([4, 15, 17, 97, 99, 100])
    FOLLOW_ml_comment_in_record_type_def5902 = frozenset([15, 97, 99, 100])
    FOLLOW_ML_COMMENT_in_record_type_def5909 = frozenset([15, 97, 99, 100])
    FOLLOW_field_def_in_record_type_def5925 = frozenset([15, 39, 97, 99, 100])
    FOLLOW_bitfield_type_def_in_record_type_def5940 = frozenset([15, 39, 97, 99, 100])
    FOLLOW_variant_field_type_def_in_record_type_def5955 = frozenset([15, 39, 97, 99, 100])
    FOLLOW_variable_format_field_type_def_in_record_type_def5970 = frozenset([15, 39, 97, 99, 100])
    FOLLOW_39_in_record_type_def5980 = frozenset([1, 4, 17, 40])
    FOLLOW_40_in_record_type_def5982 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_record_type_def5987 = frozenset([1])
    FOLLOW_ML_COMMENT_in_record_type_def5994 = frozenset([1])
    FOLLOW_scoped_id_in_type_reference6025 = frozenset([5])
    FOLLOW_ID_in_type_reference6027 = frozenset([40])
    FOLLOW_40_in_type_reference6029 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_type_reference6042 = frozenset([1])
    FOLLOW_ML_COMMENT_in_type_reference6046 = frozenset([1])
    FOLLOW_ITEM_CARDINALITY_in_indexed_type_reference6079 = frozenset([5, 11, 50, 81])
    FOLLOW_scoped_id_in_indexed_type_reference6081 = frozenset([5])
    FOLLOW_ID_in_indexed_type_reference6083 = frozenset([34])
    FOLLOW_34_in_indexed_type_reference6085 = frozenset([13])
    FOLLOW_INTLITERAL_in_indexed_type_reference6087 = frozenset([40])
    FOLLOW_40_in_indexed_type_reference6089 = frozenset([1, 4, 17])
    FOLLOW_ml_comment_in_indexed_type_reference6092 = frozenset([1])
    FOLLOW_ML_COMMENT_in_indexed_type_reference6096 = frozenset([1])
    FOLLOW_ITEM_CARDINALITY_in_field_type_reference6133 = frozenset([5, 11, 50, 81])
    FOLLOW_scoped_id_in_field_type_reference6135 = frozenset([5])
    FOLLOW_ID_in_field_type_reference6137 = frozenset([34])
    FOLLOW_34_in_field_type_reference6139 = frozenset([13])
    FOLLOW_INTLITERAL_in_field_type_reference6141 = frozenset([40])
    FOLLOW_40_in_field_type_reference6143 = frozenset([1, 17])
    FOLLOW_SL_COMMENT_in_field_type_reference6145 = frozenset([1])
    FOLLOW_109_in_return_spec6181 = frozenset([5, 11, 50, 81])
    FOLLOW_scoped_id_in_return_spec6183 = frozenset([5])
    FOLLOW_ID_in_return_spec6185 = frozenset([40])
    FOLLOW_40_in_return_spec6187 = frozenset([1])
    FOLLOW_scoped_id_in_array_type_reference6213 = frozenset([5])
    FOLLOW_ID_in_array_type_reference6215 = frozenset([60])
    FOLLOW_60_in_array_type_reference6217 = frozenset([5, 11, 50, 81])
    FOLLOW_scoped_id_in_array_type_reference6219 = frozenset([61])
    FOLLOW_61_in_array_type_reference6221 = frozenset([40])
    FOLLOW_40_in_array_type_reference6223 = frozenset([1, 17])
    FOLLOW_SL_COMMENT_in_array_type_reference6225 = frozenset([1])
    FOLLOW_32_in_attrs6251 = frozenset([5, 37])
    FOLLOW_attr_assignment_list_in_attrs6253 = frozenset([5, 37])
    FOLLOW_37_in_attrs6256 = frozenset([1])
    FOLLOW_attr_assignment_in_attr_assignment_list6281 = frozenset([1, 35])
    FOLLOW_35_in_attr_assignment_list6284 = frozenset([5])
    FOLLOW_attr_assignment_in_attr_assignment_list6286 = frozenset([1, 35])
    FOLLOW_ID_in_attr_assignment6314 = frozenset([34])
    FOLLOW_34_in_attr_assignment6316 = frozenset([8])
    FOLLOW_STRINGLITERAL_in_attr_assignment6318 = frozenset([1])
    FOLLOW_SL_COMMENT_in_ml_comment6345 = frozenset([1, 17])
    FOLLOW_set_in_keyword0 = frozenset([1])
    FOLLOW_keyword_in_scoped_id6403 = frozenset([1, 76])
    FOLLOW_ID_in_scoped_id6407 = frozenset([1, 76])
    FOLLOW_76_in_scoped_id6412 = frozenset([5, 50, 81])
    FOLLOW_keyword_in_scoped_id6415 = frozenset([1, 76])
    FOLLOW_ID_in_scoped_id6419 = frozenset([1, 76])



def main(argv, stdin=sys.stdin, stdout=sys.stdout, stderr=sys.stderr):
    from antlr3.main import ParserMain
    main = ParserMain("jaus2jsidlLexer", jaus2jsidlParser)
    main.stdin = stdin
    main.stdout = stdout
    main.stderr = stderr
    main.execute(argv)


if __name__ == '__main__':
    main(sys.argv)
