/*
 *
 * Ben Owen
 * 
 * This panel contains the text field for the team to make bold, as well as the progress bar
 * 
 * Functions:
 * getProgressBar() - returns the progress bar object
 * 
 * setProgressBarMaximum(int max) - sets the size of the progress bar
 * 	- int max: determines the size to make the progress bar maximum
 * 
 * setProgressBarValue(int value) - sets the current position of the progress bar
 * 	- int value: determines what to set the progress bar to
 * 
 * setTeamNumberEnabled(boolean enabled) - sets the team number text field enabled or not
 * 	- boolean enabled: determines whether to enable text field
 * 
 */

package com.bensuniverse.TBAAPIv3Client.Frames.Panels;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.bensuniverse.TBAAPIv3Client.Configuration;
import com.bensuniverse.TBAAPIv3Client.Frames.ErrorWindow;
import com.bensuniverse.TBAAPIv3Client.Frames.Theme;
import com.bensuniverse.TBAAPIv3Client.Main;

public class TeamNumberProgressBarPanel extends JPanel {
    
    private static JTextField bold_team;
    private static JProgressBar progress_bar;

    private static final long serialVersionUID = 1L;

    public TeamNumberProgressBarPanel() {
        
        this.setLayout(new GridLayout(1, 2, 5, 5)); // grid layout 1 row x 2 columns
        
        bold_team = new JTextField(16);

        TitledBorder border = new TitledBorder("Team number (bold in Excel file)");
        
        bold_team.setBorder(border);
        
        bold_team.setText((Configuration.readValue("team_number").equals("NV")) ? "" : Configuration.readValue("team_number")); // set value of bold team text field = config value

        progress_bar = new JProgressBar();
        progress_bar.setValue(0);
        progress_bar.setStringPainted(true);
        
        this.add(bold_team);
        this.add(progress_bar);

        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        DocumentListener documentListener = new DocumentListener() { // if text field changes, write value to config

            public void changedUpdate(DocumentEvent documentEvent) {

                System.out.println("Team number changed!");
                Configuration.writeValue("team_number", bold_team.getText());

            }

            public void insertUpdate(DocumentEvent documentEvent) {

            	System.out.println("Team number changed!");
                Configuration.writeValue("team_number", bold_team.getText());

            }

            public void removeUpdate(DocumentEvent documentEvent) {

            	System.out.println("Team number changed!");
                Configuration.writeValue("team_number", bold_team.getText());

            }
        };
        bold_team.getDocument().addDocumentListener(documentListener);

    }
    
    public static int getTeam() {
    	
    	Integer bold_team_number = -1;
    	
    	try {

    	    if (!bold_team.getText().equals(""))
    		    bold_team_number = Integer.parseInt(bold_team.getText()); // return team number if valid (no characters)
    		
    	} catch (NumberFormatException e) {

    	    e.printStackTrace();
    		new ErrorWindow("Invalid team number!");
    		
    	}
    	
    	return (bold_team_number > 0) ? bold_team_number : -1; // still write file, but make sure to not bold any teams
    	
    }

    public JProgressBar getProgressBar() {

        return progress_bar;

    }

    public static void setProgressBarMaximum(int max) {

        progress_bar.setMaximum(max);
        
    }

    public static void setProgressBarValue(int value) {

        progress_bar.setValue(value);

    }
    
    public static void setTeamNumberEnabled(boolean enabled) {
    	
    	bold_team.setEditable(enabled);
    	
    }
}