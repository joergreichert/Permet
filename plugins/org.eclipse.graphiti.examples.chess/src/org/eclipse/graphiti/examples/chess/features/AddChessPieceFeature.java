/**
 * <copyright>
 * 
 * Copyright (c) 2011, 2011 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 * 
 * </copyright>
 */
package org.eclipse.graphiti.examples.chess.features;

import org.eclipse.graphiti.examples.mm.chess.Colors;
import org.eclipse.graphiti.examples.mm.chess.Piece;
import org.eclipse.graphiti.examples.mm.chess.Types;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ICreateService;
import org.eclipse.graphiti.services.IGaLayoutService;
import org.eclipse.graphiti.util.IColorConstant;

public class AddChessPieceFeature extends AbstractAddShapeFeature {

	public AddChessPieceFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canAdd(IAddContext context) {
		if (context.getNewObject() instanceof Piece) {
			return context.getTargetContainer().getChildren().size() < 64;
		}
		return false;
	}

	public PictogramElement add(IAddContext context) {
		// Get the Graphiti services
		ICreateService createService = Graphiti.getCreateService();
		IGaLayoutService layoutService = Graphiti.getGaLayoutService();

		// Get the piece to add
		Piece piece = (Piece) context.getNewObject();

		// Get the image
		int[] points = null;
		if (Types.BISHOP.equals(piece.getType())) {
			points = new int[] { 10, 45, 10, 40, 15, 30, 20, 25, 15, 20, 15, 15, 20, 10, 20, 5, 30, 5, 30, 10, 35, 15,
					35, 20, 30, 25, 35, 30, 40, 40, 40, 45 };
		} else if (Types.KING.equals(piece.getType())) {
			points = new int[] { 10, 45, 15, 40, 10, 35, 5, 25, 5, 5, 10, 15, 15, 5, 20, 15, 25, 5, 30, 15, 35, 5, 40,
					15, 45, 5, 45, 25, 40, 35, 35, 40, 40, 45 };
		} else if (Types.KNIGHT.equals(piece.getType())) {
			points = new int[] { 20, 45, 25, 35, 20, 25, 10, 35, 5, 30, 5, 25, 10, 15, 15, 10, 15, 5, 20, 10, 25, 5,
					25, 10, 35, 15, 40, 20, 45, 30, 45, 45 };
		} else if (Types.PAWN.equals(piece.getType())) {
			points = new int[] { 15, 45, 15, 40, 20, 30, 15, 25, 20, 20, 20, 15, 25, 10, 30, 15, 30, 20, 35, 25, 30,
					30, 35, 40, 35, 45 };
		} else if (Types.QUEEN.equals(piece.getType())) {
			points = new int[] { 10, 45, 15, 40, 10, 35, 5, 25, 5, 15, 10, 10, 15, 10, 20, 15, 25, 5, 30, 15, 35, 10,
					40, 10, 45, 15, 45, 25, 40, 35, 35, 40, 40, 45 };
		} else if (Types.ROOK.equals(piece.getType())) {
			points = new int[] { 10, 45, 10, 40, 15, 30, 15, 15, 10, 10, 10, 5, 15, 5, 15, 10, 20, 10, 20, 5, 30, 5,
					30, 10, 35, 10, 35, 5, 40, 5, 40, 10, 35, 15, 35, 30, 40, 40, 40, 45 };
		}

		// Create the visualization of the board as a square
		ContainerShape pieceShape = createService.createContainerShape(context.getTargetContainer(), true);
		Polygon piecePolygon = createService.createPolygon(pieceShape, points);

		// Set the line color; it needs to be the opposite color of the square
		if (Colors.LIGHT.equals(piece.getSquare().getColor())) {
			piecePolygon.setForeground(manageColor(IColorConstant.BLACK));
		} else {
			piecePolygon.setForeground(manageColor(IColorConstant.WHITE));
		}
		piecePolygon.setLineWidth(2);

		// Set the fill color; it needs to be the color of the owner of the
		// piece
		if (Colors.LIGHT.equals(piece.getOwner())) {
			piecePolygon.setBackground(manageColor(IColorConstant.WHITE));
		} else {
			piecePolygon.setBackground(manageColor(IColorConstant.BLACK));
		}

		// Link the visualization with the board
		link(pieceShape, piece);

		// Create an anchor for attaching moves (in fact we are using two
		// anchors here. The first one is a chopbox anchor that serves for
		// catching the connection creation. The second one is a box
		// relative anchor; it is used to actually attach the connection to.
		// The box relative anchor is visualised as a circle with diameter
		// 0 (invisible). The advantage of this construction is that it is
		// possible to attach the connection to the complete shape (via the
		// chopbox anchor) and not to have the clipping (because of the box
		// relative anchor) while the user does not have the possibility to
		// move the anchor around and while he still can get hold of the
		// shape under the box relative anchor.
		createService.createChopboxAnchor(pieceShape);
		BoxRelativeAnchor relativeAnchor = createService.createBoxRelativeAnchor(pieceShape);
		relativeAnchor.setRelativeHeight(0.5d);
		relativeAnchor.setRelativeWidth(0.5d);
		relativeAnchor.setReferencedGraphicsAlgorithm(piecePolygon);
		relativeAnchor.setUseAnchorLocationAsConnectionEndpoint(true);
		Ellipse anchorEllipse = createService.createEllipse(relativeAnchor);
		layoutService.setLocationAndSize(anchorEllipse, 25, 25, 0, 0);

		return pieceShape;
	}
}
