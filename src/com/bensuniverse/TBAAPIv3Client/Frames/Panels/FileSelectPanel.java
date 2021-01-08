/*
 * 
 * Ben Owen
 * 
 * This panel contains the text field for the file output path, as well as the panel for the file type dropdown and browse button
 * 
 * Functions:
 * getFilePath() - returns the file path from the text field
 * 
 * setFilePath(String path) - sets the file path
 * 	- String path: value to set the file path to
 * 
 */

package com.bensuniverse.TBAAPIv3Client.Frames.Panels;

import java.awt.GridLayout;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.bensuniverse.TBAAPIv3Client.Configuration;
import com.bensuniverse.TBAAPIv3Client.FileIO.FileType;

public class FileSelectPanel extends JPanel {

    static JTextField file_location_input;

    private static final long serialVersionUID = 1L;

    public FileSelectPanel() {

        this.setLayout(new GridLayout(1, 2, 5, 5)); // grid layout 1 row x 2 columns

        file_location_input = new JTextField(20);

        TitledBorder border = new TitledBorder("Output file location");
        
        file_location_input.setBorder(border);

        Path currentRelativePath = Paths.get("");
        String path = currentRelativePath.toAbsolutePath().toString();
        path += File.separator + "tba_output.xlsx"; // create temporary path to be current directory + "match_schedule.xlsx"
        
        file_location_input.setText(Configuration.readValue("recent_file_location").equals("NV") ? path : Configuration.readValue("recent_file_location")); // set file path = config value

        this.add(file_location_input);
        this.add(new FileSelectPanelRIGHT()); // add secondary panel

        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        DocumentListener documentListener = new DocumentListener() { // if text field changes, write value to config

            public void changedUpdate(DocumentEvent documentEvent) {

            	System.out.println("Output file path changed!");
                writeChanges("recent_file_location", file_location_input.getText());

            }

            public void insertUpdate(DocumentEvent documentEvent) {

            	System.out.println("Output file path changed!");
                writeChanges("recent_file_location", file_location_input.getText());

            }

            public void removeUpdate(DocumentEvent documentEvent) {

                System.out.println("Output file path changed!");
                writeChanges("recent_file_location", file_location_input.getText());

            }
            
            public void writeChanges(String id, String value) {
            	
            	if (value.length() >= 5) {
	            	
	            	Configuration.writeValue(id, value);
	            	
	            	if (value.substring(value.length() - 4).equalsIgnoreCase(".txt")) {
	            		
	            		FileSelectPanelRIGHT.setFileTypeSelected(FileType.TXT); // set dropdown to TXT if path ends in ".txt"
	            		TeamNumberProgressBarPanel.setTeamNumberEnabled(false);
	            		
	            	}
	            	
	            	if (value.substring(value.length() - 5).equalsIgnoreCase(".xlsx")) {
	            		
	            		FileSelectPanelRIGHT.setFileTypeSelected(FileType.XLSX); // set dropdown to XLSX if path ends in ".xlsx"
	            		TeamNumberProgressBarPanel.setTeamNumberEnabled(true);
	            		
	            	}
	            	
	            	if (value.substring(value.length() - 4).equalsIgnoreCase(".xls")) {
	            		
	            		FileSelectPanelRIGHT.setFileTypeSelected(FileType.XLS); // set dropdown to XLS if path ends in ".xls"
	            		TeamNumberProgressBarPanel.setTeamNumberEnabled(true);
	            		
	            	}
	            	
            	}
            	
            }
        };
        file_location_input.getDocument().addDocumentListener(documentListener);

        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public static String getFilePath() {

        return file_location_input.getText();

    }

    public static void setFilePath(String path) {

    	try {
        
    		file_location_input.setText(path);

    	} catch (IllegalStateException e) { // problems due to multiple action listeners causing loop
    		
    		// ignore error (expected)
    		
    	}
    }
}