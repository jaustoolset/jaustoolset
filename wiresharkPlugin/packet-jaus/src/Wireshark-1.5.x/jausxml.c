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

/* jausxml.c
 * Support Routines for Joint Architecture for Unmanned Systems (JAUS) dissection.
 * 
 * Updated 20 Jan 2011
 *    - Linux updates
 * Bug Fix 21 April 2011
 *  Alex Evans <alex_evans@wintec-inc.com>
 */

#include <stdio.h>
//#include <stdlib.h>
//#include <math.h>
#include <string.h>
#include <dirent.h>

#if _WIN32
#include <windows.h>
#else
#include <unistd.h>
#endif

#include "jausxml.h"

#define DEBUG		0	/* Debug printouts if 1 not 0 */
#define DEBUG1		1	/* printout to file messages read if 1 not 0 */
#define DEBUG2		1	/* printout to file message tree if 1 not 0 */

message_def_t *message_set = NULL;
FILE *out;

/**
 * Add a new message_def to the message_set.
 *
 *
 */
message_def_t *add_message_def(char *name, unsigned short message_id, unsigned char is_command)
{
	message_def_t *tmp_ptr;

	if (DEBUG) printf("add_message_def(): %s\n", name);

	if (message_set == NULL) {
		message_set = (message_def_t *)malloc(sizeof(message_def_t));

		tmp_ptr = message_set;
	}
	else {
		tmp_ptr = message_set;

		while(tmp_ptr->next != NULL)
			tmp_ptr = tmp_ptr->next;

		tmp_ptr->next = (message_def_t *)malloc(sizeof(message_def_t));

		tmp_ptr = tmp_ptr->next;
	}

	tmp_ptr->message_id = message_id;
	strncpy(tmp_ptr->name, name, 64);
	tmp_ptr->is_command = is_command;
	tmp_ptr->header = NULL;
	tmp_ptr->body = NULL;
	tmp_ptr->footer = NULL;
	tmp_ptr->next = NULL;

	return tmp_ptr;
}

/**
 * Add a field to any field_t pointer.
 *
 *
 */
void *add_field(field_t **field, unsigned char field_type)
{
	field_t *tmp_field;
	int field_size;

	if (DEBUG) printf("add_field(): field_type: %d\n", field_type);

	switch (field_type) {
		case ARRAY_TYPE:
			field_size = sizeof(array_t); break;
		case RECORD_TYPE:
			field_size = sizeof(record_t); break;
		case LIST_TYPE:
			field_size = sizeof(list_t); break;
		case VARIANT_TYPE:
			field_size = sizeof(variant_t); break;
		case SEQUENCE_TYPE:
			field_size = sizeof(sequence_t); break;
		case FIXED_FIELD_TYPE:
			field_size = sizeof(fixed_field_t); break;
		case VARIABLE_FIELD_TYPE:
			field_size = sizeof(variable_field_t); break;
		case BIT_FIELD_TYPE:
			field_size = sizeof(bit_field_t); break;
		case FIXED_LENGTH_STRING_TYPE:
			field_size = sizeof(fixed_length_string_t); break;
		case VARIABLE_LENGTH_STRING_TYPE:
			field_size = sizeof(variable_length_string_t); break;
		case VARIABLE_LENGTH_FIELD_TYPE:
			field_size = sizeof(variable_length_field_t); break;
		case VARIABLE_FORMAT_FIELD_TYPE:
			field_size = sizeof(variable_format_field_t); break;
		default:
			return(NULL);
	}

	if ((*field) == NULL) { /* first field found */
		if (DEBUG) printf("add_field(): first field  %d\n", field_size);

		(*field) = (field_t *)malloc(sizeof(field_t));
		(*field)->type = field_type;
		(*field)->field = malloc(field_size);
		(*field)->next = NULL;

		if (DEBUG) printf("add_field(): exit 1\n");
		return((*field)->field);

	} else { /* another field found */
		tmp_field = (*field);
		if (DEBUG) printf("add_field(): another field  %d\n", field_size);

		while (tmp_field->next != NULL) {
			tmp_field = tmp_field->next;
		}

		tmp_field->next = (field_t *)malloc(sizeof(field_t));
		tmp_field->next->type = field_type;
		tmp_field->next->field = malloc(field_size);
		tmp_field->next->next = NULL;

		if (DEBUG) printf("add_field(): exit 2\n");
		return(tmp_field->next->field);
	}
	if (DEBUG) printf("add_field(): BAD exit\n");
	return(NULL);
}

/**
 * Parses a message_def element.
 *   Add it to the message_set
 *   Read all fields
 *
 */
void parse_message_def(xmlNode * message_node)
{
	xmlAttr *cur_node = NULL;
	xmlNode *next_node = NULL;

	message_def_t *message_def;

	char *name;
	unsigned short id;
	unsigned char command;

	if (DEBUG) printf("parse_message_def(): Enter\n");

	/* Get the attributes for message_def */
	for (cur_node = message_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"name"))) {
		    name = (char *)get_attr_value(cur_node);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"message_id"))) {
			id = (unsigned short)strtol((char *)get_attr_value(cur_node), NULL, 16);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"is_command"))) {
			if ((!xmlStrcmp(get_attr_value(cur_node), (const xmlChar *)"true")))
				command = 1;
			else
				command = 0;
 	    }
	}

	message_def = add_message_def(name, id, command);

	/* read in all fields for this message */

	/* header record */

	/* body record */
	next_node = find_child_node(message_node, "body");
	for (next_node = next_node->children; next_node; next_node = next_node->next) {
        if (next_node->type == XML_ELEMENT_NODE) {
			if ((!xmlStrcmp(next_node->name, (const xmlChar *)"record")))
				parse_record(next_node, (record_t *)add_field(&message_def->body, RECORD_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"list")))
				parse_list(next_node, (list_t *)add_field(&message_def->body, LIST_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"variant")))
				parse_variant(next_node, (variant_t *)add_field(&message_def->body, VARIANT_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"sequence")))
				parse_sequence(next_node, (sequence_t *)add_field(&message_def->body, SEQUENCE_TYPE));
		}
    }

	/* footer record */

}

/**
 * Parses a record element.
 *
 *   Read all fields
 *
 */
void parse_record(xmlNode *record_node, record_t *record)
{
	xmlAttr *cur_node = NULL;
	xmlNode *next_node = NULL;

	if (DEBUG) printf("parse_record(): Enter\n");

	/* Get the attributes for record */
	for (cur_node = record_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"name"))) {
		    strncpy(record->name, (char *)get_attr_value(cur_node), 64);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"optional"))) {
			if ((!xmlStrcmp(get_attr_value(cur_node), (const xmlChar *)"true")))
				record->optional = 1;
			else
				record->optional = 0;
 	    }
	}

	record->field = NULL;
	record->presence_vector_type = PDT_OP;

	/* read in all fields for this message */

	/* possible fields */
	for (next_node = record_node->children; next_node; next_node = next_node->next) {
        if (next_node->type == XML_ELEMENT_NODE) {
			if ((!xmlStrcmp(next_node->name, (const xmlChar *)"presence_vector"))) /* optional */
				for (cur_node = next_node->properties; cur_node; cur_node = cur_node->next) {
				    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"field_type_unsigned"))) {
					    record->presence_vector_type = get_field_type((char *)get_attr_value(cur_node));
			 	    }
				}
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"array")))
				parse_array(next_node, (array_t *)add_field(&record->field, ARRAY_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"fixed_field")))
				parse_fixed_field(next_node, (fixed_field_t *)add_field(&record->field, FIXED_FIELD_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"variable_field")))
				parse_variable_field(next_node, (variable_field_t *)add_field(&record->field, VARIABLE_FIELD_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"bit_field")))
				parse_bit_field(next_node, (bit_field_t *)add_field(&record->field, BIT_FIELD_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"fixed_length_string")))
				parse_fixed_length_string(next_node, (fixed_length_string_t *)add_field(&record->field, FIXED_LENGTH_STRING_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"variable_length_string")))
				parse_variable_length_string(next_node, (variable_length_string_t *)add_field(&record->field, VARIABLE_LENGTH_STRING_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"variable_length_field")))
				parse_variable_length_field(next_node, (variable_length_field_t *)add_field(&record->field, VARIABLE_LENGTH_FIELD_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"variable_format_field")))
				parse_variable_format_field(next_node, (variable_format_field_t *)add_field(&record->field, VARIABLE_FORMAT_FIELD_TYPE));
		}
    }

	if (DEBUG) printf("parse_record(): Exit\n");
}

