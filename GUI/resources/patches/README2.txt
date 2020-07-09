To re-build JMatter (and its module libraries)...


1. Change to the JTS patches folder: cd <JTS>/GUI/resources/patches

2. Check-out the GitHub repo to a specific folder name: "git clone https://github.com/eitansuez/jmatter-complet.git jmatter-20090430"

3. Switch/check-out a new branch based on the ebc5911bbb32669548f50e01a2c891e9758d5a59 commit (last commit dated 30-Apr-2009)

4. As a sanity check, verify no differences reported: "diff AppFrame.java.original jmatter-20090430/modules/swingvm/src/com/u2d/view/swing/AppFrame.java"

5. Apply the JTS specific patches: "source patchSource"

6. As a sanity check, verify patch applied: "diff AppFrame.java.modified jmatter-20090430/modules/swingvm/src/com/u2d/view/swing/AppFrame.java"

7. Install jmatter build dependencies: "sudo apt-get install lyx markdown"

8. Build jmatter
    a. Change to the <JTS>/GUI/resources/patches/jmatter-20090430 directory
    b. Execute: "ant make-distribution"

9. Install the updated libraries to JTS
    a. Change to the <JTS>/GUI/resources/patches directory
    b. Execute: "source jarCopy"


