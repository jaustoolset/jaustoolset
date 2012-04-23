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

package org.jts.pbValidator;

import java.util.List;
import java.util.ArrayList;

/**
 * This class is the head of the chain of responsibility pattern used in this package
 * @author Arfath Pasha
 *
 */
public class Validator 
{
    public Validator() {}
   
   /**
   *   @param sdefs List of service defs ordered in the order of their inheritance chain
   *   @param toValidate protocol behavior object belonging to the last derived service
   *
   */
    public void validate( List<org.jts.jsidl.binding.ServiceDef> sdefs, Object toValidate ) throws ValidatorException
    {
    	ProtocolBehavior pb = new ProtocolBehavior();

    	List<ValidatorResult> results = pb.validate( sdefs, toValidate );

    	if( results.size() > 0 )
    	{
    		throw new ValidatorException( results );
    	}
   }

    /**
     * validate the mxCells, only supports validation of the root state machine right now
     * @param root
     * @throws ValidatorException
     */
    public void validate( com.mxgraph.model.mxCell cell ) throws ValidatorException
    {
    	MxCell mxCell = new MxCell();

    	List<ValidatorResult> results = mxCell.validate( cell );

    	if( results.size() > 0 )
    	{
    		throw new ValidatorException( results );
    	}
   }
   
   public static void main(String [ ] args) {
       System.out.println( "Validating..." );
       org.jts.jsidl.binding.ServiceDef sdef = null;
       
       try {
          javax.xml.bind.JAXBContext jc = javax.xml.bind.JAXBContext.newInstance( "org.jts.jsidl.binding" );
          javax.xml.bind.Unmarshaller u = jc.createUnmarshaller();
          sdef  = (org.jts.jsidl.binding.ServiceDef)u.unmarshal( new java.io.File( "resources/xml/urn.jaus.jss.core/Management.xml" ) );
       } catch(  javax.xml.bind.JAXBException jaxbe ) {
          jaxbe.printStackTrace();
       }
       
       Validator validator = new Validator();
       try {
          List<org.jts.jsidl.binding.ServiceDef> sdefs = new ArrayList<org.jts.jsidl.binding.ServiceDef>();
          sdefs.add(sdef);
          validator.validate( sdefs, sdef.getProtocolBehavior() );
       } catch( ValidatorException ve) {
          List<ValidatorResult> results = ve.getResults();
          
          for( ValidatorResult result: results ) 
             System.out.println( result.getPath()+"\n" );
       }
   }
}
