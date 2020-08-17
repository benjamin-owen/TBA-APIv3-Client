/*
 * 
 * Ben Owen
 * 
 * This enum defines different cell styles to be used for Excel output
 * 
 * Values:
 * - HEADER: bold
 * - HEADER_RED: bold with red background
 * - HEADER_BLUE: bold with blue background
 * - RED: red background
 * - BLUE: blue background
 * - HIGHLIGHTED: bold with yellow background
 * 
 */

package com.bensuniverse.TBAAPIv3Client.FileIO;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;

public enum ExcelCellStyle {
	
	HEADER(true),
	HEADER_RED(true, IndexedColors.CORAL.getIndex()),
	HEADER_BLUE(true, IndexedColors.PALE_BLUE.getIndex()),
	RED(false, IndexedColors.CORAL.getIndex()),
	BLUE(false, IndexedColors.PALE_BLUE.getIndex()),
	HIGHLIGHTED(true, IndexedColors.YELLOW.getIndex());
	
	private final boolean bold;
	private final short colorIndex;
	
	private ExcelCellStyle(boolean bold) {
		
		this.bold = bold;
		this.colorIndex = -1;
		
	}
	
	private ExcelCellStyle(boolean bold, short colorIndex) {
		
		this.bold = bold;
		this.colorIndex = colorIndex;
		
	}
	
	public CellStyle getStyle(Workbook workbook) {
		
		CellStyle style = workbook.createCellStyle();
		Font styleFont = workbook.createFont();
		
		styleFont.setBold(this.bold);
		style.setFont(styleFont);
		if (colorIndex >= 0) {
			
			style.setFillForegroundColor(this.colorIndex);
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
		}
		
		return style;
		
	}
}
