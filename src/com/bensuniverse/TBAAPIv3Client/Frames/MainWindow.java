/*
 *
 * Ben Owen
 * 
 * This is the main GUI for the program
 *
 */

package com.bensuniverse.TBAAPIv3Client.Frames;

import javax.swing.*;

import com.bensuniverse.TBAAPIv3Client.Configuration;
import com.bensuniverse.TBAAPIv3Client.Frames.Panels.APIKeyDataTypePanel;
import com.bensuniverse.TBAAPIv3Client.Frames.Panels.FileSelectPanel;
import com.bensuniverse.TBAAPIv3Client.Frames.Panels.FooterPanel;
import com.bensuniverse.TBAAPIv3Client.Frames.Panels.HeaderPanel;
import com.bensuniverse.TBAAPIv3Client.Frames.Panels.MatchIDPanel;
import com.bensuniverse.TBAAPIv3Client.Frames.Panels.OutputLogPanel;
import com.bensuniverse.TBAAPIv3Client.Frames.Panels.TeamNumberProgressBarPanel;
import com.bensuniverse.TBAAPIv3Client.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

    public MainWindow() {

        super(Main.getName());
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // create menu bar
        JMenuBar menu_bar = new JMenuBar();

        // menu File
        JMenu menu_file = new JMenu("File");
        menu_file.setMnemonic(KeyEvent.VK_F);

        // menu item File > Exit
        JMenuItem file_exit = new JMenuItem("Exit");
        file_exit.setMnemonic(KeyEvent.VK_X);
        file_exit.setToolTipText("Exit program");
        menu_file.add(file_exit);

        // menu View
        JMenu menu_view = new JMenu("View");
        menu_view.setMnemonic(KeyEvent.VK_V);

        // menu item View > Change look and feel
        JMenu view_change_laf = new JMenu("Change look and feel");
        view_change_laf.setMnemonic(KeyEvent.VK_L);
        menu_view.add(view_change_laf);

        // submenu items for View > Change look and feel
        JCheckBoxMenuItem laf_system = new JCheckBoxMenuItem("System default", (Main.getTheme().equals(Theme.SYSTEM)));
        laf_system.setMnemonic(KeyEvent.VK_S);
        view_change_laf.add(laf_system);

        JCheckBoxMenuItem laf_light = new JCheckBoxMenuItem("Light theme", (Main.getTheme().equals(Theme.LIGHT)));
        laf_light.setMnemonic(KeyEvent.VK_I);
        view_change_laf.add(laf_light);

        JCheckBoxMenuItem laf_dark = new JCheckBoxMenuItem("Dark theme", (Main.getTheme().equals(Theme.DARK)));
        laf_dark.setMnemonic(KeyEvent.VK_D);
        view_change_laf.add(laf_dark);

        // add categories to menu bar
        menu_bar.add(menu_file);
        menu_bar.add(menu_view);
        this.setJMenuBar(menu_bar); // menu bar
        
        // add panels
        this.add(new HeaderPanel()).setVisible(false); // temporary fix for weird Linux look and feel issues
        this.add(new APIKeyDataTypePanel()); // api key + data to export fields
        this.add(new FileSelectPanel()); // output file location + file type + browse button
        this.add(new MatchIDPanel()); // match id + start button
        this.add(new TeamNumberProgressBarPanel()); // team number + progress bar
        this.add(new OutputLogPanel()); // output log
        this.add(new FooterPanel()); // version, author, date text

        ImageIcon icon = new ImageIcon(getClass().getResource("/tba_logo.png"));
        this.setIconImage(icon.getImage());

        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // box layout on y-axis
        this.pack();
        this.setLocationRelativeTo(null); // center window in screen

        this.getRootPane().setDefaultButton(MatchIDPanel.getStartButton()); // set default button to start button (when hit enter key)

        // menu item File > Exit
        file_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                System.exit(0);

            }
        });

        // menu item View > Change look and feel > System default
        laf_system.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Configuration.writeValue("look_and_feel", Theme.SYSTEM.getName());
                Main.restart();

            }
        });

        // menu item View > Change look and feel > Light theme
        laf_light.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Configuration.writeValue("look_and_feel", Theme.LIGHT.getName());
                Main.restart();

            }
        });

        // menu item View > Change look and feel > Dark theme
        laf_dark.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Configuration.writeValue("look_and_feel", Theme.DARK.getName());
                Main.restart();

            }
        });

        // wait 100 ms for window to load before MatchIDPanel requests focus
        try {
            Thread.sleep(100); // to avoid requesting
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MatchIDPanel.focus(); // request focus to match ID text field

        this.setVisible(true);
    }
}