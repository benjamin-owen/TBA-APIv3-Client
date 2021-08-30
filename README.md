# TBA APIv3 Client

I wrote this program to be used for robotics teams taking part in the FIRST Robotics Competition. Our team needed a way to download match schedules and team lists for various events, and we didn’t have an efficient way to automate that. This program uses the TBA APIv3 to download event match schedules, event team lists, and a complete FRC team list.  

When using the program, most settings are saved when the program is closed, including the TBA API key, data to export setting, output file location, file export type, and the team number to highlight. The only thing that is not saved is the match ID.

---

## Usage instructions can be found at [Ben's Universe](https://www.bensuniverse.com/software/tba-apiv3-client/)

---

## Other Information

#### Configuration files are stored at the following directories:
- **Linux:** *$HOME*/.config/tbaapiv3client/configuration.txt
- **Windows:** *%APPDATA%*/TBAAPIv3Client/configuration.txt
- **MacOS:** *$HOME*/Library/Containers/com.bensuniverse.tbaapiv3client/configuration.txt

#### Change program theme
- Look and feel theme options are in the ribbon menu:
  - View > Change look and feel > *Select theme*

#### Libraries Used
- [Apache POI](https://poi.apache.org/) library for exporting to .XLSX and .XLS file format
- [FlatLaf](https://www.formdev.com/flatlaf/) for light and dark themes added in version 2.2