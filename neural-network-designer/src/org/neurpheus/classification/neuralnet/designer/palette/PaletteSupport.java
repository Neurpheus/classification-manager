/*
 * PaletteSupport.java
 *
 * Created on September 25, 2006, 2:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 *
 * To understand this class, see http://platform.netbeans.org/tutorials/nbm-nodesapi3.html
 */

package org.neurpheus.classification.neuralnet.designer.palette;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.Action;
import org.netbeans.spi.palette.DragAndDropHandler;
import org.netbeans.spi.palette.PaletteActions;
import org.netbeans.spi.palette.PaletteController;
import org.netbeans.spi.palette.PaletteFactory;
import org.openide.nodes.AbstractNode;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.datatransfer.ExTransferable;

/**
 *
 * @author dave
 */
public class PaletteSupport {
    
    private static DataFlavor shapeFlavor = null;
    
    public static DataFlavor getShapeFlavor() {
        if (shapeFlavor == null) {
            try {
                shapeFlavor = new DataFlavor("application/neurpheus-neuralnet-designer");
            } catch (ClassNotFoundException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        return shapeFlavor;
    }
    
    public static PaletteController createPalette() {
        AbstractNode paletteRoot = new AbstractNode(new CategoryChildren());
        paletteRoot.setName("Palette Root");
        return PaletteFactory.createPalette( paletteRoot, new MyActions(), null, new MyDnDHandler() );
    }
    
    private static class MyActions extends PaletteActions {
        public Action[] getImportActions() {
            return null;
        }
        
        public Action[] getCustomPaletteActions() {
            return null;
        }
        
        public Action[] getCustomCategoryActions(Lookup lookup) {
            return null;
        }
        
        public Action[] getCustomItemActions(Lookup lookup) {
            return null;
        }
        
        public Action getPreferredAction(Lookup lookup) {
            return null;
        }
        
    }
    
    private static class MyDnDHandler extends DragAndDropHandler {

        public void customize(ExTransferable exTransferable, Lookup lookup) {
            DataFlavor[] flavors = exTransferable.getTransferDataFlavors();
            ShapeNode shapeNode = null;
            for (int i = 0; i < flavors.length; i++) {
                try {
                    Object obj = exTransferable.getTransferData(flavors[i]);
                    if (obj instanceof ShapeNode) {
                        shapeNode = (ShapeNode) obj;
                    }
                } catch (UnsupportedFlavorException ex) {
                    Exceptions.printStackTrace(ex);
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
            if (shapeNode != null) {
                final Shape shape = shapeNode.getShape();
                exTransferable.put(new ExTransferable.Single (getShapeFlavor()) {

                    protected Object getData() throws IOException, UnsupportedFlavorException {
                        return shape;
                    }

                });
            }
        }
        
    }

}
