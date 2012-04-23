/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010, United States Government
All rights reserved.

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions are met:

       Redistributions of source code must retain the above copyright notice, 
this list of conditions and the following disclaimer.

       Redistributions in binary form must reproduce the above copyright 
notice, this list of conditions and the following disclaimer in the 
documentation and/or other materials provided with the distribution.

       Neither the name of the United States Government nor the names of 
its contributors may be used to endorse or promote products derived from 
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.
*********************  END OF LICENSE ***********************************/

/* packet-jaus.c
 * Routines for Joint Architecture for Unmanned Systems (JAUS) dissection.
 * Copyright 2008, Alex Evans <alex_evans@wintec-inc.com>
 *
 * Wireshark - Network traffic analyzer
 * By Gerald Combs <gerald@wireshark.org>
 * Copyright 1998 Gerald Combs
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

#ifdef HAVE_CONFIG_H
#include "config.h"
#endif

#include <stdio.h>
#include <string.h>
#include <math.h>

#include "jausxml.h"

#include <glib.h>
#include <epan/packet.h>
#include <epan/prefs.h>
#include <epan/emem.h>

#define PROTO_TAG_JAUS       "JAUS"
#define JAUS_START_STRING    "JAUS01.0"

/* Minimum JAUS message length.
   16 Bytes (header) + 8 Bytes(JAUS01.0) */
#define JAUS_MIN_LEN     24

#ifndef LITTLE_ENDIAN
#define LITTLE_ENDIAN    TRUE
#endif

/* Message Properties flags */
#define JAUS_PRIORITY_FLAG       0x0F
#define JAUS_ACKNAK_FLAG         0x30
#define JAUS_SERVICE_FLAG        0x40
#define JAUS_EXPERIMENTAL_FLAG   0x80
#define JAUS_VERSION_FLAG        0x3F

#define MSG_TYPE_FLAG  0x3F
#define HC_FLAG        0xC0
#define PRIORITY_FLAG  0x03
#define BCAST_FLAG     0x0C
#define ACK_NAK_FLAG   0x30
#define DATA_FLAG      0xC0

/* message_set found in jausxml.c */
extern message_def_t *message_set;

/* global offset for data dissection */
int data_offset;

/* forward references */
void proto_register_jaus(void);
void proto_reg_handoff_jaus(void);
//static int dissect_jaus(tvbuff_t *tvb, packet_info *pinfo, proto_tree *tree);
static void dissect_jaus(tvbuff_t *tvb, packet_info *pinfo, proto_tree *tree);
int dissect_RA3_header(tvbuff_t *tvb, packet_info *pinfo, proto_tree *tree);
int dissect_sdp_header(tvbuff_t *tvb, packet_info *pinfo, proto_tree *tree);
int dissect_message_field(tvbuff_t *tvb, proto_tree *tree, field_t *f_ptr, int _op_count, guint64 presence_vector);
int dissect_message_data(tvbuff_t *tvb, proto_tree *tree, int _offset, message_def_t *m_ptr);
int get_number_of_bytes(char type);
int get_data_from_tvb(tvbuff_t *tvb, int offset, char type, int size, guint64 *data);
double scale_convert(unsigned int scaled_value, int bits, double real_lower, double real_upper, char int_function);

/* Wireshark ID of the JAUS protocol */
static int proto_jaus = -1;

/* JAUS protocol port
   From IANA port list
   jaus            3794/tcp   JAUS Robots
   jaus            3794/udp   JAUS Robots */
static guint global_jaus_port = 3794;
static guint global_jaus_extra_port = 55555; // An extra port to snoop on

/* These is the handle of the sub-dissector */
//static dissector_handle_t jaus_handle;

/* some optional text strings representing packet types */
static const range_string priority_flag[] = {
	{ 0, 5, "Low Normal" },
	{ 6, 6, "Default Normal" },
	{ 7, 11, "High Normal" },
	{ 12, 15, "Safety Critical" },
	{ 0, 0, NULL }
};

static const value_string acknak_flag[] = {
	{ 0, "None" },
	{ 1, "Request AckNak" },
	{ 2, "Nak Response" },
	{ 3, "Ack Response" },
	{ 0, NULL }
};

static const value_string service_flag[] = {
	{ 0, "None" },
	{ 1, "Service Connection" },
	{ 0, NULL }
};

static const value_string experimental_flag[] = {
	{ 0, "Jaus" },
	{ 1, "Experimental" },
	{ 0, NULL }
};

static const value_string version_flag[] = {
	{ 0, "Version 2.0" },
	{ 1, "Version 3.0 or 3.1" },
	{ 2, "Version 3.2 or 3.3" },
	{ 0, NULL }
};

/* These are the ids of the header fields that may be created */
/* RA3 header */
static gint hf_jaus_priority = -1;
static gint hf_jaus_acknak = -1;
static gint hf_jaus_service = -1;
static gint hf_jaus_experimental = -1;
static gint hf_jaus_version = -1;
static gint hf_jaus_commandCode = -1;
static gint hf_jaus_destination = -1;
static gint hf_jaus_dest_sub = -1;
static gint hf_jaus_dest_node = -1;
static gint hf_jaus_dest_comp = -1;
static gint hf_jaus_dest_inst = -1;
static gint hf_jaus_source = -1;
static gint hf_jaus_src_sub = -1;
static gint hf_jaus_src_node = -1;
static gint hf_jaus_src_comp = -1;
static gint hf_jaus_src_inst = -1;
static gint hf_jaus_dataControl = -1;
static gint hf_jaus_data_size = -1;
static gint hf_jaus_data_flag = -1;
static gint hf_jaus_sequenceNumber = -1;
static gint hf_jaus_data = -1;
/* SAE new header */
static gint hf_jaus_message_type = -1;
static gint hf_jaus_hc = -1;
static gint hf_jaus_data_size2 = -1;
static gint hf_jaus_hc_num = -1;
static gint hf_jaus_hc_lenght = -1;
static gint hf_jaus_priority2 = -1;
static gint hf_jaus_acknak2 = -1;
static gint hf_jaus_bcast = -1;
static gint hf_jaus_data_flag2 = -1;

/* universal header for all the unknown number of Fields in the Data */
static gint hf_jaus_uint64 = -1;

/* These are the ids of the subtrees that we may be creating
 * Used by Wireshark to remember open/closed trees between selected packets
 */
static gint ett_jaus = -1;
static gint ett_jaus_header = -1;
static gint ett_jaus_message_properties = -1;
static gint ett_jaus_destination = -1;
static gint ett_jaus_source = -1;
static gint ett_jaus_dataControl = -1;
static gint ett_jaus_data = -1;
static gint ett_array = -1;


/**
 * Register the protocol with Wireshark
 * Called one time
 */
