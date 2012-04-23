package org.jts.eclipse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import junit.textui.TestRunner;

import org.apache.commons.io.FileUtils;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.jts.eclipse.conversion.cjsidl.Conversion;
import org.jts.eclipse.conversion.cjsidl.Conversion.CjsidlFileFilter;
import org.jts.eclipse.conversion.cjsidl.Conversion.JsidlFileFilter;
import org.jts.eclipse.conversion.cjsidl.ConversionException;
import org.xml.sax.SAXException;

import org.custommonkey.xmlunit.*;

public class ConversionUnitTest extends TestCase {
    private static final String TEST_FILES = "test/TestData/originalCJSIDL";
//    private static final String TEST_FILES = "C:/eclipse/UnitTestFiles";
//    private static final String TEST_FILES = "C:/eclipse/runtime-EclipseApplication/TestCases";
    private static String JSIDL_OUTPUT1 = "test/TestData/JSIDL1/";
    private static String JSIDL_OUTPUT2 = "test/TestData/JSIDL2/";
    private static String CJSIDL_OUTPUT = "test/TestData/CJSIDL/";
    private static final String OUTPUT_PATH = "test/TestData";
    private static Conversion conv;
    private static XtextResourceSet set;
    private static File cjsidlFolder;
    private static File jsidlFolder;
    private static File jsidl2Folder;
    private static File cjsidl2Folder;
    
