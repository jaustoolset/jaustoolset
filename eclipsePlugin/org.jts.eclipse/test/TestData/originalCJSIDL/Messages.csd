
typeset Messages (id = org.jts.tests.msgs, version = 0.1) {
	using typeset org.jts.tests.Containers as containers;
	using typeset org.jts.tests.HBF as hbf;

	// Message variations
	message 0x0001 msg1 {
		description "";
		body emptyBody {
		}
	}
	message 0x0002 msg2 {
		description "";
		header emptyHeader {
		}
		footer emptyFooter {
		}
		body emptyBody {
		}
	}
	command message 0x0003 msg3 {
		description "";
		body emptyBody {
		}
	}
	message 0x0004 msg4 {
		description "";
		body simpleBody {
		}
		footer footer1 {
			containers.seq1 seq;
		}
	}
	command message 0x0005 msg5 {
		description "";
		footer footer1 {
			sequence seq1 {
				containers.list1 listing;
				containers.variant1 var;
			}
		}
		body body1 {
		}
	}
	message 0x0006 msg6 {
		description "";
		header hbf.simpleHeader sh;
		footer hbf.simpleFooter sf;
		body hbf.simpleBody simpleheader;
	}
}