/**
 * Parses an array element.
 *   Read all fields
 *   Add it to the record
 *
 *
 */
void parse_array(xmlNode * a_node, array_t *array)
{
	xmlAttr *cur_node = NULL;
	xmlNode *next_node = NULL;

	dimension_t *tmp_ptr;

	if (DEBUG) printf("parse_array(): Enter\n");

	/* Get the attributes for fixed_field */
	for (cur_node = a_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"name"))) {
		    strncpy(array->name, (char *)get_attr_value(cur_node), 64);
 	    }
		/* optional
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"interpretation"))) {
			strncpy(array->interpretation, (char *)get_attr_value(cur_node), 64);
 	    } */
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"optional"))) {
			if ((!xmlStrcmp(get_attr_value(cur_node), (const xmlChar *)"true")))
				array->optional = 1;
			else
				array->optional = 0;
 	    }
	}

	array->field = NULL;
	array->dimension = NULL;

	/* possible fields */
	for (next_node = a_node->children; next_node; next_node = next_node->next) {
        if (next_node->type == XML_ELEMENT_NODE) {
			if ((!xmlStrcmp(next_node->name, (const xmlChar *)"fixed_field")))
				parse_fixed_field(next_node, (fixed_field_t *)add_field(&array->field, FIXED_FIELD_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"variable_field")))
				parse_variable_field(next_node, (variable_field_t *)add_field(&array->field, VARIABLE_FIELD_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"bit_field")))
				parse_bit_field(next_node, (bit_field_t *)add_field(&array->field, BIT_FIELD_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"fixed_length_string")))
				parse_fixed_length_string(next_node, (fixed_length_string_t *)add_field(&array->field, FIXED_LENGTH_STRING_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"variable_length_string")))
				parse_variable_length_string(next_node, (variable_length_string_t *)add_field(&array->field, VARIABLE_LENGTH_STRING_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"variable_length_field")))
				parse_variable_length_field(next_node, (variable_length_field_t *)add_field(&array->field, VARIABLE_LENGTH_FIELD_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"variable_format_field")))
				parse_variable_format_field(next_node, (variable_format_field_t *)add_field(&array->field, VARIABLE_FORMAT_FIELD_TYPE));

			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"dimension"))) {
				if (array->dimension == NULL) { /* first dimension found */

					array->dimension = (dimension_t *)malloc(sizeof(dimension_t));
					array->dimension->next = NULL;
					parse_dimension(next_node, array->dimension);

				} else { /* another dimension found */

					tmp_ptr = array->dimension;

					while (tmp_ptr->next != NULL)
						tmp_ptr = tmp_ptr->next;

					tmp_ptr->next = (dimension_t *)malloc(sizeof(dimension_t));

					tmp_ptr->next->next = NULL;
					parse_dimension(next_node, tmp_ptr->next);
				}
			}
		}
    }
}

/**
 * Parses a dimension element.
 *
 *
 */
void parse_dimension(xmlNode *d_node, dimension_t *dimension)
{
	xmlAttr *cur_node = NULL;

	if (DEBUG) printf("parse_dimension(): Enter\n");

	for (cur_node = d_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"name"))) {
		    strncpy(dimension->name, (char *)get_attr_value(cur_node), 64);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"size"))) {
			dimension->size = (unsigned int)strtol((char *)get_attr_value(cur_node), NULL, 10);
 	    }
		/* optional
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"interpretation"))) {
			strncpy(dimension->interpretation, (char *)get_attr_value(cur_node), 64);
 	    } */
	}
}

/**
 * Parses a list element.
 *
 *   Read all fields
 *
 */
void parse_list(xmlNode *l_node, list_t *list)
{
	xmlAttr *cur_node = NULL;
	xmlNode *next_node = NULL;

	if (DEBUG) printf("parse_list(): Enter\n");

	for (cur_node = l_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"name"))) {
		    strncpy(list->name, (char *)get_attr_value(cur_node), 64);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"optional"))) {
			if ((!xmlStrcmp(get_attr_value(cur_node), (const xmlChar *)"true")))
				list->optional = 1;
			else
				list->optional = 0;
 	    }
		/* optional
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"interpretation"))) {
			strncpy(list->interpretation, (char *)get_attr_value(cur_node), 64);
 	    } */
	}

	list->count_field = NULL;
	list->field = NULL;

	next_node = find_child_node(l_node, "count_field");

	if (next_node != NULL) {
		list->count_field = (count_field_t *)malloc(sizeof(count_field_t));
		parse_count_field(next_node, list->count_field);
	}

	for (next_node = l_node->children; next_node; next_node = next_node->next) {
        if (next_node->type == XML_ELEMENT_NODE) {
			if ((!xmlStrcmp(next_node->name, (const xmlChar *)"record")))
				parse_record(next_node, (record_t *)add_field(&list->field, RECORD_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"list")))
				parse_list(next_node, (list_t *)add_field(&list->field, LIST_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"variant")))
				parse_variant(next_node, (variant_t *)add_field(&list->field, VARIANT_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"sequence")))
				parse_sequence(next_node, (sequence_t *)add_field(&list->field, SEQUENCE_TYPE));
		}
    }

}

/**
 * Parses a variant element.
 *
 *   Read all fields
 *
 */
void parse_variant(xmlNode *v_node, variant_t *variant)
{
	xmlAttr *cur_node = NULL;
	xmlNode *next_node = NULL;

	if (DEBUG) printf("parse_variant(): Enter\n");

	for (cur_node = v_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"name"))) {
		    strncpy(variant->name, (char *)get_attr_value(cur_node), 64);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"optional"))) {
			if ((!xmlStrcmp(get_attr_value(cur_node), (const xmlChar *)"true")))
				variant->optional = 1;
			else
				variant->optional = 0;
 	    }
		/* optional
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"interpretation"))) {
			strncpy(variant->interpretation, (char *)get_attr_value(cur_node), 64);
 	    } */
	}

	variant->min_count = 0;
	variant->max_count = 0;
	variant->field = NULL;

	next_node = find_child_node(v_node, "vtag_field");

	/* Get the attributes for vtag_field */
	for (cur_node = next_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"field_type_unsigned"))) {
		    variant->field_type_unsigned =  get_field_type((char *)get_attr_value(cur_node));
 	    }
		/* optional */
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"min_count"))) {
			variant->min_count = (unsigned int)strtol((char *)get_attr_value(cur_node), NULL, 10);
 	    }
		/* optional */
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"max_count"))) {
			variant->max_count = (unsigned int)strtol((char *)get_attr_value(cur_node), NULL, 10);
 	    }
	}

	for (next_node = v_node->children; next_node; next_node = next_node->next) {
        if (next_node->type == XML_ELEMENT_NODE) {
			if ((!xmlStrcmp(next_node->name, (const xmlChar *)"record")))
				parse_record(next_node, (record_t *)add_field(&variant->field, RECORD_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"list")))
				parse_list(next_node, (list_t *)add_field(&variant->field, LIST_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"variant")))
				parse_variant(next_node, (variant_t *)add_field(&variant->field, VARIANT_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"sequence")))
				parse_sequence(next_node, (sequence_t *)add_field(&variant->field, SEQUENCE_TYPE));
		}
    }
}

