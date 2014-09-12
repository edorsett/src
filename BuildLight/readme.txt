Author: Dave Neuman

Description:
BuildLight is used to query a Continuous Integration (Jenkins or Hudson only) project list and change a build light color based on project(s) status. 

Prerequisites:
1.  The server that runs this script is Windows. 
2.  Groovy (1.8 or higher) must be installed.
3.  The project you want to monitor must be configured in a CI server (Hudson or Jenkins only)
4.  The server that runs this script requires access to the CI server via HTTP.
5.  A DELCOM build light item 904002 (gen2) must be connnected to the USB port on the server that runs this script.
6.  The CI server you want to monitor must be running.
7.  The projects you want to monitor must be confgiured in BuildLightProjects.txt. The names must match the exact string that appears in CI (main page). 

To start the monitoring:
1. Copy the BuildLight folder and all files (including this file) to the server you want to run you build light on.
2. Open a command window and go to the BuildLight folder.
3. run "groovy BuildLightManager.groovy <ci hostname> <ci port number> BuildLightProjects.txt"
	
	example: "groovy BuildLightManager.groovy cocmcd-dzur64b 8080 BuildLightProjects.txt" 

Note:  This code is designed to work with either Jenkins or Hudson. 




>groovy BuildLightManager.groovy 10.255.188.18 8080/jenkins