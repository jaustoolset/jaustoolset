typeset BasicTypes (id = urn.jaus.jss.core.MessageSet.BasicTypes, version = 1.1) {
	header JAUSMessageHeader {
		record HeaderRec {
			##A two byte field to hold the message ID of a message##
			field uint16 MessageID one;
		}
	}
	field uint8 AuthorityCode one {
		[0, 255];
	};
	field uint16 EmergencyCode one {
		"STOP" = 1;
	};
	bit_field uint32 TimeStamp {
		Milliseconds [0:9] [0, 999];
		Seconds [10:15] [0, 59];
		Minutes [16:21] [0, 59];
		Hour [22:26] [0, 23];
		Day [27:31] [1, 31];
	};
	bit_field uint16 DateStamp {
		Day [0:4] [1, 31];
		Month [5:8] [1, 12];
		Year [9:15] [0, 127];
	};
	record TimeRec {
		optional TimeStamp TimeStamp_8A27vDR;
		optional DateStamp DateStamp_8A27vDR;
	}
	##Identifier of a JAUS compliant end point##
	bit_field uint32 JausID {
		##Component ID where a value of 255 represents all components.##
		ComponentID [0:7] [1, 255];
		##Node ID where a value of 255 represents all nodes.##
		NodeID [8:15] [1, 255];
		##Subsystem ID, where a value of 65535 represents all subsystems##
		SubsystemID [16:31] [1, 65535];
	};
	field uint16 ElementUID one;
	record ElementRec {
		##Values zero (0) and 65535 are invalid (reserved).##
		ElementUID ElementUID_8A27vDR;
		##UID of the previous (parent) element in the list. The value is zero (0) if this is the first (head) element.##
		ElementUID PreviousUID;
		##UID of the next (child) element in the list. The value is zero (0) if this is the last (tail) element.##
		ElementUID NextUID;
		##Element data to be added to the list##
		variable_format_field ElementData {
			uint16 tag [0, 65535];
			tag 0:JAUS_MESSAGE;
			tag 1:User_defined;
		};
	}
}