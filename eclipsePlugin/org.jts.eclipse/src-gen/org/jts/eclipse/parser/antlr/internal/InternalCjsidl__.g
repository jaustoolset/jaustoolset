lexer grammar InternalCjsidl;
@header {
package org.jts.eclipse.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T19 : 'service' ;
T20 : '(' ;
T21 : 'id' ;
T22 : '=' ;
T23 : ',' ;
T24 : 'version' ;
T25 : ')' ;
T26 : '{' ;
T27 : '}' ;
T28 : ';' ;
T29 : 'description' ;
T30 : 'assumptions' ;
T31 : 'references' ;
T32 : 'inherits_from' ;
T33 : 'client_of' ;
T34 : 'import' ;
T35 : 'as' ;
T36 : 'constants' ;
T37 : 'using' ;
T38 : 'typeset' ;
T39 : 'messages' ;
T40 : 'input' ;
T41 : 'output' ;
T42 : 'eventset' ;
T43 : 'event' ;
T44 : 'message' ;
T45 : 'stateless' ;
T46 : 'protocol' ;
T47 : 'start' ;
T48 : '.' ;
T49 : 'state_machine' ;
T50 : 'initial' ;
T51 : 'state' ;
T52 : 'default' ;
T53 : 'entry' ;
T54 : 'exit' ;
T55 : 'uint8' ;
T56 : 'uint16' ;
T57 : 'uint32' ;
T58 : 'uint64' ;
T59 : 'internal' ;
T60 : 'transition' ;
T61 : 'simple' ;
T62 : 'push' ;
T63 : 'pop' ;
T64 : 'popto' ;
T65 : 'secondary' ;
T66 : '->' ;
T67 : 'next' ;
T68 : 'guard' ;
T69 : ':' ;
T70 : '!=' ;
T71 : '&&' ;
T72 : '||' ;
T73 : '!' ;
T74 : 'actions' ;
T75 : 'send' ;
T76 : 'command' ;
T77 : 'header' ;
T78 : 'body' ;
T79 : 'footer' ;
T80 : 'optional' ;
T81 : 'int8' ;
T82 : 'int16' ;
T83 : 'int32' ;
T84 : 'int64' ;
T85 : 'float' ;
T86 : 'double' ;
T87 : '-' ;
T88 : 'string' ;
T89 : '[' ;
T90 : ']' ;
T91 : 'vstring' ;
T92 : 'field' ;
T93 : 'variable_field' ;
T94 : 'vfield' ;
T95 : 'tag' ;
T96 : 'variable_format_field' ;
T97 : 'offset' ;
T98 : 'bit_field' ;
T99 : '<' ;
T100 : '>' ;
T101 : 'floor' ;
T102 : 'ceiling' ;
T103 : 'round' ;
T104 : 'list' ;
T105 : 'variant' ;
T106 : 'vtag' ;
T107 : 'sequence' ;
T108 : 'record' ;
T109 : 'AU' ;
T110 : 'BMP' ;
T111 : 'JPEG' ;
T112 : 'MJPEG' ;
T113 : 'MPEG-1' ;
T114 : 'MPEG-2' ;
T115 : 'MP2' ;
T116 : 'MP3' ;
T117 : 'MP4' ;
T118 : 'RAW' ;
T119 : 'WAV' ;
T120 : 'JAUS_MESSAGE' ;
T121 : 'XML' ;
T122 : 'RNC' ;
T123 : 'RNG' ;
T124 : 'XSD' ;
T125 : 'User_defined' ;
T126 : 'meter' ;
T127 : 'kilogram' ;
T128 : 'second' ;
T129 : 'ampere' ;
T130 : 'kelvin' ;
T131 : 'mole' ;
T132 : 'candela' ;
T133 : 'one' ;
T134 : 'square_meter' ;
T135 : 'cubic_meter' ;
T136 : 'meter_per_second' ;
T137 : 'meter_per_second_squared' ;
T138 : 'reciprocal_meter' ;
T139 : 'kilogram_per_cubic_meter' ;
T140 : 'cubic_meter_per_kilogram' ;
T141 : 'ampere_per_square_meter' ;
T142 : 'ampere_per_meter' ;
T143 : 'mole_per_cubic_meter' ;
T144 : 'candela_per_square_meter' ;
T145 : 'radian' ;
T146 : 'steradian' ;
T147 : 'hertz' ;
T148 : 'newton' ;
T149 : 'pascal' ;
T150 : 'joule' ;
T151 : 'watt' ;
T152 : 'coulomb' ;
T153 : 'volt' ;
T154 : 'farad' ;
T155 : 'ohm' ;
T156 : 'siemens' ;
T157 : 'weber' ;
T158 : 'tesla' ;
T159 : 'henry' ;
T160 : 'degree_Celsius' ;
T161 : 'lumen' ;
T162 : 'lux' ;
T163 : 'becquerel' ;
T164 : 'sievert' ;
T165 : 'katal' ;
T166 : 'pascal_second' ;
T167 : 'newton_meter' ;
T168 : 'newton_per_meter' ;
T169 : 'radian_per_second' ;
T170 : 'radian_per_second_squared' ;
T171 : 'watt_per_square_meter' ;
T172 : 'joule_per_kelvin' ;
T173 : 'joule_per_kilogram' ;
T174 : 'watt_per_meter_kelvin' ;
T175 : 'joule_per_cubic_meter' ;
T176 : 'volt_per_meter' ;
T177 : 'coulomb_per_cubic_meter' ;
T178 : 'coulomb_per_square_meter' ;
T179 : 'farad_per_meter' ;
T180 : 'henry_per_meter' ;
T181 : 'joule_per_mole' ;
T182 : 'joule_per_mole_kelvin' ;
T183 : 'coulomb_per_kilogram' ;
T184 : 'gray_per_second' ;
T185 : 'watt_per_square_meter_steradian' ;
T186 : 'katal_per_cubic_meter' ;
T187 : 'minute' ;
T188 : 'hour' ;
T189 : 'day' ;
T190 : 'degree' ;
T191 : 'liter' ;
T192 : 'metric_ton' ;
T193 : 'neper' ;
T194 : 'bel' ;
T195 : 'nautical_mile' ;
T196 : 'knot' ;
T197 : 'are' ;
T198 : 'hectare' ;
T199 : 'bar' ;
T200 : 'angstrom' ;
T201 : 'barn' ;
T202 : 'curie' ;
T203 : 'roentgen' ;
T204 : 'rad' ;
T205 : 'rem' ;

// $ANTLR src "../org.jts.eclipse/src-gen/org/jts/eclipse/parser/antlr/internal/InternalCjsidl.g" 14721
RULE_VERSION : RULE_DECIMAL ('.' RULE_INT)+;

// $ANTLR src "../org.jts.eclipse/src-gen/org/jts/eclipse/parser/antlr/internal/InternalCjsidl.g" 14723
RULE_DECIMAL : (RULE_INT '.' RULE_INT|'.' RULE_INT);

// $ANTLR src "../org.jts.eclipse/src-gen/org/jts/eclipse/parser/antlr/internal/InternalCjsidl.g" 14725
RULE_MESSAGE_CODE : ('0x'|'0X') ('0'..'9'|'a'..'f'|'A'..'F') ('0'..'9'|'a'..'f'|'A'..'F') ('0'..'9'|'a'..'f'|'A'..'F') ('0'..'9'|'a'..'f'|'A'..'F');

// $ANTLR src "../org.jts.eclipse/src-gen/org/jts/eclipse/parser/antlr/internal/InternalCjsidl.g" 14727
RULE_INTLITERAL : ('0'|'1'..'9' ('0'..'9')*);

// $ANTLR src "../org.jts.eclipse/src-gen/org/jts/eclipse/parser/antlr/internal/InternalCjsidl.g" 14729
RULE_NONINTNUMBER : '-'? RULE_DECIMAL (('e'|'E') ('+'|'-')? ('0'..'9')+)?;

// $ANTLR src "../org.jts.eclipse/src-gen/org/jts/eclipse/parser/antlr/internal/InternalCjsidl.g" 14731
RULE_ESC : '\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\'|'0'..'3' '0'..'7' '0'..'7'|'0'..'7' '0'..'7'|'0'..'7');

// $ANTLR src "../org.jts.eclipse/src-gen/org/jts/eclipse/parser/antlr/internal/InternalCjsidl.g" 14733
RULE_STRINGLITERAL : ('"' (RULE_ESC|~(('\\'|'"')))* '"'|'\'' (RULE_ESC|~(('\\'|'\'')))* '\'');

// $ANTLR src "../org.jts.eclipse/src-gen/org/jts/eclipse/parser/antlr/internal/InternalCjsidl.g" 14735
RULE_ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.jts.eclipse/src-gen/org/jts/eclipse/parser/antlr/internal/InternalCjsidl.g" 14737
RULE_INTERPRETATION : '##' ( options {greedy=false;} : . )*'##';

// $ANTLR src "../org.jts.eclipse/src-gen/org/jts/eclipse/parser/antlr/internal/InternalCjsidl.g" 14739
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.jts.eclipse/src-gen/org/jts/eclipse/parser/antlr/internal/InternalCjsidl.g" 14741
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../org.jts.eclipse/src-gen/org/jts/eclipse/parser/antlr/internal/InternalCjsidl.g" 14743
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.jts.eclipse/src-gen/org/jts/eclipse/parser/antlr/internal/InternalCjsidl.g" 14745
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.jts.eclipse/src-gen/org/jts/eclipse/parser/antlr/internal/InternalCjsidl.g" 14747
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.jts.eclipse/src-gen/org/jts/eclipse/parser/antlr/internal/InternalCjsidl.g" 14749
RULE_ANY_OTHER : .;


