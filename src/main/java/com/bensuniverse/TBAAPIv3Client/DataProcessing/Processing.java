/*
 *
 * Ben Owen
 * 
 * This class contains the main processing code
 * 
 * Functions:
 * process() - retrieves data from TBA API
 *
 */

package com.bensuniverse.TBAAPIv3Client.DataProcessing;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bensuniverse.TBAAPIv3Client.FileIO.FileTypeSelect;
import com.bensuniverse.TBAAPIv3Client.Frames.CompletedWindow;
import com.bensuniverse.TBAAPIv3Client.Frames.ErrorWindow;
import com.bensuniverse.TBAAPIv3Client.Frames.Panels.OutputLogPanel;
import com.bensuniverse.TBAAPIv3Client.OperatingSystem;

public class Processing {

    private String FILE_LOCATION;
    private String MATCH_ID;
    private String API_KEY;
    private DataType DATA_TYPE;

    private boolean ERROR = false;

    int counter = 0;

    public Processing(String FILE_LOCATION, String MATCH_ID, String API_KEY, DataType DATA_TYPE) {
    	
        this.FILE_LOCATION = FILE_LOCATION;
        this.MATCH_ID = MATCH_ID;
        this.API_KEY = API_KEY;
        this.DATA_TYPE = DATA_TYPE;

    }

    public void process() {
        new Thread(new Runnable() { // to avoid freezing

            public void run() {

                OperatingSystem OS = OperatingSystem.getOperatingSystem();

                long start_time = System.currentTimeMillis();
                
                OutputLogPanel.appendText("Step 1 [Retrieving data]...\n");
                System.out.println("Step 1 [Retrieving data]...");

                Process process = null;
                Scanner s = null;

                // retrieve data using TBA APIv3 to array                
                List<String> input_list = new ArrayList<String>();
                
                if (DATA_TYPE == DataType.MATCH_SCHEDULE || DATA_TYPE == DataType.EVENT_TEAM_LIST) {

	                ProcessBuilder process_builder = new ProcessBuilder();

	                if (OS == OperatingSystem.WINDOWS) {

                        process_builder.command(getCommand(DATA_TYPE).split(" "));

                    } else if (OS == OperatingSystem.LINUX) {

                        process_builder = new ProcessBuilder();
                        process_builder.command("bash", "-c", getCommand(DATA_TYPE));

                    }
	
	                try {

	                    // define where to run curl command
	                    if (OS == OperatingSystem.WINDOWS) {

                            process_builder.directory(new File("C:"));

                        } else if (OS == OperatingSystem.LINUX) {

                            process_builder.directory(new File(System.getProperty("user.home")));

                        } else {

	                        new ErrorWindow("Your operating system is currently not supported!");

                        }

	                    process = process_builder.start(); // run command
	
	                } catch (IOException e) {
	
	                    e.printStackTrace();
	                    
	                }
	
	                InputStream input_stream = process.getInputStream();
	
	                s = new Scanner(input_stream);
	                s.useDelimiter("\\A");
	                String result = s.hasNext() ? s.next() : "";
	
	                String[] input_array = result.split("\n"); // split TBA output to array
	                
	                for (String str : input_array) {
	                	
	                	input_list.add(str);
	                	
	                }
                
                } else if (DATA_TYPE == DataType.COMPLETE_TEAM_LIST) {
                	
                	int current_page = 0;
                	
                	do {

    	                ProcessBuilder process_builder = new ProcessBuilder();

                        if (OS == OperatingSystem.WINDOWS) {

                            process_builder.command(getCommand(DATA_TYPE, current_page).split(" "));

                        } else if (OS == OperatingSystem.LINUX) {

                            process_builder.command("bash", "-c", getCommand(DATA_TYPE, current_page));

                        }
    	
    	                try {

                            // define where to run curl command
                            if (OS == OperatingSystem.WINDOWS) {

                                process_builder.directory(new File("C:"));

                            } else if (OS == OperatingSystem.LINUX) {

                                process_builder.directory(new File(System.getProperty("user.home")));

                            } else {

                                new ErrorWindow("Your operating system is currently not supported!");

                            }

    	                    process = process_builder.start(); // run command
    	
    	                } catch (IOException e) {
    	
    	                    e.printStackTrace();
    	                    
    	                }
    	
    	                InputStream input_stream = process.getInputStream();
    	
    	                s = new Scanner(input_stream);
    	                s.useDelimiter("\\A");
    	                String result = s.hasNext() ? s.next() : "";
    	
    	                String[] input_array = result.split("\n"); // split TBA output to array
    	                
    	                for (String str : input_array) {
    	                	
    	                	input_list.add(str);
    	                	
    	                }
    	                
    	                current_page++;
    	                
                	} while (!input_list.contains("[]"));
                }
                
                for (String str : input_list) {
                	
                	System.out.println(str);
                	
                }

                System.out.println(input_list.get(0));
                if (input_list.get(0).isEmpty()) {

                    new ErrorWindow("Unknown error");

                } else if (input_list.get(0).contains("X-TBA-Auth-Key is invalid")) {

                    new ErrorWindow("Invalid API Key!");

                } else if (input_list.get(0).contains("is not a valid event id") || input_list.get(0).contains("Invalid input")) {

                    new ErrorWindow("Invalid Match ID!");
                    
                } else {

                	OutputLogPanel.appendText("Step 2 [Processing JSON data]...\n");
                    System.out.println("Step 2 [Processing JSON data]...");
                    
                    List<String> final_output = new ArrayList<String>();
                    
                    final_output = OutputSelect.process(input_list, DATA_TYPE);

                    OutputLogPanel.appendText("Step 3 [Writing output to file]...\n");
					
					ERROR = FileTypeSelect.writeFile(FILE_LOCATION, final_output, DATA_TYPE);

                    if (!ERROR) {

                    	OutputLogPanel.appendText("\nFinished!");

                        s.close();

                        long end_time = System.currentTimeMillis();
                        long time = end_time - start_time;

                        new CompletedWindow(input_list.size(), final_output.size(), time);

                    }
                }
            }
        }).start();
    }
    
    private String getCommand(DataType type) {
    	
    	String command = "NV";
    	
    	if (type == DataType.MATCH_SCHEDULE) {
    		
    		command = "curl --ssl-no-revoke -X GET \"https://www.thebluealliance.com/api/v3/event/" + MATCH_ID + "/matches/simple\" -H \"accept: application/json\" -H \"X-TBA-Auth-Key: " + API_KEY + "\"";
    		
    	} else if (type == DataType.EVENT_TEAM_LIST) {
    		
    		command = "curl --ssl-no-revoke -X GET \"https://www.thebluealliance.com/api/v3/event/" + MATCH_ID + "/teams/keys\" -H \"accept: application/json\" -H \"X-TBA-Auth-Key: " + API_KEY + "\"";
    		
    	}
    	
    	return command;
    	
    }
    
    private String getCommand(DataType type, int page_num) {
    	
    	String command = "NV";
    	
    	if (type == DataType.COMPLETE_TEAM_LIST) {
    		
    		command = "curl --ssl-no-revoke -X GET \"https://www.thebluealliance.com/api/v3/teams/" + page_num + "/simple\" -H \"accept: application/json\" -H \"X-TBA-Auth-Key: " + API_KEY + "\"";
    		
    	}
    	
    	return command;
    	
    }
}