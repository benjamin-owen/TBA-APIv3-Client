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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Configuration {

    private static File CONFIG_PATH = new File(System.getProperty("user.home"), "Application Data" + File.separator + "TBAAPIClient"); // file path for config file
    private static File CONFIG = new File(System.getProperty("user.home"), "Application Data" + File.separator + "TBAAPIClient" + File.separator + "configuration.txt"); // config file

    public static void createConfig() {

        try {

            CONFIG_PATH.mkdir();
            
            createInitialConfig(CONFIG.createNewFile()); // create initial config, true if file was created, false if file already existed

        } catch (IOException e) { 

            e.printStackTrace();

        }
    }

    private static void createInitialConfig(boolean create) {

        try {

            BufferedReader file = new BufferedReader(new FileReader(CONFIG));
            StringBuffer input_buffer = new StringBuffer();

            // create initial values
            if (create) {
            	
	            input_buffer.append("api_key:NV\n");
	            input_buffer.append("data_type:\n");
	            input_buffer.append("recent_file_location:NV\n");
	            input_buffer.append("file_type:NV\n");
	            input_buffer.append("team_number:NV\n");
            	
            } else {
            	
            	// append values if they are not present
            	if (readValue("api_key").equals("")) input_buffer.append("api_key:NV\n");
	            else input_buffer.append("api_key:" + readValue("api_key") + "\n");
            	
            	if (readValue("data_type").equals("")) input_buffer.append("data_type:NV\n");
	            else input_buffer.append("data_type:" + readValue("data_type") + "\n");
	            
	            if (readValue("recent_file_location").equals("")) input_buffer.append("recent_file_location:NV\n");
	            else input_buffer.append("recent_file_location:" + readValue("recent_file_location") + "\n");
	            
	            if (readValue("file_type").equals("")) input_buffer.append("file_type:NV\n");
	            else input_buffer.append("file_type:" + readValue("file_type") + "\n");
	            
	            if (readValue("team_number").equals("")) input_buffer.append("team_number:NV\n");
	            else input_buffer.append("team_number:" + readValue("team_number") + "\n");
	            
            }

            file.close();
    
            FileOutputStream file_out = new FileOutputStream(CONFIG);
            file_out.write(input_buffer.toString().getBytes());
            file_out.close();
    
        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public static String readValue(String id) {

        String value = "";

        try {

            BufferedReader br = new BufferedReader(new FileReader(CONFIG));
            String line = null;
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

        try {

            BufferedReader file = new BufferedReader(new FileReader(CONFIG));
            StringBuffer input_buffer = new StringBuffer();
            String line;
    
            while ((line = file.readLine()) != null) {

                if (line.contains(id)) { // line found with matching ID

                    line = id + ":" + value; // change current line

                }

                input_buffer.append(line); // append line to buffer
                input_buffer.append("\n");
                
            }
            file.close();
    
            FileOutputStream file_out = new FileOutputStream(CONFIG);
            file_out.write(input_buffer.toString().getBytes()); // write new buffer to file
            file_out.close();
    
        } catch (IOException e) {

            System.out.println("Problem reading file.");

        }
    }
}