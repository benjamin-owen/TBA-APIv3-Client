/*
 *
 * Ben Owen
 * 
 * This class contains all of the functions for the config file
 * 
 * Functions:
 * createConfig() - called at runtime, creates initial config if necessary
 * 
 * createInitialConfig(boolean create) - used to create the initial config or append new values (if new fields have been added)
 * 	- boolean create: determines whether to create a completely new config or only append new values
 * 
 * readValue(String id) - returns config value for "id"
 * 	- String id: ID to get config value from
 * 
 * writeValue(String id, String value) - writes a config value for
 * 	- String id - ID to write config value for
 * 	- String value - value to write in config
 *
 */

package com.bensuniverse.TBAAPIv3Client;

import com.bensuniverse.TBAAPIv3Client.Frames.ErrorWindow;

import java.io.*;

public class Configuration {

    private static OperatingSystem OS = OperatingSystem.getOperatingSystem();

    // folder and file path for configuration file
    private static File CONFIG_DIR;
    private static File CONFIG;

    public static void createConfig() {

        if (OS == OperatingSystem.WINDOWS) {

            // AppData folder
            CONFIG_DIR = new File(System.getProperty("user.home") + "/Application Data/TBAAPIv3Client"); // file path for config file
            CONFIG = new File(CONFIG_DIR + "/configuration.txt");

        } else if (OS == OperatingSystem.LINUX) {

            // home folder
            CONFIG_DIR = new File(System.getProperty("user.home") + "/.config/tbaapiv3client"); // file path for config file
            CONFIG = new File(CONFIG_DIR + "/configuration.txt");

        } else if (OS == OperatingSystem.MAC) {

            // $HOME/Library/Containers folder
            CONFIG_DIR = new File(System.getProperty("user.home") + "/Library/Containers/com.bensuniverse.tbaapiv3client"); // file path for config file
            CONFIG = new File(CONFIG_DIR + "/configuration.txt");

        } else {

            // unknown file structures
            new ErrorWindow("Your operating system is not currently supported!");

        }

        try {

            CONFIG_DIR.mkdirs(); // create parent directories if they do not exist

            createInitialConfig(CONFIG.createNewFile()); // create initial config, true if file was created, false if file already existed

        } catch (IOException e) { 

            e.printStackTrace();

        }
    }

    private static boolean createInitialConfig(boolean create) {

        try (FileOutputStream file_out = new FileOutputStream(CONFIG)) {

            StringBuilder input_buffer = new StringBuilder();

            // create initial values
            if (create) {

                input_buffer.append("look_and_feel:DARK\n");
	            input_buffer.append("api_key:NV\n");
	            input_buffer.append("data_type:NV\n");
	            input_buffer.append("recent_file_location:NV\n");
	            input_buffer.append("file_type:NV\n");
	            input_buffer.append("team_number:NV\n");
            	
            } else {
            	
            	// append values if they are not present
                if (readValue("look_and_feel").equals("")) input_buffer.append("look_and_feel:DARK\n");
                else input_buffer.append("look_and_feel:").append(readValue("look_and_feel")).append("\n");

            	if (readValue("api_key").equals("")) input_buffer.append("api_key:NV\n");
	            else input_buffer.append("api_key:").append(readValue("api_key")).append("\n");
            	
            	if (readValue("data_type").equals("")) input_buffer.append("data_type:NV\n");
	            else input_buffer.append("data_type:").append(readValue("data_type")).append("\n");
	            
	            if (readValue("recent_file_location").equals("")) input_buffer.append("recent_file_location:NV\n");
	            else input_buffer.append("recent_file_location:").append(readValue("recent_file_location")).append("\n");
	            
	            if (readValue("file_type").equals("")) input_buffer.append("file_type:NV\n");
	            else input_buffer.append("file_type:").append(readValue("file_type")).append("\n");
	            
	            if (readValue("team_number").equals("")) input_buffer.append("team_number:NV\n");
	            else input_buffer.append("team_number:").append(readValue("team_number")).append("\n");
	            
            }

            file_out.write(input_buffer.toString().getBytes());

            return true;
    
        } catch (IOException e) {

            e.printStackTrace();
            return false;

        }
    }

    public static String readValue(String id) {

        String value = "";

        try {

            BufferedReader br = new BufferedReader(new FileReader(CONFIG));
            String line;
            while ((line = br.readLine()) != null) {

                if (line.contains(id)) { // line found with matching ID

                    value = line.substring(line.indexOf(":") + 1); // return config value

                }
            }

            br.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return value;

    }

    public static void writeValue(String id, String value) {

        try (BufferedReader file = new BufferedReader(new FileReader(CONFIG));
             FileOutputStream file_out = new FileOutputStream(CONFIG)) {

            StringBuilder input_buffer = new StringBuilder();
            String line;
    
            while ((line = file.readLine()) != null) {

                if (line.contains(id)) { // line found with matching ID

                    line = id + ":" + value; // change current line

                }

                input_buffer.append(line); // append line to buffer
                input_buffer.append("\n");
                
            }

            file_out.write(input_buffer.toString().getBytes()); // write new buffer to file
    
        } catch (IOException e) {

            System.out.println("Problem reading file.");

        }
    }
}