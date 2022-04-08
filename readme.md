# Q-Data Analysis with xls Import and SQL (Maven)

The tool provides a xlsx import function which adds the data to a mysql database.
This database can be analysed and displayed in a Pareto chart. It quantifies the data 
by the fault code and produces a Pareto overview. The Pareto can be used to identify
production errors and to prioritize corresponding projects for the fault reduction. 

This project uses Spring Boot, JSF and Primefaces.

This is my first java Project and the development takes time. 
I have plenty ideas for improvements and more functionality. I'll keep you posted.

Please drop your questions & suggestions in the comment section.

# Info
* version = 0.2
* author = MikeQMS
* author-email = mike@qms-consulting.eu
* home-page = https://github.com/MikeQMS/qAnalysis

##Start in IntelliJ
1. Start Database Server (with config from application.properties)
2. Start Application
3. Start browser and call http://localhost:1234

##Start .jar file
* cmd: java --add-opens java.base/java.lang=ALL-UNNAMED -jar target/QDataAnalysis_0.1-0.0.1-SNAPSHOT.jar
* start.bat in Project folder

##Start with Docker

###in Terminal:
* docker-compose up --build -d
* access page http://localhost:1234
* access phpmyadmin http://localhost:8082
* user: root pass: Testpassword1234#

## Funktions Version 0.1
* Data Import from Excel
* Data Base Edit Table
* Data-analysis Pareto
* RestAPI https://localhost:1234/api/all

## Version 0.2
* Jar File export and execution works
* Docker implementation


## Planning, implementation, improvements:
* make data import and datatable generic as well as data type. 
* add imports to existing tables
* create several analytical projects
* make the Pareto quantity row selectable and countable or sum the row values as output
