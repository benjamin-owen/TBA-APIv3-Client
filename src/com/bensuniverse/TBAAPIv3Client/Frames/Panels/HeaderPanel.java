/*
 * 
 * Ben Owen
 * 
 * This panel contains the header label with the title of the program
 * 
 */

package com.bensuniverse.TBAAPIv3Client.Frames.Panels;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HeaderPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public HeaderPanel() {

        JLabel main_label = new JLabel("TBA APIv3 Client", SwingConstants.CENTER);

        this.add(main_label);
        
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    }
}