/*
* generated by Xtext
*/
package org.xtext.example.chess;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class ChessStandaloneSetup extends ChessStandaloneSetupGenerated{

	public static void doSetup() {
		new ChessStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

