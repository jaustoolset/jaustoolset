package com.mxgraph.swing.view.Editor;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import com.mxgraph.swing.view.Editor.Nodes.TransitionNode;
import com.u2d.list.RelationalList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.proxy.HibernateProxy;

import com.u2d.app.Context;
import org.jts.codegenerator.CodeLines;

public class TreeModel extends DefaultTreeModel
{
	private HashMap<String, String> storedTransitions;
	private ArrayList<String> storedGuards;
	private ArrayList<String> storedActions;
	private ArrayList<String> storedPush;
	private HashMap<String, HashHolder> triggerParameters;
	private ArrayList<String> allMessages;
	
	private com.u2d.generated.ProtocolBehavior protocolBehavior;
	
	public TreeModel( TreeNode root )
	{
		super(root);

		storedTransitions = new HashMap<String, String>();
		storedGuards = new ArrayList<String>();
		storedActions = new ArrayList<String>();
		storedPush = new ArrayList<String>();
		triggerParameters = new HashMap<String, HashHolder>();
		
		allMessages = new ArrayList<String>();
	}
	
	public void setAutocompleteVariables( com.u2d.generated.ProtocolBehavior pb )
	{
		protocolBehavior = pb;

		// when there is no reference, return
		if( protocolBehavior == null )
		{
			return;
		}
		
		// assume only one referencing service definition per protocolBehavior
		RelationalList rSdf = protocolBehavior.getReferencingServiceDefs();
		
		if(rSdf == null || rSdf.getItems() == null || rSdf.getItems().size() == 0)
		{
			return;
		}
		
		// assume first service def from root is always good
		com.u2d.generated.ServiceDef serviceDef = (com.u2d.generated.ServiceDef)rSdf.getItems().get(0);
		
		// change to jaxb
		//org.jts.jsidl.binding.ServiceDef sd = org.jts.gui.jmatterToJAXB.ServiceDef.convert(serviceDef);

		// recurse through service chain
		recurseVariables(serviceDef, "");
	}
	
	private void recurseVariables(com.u2d.generated.ServiceDef serviceDef, String inheritedName)
	{
		if(serviceDef != null)
		{
			org.jts.jsidl.binding.ProtocolBehavior pb = org.jts.gui.jmatterToJAXB.ProtocolBehavior.convert(serviceDef.getProtocolBehavior());
			
			org.jts.codegenerator.protocolBehavior.Transition.getEndStateNames(pb, storedPush);
			ArrayList<String> tempGuards = new ArrayList<String>();
			org.jts.codegenerator.protocolBehavior.Guard.getGuards(pb, tempGuards, CodeLines.CodeType.C_PLUS_PLUS);
			// guards include a complete function declaration at this point
			// we only need to store the function names
			for( String guard : tempGuards )
			{
				if( guard.contains( "(" ) )
				{
					guard = guard.substring( 0, guard.indexOf( "(" ) );
				}
				
				storedGuards.add( guard );
			}

			org.jts.codegenerator.protocolBehavior.Action.getActions(pb, storedActions);
			
			// input message set
			addInputSet(serviceDef, inheritedName);

			// output message set
			addOutputSet(serviceDef, inheritedName);

			// event def
			addEventDef(serviceDef, inheritedName);

			// recurse through referenced service defs to find all other protocol behaviors along inheritance chain
			//if(serviceDef.getReferences() != null && serviceDef.getReferences().getInheritsFrom() != null)
			if(serviceDef.getInheritsFrom() != null)
			{
				// find the current name of the inheritance
				//inheritedName = inheritedName + serviceDef.getReferences().getInheritsFrom().getName() + ".";
				inheritedName = inheritedName + serviceDef.getInheritsFromName().stringValue() + ".";
				
				String id = serviceDef.getInheritsFrom().getServiceId().stringValue();
				String version = serviceDef.getInheritsFrom().get_version().toString();
	
				com.u2d.generated.ServiceDef genServiceDef = org.jts.gui.JAXBtoJmatter.ServiceDef.lookupServiceDef(id, version);

				if(genServiceDef != null)
				{
					//org.jts.jsidl.binding.ServiceDef sd = org.jts.gui.jmatterToJAXB.ServiceDef.convert(genServiceDef);
					
					recurseVariables(genServiceDef, inheritedName);
				}
			}
		}
	}
	
