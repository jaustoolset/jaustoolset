
package org.jts.eclipse;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class CjsidlStandaloneSetup extends CjsidlStandaloneSetupGenerated{

	public static void doSetup() {
		new CjsidlStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

