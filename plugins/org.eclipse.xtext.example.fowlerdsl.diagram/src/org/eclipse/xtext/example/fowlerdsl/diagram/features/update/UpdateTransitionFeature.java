package org.eclipse.xtext.example.fowlerdsl.diagram.features.update;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.xtext.example.fowlerdsl.statemachine.Event;
import org.eclipse.xtext.example.fowlerdsl.statemachine.Transition;

public class UpdateTransitionFeature extends AbstractUpdateFeature {

	public UpdateTransitionFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		Object bo = getBusinessObjectForPictogramElement(context
				.getPictogramElement());
		return (bo instanceof Transition);
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		PictogramElement pe = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pe);
		if (bo instanceof Transition) {
			Transition transition = (Transition) bo;
			if (pe instanceof Connection) {
				Connection con = (Connection) pe;
				for (ConnectionDecorator deco : con.getConnectionDecorators()) {
					if (deco.getGraphicsAlgorithm() instanceof Text) {
						Text text = (Text) deco.getGraphicsAlgorithm();
						if (transition.getEvent() != null
								&& text.getValue() != null
								&& text.getValue().equals(
										transition.getEvent().getName())) {
							return Reason.createFalseReason();
						} else {
							return Reason.createTrueReason();
						}
					}
				}
			}
		}
		return Reason.createFalseReason();
	}

	@Override
	public boolean update(IUpdateContext context) {
		PictogramElement pe = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pe);
		if (bo instanceof Transition) {
			Transition transition = (Transition) bo;
			if (pe instanceof Connection) {
				Connection con = (Connection) pe;
				for (ConnectionDecorator deco : con.getConnectionDecorators()) {
					if (deco.getGraphicsAlgorithm() instanceof Text) {
						Text text = (Text) deco.getGraphicsAlgorithm();
						if (text.getValue() != null) {
							Event event = transition.getEvent();
							if (event != null) {
								text.setValue(event.getName());
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
}
