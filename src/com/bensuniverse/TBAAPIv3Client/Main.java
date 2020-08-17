/*
 *
 * Ben Owen
 * 
 * This is the main class for the program
 *
 */

package com.bensuniverse.TBAAPIv3Client;

import com.bensuniverse.TBAAPIv3Client.Frames.MainWindow;

public class Main {

    public static void main(String[] args) {
    	
        Configuration.createConfig(); // initialize config file

        new MainWindow(); // create main GUI
        
    }
}