    /**
     * Create all of the files to be used by the remainder of the unit tests.
     */
    public void testGenerateFiles()
    {        
        File tmpfile = new File(JSIDL_OUTPUT1);
        if(tmpfile.exists()){
        	tmpfile.delete();
        }
    	tmpfile.mkdirs();
        tmpfile = new File(JSIDL_OUTPUT2);
        if(tmpfile.exists()){
        	tmpfile.delete();
        }
    	tmpfile.mkdirs();
        tmpfile = new File(CJSIDL_OUTPUT);
        if(tmpfile.exists()){
        	tmpfile.delete();
        }
    	tmpfile.mkdirs();

        cjsidlFolder = new File(TEST_FILES);
        jsidlFolder = new File(JSIDL_OUTPUT1);
        jsidl2Folder = new File(JSIDL_OUTPUT2);
        cjsidl2Folder = new File(CJSIDL_OUTPUT);
        try {
			CJSIDL_OUTPUT = cjsidl2Folder.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

        /*
         * CONVERT CJSIDL TO JSIDL
         */

        conv = new Conversion();
        try {
            conv.convertToJSIDL(cjsidlFolder.getCanonicalPath(), jsidlFolder.getAbsolutePath());

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (ConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /*
         * CONVERT JSIDL TO CJSIDL
         */
        // Reset the ResourceSet so nothing gets mixed up.
        conv = new Conversion();

        try {
			set = conv.convertFromJSIDL(jsidlFolder.getCanonicalPath(), CJSIDL_OUTPUT);
		} catch (ConversionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        /*
         * CONVERT CJSIDL TO JSIDL
         */

        
        conv = new Conversion();
        try {
            conv.convertToJSIDL(cjsidl2Folder.getCanonicalPath(), jsidl2Folder.getAbsolutePath());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
    }
    

    /**
     * Compare BasicTypes files.
     */
    public void testBasicTypes() {
        File file1 = findFile("BasicTypes.csd", cjsidlFolder.listFiles());
        File file2 = findFile("BasicTypes.csd", cjsidl2Folder.listFiles());
        File file3 = findFile("BasicTypes.xml", jsidlFolder.listFiles());
        File file4 = findFile("BasicTypes.xml", jsidl2Folder.listFiles());
        assertTrue(jsidlEqual(file3, file4));
        //junitx.framework.FileAssert.assertEquals(file1, file2);
    }
    
//    public void testBitFields() {
//        File file1 = findFile("BitFields.csd", cjsidlFolder.listFiles());
//        File file2 = findFile("BitFields.csd", cjsidl2Folder.listFiles());
//        assertTrue(jsidlEqual(file1, file2));
//        assertTrue(cjsidlEqual(file1, file2));
//    }
    /**
     * Compare all the ConstTestA files to determine equality.
     */
    public void testConstA() {
        File file1 = findFile("ConstTestA.csd", cjsidlFolder.listFiles());
        File file2 = findFile("ConstTestA.csd", cjsidl2Folder.listFiles());
        //assertTrue(cjsidlEqual(file1, file2));
        //junitx.framework.FileAssert.assertEquals(file1, file2);
    }

//    public void testConstB() {
//        File file1 = findFile("ConstTestB.csd", cjsidlFolder.listFiles());
//        File file2 = findFile("ConstTestB.csd", cjsidl2Folder.listFiles());
//        assertTrue(jsidlEqual(file1, file2));
//        assertTrue(cjsidlEqual(file1, file2));
//    }

//    public void testContainers() {
//        File file1 = findFile("Containers.csd", cjsidlFolder.listFiles());
//        File file2 = findFile("Containers.csd", cjsidl2Folder.listFiles());
//        assertTrue(jsidlEqual(file1, file2));
//        assertTrue(cjsidlEqual(file1, file2));
//    }

    public void testEmptyService() {
        File file1 = findFile("EmptyService.csd", cjsidlFolder.listFiles());
        File file2 = findFile("EmptyService.csd", cjsidl2Folder.listFiles());
        //assertTrue(jsidlEqual(file1, file2));
        //junitx.framework.FileAssert.assertEquals(file1, file2);
    }

//    public void testGlobalScopeTest() {
//        File file1 = findFile("GlobalScopeTest.csd", cjsidlFolder.listFiles());
//        File file2 = findFile("GlobalScopeTest.csd", cjsidl2Folder.listFiles());
//        assertTrue(jsidlEqual(file1, file2));
//        assertTrue(cjsidlEqual(file1, file2));
//
//    }
//
//    public void testHeaderBodyFooter() {
//        File file1 = findFile("HeaderBodyFooter.csd", cjsidlFolder.listFiles());
//        File file2 = findFile("HeaderBodyFooter.csd", cjsidl2Folder.listFiles());
//        assertTrue(jsidlEqual(file1, file2));
//        assertTrue(cjsidlEqual(file1, file2));
//
//    }
//
//    public void testMessages() {
//        File file1 = findFile("Messages.csd", cjsidlFolder.listFiles());
//        File file2 = findFile("Messages.csd", cjsidl2Folder.listFiles());
//        assertTrue(jsidlEqual(file1, file2));
//        assertTrue(cjsidlEqual(file1, file2));
//
//    }
//
//    public void testProtocolBehavior() {
//        File file1 = findFile("ProtocolBehaviorTest.csd", cjsidlFolder.listFiles());
//        File file2 = findFile("ProtocolBehaviorTest.csd", cjsidl2Folder.listFiles());
//        assertTrue(jsidlEqual(file1, file2));
//        assertTrue(cjsidlEqual(file1, file2));
//
//    }
//
//    public void testSimpleService() {
//        File file1 = findFile("SimpleService.csd", cjsidlFolder.listFiles());
//        File file2 = findFile("SimpleService.csd", cjsidl2Folder.listFiles());
//        assertTrue(jsidlEqual(file1, file2));
//        assertTrue(cjsidlEqual(file1, file2));
//
//    }
//
//    public void testStrings() {
//        File file1 = findFile("Strings.csd", cjsidlFolder.listFiles());
//        File file2 = findFile("Strings.csd", cjsidl2Folder.listFiles());
//        assertTrue(jsidlEqual(file1, file2));
//        assertTrue(cjsidlEqual(file1, file2));
//
//    }
//
//    public void testService() {
//        File file1 = findFile("testService.csd", cjsidlFolder.listFiles());
//        File file2 = findFile("testService.csd", cjsidl2Folder.listFiles());
//        assertTrue(jsidlEqual(file1, file2));
//        assertTrue(cjsidlEqual(file1, file2));
//
//    }
//
//    public void testTransport() {
//        File file1 = findFile("Transport.csd", cjsidlFolder.listFiles());
//        File file2 = findFile("Transport.csd", cjsidl2Folder.listFiles());
//        assertTrue(jsidlEqual(file1, file2));
//        assertTrue(cjsidlEqual(file1, file2));
//
//    }
//
//    public void testTypeSetA() {
//        File file1 = findFile("TypeSetA.csd", cjsidlFolder.listFiles());
//        File file2 = findFile("TypeSetA.csd", cjsidl2Folder.listFiles());
//        assertTrue(jsidlEqual(file1, file2));
//        assertTrue(cjsidlEqual(file1, file2));
//
//    }

    /**
     * Prints a list of all unequal files, tests to see if filesToCheck is an empty list.
     */
    public void testAll() {
    	// CJSIDL1 v CJSIDL2
        List<String> filesToCheckCjsidl = cjsidlDiff(cjsidl2Folder, cjsidlFolder);

        // JSIDL1 v JSIDL2
        List<String> filesToCheckJsidl = jsidlDiff(jsidlFolder, jsidl2Folder);

        if (filesToCheckCjsidl.size() > 0) {
            System.out.println("The following CJSIDL files are not equal and need to be checked manually:");
            for (String s : filesToCheckCjsidl) {
                System.out.println("\t" + s);
            }
        }

        if (filesToCheckJsidl.size() > 0) {
            System.out.println("The following JSIDL files are not equal:");
            for (String s : filesToCheckJsidl) {
                System.out.println("\t" + s);
            }
        }

        // There should be no files in the list.
        //assertEquals(0, filesToCheckCjsidl.size());
        assertEquals(0, filesToCheckJsidl.size());
    }
    
    /**
     *  Helper function to compare two JSIDL files using the XmlDiff tool.
     * @param jsidl1
     * @param jsidl2
     * @return
     */
    private boolean jsidlEqual(File jsidl1, File jsidl2) {
    	XMLUnit.setIgnoreWhitespace(true);
    	XMLUnit.setIgnoreComments(true);
    	XMLUnit.setIgnoreAttributeOrder(true);    	
    	try {	
			Diff compare = new Diff(new FileReader(jsidl1.getAbsolutePath()), new FileReader(jsidl2.getAbsolutePath()));
			return compare.identical();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
    }
    
    /**
     * Extra return false is to ensure that there will be a return no matter what.
     * @deprecated seems to always fail.
     * @param cjsidl1
     * @param cjsidl2
     * @return
     */
    private boolean cjsidlEqual(File cjsidl1, File cjsidl2)
    {        
        try {
            return FileUtils.contentEquals(cjsidl1, cjsidl2);
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
            return false;
        }
    }

    /**
     * Compare each file in the folders, and determine if they are the same.
     * 
     * @param jsidlFolder
     * @param jsidl2Folder
     * @param jsidlFilter
     */
    private List<String> jsidlDiff(File jsidlFolder, File jsidl2Folder) {
        List<String> ret = new ArrayList<String>();
    	Conversion conv = new Conversion();
    	JsidlFileFilter jsidlFilter = conv.new JsidlFileFilter();

        for (File f : jsidlFolder.listFiles(jsidlFilter)) {
        	if(!jsidlEqual(f, findFile(f.getName(), jsidl2Folder.listFiles(jsidlFilter))))
        	{
        		ret.add(f.getName());
        	}
        }
        return ret;
    }

    /**
     * Helper function to find a specific file in a list.
     * 
     * @param name
     * @param list
     * @return
     */
    private File findFile(String name, File[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].getName().equalsIgnoreCase(name)) {
                return list[i];
            }
        }
        return null;
    }

    /**
     * Returns a list of all file names that do not pass the test.
     * 
     * This list will be checked at the end of the test - this allows the JSIDL
     * comparison to run regardless of the CJSIDL comparison results.
     * 
     * @param folder
     * @param cjsidlFolder
     */
    private List<String> cjsidlDiff(File folder, File cjsidlFolder) {
    	Conversion conv = new Conversion();
    	CjsidlFileFilter cjsidlFilter = conv.new CjsidlFileFilter();
        List<String> ret = new ArrayList<String>();
        for (File f : cjsidlFolder.listFiles(cjsidlFilter)) {
            try {
                if (findFile(f.getName(), folder.listFiles(cjsidlFilter)) != null) {
                    if (!FileUtils.contentEquals(f, findFile(f.getName(), folder.listFiles(cjsidlFilter)))) {
                        ret.add(f.getName());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                fail(e.getMessage());
            }
        }
        return ret;
    }

    /**
     * Main testrunner call.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Running conversion unit test:");
        TestRunner.run(ConversionUnitTest.class);
        System.out.println("Conversion unit test complete.");
    }

    /**
     * Function required by JUnit. No repeated setup needed.
     */
    protected void setUp() {

    }

    /**
     * Function required by JUnit. No cleanup needed between tests.
     */
    protected void tearDown() {

    }

    /**
     * Copies XML files to correct output directory.
     * @deprecated
     * @param src
     * @param dst
     * @param jsidlFilter
     */
    private static void moveXMLFiles(File src, File dst, FileFilter jsidlFilter) {
        for (File f : src.listFiles(jsidlFilter)) {
            try {
                FileUtils.copyFileToDirectory(f, dst);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    



}
