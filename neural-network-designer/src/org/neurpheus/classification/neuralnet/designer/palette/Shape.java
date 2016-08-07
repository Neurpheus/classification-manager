/*
 * Shape.java
 *
 * Created on September 21, 2006, 9:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 *
 * To understand this class, see http://platform.netbeans.org/tutorials/nbm-nodesapi3.html
 */

package org.neurpheus.classification.neuralnet.designer.palette;

import java.awt.Image;

/**
 *
 * @author Geertjan Wielenga
 */
public class Shape {

    public static final int SHAPE_INPUT_LAYER = 1;
    public static final int SHAPE_OUTPUT_LAYER = 2;
    public static final int SHAPE_FULL_SYNAPSE = 3;
    public static final int SHAPE_DIRECT_SYNAPSE = 4;
    public static final int SHAPE_LINEAR_LAYER = 5;
    public static final int SHAPE_LOG_LAYER = 6;
    public static final int SHAPE_SIGMOID_LAYER = 7;
    public static final int SHAPE_TANH_LAYER = 8;
    public static final int SHAPE_SOFTMAX_LAYER = 9;
    
    private Integer number;
    private String category;
    private String imagePath;
    private Image image;

    /** Creates a new instance of Instrument */
    public Shape() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String image) {
        this.imagePath = image;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    

}