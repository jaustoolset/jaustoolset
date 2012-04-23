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

#ifndef JAUSXML_H_
#define JAUSXML_H_

#include <libxml/parser.h>
#include <libxml/tree.h>

/* Relax NG notation (jsidl)
	+ one or more occurrences
	* zero or more occurrences
	? optional (zero or one?)
	  only one occurrence
 */

#define FLOOR		0
#define ROUND		1
#define CEILING		2

#define INCLUSIVE	0
#define EXCLUSIVE	1

/* Primitive Data Types			bytes/bits */
#define PDT_BYTE			0	/*  1/8   */
#define PDT_SHORT_INT		1	/*  2/16  */
#define PDT_INT				2	/*  4/32  */
#define PDT_LONG_INT		3	/*  8/64  */
#define PDT_UBYTE			4	/*  1/8   */
#define PDT_USHORT_INT		5	/*  2/16  */
#define PDT_UINT			6	/*  4/32  */
#define PDT_ULONG_INT		7	/*  8/64  */
#define PDT_FLOAT			8	/*  4/32  */
#define PDT_LONG_FLOAT		9	/*  8/64  */
#define PDT_BAD				10 /* Not Optional but unknown ERROR */
#define PDT_OP				11 /* Optional */

/* Composite Fields */
#define ARRAY_TYPE					0
#define RECORD_TYPE					1
#define LIST_TYPE					2
#define VARIANT_TYPE				3
#define SEQUENCE_TYPE				4

/* Simple Fields */
#define FIXED_FIELD_TYPE			5
#define VARIABLE_FIELD_TYPE			6
#define BIT_FIELD_TYPE				7
#define FIXED_LENGTH_STRING_TYPE	8
#define VARIABLE_LENGTH_STRING_TYPE	9
#define VARIABLE_LENGTH_FIELD_TYPE	10
#define VARIABLE_FORMAT_FIELD_TYPE	11

typedef struct _field {
	unsigned char type;
	void *field;
	struct _field *next;
} field_t;

typedef struct record {
	char name[64];
	unsigned char optional;
	/* presence_vector (?) */
		char presence_vector_type;
	field_t *field;
} record_t;

typedef struct count_field {
	char field_type_unsigned;
	unsigned int min_count; /* ? */
	unsigned int max_count; /* ? */
	char interpretation[64]; /* ? */
} count_field_t;

typedef struct dimension {
	char name[64];
	int size;
	struct dimension *next;
} dimension_t;

typedef struct array {
	char name[64];
	unsigned char optional;
	field_t *field;
	/* dimensions ... */
		dimension_t *dimension; /* + */
} array_t;

typedef struct list {
	char name[64];
	unsigned char optional;
	count_field_t *count_field;
	field_t *field;
} list_t;

typedef struct variant {
	char name[64];
	unsigned char optional;
	/* vtag_field */
		char field_type_unsigned;
		unsigned int min_count; /* ? */
		unsigned int max_count; /* ? */
	field_t *field; /* * */
} variant_t;

typedef struct sequence {
	char name[64];
	unsigned char optional;
	/* presence_vector (?) */
		char presence_vector_type;
	field_t *field; /* + */
} sequence_t;

typedef struct _message_def {
	unsigned short message_id;
	char name[64];
	char is_command;
	field_t *header;
	field_t *body;
	field_t *footer;
	struct _message_def *next;
} message_def_t;

typedef struct value_range {
	int lower_limit;
	char lower_limit_type;
	int upper_limit;
	char upper_limit_type;
	char interpretation[64];
	struct value_range *next;
} value_range_t;

typedef struct value_enum {
	long enum_index;
	char enum_const[64];
	struct value_enum *next;
} value_enum_t;

typedef struct scale_range {
	double real_lower_limit;
	double real_upper_limit;
	char integer_function;
} scale_range_t;

typedef struct value_set {
	unsigned char offset_to_lower_limit;
	value_range_t *value_range; /* + */
	value_enum_t *value_enum; /* + */
} value_set_t;

typedef struct fixed_field {
	char name[64];
	char field_type;
	char field_units[32];
	unsigned char optional;
	char interpretation[64]; /* ? */
	scale_range_t *scale_range; /* ? */
	value_set_t *value_set; /* ? */
} fixed_field_t;

