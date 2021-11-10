/*
 * 
 * Ben Owen
 * 
 * This panel contains the footer label with author, version number, and date
 * 
 */

package com.bensuniverse.TBAAPIv3Client.Frames.Panels;

import com.bensuniverse.TBAAPIv3Client.Main;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FooterPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public FooterPanel() {

        // pull information from Main.java
        JLabel main_label = new JLabel("Version " + Main.getVersion() + " | " +
                Main.getAuthor() + " | " + Main.getDate());

        this.add(main_label);

        // padding to look good
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    }
}