package com.mxgraph.view;

import java.util.Collection;
import java.util.Iterator;

import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.util.mxUtils;

public class mxMultiplicity
{

	/**
	 * Defines the type of the source or target terminal. The type is a string
	 * passed to mxCell.is to check if the rule applies to a cell.
	 */
	protected String type;

	/**
	 * Optional string that specifies the attributename to be passed to
	 * mxCell.is to check if the rule applies to a cell.
	 */
	protected String attr;

	/**
	 * Optional string that specifies the value of the attribute to be passed
	 * to mxCell.is to check if the rule applies to a cell.
	 */
	protected String value;

	/**
	 * Boolean that specifies if the rule is applied to the source or target
	 * terminal of an edge.
	 */
	protected boolean source;

	/**
	 * Defines the minimum number of connections for which this rule applies.
	 * Default is 0.
	 */
	protected int min = 0;

	/**
	 * Defines the maximum number of connections for which this rule applies.
	 * A value of 'n' means unlimited times. Default is 'n'. 
	 */
	protected String max = "n";

	/**
	 * Holds an array of strings that specify the type of neighbor for which
	 * this rule applies. The strings are used in mxCell.is on the opposite
	 * terminal to check if the rule applies to the connection.
	 */
	protected Collection validNeighbors;

	/**
	 * Boolean indicating if the list of validNeighbors are those that are allowed
	 * for this rule or those that are not allowed for this rule.
	 */
	protected boolean validNeighborsAllowed = true;

	/**
	 * Holds the localized error message to be displayed if the number of
	 * connections for which the rule applies is smaller than min or greater
	 * than max.
	 */
	protected String countError;

	/**
	 * Holds the localized error message to be displayed if the type of the
	 * neighbor for a connection does not match the rule.
	 */
	protected String typeError;

	/**
	 * 
	 */
	public mxMultiplicity(boolean source, String type, String attr,
			String value, int min, String max, Collection validNeighbors,
			String countError, String typeError, boolean validNeighborsAllowed)
	{
		this.source = source;
		this.type = type;
		this.attr = attr;
		this.value = value;
		this.min = min;
		this.max = max;
		this.validNeighbors = validNeighbors;
		this.countError = countError;
		this.typeError = typeError;
		this.validNeighborsAllowed = validNeighborsAllowed;
	}

	/**
	 * Function: check
	 * 
	 * Checks the multiplicity for the given arguments and returns the error
	 * for the given connection or null if the multiplicity does not apply.
	 *  
	 * Parameters:
	 * 
	 * graph - Reference to the enclosing graph instance.
	 * edge - Cell that represents the edge to validate.
	 * source - Cell that represents the source terminal.
	 * target - Cell that represents the target terminal.
	 * sourceOut - Number of outgoing edges from the source terminal.
	 * targetIn - Number of incoming edges for the target terminal.
	 */
	public String check(mxGraph graph, Object edge, Object source,
			Object target, int sourceOut, int targetIn)
	{
		StringBuffer error = new StringBuffer();
		mxIGraphModel model = graph.getModel();
		Object sourceValue = model.getValue(source);
		Object targetValue = model.getValue(target);

		if ((sourceValue != null && targetValue != null)
				&& ((this.source && mxUtils.isNode(sourceValue, type, attr,
						value)) || (!this.source && mxUtils.isNode(targetValue,
						type, attr, value))))
		{
			if (!isUnlimited())
			{
				int m = getMaxValue();
				if (m == 0 || (this.source && sourceOut >= m)
						|| (!this.source && targetIn >= m))
				{
					error.append(countError + "\n");
				}
			}

			boolean isValid = !validNeighborsAllowed;
			Iterator it = validNeighbors.iterator();

			while (it.hasNext())
			{
				Object tmp = it.next();

				if (this.source
						&& mxUtils.isNode(targetValue, String.valueOf(tmp)))
				{
					isValid = this.validNeighborsAllowed;
					break;
				}
				else if (!this.source
						&& mxUtils.isNode(sourceValue, String.valueOf(tmp)))
				{
					isValid = this.validNeighborsAllowed;
					break;
				}

				if (!isValid)
				{
					error.append(typeError + "\n");
				}
			}
		}

		return (error.length() > 0) ? error.toString() : null;
	}

	/**
	 * Returns true if max is "n" (unlimited).
	 */
	public boolean isUnlimited()
	{
		return max == null || max == "n";
	}

	/**
	 * Returns the numeric value of max.
	 */
	public int getMaxValue()
	{
		try
		{
			return Integer.parseInt(this.max);
		}
		catch (NumberFormatException e)
		{
			// ignore
		}

		return 0;
	}

}
