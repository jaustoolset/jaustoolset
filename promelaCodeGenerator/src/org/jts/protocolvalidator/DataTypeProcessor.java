/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2011, United States Government
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
package org.jts.protocolvalidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jts.jsidl.binding.*;

/**
 * This class is used to process JSIDL data types and turn them into Promela data types
 * @author cmessmer
 */
public class DataTypeProcessor {

    protected List<String> definitions;
    protected String ID;
    protected Map<String, String> refMap;
    protected DataTypeProcessor dataTypes;
    protected Map<String, String> enumList;

    public DataTypeProcessor(String id, Map<String, String> refmap, Map<String, String> enumlist) {
        ID = id;
        refMap = refmap;
        enumList = enumlist;

        definitions = new ArrayList<String>();

    }

    /**
     * Processes a Record and writes the result to the output file
     * @param rec the input binding
     * @param parentID - the ID for the parent of this object,
     * which allows embedded types to have a globally unique name
     * @return - generated code
     */
    protected List<String> processRecord(Record rec, String parentID) {
        List<String> outputDef = new ArrayList<String>();
        String type = Util.getTypeNameFromDeclTypeRef(refMap, rec.getName(), parentID);
        outputDef.addAll(Util.formatCommentString(rec.getInterpretation(), false));
        outputDef.add(type + " " + rec.getName() + ";");

        return outputDef;
    }

    /**
     * Processes a List and writes the result to the output file
     * @param rec the input binding
     * @param parentID - the ID for the parent of this object,
     * which allows embedded types to have a globally unique name
     * @return - generated code
     */
    protected List<String> processList(org.jts.jsidl.binding.List list, String parentID) {
        List<String> outputDef = new ArrayList<String>();
        String type = Util.getTypeNameFromDeclTypeRef(refMap, list.getName(), parentID);

        outputDef.addAll(Util.formatCommentString(list.getInterpretation(), false));
        // TODO: need to fix this count at some point, but not sure its necessary.
        long count = 255;
        if (list.getCountField().getMaxCount() != null && !list.getCountField().getMaxCount().isEmpty()) {
            count = Long.valueOf(list.getCountField().getMaxCount());
        }
        outputDef.add(type + " " + list.getName() + "[" + count + "];");

        return outputDef;
    }

    /**
     * Processes a FixedFiel and writes the result to the output file
     * @param field the input binding
     * @param parentID - the ID for the parent of this object,
     * which allows embedded types to have a globally unique name
     * @return - generated code
     */
    protected List<String> processFixedField(FixedField field, String parentID) {
        List<String> output = new ArrayList<String>();

        // add some comments
        output.addAll(Util.formatCommentString(field.getInterpretation(), false));
        String name = field.getName();
        String type = TypeConverter.convertType(field.getFieldType());

        // in a some cases, the field has a value set that is defined elsewhere using the field's name
        if (field.getValueSet() != null && !field.getValueSet().getValueRangeOrValueEnum().isEmpty()) {
            type = Util.getTypeNameFromDeclTypeRef(refMap, name, parentID);
        }
        output.add(type + " " + name + ";");

        return output;
    }

    /**
     * Processes a FixedField definition and writes the result to the output file.
     *
     * @param field the input binding
     * @param parentID - the ID for the parent of this object,
     * which allows embedded types to have a globally unique name
     * @return - generated code
     */
    protected List<String> processFixedFieldDef(FixedField field, String parentID) throws Exception {
        List<String> output = new ArrayList<String>();
        String type = TypeConverter.convertType(field.getFieldType());
        String name = field.getName();
        ValueSet set = field.getValueSet();

        output.addAll(Util.formatCommentString(field.getInterpretation(), false));
        if (set == null) {
            name = Util.getTypeNameFromDeclTypeRef(refMap, name, parentID);
            output.add("#define " + name + " " + type);
        } else {
            output = processValueSetOrEnumDef(name, set.getValueRangeOrValueEnum(), parentID);
        }
        return output;
    }

