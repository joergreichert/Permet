package org.eclipse.xtext.example.fowlerdsl.diagram.properties.forms;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.xtext.example.fowlerdsl.diagram.properties.FowlerDslElementFilter;
import org.eclipse.xtext.example.fowlerdsl.diagram.properties.filter.TransitionFilter;
import org.eclipse.xtext.example.fowlerdsl.statemachine.parts.forms.TransitionPropertiesEditionPartForm;

public class CustomTransitionPropertiesEditionPartForm extends
		TransitionPropertiesEditionPartForm {
	private PictogramElement pe = null;
	private FowlerDslElementFilter filter = new TransitionFilter();
	
	@Override
	public EObject getState() {
		// TODO Auto-generated method stub
		return super.getState();
	}
	
	@Override
	public void firePropertiesChanged(IPropertiesEditionEvent event) {
		// Start of user code for tab synchronization
		
		// End of user code
	}
}