	private void addInputSet(com.u2d.generated.ServiceDef serviceDef, String inheritedName)
	{
		if(serviceDef.getInputSet() != null)
		{					
			com.u2d.generated.InputSet input = serviceDef.getInputSet();
			
			if(input.getMessageDefs() != null && input.getMessageDefs().getItems() != null)
			{
				for(Object obj:input.getMessageDefs().getItems())
				{
					if(obj instanceof com.u2d.generated.MessageDef)
					{
						addMessageDef((com.u2d.generated.MessageDef)obj, inheritedName, serviceDef.getName().stringValue());
					}
				}
			}
		}
	}
	
	private void addOutputSet(com.u2d.generated.ServiceDef serviceDef, String inheritedName)
	{
		if(serviceDef.getOutputSet() != null)
		{					
			com.u2d.generated.OutputSet output = serviceDef.getOutputSet();
			
			if(output.getMessageDefs() != null && output.getMessageDefs().getItems() != null)
			{
				for(Object obj:output.getMessageDefs().getItems())
				{
					if(obj instanceof com.u2d.generated.MessageDef)
					{
						addMessageDef((com.u2d.generated.MessageDef)obj, inheritedName, serviceDef.getName().stringValue());
					}
				}
			}
		}
	}
	
	private void addMessageDef(com.u2d.generated.MessageDef msgDef, String inheritedName, String currentServiceName)
	{
		String messageName = msgDef.getName().toString();
		String transitionName = inheritedName + messageName;
		
		// store transition name for trigger completion
		// we want to abbreviate the full name when showing it in the popup	
		String[] parts = transitionName.split("\\.");
		
		// last is the messageName
		String abbreviation = parts[parts.length-1];
		
		// second to last is the abbreviation if there is any
		if(parts.length > 1)
		{
			abbreviation = parts[parts.length-2] + "." + abbreviation; 
		}
		
		if(inheritedName.compareTo("") == 0)
		{
			// add current service name to transition when we are in top service
			storedTransitions.put(currentServiceName + "." + transitionName, transitionName);
		}
		else
		{
			// store abbreviation mapped to its full name
			storedTransitions.put(abbreviation, transitionName);
		}
		
		// store messageName for all message list
		allMessages.add(messageName);

		HashHolder temp = new HashHolder();
		
		checkSubJausMessage(temp, msgDef, messageName);
		
		if(temp.size() > 0)
		{
			triggerParameters.put(transitionName, temp);
		}
	}

	private void addEventDef(com.u2d.generated.ServiceDef serviceDef, String inheritedName)
	{
		if(serviceDef.getEventDefs() != null && serviceDef.getEventDefs().getItems() != null)
		{
			for(Object obj:serviceDef.getEventDefs().getItems())
			{
				if(obj instanceof com.u2d.generated.EventDef)
				{
					com.u2d.generated.EventDef eventDef = (com.u2d.generated.EventDef) obj;
					
					String eventName = eventDef.getName().stringValue();
					String transitionName = inheritedName + eventName;
					
					// store transition name for trigger completion
					// we want to abbreviate the full name when showing it in the popup	
					String[] parts = transitionName.split("\\.");
					
					// last is the messageName
					String abbreviation = parts[parts.length-1];
					
					// second to last is the abbreviation if there is any
					if(parts.length > 1)
					{
						abbreviation = parts[parts.length-2] + "." + abbreviation; 
					}
					
					if(inheritedName.compareTo("") == 0)
					{
						// add current service name to transition when we are in top service
						storedTransitions.put(serviceDef.getName().stringValue() + "." + transitionName, transitionName);
					}
					else
					{
						// store abbreviation mapped to its full name
						storedTransitions.put(abbreviation, transitionName);
					}
					
					// store messageName for all message list
					allMessages.add(eventName);
					
					HashHolder temp = new HashHolder();
					
					checkSubJausMessage(temp, eventDef, eventName);
					
					if(temp.size() > 0)
					{
						triggerParameters.put(transitionName, temp);
					}
				}
			}
		}
	}
	
