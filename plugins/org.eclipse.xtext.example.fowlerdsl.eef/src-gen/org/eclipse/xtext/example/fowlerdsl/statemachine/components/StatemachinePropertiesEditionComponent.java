/**
 * Generated with Acceleo
 */
package org.eclipse.xtext.example.fowlerdsl.statemachine.components;

// Start of user code for imports
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.WrappedException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.resource.ResourceSet;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.emf.eef.runtime.api.notify.EStructuralFeatureNotificationFilter;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.notify.NotificationFilter;

import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;

import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.context.impl.EReferencePropertiesEditionContext;

import org.eclipse.emf.eef.runtime.impl.components.SinglePartPropertiesEditingComponent;

import org.eclipse.emf.eef.runtime.impl.filters.EObjectFilter;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;

import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;

import org.eclipse.emf.eef.runtime.policies.impl.CreateEditingPolicy;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.xtext.example.fowlerdsl.statemachine.Command;
import org.eclipse.xtext.example.fowlerdsl.statemachine.Event;
import org.eclipse.xtext.example.fowlerdsl.statemachine.State;
import org.eclipse.xtext.example.fowlerdsl.statemachine.Statemachine;
import org.eclipse.xtext.example.fowlerdsl.statemachine.StatemachinePackage;

import org.eclipse.xtext.example.fowlerdsl.statemachine.parts.StatemachinePropertiesEditionPart;
import org.eclipse.xtext.example.fowlerdsl.statemachine.parts.StatemachineViewsRepository;


// End of user code

/**
 * 
 * 
 */
public class StatemachinePropertiesEditionComponent extends SinglePartPropertiesEditingComponent {

	
	public static String BASE_PART = "Base"; //$NON-NLS-1$

	
	/**
	 * Settings for events ReferencesTable
	 */
	protected ReferencesTableSettings eventsSettings;
	
	/**
	 * Settings for resetEvents ReferencesTable
	 */
	private ReferencesTableSettings resetEventsSettings;
	
	/**
	 * Settings for commands ReferencesTable
	 */
	protected ReferencesTableSettings commandsSettings;
	
