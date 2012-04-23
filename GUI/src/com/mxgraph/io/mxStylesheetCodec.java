/**
 * $Id: mxStylesheetCodec.java,v 1.3 2009/01/27 12:32:43 gaudenz Exp $
 * Copyright (c) 2006, Gaudenz Alder
 */
package com.mxgraph.io;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.mxgraph.model.mxGraphModel;
import com.mxgraph.model.mxICell;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxStylesheet;

/**
 * Codec for mxStylesheets. This class is created and registered
 * dynamically at load time and used implicitely via mxCodec
 * and the mxCodecRegistry.
 */
public class mxStylesheetCodec extends mxObjectCodec
{

	/**
	 * Constructs a new model codec.
	 */
	public mxStylesheetCodec()
	{
		this(new mxStylesheet());
	}

	/**
	 * Constructs a new stylesheet codec for the given template.
	 */
	public mxStylesheetCodec(Object template)
	{
		this(template, null, null, null);
	}

	/**
	 * Constructs a new model codec for the given arguments.
	 */
	public mxStylesheetCodec(Object template, String[] exclude,
			String[] idrefs, Map mapping)
	{
		super(template, exclude, idrefs, mapping);
	}

	/**
	 * Encodes the given mxStylesheet.
	 */
	public Node encode(mxCodec enc, Object obj)
	{
		String name = mxCodecRegistry.getName(obj.getClass());
		Element node = enc.document.createElement(name);
		
		if (obj instanceof mxStylesheet)
		{
			mxStylesheet stylesheet = (mxStylesheet) obj;
			Iterator it = stylesheet.getStyles().entrySet().iterator();

			while (it.hasNext())
			{
				Map.Entry entry = (Map.Entry) it.next();

				Element styleNode = enc.document.createElement("add");
				String stylename = String.valueOf(entry.getKey());
				styleNode.setAttribute("as", stylename);

				Hashtable style = (Hashtable) entry.getValue();
				Iterator it2 = style.entrySet().iterator();

				while (it2.hasNext())
				{
					Map.Entry entry2 = (Map.Entry) it2.next();
					Element entryNode = enc.document.createElement("add");
					entryNode.setAttribute("as", String
							.valueOf(entry2.getKey()));
					entryNode.setAttribute("value", String.valueOf(entry2
							.getValue()));
					styleNode.appendChild(entryNode);
				}

				if (styleNode.getChildNodes().getLength() > 0)
				{
					node.appendChild(styleNode);
				}
			}
		}
		
		return node;
	}

	/**
	 * Decodes the given mxStylesheet.
	 */
	public Object decode(mxCodec dec, Node node, Object into)
	{
		Object obj = null;

		if (node instanceof Element)
		{
			String id = ((Element) node).getAttribute("id");
			obj = dec.objects.get(id);

			if (obj == null)
			{
				obj = into;

				if (obj == null)
				{
					obj = cloneTemplate(node);
				}

				if (id != null && id.length() > 0)
				{
					dec.putObject(id, obj);
				}
			}

			node = node.getFirstChild();

			while (node != null)
			{
				if (!processInclude(dec, node, obj) &&
					node.getNodeName().equals("add") &&
					node instanceof Element)
				{
					String as = ((Element) node).getAttribute("as");

					if (as != null && as.length() > 0)
					{
						String extend = ((Element) node).getAttribute("extend");
						Hashtable style = (Hashtable) ((extend != null) ? ((mxStylesheet) obj)
								.getStyles().get(extend)
								: null);
						
						if (style == null)
						{
							style = new Hashtable();
						}
						else
						{
							style = new Hashtable(style);
						}
						
						Node entry = node.getFirstChild();
						
						while (entry != null)
						{
							if (entry instanceof Element)
							{
								Element entryElement = (Element) entry;
								String key = entryElement.getAttribute("as");
								
								if (entry.getNodeName().equals("add"))
								{
									String text = entry.getTextContent();
									Object value = null;
									
									if (text != null && text.length() > 0)
									{
										value = mxUtils.eval(text);
									}
									else
									{
										value = entryElement.getAttribute("value");
										
									}
									
									if (value != null)
									{
										style.put(key, value);
									}
								}
								else if (entry.getNodeName().equals("remove"))
								{
									style.remove(key);
								}
							}
							
							entry = entry.getNextSibling();							
						}
						
						((mxStylesheet) obj).putCellStyle(as, style);						
					}
				}
				
				node = node.getNextSibling();
			}
		}

		return obj;
	}

}
