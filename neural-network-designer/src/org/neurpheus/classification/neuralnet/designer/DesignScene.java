/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.neurpheus.classification.neuralnet.designer;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.Transferable;
import java.awt.geom.AffineTransform;
import javax.swing.JComponent;
import org.netbeans.api.visual.action.AcceptProvider;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.ConnectProvider;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.anchor.AnchorShape;
import org.netbeans.api.visual.model.ObjectScene;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.api.visual.widget.general.IconNodeWidget;
import org.neurpheus.classification.neuralnet.designer.palette.PaletteSupport;
import org.neurpheus.classification.neuralnet.designer.palette.Shape;
import org.neurpheus.classification.neuralnet.designer.widgets.DirectSynapseWidget;
import org.neurpheus.classification.neuralnet.designer.widgets.FullSynapseWidget;
import org.neurpheus.classification.neuralnet.designer.widgets.InputWidget;
import org.neurpheus.classification.neuralnet.designer.widgets.LinearLayerWidget;
import org.neurpheus.classification.neuralnet.designer.widgets.LogLayerWidget;
import org.neurpheus.classification.neuralnet.designer.widgets.SoftmaxLayerWidget;
import org.neurpheus.classification.neuralnet.designer.widgets.OutputWidget;
import org.neurpheus.classification.neuralnet.designer.widgets.SigmoidLayerWidget;
import org.neurpheus.classification.neuralnet.designer.widgets.TanhLayerWidget;

/**
 *
 * @author jstrychowski
 */
public class DesignScene extends ObjectScene {

    private LayerWidget mainLayer;
    private LayerWidget interactionLayer;
    private LayerWidget connectionLayer;

    public DesignScene() {
        super();
        mainLayer = new LayerWidget(this);
        addChild (mainLayer);
        connectionLayer = new LayerWidget(this);
        addChild (connectionLayer);
        interactionLayer = new LayerWidget(this);
        addChild (interactionLayer);

        getActions().addAction(ActionFactory.createAcceptAction(new AcceptProviderImpl(this)));
        getActions().addAction(ActionFactory.createZoomAction());        
        getActions().addAction(ActionFactory.createPanAction());
        
    }
    

    private class AcceptProviderImpl implements AcceptProvider {

        private WidgetAction editorAction;
        private WidgetAction connectAction;
        private WidgetAction alignWithMoveAction;
        
        Scene scene;
        
        public AcceptProviderImpl(Scene s) {
            scene = s;
            editorAction = ActionFactory.createInplaceEditorAction(new LabelTextFieldEditor());
            connectAction = ActionFactory.createExtendedConnectAction(interactionLayer, new SceneConnectProvider(scene));
            alignWithMoveAction = ActionFactory.createAlignWithMoveAction(mainLayer, interactionLayer, null);
        }

        public ConnectorState isAcceptable(Widget widget, Point point, Transferable transferable) {
            Shape shape = getShapeFromTransferable(transferable);
            Image dragImage = shape.getImage();
            JComponent view = getView();
            Graphics2D g2 = (Graphics2D) view.getGraphics();
            Rectangle visRect = view.getVisibleRect();
            view.paintImmediately(visRect.x, visRect.y, visRect.width, visRect.height);
            g2.drawImage(dragImage, AffineTransform.getTranslateInstance(point.getLocation().getX(), point.getLocation().getY()), null);
            return ConnectorState.ACCEPT;
        }

        public void accept(Widget tarnsferedWidget, Point point, Transferable transferable) {
            Shape shape = getShapeFromTransferable(transferable);
            IconNodeWidget widget = null;
            switch (shape.getNumber().intValue()) {
                case Shape.SHAPE_INPUT_LAYER :
                    widget = new InputWidget(scene);
                    break;
                case Shape.SHAPE_OUTPUT_LAYER :
                    widget = new OutputWidget(scene);
                    break;
                case Shape.SHAPE_FULL_SYNAPSE :
                    widget = new FullSynapseWidget(scene);
                    break;
                case Shape.SHAPE_DIRECT_SYNAPSE :
                    widget = new DirectSynapseWidget(scene);
                    break;
                case Shape.SHAPE_LINEAR_LAYER :
                    widget = new LinearLayerWidget(scene);
                    break;
                case Shape.SHAPE_LOG_LAYER :
                    widget = new LogLayerWidget(scene);
                    break;
                case Shape.SHAPE_SIGMOID_LAYER :
                    widget = new SigmoidLayerWidget(scene);
                    break;
                case Shape.SHAPE_TANH_LAYER :
                    widget = new TanhLayerWidget(scene);
                    break;
                case Shape.SHAPE_SOFTMAX_LAYER :
                    widget = new SoftmaxLayerWidget(scene);
                    break;
                default :
                    widget = new IconNodeWidget(scene);
            }
            widget.setImage(shape.getImage());
            widget.setLabel("X");
            widget.getActions().addAction(connectAction);
            widget.getActions().addAction(createSelectAction());
            widget.getActions().addAction(alignWithMoveAction);
            widget.getActions().addAction(createObjectHoverAction());        
            widget.getLabelWidget().getActions().addAction(editorAction);
            widget.setPreferredLocation(widget.convertLocalToScene(point));
            mainLayer.addChild(widget);
        }

        private Shape getShapeFromTransferable(Transferable transferable) {
            Shape shape = null;
            try {
                shape = (Shape) transferable.getTransferData(PaletteSupport.getShapeFlavor());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return shape;
        }
    }
    
    private class SceneConnectProvider implements ConnectProvider {

        private Scene scene;
        
        public SceneConnectProvider(Scene s) {
            scene = s;
        }
        
        public boolean isSourceWidget(Widget widget) {
            return widget instanceof IconNodeWidget;
        }

        public ConnectorState isTargetWidget(Widget sourceWidget, Widget targetWidget) {
            if (targetWidget instanceof IconNodeWidget) {
                return ConnectorState.ACCEPT;
            } else {
                return ConnectorState.REJECT;
            }
        }

        public boolean hasCustomTargetWidgetResolver(Scene arg0) {
            return false;
        }

        public Widget resolveTargetWidget(Scene arg0, Point arg1) {
            return null;
        }

        public void createConnection(Widget sourceWidget, Widget targetWidget) {
            ConnectionWidget connection = new ConnectionWidget(scene);
            connection.setTargetAnchorShape(AnchorShape.TRIANGLE_FILLED);
            connection.setSourceAnchor(AnchorFactory.createRectangularAnchor(sourceWidget));
            connection.setTargetAnchor(AnchorFactory.createRectangularAnchor(targetWidget));
            connectionLayer.addChild(connection);
        }
        
    }
}