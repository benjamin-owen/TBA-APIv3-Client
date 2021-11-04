/*
 *
 * Ben Owen
 * 
 * This window creates a popup for error messages
 *
 */

package com.bensuniverse.TBAAPIv3Client.Frames;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.bensuniverse.TBAAPIv3Client.Frames.Panels.MatchIDPanel;
import com.bensuniverse.TBAAPIv3Client.Frames.Panels.OutputLogPanel;
import com.bensuniverse.TBAAPIv3Client.Frames.Panels.TeamNumberProgressBarPanel;

public class ErrorWindow extends JOptionPane {

    private static final long serialVersionUID = 1L;

    public ErrorWindow(String error_message) {

        System.out.println("ERROR! [" + error_message + "]");
        OutputLogPanel.appendText("ERROR! [" + error_message + "]"); // send error message to output log

        ImageIcon icon = new ImageIcon(getClass().getResource("/tba_logo_error.png"));
        
        error_message = "  " + error_message + "  ";
        JOptionPane.showMessageDialog(MainWindow.getFrames()[0], error_message, "Error!", JOptionPane.INFORMATION_MESSAGE, icon);

        TeamNumberProgressBarPanel.setProgressBarValue(0); // reset progress bar
        MatchIDPanel.setMatchID(""); // reset match ID text field
        
        MatchIDPanel.setStartButtonEnabled(true); // enable start button
        MatchIDPanel.focus(); // request focus to match ID text field

        this.setVisible(true);

    }
}