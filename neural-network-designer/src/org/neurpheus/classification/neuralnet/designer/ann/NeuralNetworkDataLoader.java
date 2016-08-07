/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neurpheus.classification.neuralnet.designer.ann;

import java.io.IOException;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.UniFileLoader;
import org.openide.util.NbBundle;

public class NeuralNetworkDataLoader extends UniFileLoader {

    public static final String REQUIRED_MIME = "text/x-neurpheus-neural-network";
    private static final long serialVersionUID = 1L;

    public NeuralNetworkDataLoader() {
        super("org.neurpheus.classification.neuralnet.designer.ann.NeuralNetworkDataObject");
    }

    @Override
    protected String defaultDisplayName() {
        return NbBundle.getMessage(NeuralNetworkDataLoader.class, "LBL_NeuralNetwork_loader_name");
    }

    @Override
    protected void initialize() {
        super.initialize();
        getExtensions().addMimeType(REQUIRED_MIME);
    }

    protected MultiDataObject createMultiObject(FileObject primaryFile) throws DataObjectExistsException, IOException {
        return new NeuralNetworkDataObject(primaryFile, this);
    }

    @Override
    protected String actionsContext() {
        return "Loaders/" + REQUIRED_MIME + "/Actions";
    }
}
