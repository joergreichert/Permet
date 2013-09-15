package org.eclipse.xtext.example.fowlerdsl.diagram.properties;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.ui.utils.EEFLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.xtext.example.fowlerdsl.statemachine.Command;
import org.eclipse.xtext.example.fowlerdsl.statemachine.Event;
import org.eclipse.xtext.example.fowlerdsl.statemachine.State;
import org.eclipse.xtext.example.fowlerdsl.statemachine.Statemachine;
import org.eclipse.xtext.example.fowlerdsl.statemachine.Transition;

public class FowlerDslLabelProvider extends EEFLabelProvider {

	public String getText(Object element) {
		Object semanticObject = unwrap(element);
		String text = null;
//		if (semanticObject instanceof Command) {
//			text = getCommandText((Command) semanticObject);
//		} else if (semanticObject instanceof Event) {
//			text = getEventText((Event) semanticObject);
//		} else if (semanticObject instanceof Statemachine) {
//			text = getStatemachineText((Statemachine) semanticObject);
//		} else if (semanticObject instanceof State) {
//			text = getStateText((State) semanticObject);
//		} else if (semanticObject instanceof Transition) {
//			text = getTransitionText((Transition) semanticObject);
//		} else {
			text = super.getText(semanticObject);
//		}
		return text;
	}

	private String getStatemachineText(Statemachine semanticObject) {
		return "Statemachine: "
				+ semanticObject.eResource().getURI().trimFileExtension()
						.lastSegment();
	}

	private String getCommandText(Command semanticObject) {
		return "Command: " + semanticObject.getName() + " ("
				+ semanticObject.getCode() + ")";
	}

	private String getStateText(State semanticObject) {
		return "State: " + semanticObject.getName();
	}

	private String getEventText(Event semanticObject) {
		return "Event: " + semanticObject.getName();
	}

	private String getTransitionText(Transition semanticObject) {
		return "Transition: "
				+ getStateText((State) semanticObject.eContainer()) + " -- "
				+ getEventText(semanticObject.getEvent()) + " --> "
				+ getStateText(semanticObject.getState());
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
