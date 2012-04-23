
typeset TypeSetA (id = org.jts.tests.TypeSetA, version = 0.1) {
	using constants org.jts.tests.ConstTestA as consts;

	// Fixed Field Type 

	field uint8 field1 one (0, 5);
	##Field Interpretation##
	field uint16 field2 meter;
	field uint16 field3 one <0, 4> ceiling; 
	field uint16 field4 meter <0, consts.CONST1> round;
	optional field uint32 field5 one [consts.CONST1, consts.CONST2];
	field uint32 field6 meter (0, consts.CONST3];
	field uint64 field7 one [consts.CONST4, 10);
	field uint64 field8 meter {
		"string" = 3;
		[0, 10];
	};
	field uint64 field9 one {
		"valueSpec" = 1;
		(0, 10);
		"valueSpec2" = 2;
		(11, 20];
	};

	// Variable Field Type

	variable_field vf1 {
		tag 1:tag1 uint8 one;
		tag 2:tag2 uint16 meter {
			(0, 8];
		};
		tag consts.CONST1:tag3 uint64 one <1, 10> floor;
	};
	##Variable field interpretation##
	optional variable_field vf1 {
		tag 1:first uint32 one <0, 10> round;
	};

	// Variable Length Field Type

	vfield MP3 varLenField1;
	optional vfield MP4 varLenField2 [0, 20];
	vfield BMP varLenField3 [consts.CONST1, 20];
	##Variable length field interpretation##
	vfield JPEG varLenField3 [0, consts.CONST2];

	// Variable Format Field Type

	variable_format_field vvf1 {
		uint8 tag (0, 10);
		tag 10:MP2;
	};
	##Variable format field interpretation##
	variable_format_field vvf2 {
		## Count nterpretation ##
		uint16 tag (0, consts.CONST3];
		tag consts.CONST4:XSD;
		tag consts.CONST1:"png";
	};

	// Enums

	field uint8 enum1 one {
		"valueSpec" = 1;
	};
	##Full enum##
	field uint16 enum2 meter {
		##Value interpretation 1##
		"startState" = 1;
		##Value interpretation 2##
		"endState" = 2;
		##Value interpretation 3##
		"emergency" = 3;
		##Value interpretation 4##
		"standby" = 4;
	};
}