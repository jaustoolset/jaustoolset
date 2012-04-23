/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jts.gui.importCJSIDL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 *
 * @author vnearing, cmessmer
 *
 */
    public class StreamReader extends Thread
    {
        InputStream is;
        String type;
        String data="";

        public StreamReader(InputStream is, String type)
        {
            this.is = is;
            this.type = type;
        }

        public void run()
        {
            try
            {
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line=null;
                while ( (line = br.readLine()) != null){
                    data += line + "\n";
                }
            } catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }

        public String getData(){
            return data;
        }
    }