/**
 * Generated with Acceleo
 */
package org.eclipse.xtext.example.fowlerdsl.statemachine.parts.impl;

// Start of user code for imports
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;

import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart;

import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;

import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;

import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;

import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;

import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;

import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;

import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionStep;

import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;

import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;

import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable.ReferencesTableListener;

import org.eclipse.emf.eef.runtime.ui.widgets.SWTUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.TabElementTreeSelectionDialog;

import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableContentProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import org.eclipse.xtext.example.fowlerdsl.statemachine.parts.StatePropertiesEditionPart;
import org.eclipse.xtext.example.fowlerdsl.statemachine.parts.StatemachineViewsRepository;

import org.eclipse.xtext.example.fowlerdsl.statemachine.providers.StatemachineMessages;

// End of user code

/**
 * 
 * 
 */
public class StatePropertiesEditionPartImpl extends CompositePropertiesEditionPart implements ISWTPropertiesEditionPart, StatePropertiesEditionPart {

	protected Text name;
	protected ReferencesTable actions;
	protected List<ViewerFilter> actionsBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> actionsFilters = new ArrayList<ViewerFilter>();
	protected ReferencesTable transitions;
	protected List<ViewerFilter> transitionsBusinessFilters = new ArrayList<ViewerFilter>();
	protected List<ViewerFilter> transitionsFilters = new ArrayList<ViewerFilter>();



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public StatePropertiesEditionPartImpl(IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
	 * 			createFigure(org.eclipse.swt.widgets.Composite)
	 * 
	 */
	public Composite createFigure(final Composite parent) {
		view = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		view.setLayout(layout);
		createControls(view);
		return view;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.ISWTPropertiesEditionPart#
	 * 			createControls(org.eclipse.swt.widgets.Composite)
	 * 
	 */
	public void createControls(Composite view) { 
		CompositionSequence stateStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = stateStep.addStep(StatemachineViewsRepository.State.Properties.class);
		propertiesStep.addStep(StatemachineViewsRepository.State.Properties.name);
		propertiesStep.addStep(StatemachineViewsRepository.State.Properties.actions);
		propertiesStep.addStep(StatemachineViewsRepository.State.Properties.transitions);
		
		
		composer = new PartComposer(stateStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == StatemachineViewsRepository.State.Properties.class) {
					return createPropertiesGroup(parent);
				}
				if (key == StatemachineViewsRepository.State.Properties.name) {
					return createNameText(parent);
				}
				if (key == StatemachineViewsRepository.State.Properties.actions) {
					return createActionsAdvancedReferencesTable(parent);
				}
				if (key == StatemachineViewsRepository.State.Properties.transitions) {
					return createTransitionsAdvancedTableComposition(parent);
				}
				return parent;
			}
		};
		composer.compose(view);
	}

