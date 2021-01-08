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

    private final static String VERSION = "2.1";
    private final static String AUTHOR = "Benjamin Owen";
    private final static String DATE = "2021-01-08";

    public static void main(String[] args) {

        Configuration.createConfig(); // initialize config file

        new MainWindow(); // create main GUI
        
    }

    public static String getVersion() {

        return VERSION;

    }

    public static String getAuthor() {

        return AUTHOR;

    }

    public static String getDate() {

        return DATE;

    }
}