	private void checkSubJausMessage(HashHolder current, Object obj, String messageName)
	{
		if(obj != null)
		{
			if(obj instanceof com.u2d.generated.MessageDef)
			{
				com.u2d.generated.MessageDef msgDef = (com.u2d.generated.MessageDef)obj;

				if(msgDef.getHeader() != null && msgDef.getHeader().getComplexField() != null)
				{
				     // Record | List | Variant | Sequence
				     com.u2d.generated.ComplexField complexField = msgDef.getHeader().getComplexField();
				     
				     // if proxy is being used, get the impl behind the proxy
				     if (complexField instanceof HibernateProxy) 
				 	   complexField = (com.u2d.generated.ComplexField)(
				 	                  (HibernateProxy)complexField).getHibernateLazyInitializer().getImplementation();
				     
				     if(complexField instanceof com.u2d.generated.Record)
				     {
						com.u2d.generated.Record record = (com.u2d.generated.Record)complexField;
						
						current.add(messageName + "." + 
									msgDef.getHeader().getName().stringValue() + "." + 
									record.getName().stringValue());
						checkRecordSubJausMessage(current, record);
				     }
				}
				
				if(msgDef.getBody() != null && msgDef.getBody().getComplexField() != null)
				{
				     // Record | List | Variant | Sequence
				     com.u2d.generated.ComplexField complexField = msgDef.getBody().getComplexField();
				     
				     // if proxy is being used, get the impl behind the proxy
				     if (complexField instanceof HibernateProxy) 
				 	   complexField = (com.u2d.generated.ComplexField)(
				 	                  (HibernateProxy)complexField).getHibernateLazyInitializer().getImplementation();
				     
				     if(complexField instanceof com.u2d.generated.Record)
				     {
						com.u2d.generated.Record record = (com.u2d.generated.Record)complexField;
						
						current.add(messageName +  "." + 
									msgDef.getBody().getName().stringValue() + "." + 
									record.getName().stringValue());
						checkRecordSubJausMessage(current, record);
				     }
				}
				
				if(msgDef.getFooter() != null && msgDef.getFooter().getComplexField() != null)
				{
				     // Record | List | Variant | Sequence
				     com.u2d.generated.ComplexField complexField = msgDef.getFooter().getComplexField();
				     
				     // if proxy is being used, get the impl behind the proxy
				     if (complexField instanceof HibernateProxy) 
				 	   complexField = (com.u2d.generated.ComplexField)(
				 	                  (HibernateProxy)complexField).getHibernateLazyInitializer().getImplementation();
				     
				     if(complexField instanceof com.u2d.generated.Record)
				     {
						com.u2d.generated.Record record = (com.u2d.generated.Record)complexField;
						
						current.add(messageName + "." + 
									msgDef.getFooter().getName().stringValue() + "." + 
									record.getName().stringValue());
						checkRecordSubJausMessage(current, record);
				     }
				}
			}
			else if(obj instanceof com.u2d.generated.EventDef)
			{
				com.u2d.generated.EventDef eventDef = (com.u2d.generated.EventDef)obj;
				
				if(eventDef.getHeader() != null && eventDef.getHeader().getComplexField() != null)
				{
				     // Record | List | Variant | Sequence
				     com.u2d.generated.ComplexField complexField = eventDef.getHeader().getComplexField();
				     
				     // if proxy is being used, get the impl behind the proxy
				     if (complexField instanceof HibernateProxy) 
				 	   complexField = (com.u2d.generated.ComplexField)(
				 	                  (HibernateProxy)complexField).getHibernateLazyInitializer().getImplementation();
				     
				     if(complexField instanceof com.u2d.generated.Record)
				     {
						com.u2d.generated.Record record = (com.u2d.generated.Record)complexField;
						
						current.add(messageName + "." + 
									eventDef.getHeader().getName().stringValue() + "." + 
									record.getName().stringValue());
						checkRecordSubJausMessage(current, record);
				     }
				}
				
				if(eventDef.getBody() != null && eventDef.getBody().getComplexField() != null)
				{
				     // Record | List | Variant | Sequence
				     com.u2d.generated.ComplexField complexField = eventDef.getBody().getComplexField();
				     
				     // if proxy is being used, get the impl behind the proxy
				     if (complexField instanceof HibernateProxy) 
				 	   complexField = (com.u2d.generated.ComplexField)(
				 	                  (HibernateProxy)complexField).getHibernateLazyInitializer().getImplementation();
				     
				     if(complexField instanceof com.u2d.generated.Record)
				     {
						com.u2d.generated.Record record = (com.u2d.generated.Record)complexField;
						
						current.add(messageName + "." + 
									eventDef.getBody().getName().stringValue() + "." + 
									record.getName().stringValue());
						checkRecordSubJausMessage(current, record);
				     }
				}
				
				if(eventDef.getFooter() != null && eventDef.getFooter().getComplexField() != null)
				{
				     // Record | List | Variant | Sequence
				     com.u2d.generated.ComplexField complexField = eventDef.getFooter().getComplexField();
				     
				     // if proxy is being used, get the impl behind the proxy
				     if (complexField instanceof HibernateProxy) 
				 	   complexField = (com.u2d.generated.ComplexField)(
				 	                  (HibernateProxy)complexField).getHibernateLazyInitializer().getImplementation();
				     
				     if(complexField instanceof com.u2d.generated.Record)
				     {
						com.u2d.generated.Record record = (com.u2d.generated.Record)complexField;
						
						current.add(messageName + "." + 
									eventDef.getFooter().getName().stringValue() + "." + 
									record.getName().stringValue());
						checkRecordSubJausMessage(current, record);
				     }
				}
			}
		}
	}
	
