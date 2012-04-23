package org.jts.eclipse.ui.syntaxhighlighting;

import org.eclipse.xtext.impl.RuleCallImpl;
import org.eclipse.xtext.parsetree.AbstractNode;
import org.eclipse.xtext.parsetree.NodeUtil;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingCalculator;
/**
 * Custom syntax highlighting tool that retrieves the content of a file and parses it
 *  to find the object and apply a custom style to it.
 * @author vnearing
 *
 */

public class CjsidlHighlightingCalculator implements ISemanticHighlightingCalculator {

    /**
     * Parses the given resource to find the INTERPRETATION rule and applies the style defined
     *  in CjsidlHighlightingConfiguration.
     * @param resource
     * @param acceptor
     */
    @Override
    public void provideHighlightingFor(XtextResource resource, IHighlightedPositionAcceptor acceptor) {
        if (resource == null)
            return;
        Iterable<AbstractNode> allNodes = NodeUtil.getAllContents(resource.getParseResult().getRootNode());
        for (AbstractNode node : allNodes) {
            if(node.getGrammarElement() instanceof RuleCallImpl)
            {
                if(((RuleCallImpl) node.getGrammarElement()).getRule().getName().compareTo("INTERPRETATION") == 0)
                {
                    acceptor.addPosition(node.getOffset(), node.getLength(), CjsidlHighlightingConfiguration.INTERPRETATION_ID);
                }
                else if(((RuleCallImpl)node.getGrammarElement()).getRule().getName().compareTo("STRINGLITERAL")== 0)
                {
                	acceptor.addPosition(node.getOffset(), node.getLength(), CjsidlHighlightingConfiguration.STRINGLITERAL_ID);
                }
            }
        }
    }

}
