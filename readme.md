# Q-Data Analysis with xls Import and SQL with Maven

The Data should be imported via xls or can be created by form field. 
The Data will be analysed and displayed in different diagrams.

The Data will be automatically sorted and displayed. It can be also edited. 
A Relation to the "worktable" should be available. 

# Info
* version = 0.2
* author = MikeQMS
* author-email = mike@qms-consulting.eu
* home-page = https://github.com/MikeQMS/qAnalysis


### Funktions Version 0.1
* Data Import from Excel
* Data Base Edit Table
* Data-analysis Pareto
* RestAPI https://localhost:1234/api/all


##Start in IntelliJ
1. Start Database Server (with config from application.properties)
2. Start Application
3. Start browser and call http://localhost:1234

##Start .jar file
* cmd: java --add-opens java.base/java.lang=ALL-UNNAMED -jar target/QDataAnalysis_0.1-0.0.1-SNAPSHOT.jar

##Start with Docker
###in Terminal:
* docker-compose up --build -d
* access page http://localhost:1234
* access phpmyadmin http://localhost:8082
* user: root pass: Testpassword1234#