    /**
     * Processes a Sequence and writes the result to the output file
     * @param seq the input binding
     * @param parentID - the ID for the parent of this object,
     * which allows embedded types to have a globally unique name
     * @return - generated code
     */
    protected List<String> processSequence(Sequence seq, String parentID) {
        List<String> output = new ArrayList<String>();
        String type = Util.getTypeNameFromDeclTypeRef(refMap, seq.getName(), parentID);

        output.addAll(Util.formatCommentString(seq.getInterpretation(), false));
        output.add(type + " " + seq.getName() + ";");
        return output;
    }

    /**
     * Generates code for a bitfield defintion
     * @param bitField
     * @param parentID - the ID for the parent of this object,
     * which allows embedded types to have a globally unique name
     * @return
     */
    protected List<String> processBitFieldDef(BitField bitField, String parentID) {
        List<String> output = new ArrayList<String>();
        String name = Util.getTypeNameFromDeclTypeRef(refMap, bitField.getName(), parentID);

        String type = TypeConverter.convertType(bitField.getFieldTypeUnsigned());
        // since we can't define a bitfield in Promela, we'll make separate
        // instances of each subfield based on the contained data
        output.add("// separate types defined for subfields of BitField : " + name);
        List<SubField> bl = bitField.getSubField();
        if (!bl.isEmpty()) {
            if (bitField.getInterpretation() != null) {
                output.addAll(Util.formatCommentString(bitField.getInterpretation(), false));
            }
            output.add("typedef " + name + "{");
            output.add("");
        }
        // figure out what this bitfield contains and generate appropriate code
        for (SubField subField : bl) {
            ValueSet val = subField.getValueSet();
            List<Object> tmpren = val.getValueRangeOrValueEnum();
            for (Object renObj : tmpren) {
                // range of values: we don't bother controlling range in promela so just set the type info
                if (renObj instanceof ValueRange) {
                    String lower = ((ValueRange) renObj).getLowerLimit();
                    String upper = ((ValueRange) renObj).getUpperLimit();
                    type = TypeConverter.convertRangeBitFieldType(lower, upper);
                    output.addAll(Util.formatCommentString(subField.getInterpretation(), false));
                    output.add("\t" + type + " " + subField.getName() + ";");

                } // enum values: all enum values in Promela are type=mtype and the value is a string with no spaces
                // some enum values have been changed to comply with these rules
                else if (renObj instanceof ValueEnum) {
                    String enumval = ((ValueEnum) renObj).getEnumConst();
                    type = TypeConverter.convertEnumBitFieldType();
                    output.addAll(Util.formatCommentString(subField.getInterpretation(), false));
                    output.add(type + " {" + enumval + "};");
                } else {
                    Logger.getLogger(JTSFileWriter.class.getName()).log(Level.WARNING,
                            "Unexpected class type while evaluating ValueRange or ValueEnum");

                }
                output.add("");
            }
        }
        if (!bl.isEmpty()) {
            output.add("};\n\n");
        }

        return output;
    }

    /**
     * Read bit field information and generate code for the definition.
     * @param bitField - xml binding for a bit_field
     * @param parentID - the ID for the parent of this object,
     * which allows embedded types to have a globally unique name
     * @return - generated code
     */
    protected List<String> processBitField(BitField bitField, String parentID) {
        List<String> output = new ArrayList<String>();

        String name = bitField.getName();
        String type = Util.getTypeNameFromDeclTypeRef(refMap, bitField.getName(), parentID);

        output.add(type + " " + name);
        return output;
    }

    /**
     * Uses the type, created elsewhere, within the definition of this type
     * @param declList input declaration
     * @param parentID - the ID for the parent of this object,
     * which allows embedded types to have a globally unique name
     * @return - generated code
     */
    protected List<String> processDeclaredList(DeclaredList declList, String parentID) {
        List<String> output = new ArrayList<String>();

        String name = declList.getName();
        String type = Util.getTypeNameFromDeclTypeRef(refMap, declList.getDeclaredTypeRef(), parentID);

        output.addAll(Util.formatCommentString(declList.getInterpretation(), false));
        output.add(type + " " + name + ";");

        return output;
    }

