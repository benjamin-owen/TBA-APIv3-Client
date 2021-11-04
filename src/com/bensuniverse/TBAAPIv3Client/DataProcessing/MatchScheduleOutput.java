/*
 *
 * Ben Owen
 * 
 * This class contains the processing code for the match schedule list output
 * 
 * Functions:
 * process() - retrieves data and returns final output
 *
 */

package com.bensuniverse.TBAAPIv3Client.DataProcessing;

import com.bensuniverse.TBAAPIv3Client.Frames.ErrorWindow;
import com.bensuniverse.TBAAPIv3Client.Frames.Panels.OutputLogPanel;
import com.bensuniverse.TBAAPIv3Client.Frames.Panels.TeamNumberProgressBarPanel;

import java.util.ArrayList;
import java.util.List;

public class MatchScheduleOutput {
	
	public List<String> process(List<String> input_data) {
		
        String matchno, team1, team2, team3, team4, team5, team6;
        matchno = team1 = team2 = team3 = team4 = team5 = team6 = "NV";
        
        List<String> output = new ArrayList<>();
		
        TeamNumberProgressBarPanel.setProgressBarMaximum(input_data.size());
        System.out.println("Length of array: " + input_data.size());
        OutputLogPanel.appendText("Length of array: " + input_data.size() + " lines\n");

        for (String str : input_data) {

            System.out.print(str + ";");

        }

        if (input_data.size() > 1) { // input array contains values
            
            for (int i = 0; i < input_data.size(); i++) {

                if (input_data.get(i).contains("frc")) { // most likely a team number

                	// each line takes the number after "frc" and adds it to the team number, as long as the previous values have been filled
                    team6 = (!team1.equals("NV") && !team2.equals("NV") && !team3.equals("NV") && !team4.equals("NV") && !team5.equals("NV") && team6.equals("NV")) ? input_data.get(i).substring(input_data.get(i).indexOf("frc") + 3, input_data.get(i).indexOf("\"", input_data.get(i).indexOf("\"") + 1)) : team6;
                    team5 = (!team1.equals("NV") && !team2.equals("NV") && !team3.equals("NV") && !team4.equals("NV") && team5.equals("NV")) ? input_data.get(i).substring(input_data.get(i).indexOf("frc") + 3, input_data.get(i).indexOf("\"", input_data.get(i).indexOf("\"") + 1)) : team5;
                    team4 = (!team1.equals("NV") && !team2.equals("NV") && !team3.equals("NV") && team4.equals("NV")) ? input_data.get(i).substring(input_data.get(i).indexOf("frc") + 3, input_data.get(i).indexOf("\"", input_data.get(i).indexOf("\"") + 1)) : team4;
                    team3 = (!team1.equals("NV") && !team2.equals("NV") && team3.equals("NV")) ? input_data.get(i).substring(input_data.get(i).indexOf("frc") + 3, input_data.get(i).indexOf("\"", input_data.get(i).indexOf("\"") + 1)) : team3;
                    team2 = (!team1.equals("NV") && team2.equals("NV")) ? input_data.get(i).substring(input_data.get(i).indexOf("frc") + 3, input_data.get(i).indexOf("\"", input_data.get(i).indexOf("\"") + 1)) : team2;
                    team1 = (team1.equals("NV")) ? input_data.get(i).substring(input_data.get(i).indexOf("frc") + 3, input_data.get(i).indexOf("\"", input_data.get(i).indexOf("\"") + 1)) : team1;

                }

                if (input_data.get(i).contains("key")
                		&& input_data.get(i).contains("qm")
                		&& !team1.equals("NV")
                		&& !team2.equals("NV")
                		&& !team3.equals("NV")
                		&& !team4.equals("NV")
                		&& !team5.equals("NV")
                		&& !team6.equals("NV")) { // all team numbers are filled and index value has "qm" (most likely a match number)

                    matchno = input_data.get(i).substring(input_data.get(i).indexOf("qm") + 2, input_data.get(i).indexOf(",") - 1);

                    output.add(matchno + ";" + team4 + ";" + team5 + ";" + team6 + ";" + team1 + ";" + team2 + ";" + team3); // add processed data to output array
                    
                    matchno = team1 = team2 = team3 = team4 = team5 = team6 = "NV"; // reset all variables to default values

                }

                if (input_data.get(i).contains("actual_time")) { // end of current JSON entry

                    matchno = team1 = team2 = team3 = team4 = team5 = team6 = "NV"; // reset all variables to default values

                }

                TeamNumberProgressBarPanel.setProgressBarValue(i + 1);

            }
            
			List<String> final_output = new ArrayList<>();
			final_output.add(output.get(0));
			
			int current_match = 1;
			
			System.out.println("\ntest start");
			
			while (current_match < output.size()) {
				
			    for (int i = 1; i < output.size(); i++) {
			    	
			    	// the following code looks for the next qualification match and adds the data to the final output, effectively sorting the array by match number
			    	System.out.println("Raw input: " + output.get(i));
			    	System.out.println("Current match found: " + Integer.parseInt(output.get(i).substring(0, output.get(i).indexOf(';'))));
			    	System.out.println("Looking for: " + (Integer.parseInt(final_output.get(current_match - 1).substring(0, final_output.get(current_match - 1).indexOf(';'))) + 1));
			    	
			    	if (Integer.parseInt(output.get(i).substring(0, output.get(i).indexOf(';'))) == Integer.parseInt(final_output.get(current_match - 1).substring(0, final_output.get(current_match - 1).indexOf(';'))) + 1) {
			    		
			    		final_output.add(output.get(i));
			    		current_match++;
			    		
			    	}
			    }
			}

			for (String str : output) {

			    System.out.println(str);

			}

			System.out.println("\n\n");
			
			for (String str : final_output) {

			    System.out.println(str);

			}
			
	        return final_output;
	        
        } else {

        	TeamNumberProgressBarPanel.setProgressBarValue(0);
            new ErrorWindow("No match data found!");

        }
        
		return null;
	}
}
