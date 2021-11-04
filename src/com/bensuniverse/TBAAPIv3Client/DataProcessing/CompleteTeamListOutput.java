/*
 *
 * Ben Owen
 * 
 * This class contains the processing code for the complete team list output
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

public class CompleteTeamListOutput {
	
	public List<String> process(List<String> input_data) {
		
		String city, country, name, nickname, state, number;
		city = country = name = nickname = state = number = "NV";
        
        List<String> output = new ArrayList<>();
		
        TeamNumberProgressBarPanel.setProgressBarMaximum(input_data.size());
        System.out.println("Length of array: " + input_data.size());
        OutputLogPanel.appendText("Length of array: " + input_data.size() + " lines\n");

        for (String str : input_data) {

            System.out.println(str + ";");

        }

        if (input_data.size() > 1) { // input array contains values
        	
            for (int i = 0; i < input_data.size(); i++) {

            	System.out.println(i + " | " + input_data.get(i));
            	
            	if (input_data.get(i).contains("{")) city = country = name = nickname = state = number = "NV";
            	
            	try {
            		// substring information within "city" field
            		if (input_data.get(i).contains("city") && city.equals("NV"))
            			city = input_data.get(i).substring(input_data.get(i).indexOf(":") + 3,
								input_data.get(i).indexOf(",") - 1);
				} catch (StringIndexOutOfBoundsException e) { city = ""; } // no city value found; leave blank

				try {
					// substring information within "country" field
					if (input_data.get(i).contains("country") && country.equals("NV"))
						country = input_data.get(i).substring(input_data.get(i).indexOf(":") + 3,
								input_data.get(i).indexOf(",") - 1);
				} catch (StringIndexOutOfBoundsException e) { country = ""; } // no country value found; leave blank

				try {
					// substring information within "name" field
					if (input_data.get(i).contains("name") && name.equals("NV"))
						name = input_data.get(i).substring(input_data.get(i).indexOf(":") + 3,
							input_data.get(i).indexOf(",") - 1);
				} catch (StringIndexOutOfBoundsException e) { name = ""; } // no name value found; leave blank

				try {
					// substring information within "nickname" field
					if (input_data.get(i).contains("nickname") && nickname.equals("NV"))
						nickname = input_data.get(i).substring(input_data.get(i).indexOf(":") + 3,
							input_data.get(i).indexOf(",") - 1);
				} catch (StringIndexOutOfBoundsException e) { nickname = ""; } // no nickname value found; leave blank

				try {
					// substring information within "state_prov" field
					if (input_data.get(i).contains("state_prov") && state.equals("NV"))
						state = input_data.get(i).substring(input_data.get(i).indexOf(":") + 3,
								input_data.get(i).indexOf(",") - 1);
				} catch (StringIndexOutOfBoundsException e) { state = ""; } // no state value found; leave blank

				try {
					// substring information within "team_number" field
					if (input_data.get(i).contains("team_number") && number.equals("NV"))
						number = input_data.get(i).substring(input_data.get(i).indexOf(":") + 2);
				} catch (StringIndexOutOfBoundsException e) { number = ""; } // no team number value found; leave blank
        		
            	if (input_data.get(i).contains("}")) { // end of entry
        			
            		if (!city.equals("u") && !state.equals("u") && !country.equals("u"))
            			output.add(number + ";" + nickname + ";" + city + ", " + state + ", " + country + ";" + name);
            		
        			city = country = name = nickname = state = number = "NV";
            		
        		}

                TeamNumberProgressBarPanel.setProgressBarValue(i + 1);

            }

			List<String> final_output = output;

			for (String str : output) {

			    System.out.println(str);

			}
			
			System.out.println();
			System.out.println();
			System.out.println();
			
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
