/**
 * $Id: mxCellPath.java,v 1.4 2008/06/09 11:15:28 gaudenz Exp $
 * Copyright (c) 2007, Gaudenz Alder
 */
package com.mxgraph.model;

/**
 * Implements a mechanism for temporary cell Ids.
 */
public class mxCellPath
{

	/**
	 * Defines the separator between the path components. Default is
	 * <code>.</code>.
	 */
	public static String PATH_SEPARATOR = ".";

	/**
	 * Creates the cell path for the given cell. The cell path is a
	 * concatenation of the indices of all cells on the (finite) path to
	 * the root, eg. "0.0.0.1".
	 * 
	 * @param cell Cell whose path should be returned.
	 * @return Returns the string that represents the path.
	 */
	public static String create(mxICell cell)
	{
		String result = "";

		if (cell != null)
		{
			mxICell parent = cell.getParent();

			while (parent != null)
			{
				int index = parent.getIndex(cell);
				result = index + mxCellPath.PATH_SEPARATOR + result;

				cell = parent;
				parent = cell.getParent();
			}
		}

		return (result.length() > 1) ? result.substring(0, result.length() - 1)
				: "";
	}

	/**
	 * Returns the path for the parent of the cell represented by the given
	 * path. Returns null if the given path has no parent.
	 * 
	 * @param path Path whose parent path should be returned.
	 */
	public static String getParentPath(String path)
	{
		if (path != null)
		{
			int index = path.lastIndexOf(mxCellPath.PATH_SEPARATOR);

			if (index >= 0)
			{
				return path.substring(0, index);
			}
			else if (path.length() > 0)
			{
				return "";
			}
		}

		return null;
	}

	/**
	 * Returns the cell for the specified cell path using the given root as the
	 * root of the path.
	 * 
	 * @param root Root cell of the path to be resolved.
	 * @param path String that defines the path.
	 * @return Returns the cell that is defined by the path.
	 */
	public static mxICell resolve(mxICell root, String path)
	{
		mxICell parent = root;
		String[] tokens = path.split(PATH_SEPARATOR);

		for (int i = 0; i < tokens.length; i++)
		{
			parent = parent.getChildAt(Integer.parseInt(tokens[i]));
		}

		return parent;
	}

}
