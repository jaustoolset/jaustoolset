lexer grammar InternalCjsidl;
@header {
package org.jts.eclipse.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;
}

T19 : 'uint8' ;
T20 : 'uint16' ;
T21 : 'uint32' ;
T22 : 'uint64' ;
T23 : '->' ;
T24 : 'next' ;
T25 : '=' ;
T26 : '!=' ;
T27 : '&&' ;
T28 : '||' ;
T29 : 'int8' ;
T30 : 'int16' ;
T31 : 'int32' ;
T32 : 'int64' ;
T33 : 'float' ;
T34 : 'double' ;
T35 : '(' ;
T36 : '[' ;
T37 : ')' ;
T38 : ']' ;
T39 : 'floor' ;
T40 : 'ceiling' ;
T41 : 'round' ;
T42 : 'AU' ;
T43 : 'BMP' ;
T44 : 'JPEG' ;
T45 : 'MJPEG' ;
T46 : 'MPEG-1' ;
T47 : 'MPEG-2' ;
T48 : 'MP2' ;
T49 : 'MP3' ;
T50 : 'MP4' ;
T51 : 'RAW' ;
T52 : 'WAV' ;
T53 : 'JAUS_MESSAGE' ;
T54 : 'XML' ;
T55 : 'RNC' ;
T56 : 'RNG' ;
T57 : 'XSD' ;
T58 : 'User_defined' ;
T59 : 'meter' ;
T60 : 'kilogram' ;
T61 : 'second' ;
T62 : 'ampere' ;
T63 : 'kelvin' ;
T64 : 'mole' ;
T65 : 'candela' ;
T66 : 'one' ;
T67 : 'square_meter' ;
T68 : 'cubic_meter' ;
T69 : 'meter_per_second' ;
T70 : 'meter_per_second_squared' ;
T71 : 'reciprocal_meter' ;
T72 : 'kilogram_per_cubic_meter' ;
T73 : 'cubic_meter_per_kilogram' ;
T74 : 'ampere_per_square_meter' ;
T75 : 'ampere_per_meter' ;
T76 : 'mole_per_cubic_meter' ;
T77 : 'candela_per_square_meter' ;
T78 : 'radian' ;
T79 : 'steradian' ;
T80 : 'hertz' ;
T81 : 'newton' ;
T82 : 'pascal' ;
T83 : 'joule' ;
T84 : 'watt' ;
T85 : 'coulomb' ;
T86 : 'volt' ;
T87 : 'farad' ;
T88 : 'ohm' ;
T89 : 'siemens' ;
T90 : 'weber' ;
T91 : 'tesla' ;
T92 : 'henry' ;
T93 : 'degree_Celsius' ;
T94 : 'lumen' ;
T95 : 'lux' ;
T96 : 'becquerel' ;
T97 : 'sievert' ;
T98 : 'katal' ;
T99 : 'pascal_second' ;
T100 : 'newton_meter' ;
T101 : 'newton_per_meter' ;
T102 : 'radian_per_second' ;
T103 : 'radian_per_second_squared' ;
T104 : 'watt_per_square_meter' ;
T105 : 'joule_per_kelvin' ;
T106 : 'joule_per_kilogram' ;
T107 : 'watt_per_meter_kelvin' ;
T108 : 'joule_per_cubic_meter' ;
T109 : 'volt_per_meter' ;
T110 : 'coulomb_per_cubic_meter' ;
T111 : 'coulomb_per_square_meter' ;
T112 : 'farad_per_meter' ;
T113 : 'henry_per_meter' ;
T114 : 'joule_per_mole' ;
T115 : 'joule_per_mole_kelvin' ;
T116 : 'coulomb_per_kilogram' ;
T117 : 'gray_per_second' ;
T118 : 'watt_per_square_meter_steradian' ;
T119 : 'katal_per_cubic_meter' ;
T120 : 'minute' ;
T121 : 'hour' ;
T122 : 'day' ;
T123 : 'degree' ;
T124 : 'liter' ;
T125 : 'metric_ton' ;
T126 : 'neper' ;
T127 : 'bel' ;
T128 : 'nautical_mile' ;
T129 : 'knot' ;
T130 : 'are' ;
T131 : 'hectare' ;
T132 : 'bar' ;
T133 : 'angstrom' ;
T134 : 'barn' ;
T135 : 'curie' ;
T136 : 'roentgen' ;
T137 : 'rad' ;
T138 : 'rem' ;
T139 : 'service' ;
T140 : 'id' ;
T141 : ',' ;
T142 : 'version' ;
T143 : '{' ;
T144 : '}' ;
T145 : ';' ;
T146 : 'description' ;
T147 : 'assumptions' ;
T148 : 'references' ;
T149 : 'inherits_from' ;
T150 : 'client_of' ;
T151 : 'import' ;
T152 : 'as' ;
T153 : 'constants' ;
T154 : 'using' ;
T155 : 'typeset' ;
T156 : 'messages' ;
T157 : 'input' ;
T158 : 'output' ;
T159 : 'eventset' ;
T160 : 'event' ;
T161 : 'message' ;
T162 : 'protocol' ;
T163 : 'start' ;
T164 : '.' ;
T165 : 'state_machine' ;
T166 : 'state' ;
T167 : 'default' ;
T168 : 'entry' ;
T169 : 'exit' ;
T170 : 'transition' ;
T171 : 'popto' ;
T172 : 'secondary' ;
T173 : 'guard' ;
T174 : ':' ;
T175 : 'actions' ;
T176 : 'send' ;
T177 : 'header' ;
T178 : 'body' ;
T179 : 'footer' ;
T180 : '-' ;
T181 : 'string' ;
T182 : 'vstring' ;
T183 : 'field' ;
T184 : 'variable_field' ;
T185 : 'vfield' ;
T186 : 'tag' ;
T187 : 'variable_format_field' ;
T188 : 'bit_field' ;
T189 : '<' ;
T190 : '>' ;
T191 : 'list' ;
T192 : 'variant' ;
T193 : 'vtag' ;
T194 : 'sequence' ;
T195 : 'record' ;
T196 : 'stateless' ;
T197 : 'initial' ;
T198 : 'internal' ;
T199 : 'simple' ;
T200 : 'push' ;
T201 : 'pop' ;
T202 : '!' ;
T203 : 'command' ;
T204 : 'optional' ;
T205 : 'offset' ;

