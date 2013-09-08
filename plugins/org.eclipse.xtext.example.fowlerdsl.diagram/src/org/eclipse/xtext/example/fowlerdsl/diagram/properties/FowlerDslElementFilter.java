package org.eclipse.xtext.example.fowlerdsl.diagram.properties;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

public abstract class FowlerDslElementFilter extends AbstractPropertySectionFilter {

	@Override
	protected boolean accept(PictogramElement pe) {
		EObject eObject = Graphiti.getLinkService()
				.getBusinessObjectForLinkedPictogramElement(pe);
		return accept(eObject);
	}
	
	protected abstract boolean accept(EObject eObject);
}