void proto_register_jaus(void)
{
	/* A header field is something you can search/filter on.
	 *
	 * Create a structure to register our fields. It consists of an
	 * array of hf_register_info structures, each of which are of the format
	 * {&(field_id),
	 * {name, abbrev, type, display,
	 * strings, bitmask, blurb, HFILL}
	 * },
	 */
	static hf_register_info hf[] = {

		{ &hf_jaus_priority,
		{ "Priority", "jaus.priority", FT_UINT8, BASE_DEC|BASE_RANGE_STRING,
			RVALS(priority_flag), JAUS_PRIORITY_FLAG, "Priority", HFILL }
		},
		{ &hf_jaus_acknak,
		{ "ack/nak", "jaus.acknak", FT_UINT8, BASE_DEC,
			VALS(acknak_flag), JAUS_ACKNAK_FLAG, "ack/nak", HFILL }
		},
		{ &hf_jaus_service,
		{ "Service", "jaus.service", FT_UINT8, BASE_DEC,
			VALS(service_flag), JAUS_SERVICE_FLAG, "Service", HFILL }
		},
		{ &hf_jaus_experimental,
		{ "Experimental?", "jaus.experimental", FT_UINT8, BASE_DEC,
			VALS(experimental_flag), JAUS_EXPERIMENTAL_FLAG, "Experimental?", HFILL }
		},
		{ &hf_jaus_version,
		{ "Version", "jaus.version", FT_UINT8, BASE_DEC,
			VALS(version_flag), JAUS_VERSION_FLAG, "Version", HFILL }
		},
		{ &hf_jaus_commandCode,
		{ "Command Code", "jaus.command", FT_UINT16, BASE_HEX,
			NULL, 0x0, "Command", HFILL }
		},
		{ &hf_jaus_destination,
		{ "Destination ID", "jaus.dest", FT_IPv4, BASE_NONE,
			NULL, 0x0, "Destination", HFILL }
		},
		{ &hf_jaus_dest_sub,
		{ "Subsystem ID", "jaus.dest.sub", FT_UINT32, BASE_DEC,
			NULL, 0xFF000000, "Destination Subsystem", HFILL }
		},
		{ &hf_jaus_dest_node,
		{ "Node ID", "jaus.dest.node", FT_UINT32, BASE_DEC,
			NULL, 0x00FF0000, "Destination Node", HFILL }
		},
		{ &hf_jaus_dest_comp,
		{ "Component ID", "jaus.dest.comp", FT_UINT32, BASE_DEC,
			NULL, 0x0000FF00, "Destination Component", HFILL }
		},
		{ &hf_jaus_dest_inst,
		{ "Instance ID", "jaus.dest.inst", FT_UINT32, BASE_DEC,
			NULL, 0x000000FF, "Destination Instance", HFILL }
		},
		{ &hf_jaus_source,
		{ "Source ID", "jaus.source", FT_IPv4, BASE_NONE,
			NULL, 0x0, "Source", HFILL }
		},
		{ &hf_jaus_src_sub,
		{ "Subsystem ID", "jaus.src.sub", FT_UINT32, BASE_DEC,
			NULL, 0xFF000000, "Source Subsystem", HFILL }
		},
		{ &hf_jaus_src_node,
		{ "Node ID", "jaus.src.node", FT_UINT32, BASE_DEC,
			NULL, 0x00FF0000, "Source Node", HFILL }
		},
		{ &hf_jaus_src_comp,
		{ "Component ID", "jaus.src.comp", FT_UINT32, BASE_DEC,
			NULL, 0x0000FF00, "Source Component", HFILL }
		},
		{ &hf_jaus_src_inst,
		{ "Instance ID", "jaus.src.inst", FT_UINT32, BASE_DEC,
			NULL, 0x000000FF, "Source Instance", HFILL }
		},
		{ &hf_jaus_dataControl,
		{ "Data Control", "jaus.dataControl", FT_UINT16, BASE_HEX,
			NULL, 0x0, "Data Control", HFILL }
		},
		{ &hf_jaus_data_size,
		{ "Data Size", "jaus.dataC.size", FT_UINT16, BASE_DEC,
			NULL, 0x0FFF, "Data Size", HFILL }
		},
		{ &hf_jaus_data_flag,
		{ "Data Flag", "jaus.dataC.flag", FT_UINT16, BASE_HEX,
			NULL, 0xF000, "Data Flag", HFILL }
		},
		{ &hf_jaus_sequenceNumber,
		{ "Sequence Number", "jaus.sequenceNumber", FT_UINT16, BASE_DEC,
			NULL, 0x0, "Sequence Number", HFILL }
		},
		{ &hf_jaus_data,
		{ "Data", "jaus.data", FT_BYTES, BASE_NONE,
			NULL, 0x0, "data", HFILL }
		},

		/* New Header */
		{ &hf_jaus_message_type,
			{ "Message Type", "jaus.messagetype", FT_UINT8, BASE_DEC,
			NULL, MSG_TYPE_FLAG, "Message Type", HFILL }
		},
		{ &hf_jaus_hc,
			{ "Header Compression", "jaus.hc", FT_UINT8, BASE_DEC,
			NULL, HC_FLAG, "Using Header Compression", HFILL }
		},
		{ &hf_jaus_data_size2,
		{ "Data Size", "jaus.datasize", FT_UINT16, BASE_DEC,
			NULL, 0x0, "Size of message", HFILL }
		},
		{ &hf_jaus_hc_num,
		{ "HC Number", "jaus.hc.num", FT_UINT8, BASE_DEC,
			NULL, 0x0, "Header Compression Number", HFILL }
		},
		{ &hf_jaus_hc_lenght,
		{ "HC Lenght", "jaus.hc.lenght", FT_UINT8, BASE_DEC,
			NULL, HC_FLAG, "Header Compression Lenght", HFILL }
		},
		{ &hf_jaus_priority2,
		{ "Priority", "jaus.priority", FT_UINT8, BASE_DEC,
			NULL, PRIORITY_FLAG, "Priority", HFILL }
		},
		{ &hf_jaus_acknak2,
		{ "ack/nak", "jaus.acknak", FT_UINT8, BASE_DEC,
			VALS(acknak_flag), ACK_NAK_FLAG, "ack/nak", HFILL }
		},
		{ &hf_jaus_bcast,
		{ "Broadcast", "jaus.bcast", FT_UINT8, BASE_DEC,
			NULL, BCAST_FLAG, "Broadcast", HFILL }
		},
		{ &hf_jaus_data_flag2,
		{ "Data Flag", "jaus.dataflag", FT_UINT8, BASE_DEC,
			NULL, DATA_FLAG, "Data Flag", HFILL }
		},

		{ &hf_jaus_uint64,
		{ "jaus data", "jaus.data", FT_UINT64, BASE_HEX,
			NULL, 0x0, "jaus payload data", HFILL }
		}

	};

	/* Setup protocol subtree array */
	static gint *ett[] = {
		&ett_jaus,
		&ett_jaus_header,
		&ett_jaus_message_properties,
		&ett_jaus_destination,
		&ett_jaus_source,
		&ett_jaus_dataControl,
		&ett_jaus_data,
		&ett_array
	};

	module_t *jaus_module = NULL;

	/* Register the protocol name and description */
	proto_jaus = proto_register_protocol (
		"JAUS Robots",	/* name */
		"JAUS",			/* short name */
		"jaus"			/* abbrev */
	);
	
	/* module Preferences */
    jaus_module = prefs_register_protocol(proto_jaus, proto_reg_handoff_jaus);
	
	/* Allow the user to set the UDP port for the decode under the Edit -> Preferences menu */
	prefs_register_uint_preference(jaus_module, "tcp.port", "JAUS Port (default 3794)",
		"Set the port for JAUS TCP/UDP (default 3794)",
		10, &global_jaus_port);
	
	prefs_register_uint_preference(jaus_module, "udp.port", "JAUS Debug intra-node UDP Port",
		"Using the JR nodeManager, intra-node traffic may be set to output on a different Port",
		10, &global_jaus_extra_port);
	
	/* Required function calls to register the header fields and subtrees used */
	proto_register_field_array (proto_jaus, hf, array_length(hf));
	proto_register_subtree_array (ett, array_length(ett));
	//register_dissector("jaus", dissect_jaus, proto_jaus); /* OLD */
	
	/* called in jausxml.c to parse the xml on start-up of wireshark */
	start_xml_parse();
}

/**
 * This function is called to register a handoff for our protocol
 * Whenever a packet on the udp port is recieved, wireskark will call dissect_jaus()
 *
 * This may be call more then once by wireshark, preferences updates
 */
void proto_reg_handoff_jaus(void)
{
	static int jaus_inited = FALSE;
	static guint jaus_port;
	static guint jaus_extra_port;
	static dissector_handle_t jaus_handle;
	
	if ( !jaus_inited )
	{
		/* Create a dissector handle for the Jmirror protocol */
		jaus_handle = create_dissector_handle(dissect_jaus, proto_jaus);
		
		/* Set the init flag */
		jaus_inited = TRUE;
	} else {
		/* Unregister from the old UDP port */
		dissector_delete_uint("tcp.port", jaus_port, jaus_handle);
		dissector_delete_uint("udp.port", jaus_port, jaus_handle);
		dissector_delete_uint("udp.port", jaus_extra_port, jaus_handle);
	}
	
	jaus_port = global_jaus_port;
	jaus_extra_port = global_jaus_extra_port;
	
	/* Register as a normal IP dissector with default UDP port 3794 */
	dissector_add_uint("tcp.port", jaus_port, jaus_handle);
	dissector_add_uint("udp.port", jaus_port, jaus_handle);
	dissector_add_uint("udp.port", jaus_extra_port, jaus_handle);
}

/**
 * Code called by wireshark to dissect the packets
 *
 * Figures out the jaus message version.
 *
 * Return value is the amount that was dissected
 */
#if 0
static int dissect_jaus(tvbuff_t *tvb, packet_info *pinfo, proto_tree *tree)
{

	guint8 version = 0;

	/* get the first byte to check for the version of jaus message */
	version = tvb_get_guint8( tvb, 0 );

	if (version == 'J') { /* Legacy Header RA3 */
		return(dissect_RA3_header(tvb, pinfo, tree));
	} else if (version == 2) { /* new transport spec v2 */
		return(dissect_sdp_header(tvb, pinfo, tree));
	} else { /* unsupported */
		return 0;
	}

}
#endif

