service Transport (id = urn.jaus.jss.core.Transport, version = 1.1) {
	description "The transport service acts as an interface to the JAUS transport layer. It models an abstract bi-directional communication channel (input queue and output queue) whose primary function is to provide the capability of sending messages to a single destination endpoint or broadcasting messages to all endpoints in the system, and to receive a message from any source endpoint. It also provides the capability to prioritize the delivery of sent messages. 
    This service establishes a communication endpoint whose address is defined by a triple {SubsystemID, NodeID, ComponentID} as specified by the Send and Receive internal events. Other services that need to utilize the communication channel provided by the transport service must inherit from the transport service.";
	assumptions " Messages may be delayed, lost or reordered. "; typeset TransportTypeSet {
		using typeset urn.jaus.jss.core.MessageSet.BasicTypes as basicTypes;
		record SendRec {
			##Identifier of destination to send the message##
			basicTypes.JausID DestinationID;
			##Identifier of the sender of the message##
			optional basicTypes.JausID SourceID;
			##Message payload.##
			vfield JAUS_MESSAGE MessagePayload [2];
			field uint8 ReliableDelivery one {
				[0, 1];
				"Unreliable" = 0;
				"Reliable" = 1;
			};
			##Priority level of this message. If not specified, the normal priority level is used.##
			optional field uint8 Priority one {
				[0, 3];
				"LOW" = 0;
				"NORMAL" = 1;
				"HIGH" = 2;
				"SAFETY" = 3;
			};
		}
		record BroadcastRec {
			##Identifier of destination to send the message##
			basicTypes.JausID DestinationID;
			##Identifier of the sender of the message##
			optional basicTypes.JausID SourceID;
			##Message payload.##
			vfield JAUS_MESSAGE MessagePayload [2];
			##Priority level of this message. If not specified, the normal priority level is used.##
			optional field uint8 Priority one {
				[0, 3];
				"LOW" = 0;
				"NORMAL" = 1;
				"HIGH" = 2;
				"SAFETY" = 3;
			};
		}
	}
	messages {
		input {
		}
		output {
		}
	}
	eventset {
		event Send {
			description
			"The Send event is used by a derived service to hand over the payload data that it needs to send to a specified destination endpoint via the transport layer. Upon receipt, this service prepares the message for delivery (marshals the message) as specified by the transport layer specification and attempts to deliver the encapsulated payload data to the destination endpoint.";
			header Header {
			}
			body Body {
				SendRec SendRec_8A27vDR;
			}
			footer Footer {
			}
		}
		event BroadcastLocal {
			description
			"The Broadcast Local event is the same as the Send event except that this service sends the payload to all endpoints within the subsystem.";
			header Header {
			}
			body Body {
				BroadcastRec BroadcastRec_8A27vDR;
			}
			footer Footer {
			}
		}
		event BroadcastGlobal {
			description
			"The Broadcast Global event is the same as the Send event except that this service sends the payload to all endpoints on all subsystems.";
			header Header {
			}
			body Body {
				BroadcastRec BroadcastRec_8A27vDR;
			}
			footer Footer {
			}
		}
		event Receive {
			description
			"The Receive event is used by derived services to hand over the data that was sent by another service to an endpoint established by this service.";
			header Header {
			}
			body Body {
				##transport data##
				record ReceiveRec {
					##Identifier of the sender of the message##
					basicTypes.JausID SourceID;
					##message payload##
					vfield JAUS_MESSAGE MessagePayload [2];
				}
			}
			footer Footer {
			}
		}
	}
	protocol {
		state_machine ReceiveFSM (start Receiving) {
			state Receiving {
				internal transition Receive () {
				}
			}
		}
		state_machine SendFSM (start Sending) {
			state Sending {
				internal transition Send (Send msg) {
					actions {
						##Convert the destination address into an unsigned integer such that the ComponentID maps to the least significant byte, NodeID to the next least significant byte and SubsystemID maps onto the remaining two bytes of the integer. Package the message as specified by the transport layer specification and send it to its destination as per the specified priority.##
						Enqueue (msg);
					}
				}
				internal transition BroadcastLocal (BroadcastLocal msg) {
					actions {
						##Package the message as specified by the transport layer specification and send it to all endpoints in the local subsystem.##
						BroadcastLocalEnqueue (msg);
					}
				}
				internal transition BroadcastGlobal (BroadcastGlobal msg) {
					actions {
						##Package the message as specified by the transport layer specification and send it to all endpoints in on all subsystems.##
						BroadcastGlobalEnqueue (msg);
					}
				}
			}
		}
	}
}