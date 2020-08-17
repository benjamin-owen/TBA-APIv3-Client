/*
 * 
 * Ben Owen
 * 
 * This enum defines different data that can be retrieved
 * 
 * Values:
 * - MATCH_SCHEDULE: match schedule for a specified event
 * - EVENT_TEAM_LIST: list of teams at a specified event
 * - COMPLETE_TEAM_LIST: list of all teams in FRC
 * 
 */

package com.bensuniverse.TBAAPIv3Client.Frames.Panels;

public enum DataType {
	
	MATCH_SCHEDULE("Match Schedule"),
	EVENT_TEAM_LIST("Event Team List"),
	COMPLETE_TEAM_LIST("Complete Team List");
	
	private String name;
	
	private DataType(String name) {
		
		this.name = name;
		
	}
	
	public String getName() {
		
		return this.name;
		
	}
}
