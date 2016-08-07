/*
 * ShapeNode.java
 *
 * Created on September 21, 2006, 9:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 *
 * To understand this class, see http://platform.netbeans.org/tutorials/nbm-nodesapi3.html
 */

package org.neurpheus.classification.neuralnet.designer.palette;

import java.beans.BeanInfo;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Geertjan Wielenga
 */
public class ShapeNode extends AbstractNode {
    
    private Shape shape;
    
    /** Creates a new instance of InstrumentNode */
    public ShapeNode(Shape key) {
        super(Children.LEAF, Lookups.fixed( new Object[] {key} ) );
        this.shape = key;
        setIconBaseWithExtension(key.getImagePath());
        shape.setImage(getIcon(BeanInfo.ICON_COLOR_16x16));
    }
    
    public Shape getShape() {
        return shape;
    }
    
}