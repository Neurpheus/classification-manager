/*
 * ShapeChildren.java
 *
 * Created on September 21, 2006, 9:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 *
 * To understand this class, see http://platform.netbeans.org/tutorials/nbm-nodesapi3.html
 */


package org.neurpheus.classification.neuralnet.designer.palette;

import java.util.ArrayList;
import org.openide.nodes.Index;
import org.openide.nodes.Node;

/**
 *
 * @author Geertjan Wielenga
 */
public class ShapeChildren  extends Index.ArrayChildren {

    private Category category;

    private static final String iconsPath = "org/neurpheus/classification/neuralnet/designer/palette/icons/";
    
    private String[][] items = new String[][]{
        {Integer.toString(Shape.SHAPE_INPUT_LAYER), "Inputs/Outputs", iconsPath + "NeuralNetworkInput.png"},
        {Integer.toString(Shape.SHAPE_OUTPUT_LAYER), "Inputs/Outputs", iconsPath + "NeuralNetworkOutput.png"},
        {Integer.toString(Shape.SHAPE_LINEAR_LAYER), "Layers", iconsPath + "LinearLayer.png"},
        {Integer.toString(Shape.SHAPE_LOG_LAYER), "Layers", iconsPath + "LogLayer.png"},
        {Integer.toString(Shape.SHAPE_SIGMOID_LAYER), "Layers", iconsPath + "SigmoidLayer.png"},
        {Integer.toString(Shape.SHAPE_TANH_LAYER), "Layers", iconsPath + "TanhLayer.png"},
        {Integer.toString(Shape.SHAPE_SOFTMAX_LAYER), "Layers", iconsPath + "SoftmaxLayer.png"},
        {Integer.toString(Shape.SHAPE_FULL_SYNAPSE), "Synapses", iconsPath + "FullSynapse.png"},
        {Integer.toString(Shape.SHAPE_DIRECT_SYNAPSE), "Synapses", iconsPath + "DirectSynapse.png"},
    };

    public ShapeChildren(Category Category) {
        this.category = Category;
    }

    protected java.util.List<Node> initCollection() {
        ArrayList childrenNodes = new ArrayList( items.length );
        for( int i=0; i<items.length; i++ ) {
            if( category.getName().equals( items[i][1] ) ) {
                Shape item = new Shape();
                item.setNumber(new Integer(items[i][0]));
                item.setCategory(items[i][1]);
                item.setImagePath(items[i][2]);
                childrenNodes.add( new ShapeNode( item ) );
            }
        }
        return childrenNodes;
    }

}