    /**
     * Uses the type, created elsewhere, within the definition of this type
     * @param declRec input declaration
     * @param parentID - the ID for the parent of this object,
     * which allows embedded types to have a globally unique name
     * @return - generated code
     */
    protected List<String> processDeclaredRecord(DeclaredRecord declRec, String parentID) {
        List<String> output = new ArrayList<String>();

        String name = declRec.getName();
        String type = Util.getTypeNameFromDeclTypeRef(refMap, declRec.getDeclaredTypeRef(), parentID);

        output.addAll(Util.formatCommentString(declRec.getInterpretation(), false));
        output.add(type + " " + name + ";");

        return output;
    }

    /**
     * Uses the type, created elsewhere, within the definition of this type
     * @param declSeq input declaration
     * @param parentID - the ID for the parent of this object,
     * which allows embedded types to have a globally unique name
     * @return - generated code
     */
    protected List<String> processDeclaredSequence(DeclaredSequence declSeq, String parentID) {
        List<String> output = new ArrayList<String>();

        String name = declSeq.getName();
        String type = Util.getTypeNameFromDeclTypeRef(refMap, declSeq.getDeclaredTypeRef(), parentID);

        output.addAll(Util.formatCommentString(declSeq.getInterpretation(), false));
        output.add(type + " " + name + ";");

        return output;
    }

    /**
     * Uses the type, created elsewhere, within the definition of this type
     * @param declVar input declaration
     * @param parentID - the ID for the parent of this object,
     * which allows embedded types to have a globally unique name
     * @return - generated code
     */
    protected List<String> processDeclaredVariant(DeclaredVariant declVar, String parentID) {
        List<String> output = new ArrayList<String>();
        String name = declVar.getName();
        String type = Util.getTypeNameFromDeclTypeRef(refMap, declVar.getDeclaredTypeRef(), parentID);

        output.addAll(Util.formatCommentString(declVar.getInterpretation(), false));
        output.add(type + " " + name + ";");
        return output;
    }

    /**
     * Generate code for a Variant
     * @param var - the variant to convert
     * @param parentID - the ID for the parent of this object,
     * which allows embedded types to have a globally unique name
     *
     * @return - code lines for the variant
     */
    protected List<String> processVariant(Variant var, String parentID) {
        List<String> output = new ArrayList<String>();
        String type = Util.getTypeNameFromDeclTypeRef(refMap, var.getName(), parentID);

        output.addAll(Util.formatCommentString(var.getInterpretation(), false));
        output.add(type + " " + var.getName() + ";");
        return output;
    }