	/**
	 * Settings for states ReferencesTable
	 */
	protected ReferencesTableSettings statesSettings;
	
	
	/**
	 * Default constructor
	 * 
	 */
	public StatemachinePropertiesEditionComponent(PropertiesEditingContext editingContext, EObject statemachine, String editing_mode) {
		super(editingContext, statemachine, editing_mode);
		parts = new String[] { BASE_PART };
		repositoryKey = StatemachineViewsRepository.class;
		partKey = StatemachineViewsRepository.Statemachine_.class;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#initPart(java.lang.Object, int, org.eclipse.emf.ecore.EObject, 
	 *      org.eclipse.emf.ecore.resource.ResourceSet)
	 * 
	 */
	public void initPart(Object key, int kind, EObject elt, ResourceSet allResource) {
		setInitializing(true);
		if (editingPart != null && key == partKey) {
			editingPart.setContext(elt, allResource);
			
			final Statemachine statemachine = (Statemachine)elt;
			final StatemachinePropertiesEditionPart basePart = (StatemachinePropertiesEditionPart)editingPart;
			// init values
			if (isAccessible(StatemachineViewsRepository.Statemachine_.Properties.events)) {
				eventsSettings = new ReferencesTableSettings(statemachine, StatemachinePackage.eINSTANCE.getStatemachine_Events());
				basePart.initEvents(eventsSettings);
			}
			if (isAccessible(StatemachineViewsRepository.Statemachine_.Properties.resetEvents)) {
				resetEventsSettings = new ReferencesTableSettings(statemachine, StatemachinePackage.eINSTANCE.getStatemachine_ResetEvents());
				basePart.initResetEvents(resetEventsSettings);
			}
			if (isAccessible(StatemachineViewsRepository.Statemachine_.Properties.commands)) {
				commandsSettings = new ReferencesTableSettings(statemachine, StatemachinePackage.eINSTANCE.getStatemachine_Commands());
				basePart.initCommands(commandsSettings);
			}
			if (isAccessible(StatemachineViewsRepository.Statemachine_.Properties.states)) {
				statesSettings = new ReferencesTableSettings(statemachine, StatemachinePackage.eINSTANCE.getStatemachine_States());
				basePart.initStates(statesSettings);
			}
			// init filters
			if (isAccessible(StatemachineViewsRepository.Statemachine_.Properties.events)) {
				basePart.addFilterToEvents(new ViewerFilter() {
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof String && element.equals("")) || (element instanceof Event); //$NON-NLS-1$ 
					}
			
				});
				// Start of user code for additional businessfilters for events
				// End of user code
			}
			if (isAccessible(StatemachineViewsRepository.Statemachine_.Properties.resetEvents)) {
				basePart.addFilterToResetEvents(new EObjectFilter(StatemachinePackage.Literals.EVENT));
				// Start of user code for additional businessfilters for resetEvents
				// End of user code
			}
			if (isAccessible(StatemachineViewsRepository.Statemachine_.Properties.commands)) {
				basePart.addFilterToCommands(new ViewerFilter() {
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof String && element.equals("")) || (element instanceof Command); //$NON-NLS-1$ 
					}
			
				});
				// Start of user code for additional businessfilters for commands
				// End of user code
			}
			if (isAccessible(StatemachineViewsRepository.Statemachine_.Properties.states)) {
				basePart.addFilterToStates(new ViewerFilter() {
					/**
					 * {@inheritDoc}
					 * 
					 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
					 */
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						return (element instanceof String && element.equals("")) || (element instanceof State); //$NON-NLS-1$ 
					}
			
				});
				// Start of user code for additional businessfilters for states
				// End of user code
			}
			// init values for referenced views
			
			// init filters for referenced views
			
		}
		setInitializing(false);
	}







	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#associatedFeature(java.lang.Object)
	 */
	public EStructuralFeature associatedFeature(Object editorKey) {
		if (editorKey == StatemachineViewsRepository.Statemachine_.Properties.events) {
			return StatemachinePackage.eINSTANCE.getStatemachine_Events();
		}
		if (editorKey == StatemachineViewsRepository.Statemachine_.Properties.resetEvents) {
			return StatemachinePackage.eINSTANCE.getStatemachine_ResetEvents();
		}
		if (editorKey == StatemachineViewsRepository.Statemachine_.Properties.commands) {
			return StatemachinePackage.eINSTANCE.getStatemachine_Commands();
		}
		if (editorKey == StatemachineViewsRepository.Statemachine_.Properties.states) {
			return StatemachinePackage.eINSTANCE.getStatemachine_States();
		}
		return super.associatedFeature(editorKey);
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void updateSemanticModel(final IPropertiesEditionEvent event) {
		Statemachine statemachine = (Statemachine)semanticObject;
		if (StatemachineViewsRepository.Statemachine_.Properties.events == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, eventsSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			} else if (event.getKind() == PropertiesEditionEvent.EDIT) {
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, (EObject) event.getNewValue(), editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt((EObject) event.getNewValue(), PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy editionPolicy = provider.getPolicy(context);
					if (editionPolicy != null) {
						editionPolicy.execute();
					}
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				eventsSettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				eventsSettings.move(event.getNewIndex(), (Event) event.getNewValue());
			}
		}
		if (StatemachineViewsRepository.Statemachine_.Properties.resetEvents == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				if (event.getNewValue() instanceof Event) {
					resetEventsSettings.addToReference((EObject) event.getNewValue());
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				resetEventsSettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				resetEventsSettings.move(event.getNewIndex(), (Event) event.getNewValue());
			}
		}
		if (StatemachineViewsRepository.Statemachine_.Properties.commands == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, commandsSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			} else if (event.getKind() == PropertiesEditionEvent.EDIT) {
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, (EObject) event.getNewValue(), editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt((EObject) event.getNewValue(), PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy editionPolicy = provider.getPolicy(context);
					if (editionPolicy != null) {
						editionPolicy.execute();
					}
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				commandsSettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				commandsSettings.move(event.getNewIndex(), (Command) event.getNewValue());
			}
		}
		if (StatemachineViewsRepository.Statemachine_.Properties.states == event.getAffectedEditor()) {
			if (event.getKind() == PropertiesEditionEvent.ADD) {
				EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, statesSettings, editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy policy = provider.getPolicy(context);
					if (policy instanceof CreateEditingPolicy) {
						policy.execute();
					}
				}
			} else if (event.getKind() == PropertiesEditionEvent.EDIT) {
				EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, (EObject) event.getNewValue(), editingContext.getAdapterFactory());
				PropertiesEditingProvider provider = (PropertiesEditingProvider)editingContext.getAdapterFactory().adapt((EObject) event.getNewValue(), PropertiesEditingProvider.class);
				if (provider != null) {
					PropertiesEditingPolicy editionPolicy = provider.getPolicy(context);
					if (editionPolicy != null) {
						editionPolicy.execute();
					}
				}
			} else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
				statesSettings.removeFromReference((EObject) event.getNewValue());
			} else if (event.getKind() == PropertiesEditionEvent.MOVE) {
				statesSettings.move(event.getNewIndex(), (State) event.getNewValue());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
	 */
	public void updatePart(Notification msg) {
		super.updatePart(msg);
		if (editingPart.isVisible()) {
			StatemachinePropertiesEditionPart basePart = (StatemachinePropertiesEditionPart)editingPart;
			if (StatemachinePackage.eINSTANCE.getStatemachine_Events().equals(msg.getFeature()) && isAccessible(StatemachineViewsRepository.Statemachine_.Properties.events))
				basePart.updateEvents();
			if (StatemachinePackage.eINSTANCE.getStatemachine_ResetEvents().equals(msg.getFeature())  && isAccessible(StatemachineViewsRepository.Statemachine_.Properties.resetEvents))
				basePart.updateResetEvents();
			if (StatemachinePackage.eINSTANCE.getStatemachine_Commands().equals(msg.getFeature()) && isAccessible(StatemachineViewsRepository.Statemachine_.Properties.commands))
				basePart.updateCommands();
			if (StatemachinePackage.eINSTANCE.getStatemachine_States().equals(msg.getFeature()) && isAccessible(StatemachineViewsRepository.Statemachine_.Properties.states))
				basePart.updateStates();
			
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#getNotificationFilters()
	 */
	@Override
	protected NotificationFilter[] getNotificationFilters() {
		NotificationFilter filter = new EStructuralFeatureNotificationFilter(
			StatemachinePackage.eINSTANCE.getStatemachine_Events(),
			StatemachinePackage.eINSTANCE.getStatemachine_ResetEvents(),
			StatemachinePackage.eINSTANCE.getStatemachine_Commands(),
			StatemachinePackage.eINSTANCE.getStatemachine_States()		);
		return new NotificationFilter[] {filter,};
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#validateValue(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public Diagnostic validateValue(IPropertiesEditionEvent event) {
		Diagnostic ret = Diagnostic.OK_INSTANCE;
		if (event.getNewValue() != null) {
			try {
			} catch (IllegalArgumentException iae) {
				ret = BasicDiagnostic.toDiagnostic(iae);
			} catch (WrappedException we) {
				ret = BasicDiagnostic.toDiagnostic(we);
			}
		}
		return ret;
	}


	

}
