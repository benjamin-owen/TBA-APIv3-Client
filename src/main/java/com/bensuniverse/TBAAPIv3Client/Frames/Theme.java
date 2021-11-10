/*
 * 
 * Ben Owen
 * 
 * This enum defines different look and feel themes the program can use
 * 
 * Values:
 * - SYSTEM: the default theme for the operating system
 * - LIGHT: FlatLaf light theme
 * - DARK: FlatLaf dark theme
 * 
 */

package com.bensuniverse.TBAAPIv3Client.Frames;

public enum Theme {

	SYSTEM("SYSTEM"),
	LIGHT("LIGHT"),
	DARK("DARK");

	private String name;

	private Theme(String name) {
		
		this.name = name;
		
	}
	
	public String getName() {
		
		return this.name;
		
	}
}