	private void checkRecordSubJausMessage(HashHolder current, com.u2d.generated.Record record)
	{
		if(record != null && record.getSimpleFields() != null && record.getSimpleFields().getItems() != null)
		{
			for(Object obj:record.getSimpleFields().getItems())
			{
				if (obj instanceof HibernateProxy) 
				{
					obj = (com.u2d.generated.SimpleField)( (HibernateProxy)obj ).getHibernateLazyInitializer().getImplementation();
				}
				
				if(obj instanceof com.u2d.generated.VariableLengthField)
				{
					com.u2d.generated.VariableLengthField field = (com.u2d.generated.VariableLengthField) obj;
					
					if(field.getFieldFormat().toString().compareTo("JAUS MESSAGE") == 0)
					{
						current.setNeedsAllMessages(true);
					}
				}
			}
		}
	}
	
	/**
	 * gets the parameters for the transition of the trigger that is currently being edited
	 * @return
	 */
	public ArrayList< String > getCurrentTransitionParameterValues( TransitionNode node )
	{
		return null;
	}
	
	/**
	 * Gets a list of strings corresponding to the parameter types which are stored for 
	 * the input trigger.  
	 * @param userObject - the current trigger node 
	 * @return
	 */
	public ArrayList< String > getCurrentTransitionParameterTypes( TransitionNode node )
	{
		ArrayList< String > parms = new ArrayList< String >();
		
		if( node == null )
		{
			return null;
		}

		String transitionName = node.toString();
		
		HashHolder stored = triggerParameters.get( transitionName );
		
		if( stored == null )
		{
			return null;
		}
		
		parms.addAll( stored.getValues() );
		
		// add all messages when it is a JAUS message
		if( stored.getNeedsAllMessages() )
		{
			parms.addAll( allMessages );
		}

		return parms;
	}
	
	public ArrayList< String > getCurrentArguments( TransitionNode node )
	{
		if( node == null )
		{
			return null;
		}

		String transitionName = node.toString();
		
		return node.getParameterValueStrings();
	}

	public HashMap<String, String> getMessages()
	{
		return storedTransitions;
	}
	
	public ArrayList< String > getActions()
	{
		return storedActions;
	}
	
	public ArrayList< String > getGuards()
	{
		return storedGuards;
	}

	
	
	// we will use a new class to hold the hash definitions so that we can add all the
	// message defs when the subelement of a record is a jaus message so we don't have to 
	// query and loop through every service definition in the inheritance chain twice
	// (we will mark whether the hash needs all of the message for the key)
	private class HashHolder
	{
		private boolean needsAllMessages;
		private ArrayList<String> values;
		
		HashHolder()
		{
			values = new ArrayList<String>();
			needsAllMessages = false;
		}
		
		public void add(String str)
		{
			values.add(str);
		}
		
		public void setNeedsAllMessages(boolean value)
		{
			needsAllMessages = value;
		}
		
		public boolean getNeedsAllMessages()
		{
			return needsAllMessages;
		}
		
		public int size()
		{
			return values.size();
		}
		
		public ArrayList<String> getValues()
		{
			return values;
		}
	}

}
