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

import java.util.Locale;

public enum OperatingSystem {

	WINDOWS("Windows"),
	LINUX("Linux"),
	MAC("Mac");

	private String name;

	private OperatingSystem(String name) {
		
		this.name = name;
		
	}
	
	public String getName() {
		
		return this.name;
		
	}

	public static OperatingSystem getOperatingSystem() {

		String OS = System.getProperty("os.name").toUpperCase();

		if (OS.contains("WINDOWS")) {

			return WINDOWS;

		} else if (OS.contains("LINUX")) {

			return LINUX;

		} else if (OS.contains("MAC")) {

			return MAC;

		}

		return null;
	}
}
