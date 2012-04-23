package org.jts.eclipse.formatting;

import java.util.List;
import java.util.Set;

import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.formatting.impl.FormattingConfig;
import org.eclipse.xtext.util.Pair;

public class GenericFormatter {

	/**
	 * In your implementation of
	 * {@link org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter#configureFormatting(org.eclipse.xtext.formatting.impl.FormattingConfig)}
	 * you may call this generic formatting method first. It indents blocks
	 * between curly braces and sets a linewrap before each keyword. Add your
	 * own behavior afterwards, e.g.
	 * 
	 * <pre>
	 *   keywords = grammar.findKeywords(...);
	 *   for (final Keyword keyword : keywords) {
	 *    config.setNoLinewrap().before(keyword);
	 *   }
	 * </pre>
	 */
	public static void genericFormatting(final FormattingConfig config, final IGrammarAccess grammar) {
		for (final Pair<Keyword, Keyword> pair : grammar.findKeywordPairs("{", "}")) { //$NON-NLS-1$ //$NON-NLS-2$
			// a space before the first '{'
			config.setSpace(" ").before(pair.getFirst()); //$NON-NLS-1$
			//config.setLinewrap(1).after(pair.getFirst());			// indentation between
			config.setIndentation(pair.getFirst(), pair.getSecond());
			// and a linewrap before the last '{'
			//config.setLinewrap(1).before(pair.getSecond());
		}

		// linewrap before all keywords
		final Set<String> allKeywords = GrammarUtil.getAllKeywords(grammar.getGrammar());
		final List<Keyword> keywords = grammar.findKeywords(allKeywords.toArray(new String[allKeywords.size()]));
		for (final Keyword keyword : keywords) {
			if(keyword.getValue().equals("}") || keyword.getValue().equals("{") || 
					keyword.getValue().equals(";")){
				config.setLinewrap().after(keyword);
			} else if(keyword.getValue().equals("{") || keyword.getValue().equals("types") ||
					keyword.getValue().equals("field") || keyword.getValue().equals("vfield") ||
					keyword.getValue().equals("messages") || keyword.getValue().equals("record") ||
					keyword.getValue().equals("list") || keyword.getValue().equals("sequence") ||
					keyword.getValue().equals("variable_field") || keyword.getValue().equals("bit_field") ||
					keyword.getValue().equals("string") || keyword.getValue().equals("vstring") ||
					keyword.getValue().equals("variable_format_field") || keyword.getValue().equals("enum") ||
					keyword.getValue().equals("header") || keyword.getValue().equals("body") ||
					keyword.getValue().equals("footer") || keyword.getValue().equals("references") ||
					keyword.getValue().equals("constants") || keyword.getValue().equals("types")){
				config.setLinewrap().before(keyword);
			}
		}
		
	}
}
