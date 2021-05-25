# TBA APIv3 Client

I wrote this program to be used for robotics teams taking part in the FIRST Robotics Competition. Our team needed a way to download match schedules and team lists for various events, and we didn’t have an efficient way to automate that. This program uses the TBA APIv3 to download event match schedules, event team lists, and a complete FRC team list.  

When using the program, most settings are saved when the program is closed, including the TBA API key, data to export setting, output file location, file export type, and the team number to highlight. The only thing that is not saved is the match ID. Instructions for how to use the program can be found below.

---

## Usage Instructions

<table>
<caption>GUI Fields and Descriptions</caption>
<tr>
<th width = "20%">Field</th>
<th width = "45%">Description</th>
<th width = "35%">Image</th>
</tr>

<tr>
<td>TBA APIv3 Key</td>
<td>To use the program, you first must obtain an API key. This can be done by logging in to <a href = "https://www.thebluealliance.com/" target = "_blank">The Blue Alliance</a> and requesting a key on your account page. These instructions can be found <a href = "https://www.thebluealliance.com/apidocs/v3" target = "_blank">here.</a> Open the program and copy and paste the key into the "TBA API key" field in the program.</td>
<td width = "33%"><img src = "https://www.bensuniverse.com/media/software/tba-api-v3-client/TBA-APIv3-API-key.jpg" alt = "TBA APIv3 Client API key"></td>
</tr>

<tr>
<td>Data to Export</td>
<td>You can select what data to pull from The Blue Alliance database. The "Data to Export" drop down lists all of the options available. "Match schedule" will download a list of all qualification matches with team numbers and alliance colors. "Event Team List" will download a list of all teams present at an event. "Complete Team List" will download a list of all registered FRC teams. Other program settings will determine which file type this data will be exported to.</td>
<td><img src = "https://www.bensuniverse.com/media/software/tba-api-v3-client/TBA-APIv3-data-to-export.jpg" alt = "TBA APIv3 Client data to export"></td>
</tr>

<tr>
<td>File Type & Location</td>
<td>The next settings available determine where the exported file will save and what format the file will be. The "Browse" button will open a dialog where the output location can be changed. To change the output file type, open the “File type” drop down. Available output file types are .XLSX (default), .TXT, and .XLS.</td>
<td><img src = "https://www.bensuniverse.com/media/software/tba-api-v3-client/TBA-APIv3-file-type-browse.jpg" alt = "TBA APIv3 Client file type and location"></td>
</tr>

<tr>
<td>Match ID</td>
<td>The match ID is determines what match data to fetch. To find a match ID for an event, first open the corresponding TBA page. For this example, I will use the <a href = "https://www.thebluealliance.com/event/2019incmp">2019 Indiana FIRST championship event.</a> The URL of the event page for this event is "thebluealliance.com/event/2019incmp." The match ID is the text after "/event/," which for this event, is "2019incmp." This text should be copied into the “Match ID” text field.</td>
<td><img src = "https://www.bensuniverse.com/media/software/tba-api-v3-client/TBA-APIv3-match-id.jpg" alt = "TBA APIv3 Client match ID"></td>
</tr>

<tr>
<td>(Optional) Team Number</td>
<td>This setting simply highlights the given team number in the exported Excel file. This can be used to easily find which matches a specific team is competing in. To use this setting, type a team number into the "Team number" text field.</td>
<td><img src = "https://www.bensuniverse.com/media/software/tba-api-v3-client/TBA-APIv3-team-number.jpg" alt = "TBA APIv3 Client team number"></td>
</tr>

<tr>
<td>Run Program</td>
<td>Once all the settings are chosen, run the program by pressing the "Start" button. As the program runs, the progress bar will estimate how much data is left to process. Additional information will be given in the output log during the process. Once the program is finished, the output file will contain the downloaded information.</td>
<td><img src = "https://www.bensuniverse.com/media/software/tba-api-v3-client/TBA-APIv3-start.jpg" alt = "TBA APIv3 Client start"></td>
</tr>
</table>

---

## Other Information

#### Configuration files are stored at the following directories:
- **Linux:** *$HOME*/.config/tbaapiclient/configuration.txt
- **Windows:** *%APPDATA%*/TBAAPIClient/configuration.txt
- **MacOS:** *$HOME*/Library/Containers/com.bensuniverse.tbaapiclient/configuration.txt

#### Libraries Used
- [Apache POI](https://poi.apache.org/) library for exporting to .XLSX and .XLS file format
- [FlatLaf](https://www.formdev.com/flatlaf/) for light and dark themes added in version 2.2