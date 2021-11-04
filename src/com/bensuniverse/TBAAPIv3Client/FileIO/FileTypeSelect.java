/*
 * 
 * Ben Owen
 * 
 * This class determines which file type to output to (taken from dropdown menu in window)
 * 
 * Functions:
 * writeFile(String filePath, List<String> data)
 * 	- String filePath: path to write output to
 * 	- List<String> data: output data list (contains match numbers and team numbers)
 * 
 */

package com.bensuniverse.TBAAPIv3Client.FileIO;

import com.bensuniverse.TBAAPIv3Client.DataProcessing.DataType;
import com.bensuniverse.TBAAPIv3Client.Frames.Panels.FileSelectPanelRIGHT;
import com.bensuniverse.TBAAPIv3Client.Frames.Panels.TeamNumberProgressBarPanel;

import java.util.List;

public class FileTypeSelect {

	public static boolean writeFile(String file_path, List<String> data, DataType data_type) {

		FileType file_type = FileSelectPanelRIGHT.getFileTypeSelected(); // get file type from dropdown menu in main window

		if (file_type == FileType.TXT || file_type == FileType.CSV) {

			return new Text(file_path).writeFile(data, data_type); // create text file output

		} else if (file_type == FileType.XLS || file_type == FileType.XLSX) {

			return new Excel(file_path).writeFile(file_type, data_type, data, TeamNumberProgressBarPanel.getTeam()); // create Excel file output

		}

		return true;

	}
}
