typeset Containers (id = org.jts.tests.Containers, version = 0.1) {
	using constants org.jts.tests.ConstTestA as consts;
	string str1 [20];
	vstring vstr1 [5, 10];
	// Variants
	variant variant1 [0, 10] {
	};
	optional variant variant2 [0, 10] {
		vtag:variant variant3 [0, 10] {
			vtag:variant1 var1;
		};
		vtag:variant1 var1;
	};

	// Lists

	list list1 [0, 10] {
		variant1 var1;
	}
	##List2 interpretation##
	optional list list2
	##Count interpretation##
	[0, 10] {
		list1 listing;
	}
	list list3 [0, 8] {
		sequence seq1 {
			variant1 var;
		}
	}

	// Sequences

	sequence seq1 {
		list1 listing;
		variant1 var;
	}
	##Seq2 interpretation##
	sequence seq2 {
		variant2 var;
		optional seq1 seq;
		list list1 [0, 10] {
			sequence seq3 {
				variant1 var;
			}
		};
	}

	// Records

	// Globals scoped tests in Records.csd 	

	record rec1 {
		str1 str;
	}
	record rec2 {
		optional field uint8 type one (0, 6);
	}

	// Arrays 
	str1 strArray [4];
	vstr1 vstrArray [4];
}