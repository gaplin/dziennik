# GradeBook
Simple gradeBook

## Requirements
* java 11 or higher
* javafx 11 or higher
* maven
* postgresql db initiated with dziennik.sql (optionally you can use also sample-data.sql)
* db connection correctly set in src/main/java/schoolregister/ConnectionConfig.java


## Running project
* mvn compile exec:java

## Creating jar file
* java -jar shade/schoolregister.jar

## Logging if sample-data.sql was used
* email - email of any person in sample-data
* password - email in reverse
* For Example:
  * Students
    * Shad@<span>eric.io</span>&emsp; oi.cire@dahS
    * Mark@<span>jace.ca</span>&emsp; ac.ecaj@kraM
  * Teachers
    * kazik123@<span>op.pl</span>&emsp; lp.po@321kizak
    * Sadye@<span>niko.biz</span>&emsp; zib.okin@eydaS
    * Adrain@<span>nella.name</span>&emsp; eman.allen@niardA
  * Parents
    * Brad@<span>luna.io</span>&emsp; oi.anul@darB
    * Fletcher_Legros@<span>aryanna.net</span>&emsp; ten.annayra@sorgeL_rehctelF