/**
 * Parses a sequence element.
 *
 *   Read all fields
 *
 */
void parse_sequence(xmlNode *s_node, sequence_t *sequence)
{
	xmlAttr *cur_node = NULL;
	xmlNode *next_node = NULL;

	if (DEBUG) printf("parse_sequence(): Enter\n");

	for (cur_node = s_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"name"))) {
		    strncpy(sequence->name, (char *)get_attr_value(cur_node), 64);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"optional"))) {
			if ((!xmlStrcmp(get_attr_value(cur_node), (const xmlChar *)"true")))
				sequence->optional = 1;
			else
				sequence->optional = 0;
 	    }
		/* optional
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"interpretation"))) {
			strncpy(sequence->interpretation, (char *)get_attr_value(cur_node), 64);
 	    } */
	}

	sequence->presence_vector_type = PDT_OP;
	sequence->field = NULL;

	for (next_node = s_node->children; next_node; next_node = next_node->next) {
        if (next_node->type == XML_ELEMENT_NODE) {
			if ((!xmlStrcmp(next_node->name, (const xmlChar *)"presence_vector"))) /* optional */
				for (cur_node = next_node->properties; cur_node; cur_node = cur_node->next) {
				    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"field_type_unsigned"))) {
					    sequence->presence_vector_type = get_field_type((char *)get_attr_value(cur_node));
			 	    }
				}
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"record")))
				parse_record(next_node, (record_t *)add_field(&sequence->field, RECORD_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"list")))
				parse_list(next_node, (list_t *)add_field(&sequence->field, LIST_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"variant")))
				parse_variant(next_node, (variant_t *)add_field(&sequence->field, VARIANT_TYPE));
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"sequence")))
				parse_sequence(next_node, (sequence_t *)add_field(&sequence->field, SEQUENCE_TYPE));
		}
    }

}

/**
 * Parses a fixed_field element.
 *   Read all fields
 *
 *
 */
void parse_fixed_field(xmlNode * ff_node, fixed_field_t *fixed_field)
{
	xmlAttr *cur_node = NULL;
	xmlNode *next_node = NULL;

	if (DEBUG) printf("parse_fixed_field(): Enter\n");

	strncpy(fixed_field->interpretation, "none", 64);

	/* Get the attributes for fixed_field */
	for (cur_node = ff_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"name"))) {
		    strncpy(fixed_field->name, (char *)get_attr_value(cur_node), 64);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"field_type"))) {
			fixed_field->field_type = get_field_type((char *)get_attr_value(cur_node));
 	    }
		/* optional */
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"field_units"))) {
			strncpy(fixed_field->field_units, (char *)get_attr_value(cur_node), 32);
 	    }
		/* optional */
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"interpretation"))) {
			strncpy(fixed_field->interpretation, (char *)get_attr_value(cur_node), 64);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"optional"))) {
			if ((!xmlStrcmp(get_attr_value(cur_node), (const xmlChar *)"true")))
				fixed_field->optional = 1;
			else
				fixed_field->optional = 0;
 	    }
	}

	fixed_field->scale_range = NULL;
	fixed_field->value_set = NULL;

	/* fields */
	for (next_node = ff_node->children; next_node; next_node = next_node->next) {
        if (next_node->type == XML_ELEMENT_NODE) {
			if ((!xmlStrcmp(next_node->name, (const xmlChar *)"scale_range"))) {

				fixed_field->scale_range = (scale_range_t *)malloc(sizeof(scale_range_t));

				parse_scale_range(next_node, fixed_field->scale_range);

			}
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"value_set"))) {

				fixed_field->value_set = (value_set_t *)malloc(sizeof(value_set_t));

				parse_value_set(next_node, fixed_field->value_set);
			}
		}
    }
}

/**
 * Parses a scale_range element.
 *
 *
 */
void parse_scale_range(xmlNode *a_node, scale_range_t *field_ptr)
{
	xmlAttr *cur_node = NULL;
	char *value;

	if (DEBUG) printf("parse_scale_range(): Enter\n");

	/* NULL the optional attributes */

	/* Get the attributes for scale_range */
	for (cur_node = a_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"integer_function"))) {
		value = (char *)get_attr_value(cur_node);
			if ((!strcmp(value, "round")))
				field_ptr->integer_function = ROUND;
			else if ((!strcmp(value, "floor")))
				field_ptr->integer_function = FLOOR;
			else
				field_ptr->integer_function = CEILING;
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"real_lower_limit"))) {
		    field_ptr->real_lower_limit = strtod((char *)get_attr_value(cur_node), NULL);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"real_upper_limit"))) {
		    field_ptr->real_upper_limit = strtod((char *)get_attr_value(cur_node), NULL);
 	    }
	}
}

/**
 * Parses a bit_field element.
 *   Read all fields
 *   Add it to the record
 *
 */
void parse_bit_field(xmlNode * bf_node, bit_field_t *bit_field)
{
	xmlAttr *cur_node = NULL;
	xmlNode *next_node = NULL;

	sub_field_t *tmp_ptr;

	if (DEBUG) printf("parse_bit_field(): Enter\n");

	/* Get the attributes for bit_field */
	for (cur_node = bf_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"name"))) {
		    strncpy(bit_field->name, (char *)get_attr_value(cur_node), 64);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"field_type_unsigned"))) {
			bit_field->field_type_unsigned = get_field_type((char *)get_attr_value(cur_node));
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"optional"))) {
			if ((!xmlStrcmp(get_attr_value(cur_node), (const xmlChar *)"true")))
				bit_field->optional = 1;
			else
				bit_field->optional = 0;
 	    }
	}

	bit_field->sub_field = NULL;

	/* fields */
	for (next_node = bf_node->children; next_node; next_node = next_node->next) {
        if (next_node->type == XML_ELEMENT_NODE) {
			if ((!xmlStrcmp(next_node->name, (const xmlChar *)"sub_field"))) {
				if (bit_field->sub_field == NULL) { /* first sub_field found */

					bit_field->sub_field = (sub_field_t *)malloc(sizeof(sub_field_t));
					bit_field->sub_field->next = NULL;
					parse_sub_field(next_node, bit_field->sub_field);

				} else { /* another sub_field found */

					tmp_ptr = bit_field->sub_field;

					while (tmp_ptr->next != NULL)
						tmp_ptr = tmp_ptr->next;

					tmp_ptr->next = (sub_field_t *)malloc(sizeof(sub_field_t));

					tmp_ptr->next->next = NULL;
					parse_sub_field(next_node, tmp_ptr->next);
				}
			}
		}
    }
    
	if (DEBUG) printf("parse_bit_field(): Exit\n");
}

/**
 * Parses a sub_field element.
 *   Read all fields
 *
 *
 */
