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

/**
 * Used by the DataTypeProcess to convert a JSIDL data type into a Promela data type
 * @author cmessmer
 */
public class TypeConverter {

    /**
     * many types are not supported between Promela and JSIDL, so this function
     * basically represents all types as an int.
     * @param inputType - the input integral type
     * @return - the Promela type it is converted to
     */
    static String convertType(String inputType) {
        String outputType = "";
        if (inputType.contains("unsigned")) {
            // while Promela supports multiple unsigned types
            // it is difficult to implement and may be done later if necessary
            outputType = "int";
        } else if (inputType.contains("integer") || inputType.contains("byte")) {
            outputType = "int";
        }

        return outputType;
    }

    /**
     * Converts a range bit field to an integral type of the correct size.
     * @param lower - lower limit
     * @param upper - upper limit
     * @return - The Promela type to us
     */
    static String convertRangeBitFieldType(String lower, String upper) {
        String outputType = "";

        long lupper = Long.valueOf(upper);
        long llower = Long.valueOf(lower);
        long range = lupper - llower;

        if (range <= 256) {
            outputType = "byte";
        } else if (range <= 65536) {
            outputType = "short";
        } else {
            outputType = "int";
        }

        return outputType;
    }

    /**
     * Converts an enum bitfield to a Promela type
     * @return - Promela uses "mtype" as the enum type.
     */
    static String convertEnumBitFieldType() {
        String outputType = "mtype";

        return outputType;
    }
}
