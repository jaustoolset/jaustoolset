package org.jts.eclipse.ui.syntaxhighlighting;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfigurationAcceptor;
import org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.utils.TextStyle;
/**
 * A custom syntax highlighting class that sets up the configuration information for a given style.
 * @author vnearing
 *
 */
// some comments

public class CjsidlHighlightingConfiguration implements ISemanticHighlightingConfiguration  {

     public static final String INTERPRETATION_ID = "interpretation";
     public static final String KEYWORD_ID = "keyword";
     public static final String SL_COMMENT_ID = "sl_comment";
     public static final String ML_COMMENT_ID = "ml_comment";     
     public static final String STRINGLITERAL_ID = "stringliteral";
     
     /**
      * Add the custom style function to the acceptor
      * @param acceptor
      */
     public void configure(IHighlightingConfigurationAcceptor acceptor) {
    	 //acceptor.acceptDefaultHighlighting(KEYWORD_ID, "Keyword", keywordTextStyle());
    	 acceptor.acceptDefaultHighlighting(INTERPRETATION_ID, "Interpretation", interpretationTextStyle());
    	 //acceptor.acceptDefaultHighlighting(SL_COMMENT_ID, "sl_comment", commentTextStyle());
    	 //acceptor.acceptDefaultHighlighting(ML_COMMENT_ID, "ml_comment", mlcommentTextStyle());
    	 acceptor.acceptDefaultHighlighting(STRINGLITERAL_ID, "Stringliteral", stringTextStyle());
     }
     
     /**
      * The details of the style - font, color, style, etc.
      * @return the style object.
      */
     public TextStyle interpretationTextStyle() {
         TextStyle textStyle = new TextStyle();
         textStyle.setColor(new RGB(30, 144, 255));
         textStyle.setStyle(SWT.ITALIC);
         return textStyle;
     }
     public TextStyle keywordTextStyle() {
    	    TextStyle textStyle = new TextStyle();
    	    textStyle.setColor(new RGB(127, 0, 85));
    	    textStyle.setStyle(SWT.BOLD);
    	    return textStyle;
    	  }
     public TextStyle commentTextStyle() {
 	    TextStyle textStyle = new TextStyle();
 	    textStyle.setColor(new RGB(63, 127, 95));
 	    return textStyle;
 	  }
     public TextStyle mlcommentTextStyle() {
  	    TextStyle textStyle = new TextStyle();
  	    textStyle.setColor(new RGB(63, 95, 191));
  	    return textStyle;
  	  }
     public TextStyle stringTextStyle() {
 	    TextStyle textStyle = new TextStyle();
 	    textStyle.setColor(new RGB(0, 0, 192));
 	    return textStyle;
 	  }
}
