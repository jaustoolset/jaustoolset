/**
 * 
 */
package org.jts.eclipse.conversion.cjsidl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.jts.eclipse.cjsidl.guard;
import org.jts.eclipse.cjsidl.guardAction;
import org.jts.eclipse.cjsidl.guardParam;
import org.jts.eclipse.cjsidl.transParam;
import org.jts.eclipse.cjsidl.transition;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class GuardExpressionParser extends ConversionUtil{//extends CjsidlParser {

	/**
	 * Converts a JSIDL guard to a CJSIDL guard
	 * @param input - the JSIDL guard
	 * @param parentTrans - parent CJSIDL transition for the new guard
	 * @return - resulting CJSIDL guard
	 */
	public guard parse(org.jts.jsidl.binding.Guard input, transition parentTrans) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		guard output = factory.createguard();
		output.setComment(JSIDLInterpToCJSIDLComment(input.getInterpretation()));
		
		EList<guardAction> outputactions = output.getGuardAction();
		EList<String> logicalOperators = output.getLogicalOperator();
		
		// this is a comparative expression
		if(input.getCondition().contains("=")){
			// this should contain two actions exactly in order to compare
			if(input.getCondition().contains("!=")){
				output.setEquiv("!=");
			} else{
				output.setEquiv("=");
			}
			outputactions.addAll(getGuardActions(input.getCondition(), output.getEquiv(), parentTrans));
			
		} else if(input.getCondition().contains("&&") || input.getCondition().contains("||")){
			// this can take any number of actions with logical operations 
			outputactions.addAll(getGuardActions(input.getCondition(), parentTrans));
			logicalOperators.addAll(getLogicalOperators(input.getCondition(), parentTrans));
		} else {
			// there are no logical operations so there should be one action
			outputactions.add(getGuardAction(input.getCondition(), parentTrans));
		}
		
		
		
		return output;
	}
	/**
	 * Converts a string representation of guard actions to CJSIDL guardActions,
	 * given an equivalence within the expression
	 * @param input - String representation of guard actions
	 * @param equiv - empty if no equivalence operator (= or !=), or the operator
	 * @param parentTrans - the CJSIDL parent transition for the parent guard
	 * @return - collection of CJSIDL guardAction
	 */
	private Collection<? extends guardAction> getGuardActions(String input,
		String equiv, transition parentTrans) {
		EList<guardAction> results  = new BasicEList<guardAction>();
		
		int index = input.indexOf(equiv);
		if(index > 0){
			
			results.add(getGuardAction(input.substring(0, index-1), parentTrans));
			input = input.substring(index+2);
			results.add(getGuardAction(input, parentTrans));
			
		} else {
            Logger.getLogger("CJSIDL").log(Level.SEVERE,
                    "Unexpected logical operator\"" + equiv + "\" at position " + index + " for guard string : " + input);
		}
		return results;
	}

	/**
	 * Converts a string representation of guard actions to CJSIDL guardActions
	 * @param input - String representation of guard actions
	 * @param parentTrans - the CJSIDL parent transition for the parent guard
	 * @return - collection of CJSIDL guardAction
	 */
	private Collection<? extends guardAction> getGuardActions(String input, transition parentTrans) {
		EList<guardAction> results  = new BasicEList<guardAction>();
		
		int andindex = input.indexOf("&&");
		int orindex = input.indexOf("||");
		while(andindex >= 0 || orindex >=0){
			if((andindex < orindex || orindex < 0) && andindex >= 0){
				results.add(getGuardAction(input.substring(0, andindex-1), parentTrans));
				input = input.substring(andindex+2);
			} else if((orindex < andindex || andindex < 0) && orindex >=0){
				results.add(getGuardAction(input.substring(0, orindex-1), parentTrans));
				input = input.substring(orindex+2);
			}
			andindex = input.indexOf("&&");
			orindex = input.indexOf("||");
		}
		results.add(getGuardAction(input, parentTrans));
		return results;
	}

	/**
	 * Retrieve a collection of logical operators from a string expression
	 * @param input - expression 
	 * @param parentTrans - parent transition for the guard
	 * @return - resulting collection of logical operators
	 */
	private Collection<? extends String> getLogicalOperators(String input, transition parentTrans) {
		java.util.List<String> results = new ArrayList<String>();
		
		int andindex = input.indexOf("&&");
		int orindex = input.indexOf("||");
		while(andindex >= 0 || orindex >=0){
			if((andindex < orindex || orindex < 0) && andindex >= 0){
				results.add("&&");
				input = input.substring(andindex+1);
			} else if((orindex < andindex || andindex < 0) && orindex >=0){
				results.add("||");
				input = input.substring(orindex+1);
			}
			andindex = input.indexOf("&&");
			orindex = input.indexOf("||");
		}
		
		return results;
	}

	/**
	 * retrieve a CJSIDL guard action for an action expression
	 * @param input - string action expression
	 * @param parentTrans - parent transition for the guard
	 * @return - resulting CJSIDL guard action
	 */
	private guardAction getGuardAction(String input, transition parentTrans) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		guardAction output = factory.createguardAction();
		
		input = input.trim();
		if(input.contains("!")){
			output.setNot("!");
			input = input.replace("!", "");
			input = input.trim();
		}
		int openparenIndex = input.indexOf("(");
		int endparenIndex = input.indexOf(")");
		
		String name = input.substring(0,openparenIndex);		
		output.setName(name);
		
		EList<guardParam> params = output.getParam();
		String paramstr = input.substring(openparenIndex+1, endparenIndex);
		if(paramstr != null && !paramstr.trim().isEmpty()){
			String inputparams[] = paramstr.split("[,]");
			for(int i=0; i< inputparams.length; i++){
				params.add(getGuardParam(inputparams[i], parentTrans));
			}
		}		
		return output;
	}

	/**
	 * Extracts guard parameters from a string of parameters
	 * @param input - parameter string
	 * @param parentTrans - parent transition for the guard
	 * @return - resulting CJSIDL guardParam
	 */
	private guardParam getGuardParam(String input, transition parentTrans) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		guardParam output = factory.createguardParam();
		
		input = input.trim();
		if(input.contains("'")){
			// this is a constant
			output.setGuardConst(input);
		} else {
			output.setParameter((transParam) Conversion.referenceHelper.getEObjectFromTransition(input, parentTrans));
		}
		
		return output;
	}

}
