
typeset HeaderBodyFooter (id = org.jts.tests.HBF, version = 0.1) {
	using typeset org.jts.tests.Containers as container;
	using typeset org.jts.tests.TypeSetA as type;

	// Headers

	header simpleHeader {
	}
	header refHeader {
		container.variant1 var;
	}
	## Header containing a new definition##
	header newHeader {
		## Variant interpretation##
		variant variant1 ##Variant tag interpretation##[0, 10]
		{
			
		};
	}

	// Body

	body simpleBody {
	}
	body refBody {
		container.rec1 rec;
	}
	body newBody {
		optional variant variant2 [0, 10]{
			vtag:variant variant3 [0, 10]{
				vtag:container.variant1 var1;
			};
			vtag:container.variant1 var1;
		};
	}

	// Footers

	footer simpleFooter {
	}
	footer refFooter {
		container.variant2 var;
	}
	footer newFooter {
		optional list list2 [0, 10]{
			container.list1 listing;
		}
	}
	message 0x0001 msg1 {
		description "";
		body refBody ref;
	}
	message 0x0002 msg2 {
		description "";
		header refHeader ref;
		// BROKEN 

		footer refFooter ref2;
		body refBody ref3;
	}
	record rec {
		optional type.field2 variable;
	}
}