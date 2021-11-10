/*
 *
 * Ben Owen
 * 
 * This class contains the processing code for the event team list output
 * 
 * Functions:
 * process() - retrieves data and returns final output
 *
 */

package com.bensuniverse.TBAAPIv3Client.DataProcessing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bensuniverse.TBAAPIv3Client.Frames.ErrorWindow;
import com.bensuniverse.TBAAPIv3Client.Frames.Panels.OutputLogPanel;
import com.bensuniverse.TBAAPIv3Client.Frames.Panels.TeamNumberProgressBarPanel;

public class EventTeamListOutput {
	
	public List<String> process(List<String> input_data) {
		
		List<Integer> team_list = new ArrayList<Integer>();        
        List<String> final_output = new ArrayList<String>();
		
		TeamNumberProgressBarPanel.setProgressBarMaximum(input_data.size());
        System.out.println("Length of array: " + input_data.size());
        OutputLogPanel.appendText("Length of array: " + Integer.toString(input_data.size()) + " lines\n");

        for (String str : input_data) {

            System.out.print(str + ";");

        }

        if (input_data.size() > 1) { // input array contains values
        	
        	for (int i = 0; i < input_data.size(); i++) {
        		
        		String str = input_data.get(i);
        		
        		if (str.contains("frc")) {
        			
    				team_list.add(Integer.parseInt(str.substring((str.indexOf("\"") + 4), str.indexOf("\"", (str.indexOf("\"") + 1)))));
    				TeamNumberProgressBarPanel.setProgressBarValue(i);
    				
    			}
        	}
    		
    		Collections.sort(team_list);
    		
    		for (Integer i : team_list) {
    			
    			final_output.add(i.toString());
    			
    		}
    		
    		System.out.println();
    		System.out.println();
    		System.out.println("START");
    		
    		for (String str : final_output) {
    			
    			System.out.println(str);
    			
    		}
    		
    		System.out.println("END");
			
	        return final_output;
	        
        } else {

        	TeamNumberProgressBarPanel.setProgressBarValue(0);
            new ErrorWindow("No team list data found!");

        }
        
		return null;
	}
}