static void 
dissect_jaus(tvbuff_t *tvb, packet_info *pinfo, proto_tree *tree)
{

	guint8 version = 0;

	/* get the first byte to check for the version of jaus message */
	version = tvb_get_guint8( tvb, 0 );

	if (version == 'J') { /* Legacy Header RA3 */
		dissect_RA3_header(tvb, pinfo, tree);
	} else if (version == 2) { /* new transport spec v2 */
		dissect_sdp_header(tvb, pinfo, tree);
	} else { /* unsupported */
		return;
	}

}

/**
 * Starts the dissection of the new header.
 *
 * Return value is the amount that was dissected
 */
int dissect_sdp_header(tvbuff_t *tvb, packet_info *pinfo, proto_tree *tree)
{
	proto_item *jaus_item = NULL;
	proto_item *jaus_sub_item = NULL;
	proto_tree *jaus_tree = NULL;
	//proto_tree *jaus_sub_tree = NULL;
	proto_tree *jaus_header_tree = NULL;
/*	proto_tree *jaus_properties_tree = NULL;
	proto_tree *jaus_dest_tree = NULL;
	proto_tree *jaus_src_tree = NULL;
	proto_tree *jaus_dataC_tree = NULL;   */
	proto_tree *jaus_payload_tree = NULL;
	proto_tree *jaus_command_tree = NULL;

	message_def_t *m_ptr;

	int offset = 1; /* starting offset in buffer */

	guint8 hc = 0;
	guint8 compression = 0; /* flag for using compression */
	guint16 data_size = 0;
	guint8 hc_num = 0;
	guint8 hc_length = 0;
	guint8 properties = 0;
	guint16 command = 0;

	int bytes, found_msg = 0;


	/* Make entries in Protocol column and Info column on summary display */
	if (check_col(pinfo->cinfo, COL_PROTOCOL)) {
		col_set_str(pinfo->cinfo, COL_PROTOCOL, PROTO_TAG_JAUS);
	}

	/* Clear out stuff in the info column if present*/
	if (check_col(pinfo->cinfo,COL_INFO)) {
		col_clear(pinfo->cinfo,COL_INFO);
	}

	if (tree) {
		/* JAUS Main tree */
		jaus_item = proto_tree_add_item(tree, proto_jaus, tvb, 0, -1, FALSE);
		jaus_tree = proto_item_add_subtree(jaus_item, ett_jaus);

	}

	hc = tvb_get_guint8( tvb, offset );

	if ((hc & HC_FLAG) != 0) {
		compression = 1;
	}

	if (tree) {
		/* Header Sub tree */
		jaus_sub_item = proto_tree_add_text(jaus_tree, tvb, offset, (!compression)? 14: 3, "Message Header");
		jaus_header_tree = proto_item_add_subtree(jaus_sub_item, ett_jaus_header);

		/* add message type and hc to header tree */
		proto_tree_add_item(jaus_header_tree, hf_jaus_message_type, tvb, offset, 1, LITTLE_ENDIAN);
		proto_tree_add_item(jaus_header_tree, hf_jaus_hc, tvb, offset, 1, LITTLE_ENDIAN);
	}

	offset+=1;

	if (!compression) {
		data_size = tvb_get_letohs(tvb, offset);


		if (data_size < 14) {
			/* data too small */
			if (check_col(pinfo->cinfo, COL_INFO)) {
				col_add_fstr(pinfo->cinfo, COL_INFO, "[ERROR Data Size is below the minimum value of 14]");
			}
			if (tree) {
				/* add error data_size to header tree */
				proto_tree_add_text(jaus_header_tree, tvb, offset, 2, "[ERROR Data Size is below the minimum value of 14]");
			}
			return offset;
		}

		if (data_size != (tvb_length(tvb) -1) ) {
			/* error in packet size */
			if (check_col(pinfo->cinfo, COL_INFO)) {
				col_add_fstr(pinfo->cinfo, COL_INFO, "[ERROR Data Size (%d) does not match size of packet (%d)]", data_size, (tvb_length(tvb)-1));
			}
			if (tree) {
				/* add error data_size to header tree */
				proto_tree_add_text(jaus_header_tree, tvb, offset, 2, "[ERROR Data Size (%d) does not match size of packet (%d)]", data_size, (tvb_length(tvb)-1) );
			}
			return offset;
		}

		if (tree) {
			/* add data_size to header tree */
			proto_tree_add_item(jaus_header_tree, hf_jaus_data_size2, tvb, offset, 2, LITTLE_ENDIAN);
		}
		offset+=2;
	}

	if (compression) {

		hc_num = tvb_get_guint8(tvb, offset);
		if (tree) {
			/* add hc_mun to header tree */
			proto_tree_add_item(jaus_header_tree, hf_jaus_hc_num, tvb, offset, 1, LITTLE_ENDIAN);
		}
		offset+=1;

		hc_length = tvb_get_guint8(tvb, offset);
		if (tree) {
			/* add hc_length to header tree */
			proto_tree_add_item(jaus_header_tree, hf_jaus_hc_lenght, tvb, offset, 1, LITTLE_ENDIAN);
		}
		offset+=1;

	}

	if (!compression) {

		properties = tvb_get_guint8(tvb, offset);

		if (tree) {
			/* add properties to header tree */
			proto_tree_add_item(jaus_header_tree, hf_jaus_priority2, tvb, offset, 1, LITTLE_ENDIAN);
			proto_tree_add_item(jaus_header_tree, hf_jaus_acknak2, tvb, offset, 1, LITTLE_ENDIAN);
			proto_tree_add_item(jaus_header_tree, hf_jaus_bcast, tvb, offset, 1, LITTLE_ENDIAN);
			proto_tree_add_item(jaus_header_tree, hf_jaus_data_flag2, tvb, offset, 1, LITTLE_ENDIAN);
		}
		offset+=1;

		if (tree) {
			/* add destination to header tree */
			proto_tree_add_item(jaus_header_tree, hf_jaus_destination, tvb, offset, 4, LITTLE_ENDIAN);
		}
		offset+=4;

		if (tree) {
			/* add source to header tree */
			proto_tree_add_item(jaus_header_tree, hf_jaus_source, tvb, offset, 4, LITTLE_ENDIAN);
		}
		offset+=4;

		/* beginning of n-byte payload
		 * This could be empty?, or many messages
		 */
		if (tvb_length_remaining(tvb, offset) > 2) {
			bytes = tvb_length_remaining(tvb, offset) - 2;
			if (tree) {
				jaus_sub_item = proto_tree_add_text(jaus_tree, tvb, offset, bytes, "Payload (%d byte%s)", bytes, plurality(bytes, "", "s"));
				jaus_payload_tree = proto_item_add_subtree(jaus_sub_item, ett_jaus_data);
			}
		}

		while (tvb_length_remaining(tvb, offset) > 2) { /* WHILE not last 2 bytes */

			command = tvb_get_letohs(tvb , offset);
			found_msg = 0;
			if (message_set != NULL) {
				m_ptr = message_set;
				while (m_ptr != NULL) {
					if (m_ptr->message_id == command) {
						found_msg = 1; break;
					}
					m_ptr = m_ptr->next;
				}
			}

			if (check_col(pinfo->cinfo, COL_INFO)) {
				col_append_fstr(pinfo->cinfo, COL_INFO, "Cmd(0x%04X) %s, ", command, (found_msg)?m_ptr->name:" --- " );
			}

			if (tree) {
				proto_item_append_text(jaus_item, ", Msg: %s, is_Command: %s", (found_msg) ? m_ptr->name : "NotFoundInXML",
					(found_msg) ? ((m_ptr->is_command) ? "true" : "false") : "NotFoundInXML");

				/* submit the commandCode parameter to the payload sub tree. */
				jaus_sub_item = proto_tree_add_uint_format(jaus_payload_tree, hf_jaus_commandCode, tvb, offset, 2, command,
					"Command Code: %s (0x%04X)", (found_msg) ? m_ptr->name : "NotFoundInXML", command);
				jaus_command_tree = proto_item_add_subtree(jaus_sub_item, ett_jaus_data);
			}

			offset+=2;

			/* If the message is not found in the xml, offset to the next can not to found, just show the data and continue */
			if (found_msg) {
				offset = dissect_message_data(tvb, jaus_command_tree, offset, m_ptr);
				if (offset < 0) {return(data_offset);}
			} else {/* if no message found then show the data  */
				proto_item_append_text(jaus_sub_item, ", No message_def found in XML to dissect data");
				bytes = tvb_length_remaining(tvb, offset) - 2;
				proto_tree_add_item(jaus_command_tree, hf_jaus_data, tvb, offset, bytes, FALSE);
				break;
			}

		} /* END WHILE  check for last 16 bits */

	}

	if (!compression) {
		if (!found_msg) {
			while (tvb_length_remaining(tvb, offset) > 2) {
				offset++;
			}
		}
		/* submit the sequenceNumber parameter to Header Sub tree. */
		proto_tree_add_item(jaus_header_tree, hf_jaus_sequenceNumber, tvb, offset, 2, LITTLE_ENDIAN);
		offset+=2;
	}

	/* Return the amount of data this dissector was able to dissect */
	return(offset);
}

