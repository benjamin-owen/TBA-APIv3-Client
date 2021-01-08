/*
 * 
 * Ben Owen
 * 
 * This enum defines different operating systems
 * 
 * Values:
 * - WINDOWS: Windows OS
 * - LINUX: Linux-based OS
 * 
 */

package com.bensuniverse.TBAAPIv3Client;

public enum OperatingSystem {

	WINDOWS("Windows"),
	LINUX("Linux");

	private String name;

	private OperatingSystem(String name) {
		
		this.name = name;
		
	}
	
	public String getName() {
		
		return this.name;
		
	}

	public static OperatingSystem getOperatingSystem() {

		if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) {

			return WINDOWS;

		} else if (System.getProperty("os.name").toUpperCase().contains("LINUX")) {

			return LINUX;

		}

		return null;
	}
}
