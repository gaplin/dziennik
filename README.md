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
    * Shad@<span>eric.io</span>    oi.cire@dahS
    * Mark@<span>jace.ca</span>    ac.ecaj@kraM
  * Teachers
    * kazik123@<span>op.pl</span>    lp.po@321kizak
    * Sadye@<span>niko.biz</span>    zib.okin@eydaS
    * Adrain@<span>nella.name</span> eman.allen@niardA
  * Parents
    * Brad@<span>luna.io</span>    oi.anul@darB
    * Fletcher_Legros@<span>aryanna.net</span>    ten.annayra@sorgeL_rehctelF