/**
 * Starts the dissection of the RA3 header.
 *
 * Return value is the amount that was dissected
 */
int dissect_RA3_header(tvbuff_t *tvb, packet_info *pinfo, proto_tree *tree)
{
	proto_item *jaus_item = NULL;
	proto_item *jaus_sub_item = NULL;
	proto_tree *jaus_tree = NULL;
	//proto_tree *jaus_sub_tree = NULL;
	proto_tree *jaus_header_tree = NULL;
	proto_tree *jaus_properties_tree = NULL;
	proto_tree *jaus_dest_tree = NULL;
	proto_tree *jaus_src_tree = NULL;
	proto_tree *jaus_dataC_tree = NULL;
	proto_tree *jaus_data_tree = NULL;

	message_def_t *m_ptr;

	int offset = 0;
	guint8 properties = 0;
	guint16 command = 0;
	guint16 dataC = 0;

	int bytes, found_msg = 0;

	/* Check that there's enough data, at least a Jaus header */
	if (tvb_length(tvb) < JAUS_MIN_LEN)
		return 0;

	/*	Checks for the JAUS01.0 string to make sure it is a JAUS Message */
	if ( strcmp(tvb_get_ephemeral_string(tvb, offset, 8), JAUS_START_STRING) != 0)
		/*  This packet does not appear to belong to JAUS.
		 *  Return 0 to give another dissector a chance to dissect it.
		 */
		return 0;

	/* increment the offset to get past the 8 bytes indicating message */
	offset += 8;

	/* Make entries in Protocol column and Info column on summary display */
	if (check_col(pinfo->cinfo, COL_PROTOCOL)) {
		col_set_str(pinfo->cinfo, COL_PROTOCOL, PROTO_TAG_JAUS);
	}

	/* Clear out stuff in the info column if present*/
	if (check_col(pinfo->cinfo,COL_INFO)) {
		col_clear(pinfo->cinfo,COL_INFO);
	}

	/* Here we check to see if the INFO column is present. If it is we output
	* which ports the packet came from and went to. Also, we indicate the type
	* of packet.
	*/

	command = tvb_get_letohs(tvb , offset+2 );

	if (message_set != NULL) {
		m_ptr = message_set;
		while (m_ptr != NULL) {
			if (m_ptr->message_id == command) {
				found_msg = 1; break;
			}
			m_ptr = m_ptr->next;
		}
	}

	properties = tvb_get_guint8(tvb, offset);

	if (check_col(pinfo->cinfo, COL_INFO)) {
		col_add_fstr(pinfo->cinfo, COL_INFO, "Cmd(0x%04X) %s  %s  [priority %d]", command, (found_msg)?m_ptr->name:" --- ",
				(((properties&JAUS_ACKNAK_FLAG) >> 4)!= 0)? val_to_str(((properties&JAUS_ACKNAK_FLAG) >> 4), acknak_flag, "Unknown") : "",
				(properties&JAUS_PRIORITY_FLAG));
	}

	if (tree) { /* we are being asked for a tree*/

		/* JAUS Main tree */
		jaus_item = proto_tree_add_protocol_format(tree, proto_jaus, tvb, 0, -1, "JAUS Robots, Msg: %s, is_Command: %s", (found_msg) ? m_ptr->name : "NotFoundInXML",
			(found_msg) ? ((m_ptr->is_command) ? "true" : "false") : "NotFoundInXML");
		jaus_tree = proto_item_add_subtree(jaus_item, ett_jaus);

		/* Legacy RA3 Header */
		/* Header Sub tree */
		jaus_sub_item = proto_tree_add_text(jaus_tree, tvb, offset, 16, "Message Header, Cmd: 0x%04X", command);
		jaus_header_tree = proto_item_add_subtree(jaus_sub_item, ett_jaus_header);
		/* Message Properties sub tree of Header */
		jaus_sub_item = proto_tree_add_text(jaus_header_tree, tvb, offset, 2, "Message Properties " );
		jaus_properties_tree = proto_item_add_subtree(jaus_sub_item, ett_jaus_message_properties);
		/* submit the priority parameter to Message Properties.  */
		proto_tree_add_item(jaus_properties_tree, hf_jaus_priority, tvb, offset, 1, LITTLE_ENDIAN);
		/* submit the acknak parameter to Message Properties.  */
		proto_tree_add_item(jaus_properties_tree, hf_jaus_acknak, tvb, offset, 1, LITTLE_ENDIAN);
		/* submit the service parameter to Message Properties.  */
		proto_tree_add_item(jaus_properties_tree, hf_jaus_service, tvb, offset, 1, LITTLE_ENDIAN);
		/* submit the experimental parameter to Message Properties.  */
		proto_tree_add_item(jaus_properties_tree, hf_jaus_experimental, tvb, offset, 1, LITTLE_ENDIAN);
	}
	offset += 1;

	if (tree) {
		/* submit the version parameter to Message Properties.  */
		proto_tree_add_item(jaus_properties_tree, hf_jaus_version, tvb, offset, 1, LITTLE_ENDIAN);
	}
	offset += 1;

	if (tree) {
		/* submit the commandCode parameter to Header sub tree. */
		proto_tree_add_uint_format(jaus_header_tree, hf_jaus_commandCode, tvb, offset, 2, command,
				"Command Code: %s (0x%04X)", (found_msg) ? m_ptr->name : "NotFoundInXML", command);
	}
	offset += 2;

	if (tree) {
		/* Destination Sub tree of Header. */
		jaus_sub_item = proto_tree_add_item(jaus_header_tree, hf_jaus_destination, tvb, offset, 4, LITTLE_ENDIAN);
		jaus_dest_tree = proto_item_add_subtree(jaus_sub_item, ett_jaus_destination);
		/* Dest IP split apart, added to Destnation */
		proto_tree_add_item(jaus_dest_tree, hf_jaus_dest_sub, tvb, offset, 4, LITTLE_ENDIAN);
		proto_tree_add_item(jaus_dest_tree, hf_jaus_dest_node, tvb, offset, 4, LITTLE_ENDIAN);
		proto_tree_add_item(jaus_dest_tree, hf_jaus_dest_comp, tvb, offset, 4, LITTLE_ENDIAN);
		proto_tree_add_item(jaus_dest_tree, hf_jaus_dest_inst, tvb, offset, 4, LITTLE_ENDIAN);
	}
	offset += 4;

	if (tree) {
		/* Source Sub tree of Header */
		jaus_sub_item = proto_tree_add_item(jaus_header_tree, hf_jaus_source, tvb, offset, 4, LITTLE_ENDIAN);
		jaus_src_tree = proto_item_add_subtree(jaus_sub_item, ett_jaus_source);
		/* Src IP split apart, added to Source */
		proto_tree_add_item(jaus_src_tree, hf_jaus_src_sub, tvb, offset, 4, LITTLE_ENDIAN);
		proto_tree_add_item(jaus_src_tree, hf_jaus_src_node, tvb, offset, 4, LITTLE_ENDIAN);
		proto_tree_add_item(jaus_src_tree, hf_jaus_src_comp, tvb, offset, 4, LITTLE_ENDIAN);
		proto_tree_add_item(jaus_src_tree, hf_jaus_src_inst, tvb, offset, 4, LITTLE_ENDIAN);
	}
	offset += 4;

	dataC = tvb_get_letohs(tvb,offset);

	if (tree) {
		/* dataControl Sub tree of Header. */
		jaus_sub_item = proto_tree_add_item(jaus_header_tree, hf_jaus_dataControl, tvb, offset, 2, LITTLE_ENDIAN);
		jaus_dataC_tree = proto_item_add_subtree(jaus_sub_item, ett_jaus_dataControl);
		/* DataC info split apart, added to dataControl */
		proto_tree_add_item(jaus_dataC_tree, hf_jaus_data_size, tvb, offset, 2, LITTLE_ENDIAN);
		proto_tree_add_item(jaus_dataC_tree, hf_jaus_data_flag, tvb, offset, 2, LITTLE_ENDIAN);
	}
	offset+=2;

	if (tree) {
		/* submit the sequenceNumber parameter to Header Sub tree. */
		proto_tree_add_item(jaus_header_tree, hf_jaus_sequenceNumber, tvb, offset, 2, LITTLE_ENDIAN);
	}
	offset+=2;

	if (tree) {
		/* show the rest of the Data in the message */
		bytes = tvb_length_remaining(tvb, offset);
		if (bytes > 0) {
			jaus_sub_item = proto_tree_add_text(jaus_tree, tvb, offset, bytes, "Data (%d byte%s)", bytes, plurality(bytes, "", "s"));
			jaus_data_tree = proto_item_add_subtree(jaus_sub_item, ett_jaus_data);

			if (bytes == (dataC & 0xfff)) {
				if (found_msg) {
					offset = dissect_message_data(tvb, jaus_data_tree, offset, m_ptr);
					if (offset < 0) {return data_offset;}
				} else {/* if no message found then show the data  */
					proto_item_append_text(jaus_sub_item, ", No message_def found in XML to dissect data");
					proto_tree_add_item(jaus_data_tree, hf_jaus_data, tvb, offset, bytes, FALSE);
				}
			} else {
				proto_item_append_text(jaus_sub_item, ", [ERROR Data remaining (%d) does not match Data Control Size (%d)]",
					bytes, (dataC & 0xfff));
				proto_tree_add_item(jaus_data_tree, hf_jaus_data, tvb, offset, bytes, FALSE);
			}
		}
	}

	return offset;
}

