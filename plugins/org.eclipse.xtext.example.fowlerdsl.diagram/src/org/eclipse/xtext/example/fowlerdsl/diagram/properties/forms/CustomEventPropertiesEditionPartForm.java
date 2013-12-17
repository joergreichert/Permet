package org.eclipse.xtext.example.fowlerdsl.diagram.properties.forms;

import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.example.fowlerdsl.diagram.properties.FowlerDslPropertiesEditionPartFormUtils;
import org.eclipse.xtext.example.fowlerdsl.diagram.properties.filter.EventFilter;
import org.eclipse.xtext.example.fowlerdsl.statemachine.parts.forms.EventPropertiesEditionPartForm;

public class CustomEventPropertiesEditionPartForm extends
		EventPropertiesEditionPartForm {
	private EventFilter filter = new EventFilter();
	private IWorkbenchPart part = null;
	private PictogramElement pe = null;

	@Override
	public void refresh() {
		if (this.propertiesEditionComponent != null) {
			this.propertiesEditionComponent.dispose();
		}
		part = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().getActivePart();
		if (part != null) {
			ISelection selection = FowlerDslPropertiesEditionPartFormUtils
					.createSelection(part, filter);
			if (selection != null) {
				pe = FowlerDslPropertiesEditionPartFormUtils.getSelectedPictogramElement(selection);
				setInput(part, selection);
				if (this.propertiesEditionComponent != null) {
					Display.getCurrent().asyncExec(new Runnable() {
	                    public void run() {
	                    	CustomEventPropertiesEditionPartForm.super.refresh();
	                    }
					});
				}
			}
		}
	}
	
	@Override
	public void firePropertiesChanged(IPropertiesEditionEvent event) {
		if (part != null && pe != null) {
			FowlerDslPropertiesEditionPartFormUtils.firePropertiesChanged(
					event, pe, part);
		}
	}
}
