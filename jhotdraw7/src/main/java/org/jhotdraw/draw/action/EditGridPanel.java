/*
 * @(#)EditGridPanel.java  2.0  2007-12-22
 *
 * Copyright (c) 2007 by the original authors of JHotDraw
 * and all its contributors ("JHotDraw.org")
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * JHotDraw.org ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * JHotDraw.org.
 */

package org.jhotdraw.draw.action;

import java.beans.*;
import java.text.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import org.jhotdraw.draw.*;
import org.jhotdraw.util.*;

/**
 * The EditGridPanel can be used to edit the properties of
 * a GridConstrainer.
 * 
 * @see org.jhotdraw.draw.GridConstrainer
 *
 * @author Werner Randelshofer
 * @version 2.0 2007-12-22 Added support for angle constraint. 
 * <br>1.0 July 31, 2007 Created.
 */
public class EditGridPanel extends javax.swing.JPanel {
    private ResourceBundleUtil labels;
    private GridConstrainer constrainer;
    
    /** Creates new instance. */
    public EditGridPanel() {
        labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
        initComponents();
        
        widthField.setMinimum(0);
        widthField.setMaximum(1000);
        heightField.setMinimum(0);
        heightField.setMaximum(1000);
        thetaField.setMinimum(0);
        thetaField.setMaximum(180);
        
        constrainer = new GridConstrainer(10, 10);
        
        widthField.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("value")) {
                    if (evt.getNewValue() != null) {
                        constrainer.setWidth((Double) evt.getNewValue());
                    }
                }
            }
        });
        
        heightField.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("value")) {
                    if (evt.getNewValue() != null) {
                        constrainer.setHeight((Double) evt.getNewValue());
                    }
                }
            }
        });
        
        thetaField.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("value")) {
                    if (evt.getNewValue() != null) {
                        constrainer.setTheta((Double) evt.getNewValue() * Math.PI / 180d);
                    }
                }
            }
        });
        
    }
    
    /**
     * Sets the GridConstrainer to be edited by this panel.
     * 
     * @param newValue The GridConstrainer.
     */
    public void setConstrainer(GridConstrainer newValue) {
        constrainer = newValue;
        widthField.setValue(constrainer.getWidth());
        heightField.setValue(constrainer.getHeight());
        thetaField.setValue(constrainer.getTheta() * 180d / Math.PI);
    }
    
    /**
     * Returns the GridConstrainer currently being edited by this panel.
     * 
     * @return The GridConstrainer.
     */
    public GridConstrainer getConstrainer() {
        return constrainer;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        widthLabel = new javax.swing.JLabel();
        heightLabel = new javax.swing.JLabel();
        widthField = new org.jhotdraw.gui.JDoubleTextField();
        heightField = new org.jhotdraw.gui.JDoubleTextField();
        thetaLabel = new javax.swing.JLabel();
        thetaField = new org.jhotdraw.gui.JDoubleTextField();

        widthLabel.setText(labels.getString("grid.width")); // NOI18N

        heightLabel.setText(labels.getString("grid.height")); // NOI18N

        widthField.setColumns(5);

        heightField.setColumns(5);

        thetaLabel.setText(labels.getString("grid.theta")); // NOI18N

        thetaField.setColumns(5);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, heightLabel)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, widthLabel)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, thetaLabel))
                .add(4, 4, 4)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(thetaField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(heightField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(widthField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(widthLabel)
                    .add(widthField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(heightLabel)
                    .add(heightField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(thetaLabel)
                    .add(thetaField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jhotdraw.gui.JDoubleTextField heightField;
    private javax.swing.JLabel heightLabel;
    private org.jhotdraw.gui.JDoubleTextField thetaField;
    private javax.swing.JLabel thetaLabel;
    private org.jhotdraw.gui.JDoubleTextField widthField;
    private javax.swing.JLabel widthLabel;
    // End of variables declaration//GEN-END:variables
    
}