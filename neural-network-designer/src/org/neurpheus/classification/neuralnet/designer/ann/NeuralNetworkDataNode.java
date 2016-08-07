/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neurpheus.classification.neuralnet.designer.ann;

import org.openide.loaders.DataNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;

public class NeuralNetworkDataNode extends DataNode {

    private static final String IMAGE_ICON_BASE = "org/neurpheus/classification/neuralnet/designer/ann/nnet_16_16 (Custom).gif";

    public NeuralNetworkDataNode(NeuralNetworkDataObject obj) {
        super(obj, Children.LEAF);
        setIconBaseWithExtension(IMAGE_ICON_BASE);
    }

    NeuralNetworkDataNode(NeuralNetworkDataObject obj, Lookup lookup) {
        super(obj, Children.LEAF, lookup);
        setIconBaseWithExtension(IMAGE_ICON_BASE);
    }

//    /** Creates a property sheet. */
//    @Override
//    protected Sheet createSheet() {
//        Sheet s = super.createSheet();
//        Sheet.Set ss = s.get(Sheet.PROPERTIES);
//        if (ss == null) {
//            ss = Sheet.createPropertiesSet();
//            s.put(ss);
//        }
//        // TODO add some relevant properties: ss.put(...)
//        return s;
//    }
}
