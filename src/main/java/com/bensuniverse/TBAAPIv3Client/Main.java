/*
 *
 * Ben Owen
 * 
 * This is the main class for the program
 *
 */

package com.bensuniverse.TBAAPIv3Client;

import com.bensuniverse.TBAAPIv3Client.Frames.MainWindow;
import com.bensuniverse.TBAAPIv3Client.Frames.Theme;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Main {

    private final static String NAME = "TBA APIv3 Client";
    private final static String VERSION = "2.4";
    private final static String AUTHOR = "Benjamin Owen";
    private final static String DATE = "2021-11-10";

    public static void main(String[] args) {

        Configuration.createConfig(); // initialize config file

        // get theme from configuration
        Theme theme = getTheme();

        // set look and feel based on configuration setting
        if (theme == Theme.SYSTEM) { // system theme selected
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (theme == Theme.LIGHT) { // light theme selected
            FlatLightLaf.setup();
        } else if (theme == Theme.DARK) { // dark theme selected
            FlatDarkLaf.setup();
        }

        new MainWindow(); // create main GUI
        
    }

    public static String getName() {

        return NAME;

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

    public static Theme getTheme() {

        return Theme.valueOf(Configuration.readValue("look_and_feel"));

    }

    // restart jar file
    public static void restart() {

        // get current file
        final String javaBin = System.getProperty("java.home") + "/bin/java";
        File currentJar = null;
        try {
            // get current file name
            currentJar = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        // check to make sure it is jar file
        if (!currentJar.getName().endsWith(".jar"))
            return;

        // build command
        final ArrayList<String> command = new ArrayList<String>();
        command.add(javaBin);
        command.add("-jar");
        command.add(currentJar.getPath());

        // run command
        final ProcessBuilder builder = new ProcessBuilder(command);
        try {
            builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // close old instance
        System.exit(0);
    }
}