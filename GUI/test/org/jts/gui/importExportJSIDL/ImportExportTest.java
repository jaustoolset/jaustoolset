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

package org.jts.gui.importExportJSIDL;

import org.junit.*;

import com.u2d.type.atom.FileEO;
import com.u2d.type.atom.FileWEO;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.u2d.app.Application;

import java.io.File;

import org.jts.gui.importJSIDL.Import;
import org.jts.gui.exportJSIDL.Export;


/**
 *
 * @author Dave
 */
public class ImportExportTest {

    @Test
    public void importExport() {

		// Set-up some test parameters
		String output_dir = "test/org/jts/gui/importExportJSIDL/output_xml/";

		//Service defs tested...
		String service_def_input_files[] = {"resources/xml/urn.jaus.jss.core/Transport.xml",
								"resources/xml/urn.jaus.jss.core/Events.xml",
								"resources/xml/urn.jaus.jss.core/AccessControl.xml",
								"resources/xml/urn.jaus.jss.core/Management.xml",
								"resources/xml/urn.jaus.jss.core/Liveness.xml",
								"resources/xml/urn.jaus.jss.core/Time.xml",
								"resources/xml/urn.jaus.jss.core/Discovery.xml",
								"resources/xml/urn.jaus.jss.mobility/ListManager.xml",
								"resources/xml/urn.jaus.jss.mobility/GlobalPoseSensor.xml",
								"resources/xml/urn.jaus.jss.mobility/GlobalVectorDriver.xml",
								"resources/xml/urn.jaus.jss.mobility/GlobalWaypointDriver.xml",
								"resources/xml/urn.jaus.jss.mobility/GlobalWaypointListDriver.xml"};

		String service_def_names_and_versions[] = {"urn:jaus:jss:core:Transport", "1.0",
												   "urn:jaus:jss:core:Events", "1.0",
												   "urn:jaus:jss:core:AccessControl", "1.0",
												   "urn:jaus:jss:core:Management", "1.0",
												   "urn:jaus:jss:core:Liveness", "1.0",
												   "urn:jaus:jss:core:Time", "1.0",
		   										   "urn:jaus:jss:core:Discovery", "1.0",
		   										   "urn:jaus:jss:mobility:ListManager", "1.0",
		   										   "urn:jaus:jss:mobility:GlobalPoseSensor", "1.0",
		   										   "urn:jaus:jss:mobility:GlobalVectorDriver", "1.0",
		   										   "urn:jaus:jss:mobility:GlobalWaypointDriver", "1.0",
		   										   "urn:jaus:jss:mobility:GlobalWaypointListDriver", "1.0"};

	    // seed database
	    Logger.getLogger("org.springframework").setLevel(Level.WARNING);
	    ApplicationContext context = 
			new ClassPathXmlApplicationContext("applicationContext.xml");
	    ((Application) context.getBean("application")).seedDatabase();

		// import service defs
		for (int i=0; i<service_def_input_files.length; i++)
		{
			File file = new File(service_def_input_files[i]);
			System.out.printf("Importing ServiceDefs from %s\n", file.getAbsolutePath());
			// removed because import changed, add back when fixed
			FileEO readFile = new FileEO(file);
                        Import tmpimport = new Import();
			tmpimport.importServiceDefs( file );
		}
	    
		// export service defs
		for (int i=0; i<service_def_names_and_versions.length; i+=2)
		{
			com.u2d.generated.ServiceDef ret = 
				 org.jts.gui.JAXBtoJmatter.ServiceDef.lookupServiceDef(service_def_names_and_versions[i], service_def_names_and_versions[i+1]);
			if(ret == null)
			{
				System.out.printf("ServiceDef with name: \"%s\" and version: \"%s\" not found in database.", service_def_names_and_versions[i], service_def_names_and_versions[i+1]);
				Assert.assertNotNull(ret);
			}
			else
			{
				File file = new File(output_dir + service_def_names_and_versions[i].substring(service_def_names_and_versions[i].lastIndexOf(":")+1) + ".xml");
				System.out.printf("Exporting ServiceDefs to %s\n", file.getAbsolutePath());
				Export.exportServiceDefJSIDL( ret, file );
			}
		}
    }
}