    /**
     * Uses the Record to declare this type
     * @param rec input Record
     * @param parentID - the ID for the parent of this object,
     * which allows embedded types to have a globally unique name
     * @return - generated code
     */
    protected List<String> processListDef(org.jts.jsidl.binding.List list, String parentID) throws Exception {
        List<String> outputDef = new ArrayList<String>();
        List<String> waitingOutput = new ArrayList<String>();
        List<String> indentedOutput = new ArrayList<String>();
        String name = Util.getTypeNameFromDeclTypeRef(refMap, list.getName(), parentID);

        waitingOutput.addAll(Util.formatCommentString(list.getInterpretation(), false));
        waitingOutput.add("typedef " + name + "{");

        // try to get data
        Record rec = list.getRecord();
        org.jts.jsidl.binding.List jtsList = list.getList();
        Sequence seq = list.getSequence();
        org.jts.jsidl.binding.Variant var = list.getVariant();
        DeclaredList decllist = list.getDeclaredList();
        DeclaredRecord declrec = list.getDeclaredRecord();
        DeclaredSequence declseq = list.getDeclaredSequence();
        DeclaredVariant declvar = list.getDeclaredVariant();

        // process what we got
        if (rec != null) {
            outputDef.addAll(processRecordDef(rec, parentID + ":" + rec.getName()));
            indentedOutput.addAll(processRecord(rec, parentID + ":" + rec.getName()));
        } else if (jtsList != null) {
            outputDef.addAll(processListDef(jtsList, parentID + ":" + jtsList.getName()));
            indentedOutput.addAll(processList(jtsList, parentID + ":" + jtsList.getName()));
        } else if (seq != null) {
            outputDef.addAll(processSequenceDef(seq, parentID + ":" + seq.getName()));
            indentedOutput.addAll(processSequence(seq, parentID + ":" + seq.getName()));
        } else if (var != null) {
            outputDef.addAll(processVariantDef(var, parentID + ":" + var.getName()));
            indentedOutput.addAll(processVariant(var, parentID + ":" + var.getName()));
        } else if (declrec != null) {
            indentedOutput.addAll(processDeclaredRecord(declrec, parentID + ":" + declrec.getName()));
        } else if (decllist != null) {
            indentedOutput.addAll(processDeclaredList(decllist, parentID + ":" + decllist.getName()));
        } else if (declseq != null) {
            indentedOutput.addAll(processDeclaredSequence(declseq, parentID + ":" + declseq.getName()));
        } else if (declvar != null) {
            indentedOutput.addAll(processDeclaredVariant(declvar, parentID + ":" + declvar.getName()));
        }



        Util.indent(1, indentedOutput);
        waitingOutput.addAll(indentedOutput);
        waitingOutput.add("};\n\n");

        outputDef.addAll(waitingOutput);
        return outputDef;
    }

    /**
     * Uses the Record to declare this type
     * @param rec input Record
     * @param parentID - the ID for the parent of this object,
     * which allows embedded types to have a globally unique name
     * @return - generated code
     */
    protected List<String> processRecordDef(Record rec, String parentID) throws Exception {
        List<String> outputDef = new ArrayList<String>();
        List<String> waitingOutput = new ArrayList<String>();

        List<Object> tmpList = rec.getArrayOrFixedFieldOrVariableField();
        if (rec.getName() == null) {
            Logger.getLogger(JTSFileWriter.class.getName()).log(Level.WARNING, "Record name is null");
            throw new CodeGeneratorException("Unexpected class type while evaluating ValueRange or ValueEnum");
        }
        String recname = Util.getTypeNameFromDeclTypeRef(refMap, rec.getName(), parentID);
        // the following are object types that will not be processed
        // due to the incompatibility with Promela
        //DeclaredVariableField
        //DeclaredVariableLengthField
        //DeclaredVariableLengthString
        //DeclaredFixedLengthString
        //VariableFormatField
        //VariableField
        //DeclaredVariableFormatField
        //VariableLengthString
        //VariableLengthField
        //DeclaredArray
        //Array
        //FixedLengthString
        waitingOutput.addAll(Util.formatCommentString(rec.getInterpretation(), false));
        waitingOutput.add("typedef " + recname + "{");

        List<String> tmpout = new ArrayList<String>();
        for (Object tmpObj : tmpList) {
            //FixedField
            if (tmpObj instanceof FixedField) {
                FixedField field = (FixedField) tmpObj;
                tmpout.addAll(processFixedField(field, parentID + ":" + field.getName()));
                outputDef.addAll(processFixedFieldDef(field, parentID + ":" + field.getName()));
            } //DeclaredFixedField
            else if (tmpObj instanceof DeclaredFixedField) {
                DeclaredFixedField field = (DeclaredFixedField) tmpObj;
                // add some comments
                tmpout.addAll(Util.formatCommentString(field.getInterpretation(), false));

                String name = field.getName();
                String type = Util.getTypeNameFromDeclTypeRef(refMap, field.getDeclaredTypeRef(), parentID);

                tmpout.add(type + " " + name + ";");
                tmpout.add("");
            } //DeclaredBitField
            else if (tmpObj instanceof DeclaredBitField) {
                DeclaredBitField field = (DeclaredBitField) tmpObj;
                // add some comments
                tmpout.addAll(Util.formatCommentString(field.getInterpretation(), false));

                String name = field.getName();
                String type = Util.getTypeNameFromDeclTypeRef(refMap, field.getDeclaredTypeRef(), parentID);
                tmpout.add(type + " " + name + ";");

            } //BitField
            else if (tmpObj instanceof BitField) {
                BitField field = (BitField) tmpObj;
                // add some comments
                tmpout.addAll(Util.formatCommentString(field.getInterpretation(), false));
                String name = field.getName();
                String type = Util.getTypeNameFromDeclTypeRef(refMap, name, parentID);
                tmpout.add(type + " " + name + ";");
                outputDef.addAll(processBitFieldDef(field, parentID + ":" + field.getName()));
            } // not implemented
            else {
            }
        }
        Util.indent(1, tmpout);
        waitingOutput.addAll(tmpout);
        waitingOutput.add("};\n\n");

        outputDef.addAll(waitingOutput);
        return outputDef;
    }

