package org.eclipse.xtext.example.fowlerdsl.resource;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.impl.DefaultResourceDescription;
import org.eclipse.xtext.util.IResourceScopeCache;

public class StatemachineResourceDescription extends DefaultResourceDescription {

	public StatemachineResourceDescription(Resource resource,
			IDefaultResourceDescriptionStrategy strategy,
			IResourceScopeCache iResourceScopeCache) {
		super(resource, strategy, iResourceScopeCache);
	}

	@Override
	public Iterable<QualifiedName> getImportedNames() {
		// TODO: add Graphiti diagram elements to imported names, so e.g. a
		// label change on state shape affects State EObject?
		return super.getImportedNames();
	}
}