void parse_sub_field(xmlNode * sf_node, sub_field_t *sub_field)
{
	xmlAttr *cur_node = NULL;
	xmlNode *next_node = NULL;

	value_range_t *tmp_ptr;
	value_enum_t *etmp_ptr;

	if (DEBUG) printf("parse_sub_field(): Enter\n");

	/* Get the attributes for sub_field */
	for (cur_node = sf_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"name"))) {
		    strncpy(sub_field->name, (char *)get_attr_value(cur_node), 64);
 	    }
	}

	next_node = find_child_node(sf_node, "bit_range");

	/* Get the attributes for bit_field */
	for (cur_node = next_node->properties; cur_node; cur_node = cur_node->next) {
		if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"from_index"))) {
		    sub_field->from_index = (unsigned char)strtol((char *)get_attr_value(cur_node), NULL, 10);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"to_index"))) {
			sub_field->to_index = (unsigned char)strtol((char *)get_attr_value(cur_node), NULL, 10);
 	    }
	}

	next_node = find_child_node(sf_node, "value_set");

	/* Get the attributes for value_set */
	for (cur_node = next_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"offset_to_lower_limit"))) {
		    if ((!xmlStrcmp(get_attr_value(cur_node), (const xmlChar *)"true")))
				sub_field->offset_to_lower_limit = 1;
			else
				sub_field->offset_to_lower_limit = 0;
 	    }
	}

	sub_field->value_range = NULL;
	sub_field->value_enum = NULL;

	/* fields */
	for (next_node = next_node->children; next_node; next_node = next_node->next) {
        if (next_node->type == XML_ELEMENT_NODE) {
			if ((!xmlStrcmp(next_node->name, (const xmlChar *)"value_range"))) {
				if (sub_field->value_range == NULL) { /* first value_range found */

					sub_field->value_range = (value_range_t *)malloc(sizeof(value_range_t));
					sub_field->value_range->next = NULL;
					parse_value_range(next_node, sub_field->value_range);

				} else { /* another value_range found */
					tmp_ptr = sub_field->value_range;

					while (tmp_ptr->next != NULL)
						tmp_ptr = tmp_ptr->next;

					tmp_ptr->next = (value_range_t *)malloc(sizeof(value_range_t));
					tmp_ptr->next->next = NULL;
					parse_value_range(next_node, tmp_ptr->next);
				}
			} else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"value_enum"))) {
				if (sub_field->value_enum == NULL) { /* first value_enum found */

					sub_field->value_enum = (value_enum_t *)malloc(sizeof(value_enum_t));
					sub_field->value_enum->next = NULL;
					parse_value_enum(next_node, sub_field->value_enum);

				} else { /* another value_enum found */
					etmp_ptr = sub_field->value_enum;

					while (etmp_ptr->next != NULL)
						etmp_ptr = etmp_ptr->next;

					etmp_ptr->next = (value_enum_t *)malloc(sizeof(value_enum_t));
					etmp_ptr->next->next = NULL;
					parse_value_enum(next_node, etmp_ptr->next);
				}
			}
		}
    }
    
	if (DEBUG) printf("parse_sub_field(): Exit\n");
}

/**
 * Parses a value_range element.
 *
 *
 */
void parse_value_range(xmlNode *vr_node, value_range_t *value_range)
{
	xmlAttr *cur_node = NULL;

	if (DEBUG) printf("parse_value_range(): Enter\n");

	strncpy(value_range->interpretation, "none", 64);

	/* Get the attributes for value_range */
	for (cur_node = vr_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"lower_limit"))) {
		    value_range->lower_limit = (int)strtol((char *)get_attr_value(cur_node), NULL, 10);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"upper_limit"))) {
			value_range->upper_limit = (int)strtol((char *)get_attr_value(cur_node), NULL, 10);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"lower_limit_type"))) {
			if ((!xmlStrcmp(get_attr_value(cur_node), (const xmlChar *)"inclusive")))
				value_range->lower_limit_type = INCLUSIVE;
			else /* exclusive */
				value_range->lower_limit_type = EXCLUSIVE;
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"upper_limit_type"))) {
			if ((!xmlStrcmp(get_attr_value(cur_node), (const xmlChar *)"inclusive")))
				value_range->upper_limit_type = INCLUSIVE;
			else /* exclusive */
				value_range->upper_limit_type = EXCLUSIVE;
 	    }
		/* optional */
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"interpretation"))) {
			strncpy(value_range->interpretation, (char *)get_attr_value(cur_node), 64);
 	    }
	}

}

/**
 * Parses a value_enum element.
 *
 *
 */
void parse_value_enum(xmlNode *ve_node, value_enum_t *value_enum)
{
	xmlAttr *cur_node = NULL;

	if (DEBUG) printf("parse_value_enum(): Enter\n");

	/* Get the attributes for value_enum */
	for (cur_node = ve_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"enum_index"))) {
		    value_enum->enum_index = strtol((char *)get_attr_value(cur_node), NULL, 10);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"enum_const"))) {
			strncpy(value_enum->enum_const, (char *)get_attr_value(cur_node), 64);
 	    }
	}

	if (DEBUG) printf("parse_value_enum(): Exit\n");
}

/**
 * Parses a value_set element.
 *
 *
 */
void parse_value_set(xmlNode *vs_node, value_set_t *value_set)
{
	xmlAttr *cur_node = NULL;
	xmlNode *next_node = NULL;

	value_range_t *tmp_ptr;
	value_enum_t *etmp_ptr;

	if (DEBUG) printf("parse_value_set(): Enter\n");

	/* Get the attributes for value_set */
	for (cur_node = vs_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"offset_to_lower_limit"))) {
			if ((!xmlStrcmp(get_attr_value(cur_node), (const xmlChar *)"true")))
				value_set->offset_to_lower_limit = 1;
			else
				value_set->offset_to_lower_limit = 0;
 	    }
	}

	value_set->value_range = NULL;
	value_set->value_enum = NULL;

	/* fields */
	for (next_node = vs_node->children; next_node; next_node = next_node->next) {
        if (next_node->type == XML_ELEMENT_NODE) {
			if ((!xmlStrcmp(next_node->name, (const xmlChar *)"value_range"))) {
				if (value_set->value_range == NULL) { /* first value_range found */

					value_set->value_range = (value_range_t *)malloc(sizeof(value_range_t));
					value_set->value_range->next = NULL;
					parse_value_range(next_node, value_set->value_range);

				} else { /* another value_range found */
					tmp_ptr = value_set->value_range;

					while (tmp_ptr->next != NULL)
						tmp_ptr = tmp_ptr->next;

					tmp_ptr->next = (value_range_t *)malloc(sizeof(value_range_t));
					tmp_ptr->next->next = NULL;
					parse_value_range(next_node, tmp_ptr->next);
				}
			} else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"value_enum"))) {
				if (value_set->value_enum == NULL) { /* first value_enum found */

					value_set->value_enum = (value_enum_t *)malloc(sizeof(value_enum_t));
					value_set->value_enum->next = NULL;
					parse_value_enum(next_node, value_set->value_enum);

				} else { /* another value_enum found */
					etmp_ptr = value_set->value_enum;

					while (etmp_ptr->next != NULL)
						etmp_ptr = etmp_ptr->next;

					etmp_ptr->next = (value_enum_t *)malloc(sizeof(value_enum_t));
					etmp_ptr->next->next = NULL;
					parse_value_enum(next_node, etmp_ptr->next);
				}
			}
		}
    }

	if (DEBUG) printf("parse_value_set(): Exit\n");
}

/**
 * Parses a fixed_field element.
 *   Read all fields
 *   Add it to the record
 *
 */
void parse_variable_field(xmlNode * vf_node, variable_field_t *variable_field)
{
	xmlAttr *cur_node = NULL;
	xmlNode *next_node = NULL;

	type_and_units_enum_t *tmp_ptr;

	if (DEBUG) printf("parse_variable_field(): Enter\n");

	strncpy(variable_field->interpretation, "none", 64);

	/* Get the attributes for variable_field */
	for (cur_node = vf_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"name"))) {
		    strncpy(variable_field->name, (char *)get_attr_value(cur_node), 64);
 	    }
		/* optional */
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"interpretation"))) {
			strncpy(variable_field->interpretation, (char *)get_attr_value(cur_node), 64);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"optional"))) {
			if ((!xmlStrcmp(get_attr_value(cur_node), (const xmlChar *)"true")))
				variable_field->optional = 1;
			else
				variable_field->optional = 0;
 	    }
	}

	variable_field->taue = NULL;

	/* fields */
	for (next_node = vf_node->children; next_node; next_node = next_node->next) {
        if (next_node->type == XML_ELEMENT_NODE) {
			if ((!xmlStrcmp(next_node->name, (const xmlChar *)"type_and_units_enum"))) {
				if (variable_field->taue == NULL) { /* first type_and_units_enum found */

					variable_field->taue = (type_and_units_enum_t *)malloc(sizeof(type_and_units_enum_t));

					variable_field->taue->next = NULL;
					parse_type_and_units_enum(next_node, variable_field->taue);

				} else { /* another type_and_units_enum found */
					tmp_ptr = variable_field->taue;

					while (tmp_ptr->next != NULL)
						tmp_ptr = tmp_ptr->next;

					tmp_ptr->next = (type_and_units_enum_t *)malloc(sizeof(type_and_units_enum_t));
					tmp_ptr->next->next = NULL;
					parse_type_and_units_enum(next_node, tmp_ptr->next);
				}
			}
		}
    }
    
	if (DEBUG) printf("parse_variable_field(): Exit\n");
}

