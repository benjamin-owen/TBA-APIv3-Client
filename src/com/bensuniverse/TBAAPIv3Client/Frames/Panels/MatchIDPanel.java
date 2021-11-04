/*
 *
 * Ben Owen
 * 
 * This panel contains the match ID text field
 * 
 * Functions:
 * getStartButton() - returns start button object
 * 
 * setStartButtonEnabled(boolean enabled) - sets if start button is enabled
 * 	- boolean enabled: enable button (true / false)
 * 
 * focus() - requests focus for the match ID text field
 * 
 * getMatchID() - returns the match ID from text field
 * 
 * setMatchID(String text) - sets the match ID text field value
 * 	- String text: value to set the match ID text field to
 * 
 * invalidCharacters(String id) - returns if "id" contains any invalid characters
 * 	- id: String to check for invalid characters
 *
 */

package com.bensuniverse.TBAAPIv3Client.Frames.Panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import com.bensuniverse.TBAAPIv3Client.DataProcessing.DataType;
import com.bensuniverse.TBAAPIv3Client.DataProcessing.Processing;
import com.bensuniverse.TBAAPIv3Client.Frames.ErrorWindow;

public class MatchIDPanel extends JPanel {

    private static JButton start_button;
    private static JTextField match_ID_input;

    private static final long serialVersionUID = 1L;

    public MatchIDPanel() {

        this.setLayout(new GridLayout(1, 1, 5, 5)); // grid layout 1 row x 1 column

        match_ID_input = new JTextField(16);

        TitledBorder border = new TitledBorder("Match ID");
        
        match_ID_input.setBorder(border);

        start_button = new JButton("Start");
        start_button.addActionListener(new ActionListener() { // check if start button was clicked
            public void actionPerformed(ActionEvent e) {
            	
            	setStartButtonEnabled(false); // disable start button

                String MATCH_ID = match_ID_input.getText();
                OutputLogPanel.clear(); // clear output panel

                if (!MATCH_ID.equals("") || APIKeyDataTypePanel.getDataType() == DataType.COMPLETE_TEAM_LIST) { // if match ID contains a value

                    if (!invalidCharacters(MATCH_ID)) { // if the match ID doesn't contain invalid characters
                    	
                    	String temp_path = FileSelectPanel.getFilePath();
                    	
                    	if (temp_path.substring(temp_path.length() - 4).equalsIgnoreCase(".txt")
                                || temp_path.substring(temp_path.length() - 4).equalsIgnoreCase(".csv")
                    			|| temp_path.substring(temp_path.length() - 5).equalsIgnoreCase(".xlsx")
                    			|| temp_path.substring(temp_path.length() - 4).equalsIgnoreCase(".xls")) { // if file path contains correct extension

                    		new Processing(FileSelectPanel.getFilePath(), MATCH_ID, APIKeyDataTypePanel.getAPIKey(), APIKeyDataTypePanel.getDataType()).process(); // process data
                        
                    	} else {
                    		
                    		new ErrorWindow("Invalid output file extension!");
                    		
                    	}

                    } else {

                        new ErrorWindow("Invalid Match ID!");

                    }
                    
                } else {

                    new ErrorWindow("Match ID must contain a value!");

                }
            }
        });
        
        APIKeyDataTypePanel.refresh_data_type_select();

        this.add(match_ID_input);
        this.add(start_button);

        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    }
    
    public static JButton getStartButton() {
    	
    	return start_button;
    	
    }
    
    public static void setStartButtonEnabled(boolean enabled) {
    	
    	start_button.setEnabled(enabled);
    	
    }
    
    public static void focus() {
    	
    	match_ID_input.requestFocus();
    	
    }

    public String getMatchID() {

        return match_ID_input.getText();

    }

    public static void setMatchID(String text) {

        match_ID_input.setText(text);

    }
    
    public static void setMatchIDEnabled(boolean enabled) {
    	
    	match_ID_input.setEditable(enabled);
    	
    }

    private boolean invalidCharacters(String id) {

        String[] invalidChars = { "{", "}", "[", "]", "(", ")", "#", "|", "/", "\\", "'", "\"", "?", "%" };

        return Arrays.stream(invalidChars).parallel().anyMatch(id::contains); // check for invalid characters from above array

    }
}