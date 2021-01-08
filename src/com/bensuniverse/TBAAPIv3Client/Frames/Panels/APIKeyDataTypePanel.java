/*
 * 
 * Ben Owen
 * 
 * This panel contains the text field for the TBA APIv3 key and the dropdown for the data to retrieve
 * 
 * Functions:
 * getAPIKey() - returns the API key from the text field
 * getDataType() - returns the type of data to fetch
 * 
 */

package com.bensuniverse.TBAAPIv3Client.Frames.Panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.bensuniverse.TBAAPIv3Client.Configuration;

public class APIKeyDataTypePanel extends JPanel {
	
	private static DataType[] values;
	private static String[] values_str;

    static JTextField api_key;
    
    static JComboBox<String> data_type_select;

    private static final long serialVersionUID = 1L;

    public APIKeyDataTypePanel() {

        this.setLayout(new GridLayout(1, 2, 5, 5)); // create layout 2 columns x 1 row

        api_key = new JTextField(16);

        TitledBorder api_key_border = new TitledBorder("TBA API key");
        api_key.setBorder(api_key_border);
        
        values = DataType.values();
        values_str = new String[DataType.values().length];
        for (int i = 0; i < values.length; i++) {
        	
        	values_str[i] = values[i].getName();
        	
        }
        data_type_select = new JComboBox<String>(values_str);
        
        TitledBorder data_type_border = new TitledBorder("Data to Export");
        data_type_select.setBorder(data_type_border);

        this.add(api_key);
        this.add(data_type_select);
        
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        data_type_select.setSelectedItem((Configuration.readValue("data_type").equals("NV")) ? DataType.MATCH_SCHEDULE.getName()
        		: DataType.valueOf(Configuration.readValue("data_type")).getName()); // set data type dropdown = config value
        api_key.setText((Configuration.readValue("api_key").equals("NV")) ? "" : Configuration.readValue("api_key")); // set API key text field = config value
        
        data_type_select.addActionListener(new ActionListener() { // check for changes to dropdown

			public void actionPerformed(ActionEvent e) {
				
				if (getDataType() == DataType.COMPLETE_TEAM_LIST) {

					MatchIDPanel.setMatchIDEnabled(false);
					MatchIDPanel.setMatchID("");
					
				} else {
					
					MatchIDPanel.setMatchIDEnabled(true);
					
				}
				
				Configuration.writeValue("data_type", getDataType().name());

			}
		});
        
        DocumentListener documentListener = new DocumentListener() { // if text field changes, write new value to config

            public void changedUpdate(DocumentEvent documentEvent) {

            	System.out.println("API key changed!");
                Configuration.writeValue("api_key", getAPIKey());

            }

            public void insertUpdate(DocumentEvent documentEvent) {

            	System.out.println("API key changed!");
                Configuration.writeValue("api_key", getAPIKey());

            }

            public void removeUpdate(DocumentEvent documentEvent) {

                System.out.println("API key changed!");
                Configuration.writeValue("api_key", getAPIKey());

            }
        };
        api_key.getDocument().addDocumentListener(documentListener);

        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public static String getAPIKey() {

        return api_key.getText();
        
    }
    
    public static DataType getDataType() {
    	
    	for (int i = 0; i < values_str.length; i++) {
    		
    		if (((String) data_type_select.getSelectedItem()).equals(values_str[i])) // return DataType that corresponds to selected item
    			return values[i];
    		
    	}
    	
		return null;
    }
    
    public static void refresh_data_type_select() {
    	
    	if (getDataType() == DataType.COMPLETE_TEAM_LIST) {

    		MatchIDPanel.setMatchIDEnabled(false);
    		MatchIDPanel.setMatchID("");
			
		} else {
			
			MatchIDPanel.setMatchIDEnabled(true);
			
		}
		
		Configuration.writeValue("data_type", getDataType().name());
		
    }
}