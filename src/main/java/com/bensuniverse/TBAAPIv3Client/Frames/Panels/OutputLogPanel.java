/*
 *
 * Ben Owen
 * 
 * This panel contains the output log
 * 
 * Functions:
 * clear() - clear output log
 * 
 * appendText(String text) - adds text to output log
 * 	- String text: text to append
 *
 */

package com.bensuniverse.TBAAPIv3Client.Frames.Panels;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class OutputLogPanel extends JPanel {

    private static JTextArea output_log;

    private static final long serialVersionUID = 1L;

    public OutputLogPanel() {

        this.setLayout(new GridLayout(1, 1, 5, 5)); // grid layout 1 row x 1 column

        output_log = new JTextArea(6, 40);
        output_log.setEditable(false);
        output_log.setLineWrap(true);

        TitledBorder border = new TitledBorder("Output log");

        output_log.setBorder(border);

        this.add(output_log);

        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    }

    public static void clear() {

        output_log.selectAll();
        output_log.replaceSelection("");

    }

    public static void appendText(String text) {

        SwingUtilities.invokeLater(new Runnable() { // to avoid freezing
        	
            public void run() {
            	
                output_log.append(text);
                
            }
        });
    }
}