service testService (id = org.jts.tests.testService, version = 0.1) {
//This is a generated stub file
	description "";
	assumptions "";
	typeset Types {
		using typeset org.jts.tests.msgs as msgs;
	}
	messages {
		input {
			message 0x0001 msg1 {
				description "";
				body emptyBody {
				}
			}
			message msgs.msg2 secondMsg;
		}
		output {
			message 0x0000 msg0 {
				description "";
				header msgs.hbf.refHeader rHeader;
				body msgs.hbf.simpleBody sBody;
			}
			message msgs.msg2 secondMsg;
		}
	}
	eventset {
	}
	protocol {
		state_machine stateMachine (start StartState) {
			state StartState {
			}
		}
	}
}