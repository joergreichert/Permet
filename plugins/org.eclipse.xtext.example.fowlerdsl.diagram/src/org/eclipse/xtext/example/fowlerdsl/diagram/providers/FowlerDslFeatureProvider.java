package org.eclipse.xtext.example.fowlerdsl.diagram.providers;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;
import org.eclipse.xtext.example.fowlerdsl.diagram.features.add.AddStateFeature;
import org.eclipse.xtext.example.fowlerdsl.diagram.features.add.AddTransitionFeature;
import org.eclipse.xtext.example.fowlerdsl.diagram.features.create.CreateStateFeature;
import org.eclipse.xtext.example.fowlerdsl.diagram.features.create.CreateTransitionFeature;
import org.eclipse.xtext.example.fowlerdsl.diagram.features.update.UpdateStateFeature;
import org.eclipse.xtext.example.fowlerdsl.diagram.features.update.UpdateTransitionFeature;
import org.eclipse.xtext.example.fowlerdsl.statemachine.State;
import org.eclipse.xtext.example.fowlerdsl.statemachine.Transition;

public class FowlerDslFeatureProvider extends DefaultFeatureProvider {

	public FowlerDslFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		if (context.getNewObject() instanceof State) {
			return new AddStateFeature(this);
		} else if (context.getNewObject() instanceof Transition) {
			return new AddTransitionFeature(this);
		}
		return super.getAddFeature(context);
	}
	
	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		UpdateStateFeature us = new UpdateStateFeature(this);
		if(us.canUpdate(context)) {
			return us;
		} else {
			UpdateTransitionFeature ts = new UpdateTransitionFeature(this);
			if(ts.canUpdate(context)) {
				return ts;
			}			
		}
		return super.getUpdateFeature(context);
	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] { new CreateStateFeature(this) };
	}

	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] { new CreateTransitionFeature(
				this) };
	}
}
