/*
 * @(#)SaveAction.java  1.2.1  2006-07-25
 *
 * Copyright (c) 1996-2007 by the original authors of JHotDraw
 * and all its contributors ("JHotDraw.org")
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * JHotDraw.org ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * JHotDraw.org.
 */

package org.jhotdraw.application.action;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import org.jhotdraw.application.*;
import org.jhotdraw.gui.Worker;
import org.jhotdraw.io.*;
import org.jhotdraw.util.*;
import org.jhotdraw.gui.*;
import org.jhotdraw.gui.event.*;

/**
 * SaveAction.
 *
 * @author  Werner Randelshofer
 * @version 1.2.1 2006-07-25 Add saved file to recent file list of application.
 * <br>1.2 2006-05-19 Make filename acceptable by ExtensionFileFilter.
 * <br>1.1 2006-02-23 Support multiple open id.
 * <br>1.0 28. September 2005 Created.
 */
public class SaveAction extends AbstractProjectAction {
    public final static String ID = "save";
    private boolean saveAs;
    private Component oldFocusOwner;
    
    /** Creates a new instance. */
    public SaveAction() {
        this(false);
    }
    /** Creates a new instance. */
    public SaveAction(boolean saveAs) {
        this.saveAs = saveAs;
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.application.Labels");
        labels.configureAction(this, ID);
    }
    
    
    public void actionPerformed(ActionEvent evt) {
        final Project project = getCurrentProject();
        if (project.isEnabled()) {
            oldFocusOwner = SwingUtilities.getWindowAncestor(project.getComponent()).getFocusOwner();
            project.setEnabled(false);
            
            File saveToFile;
            if (!saveAs && project.getFile() != null) {
                saveToFile(project, project.getFile());
            } else {
                JFileChooser fileChooser = project.getSaveChooser();
                
                JSheet.showSaveSheet(fileChooser, project.getComponent(), new SheetListener() {
                    public void optionSelected(final SheetEvent evt) {
                        if (evt.getOption() == JFileChooser.APPROVE_OPTION) {
                            final File file;
                            if (evt.getFileChooser().getFileFilter() instanceof ExtensionFileFilter) {
                                file = ((ExtensionFileFilter) evt.getFileChooser().getFileFilter()).
                                        makeAcceptable(evt.getFileChooser().getSelectedFile());
                            } else {
                                file = evt.getFileChooser().getSelectedFile();
                            }
                            saveToFile(project, file);
                        } else {
                            project.setEnabled(true);
                            if (oldFocusOwner != null) {
                                oldFocusOwner.requestFocus();
                            }
                        }
                    }
                });
            }
        }
    }
    
    protected void saveToFile(final Project project, final File file) {
        project.execute(new Worker() {
            public Object construct() {
                try {
                    project.write(file);
                    return null;
                } catch (IOException e) {
                    return e;
                }
            }
            public void finished(Object value) {
                fileSaved(project, file, value);
            }
        });
    }
    /**
     * XXX - Change type of value to Throwable
     *
     * @param value is either null for success or a Throwable on failure.
     */
    protected void fileSaved(final Project project, File file, Object value) {
        if (value == null) {
            project.setFile(file);
            project.markChangesAsSaved();
            int multiOpenId = 1;
            for (Project p : project.getWindowManager().projects()) {
                if (p != project && p.getFile() != null && p.getFile().equals(file)) {
                    multiOpenId = Math.max(multiOpenId, p.getMultipleOpenId() + 1);
                }
            }
            WindowManager.getInstance().addRecentFile(file);
            project.setMultipleOpenId(multiOpenId);
        } else {
            ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.application.Labels");
            JSheet.showMessageSheet(project.getComponent(),
                    "<html>"+UIManager.getString("OptionPane.css")+
                    labels.getFormatted("couldntSave", file, value),
                    JOptionPane.ERROR_MESSAGE
                    );
        }
        project.setEnabled(true);
        SwingUtilities.getWindowAncestor(project.getComponent()).toFront();
        if (oldFocusOwner != null) {
            oldFocusOwner.requestFocus();
        }
    }
}