/**
 * Prints an Error message to the current tree location
 *    for the different types of errors
 *
 */
void print_error(tvbuff_t *tvb, proto_tree *tree, int error)
{
	if (error == -1) {
		proto_tree_add_text(tree, tvb, data_offset, -1, "[ERROR: Unkown data_type, check xml]");
	} else if (error == -2) {
		proto_tree_add_text(tree, tvb, data_offset, -1, "[ERROR: Malformed packet, not enough data]");
	}
}

int dissect_array(tvbuff_t *tvb, proto_tree *tree, array_t *a_ptr)
{
	proto_item *sub_item = NULL;
	proto_tree *sub_tree = NULL;

	dimension_t *d_ptr;

	int size = 1;
	int error;

	/* print bit_field value to bit_field tree of the tree*/
	sub_item = proto_tree_add_text(tree, tvb, data_offset, 1, "[ARRAY] %s (%s)",
		a_ptr->name, (a_ptr->optional) ? "optional" : "req");
	sub_tree = proto_item_add_subtree(sub_item, ett_array);


	proto_item_append_text(sub_item, ",[Dimension] ");
	d_ptr = a_ptr->dimension;
	while (d_ptr != NULL) {
		size *= d_ptr->size;
		proto_item_append_text(sub_item, "%s(%d)%s", d_ptr->name, d_ptr->size, (d_ptr->next == NULL)?" ":"x");
		d_ptr = d_ptr->next;
	}

	while (size != 0) {
		error = dissect_message_field(tvb, tree, a_ptr->field, 0, 0);
		if (error < 0) {return(error);}
		size--;
	}

	return(0);
}

int dissect_fixed_field(tvbuff_t *tvb, proto_tree *tree, fixed_field_t *ff_ptr)
{
	scale_range_t *sr_ptr;
	value_set_t *vs_ptr;
	value_range_t *vr_ptr;
	value_enum_t *ve_ptr;

	guint64 data;
	long datacopy;
	double c_data;
	int size;
	int found = 0;
	int error;

	sr_ptr = ff_ptr->scale_range;
	vs_ptr = ff_ptr->value_set;

	/* Get data field size and data from buffer */
	size = get_number_of_bytes(ff_ptr->field_type);
	if (size < 0) {print_error(tvb, tree, size); return(size);}
	error = get_data_from_tvb(tvb, data_offset, ff_ptr->field_type, size, &data);
	if (error < 0) {print_error(tvb, tree, error); return(error);}

	datacopy = (long) data;

	if (sr_ptr != NULL) { /* scale_range */
		c_data = scale_convert((unsigned int) data, (size * 8), sr_ptr->real_lower_limit, sr_ptr->real_upper_limit, sr_ptr->integer_function);

		proto_tree_add_uint64_format(tree, hf_jaus_uint64, tvb, data_offset, size, data, "[FFsr] %s: (%s) %f",
			ff_ptr->name, ff_ptr->field_units, c_data);

	} else if (vs_ptr != NULL) { /* value_set */

		vr_ptr = vs_ptr->value_range;
		ve_ptr = vs_ptr->value_enum;

		if (vs_ptr->offset_to_lower_limit == 0) { /* No offset_to_lower_limit */

			/* value_enum: Find matching enum to data(index) pulled from buffer */
			while (ve_ptr != NULL) {
				if (data == ve_ptr->enum_index) {
					proto_tree_add_uint64_format(tree, hf_jaus_uint64, tvb, data_offset, size, data, "[FFvs] %s: (%s) %s (%d)",
						ff_ptr->name, ff_ptr->field_units, ve_ptr->enum_const, data);
					found = 1; break;
				}
				ve_ptr = ve_ptr->next;
			}

			if (!found) {
				/* value_range */
				while (vr_ptr != NULL) {
					if (vr_ptr->lower_limit <= data && data <= vr_ptr->upper_limit) {
						if (strcmp(vr_ptr->interpretation,"none")) {
							proto_tree_add_uint64_format(tree, hf_jaus_uint64, tvb, data_offset, size, data, "[FFvs] %s: (%s) %s (%d)",
								ff_ptr->name, ff_ptr->field_units, vr_ptr->interpretation, data);
							found = 1; break;
						}
					}
					vr_ptr = vr_ptr->next;
				}
			}

			if (!found) {
				proto_tree_add_uint64_format(tree, hf_jaus_uint64, tvb, data_offset, size, data, "[FFvs] %s: (%s) %d (0x%X)",
					ff_ptr->name, ff_ptr->field_units, datacopy, data);
			}

		} else { /* offset_to_lower_limit */
			/* TODO */
			proto_tree_add_uint64_format(tree, hf_jaus_uint64, tvb, data_offset, size, data, "[FFvs] %s: (%s) 0x%X (offset->lower TODO)",
				ff_ptr->name, ff_ptr->field_units, data);
		}

	} else { /* fixed_field without a scale_range for value_set */
		/* test to see if unsigned or signed to print out different ways */
		if ( ff_ptr->field_type > PDT_LONG_INT)
			proto_tree_add_uint64_format(tree, hf_jaus_uint64, tvb, data_offset, size, data, "[FF] %s: (%s) %u (0x%X)",
				ff_ptr->name, ff_ptr->field_units, datacopy, data);
		else
			proto_tree_add_uint64_format(tree, hf_jaus_uint64, tvb, data_offset, size, data, "[FF] %s: (%s) %d (0x%X)",
				ff_ptr->name, ff_ptr->field_units, datacopy, data);
	}

	data_offset += size;
	return(0);
}

