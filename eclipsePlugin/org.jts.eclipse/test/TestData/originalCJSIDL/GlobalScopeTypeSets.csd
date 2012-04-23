typeset TypeSets (id = org.jts.tests.globalScopeTypeSets, version = 0.1) {
	using typeset org.jts.tests.Containers as container;

	// Variants
	variant var1 [container.consts.CONST1, 5] {
		vtag:container.variant1 var;
		vtag:container.rec1 rec;
		vtag:container.seq1 seq;
		vtag:container.list1 l;
		vtag:rec1 rec2;
		vtag:list1 l1;
		vtag:seq1 seq_local;
	};
	//Records

	record rec1 {
		container.str1 str;
	}
	##rec2 interpretation## 
	record rec2 {
		##var2 interpretation##
		container.vstr1 vstr;
	}

	// Arrays
	container.str1 strArray [3];
	##Record array interpretation##
	container.vstr1 vstrArray [4];

	// Lists

	list list1 [0, 10] {
		container.variant1 var1;
	}
	list list2 [0, 10] {
		container.seq1 seq;
	}
	list list3 [0, 10] {
		container.list1 listing;
	}
	list list4 [0, 10] {
		container.rec1 rec;
	}

	//Sequences

	sequence seq1 {
		container.seq1 seqref;
	}
	##Seq2 interpretation##
	sequence seq2 {
		##Scoped interpretation##
		container.variant2 var1;
		optional container.rec1 rec;
		container.list1 listref;
	}

	// Type sets allow for referened types - may need to rethink the syntax.
	container.seq1 seqRef;
	container.rec1 recRef;
}