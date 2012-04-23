service simpleService (id = org.jts.tests.simpleService, version = 0.0) {
	description "This is a description";
	assumptions "This is an assumption";
	references {
		inherits_from import urn.jaus.jss.core.Transport as transport;
	}
	constants Const (id = org.jts.tests.Const, version = 0.1) {
		uint8 CONST1 = 0 one;
		uint16 CONST2 = 0 meter;
		uint16 CONST3 = 0 kilogram;
		uint32 CONST4 = 0 second;
		uint64 CONST5 = 0 hertz;
		int8 CONST6 = 0 ohm;
		int16 CONST7 = 0 degree;
		int32 CONST8 = 0 hour;
		int64 CONST9 = 0 metric_ton;
		float CONST10 = 0 curie;
		double CONST11 = 0 newton_meter;
	}
	typeset TypeSetA (id = org.jts.tests.TypeSetA, version = 0.1) {
		using typeset org.jts.tests.HBF as hbf;

		// Fixed Field Type

		field uint8 field1 one (0, 5);
		##Field Interpretation##
		field uint16 field2 meter;
		field uint8 field3 one <0, 4> ceiling;
		field uint8 field4 meter <0, CONST1> round;
		optional field uint16 field5 one [CONST1, CONST2];
		field uint32 field6 meter (0, CONST3];
		field uint64 field7 one [CONST4, 10);
		field uint64 field8 meter {
			"string" = 3;
			[0, 10];
		};
		field uint64 field9 one {
			"valueSpec" = 0;
			(0, 10);
			"valueSpec2" = 1;
			(11, 20];
		};
		// Variable Field Type

		variable_field vf1 {
			tag 1:tag1 uint8 one;
			tag 2:tag2 uint16 meter {
				(0, 8];
			};
			tag CONST1:tag3 uint64 one <1, 10> floor;
		};
		##Variable field interpretation##
		optional variable_field vf1 {
			tag 1:first uint32 one <0, 10> round;
		};

		// Variable Length Field Type

		vfield MP3 varLenField1;
		optional vfield MP4 varLenField2 [0, 20];
		vfield BMP varLenField3 [CONST1, 20];

		// Variable Format Field Type

		variable_format_field vvf1 {
			uint8 tag (0, 10);
			tag 10:MP2;
		};
	}
	messages {
		input {
			##Trying to link this...##
			message hbf.msg1 msg;
			message 0x0001 msg1 {
				description "";
				body varBody {
					variant variant1 [0, 10] {
					};
				}
			}
			command message 0x0002 msg2 {
				description "";
				body newBody {
					sequence seq2 {
						list list1 [0, 10] {
							sequence seq3 {
								record rec1 {
									field1 recField;
								};
							}
						};
					}
				}
			}
		}
		output {
			message 0x0003 msg3 {
				description "";
				body emptyBody {
				}
				header emptyHeader {
				}
				footer foot {
					variant variant1 [0, 10] {
					};
				}
			}
		}
	}
	eventset {
	// declaredEventDefs
		event Send2 {
			description "";
			body somebody {
			}
		}
		event scopedSend {
			description "";
			body emptyBody {
			}
		}
		event scopedReturnSend {
			description "Returns a different vf1 from an imported file.";
			body emptyBody {
			}
		}

		//eventTypeDefs
		event eventRef {
			description "An event with all of the header/body/footer types referenced.";
			body hbf.refBody rb;
		}
		event eventMultiRef {
			description "";
			footer hbf.refFooter rf;
			header hbf.refHeader rh;
			body hbf.refBody rb;
		}
		event newEvent {
			description "This is an eventTypeDef";
			##Must have a body as it's required by eventTypeDef'##
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
			} }
	}
	protocol {
		state_machine stateMachine (start StartState) {
			state StartState {
				internal transition newEvent (msg2 ev) {
					guard:guardAct (ev) != guardAct2 (ev);
				}
			}
		}
	}
}