int dissect_variable_field(tvbuff_t *tvb, proto_tree *tree, variable_field_t *vf_ptr)
{
	proto_item *sub_item = NULL;
	proto_tree *sub_tree = NULL;

	type_and_units_enum_t *taue_ptr;
	scale_range_t *sr_ptr;
	value_set_t *vs_ptr;
	value_range_t *vr_ptr;
	value_enum_t *ve_ptr;

	guint64 data;
	int offset, size, total_size = 0;
	int found = 0;
	double c_data;
	int error;

	offset = data_offset;

	/* count the size of all taue fields in the variable_field
	* to be able to highlight the right number of bytes
	*/
	taue_ptr = vf_ptr->taue;
	while (taue_ptr != NULL) {
		size = get_number_of_bytes(taue_ptr->field_type);
		if (size < 0) {print_error(tvb, tree, size); return(size);}
		total_size += size;
		taue_ptr = taue_ptr->next;
	}

	sub_item = proto_tree_add_text(tree, tvb, offset, total_size, "[VF] %s (%s)",
		vf_ptr->name, (vf_ptr->optional) ? "optional" : "req");
	sub_tree = proto_item_add_subtree(sub_item, ett_jaus_data);

	taue_ptr = vf_ptr->taue;

	while (taue_ptr != NULL) {
		/* Get data field size and data from buffer */
		size = get_number_of_bytes(taue_ptr->field_type);
		if (size < 0) {print_error(tvb, tree, size); return(size);}
		error = get_data_from_tvb(tvb, data_offset, taue_ptr->field_type, size, &data);
		if (error < 0) {print_error(tvb, tree, error); return(error);}

		sr_ptr = taue_ptr->scale_range;
		vs_ptr = taue_ptr->value_set;

		if (sr_ptr != NULL) { /* scale_range */
			c_data = scale_convert((unsigned int) data, (size * 8), sr_ptr->real_lower_limit, sr_ptr->real_upper_limit, sr_ptr->integer_function);

			proto_tree_add_uint64_format(sub_tree, hf_jaus_uint64, tvb, offset, size, data, "[VFsr] index: %d (%s) %f",
				taue_ptr->index, taue_ptr->field_units, c_data);

		} else if (vs_ptr != NULL) { /* value_set */

			vr_ptr = vs_ptr->value_range;
			ve_ptr = vs_ptr->value_enum;

			if (vs_ptr->offset_to_lower_limit == 0) { /* No offset_to_lower_limit */

				/* value_enum: Find matching enum to data(index) pulled from buffer */
				while (ve_ptr != NULL) {
					if (data == ve_ptr->enum_index) {
						proto_tree_add_uint64_format(sub_tree, hf_jaus_uint64, tvb, offset, size, data, "[VFvs] index: %d (%s) %s (%d)",
							taue_ptr->index, taue_ptr->field_units, ve_ptr->enum_const, data);
						found = 1; break;
					}
					ve_ptr = ve_ptr->next;
				}

				if (!found) {
				/* value_range*/
					while (vr_ptr != NULL) {
						if (vr_ptr->lower_limit <= data && data <= vr_ptr->upper_limit) {
							if (strcmp(vr_ptr->interpretation,"none")) {
								proto_tree_add_uint64_format(sub_tree, hf_jaus_uint64, tvb, offset, size, data, "[VFvs] index: %d (%s) %s (%d)",
									taue_ptr->index, taue_ptr->field_units, vr_ptr->interpretation, data);
								found = 1; break;
							}
						}
						vr_ptr = vr_ptr->next;
					}
				}

				if (!found)
					proto_tree_add_uint64_format(sub_tree, hf_jaus_uint64, tvb, offset, size, data, "[VFvs] index: %d (%s) %d",
						taue_ptr->index, taue_ptr->field_units, data);

			} else { /* offset_to_lower_limit */
				/* TODO */
				proto_tree_add_uint64_format(sub_tree, hf_jaus_uint64, tvb, offset, size, data, "[VFvs] index: %d (%s) 0x%X (offset->lower TODO)",
					taue_ptr->index, taue_ptr->field_units, data);
			}

		} else {
			/* variable_field without a scale_range for value_set */
			proto_tree_add_uint64_format(sub_tree, hf_jaus_uint64, tvb, offset, size, data, "[VF] index: %d (%s) 0x%X",
				taue_ptr->index, taue_ptr->field_units, data);

		}

		offset += size;
		taue_ptr = taue_ptr->next;
	}

	data_offset = offset;
	return(0);
}

int dissect_bit_field(tvbuff_t *tvb, proto_tree *tree, bit_field_t *bf_ptr)
{
	proto_item *sub_item = NULL;
	proto_tree *sub_tree = NULL;

	sub_field_t *sf_ptr;
	value_range_t *vr_ptr;
	value_enum_t *ve_ptr;

	guint64 data, sub_data;
	int size, mask, found = 0;
	unsigned char count;
	int error;

	/* Get data field size and data from buffer */
	size = get_number_of_bytes(bf_ptr->field_type_unsigned);
	if (size < 0) {print_error(tvb, tree, size); return(size);}
	error = get_data_from_tvb(tvb, data_offset, bf_ptr->field_type_unsigned, size, &data);
	if (error < 0) {print_error(tvb, tree, error); return(error);}

	/* print bit_field value to bit_field tree of the tree*/
	sub_item = proto_tree_add_uint64_format(tree, hf_jaus_uint64, tvb, data_offset, size, data, "[BF] %s (%d) %s [0x%X]",
		bf_ptr->name, bf_ptr->field_type_unsigned, (bf_ptr->optional) ? "optional" : "req", data);
	sub_tree = proto_item_add_subtree(sub_item, ett_jaus_data);


	/* print sub_field values to a sub tree of bit_fields tree */
	sf_ptr = bf_ptr->sub_field;
	while (sf_ptr != NULL) {

		count = sf_ptr->to_index - sf_ptr->from_index;
		mask = 0x01;
		while (count > 0) {
			mask = (mask << 1) | 0x01;
			count--;
		}

		sub_data = (data >> sf_ptr->from_index) & mask;

		/* TODO check to see if sub_data is with-in value_range and/or has value_enum */
		/* value_set information */
		vr_ptr = sf_ptr->value_range;
		ve_ptr = sf_ptr->value_enum;

		/* value_enum: Find matching enum to data(index) pulled from buffer */
		while (ve_ptr != NULL) {
			if (sub_data == ve_ptr->enum_index) {
				proto_tree_add_uint64_format(sub_tree, hf_jaus_uint64, tvb, data_offset, size, sub_data, "[BFsf] %s: %s (%d)",
					sf_ptr->name, ve_ptr->enum_const, sub_data);
				found = 1; break;
			}
			ve_ptr = ve_ptr->next;
		}

		if (!found) {
			/* value_range */
			while (vr_ptr != NULL) {
				if (vr_ptr->lower_limit <= data && data <= vr_ptr->upper_limit) {
					if (strcmp(vr_ptr->interpretation,"none")) {
						proto_tree_add_uint64_format(sub_tree, hf_jaus_uint64, tvb, data_offset, size, sub_data, "[BFsf] %s: %s (%d)",
							sf_ptr->name, vr_ptr->interpretation, sub_data);
						found = 1; break;
					}
				}
				vr_ptr = vr_ptr->next;
			}
		}

		if (!found)
			proto_tree_add_uint64_format(sub_tree, hf_jaus_uint64, tvb, data_offset, size, sub_data, "[BFsf] %s: %d",
				sf_ptr->name, sub_data);

		sf_ptr = sf_ptr->next;
	}

	data_offset += size;
	return(0);
}

int dissect_fixed_length_string(tvbuff_t *tvb, proto_tree *tree, fixed_length_string_t *fls_ptr)
{
	guint8 *string;
	unsigned int size;

	/* Get data field size and data from buffer */
	size = fls_ptr->string_length;

	if ((unsigned int)tvb_length_remaining(tvb, data_offset) < size) {
		print_error(tvb, tree, -2); /* malformed packet likely */
		return(-2);
	}

	string = tvb_get_ephemeral_string(tvb, data_offset, size);

	proto_tree_add_text(tree, tvb, data_offset, size, "[FLS] %s(%d): %s",
			fls_ptr->name, size, string);

	data_offset += size;
	return(0);
}

int dissect_variable_length_string(tvbuff_t *tvb, proto_tree *tree, variable_length_string_t *vls_ptr)
{
	count_field_t *cf_ptr;

	guint8 *string;
	guint64 string_length;
	int size, offset;
	int error;

	offset = data_offset;

	cf_ptr = vls_ptr->count_field;

	/* Get data field size and data from buffer */
	size = get_number_of_bytes(cf_ptr->field_type_unsigned);
	if (size < 0) {print_error(tvb, tree, size); return(size);}
	error = get_data_from_tvb(tvb, offset, cf_ptr->field_type_unsigned, size, &string_length);
	if (error < 0) {print_error(tvb, tree, error); return(error);}

	/* TODO? check min_count and max_count for count_field */

	offset += size;

	string = tvb_get_ephemeral_string(tvb, offset , (gint)string_length);

	proto_tree_add_text(tree, tvb, offset, ((int)string_length + size), "[VLS] %s(%d): %s",
			vls_ptr->name, string_length, string);

	data_offset = (offset + (int)string_length);
	return(0);
}

