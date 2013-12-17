package org.eclipse.xtext.example.fowlerdsl.diagram.properties;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.api.adapters.SemanticAdapter;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.ui.widgets.ReferencesTable;
import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.internal.command.CommandExec;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.platform.IDiagramContainer;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.internal.T;
import org.eclipse.graphiti.ui.internal.parts.FreeFormConnectionEditPart;
import org.eclipse.graphiti.ui.internal.parts.IContainerShapeEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.IContributedContentsView;

@SuppressWarnings("restriction")
public class FowlerDslPropertiesEditionPartFormUtils {

	public static ISelection createSelection(final IWorkbenchPart part,
			final FowlerDslElementFilter filter) {
		IDiagramContainer diagramEditor = getDiagramEditor(part);
		if (diagramEditor != null) {
			ISelectionProvider selectionProvider = part.getSite()
					.getSelectionProvider();
			Object selectedDiagramElement = null;
			if (selectionProvider != null) {
				ISelection selection = selectionProvider.getSelection();
				if (selection instanceof IStructuredSelection) {
					IStructuredSelection structuredSelection = (IStructuredSelection) selection;
					selectedDiagramElement = structuredSelection
							.getFirstElement();
				}
			}
			List<EObject> selectedEObjects = new ArrayList<EObject>();
			EObject semanticObject = null;
			if (selectedDiagramElement == null) {
				List<PictogramElement> pes = Arrays.asList(diagramEditor
						.getSelectedPictogramElements());
				if (pes.size() > 0) {
					semanticObject = resolveSemanticObject(pes.get(0), filter);
				}
			} else {
				semanticObject = resolveSemanticObject(selectedDiagramElement,
						filter);
			}
			if (semanticObject != null) {
				selectedEObjects.add(semanticObject);
			}
			return new StructuredSelection(selectedEObjects);
		}
		return null;
	}

	/**
	 * @return the selected pictogram element.
	 */
	public static PictogramElement getSelectedPictogramElement(
			ISelection selection) {
		if (selection instanceof StructuredSelection) {
			StructuredSelection structuredSelection = (StructuredSelection) selection;

			Object firstElement = structuredSelection.getFirstElement();

			if (firstElement instanceof PictogramElement) {
				return (PictogramElement) firstElement;
			}

			EditPart editPart = null;
			if (firstElement instanceof EditPart) {
				editPart = (EditPart) firstElement;
			} else if (firstElement instanceof IAdaptable) {
				editPart = (EditPart) ((IAdaptable) firstElement)
						.getAdapter(EditPart.class);
			}
			if (editPart != null
					&& editPart.getModel() instanceof PictogramElement) {
				return (PictogramElement) editPart.getModel();
			}
		}
		return null;
	}

	/**
	 * Executes the feature and adds it to the command stack.
	 * 
	 * @param feature
	 *            the feature
	 * @param context
	 *            the context
	 */
	public static void execute(IFeature feature, IContext context) {
		try {
			CommandExec.executeFeatureWithContext(feature, context);
		} catch (Exception e) {
			if (e instanceof OperationCanceledException) {
				// Just log it as info (operation was cancelled on purpose)
				T.racer().log(IStatus.INFO,
						"GFCommandStack.execute(Command) " + e, e); //$NON-NLS-1$
			} else {
				// Just log it as an error
				T.racer().error("GFCommandStack.execute(Command) " + e, e); //$NON-NLS-1$
			}
		}
	}

	public static void propertyChange(PropertyChangeEvent evt) {
		// refresh();
	}

	/**
	 * @return the {@link IDiagramContainer} diagram editor.
	 */
	public static IDiagramContainer getDiagramEditor(IWorkbenchPart part) {
		if (part instanceof DiagramEditor) {
			return (DiagramEditor) part;
		}
		IContributedContentsView contributedView = (IContributedContentsView) part
				.getAdapter(IContributedContentsView.class);
		if (contributedView != null) {
			part = contributedView.getContributingPart();
		}
		if (part instanceof DiagramEditor) {
			return (DiagramEditor) part;
		}

		return null;
	}

	/**
	 * @return the diagram.
	 */
	public static Diagram getDiagram(IWorkbenchPart part) {
		IDiagramTypeProvider diagramTypeProvider = getDiagramTypeProvider(part);
		if (diagramTypeProvider == null) {
			return null;
		}
		return diagramTypeProvider.getDiagram();
	}

	/**
	 * @return the diagram type provider.
	 */
	public static IDiagramTypeProvider getDiagramTypeProvider(
			IWorkbenchPart part) {
		IDiagramContainer diagramEditor = getDiagramEditor(part);
		if (diagramEditor == null) {
			return null;
		}
		return diagramEditor.getDiagramTypeProvider();
	}

	public static void disableReferencesTable(ReferencesTable table) {
		DummyReferencesTableListener dummy = new DummyReferencesTableListener();
		table.setEnabled(false);
		table.addTableReferenceListener(dummy);
	}

	public static EObject resolveSemanticObject(Object object,
			FowlerDslElementFilter filter) {
		PictogramElement pe = null;
		if (object instanceof EObject) {
			return (EObject) object;
		} else if (object instanceof IContainerShapeEditPart) {
			pe = ((IContainerShapeEditPart) object).getPictogramElement();
			PictogramLink link = pe.getLink();
			if (link != null) {
				for (EObject bo : link.getBusinessObjects()) {
					if (filter.accept(bo)) {
						return bo;
					}
				}
			}
		} else if (object instanceof FreeFormConnectionEditPart) {
			pe = ((FreeFormConnectionEditPart) object).getPictogramElement();
			PictogramLink link = pe.getLink();
			if (link != null) {
				for (EObject bo : link.getBusinessObjects()) {
					if (filter.accept(bo)) {
						return bo;
					}
				}
			}
		} else if (object instanceof IAdaptable) {
			IAdaptable adaptable = (IAdaptable) object;
			if (adaptable.getAdapter(SemanticAdapter.class) != null) {
				SemanticAdapter semanticAdapter = (SemanticAdapter) adaptable
						.getAdapter(SemanticAdapter.class);
				return semanticAdapter.getEObject();
			} else if (adaptable.getAdapter(EObject.class) != null) {
				return (EObject) adaptable.getAdapter(EObject.class);
			}
		}
		return null;
	}

	public static void firePropertiesChanged(IPropertiesEditionEvent event,
			PictogramElement pe, IWorkbenchPart part) {
		if (pe != null) {
			UpdateContext context = new UpdateContext(pe);
			IDiagramContainer diagramEditor = getDiagramEditor(part);
			if (diagramEditor != null
					&& diagramEditor.getDiagramTypeProvider() != null
					&& diagramEditor.getDiagramTypeProvider()
							.getFeatureProvider() != null) {
				diagramEditor.getDiagramTypeProvider().getFeatureProvider()
						.updateIfPossible(context);
			}
		}
	}
}