typedef struct sub_field {
	char name[64];
	/* bit_range (req) */
		unsigned char from_index;
		unsigned char to_index;
	/* value_set (req) */
		unsigned char offset_to_lower_limit;
		value_range_t *value_range; /* + */
		value_enum_t *value_enum; /* + */
	struct sub_field *next;
} sub_field_t;

typedef struct bit_field {
	char name[64];
	char field_type_unsigned;
	unsigned char optional;
	sub_field_t *sub_field; /* + */
} bit_field_t;

typedef struct type_and_units_enum {
	unsigned char index;
	char field_type;
	char field_units[32];
	value_set_t *value_set; /* ? */
	scale_range_t *scale_range; /* ? */
	struct type_and_units_enum *next;
} type_and_units_enum_t;

typedef struct variable_field {
	char name[64];
	unsigned char optional;
	char interpretation[64]; /* ? */
	/* type_and_units_field (req) */
		type_and_units_enum_t *taue; /* + */
} variable_field_t;

typedef struct fixed_length_string {
	char name[64];
	unsigned int string_length;
	unsigned char optional;
	char interpretation[64]; /* ? */
} fixed_length_string_t;

typedef struct variable_length_string {
	char name[64];
	unsigned char optional;
	char interpretation[64]; /* ? */
	count_field_t *count_field;
} variable_length_string_t;

typedef struct variable_length_field {
	char name[64];
	char field_format[15];
	unsigned char optional;
	char interpretation[64]; /* ? */
	count_field_t *count_field;
} variable_length_field_t;

typedef struct format_enum {
	unsigned char index;
	char field_format[15];
	struct format_enum *next;
} format_enum_t;

typedef struct variable_format_field {
	char name[64];
	unsigned char optional;
	char interpretation[64]; /* ? */
	/* format_field (req) */
		format_enum_t *format_enum; /* + */
	count_field_t *count_field;
} variable_format_field_t;

typedef struct vtag_field {
	char field_type_unsigned[24];
	unsigned int min_count; /* ? */
	unsigned int max_count; /* ? */
} vtag_field_t;



/* forward references */
message_def_t *add_message_def(char *name, unsigned short message_id, unsigned char is_command);
void *add_field(field_t **field, unsigned char field_type);

void parse_message_def(xmlNode *message_node);
/* Composite Fields */
void parse_record(xmlNode *record_node, record_t *record);
void parse_array(xmlNode *a_node, array_t *array);
void parse_list(xmlNode *l_node, list_t *list);
void parse_variant(xmlNode *v_node, variant_t *variant);
void parse_sequence(xmlNode *s_node, sequence_t *sequence);
/* Simple Fields */
void parse_fixed_field(xmlNode *ff_node, fixed_field_t *fixed_field);
void parse_bit_field(xmlNode *bf_node, bit_field_t *bit_field);
void parse_variable_field(xmlNode *vf_node, variable_field_t *variable_field);
void parse_fixed_length_string(xmlNode *fls_node, fixed_length_string_t *fixed_length_string);
void parse_variable_length_string(xmlNode *vls_node, variable_length_string_t *variable_length_string);
void parse_variable_length_field(xmlNode *vlf_node, variable_length_field_t *variable_length_field);
void parse_variable_format_field(xmlNode *vff_node, variable_format_field_t *variable_format_field);
/* Meta Fields */
void parse_presence_vector(xmlNode *pv_node, record_t *record);
void parse_count_field(xmlNode *cf_node, count_field_t *count_field);
/* Field Information */
void parse_dimension(xmlNode *d_node, dimension_t *dimension);
void parse_scale_range(xmlNode *sr_node, scale_range_t *field_ptr);
void parse_sub_field(xmlNode * sf_node, sub_field_t *sub_field);
void parse_value_range(xmlNode *vr_node, value_range_t *value_range);
void parse_value_enum(xmlNode *next_node, value_enum_t *value_enum);
void parse_value_set(xmlNode *next_node, value_set_t *value_set);
void parse_type_and_units_enum(xmlNode *taue_node, type_and_units_enum_t *taue);
void parse_format_enum(xmlNode *fe_node, format_enum_t *format_enum);

char get_field_type(char* type);
static const xmlChar* get_attr_name(xmlAttr *);
static const xmlChar* get_attr_value(xmlAttr *);
xmlNode *find_child_node(xmlNode * a_node, char *name);
void read_message_defs(xmlNode * a_node);
void start_xml_parse();


#endif /*JAUSXML_H_*/