int dissect_variable_length_field(tvbuff_t *tvb, proto_tree *tree, variable_length_field_t *vlf_ptr)
{
	count_field_t *cf_ptr;

	guint8 *data;
	guint64 field_length;
	int size, offset;
	int error;

	offset = data_offset;

	cf_ptr = vlf_ptr->count_field;

	/* Get data field size and data from buffer */
	size = get_number_of_bytes(cf_ptr->field_type_unsigned);
	if (size < 0) {print_error(tvb, tree, size); return(size);}
	error = get_data_from_tvb(tvb, offset, cf_ptr->field_type_unsigned, size, &field_length);
	if (error < 0) {print_error(tvb, tree, error); return(error);}


	/* TODO? check min_count and max_count for count_field */

	offset += size;

	data = ep_tvb_memdup(tvb, offset, (int)field_length);

	proto_tree_add_bytes_format(tree, hf_jaus_uint64, tvb, offset, ((int)field_length + size), data, "[VLF] %s: (%s) 0x%X",
		vlf_ptr->name, vlf_ptr->field_format, data);

	data_offset = (offset + (int)field_length);
	return(0);
}

int dissect_variable_format_field(tvbuff_t *tvb, proto_tree *tree, variable_format_field_t *vff_ptr)
{
	format_enum_t *fe_ptr;
	count_field_t *cf_ptr;

	guint64 data;
	int size, found_fe = 0;
	int error;

	fe_ptr = vff_ptr->format_enum;
	cf_ptr = vff_ptr->count_field;

	/* Get data field size and data from buffer */
	size = get_number_of_bytes(cf_ptr->field_type_unsigned);
	if (size < 0) {print_error(tvb, tree, size); return(size);}
	error = get_data_from_tvb(tvb, data_offset, cf_ptr->field_type_unsigned, size, &data);
	if (error < 0) {print_error(tvb, tree, error); return(error);}


	/* Find matching format_enum to data(index) pulled from buffer */
	while (fe_ptr != NULL) {
		if (data == fe_ptr->index) {
			found_fe = 1; break;
		}
		fe_ptr = fe_ptr->next;
	}

	/* print with enum field_format name else error? with just the data from buffer */
	proto_tree_add_uint64_format(tree, hf_jaus_uint64, tvb, data_offset, size, data, "[VFF] %s (%d) %s [index: %d]",
		vff_ptr->name, cf_ptr->field_type_unsigned, (found_fe)? fe_ptr->field_format : "" , data);

	data_offset += size;
	return(0);
}

int dissect_message_field(tvbuff_t *tvb, proto_tree *tree, field_t *f_ptr, int _op_count, guint64 presence_vector)
{
	array_t *a_ptr;
	fixed_field_t *ff_ptr;
	variable_field_t *vf_ptr;
	bit_field_t *bf_ptr;
	fixed_length_string_t *fls_ptr;
	variable_length_string_t *vls_ptr;
	variable_length_field_t *vlf_ptr;
	variable_format_field_t *vff_ptr;

	int op_count = _op_count;
	int error;

	switch (f_ptr->type) {
		case ARRAY_TYPE:
			a_ptr = (array_t *)f_ptr->field;
			if ((!a_ptr->optional) || ((presence_vector >> op_count) & 0x01)) {

				error = dissect_array(tvb, tree, a_ptr);
				if (error < 0) {return(error);}

			}
			if (a_ptr->optional)
				op_count++;
			break;
		case FIXED_FIELD_TYPE:
			ff_ptr = (fixed_field_t *)f_ptr->field;
			if ((!ff_ptr->optional) || ((presence_vector >> op_count) & 0x01)) {

				error = dissect_fixed_field(tvb, tree, ff_ptr);
				if (error < 0) {return(error);}

			}
			if (ff_ptr->optional)
				op_count++;
			break;
		case VARIABLE_FIELD_TYPE:
			vf_ptr = (variable_field_t *)f_ptr->field;
			if ((!vf_ptr->optional) || ((presence_vector >> op_count) & 0x01)) {

				error = dissect_variable_field(tvb, tree, vf_ptr);
				if (error < 0) {return(error);}

			}
			if (vf_ptr->optional)
				op_count++;
			break;
		case BIT_FIELD_TYPE:
			bf_ptr = (bit_field_t *)f_ptr->field;
			if ((!bf_ptr->optional) || ((presence_vector >> op_count) & 0x01)) {

				error = dissect_bit_field(tvb, tree, bf_ptr);
				if (error < 0) {return(error);}

			}
			if (bf_ptr->optional)
				op_count++;
			break;
		case FIXED_LENGTH_STRING_TYPE:
			fls_ptr = (fixed_length_string_t *)f_ptr->field;
			if ((!fls_ptr->optional) || ((presence_vector >> op_count) & 0x01)) {

				error = dissect_fixed_length_string(tvb, tree, fls_ptr);
				if (error < 0) {return(error);}

			}
			if (fls_ptr->optional)
				op_count++;
			break;
		case VARIABLE_LENGTH_STRING_TYPE:
			vls_ptr = (variable_length_string_t *)f_ptr->field;
			if ((!vls_ptr->optional) || ((presence_vector >> op_count) & 0x01)) {

				error = dissect_variable_length_string(tvb, tree, vls_ptr);
				if (error < 0) {return(error);}

			}
			if (vls_ptr->optional)
				op_count++;
			break;
		case VARIABLE_LENGTH_FIELD_TYPE:
			vlf_ptr = (variable_length_field_t *)f_ptr->field;
			if ((!vlf_ptr->optional) || ((presence_vector >> op_count) & 0x01)) {

				error = dissect_variable_length_field(tvb, tree, vlf_ptr);
				if (error < 0) {return(error);}

			}
			if (vlf_ptr->optional)
				op_count++;
			break;
		case VARIABLE_FORMAT_FIELD_TYPE:
			vff_ptr = (variable_format_field_t *)f_ptr->field;
			if ((!vff_ptr->optional) || ((presence_vector >> op_count) & 0x01)) {

				error = dissect_variable_format_field(tvb, tree, vff_ptr);
				if (error < 0) {return(error);}

			}
			if (vff_ptr->optional)
				op_count++;
			break;
		default:
			proto_tree_add_text(tree, tvb, data_offset, -1, "No supported printout for field_type");
	}
	return(op_count);
}

