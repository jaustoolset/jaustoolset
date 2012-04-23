service emptyService (id = org.jts.tests.emptyService, version = 0.0) {
	description "";
	assumptions "" ;
	messages {
		input {
		}
		output {
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