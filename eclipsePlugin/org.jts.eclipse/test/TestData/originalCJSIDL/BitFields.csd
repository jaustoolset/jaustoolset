typeset BitFields (id = org.jts.tests.BitFields, version = 0.1) {
	bit_field uint8 bitfield1 {
		range1 [0:1] {(0, 10);};
	};
	optional bit_field uint16 bitfield2 {
		range1 [0:3] {(0, 1);};
		range2 [4:5] {(4, 6];};
	};
	bit_field uint32 bitfield3 {
		range1 [0:1] {[0, 0);};
	};
	##Largest possible bitfield##
	bit_field uint64 bitfield4 {
		##Subfield interpretation##
		range [0:1] {[0, 10];};
	};
}