int dissect_message_comp(tvbuff_t *tvb, proto_tree *tree, field_t *f_ptr, int _c_op_count, guint64 presence_vector)
{
	proto_item *sub_item = NULL;
	proto_tree *sub_tree = NULL;

	field_t *lf_ptr;

	record_t *r_ptr;
	list_t *l_ptr;
		count_field_t *cf_ptr;
	variant_t *v_ptr;
	sequence_t *s_ptr;

	guint64 data, l_presence_vector = 0;

	int op_count, c_op_count, size;
	int error;

	c_op_count = _c_op_count;

	switch (f_ptr->type) {
		case RECORD_TYPE:
			r_ptr = (record_t *)f_ptr->field;
			if ((!r_ptr->optional) || ((presence_vector >> c_op_count) & 0x01)) {
				/* record subtree of the tree*/
				sub_item = proto_tree_add_text(tree, tvb, data_offset, 1, "[RECORD]: %s (%s)", r_ptr->name,
					(r_ptr->optional) ? "optional" : "req");
				sub_tree = proto_item_add_subtree(sub_item, ett_jaus_data);

				/* check to see if there is a presence_vector for this message */
				if (r_ptr->presence_vector_type != PDT_OP) {
					size = get_number_of_bytes(r_ptr->presence_vector_type);
					if (size < 0) {print_error(tvb, tree, size); return(size);}
					error = get_data_from_tvb(tvb, data_offset, r_ptr->presence_vector_type, size, &l_presence_vector);
					if (error < 0) {print_error(tvb, tree, error); return(error);}
					proto_tree_add_uint64_format(sub_tree, hf_jaus_uint64, tvb, data_offset, size, l_presence_vector, "[PV] (%d) 0x%X",
						r_ptr->presence_vector_type, l_presence_vector);
					data_offset += size;
				}
				op_count = 0;

				lf_ptr = r_ptr->field;
				while (lf_ptr != NULL) {
					op_count = dissect_message_field(tvb, sub_tree, lf_ptr, op_count, l_presence_vector);
					if (op_count < 0) {return(op_count);}
					lf_ptr = lf_ptr->next;
				}
			}
			if (r_ptr->optional)
				c_op_count++;
			break;
		case LIST_TYPE:
			l_ptr = (list_t *)f_ptr->field;
			if ((!l_ptr->optional) || ((presence_vector >> c_op_count) & 0x01)) {
				/* list subtree of the tree*/
				sub_item = proto_tree_add_text(tree, tvb, data_offset, 1, "[LIST]: %s (%s)", l_ptr->name,
					(l_ptr->optional) ? "optional" : "req");
				sub_tree = proto_item_add_subtree(sub_item, ett_jaus_data);


				/* count_field data */
				cf_ptr = l_ptr->count_field;
				size = get_number_of_bytes(cf_ptr->field_type_unsigned);
				if (size < 0) {print_error(tvb, tree, size); return(size);}
				error = get_data_from_tvb(tvb, data_offset, cf_ptr->field_type_unsigned, size, &data);
				if (error < 0) {print_error(tvb, tree, error); return(error);}
				proto_tree_add_uint64_format(sub_tree, hf_jaus_uint64, tvb, data_offset, size, data, "[CF] (%d) min: %d, max: %d, count: %u",
					cf_ptr->field_type_unsigned, cf_ptr->min_count, cf_ptr->max_count, data);
				data_offset += size;

				lf_ptr = l_ptr->field;
				if (cf_ptr->min_count != 0 && cf_ptr->max_count != 0) {
					if (cf_ptr->min_count <= data && data <= cf_ptr->max_count) {
						while (data != 0) {
								c_op_count = dissect_message_comp(tvb, sub_tree, lf_ptr, c_op_count, l_presence_vector);
								if (c_op_count < 0) {return(c_op_count);}
								data--;
						}
					} else {
						proto_tree_add_text(sub_tree, tvb, data_offset, 1, "[ERROR]: count out of range (%d)", data);
						break;
					}
				} else {
					while (data != 0) {
						if (lf_ptr != NULL) {
							c_op_count = dissect_message_comp(tvb, sub_tree, lf_ptr, c_op_count, l_presence_vector);
							if (c_op_count < 0) {return(c_op_count);}
							data--;
						}
					}
				}
			}
			if (l_ptr->optional)
				c_op_count++;
			break;
		case VARIANT_TYPE:
			v_ptr = (variant_t *)f_ptr->field;
			if ((!v_ptr->optional) || ((presence_vector >> c_op_count) & 0x01)) {
				/* variant subtree of the tree*/
				sub_item = proto_tree_add_text(tree, tvb, data_offset, 1, "[VARIANT]: %s (%s)", v_ptr->name,
					(v_ptr->optional) ? "optional" : "req");
				sub_tree = proto_item_add_subtree(sub_item, ett_jaus_data);

				/* vtag_field data */
				size = get_number_of_bytes(v_ptr->field_type_unsigned);
				if (size < 0) {print_error(tvb, tree, size); return(size);}
				error = get_data_from_tvb(tvb, data_offset, v_ptr->field_type_unsigned, size, &data);
				if (error < 0) {print_error(tvb, tree, error); return(error);}
				proto_tree_add_uint64_format(sub_tree, hf_jaus_uint64, tvb, data_offset, size, data, "[VTAG] (%d) min: %d, max: %d, count: %u",
					v_ptr->field_type_unsigned, v_ptr->min_count, v_ptr->max_count, data);
				data_offset += size;

				lf_ptr = v_ptr->field;
				while (data != 0) {
					lf_ptr = lf_ptr->next;
					data--;
				}
				if (lf_ptr != NULL) {
					c_op_count = dissect_message_comp(tvb, tree, lf_ptr, c_op_count, l_presence_vector);
					if (c_op_count < 0) {return(c_op_count);}
				}
			}
			if (v_ptr->optional)
				c_op_count++;
			break;
		case SEQUENCE_TYPE:
			s_ptr = (sequence_t *)f_ptr->field;
			if ((!s_ptr->optional) || ((presence_vector >> c_op_count) & 0x01)) {
				/* sequence subtree of the tree*/
				sub_item = proto_tree_add_text(tree, tvb, data_offset, 1, "[SEQUENCE]: %s (%s)", s_ptr->name,
					(s_ptr->optional) ? "optional" : "req");
				sub_tree = proto_item_add_subtree(sub_item, ett_jaus_data);


				/* check to see if there is a presence_vector for this message */
				if (s_ptr->presence_vector_type != PDT_OP) {
					size = get_number_of_bytes(s_ptr->presence_vector_type);
					if (size < 0) {print_error(tvb, tree, size); return(size);}
					error = get_data_from_tvb(tvb, data_offset, s_ptr->presence_vector_type, size, &l_presence_vector);
					if (error < 0) {print_error(tvb, tree, error); return(error);}
					proto_tree_add_uint64_format(sub_tree, hf_jaus_uint64, tvb, data_offset, size, l_presence_vector, "[PV] (%d) 0x%X",
						s_ptr->presence_vector_type, l_presence_vector);
					data_offset += size;
				}

				c_op_count = 0;
				lf_ptr = s_ptr->field;
				while (lf_ptr != NULL) {
					c_op_count = dissect_message_comp(tvb, tree, lf_ptr, c_op_count, l_presence_vector);
					if (c_op_count < 0) {return(c_op_count);}
					lf_ptr = lf_ptr->next;
				}
			}
			if (s_ptr->optional)
				c_op_count++;
			break;
		default:
			proto_tree_add_text(tree, tvb, data_offset, -1, "No supported printout for Composite field_type");
	}
	return(c_op_count);
}

int dissect_message_data(tvbuff_t *tvb, proto_tree *tree, int offset, message_def_t *m_ptr)
{
	field_t *f_ptr;
	int error = 0;

	data_offset = offset;

	/* working with body of type record only right now */
	f_ptr = m_ptr->body;
	if (f_ptr != NULL) {

		error = dissect_message_comp(tvb, tree, f_ptr, 0, 0);
		if (error < 0) {return(error);}

	} else {/* message found but no body record and data available */
		proto_tree_add_item(tree, hf_jaus_data, tvb, offset, -1, FALSE);
	}

	return(data_offset);
}

/**
 * Returns the number of bytes based of the data type.
 *
 */
int get_number_of_bytes(char type)
{
	if (type == PDT_BAD) {
		return(-1); /* Bad Primitive Data Type, error with xml likely */
	}

	if (type == PDT_BYTE || type == PDT_UBYTE)
		return(1);
	else if (type == PDT_SHORT_INT || type == PDT_USHORT_INT)
		return(2);
	else if (type == PDT_INT || type == PDT_UINT || type == PDT_FLOAT)
		return(4);
	else /* if (type == PDT_LONG_INT || type == PDT_ULONG_INT || type == PDT_LONG_FLOAT) */
		return(8);
}

/**
 * Returns data from the buffer of size based of the type.
 *  always of type guint64, cast as needed.
 */
int get_data_from_tvb(tvbuff_t *tvb, int offset, char type, int size, guint64 *data)
{
	if (type == PDT_BAD) {
		return(-1); /* Bad Primitive Data Type, error with xml likely */
	}

	if (tvb_length_remaining(tvb, offset) < size) {
		return(-2); /* malformed packet likely */
	}

	if (type == PDT_BYTE || type == PDT_UBYTE)
		*data = (guint64)tvb_get_guint8(tvb, offset);
	else if (type == PDT_SHORT_INT || type == PDT_USHORT_INT)
		*data = (guint64)tvb_get_letohs(tvb, offset);/* Little-Endian-to-host-order accessors */
	else if (type == PDT_INT || type == PDT_UINT || type == PDT_FLOAT)
		*data = (guint64)tvb_get_letohl(tvb, offset);
	else /* if (type == PDT_LONG_INT || type == PDT_ULONG_INT || type == PDT_LONG_FLOAT) */
		*data = tvb_get_letoh64(tvb, offset);

	return(0);
}

/**
 * Used to convert a scaled int value to real double value for scale_range.
 *  Uses the real_lower to determine if signed or unsigned for now.
 *
 *  Returns real_value depending on int_function (floor, ceil, round).
 */
double scale_convert(unsigned int scaled_value, int bits, double real_lower, double real_upper, char int_function)
{
	double integer_range;
	double scale_factor;
	double bias;
	double real_value;

	if (real_lower >= 0 ) {
		integer_range = pow(2,bits) - 1;

		scale_factor = (real_upper - real_lower) / integer_range;

		bias = real_lower;

		real_value = scaled_value * scale_factor + bias;
	} else {
		/* signed */
		integer_range = pow(2,(bits-1)) - 1;

		scale_factor = (real_upper - real_lower) / integer_range;

		bias = (real_upper + real_lower) / 2;

		real_value = ((int)scaled_value / 2) * scale_factor + bias;
	}

	if (int_function == ROUND)
		return(real_value);
	else if (int_function == FLOOR)
		return(floor(real_value));
	else return(ceil(real_value));
}
