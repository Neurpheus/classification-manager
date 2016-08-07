/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.neurpheus.classification.neuralnet.designer.ann;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import org.neurpheus.classification.neuralnet.xml.XmlNeuralNetwork;
import org.openide.ErrorManager;

/**
 *
 * @author jstrychowski
 */
class NeuralNetworkModelSynchronizer {

    NeuralNetworkDataObject nnDataObject;
    
    public NeuralNetworkModelSynchronizer(NeuralNetworkDataObject dataObject) {
        //super(dataObject, 500);
        nnDataObject = dataObject;
    }

    protected boolean mayUpdateData(boolean allowDialog) {
        return true;
    }

    protected void updateDataFromModel(Object model, org.openide.filesystems.FileLock lock, boolean modify) {
        if (model == null) {
            return;
        }
        try {
            Writer out = new StringWriter();
            ((XmlNeuralNetwork) model).write(out, "utf-8");
            out.close();
//            nnDataObject.getDataCache().setData(lock, out.toString(), modify);
        } catch (IOException e) {
            ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, e);
        }
    }

    protected Object getModel() {
        return nnDataObject.getNeuralNetwork();
    }

    protected void reloadModelFromData() {
        try {
            nnDataObject.parseDocument();
        } catch (IOException e) {
            ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, e);
        }
    }
    
}
