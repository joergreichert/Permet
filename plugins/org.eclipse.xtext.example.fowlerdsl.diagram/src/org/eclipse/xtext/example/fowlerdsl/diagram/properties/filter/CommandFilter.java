package org.eclipse.xtext.example.fowlerdsl.diagram.properties.filter;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.example.fowlerdsl.diagram.properties.FowlerDslElementFilter;
import org.eclipse.xtext.example.fowlerdsl.statemachine.Command;

public class CommandFilter extends FowlerDslElementFilter {

	@Override
	protected boolean accept(EObject eObject) {
		return eObject instanceof Command;
	}
}