    /**
     * Uses the type created by processSequence within the definition of this type
     * @param seq input Sequence
     * @param parentID - the ID for the parent of this object,
     * which allows embedded types to have a globally unique name
     * @return - generated code
     */
    protected List<String> processSequenceDef(Sequence seq, String parentID) throws Exception {
        List<String> output = new ArrayList<String>();
        List<String> waitingOutput = new ArrayList<String>();

        String name = Util.getTypeNameFromDeclTypeRef(refMap, seq.getName(), parentID);
        List<Object> tmplist = seq.getRecordOrDeclaredRecordOrList();
        for (Object tmpobj : tmplist) {
            if (tmpobj instanceof Record) {
                output.addAll(processRecordDef((Record) tmpobj, parentID + ":" + ((Record) tmpobj).getName()));
                waitingOutput.addAll(processRecord((Record) tmpobj, parentID + ":" + ((Record) tmpobj).getName()));
            } else if (tmpobj instanceof org.jts.jsidl.binding.List) {
                output.addAll(processListDef((org.jts.jsidl.binding.List) tmpobj,
                        parentID + ":" + ((org.jts.jsidl.binding.List) tmpobj).getName()));
                waitingOutput.addAll(processList((org.jts.jsidl.binding.List) tmpobj,
                        parentID + ":" + ((org.jts.jsidl.binding.List) tmpobj).getName()));
            } else if (tmpobj instanceof DeclaredRecord) {
                waitingOutput.addAll(processDeclaredRecord((DeclaredRecord) tmpobj,
                        parentID + ":" + ((DeclaredRecord) tmpobj).getName()));
            } else {
                Logger.getLogger(JTSFileWriter.class.getName()).log(Level.WARNING, "Unexpected class type while evaluating SequenceDef");
                throw new CodeGeneratorException("Unexpected class type while evaluating SequenceDef");
            }
        }

        Util.indent(1, waitingOutput);
        output.addAll(Util.formatCommentString(seq.getInterpretation(), false));
        output.add("typedef " + name + "{");
        output.addAll(waitingOutput);
        output.add("};");
        return output;
    }