	/**
	 * 
	 */
	protected Composite createPropertiesGroup(Composite parent) {
		Group propertiesGroup = new Group(parent, SWT.NONE);
		propertiesGroup.setText(StatemachineMessages.StatePropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesGroupData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesGroupData.horizontalSpan = 3;
		propertiesGroup.setLayoutData(propertiesGroupData);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		return propertiesGroup;
	}

	
	protected Composite createNameText(Composite parent) {
		createDescription(parent, StatemachineViewsRepository.State.Properties.name, StatemachineMessages.StatePropertiesEditionPart_NameLabel);
		name = SWTUtils.createScrollableText(parent, SWT.BORDER);
		GridData nameData = new GridData(GridData.FILL_HORIZONTAL);
		name.setLayoutData(nameData);
		name.addFocusListener(new FocusAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StatePropertiesEditionPartImpl.this, StatemachineViewsRepository.State.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
			}

		});
		name.addKeyListener(new KeyAdapter() {

			/**
			 * {@inheritDoc}
			 * 
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StatePropertiesEditionPartImpl.this, StatemachineViewsRepository.State.Properties.name, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, name.getText()));
				}
			}

		});
		EditingUtils.setID(name, StatemachineViewsRepository.State.Properties.name);
		EditingUtils.setEEFtype(name, "eef::Text"); //$NON-NLS-1$
		SWTUtils.createHelpButton(parent, propertiesEditionComponent.getHelpContent(StatemachineViewsRepository.State.Properties.name, StatemachineViewsRepository.SWT_KIND), null); //$NON-NLS-1$
		// Start of user code for createNameText

		// End of user code
		return parent;
	}

	/**
	 * 
	 */
	protected Composite createActionsAdvancedReferencesTable(Composite parent) {
		String label = getDescription(StatemachineViewsRepository.State.Properties.actions, StatemachineMessages.StatePropertiesEditionPart_ActionsLabel);		 
		this.actions = new ReferencesTable(label, new ReferencesTableListener() {
			public void handleAdd() { addActions(); }
			public void handleEdit(EObject element) { editActions(element); }
			public void handleMove(EObject element, int oldIndex, int newIndex) { moveActions(element, oldIndex, newIndex); }
			public void handleRemove(EObject element) { removeFromActions(element); }
			public void navigateTo(EObject element) { }
		});
		this.actions.setHelpText(propertiesEditionComponent.getHelpContent(StatemachineViewsRepository.State.Properties.actions, StatemachineViewsRepository.SWT_KIND));
		this.actions.createControls(parent);
		this.actions.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StatePropertiesEditionPartImpl.this, StatemachineViewsRepository.State.Properties.actions, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData actionsData = new GridData(GridData.FILL_HORIZONTAL);
		actionsData.horizontalSpan = 3;
		this.actions.setLayoutData(actionsData);
		this.actions.disableMove();
		actions.setID(StatemachineViewsRepository.State.Properties.actions);
		actions.setEEFType("eef::AdvancedReferencesTable"); //$NON-NLS-1$
		return parent;
	}

	/**
	 * 
	 */
	protected void addActions() {
		TabElementTreeSelectionDialog dialog = new TabElementTreeSelectionDialog(actions.getInput(), actionsFilters, actionsBusinessFilters,
		"actions", propertiesEditionComponent.getEditingContext().getAdapterFactory(), current.eResource()) {
			@Override
			public void process(IStructuredSelection selection) {
				for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
					EObject elem = (EObject) iter.next();
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StatePropertiesEditionPartImpl.this, StatemachineViewsRepository.State.Properties.actions,
						PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, elem));
				}
				actions.refresh();
			}
		};
		dialog.open();
	}

	/**
	 * 
	 */
	protected void moveActions(EObject element, int oldIndex, int newIndex) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StatePropertiesEditionPartImpl.this, StatemachineViewsRepository.State.Properties.actions, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
		actions.refresh();
	}

	/**
	 * 
	 */
	protected void removeFromActions(EObject element) {
		propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StatePropertiesEditionPartImpl.this, StatemachineViewsRepository.State.Properties.actions, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
		actions.refresh();
	}

	/**
	 * 
	 */
	protected void editActions(EObject element) {
		EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(propertiesEditionComponent.getEditingContext(), propertiesEditionComponent, element, adapterFactory);
		PropertiesEditingProvider provider = (PropertiesEditingProvider)adapterFactory.adapt(element, PropertiesEditingProvider.class);
		if (provider != null) {
			PropertiesEditingPolicy policy = provider.getPolicy(context);
			if (policy != null) {
				policy.execute();
				actions.refresh();
			}
		}
	}

	/**
	 * @param container
	 * 
	 */
	protected Composite createTransitionsAdvancedTableComposition(Composite parent) {
		this.transitions = new ReferencesTable(getDescription(StatemachineViewsRepository.State.Properties.transitions, StatemachineMessages.StatePropertiesEditionPart_TransitionsLabel), new ReferencesTableListener() {
			public void handleAdd() { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StatePropertiesEditionPartImpl.this, StatemachineViewsRepository.State.Properties.transitions, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.ADD, null, null));
				transitions.refresh();
			}
			public void handleEdit(EObject element) {
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StatePropertiesEditionPartImpl.this, StatemachineViewsRepository.State.Properties.transitions, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.EDIT, null, element));
				transitions.refresh();
			}
			public void handleMove(EObject element, int oldIndex, int newIndex) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StatePropertiesEditionPartImpl.this, StatemachineViewsRepository.State.Properties.transitions, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.MOVE, element, newIndex));
				transitions.refresh();
			}
			public void handleRemove(EObject element) { 
				propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StatePropertiesEditionPartImpl.this, StatemachineViewsRepository.State.Properties.transitions, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.REMOVE, null, element));
				transitions.refresh();
			}
			public void navigateTo(EObject element) { }
		});
		for (ViewerFilter filter : this.transitionsFilters) {
			this.transitions.addFilter(filter);
		}
		this.transitions.setHelpText(propertiesEditionComponent.getHelpContent(StatemachineViewsRepository.State.Properties.transitions, StatemachineViewsRepository.SWT_KIND));
		this.transitions.createControls(parent);
		this.transitions.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null && e.item.getData() instanceof EObject) {
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(StatePropertiesEditionPartImpl.this, StatemachineViewsRepository.State.Properties.transitions, PropertiesEditionEvent.CHANGE, PropertiesEditionEvent.SELECTION_CHANGED, null, e.item.getData()));
				}
			}
			
		});
		GridData transitionsData = new GridData(GridData.FILL_HORIZONTAL);
		transitionsData.horizontalSpan = 3;
		this.transitions.setLayoutData(transitionsData);
		this.transitions.setLowerBound(0);
		this.transitions.setUpperBound(-1);
		transitions.setID(StatemachineViewsRepository.State.Properties.transitions);
		transitions.setEEFType("eef::AdvancedTableComposition"); //$NON-NLS-1$
		// Start of user code for createTransitionsAdvancedTableComposition

		// End of user code
		return parent;
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionListener#firePropertiesChanged(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void firePropertiesChanged(IPropertiesEditionEvent event) {
		// Start of user code for tab synchronization
		
		// End of user code
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.xtext.example.fowlerdsl.statemachine.parts.StatePropertiesEditionPart#getName()
	 * 
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.xtext.example.fowlerdsl.statemachine.parts.StatePropertiesEditionPart#setName(String newValue)
	 * 
	 */
	public void setName(String newValue) {
		if (newValue != null) {
			name.setText(newValue);
		} else {
			name.setText(""); //$NON-NLS-1$
		}
		boolean eefElementEditorReadOnlyState = isReadOnly(StatemachineViewsRepository.State.Properties.name);
		if (eefElementEditorReadOnlyState && name.isEnabled()) {
			name.setEnabled(false);
			name.setToolTipText(StatemachineMessages.State_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !name.isEnabled()) {
			name.setEnabled(true);
		}	
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.xtext.example.fowlerdsl.statemachine.parts.StatePropertiesEditionPart#initActions(org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings)
	 */
	public void initActions(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		actions.setContentProvider(contentProvider);
		actions.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(StatemachineViewsRepository.State.Properties.actions);
		if (eefElementEditorReadOnlyState && actions.getTable().isEnabled()) {
			actions.setEnabled(false);
			actions.setToolTipText(StatemachineMessages.State_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !actions.getTable().isEnabled()) {
			actions.setEnabled(true);
		}
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.xtext.example.fowlerdsl.statemachine.parts.StatePropertiesEditionPart#updateActions()
	 * 
	 */
	public void updateActions() {
	actions.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.xtext.example.fowlerdsl.statemachine.parts.StatePropertiesEditionPart#addFilterActions(ViewerFilter filter)
	 * 
	 */
	public void addFilterToActions(ViewerFilter filter) {
		actionsFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.xtext.example.fowlerdsl.statemachine.parts.StatePropertiesEditionPart#addBusinessFilterActions(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToActions(ViewerFilter filter) {
		actionsBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.xtext.example.fowlerdsl.statemachine.parts.StatePropertiesEditionPart#isContainedInActionsTable(EObject element)
	 * 
	 */
	public boolean isContainedInActionsTable(EObject element) {
		return ((ReferencesTableSettings)actions.getInput()).contains(element);
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.xtext.example.fowlerdsl.statemachine.parts.StatePropertiesEditionPart#initTransitions(EObject current, EReference containingFeature, EReference feature)
	 */
	public void initTransitions(ReferencesTableSettings settings) {
		if (current.eResource() != null && current.eResource().getResourceSet() != null)
			this.resourceSet = current.eResource().getResourceSet();
		ReferencesTableContentProvider contentProvider = new ReferencesTableContentProvider();
		transitions.setContentProvider(contentProvider);
		transitions.setInput(settings);
		boolean eefElementEditorReadOnlyState = isReadOnly(StatemachineViewsRepository.State.Properties.transitions);
		if (eefElementEditorReadOnlyState && transitions.isEnabled()) {
			transitions.setEnabled(false);
			transitions.setToolTipText(StatemachineMessages.State_ReadOnly);
		} else if (!eefElementEditorReadOnlyState && !transitions.isEnabled()) {
			transitions.setEnabled(true);
		}	
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.xtext.example.fowlerdsl.statemachine.parts.StatePropertiesEditionPart#updateTransitions()
	 * 
	 */
	public void updateTransitions() {
	transitions.refresh();
}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.xtext.example.fowlerdsl.statemachine.parts.StatePropertiesEditionPart#addFilterTransitions(ViewerFilter filter)
	 * 
	 */
	public void addFilterToTransitions(ViewerFilter filter) {
		transitionsFilters.add(filter);
		if (this.transitions != null) {
			this.transitions.addFilter(filter);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.xtext.example.fowlerdsl.statemachine.parts.StatePropertiesEditionPart#addBusinessFilterTransitions(ViewerFilter filter)
	 * 
	 */
	public void addBusinessFilterToTransitions(ViewerFilter filter) {
		transitionsBusinessFilters.add(filter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.xtext.example.fowlerdsl.statemachine.parts.StatePropertiesEditionPart#isContainedInTransitionsTable(EObject element)
	 * 
	 */
	public boolean isContainedInTransitionsTable(EObject element) {
		return ((ReferencesTableSettings)transitions.getInput()).contains(element);
	}






	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return StatemachineMessages.State_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
