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

package org.jts.validator;

import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import com.u2d.app.Context;

import org.jts.gui.util.TypeConverter;

public class MessageDef 
{
	/**
	 * Validates a JSIDL message definition element.
	 * <pre>
		message_def =
		   element message_def {
		      attribute name { identifier },
				# 1. Must follow ANSI C syntax             
		      attribute message_id { xsd:hexBinary },
		      attribute is_command { xsd:boolean },
		      element description {  attribute xml:space { "default" | "preserve" }?, text },
		      (header | declared_header),
		      (body | declared_body),
		      (footer | declared_footer)
		   }
	 * </pre>
  	 * @param message JSIDL binding object of the message definition to be checked
	 * @throws ValidatorException 	
	 */
	public static void validate(org.jts.jsidl.binding.MessageDef message ) throws ValidatorException
	{
		Validator.validateName(message.getName());

	  // check for existence
		if (message.getMessageId() == null)
		{
				throw new ValidatorException("Message ID is not in proper 2 byte HEX format. Ex \"1AFF\"");
		}

		if(message.getMessageId().length != 2)
		{
			throw new ValidatorException("Message ID is not in proper 2 byte HEX format. Ex \"1AFF\"");
		}

	  if (message.getHeader() == null)
		{
				throw new ValidatorException("Header must be defined");
		}

		if (message.getBody() == null)
		{
				throw new ValidatorException("Body must be defined, even if empty");
		}

		if (message.getFooter() == null)
		{
				throw new ValidatorException("Footer must be defined, even if empty");
		}
		
		// SPECIAL!  For now, make sure the header is only a record with fixed field of unsigned short.
		try 
		{
			if (message.getHeader().getRecord().getArrayOrFixedFieldOrVariableField().size() != 1)
				throw new Exception();
				
			org.jts.jsidl.binding.FixedField ff = (org.jts.jsidl.binding.FixedField) message.getHeader().getRecord().getArrayOrFixedFieldOrVariableField().get(0);	
			if (ff.getFieldType().compareTo("unsigned short integer") != 0)
				throw new Exception();
		}
		catch (Exception e)
		{
			String dialogMsg = "JTS presently requires message headers that contain only an unsigned short integer.  Any other format is not recommended.";
			javax.swing.SwingUtilities.invokeLater(new ValidatorWarning(dialogMsg));
		}
	}
	
	public static com.u2d.generated.MessageDef getDuplicateMessageID( String ID )
	{
		// get current hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism();
		Session session = persistenceMechanism.getSession();

		// query for existing items
		String hql = "from MessageDef md where md.messageId=:messageId";
		Query hibernateQuery = session.createQuery( hql );
		//byte[] bytes = messageDef.getMessageId().toString();
		//String id = TypeConverter.byteArrayToHexString( messageId );
		hibernateQuery.setParameter( "messageId", ID );

		// get results
		return ( com.u2d.generated.MessageDef )hibernateQuery.uniqueResult();		
	}

	public static void replaceIfDuplicate( com.u2d.generated.MessageDef messageDef ) throws ValidatorException
	{
		if( messageDef == null )
		{
			return;
		}
		
		com.u2d.generated.MessageDef existingMessage = getDuplicateMessageID( messageDef.getMessageId().toString().toUpperCase() );

		// filter only when duplicate message id was found
		if( existingMessage != null )
		{
			// run popup window
			java.awt.EventQueue.invokeLater( new Runnable()
			{
				public void run()
				{
					int rval = javax.swing.JOptionPane.showConfirmDialog( null,
								"A message definition with this id already exists in the database. \n Do you want to overwrite this message?",
								"JTS Confirm Dialog", javax.swing.JOptionPane.YES_NO_OPTION );
					setUserOption( rval );
				}
			});

			// wait for user interaction with popup message
			while( userOption == -1 )
			{
				try
				{
					Thread.sleep( 500 );
				}
				catch( InterruptedException ie )
				{

				}
				
				if( userOption == 0 )
				{
					userOption = -1;
					
					com.u2d.persist.HBMSingleSession persistenceMechanism = 
						(com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism();
					Session session = persistenceMechanism.getSession();
					
					Transaction tx = session.beginTransaction();

					// reset properties for existing item in database 
					existingMessage.setBody( messageDef.getBody() );
					existingMessage.setDescription( messageDef.getDescription().stringValue() );
					existingMessage.setFooter( messageDef.getFooter() );
					existingMessage.setHeader( messageDef.getHeader() );
					existingMessage.setIsCommand( messageDef.getIsCommand().booleanValue() );
					existingMessage.setName( messageDef.getName().stringValue() );

					// change item in database
					session.update( existingMessage );
					tx.commit();
					
					throw new ValidatorException("MessageDef replaced, please close window");
				}
				else if( userOption == 1 || userOption == 2 )
				{
					userOption = -1;
					throw new ValidatorException("To avoid an overwrite, enter a unique message id");
				}
			}
		}
	}

	private static int userOption = -1;
	private static void setUserOption( int option )
	{
		userOption = option;
	}

}