/**
 * Parses a type_and_units_enum element.
 *
 *
 */
void parse_type_and_units_enum(xmlNode *taue_node, type_and_units_enum_t *taue)
{
	xmlAttr *cur_node = NULL;
	xmlNode *next_node = NULL;

	if (DEBUG) printf("parse_type_and_units_enum(): Enter\n");

	/* Get the attributes for type_and_units_enum */
	for (cur_node = taue_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"index"))) {
		    taue->index = (unsigned char)strtol((char *)get_attr_value(cur_node), NULL, 10);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"field_type"))) {
			taue->field_type = get_field_type((char *)get_attr_value(cur_node));
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"field_units"))) {
			strncpy(taue->field_units, (char *)get_attr_value(cur_node), 32);
 	    }
	}

	taue->value_set = NULL;
	taue->scale_range = NULL;

	/* fields */
	for (next_node = taue_node->children; next_node; next_node = next_node->next) {
        if (next_node->type == XML_ELEMENT_NODE) {
			if ((!xmlStrcmp(next_node->name, (const xmlChar *)"value_set"))) {

				taue->value_set = (value_set_t *)malloc(sizeof(value_set_t));
				parse_value_set(next_node, taue->value_set);
			}
			else if ((!xmlStrcmp(next_node->name, (const xmlChar *)"scale_range"))) {

				taue->scale_range = (scale_range_t *)malloc(sizeof(scale_range_t));
				parse_scale_range(next_node, taue->scale_range);
			}
		}
    }

	if (DEBUG) printf("parse_type_and_units_enum(): Exit\n");
}

/**
 * Parses a fixed_length_string element.
 *   Add it to the record
 *
 */
void parse_fixed_length_string(xmlNode * fls_node, fixed_length_string_t *fixed_length_string)
{
	xmlAttr *cur_node = NULL;

	if (DEBUG) printf("parse_fixed_length_string(): Enter\n");

	strncpy(fixed_length_string->interpretation, "none", 64);

	/* Get the attributes for fixed_length_sting */
	for (cur_node = fls_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"name"))) {
		    strncpy(fixed_length_string->name, (char *)get_attr_value(cur_node), 64);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"string_length"))) {
			fixed_length_string->string_length = (unsigned int)strtol((char *)get_attr_value(cur_node), NULL, 10);
 	    }
		/* optional */
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"interpretation"))) {
			strncpy(fixed_length_string->interpretation, (char *)get_attr_value(cur_node), 64);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"optional"))) {
			if ((!xmlStrcmp(get_attr_value(cur_node), (const xmlChar *)"true")))
				fixed_length_string->optional = 1;
			else
				fixed_length_string->optional = 0;
 	    }
	}
    
	if (DEBUG) printf("parse_fixed_length_string(): Exit\n");
}

/**
 * Parses a variable_length_string element.
 *   Add it to the record
 *
 */
void parse_variable_length_string(xmlNode * vls_node, variable_length_string_t *variable_length_string)
{
	xmlAttr *cur_node = NULL;
	xmlNode *next_node = NULL;

	if (DEBUG) printf("parse_variable_length_string(): Enter\n");

	strncpy(variable_length_string->interpretation, "none", 64);

	/* Get the attributes for variable_length_string */
	for (cur_node = vls_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"name"))) {
		    strncpy(variable_length_string->name, (char *)get_attr_value(cur_node), 64);
 	    }
		/* optional */
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"interpretation"))) {
			strncpy(variable_length_string->interpretation, (char *)get_attr_value(cur_node), 64);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"optional"))) {
			if ((!xmlStrcmp(get_attr_value(cur_node), (const xmlChar *)"true")))
				variable_length_string->optional = 1;
			else
				variable_length_string->optional = 0;
 	    }
	}

	variable_length_string->count_field = NULL;

	next_node = find_child_node(vls_node, "count_field");

	if (next_node != NULL) {
		variable_length_string->count_field = (count_field_t *)malloc(sizeof(count_field_t));
		parse_count_field(next_node, variable_length_string->count_field);
	}
    
	if (DEBUG) printf("parse_variable_length_string(): Exit\n");
}

/**
 * Parses a count_field element.
 *
 *
 */
void parse_count_field(xmlNode *cf_node, count_field_t *count_field)
{
	xmlAttr *cur_node = NULL;

	if (DEBUG) printf("parse_count_field(): Enter\n");

	strncpy(count_field->interpretation, "none", 64);
	count_field->min_count = 0;
	count_field->max_count = 0;

	/* Get the attributes for count_field */
	for (cur_node = cf_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"field_type_unsigned"))) {
		    count_field->field_type_unsigned =  get_field_type((char *)get_attr_value(cur_node));
 	    }
		/* optional */
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"min_count"))) {
			count_field->min_count = (unsigned int)strtol((char *)get_attr_value(cur_node), NULL, 10);
 	    }
		/* optional */
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"max_count"))) {
			count_field->max_count = (unsigned int)strtol((char *)get_attr_value(cur_node), NULL, 10);
 	    }
		/* optional */
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"interpretation"))) {
			strncpy(count_field->interpretation, (char *)get_attr_value(cur_node), 64);
 	    }
	}
    
	if (DEBUG) printf("parse_count_field(): Exit\n");
}

/**
 * Parses a variable_length_field element.
 *
 *
 */
void parse_variable_length_field(xmlNode *vlf_node, variable_length_field_t *variable_length_field)
{
	xmlAttr *cur_node = NULL;
	xmlNode *next_node = NULL;

	if (DEBUG) printf("parse_variable_length_field(): Enter\n");

	strncpy(variable_length_field->interpretation, "none", 64);

	/* Get the attributes for variable_length_field */
	for (cur_node = vlf_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"name"))) {
		    strncpy(variable_length_field->name, (char *)get_attr_value(cur_node), 64);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"field_format"))) {
			strncpy(variable_length_field->field_format, (char *)get_attr_value(cur_node), 15);
 	    }
		/* optional */
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"interpretation"))) {
			strncpy(variable_length_field->interpretation, (char *)get_attr_value(cur_node), 64);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"optional"))) {
			if ((!xmlStrcmp(get_attr_value(cur_node), (const xmlChar *)"true")))
				variable_length_field->optional = 1;
			else
				variable_length_field->optional = 0;
 	    }
	}

	variable_length_field->count_field = NULL;

	next_node = find_child_node(vlf_node, "count_field");

	if (next_node != NULL) {
		variable_length_field->count_field = (count_field_t *)malloc(sizeof(count_field_t));
		parse_count_field(next_node, variable_length_field->count_field);
	}
    
    if (DEBUG) printf("parse_variable_length_field(): Exit\n");
}

/**
 * Parses a variable_format_field element.
 *
 *
 */
