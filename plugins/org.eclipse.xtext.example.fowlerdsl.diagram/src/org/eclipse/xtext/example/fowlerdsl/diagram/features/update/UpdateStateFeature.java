package org.eclipse.xtext.example.fowlerdsl.diagram.features.update;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.xtext.example.fowlerdsl.statemachine.State;

public class UpdateStateFeature extends AbstractUpdateFeature {

	public UpdateStateFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		Object bo = getBusinessObjectForPictogramElement(context
				.getPictogramElement());
		return (bo instanceof State);
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		PictogramElement pe = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pe);
		if (bo instanceof State) {
			if(pe.getGraphicsAlgorithm() instanceof Text) {
				Text text = (Text) pe.getGraphicsAlgorithm();
				if(text.getValue() != null && text.getValue().equals(((State) bo).getName())) {
					return Reason.createFalseReason();
				} else {
					return Reason.createTrueReason();
				}
			}
		}
		return Reason.createFalseReason();
	}

	@Override
	public boolean update(IUpdateContext context) {
		PictogramElement pe = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pe);
		if (bo instanceof State) {
			if(pe.getGraphicsAlgorithm() instanceof Text) {
				Text text = (Text) pe.getGraphicsAlgorithm();
				text.setValue(((State) bo).getName());
				return true;
			}
		}
		return false;
	}
}
