/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2011, United States Government
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
package org.jts.deepDelete;

import java.util.HashMap;
import java.util.Set;

/**
 * Set-like class offering a get method so a contained instance may be modified.  This is not possible with 
 * java.util.Set.  Stored mutable instances must implement getHashCode and equals so that their results do not vary as
 * the instance changes.  This allows another instance which gives the same results for getHashCode and equals to be
 * used to retrieve the original instance stored in the MutableSet.
 * @author idurkan
 */
class MutableSet<T> {
    // maps Ts to Ts.  value instances are ignored.
    private HashMap<T, T> backingMap = new HashMap<T, T>();

    /**
     * Indicates whether the MutableSet already contains toCheck.  Behavior undefined if toCheck is null.
     * @param toCheck T instance whose presence in the set is to be tested.
     * @return true if an instance I with toCheck.equals(I) == true has already been added to the set, false otherwise.
     */
    public boolean contains(T toCheck) {
        return backingMap.containsKey(toCheck);
    }

    /**
     * Adds toAdd to the MutableSet, unless toAdd already exists in the set.
     * @param toAdd The instance to add.
     * @return false if an instance I with toCheck.equals(I) == true has already been added to the set, true if
     *      no such instance was already added, allowing the current add to proceed.
     */
    public boolean add(T toAdd) {
        if (contains(toAdd)) {
            return false;
        } else {
            backingMap.put(toAdd, toAdd);
            return true;
        }
    }

    /**
     * Gets the instance stored in the MutableSet equal to toCheck
     * @param toCheck T instance whose equal stored in the MutableSet will be returned, if an equal is stored.
     * @return A T instance equal to toCheck if an equal is stored, or null otherwise.
     */
    public T get(T toCheck) {
        return backingMap.get(toCheck);
    }

    /**
     * Returns the contents of the MutableSet as a java.util.Set.
     * @return
     */
    public Set<T> asSet() {
        return backingMap.keySet();
    }
}
