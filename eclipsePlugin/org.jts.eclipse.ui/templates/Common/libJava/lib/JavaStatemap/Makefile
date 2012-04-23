# 
# The contents of this file are subject to the Mozilla Public
# License Version 1.1 (the "License"); you may not use this file
# except in compliance with the License. You may obtain a copy
# of the License at http://www.mozilla.org/MPL/
# 
# Software distributed under the License is distributed on an
# "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
# implied. See the License for the specific language governing
# rights and limitations under the License.
# 
# The Original Code is State Machine Compiler (SMC).
# 
# The Initial Developer of the Original Code is Charles W. Rapp.
# Portions created by Charles W. Rapp are
# Copyright (C) 2000 - 2005, 2009. Charles W. Rapp.
# All Rights Reserved.
# 
# Contributor(s):
#
# RCS ID
# $Id: Makefile,v 1.16 2009/03/01 18:20:40 cwrapp Exp $
#
# CHANGE LOG
# (See the bottom of this file.)
#

#################################################################
# Macros.
#

# Include the official macros.
include ../../smc.mk

LIB_SOURCES=    FSMContext.java \
                State.java \
                TransitionUndefinedException.java \
                StateUndefinedException.java

JAVAC=          javac
JAVAC_FLAGS=    -d . -g
JAR=            jar
JAR_FLAGS=      cvf
JAR_FILE=       ./$(PACKAGE_NAME).jar

LIB_STAGING_DIR=../../$(SMC_STAGING_DIR)/lib/Java
CLASS_DIR=      $(LIB_STAGING_DIR)/$(PACKAGE_NAME)

#################################################################
# Rules.
#

$(JAR_FILE) :   $(LIB_SOURCES)
		$(JAVAC) $(JAVAC_FLAGS) $(LIB_SOURCES)
		$(JAR) $(JAR_FLAGS) $(JAR_FILE) $(PACKAGE_NAME)

$(LIB_STAGING_DIR) :
		$(MKDIR) $@

$(CLASS_DIR) :  $(LIB_STAGING_DIR)
		$(MKDIR) $@

install :       $(JAR_FILE) $(CLASS_DIR)
		$(CP_F) $(JAR_FILE) ..
		$(CP_F) $(JAR_FILE) $(LIB_STAGING_DIR)
		-$(CHMOD) 444 $(LIB_STAGING_DIR)/$(JAR_FILE)
		$(CP_F) $(PACKAGE_NAME)/*.class $(CLASS_DIR)

uninstall :
		-$(RM_F) ../$(JAR_FILE)
		-$(RM_F) $(LIB_STAGING_DIR)/$(JAR_FILE)

clean :
		-$(RM_F) $(JAR_FILE) $(PACKAGE_NAME)/*

realclean :     clean
		-$(RM_RF) $(PACKAGE_NAME)

#
# CHANGE LOG
# $Log: Makefile,v $
# Revision 1.16  2009/03/01 18:20:40  cwrapp
# Preliminary v. 6.0.0 commit.
#
# Revision 1.15  2008/05/20 18:31:12  cwrapp
# ----------------------------------------------------------------------
#
# Committing release 5.1.0.
#
# Modified Files:
# 	Makefile README.txt smc.mk tar_list.txt bin/Smc.jar
# 	examples/Ant/EX1/build.xml examples/Ant/EX2/build.xml
# 	examples/Ant/EX3/build.xml examples/Ant/EX4/build.xml
# 	examples/Ant/EX5/build.xml examples/Ant/EX6/build.xml
# 	examples/Ant/EX7/build.xml examples/Ant/EX7/src/Telephone.java
# 	examples/Java/EX1/Makefile examples/Java/EX4/Makefile
# 	examples/Java/EX5/Makefile examples/Java/EX6/Makefile
# 	examples/Java/EX7/Makefile examples/Ruby/EX1/Makefile
# 	lib/statemap.jar lib/C++/statemap.h lib/Java/Makefile
# 	lib/Php/statemap.php lib/Scala/Makefile
# 	lib/Scala/statemap.scala net/sf/smc/CODE_README.txt
# 	net/sf/smc/README.txt net/sf/smc/Smc.java
# ----------------------------------------------------------------------
#
# Revision 1.14  2008/02/04 10:45:22  fperrad
# + use common variables
#
# Revision 1.13  2007/10/11 12:48:35  fperrad
# - refactor: Makefile with variables
#
# Revision 1.12  2007/10/10 12:29:06  fperrad
# - fix Makefile (mkdir)
#
# Revision 1.11  2005/06/18 18:28:42  cwrapp
# SMC v. 4.0.1
#
# New Features:
#
# (No new features.)
#
# Bug Fixes:
#
# + (C++) When the .sm is in a subdirectory the forward- or
#   backslashes in the file name are kept in the "#ifndef" in the
#   generated header file. This is syntactically wrong. SMC now
#   replaces the slashes with underscores.
#
# + (Java) If %package is specified in the .sm file, then the
#   generated *Context.java class will have package-level access.
#
# + The Programmer's Manual had incorrect HTML which prevented the
#   pages from rendering correctly on Internet Explorer.
#
# + Rewrote the Programmer's Manual section 1 to make it more
#   useful.
#
# Revision 1.10  2005/06/16 18:26:35  fperrad
# Sorry, reverse to revision 1.8
#
# Revision 1.8  2005/06/08 11:09:14  cwrapp
# + Updated Python code generator to place "pass" in methods with empty
#   bodies.
# + Corrected FSM errors in Python example 7.
# + Removed unnecessary includes from C++ examples.
# + Corrected errors in top-level makefile's distribution build.
#
# Revision 1.7  2005/05/28 18:44:13  cwrapp
# Updated C++, Java and Tcl libraries, added CSharp, Python and VB.
#
# Revision 1.3  2005/02/21 19:04:40  charlesr
# Make installed jar file read-only.
#
# Revision 1.2  2005/02/21 19:02:50  charlesr
# Including standard SMC makefile macros.
#
# Revision 1.1  2004/10/30 15:55:25  charlesr
# Added -f option to install copy.
#
# Revision 1.0  2003/12/14 20:38:57  charlesr
# Initial revision
#
