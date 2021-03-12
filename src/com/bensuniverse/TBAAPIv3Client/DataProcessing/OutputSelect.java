/*
 *
 * Ben Owen
 * 
 * This class contains the processing code for the complete team list output
 * 
 * Functions:
 * process(List<String> input_data, DataType data_type) - selects correct process method and returns output
 * - List<String> input_data: input data from TBA API command
 * - DataType data_type: data type selected
 *
 */

package com.bensuniverse.TBAAPIv3Client.DataProcessing;

import java.util.List;

public class OutputSelect {
	
	public static List<String> process(List<String> input_data, DataType data_type) {
		
		if (data_type == DataType.MATCH_SCHEDULE) {
			
			return new MatchScheduleOutput().process(input_data);
			
		} else if (data_type == DataType.EVENT_TEAM_LIST) {
			
			return new EventTeamListOutput().process(input_data);
			
		} else if (data_type == DataType.COMPLETE_TEAM_LIST) {
			
			return new CompleteTeamListOutput().process(input_data);
			
		}
		
		return null;
	}
}
