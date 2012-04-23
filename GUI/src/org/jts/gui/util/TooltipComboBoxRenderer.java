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

package org.jts.gui.util;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 * A ComboBoxRenderer that displays tooltips.  For use with non-editable JComboBox.
 * Use JComboBox.setRenderer to pass JComboBox an instance of this class.  Ensure the array of
 * tooltips passed in at construction has the same length as the model associated with the JComboBox,
 * and that the tooltip at index N corresponds with the model option at index N.  
 * @author idurkan
 */
public class TooltipComboBoxRenderer extends BasicComboBoxRenderer {

    /**
     * The tooltips to display when user hovers over an item in the owner dropdown.
     */
    protected String[] tooltips = null;

    /**
     * Create TooltipComboBoxRenderer with given list of tooltips.
     * @param newTooltips
     */
    public TooltipComboBoxRenderer(String[] newTooltips) {
        tooltips = new String[newTooltips.length];
        System.arraycopy(newTooltips, 0, tooltips, 0, newTooltips.length);
    }

    /**
     * Overrides BasicComboBoxRenderer's getListCellRendererComponent.  Behaves
     * like BasicComboBoxRenderer's method but will show a tooltip when user hovers mouse.
     * @param list
     *    JList of the items in the combo box's model.  Tooltip is set on this
     * @param value
     *    Selected model value if any
     * @param index
     *    Index of the selected list cell or -1 if no selection
     * @param isSelected
     *    True if a list cell is selected
     * @param cellHasFocus
     *    True if the selected cell has focus.
     * @return
     */
    @Override
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());

            // prevent array out-of-bounds
            if (index > -1 && index < tooltips.length) {
                list.setToolTipText(tooltips[index]);
            }
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        setFont(list.getFont());

        if (value != null) {
            setText(value.toString());
        } else {
            setText("");
        }

        return this;
    }
}
