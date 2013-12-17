package org.eclipse.xtext.example.fowlerdsl.resource;

import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionManager;

public class StatemachineResourceDescriptionManager extends
		DefaultResourceDescriptionManager {

	@Override
	protected IResourceDescription internalGetResourceDescription(Resource resource, IDefaultResourceDescriptionStrategy strategy) {
		return new StatemachineResourceDescription(resource, strategy, getCache());
	}
	
	@Override
	protected Collection<QualifiedName> getImportedNames(
			IResourceDescription candidate) {
		// TODO: add Graphiti diagram elements to imported names, so e.g. a
		// label change on state shape affects State EObject?
		return super.getImportedNames(candidate);
	}
	
	@Override
	protected boolean isAffected(Collection<QualifiedName> importedNames,
			IResourceDescription description) {
		// TODO: add Graphiti diagram elements to affected, so e.g. a
		// label change on state shape affects State EObject?
		return super.isAffected(importedNames, description);
	}
}
