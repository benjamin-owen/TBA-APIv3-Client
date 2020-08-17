/*
 * 
 * Ben Owen
 * 
 * This class creates the Excel file output
 * 
 * Functions:
 * writeFile(FileType file_type, DataType data_type, List<String> final_output, int bold_team) - writes Excel file output
 * 	- FileType file_type: determines whether to create a .xls or .xlsx file
 * 	- DataType data_type: determines which dataset to create headers for
 * 	- List<String> final_output: output data list (contains match numbers and team numbers)
 * 	- int bold_team: determines which team number to highlight and bold in Excel file
 * 
 */

package com.bensuniverse.TBAAPIv3Client.FileIO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bensuniverse.TBAAPIv3Client.Frames.ErrorWindow;
import com.bensuniverse.TBAAPIv3Client.Frames.Panels.DataType;

public class Excel {

	private String filePath;

	public Excel(String filePath) {

		this.filePath = filePath; // get the path of the file to be created

	}

	public boolean writeFile(FileType file_type, DataType data_type, List<String> final_output, int bold_team) {

		// create workbook (sheet, headers, cell styles)

		Workbook workbook = (file_type == FileType.XLSX) ? new XSSFWorkbook() : new HSSFWorkbook(); // determine to make file .xls or .xlsx
		
		Sheet sheet = null;

		if (data_type == DataType.MATCH_SCHEDULE) {
			
			sheet = workbook.createSheet("Match Schedule");
	
			Row headerRow = sheet.createRow(0);
			Cell matchno = headerRow.createCell(0);
			Cell red1 = headerRow.createCell(1);
			Cell red2 = headerRow.createCell(2);
			Cell red3 = headerRow.createCell(3);
			Cell blue1 = headerRow.createCell(4);
			Cell blue2 = headerRow.createCell(5);
			Cell blue3 = headerRow.createCell(6);
	
			matchno.setCellStyle(ExcelCellStyle.HEADER.getStyle(workbook));
			red1.setCellStyle(ExcelCellStyle.HEADER_RED.getStyle(workbook));
			red2.setCellStyle(ExcelCellStyle.HEADER_RED.getStyle(workbook));
			red3.setCellStyle(ExcelCellStyle.HEADER_RED.getStyle(workbook));
			blue1.setCellStyle(ExcelCellStyle.HEADER_BLUE.getStyle(workbook));
			blue2.setCellStyle(ExcelCellStyle.HEADER_BLUE.getStyle(workbook));
			blue3.setCellStyle(ExcelCellStyle.HEADER_BLUE.getStyle(workbook));
	
			matchno.setCellValue("Match #");
			red1.setCellValue("Red 1");
			red2.setCellValue("Red 2");
			red3.setCellValue("Red 3");
			blue1.setCellValue("Blue 1");
			blue2.setCellValue("Blue 2");
			blue3.setCellValue("Blue 3");
		
		} else if (data_type == DataType.EVENT_TEAM_LIST) {
			
			sheet = workbook.createSheet("Event Team List");
			
			Row headerRow = sheet.createRow(0);
			Cell teamno = headerRow.createCell(0);
	
			teamno.setCellStyle(ExcelCellStyle.HEADER.getStyle(workbook));
	
			teamno.setCellValue("Team #");
			
		} else if (data_type == DataType.COMPLETE_TEAM_LIST) {
			
			sheet = workbook.createSheet("Complete Team List");
			
			Row headerRow = sheet.createRow(0);
			Cell teamno = headerRow.createCell(0);
			Cell nickname = headerRow.createCell(1);
			Cell name = headerRow.createCell(2);
			Cell location = headerRow.createCell(3);
	
			teamno.setCellStyle(ExcelCellStyle.HEADER.getStyle(workbook));
			nickname.setCellStyle(ExcelCellStyle.HEADER.getStyle(workbook));
			name.setCellStyle(ExcelCellStyle.HEADER.getStyle(workbook));
			location.setCellStyle(ExcelCellStyle.HEADER.getStyle(workbook));
	
			teamno.setCellValue("Team #");
			nickname.setCellValue("Nickname");
			name.setCellValue("Location");
			location.setCellValue("Name");
			
		}

		// loop through data list and place data in cells
		int cellIndex = 0;
		for (int i = 0; i < final_output.size(); i++) {

			Row row = sheet.createRow(i + 1);

			String temp_str = final_output.get(i) + ";";

			while (temp_str.contains(";")) {

				Cell current_cell = row.createCell(cellIndex);

				System.out.println(temp_str);
				
				String current_team = temp_str.substring(0, temp_str.indexOf(";")); // get the number before the ';'

				try {
					
					current_cell.setCellValue(Integer.parseInt(current_team));
					
				} catch (NumberFormatException e) {
					
					current_cell.setCellValue(current_team);
					
				}
				
				if (data_type == DataType.EVENT_TEAM_LIST) temp_str = "";
				else temp_str = temp_str.substring(temp_str.indexOf(";") + 1); // remove the number already written

				if (cellIndex >= 1 && cellIndex <= 3 && data_type == DataType.MATCH_SCHEDULE) { // red

					current_cell.setCellStyle(ExcelCellStyle.RED.getStyle(workbook));

				} else if (cellIndex >= 4 && cellIndex <= 6 && data_type == DataType.MATCH_SCHEDULE) { // blue

					current_cell.setCellStyle(ExcelCellStyle.BLUE.getStyle(workbook));

				}
				
				try {
					
					if (Integer.parseInt(current_team) == bold_team) current_cell.setCellStyle(ExcelCellStyle.HIGHLIGHTED.getStyle(workbook));
					
				} catch (NumberFormatException e) {
					
					// ignore
					
				}

				cellIndex++;

			}

			cellIndex = 0;

		}

		// write Excel file

		try {

			FileOutputStream fos = new FileOutputStream(filePath);
			workbook.write(fos);
			fos.close();

			System.out.println("xls file written");

		} catch (IOException e) {

			new ErrorWindow("Error writing file!");
			return true;

		}

		return false;

	}
}
