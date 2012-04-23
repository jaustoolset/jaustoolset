service protocolBehaviorTest (id = org.jts.tests.pBT, version = 0.1) {
	description "";
	assumptions "";
	references {
		inherits_from import org.jts.tests.simpleService as simpleService;
	}
	constants Constants {
		using constants org.jts.tests.ConstTestA as consts;
	}
	typeset Types {
		variable_field vf1 {
			tag 1:tag1 uint8 one;
			tag 2:tag2 uint16 meter {
				(0, 8];
			};
			tag consts.CONST1:tag3 uint64 one <1, 10> floor;
		};
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
	}
	messages {
		input {
			message msg1 msgA;
		}
		output {
		}
	}
	eventset {
		event LocalSend {
			description "";
			body emptyBody {
			}
		}
		event LocalSend2 {
			description "";
			body emptyBody {
			}
		}
		event LocalSend3 {
			description "";
			body emptyBody {
			}
		}
		event LocalReceive {
			description "";
			body emptyBody {
			}
		}
		event SecondaryPopEvent {
			description "";
			body emptyBody {
			}
		}

		event newEvent {
			description "";
			body emptyBody {
			}
			header head {
				sequence seq2 {
					list list1 [0, 10] {
						sequence seq3 {
							record rec1 {
								field uint8 fixedField one;
							};
						}
					};
				}
			}
			footer foot {
				variant var [0, 10] {
				};
			}
		}
	}
	##A more complex protocol example##
	stateless protocol {
		##Empty stateMachineA##
		state_machine stateMachineA (start StartState) {
			##Empty startState##
			state StartState {
			}
		}
		state_machine simpleService.transport.SendFSM (start StateA.StateAB.StateABC) {
			state default {
				internal transition LocalSend () {
				}
			}
			state StartState {
			// Should not be allowed.
				simple transition LocalSend (msgA msg) {
					-> StateA;
				}
				simple transition simpleService.transport.Send (msgA msg) {
					-> StateA.StateAB;
				}
				internal transition simpleService.transport.Receive (Receive.Body.ReceiveRec ev) {
					actions {
						act (ev);
						act ();
					}
					send actions {
						sendAct (ev);
					}
				}
				default internal transition {
					actions {
						act ();
					}
				}
			}
			state PopState{
				pop transition LocalSend2 (newEvent ev) {
					secondary SecondaryPopEvent ();
				}			
				internal transition SecondaryPopEvent(){
					actions {
						somePopAction();
					}
				}
			}
			state StateA {
				internal transition transport.Receive (Receive.Body.ReceiveRec rec) {
				}
				state StateAB {
					entry {
						actions {
							act ();
						}
						send actions {
							actSend ();
						}
					}
					exit {
						actions {
							exitAct ();
						}
						send actions {
							exitSendAct ();
						}
					}
					simple transition LocalSend (newEvent ev) {
						next StartState;
					}
					push transition LocalReceive () {
						-> PopState;
					}
					pop transition LocalSend2 (newEvent ev) {
							secondary LocalSend (ev);
						}
						default internal transition {
							actions {
								actD ();
							}
						}
						state StateABC {
							push transition LocalSend3 () {
								-> PopState;
								}
								state StateABCD {
									state StateAB {
									}
								}
							}
						}
						initial state StateAC {
						}
					}
				}
				state_machine stateMachine (start StartState) {
					state StartState {
					}
				}
			}
		}