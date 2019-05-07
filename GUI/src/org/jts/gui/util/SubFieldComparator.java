package org.jts.gui.util;

import java.util.Comparator;

/**
 * Compares SubFields first by BitRange, then by name. This class is useful
 * for sorting SubFields in a collection.
 * 
 * @author cgagner
 */
public class SubFieldComparator implements Comparator<org.jts.jsidl.binding.SubField> {

    /**
     * Compare the names of two SubFields
     */
    private int compareNames(String name1, String name2) {
        if (name1 == null && name2 == null) {
            return 0;
        } else if (name1 == null) {
            return -1;
        } else if (name2 == null) {
            return 1;
        } else {
            return name1.compareTo(name2);
        }
    }

    /**
     * Compares two SubFields first by BitRane, then by name.
     * @param lhs SubField on the left-hand-side
     * @param rhs SubField on the right-hand-side
     * @return 1 if rhs should be before lhs; -1 if lhs should be before rhs; 
     * 0 otherwise.
     */
    public int compare(org.jts.jsidl.binding.SubField lhs, org.jts.jsidl.binding.SubField rhs) {

        String name1 = lhs.getName();
        String name2 = rhs.getName();
        org.jts.jsidl.binding.BitRange range1 = lhs.getBitRange();
        org.jts.jsidl.binding.BitRange range2 = rhs.getBitRange();
        if (range1 == null || range2 == null) {
            return compareNames(name1, name2);
        } else {
            if (range1.getFromIndex() < range2.getFromIndex()) {
                return -1;
            } else if (range1.getFromIndex() > range2.getFromIndex()) {
                return 1;
            } else {
                if (range1.getToIndex() < range2.getToIndex()) {
                    return -1;
                } else if (range1.getToIndex() > range2.getToIndex()) {
                    return 1;
                } else {
                    return compareNames(name1, name2);
                }
            }
        }
    }
}
