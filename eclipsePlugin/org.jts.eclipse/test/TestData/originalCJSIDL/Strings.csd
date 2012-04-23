
typeset Strings (id = org.jts.tests.Strings, version = 0.1) {
	using constants org.jts.tests.ConstTestB as consts;
	using typeset org.jts.tests.TypeSetA as types;
	string str1 [20];
	vstring vstr1 [5, 10];
	## fixed string ##
	optional string str2 [20];
	string str3 [20];
	string str4 [20];
	## variable string ##
	optional vstring str5 [5, 20];
	vstring str6 [5, 20];
	vstring str7 [consts.consts.CONST1, 20];
	string str8 [consts.CONST1];
	vstring vstr8 [consts.consts.CONST2, consts.consts.CONST3];
}