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

package org.jts.docGenerator;

import org.jts.gui.docGenerator.DocgenStatusMonitor;

/**
 * Abstract class defining common capabilities of all concrete DocGenerators.
 * Implements runnable for the sake of avoiding the need to generate wrapper Runnable implementations
 * for carrying out document generation.
 * @author idurkan
 */
public abstract class DocGenerator implements Runnable {

    DocgenStatusMonitor statusMonitor = null;

    public final void setStatusMonitor(DocgenStatusMonitor monitor) {
        statusMonitor = monitor;
    }

    /**
     * Generate documentation after concrete instance's constructor is called providing
     * all needed info.
     */
    public abstract void generate();

    public void run() {
        if (statusMonitor == null) {
            throw new RuntimeException("Status monitor was null when running a DocGenerator; "
                    + "it must be set via setStatusMonitor.");
        }

        try {
            generate();
            statusMonitor.postSuccess("Document generation complete.");
        } catch (Exception ex) {
            statusMonitor.postFailure("An error occurred during document generation.", ex);
            throw new RuntimeException(ex);
        }
    }

}
