/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010-2011, United States Government
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jts.docGenerator.util;

import java.io.File;

/**
 * Provides methods for dealing with URLS and paths used during document generation.
 * @author idurkan
 */
public class URLHelpers {
    /**
     * Replaces the OS-specific path separator characters in a string builder with URL path separators ('/').
     */
    public static void urlize(StringBuilder str) {
        for (int ii = 0; ii < str.length(); ii++) {
            if (str.charAt(ii) == File.separatorChar) {
                str.setCharAt(ii, '/');
            }
        }
    }

    /**
     * Gets the relative URL to the file-path given in urlBuilder, relative to the directory in ssetDirPath.
     * The path in urlBuilder must be in the path format specific to the machine running JTS; it must also be a
     * a path to a file under ssetDirPath, and setDirPath must be a directory.  If these preconditions aren't met,
     * behavior is undefined.
     * @param urlBuilder Must contain a path to a file under the directory in ssetDirPath.
     * @param ssetDirPath The directory that contains the path given in urlBuilder
     * @return A string giving the relative URL to the file in urlBuilder from the directory in ssetDirPath.
     */
    public static String getRelativeURL(StringBuilder urlBuilder, File ssetDirPath) {
        // remove parent directories
        urlBuilder = urlBuilder.delete(0, ssetDirPath.toString().length() + 1);
        urlize(urlBuilder);

        return urlBuilder.toString();
    }

    /**
     * See StringBuilder version; same behavior but with a string argument.
     * @param filePath
     * @param baseDir
     * @return
     */
    public static String getRelativeURL(String filePath, File baseDir) {
        // make link relative
        filePath = new File(
                new File(baseDir.getPath()).toURI().relativize(
                new File(filePath).toURI()).getPath()).getPath();

        StringBuilder urlBuilder = new StringBuilder(filePath);

        URLHelpers.urlize(urlBuilder);

        return urlBuilder.toString();
    }

}
