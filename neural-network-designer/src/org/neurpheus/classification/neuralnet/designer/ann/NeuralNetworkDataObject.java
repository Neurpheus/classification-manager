/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neurpheus.classification.neuralnet.designer.ann;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import org.netbeans.api.xml.cookies.CheckXMLCookie;
import org.netbeans.api.xml.cookies.ValidateXMLCookie;
import org.netbeans.spi.xml.cookies.CheckXMLSupport;
import org.netbeans.spi.xml.cookies.DataObjectAdapters;
import org.netbeans.spi.xml.cookies.ValidateXMLSupport;
import org.neurpheus.classification.neuralnet.xml.XmlNeuralNetwork;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.nodes.CookieSet;
import org.openide.nodes.Node;
import org.openide.util.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class NeuralNetworkDataObject extends MultiDataObject {

    private static final int TYPE_TOOLBAR = 0;
    private static final int TYPE_TREEPANEL = 1;
    
    private NeuralNetworkModelSynchronizer modelSynchronizer;
    private XmlNeuralNetwork ann;
            
    public NeuralNetworkDataObject(FileObject pf, NeuralNetworkDataLoader loader) throws DataObjectExistsException, IOException {
        super(pf, loader);
        modelSynchronizer = new NeuralNetworkModelSynchronizer(this);
        InputSource in = DataObjectAdapters.inputSource(this);
        CookieSet cookies = getCookieSet();
        CheckXMLCookie checkCookie = new CheckXMLSupport(in);
        cookies.add(checkCookie);
        ValidateXMLCookie validateCookie = new ValidateXMLSupport(in);
        cookies.add(validateCookie);
        parseDocument();
        //cookies.add((Node.Cookie) DataEditorSupport.create(this, getPrimaryEntry(), cookies));
    }
    
    public XmlNeuralNetwork getNeuralNetwork() {
        if (ann == null) {
            InputStream in = null;
            try {
                in = new BufferedInputStream(new FileInputStream(FileUtil.toFile(getPrimaryFile())));
                ann = XmlNeuralNetwork.read(in);
            } catch (ParserConfigurationException ex) {
                Exceptions.printStackTrace(ex);
            } catch (SAXException ex) {
                Exceptions.printStackTrace(ex);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            }
        }
        return ann;
    }

    @Override
    public boolean isDeleteAllowed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCopyAllowed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isMoveAllowed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isRenameAllowed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HelpCtx getHelpCtx() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected void parseDocument() throws IOException {
        if (ann==null) {
            ann = getNeuralNetwork();
        } else {
            try {
                java.io.InputStream is = getEditorSupport().getInputStream();
                XmlNeuralNetwork newNeuralNetwork = null;
                newNeuralNetwork = XmlNeuralNetwork.read(is);
                if (newNeuralNetwork != null) {
                    // ann.merge(newNeuralNetwork, org.netbeans.modules.schema2beans.BaseBean.MERGE_UPDATE);
                }
            } catch (ParserConfigurationException ex) {
                Exceptions.printStackTrace(ex);
            } catch (SAXException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
    
    @Override
    protected Node createNodeDelegate() {
        return new NeuralNetworkDataNode(this, getLookup());
    }

    @Override
    public Lookup getLookup() {
        return getCookieSet().getLookup();
    }

//    @Override
//    protected DesignMultiViewDesc[] getMultiViewDesc() {
//        return new DesignMultiViewDesc[]{
////            new DesignView(this,TYPE_TOOLBAR),
////            new GraphView(this,TYPE_TOOLBAR)
//        };
//    }

    @Override
    protected DataObject handleCopy(DataFolder df) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void handleDelete() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected FileObject handleRename(String string) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected FileObject handleMove(DataFolder df) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected DataObject handleCreateFromTemplate(DataFolder df, String string) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static class GraphView extends DesignMultiViewDesc {
        private int type;
        GraphView(NeuralNetworkDataObject dObj, int type) {
            //super(dObj, "Design"+String.valueOf(type));
            super(dObj, "Graph");
            this.type=type;
        }
        
        public org.netbeans.core.spi.multiview.MultiViewElement createElement() {
            NeuralNetworkDataObject dObj = (NeuralNetworkDataObject)getDataObject();
            return null;//new GraphToolBarMVElement(dObj);
        }
        
        public java.awt.Image getIcon() {
            return ImageUtilities.loadImage("org/netbeans/modules/NeuralNetworkmultiview/Datasource.gif"); //NOI18N
        }
        
        public String preferredID() {
            return "NeuralNetwork_multiview_design"+String.valueOf(type);
        }
    }
    

    /** Enable to focus specific object in Multiview Editor
     *  The default implementation opens the XML View
     */
    public void showElement(Object element) {
        Object target=null;
//        if (element instanceof Chapter) {
//            openView(0);
//            target=element;
//        }
//        if (target!=null) {
//            final Object key=target;
//            org.netbeans.modules.xml.multiview.Utils.runInAwtDispatchThread(new Runnable() {
//                public void run() {
//                    getActiveMultiViewElement0().getSectionView().openPanel(key);
//                }
//            });
//        }
    }
    
    protected String getPrefixMark() {
        return null;
    }
    
//    /** Enable to get active MultiViewElement object
//     */
//    public ToolBarMultiViewElement getActiveMultiViewElement0() {
//        return (ToolBarMultiViewElement)super.getActiveMultiViewElement();
//    }
//    
//    public void modelUpdatedFromUI() {
//        modelSynchronizer.requestUpdateData();
//    }
    
}
