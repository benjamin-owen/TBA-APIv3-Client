/*
 * 
 * Ben Owen
 * 
 * This panel contains the file type dropdown and the browse button
 * 
 * Functions:
 * getFileTypeSelected() - returns value from dropdown
 *  
 * setFileTypeSelected(FileType type) - sets dropdown value
 * 	- file type to set dropdown to
 * 
 */

package com.bensuniverse.TBAAPIv3Client.Frames.Panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.bensuniverse.TBAAPIv3Client.Configuration;
import com.bensuniverse.TBAAPIv3Client.FileIO.FileType;
import com.bensuniverse.TBAAPIv3Client.Frames.MainWindow;
import com.bensuniverse.TBAAPIv3Client.Frames.Theme;
import com.bensuniverse.TBAAPIv3Client.Main;

public class FileSelectPanelRIGHT extends JPanel {

	static JComboBox<FileType> file_select = new JComboBox<FileType>(FileType.values()); // dropdown menu populated from FileType enum

	private static final long serialVersionUID = 1L;

	public FileSelectPanelRIGHT() {

		this.setLayout(new GridLayout(1, 2, 5, 5)); // grid layout 1 row x 2 columns

		file_select.setSelectedItem(Configuration.readValue("file_type").equals("NV") ? FileType.XLSX
				: FileType.valueOf(Configuration.readValue("file_type"))); // set dropdown item = config value

		TitledBorder border = new TitledBorder("File type");

		file_select.setBorder(border);

		JButton browse_button = new JButton("Browse");
		JFileChooser file_chooser = new JFileChooser(); // create file chooser
		file_chooser.setDialogTitle("Select Output File Location");
		file_chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		file_chooser.setAcceptAllFileFilterUsed(true);
		if (getFileTypeSelected() == FileType.TXT) file_chooser.setSelectedFile(new File("tba_output.txt")); // set default file name if dropdown is TXT
		else if (getFileTypeSelected() == FileType.XLSX) file_chooser.setSelectedFile(new File("tba_output.xlsx")); // set default file name if dropdown is XLSX
		else if (getFileTypeSelected() == FileType.XLS) file_chooser.setSelectedFile(new File("tba_output.xls")); // set default file name if dropdown is XLS

		file_select.addActionListener(new ActionListener() { // check for changes to dropdown

			public void actionPerformed(ActionEvent e) {

				String temp_path = FileSelectPanel.getFilePath();
				for (int i = temp_path.length() - 1; i > 0; i--) {

					if (temp_path.charAt(i) == '.' && i > 0) {

						temp_path = temp_path.substring(0, i); // get name of file sans extension
						break;

					}
				}

				// set file chooser value depending on dropdown value
				if (getFileTypeSelected() == FileType.TXT) {

					System.out.println("File type changed!");
					FileSelectPanel.setFilePath(temp_path + ".txt");
					file_chooser.setSelectedFile(new File("tba_output.txt"));
					TeamNumberProgressBarPanel.setTeamNumberEnabled(false);

				} else if (getFileTypeSelected() == FileType.XLSX) {

					System.out.println("File type changed!");
					FileSelectPanel.setFilePath(temp_path + ".xlsx");
					file_chooser.setSelectedFile(new File("tba_output.xlsx"));
					TeamNumberProgressBarPanel.setTeamNumberEnabled(true);

				} else if (getFileTypeSelected() == FileType.XLS) {

					System.out.println("File type changed!");
					FileSelectPanel.setFilePath(temp_path + ".xls");
					file_chooser.setSelectedFile(new File("tba_output.xls"));
					TeamNumberProgressBarPanel.setTeamNumberEnabled(true);

				}

				Configuration.writeValue("file_type", file_select.getSelectedItem().toString());

			}
		});

		browse_button.addActionListener(new ActionListener() { // if button is clicked
			public void actionPerformed(ActionEvent e) {

				if (file_chooser.showDialog(MainWindow.getFrames()[0], "Select") == JFileChooser.APPROVE_OPTION) { // if user selects a file

					if (file_chooser.getSelectedFile().getAbsolutePath().toLowerCase().contains(".txt")
							|| file_chooser.getSelectedFile().getAbsolutePath().toLowerCase().contains(".xlsx")
							|| file_chooser.getSelectedFile().getAbsolutePath().toLowerCase().contains(".xls")) { // if file extension contains correct extension

						FileSelectPanel.file_location_input.setText(file_chooser.getSelectedFile().getAbsolutePath()); // set text field = new file path

					} else { // incorrect file extension

						FileSelectPanel.file_location_input.setText(file_chooser.getSelectedFile().getAbsolutePath() + "." + file_select.getSelectedItem().toString().toLowerCase()); // manually add correct extension

					}

				}
			}
		});

		this.add(file_select);
		this.add(browse_button);

	}

	public static FileType getFileTypeSelected() {

		return FileType.valueOf(file_select.getSelectedItem().toString());

	}

	public static void setFileTypeSelected(FileType type) {

		file_select.setSelectedItem(type);

	}
}