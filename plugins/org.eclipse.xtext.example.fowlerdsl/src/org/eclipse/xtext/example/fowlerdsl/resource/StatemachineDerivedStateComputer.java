package org.eclipse.xtext.example.fowlerdsl.resource;

import org.eclipse.xtext.resource.DerivedStateAwareResource;
import org.eclipse.xtext.resource.IDerivedStateComputer;

public class StatemachineDerivedStateComputer implements IDerivedStateComputer {

	@Override
	public void installDerivedState(DerivedStateAwareResource resource,
			boolean preLinkingPhase) {
		// TODO register relation to Graphiti diagram?
	}

	@Override
	public void discardDerivedState(DerivedStateAwareResource resource) {
		// TODO discard relation to Graphiti diagram?
	}
}