void parse_variable_format_field(xmlNode *vff_node, variable_format_field_t *variable_format_field)
{
	xmlAttr *cur_node = NULL;
	xmlNode *next_node = NULL;

	format_enum_t *tmp_ptr;

	if (DEBUG) printf("parse_variable_format_field(): Enter\n");

	strncpy(variable_format_field->interpretation, "none", 64);

	/* Get the attributes for variable_format_field */
	for (cur_node = vff_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"name"))) {
		    strncpy(variable_format_field->name, (char *)get_attr_value(cur_node), 64);
 	    }
		/* optional */
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"interpretation"))) {
			strncpy(variable_format_field->interpretation, (char *)get_attr_value(cur_node), 64);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"optional"))) {
			if ((!xmlStrcmp(get_attr_value(cur_node), (const xmlChar *)"true")))
				variable_format_field->optional = 1;
			else
				variable_format_field->optional = 0;
 	    }
	}

	variable_format_field->format_enum = NULL;

	next_node = find_child_node(vff_node, "format_field");

	/* fields */
	for (next_node = next_node->children; next_node; next_node = next_node->next) {
        if (next_node->type == XML_ELEMENT_NODE) {
			if ((!xmlStrcmp(next_node->name, (const xmlChar *)"format_enum"))) {
				if (variable_format_field->format_enum == NULL) { /* first format_enum found */

					variable_format_field->format_enum = (format_enum_t *)malloc(sizeof(format_enum_t));
					variable_format_field->format_enum->next = NULL;
					parse_format_enum(next_node, variable_format_field->format_enum);

				} else { /* another format_enum found */
					tmp_ptr = variable_format_field->format_enum;

					while (tmp_ptr->next != NULL)
						tmp_ptr = tmp_ptr->next;

					tmp_ptr->next = (format_enum_t *)malloc(sizeof(format_enum_t));
					tmp_ptr->next->next = NULL;
                    
					parse_format_enum(next_node, tmp_ptr->next);
				}
			}
		}
    }

	variable_format_field->count_field = NULL;

	next_node = find_child_node(vff_node, "count_field");

	if (next_node != NULL) {
		variable_format_field->count_field = (count_field_t *)malloc(sizeof(count_field_t));
		parse_count_field(next_node, variable_format_field->count_field);
	}
    
    if (DEBUG) printf("parse_variable_format_field(): Exit\n");
}

/**
 * Parses a format_enum element.
 *
 *
 */
void parse_format_enum(xmlNode *fe_node, format_enum_t *format_enum)
{
	xmlAttr *cur_node = NULL;

	if (DEBUG) printf("parse_format_enum(): Enter\n");

	/* Get the attributes for format_enum */
	for (cur_node = fe_node->properties; cur_node; cur_node = cur_node->next) {
	    if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"index"))) {
			format_enum->index = (unsigned char)strtol((char *)get_attr_value(cur_node), NULL, 10);
 	    }
		else if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"format_field"))) {
			strncpy(format_enum->field_format, (char *)get_attr_value(cur_node), 15);
 	    }
	}
    
	if (DEBUG) printf("parse_format_enum(): Exit\n");
}



char get_field_type(char* type)
{
	if ((!strcmp(type, "byte")))
		return(PDT_BYTE);
	else if ((!strcmp(type, "short integer")))
		return(PDT_SHORT_INT);
	else if ((!strcmp(type, "integer")))
		return(PDT_INT);
	else if ((!strcmp(type, "long integer")))
		return(PDT_LONG_INT);
	else if ((!strcmp(type, "unsigned byte")))
		return(PDT_UBYTE);
	else if ((!strcmp(type, "unsigned short integer")))
		return(PDT_USHORT_INT);
	else if ((!strcmp(type, "unsigned integer")))
		return(PDT_UINT);
	else if ((!strcmp(type, "unsigned long integer")))
		return(PDT_ULONG_INT);
	else if ((!strcmp(type, "float")))
		return(PDT_FLOAT);
	else if ((!strcmp(type, "long float")))
		return(PDT_LONG_FLOAT);
	return(PDT_BAD);
}

/**
 * Gets the name of an attribute node.
 *
 * Returns the name of the attribute.
 */
static const xmlChar* get_attr_name(xmlAttr * a_node)
{
	if (a_node->type == XML_ATTRIBUTE_NODE) {
		return(a_node->name);
	}

    return(NULL);
}

/**
 * Gets the content of an attribute node.
 *
 * Returns the content of the attribute.
 */
static const xmlChar* get_attr_value(xmlAttr * a_node)
{
	if (a_node->type == XML_ATTRIBUTE_NODE) {
		return(a_node->children->content);
	}

    return(NULL);
}

/**
 * Finds the next node with the given name.
 *
 * Returns the pointer to the node.
 */
xmlNode *find_child_node(xmlNode * a_node, char *name)
{
	xmlNode *cur_node = NULL;
	if (DEBUG) printf("find_child_node(): Enter\n");
	for (cur_node = a_node->children; cur_node; cur_node = cur_node->next) {
        if (cur_node->type == XML_ELEMENT_NODE) {
			if ((!xmlStrcmp(cur_node->name, (const xmlChar *)name)))
			{
				if (DEBUG) printf("find_child_node(): Found Node name: %s\n", cur_node->name);
				return(cur_node);
			}
		} else if (DEBUG) printf("find_child_node(): Not ELEMENT (%d)\n",cur_node->type);

    }
	return(NULL);
}

/**
 * Finds the first node for message_def and starts the parser then loops till
 *  no more message_def elements are found.
 *
 *
 */
void read_message_defs(xmlNode * a_node)
{
	xmlNode *cur_node = NULL;

	for (cur_node = a_node; cur_node; cur_node = cur_node->next) {
        if (cur_node->type == XML_ELEMENT_NODE) {
			if ((!xmlStrcmp(cur_node->name, (const xmlChar *)"message_def")))
			{
				if (DEBUG) printf("read_message_defs(): Found name: %s\n", cur_node->name);
				parse_message_def(cur_node);
			}
		}else if (DEBUG) printf("read_message_defs(): Not ELEMENT (%d)\n",cur_node->type);

		if ((xmlStrcmp(cur_node->name, (const xmlChar *)"message_def")))
			read_message_defs(cur_node->children);
    }

}

