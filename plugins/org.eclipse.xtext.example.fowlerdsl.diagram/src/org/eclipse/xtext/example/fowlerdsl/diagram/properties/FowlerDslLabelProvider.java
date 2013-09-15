package org.eclipse.xtext.example.fowlerdsl.diagram.properties;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.ui.utils.EEFLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;

public class FowlerDslLabelProvider extends EEFLabelProvider {

	public String getText(Object element) {
		String text = null;
		Object semanticObject = unwrap(element);
		if (semanticObject != null) {
			text = super.getText(semanticObject);
		} else {
			text = super.getText(element);
		}
		return text;
	}

	private Object unwrap(Object element) {
		Object resolvedObject = null;
		if (element instanceof IStructuredSelection) {
			resolvedObject = ((IStructuredSelection) element).getFirstElement();
		}
		if (element instanceof IAdaptable) {
			EObject eObject = (EObject) ((IAdaptable) element)
					.getAdapter(EObject.class);
			if (eObject != null) {
				return eObject;
			}
		}
		if (resolvedObject != null) {
			return FowlerDslPropertiesEditionPartFormUtils
					.resolveSemanticObject(resolvedObject,
							new FowlerDslElementFilter());
		}
		return element;
	}
}