    /**
     * Uses the type created by processVariant within the definition of this type
     * @param var input variant
     * @param parentID - the ID for the parent of this object,
     * which allows embedded types to have a globally unique name
     * @return - generated code
     */
    protected List<String> processVariantDef(Variant var, String parentID) throws Exception {
        List<String> output = new ArrayList<String>();
        List<String> waitingOutput = new ArrayList<String>();

        String name = Util.getTypeNameFromDeclTypeRef(refMap, var.getName(), parentID);
        List<Object> tmplist = var.getRecordOrDeclaredRecordOrList();
        for (Object tmpobj : tmplist) {
            if (tmpobj instanceof Record) {
                output.addAll(processRecordDef((Record) tmpobj, parentID + ":" + ((Record) tmpobj).getName()));
                waitingOutput.addAll(processRecord((Record) tmpobj, parentID + ":" + ((Record) tmpobj).getName()));
            } else if (tmpobj instanceof org.jts.jsidl.binding.List) {
                output.addAll(processListDef((org.jts.jsidl.binding.List) tmpobj,
                        parentID + ":" + ((org.jts.jsidl.binding.List) tmpobj).getName()));
                waitingOutput.addAll(processList((org.jts.jsidl.binding.List) tmpobj,
                        parentID + ":" + ((org.jts.jsidl.binding.List) tmpobj).getName()));
            } else if (tmpobj instanceof DeclaredRecord) {
                waitingOutput.addAll(processDeclaredRecord((DeclaredRecord) tmpobj,
                        parentID + ":" + ((DeclaredRecord) tmpobj).getName()));
            } else {
                Logger.getLogger(JTSFileWriter.class.getName()).log(Level.WARNING, "Unexpected class type while evaluating VariantDef");
                throw new CodeGeneratorException("Unexpected class type while evaluating VariantDef");
            }
        }

        Util.indent(1, waitingOutput);
        output.addAll(Util.formatCommentString(var.getInterpretation(), false));
        output.add("typedef " + name + "{");
        output.addAll(waitingOutput);
        output.add("};");
        return output;
    }

    /**
     * Processes all value sets or value enums and returns the definition
     * @param parentName - Name of parent which contains this data
     * @param valueRangeOrValueEnum - objects to process
     * @param parentID - the ID for the parent of this object,
     * which allows embedded types to have a globally unique name
     * @return - generated code
     */
    protected List<String> processValueSetOrEnumDef(String parentName, List<Object> valueRangeOrValueEnum, String parentID) throws Exception {
        List<String> output = new ArrayList<String>();
        String typename = Util.getTypeNameFromDeclTypeRef(refMap, parentName, parentID);
        String type = "";
        String enumvals = "";
        String enumtype = "mtype";
        boolean containsEnum = false;

        for (Object renObj : valueRangeOrValueEnum) {
            if (renObj instanceof ValueRange) {
                if (!containsEnum) {
                    String lower = ((ValueRange) renObj).getLowerLimit();
                    String upper = ((ValueRange) renObj).getUpperLimit();
                    type = TypeConverter.convertRangeBitFieldType(lower, upper);

                }
            } else if (renObj instanceof ValueEnum) {
                containsEnum = true;
                String enumval = ((ValueEnum) renObj).getEnumConst();
                enumval = Util.formatAsWord(enumval);
                // we can only define this once in Promela
                type = TypeConverter.convertEnumBitFieldType();
                if (enumList.get(enumval) == null || enumList.get(enumval).isEmpty()) {
                    enumList.put(enumval, typename);
                    if (enumvals.isEmpty()) {
                        enumvals = enumval;
                    } else {
                        enumvals += ", " + enumval;
                    }
                }
            } else {
                Logger.getLogger(JTSFileWriter.class.getName()).log(Level.WARNING,
                        "Unexpected class type while evaluating ValueRange or ValueEnum");
                throw new CodeGeneratorException("Unexpected class type while evaluating ValueRange or ValueEnum");
            }
        }

        // this can only be true if there is an enum and it wasn't a duplicate
        if (containsEnum) {
            // clear the list, since we aren't interested in the value range if its an enum
            output.clear();
            output.add("#define " + typename + " " + type);
            if (!enumvals.isEmpty()) {
                output.add(typename + " {" + enumvals + "};");
            }
        } else //if(!containsEnum)
        {
            output.add("#define " + typename + " " + type);
        }
        return output;
    }
}
