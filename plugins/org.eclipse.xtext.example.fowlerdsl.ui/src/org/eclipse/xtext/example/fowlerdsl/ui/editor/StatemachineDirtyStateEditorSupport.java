package org.eclipse.xtext.example.fowlerdsl.ui.editor;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.DirtyStateEditorSupport;

public class StatemachineDirtyStateEditorSupport extends
		DirtyStateEditorSupport {

	// TODO start update job that updates Graphiti diagram?
	
	protected Collection<Resource> collectAffectedResources(XtextResource resource, IResourceDescription.Event event) {
		Collection<Resource> result = super.collectAffectedResources(resource, event);
		URI diagramURI = resource.getURI().trimFileExtension().appendFileExtension(".diagram");
		ResourceSet rs = new ResourceSetImpl();
		Resource diagramResource = rs.getResource(diagramURI, false);
		if(diagramResource != null) {
			result.add(diagramResource);
		}
		return result;
	}
}
