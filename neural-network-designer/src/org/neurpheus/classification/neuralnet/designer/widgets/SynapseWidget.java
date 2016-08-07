/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.neurpheus.classification.neuralnet.designer.widgets;

import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.general.IconNodeWidget;

/**
 *
 * @author jstrychowski
 */
public class SynapseWidget extends IconNodeWidget {
    
    public SynapseWidget(Scene scene) {
        super(scene);
        getLabelWidget().setVisible(false);
    }

}
