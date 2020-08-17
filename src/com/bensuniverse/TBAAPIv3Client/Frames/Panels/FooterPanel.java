/*
 * 
 * Ben Owen
 * 
 * This panel contains the footer label with author, version number, and date
 * 
 */

package com.bensuniverse.TBAAPIv3Client.Frames.Panels;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class FooterPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public FooterPanel() {

        super(true);

        JLabel main_label = new JLabel("Version 2.0 | Ben Owen | 2020-08-16", SwingConstants.CENTER);

        this.add(main_label);
        
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}