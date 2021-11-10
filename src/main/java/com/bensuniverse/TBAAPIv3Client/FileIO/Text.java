/*
 * 
 * Ben Owen
 * 
 * This class creates the text file output
 * 
 * Functions:
 * writeFile(List<String> finalOutput) - writes text file output
 * 	- List<String> finalOutput: output data list (contains match numbers and team numbers)
 * 
 */

package com.bensuniverse.TBAAPIv3Client.FileIO;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.bensuniverse.TBAAPIv3Client.DataProcessing.DataType;
import com.bensuniverse.TBAAPIv3Client.Frames.ErrorWindow;

public class Text {

	private String filePath;

	public Text(String filePath) {

		this.filePath = filePath; // get the path of the file to be created

	}

	public boolean writeFile(List<String> finalOutput, DataType data_type) {

		try {

			File outputFile = new File(filePath);
			if (outputFile.createNewFile()) { }

			// add header for text output (txt, csv)
			if (data_type == DataType.MATCH_SCHEDULE)
				finalOutput.add(0, "match number;red 1;red 2;red 3;blue 1;blue 2;blue 3");
			else if (data_type == DataType.EVENT_TEAM_LIST)
				finalOutput.add(0, "team number");
			else if (data_type == DataType.COMPLETE_TEAM_LIST)
				finalOutput.add(0, "team number;team name;city;state;country;misc info");

			Path file = Paths.get(filePath);
			Files.write(file, finalOutput, StandardCharsets.UTF_8); // write the output list to the text file

		} catch (IOException e) {

			new ErrorWindow("Error writing file!");
			return true;

		}

		return false;

	}
}