// $ANTLR src "../org.jts.eclipse.ui/src-gen/org/jts/eclipse/ui/contentassist/antlr/internal/InternalCjsidl.g" 33899
RULE_VERSION : RULE_DECIMAL ('.' RULE_INT)+;

// $ANTLR src "../org.jts.eclipse.ui/src-gen/org/jts/eclipse/ui/contentassist/antlr/internal/InternalCjsidl.g" 33901
RULE_DECIMAL : (RULE_INT '.' RULE_INT|'.' RULE_INT);

// $ANTLR src "../org.jts.eclipse.ui/src-gen/org/jts/eclipse/ui/contentassist/antlr/internal/InternalCjsidl.g" 33903
RULE_MESSAGE_CODE : ('0x'|'0X') ('0'..'9'|'a'..'f'|'A'..'F') ('0'..'9'|'a'..'f'|'A'..'F') ('0'..'9'|'a'..'f'|'A'..'F') ('0'..'9'|'a'..'f'|'A'..'F');

// $ANTLR src "../org.jts.eclipse.ui/src-gen/org/jts/eclipse/ui/contentassist/antlr/internal/InternalCjsidl.g" 33905
RULE_INTLITERAL : ('0'|'1'..'9' ('0'..'9')*);

// $ANTLR src "../org.jts.eclipse.ui/src-gen/org/jts/eclipse/ui/contentassist/antlr/internal/InternalCjsidl.g" 33907
RULE_NONINTNUMBER : '-'? RULE_DECIMAL (('e'|'E') ('+'|'-')? ('0'..'9')+)?;

// $ANTLR src "../org.jts.eclipse.ui/src-gen/org/jts/eclipse/ui/contentassist/antlr/internal/InternalCjsidl.g" 33909
RULE_ESC : '\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\'|'0'..'3' '0'..'7' '0'..'7'|'0'..'7' '0'..'7'|'0'..'7');

// $ANTLR src "../org.jts.eclipse.ui/src-gen/org/jts/eclipse/ui/contentassist/antlr/internal/InternalCjsidl.g" 33911
RULE_STRINGLITERAL : ('"' (RULE_ESC|~(('\\'|'"')))* '"'|'\'' (RULE_ESC|~(('\\'|'\'')))* '\'');

// $ANTLR src "../org.jts.eclipse.ui/src-gen/org/jts/eclipse/ui/contentassist/antlr/internal/InternalCjsidl.g" 33913
RULE_ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.jts.eclipse.ui/src-gen/org/jts/eclipse/ui/contentassist/antlr/internal/InternalCjsidl.g" 33915
RULE_INTERPRETATION : '##' ( options {greedy=false;} : . )*'##';

// $ANTLR src "../org.jts.eclipse.ui/src-gen/org/jts/eclipse/ui/contentassist/antlr/internal/InternalCjsidl.g" 33917
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.jts.eclipse.ui/src-gen/org/jts/eclipse/ui/contentassist/antlr/internal/InternalCjsidl.g" 33919
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../org.jts.eclipse.ui/src-gen/org/jts/eclipse/ui/contentassist/antlr/internal/InternalCjsidl.g" 33921
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.jts.eclipse.ui/src-gen/org/jts/eclipse/ui/contentassist/antlr/internal/InternalCjsidl.g" 33923
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.jts.eclipse.ui/src-gen/org/jts/eclipse/ui/contentassist/antlr/internal/InternalCjsidl.g" 33925
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.jts.eclipse.ui/src-gen/org/jts/eclipse/ui/contentassist/antlr/internal/InternalCjsidl.g" 33927
RULE_ANY_OTHER : .;


