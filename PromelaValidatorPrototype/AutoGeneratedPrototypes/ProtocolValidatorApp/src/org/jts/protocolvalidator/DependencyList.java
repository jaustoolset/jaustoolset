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
import java.util.Collections;
import java.util.List;

/**
 * Used to create an ordered list of include files based on all
 * the file dependencies.  The list is maintained in an order where
 * the each file depends on the files below.
 * @author cmessmer
 */
public class DependencyList extends ArrayList<String> {

    /**
     * Incorporates a file dependency to our include files list
     * @param file1 - the file on which file2 depends
     * @param file2 - the file depending on file1
     */
    public void addDependency(String file1, String file2) {
        String include1 = "#include \"" + file1 + "\"";
        String include2 = "#include \"" + file2 + "\"";

        // neither are in the list so add them in order
        if (!contains(include1)
                && !contains(include2)) {
            //
            add(include2);
            add(include1);
        }
        // both are already in the list, so make sure they are in order
        // if not, move the second below the first
        else if (contains(include1)
                && contains(include2)) {
            int index1 = indexOf(include1);
            int index2 = indexOf(include2);
            if (index1 < index2) {
                remove(include2);
                insertSecondBeforeFirst(include1, include2);
            }
        }
        // The first is already in the list, so put the second in the proper location
        else if (contains(include1) && !contains(include2)) {
            insertSecondBeforeFirst(include1, include2);
        }
        // The second is already in the list, so put the first in the proper location
        else if (contains(include2) && !contains(include1)) {
            insertFirstAfterSecond(include1, include2);
        }
    }

    /**
     * Inserts the second include statement in the list after the first
     * @param include1 - first include statement
     * @param include2 - dependent include statement
     */
    private void insertSecondBeforeFirst(String include1, String include2) {
        int index1 = indexOf(include1);
        // split the list so we can insert the element at the right spot
        List<String> listTop = new ArrayList<String>();
        if (index1 - 1 == 0) {
            // there is only one item above include1
            listTop.add(get(0));
        } else {
            listTop.addAll(subList(0, index1));
        }

        List<String> listBottom = new ArrayList<String>();
        if (index1 < size() - 1) {
            listBottom.addAll(subList(index1, size()));
        } else {
            // include1 is the only item at the bottom of the list
            listBottom.add(include1);
        }
        clear();

        addAll(listTop);
        add(include2);
        addAll(listBottom);

    }

    /**
     * Inserts the first in the list after the second
     * @param include1 - first file
     * @param include2 - file dependent on the first file
     */
    private void insertFirstAfterSecond(String include1, String include2) {
        int index2 = indexOf(include2);
        // split the list so we can insert the element at the right spot
        List<String> listTop = new ArrayList<String>();
        if (index2 == 0) {
            // there is only one item above include1
            listTop.add(include2);
        } else {
            listTop.addAll(subList(0, index2 + 1));
        }

        List<String> listBottom = new ArrayList<String>();
        if (index2 < size() - 1) {
            listBottom.addAll(subList(index2 + 1, size()));
        } else {
            // include1 is the only item at the bottom of the list
            //listBottom.add(include2);
        }
        clear();

        addAll(listTop);
        add(include1);
        addAll(listBottom);
    }

    /**
     * Retrieve the list of include statements in top-down or bottom-up order.
     * @param reverse - true if the list should be in reverse order,
     * meaning that included files with no dependencies would be at the top of the list.
     * @return - the list of include file strings
     */
    public List<String> getIncludeList(boolean reverse) {
        List<String> tmpList = new ArrayList<String>();
        tmpList.addAll(this);
        if (reverse) {
            Collections.reverse(tmpList);
        }
        return tmpList;
    }
}