void test_print_field(field_t *f_ptr, int f_count)
{
	array_t *a_ptr;
	dimension_t *d_ptr;

	fixed_field_t *ff_ptr;
	scale_range_t *sr_ptr;
	value_set_t *vs_ptr;
		value_range_t *vr_ptr;
		value_enum_t *ve_ptr;

	variable_field_t *vf_ptr;
	type_and_units_enum_t *taue_ptr;

	bit_field_t *bf_ptr;
	sub_field_t *sf_ptr;

	fixed_length_string_t *fls_ptr;

	variable_length_string_t *vls_ptr;
	count_field_t *cf_ptr;

	variable_length_field_t *vlf_ptr;

	variable_format_field_t *vff_ptr;
	format_enum_t *fe_ptr;


	/* fprintf(out," Field Type: %d\n", f_ptr->type); */
	switch (f_ptr->type) {
		case ARRAY_TYPE:
			a_ptr = (array_t *)f_ptr->field;
			fprintf(out,"\n      [%d A] %s (%s) \n", f_count, a_ptr->name, (a_ptr->optional) ? "optional" : "req");
			d_ptr = a_ptr->dimension;
			while (d_ptr != NULL) {
				fprintf(out,"      [d] %s  %d \n", d_ptr->name, d_ptr->size);
				d_ptr = d_ptr->next;
			}
			test_print_field(a_ptr->field, f_count);
			break;
		case FIXED_FIELD_TYPE:
			ff_ptr = (fixed_field_t *)f_ptr->field;
			fprintf(out,"\n      [%d FF] %s (ft:%d) %s (%s)\n        %s\n", f_count,
				ff_ptr->name, ff_ptr->field_type, ff_ptr->field_units,
				(ff_ptr->optional) ? "optional" : "req", ff_ptr->interpretation);
			if (ff_ptr->scale_range != NULL) {
				sr_ptr = ff_ptr->scale_range;
				fprintf(out,"        [SR] upper %f lower %f func %d\n", sr_ptr->real_upper_limit, sr_ptr->real_lower_limit,
					sr_ptr->integer_function);
			}
			if (ff_ptr->value_set != NULL) {
				vs_ptr = ff_ptr->value_set;
				fprintf(out,"        [VS] offset_to_lower: %s\n",(vs_ptr->offset_to_lower_limit)? "True":"False");
				vr_ptr = vs_ptr->value_range;
				ve_ptr = vs_ptr->value_enum;
				while (vr_ptr != NULL) {
					fprintf(out,"          [VR] lower: %d (%s) upper: %d (%s) inter: %s\n", vr_ptr->lower_limit, (vr_ptr->lower_limit_type)?"exclusive":"inclusive",
						vr_ptr->upper_limit, (vr_ptr->upper_limit_type)?"exclusive":"inclusive", vr_ptr->interpretation);
					vr_ptr = vr_ptr->next;
				}
				while (ve_ptr != NULL) {
					fprintf(out,"          [VE] index: %d const: %s\n", ve_ptr->enum_index, ve_ptr->enum_const);
					ve_ptr = ve_ptr->next;
				}
			}
			break;
		case VARIABLE_FIELD_TYPE:
			vf_ptr = (variable_field_t *)f_ptr->field;
			fprintf(out,"\n      [%d VF] %s (%s)\n        %s\n", f_count,
				vf_ptr->name, (vf_ptr->optional) ? "optional" : "req", vf_ptr->interpretation);
			if (vf_ptr->taue != NULL) {
				taue_ptr = vf_ptr->taue;
				while (taue_ptr != NULL) {
					fprintf(out,"        [TAUE]: index %d (ft:%d) %s\n", taue_ptr->index, taue_ptr->field_type,
						taue_ptr->field_units);
					if (taue_ptr->scale_range != NULL) {
						sr_ptr = taue_ptr->scale_range;
						fprintf(out,"        [SR] upper %f lower %f func %d\n", sr_ptr->real_upper_limit, sr_ptr->real_lower_limit,
							sr_ptr->integer_function);
					}
					if (taue_ptr->value_set != NULL) {
						vs_ptr = taue_ptr->value_set;
						fprintf(out,"        [VS] offset_to_lower: %s\n",(vs_ptr->offset_to_lower_limit)? "True":"False");
						vr_ptr = vs_ptr->value_range;
						ve_ptr = vs_ptr->value_enum;
						while (vr_ptr != NULL) {
							fprintf(out,"          [VR] lower: %d (%s) upper: %d (%s) inter: %s\n", vr_ptr->lower_limit, (vr_ptr->lower_limit_type)?"exclusive":"inclusive",
								vr_ptr->upper_limit, (vr_ptr->upper_limit_type)?"exclusive":"inclusive", vr_ptr->interpretation);
							vr_ptr = vr_ptr->next;
						}
						while (ve_ptr != NULL) {
							fprintf(out,"          [VE] index: %d const: %s\n", ve_ptr->enum_index, ve_ptr->enum_const);
							ve_ptr = ve_ptr->next;
						}
					}
					taue_ptr = taue_ptr->next;
				}
			}
			break;
		case BIT_FIELD_TYPE:
			bf_ptr = (bit_field_t *)f_ptr->field;
			fprintf(out,"\n      [%d BF] %s (ft:%d) (%s)\n", f_count, bf_ptr->name, bf_ptr->field_type_unsigned,
				(bf_ptr->optional) ? "optional" : "req");
			sf_ptr = bf_ptr->sub_field;
			while (sf_ptr != NULL) {
				fprintf(out,"       [SF]: %s from: %d to: %d offset_to_low: %s\n", sf_ptr->name, sf_ptr->from_index,
					sf_ptr->to_index, (sf_ptr->offset_to_lower_limit) ? "yes" : "no");
				vr_ptr = sf_ptr->value_range;
				ve_ptr = sf_ptr->value_enum;
				while (vr_ptr != NULL) {
					fprintf(out,"         [VR] lower: %d (%s) upper: %d (%s) inter: %s\n", vr_ptr->lower_limit, (vr_ptr->lower_limit_type)?"exclusive":"inclusive",
						vr_ptr->upper_limit, (vr_ptr->upper_limit_type)?"exclusive":"inclusive", vr_ptr->interpretation);
					vr_ptr = vr_ptr->next;
				}
				while (ve_ptr != NULL) {
					fprintf(out,"         [VE] index: %d const: %s\n", ve_ptr->enum_index, ve_ptr->enum_const);
					ve_ptr = ve_ptr->next;
				}
				sf_ptr = sf_ptr->next;
			}
			break;
		case FIXED_LENGTH_STRING_TYPE:
			fls_ptr = (fixed_length_string_t *)f_ptr->field;
			fprintf(out,"\n      [%d FLS] %s (sl:%d) (%s)\n", f_count, fls_ptr->name, fls_ptr->string_length,
				(fls_ptr->optional) ? "optional" : "req");
			break;
		case VARIABLE_LENGTH_STRING_TYPE:
			vls_ptr = (variable_length_string_t *)f_ptr->field;
			fprintf(out,"\n      [%d VLS] %s (%s)\n", f_count, vls_ptr->name, (vls_ptr->optional) ? "optional" : "req");
			cf_ptr = vls_ptr->count_field;
			if (cf_ptr != NULL) {
				fprintf(out,"        [CF] type(%d) min: %d max: %d\n", cf_ptr->field_type_unsigned, cf_ptr->min_count, cf_ptr->max_count);
			}
			break;
		case VARIABLE_LENGTH_FIELD_TYPE:
			vlf_ptr = (variable_length_field_t *)f_ptr->field;
			fprintf(out,"\n      [%d VLF] %s format: %s (%s)\n", f_count, vlf_ptr->name, vlf_ptr->field_format, (vlf_ptr->optional) ? "optional" : "req");
			cf_ptr = vlf_ptr->count_field;
			if (cf_ptr != NULL) {
				fprintf(out,"        [CF] type(%d) min: %d max: %d\n", cf_ptr->field_type_unsigned, cf_ptr->min_count, cf_ptr->max_count);
			}
			break;
		case VARIABLE_FORMAT_FIELD_TYPE:
			vff_ptr = (variable_format_field_t *)f_ptr->field;
			fprintf(out,"\n      [%d VFF] %s (%s)\n", f_count, vff_ptr->name, (vff_ptr->optional) ? "optional" : "req");
			fe_ptr = vff_ptr->format_enum;
			while (fe_ptr != NULL) {
				fprintf(out,"        [FE] index: %d format: %s\n", fe_ptr->index, fe_ptr->field_format);
				fe_ptr = fe_ptr->next;
			}
			cf_ptr = vff_ptr->count_field;
			if (cf_ptr != NULL) {
				fprintf(out,"        [CF] type(%d) min: %d max: %d\n", cf_ptr->field_type_unsigned, cf_ptr->min_count, cf_ptr->max_count);
			}
			break;
		default:
			fprintf(out,"      [%d] Unknown field type %d\n\n",f_count, f_ptr->type);
	}
}

