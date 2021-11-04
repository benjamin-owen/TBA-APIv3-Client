/*
 *
 * Ben Owen
 * 
 * This window contains the successful processing message
 *
 */

package com.bensuniverse.TBAAPIv3Client.Frames;

import javax.swing.*;

import com.bensuniverse.TBAAPIv3Client.Frames.Panels.MatchIDPanel;
import com.bensuniverse.TBAAPIv3Client.Frames.Panels.TeamNumberProgressBarPanel;

public class CompletedWindow extends JOptionPane {

    private static final long serialVersionUID = 1L;

    public CompletedWindow(int total_records, int found_records, long time) {
        
        double timeSeconds = (time / 1000.0);
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/tba_logo_success.png"));

        String message = "Found " + found_records + " entries from " + total_records + " total lines in " + timeSeconds + " seconds."; // display information
        System.out.println(message);
        JOptionPane.showMessageDialog(MainWindow.getFrames()[0], message, "Completed!", JOptionPane.INFORMATION_MESSAGE, icon);

        TeamNumberProgressBarPanel.setProgressBarValue(0); // reset progress bar
        MatchIDPanel.setMatchID(""); // reset match ID
        
        MatchIDPanel.setStartButtonEnabled(true); // enable start button
        MatchIDPanel.focus(); // request focus to match ID text field

        this.setVisible(true);

    }
}