void test_print_comp(field_t *f_ptr)
{
	field_t *lf_ptr;

	record_t *r_ptr;
	list_t *l_ptr;
		count_field_t *cf_ptr;
	variant_t *v_ptr;
	sequence_t *s_ptr;

	int f_count = 0;

	switch (f_ptr->type) {
		case RECORD_TYPE:
			r_ptr = (record_t *)f_ptr->field;
			fprintf(out,"\n   [RECORD]: %s (%s) pv = (%d)\n", r_ptr->name,
				(r_ptr->optional) ? "optional" : "req", r_ptr->presence_vector_type);
			f_ptr = r_ptr->field;
			while (f_ptr != NULL) {
				f_count++;
				test_print_field(f_ptr, f_count);
				f_ptr = f_ptr->next;
			}
			break;
		case LIST_TYPE:
			l_ptr = (list_t *)f_ptr->field;
			fprintf(out,"\n   [LIST]: %s (%s) \n", l_ptr->name,
				(l_ptr->optional) ? "optional" : "req");
			cf_ptr = l_ptr->count_field;
			fprintf(out,"     [CF]: ft: %d min: %d max: %d\n", cf_ptr->field_type_unsigned, cf_ptr->min_count,
				cf_ptr->max_count);
			test_print_comp(l_ptr->field);
			break;
		case VARIANT_TYPE:
			v_ptr = (variant_t *)f_ptr->field;
			fprintf(out,"\n   [VARIANT]: %s (%s) \n", v_ptr->name,
				(v_ptr->optional) ? "optional" : "req");
			fprintf(out,"     [vtag]: ft: %d min: %d max: %d\n", v_ptr->field_type_unsigned, v_ptr->min_count,
				v_ptr->max_count);
			lf_ptr = v_ptr->field;
			while (lf_ptr != NULL) {
				test_print_comp(lf_ptr);
				lf_ptr = lf_ptr->next;
			}
			break;
		case SEQUENCE_TYPE:
			s_ptr = (sequence_t *)f_ptr->field;
			fprintf(out,"\n   [SEQUENCE]: %s (%s) pv = (%d)\n", s_ptr->name,
				(s_ptr->optional) ? "optional" : "req", s_ptr->presence_vector_type);
			lf_ptr = s_ptr->field;
			while (lf_ptr != NULL) {
				test_print_comp(lf_ptr);
				lf_ptr = lf_ptr->next;
			}
			break;
		default:
			fprintf(out,"   Unknown comp field type %d\n\n", f_ptr->type);
	}
}

/**
 * Called if DEBUG is one for testing reading of tree information
 *
 */
void test_print()
{
	message_def_t *m_ptr;
	field_t *f_ptr;

	int m_count;
	m_count = 0;

	if (message_set != NULL) {
		m_ptr = message_set;

		while (m_ptr != NULL) {
			m_count++;
			fprintf(out,"\n[%d] %s (0x%04X) is_command: %s\n", m_count, m_ptr->name, m_ptr->message_id,
				(m_ptr->is_command) ? "yes" : "no");

			f_ptr = m_ptr->body;
			if (f_ptr != NULL){

				if (DEBUG2) test_print_comp(f_ptr);

			} else if (DEBUG2) fprintf(out,"\tf_ptr is NULL\n");
			m_ptr = m_ptr->next;
		}

	} else fprintf(out,"No message_set made!!");

}

/**
 * Starts the libxml2 xml parser on the files.
 *    Finds the root element and calls read_message_defs
 *    Cleans up the xml parser.
 *
 */
void start_xml_parse()
{
	xmlDoc *doc = NULL;
	xmlNode *root_element = NULL;
	/*
	Need to be able to read multiple files in a folder of type .xml
	*/
#if _WIN32
	/*
	This way is for Windows using <windows.h>.
	 */
	WIN32_FIND_DATA FindFileData;
	HANDLE hFind;
	char filename[MAX_PATH];
	int path_len;
	char path_name[200];
	/*
	SearchPath returns the full path to wireshark.exe
	Used to get the path of the wireshark install then used to find location of XMLs
	*/
	path_len = SearchPath(NULL,"wireshark.exe",NULL, 200, path_name,NULL);
	if (!path_len) { return;}

	/* Remove the wireshark.exe from the path */
	while (path_name[path_len] != '\\') {
		path_name[path_len] = '\0';
		path_len--;
	}

	strncpy(filename, path_name, 150);

	if (DEBUG1) {
		strncat(filename, "packet-jaus-support/Messages_Found.txt", 200);
		out = fopen(filename,"w");
		if(out == NULL) { return;}
	}

	strncpy(filename, path_name, 150);
	strncat(filename, "packet-jaus-support/jausxml/*.xml", 200);

	/* Finds first file */
	hFind = FindFirstFile(filename, &FindFileData);
	if (hFind != INVALID_HANDLE_VALUE) {
		do {
			/* setup filename */
			strncpy(filename, path_name, 150);
			strncat(filename,"packet-jaus-support/jausxml/", 50);
			strncat(filename, FindFileData.cFileName, 200);

			if (DEBUG1) fprintf(out,"-- FILE FOUND \"%s\"\n", filename);

			/* send filename to xml reader */
			doc = xmlReadFile(filename, NULL, 0);

			if (doc == NULL) {
				fprintf(out,"error: could not parse file\n");
			}

			/*Get the root element node */
			root_element = xmlDocGetRootElement(doc);


			if (root_element != NULL) {
				/* start parse for message_defs on root */
				read_message_defs(root_element);
			} else {
				fprintf(out,"error: empty document\n");
			}

			/*free the document */
			xmlFreeDoc(doc);

			/*
			 *Free the global variables that may
			 *have been allocated by the parser.
			 */
			xmlCleanupParser();

		/* Find Next file using the info for finding the first, 0 = NO_MORE_FILES */
		} while (FindNextFile(hFind, &FindFileData));

		if (DEBUG1) fprintf(out,"FindFile failed (%s)\n", (GetLastError() == ERROR_NO_MORE_FILES)? "ERROR_NO_MORE_FILES" : "ERROR");
		FindClose(hFind);
	}

#else

	/* Use dirent.h */
	struct dirent *entry;
	DIR *dp;
	char filename[256];
	char path_name[200];

	strncpy(path_name, "/usr/local/lib/wireshark/packet-jaus-support/jausxml/", 60);

	dp = opendir(path_name);
	if (dp == NULL) {
		perror("opendir");
		return -1;
	}

	if (DEBUG1) {
		strncpy(filename, "/usr/local/lib/wireshark/packet-jaus-support/Messages_Found.txt", 70);
		out = fopen(filename,"w");
		if(out == NULL) { return;}
	}

	while((entry = readdir(dp))) {
		puts(entry->d_name);
		// skip "." and ".."
		if (strcmp(".", entry->d_name) == 0 || strcmp("..", entry->d_name) == 0)
        		continue;

		// if file is .xml, open and try to parse
		if (strstr(entry->d_name, ".xml") == NULL) {
			printf(" not .xml: %s, continue.\n", entry->d_name);
			continue;
		}
		
		strncpy(filename, path_name, 150);
		strncat(filename, entry->d_name, 200);

		if (DEBUG1) fprintf(out,"-- FILE FOUND \"%s\"\n", filename);

		/* send filename to xml reader */
		doc = xmlReadFile(filename, NULL, 0);

		if (doc == NULL) {
			fprintf(out,"error: could not parse file\n\n");
		}

		/*Get the root element node */
		root_element = xmlDocGetRootElement(doc);


		if (root_element != NULL) {
			/* start parse for message_defs on root */
			read_message_defs(root_element);
		} else {
			fprintf(out,"error: empty document\n");
		}

		/*free the document */
		xmlFreeDoc(doc);

		/*
		 *Free the global variables that may
		 *have been allocated by the parser.
		 */
		xmlCleanupParser();

	/* Find Next file using the info for finding the first, 0 = NO_MORE_FILES */
	} 

	closedir(dp);
#endif

	if (DEBUG1) {
		test_print();
		fclose(out);
	}


}

/**
 * Test jausxml.c
 *
 * XML files should be here "packet-jaus-support\jausxml\*.xml"
 *  
 * Note2self: this is how to compile this in windows cmd :)
 *     C:\wireshark-1.4\plugins\jaus>CL -I \libxml2\include jausxml.c \libxml2\lib\libxml2.lib
 *
 *  ****This test will no longer work with the addition of the look for wireshark location code.****
 *                  required to run from the dissector
 *     Acttualy, if you run this from the the wireshark complied directory, then it will work
 */
int main(int argc, char **argv)
{
	if (DEBUG) printf("Testing JAUSxml parsing\n\n");
    
	start_xml_parse();

	if (DEBUG) printf("\n\nDone Testing JAUSxml parsing\n");
    return 0;
}
