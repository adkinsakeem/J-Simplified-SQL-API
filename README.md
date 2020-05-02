# J-Simplified-SQL-API
This combines with the mysql-connector to give the user an easier way to communicate with SQL

# Configuration Instructions
These files need to be compiled and exported to a jar file. 

# Inclusion Path
### src.javaSQL;

# Installation Instructions
## These directions are made using Eclipse. Directions may vary depending on the Integrated Development Editor used.
1. The jar file needs to be added to the program via the "Java Build Path". 
2. In the file "Properties" select "Java Build Path" in the menu on the left side. 
3. Then select "Libraries". 
4. Then select "Add External JARs" and select the jar file. 
5. Finally, press the "Open" button and apply changes.

# Operating Instructions
## Below are the functions used to execute various queries with this API

### Login Information
JSimpleSQL.hostname("Enter Host Name"); = Enter the name of the host you are connecting to.
JSimpleSQL.username("Enter Database Username"); = Enter the username of the database you are connecting to.
JSimpleSQL.password("Enter Database Password"); = Enter the password of the database you are connecting to. 
JSimpleSQL.open(); = Opens the database connection with the Hostname, Username, and Password. 

### Creating Various Items
JSimpleSQL.createDB("Enter Name of Database"); = Enter the name of the database you want to create.
JSimpleSQL.createDatabase("Enter Name of Database"); = Same as "JSimpleSQL.createDB("Enter Name of Database");". Enter the name of the database you want to create.
JSimpleSQL.makeDB("Enter Name of Database"); = Same as "JSimpleSQL.createDB("Enter Name of Database");". Enter the name of the database you want to create.
JSimpleSQL.makeDatabase("Enter Name of Database"); = Same as "JSimpleSQL.createDB("Enter Name of Database");". Enter the name of the database you want to create.
JSimpleSQL.createTable("Enter Name of Table"); = Enter the name of the table you want to create.


### Adding Columns
JSimpleSQL.columnToTable("Enter Table You Are Adding Column To"); = Enter the name of the table you are adding.
JSimpleSQL.columnName("Enter Name of Column"); = Enter the name of the column you are adding.
JSimpleSQL.columnType("Column Type", Column Length); = Enter the variable type and the variable length(if necessary) to add to the table. 
JSimpleSQL.columnNull("NULL option y, n"); = Enter Y or N depending on if you want this column to accept NULL values.
JSimpleSQL.columnComment("Add a Comment to the Column"); = Add any comment you would like to add to the column. 
JSimpleSQL.columnExtra("Add Extra Values"); = Add any extra values you would like to add to the column (Auto_Increment, Null, Attributes).
JSimpleSQL.insertValues(); = Use to insert values into the table.
JSimpleSQL.insertData(); = Same as "insertValues();". Use to insert values into the table.
JSimpleSQL.insert(); = Same as "insertValues();" and "insertData();". Use to insert values into the table.

### Adding Data to Columns
JSimpleSQL.addToColumns("Name of Column"); = Enter the column you want to add a value to.
JSimpleSQL.addValues("Name of Value that we are Adding"); = Enter value that you want to add to the table.

### Select Data from Database
JSimpleSQL.useDB("Which Database are we Using"); = Enter the name of the datababse you are retrieving data from. 
JSimpleSQL.selectData("Data We are Selecting"); = Enter the data we are selecting from the table.
JSimpleSQL.selectFrom("Table we are Getting Data From"); = Enter the table you are selecting the data from. 
JSimpleSQL.selectWhere(Column Name, "Operator(=, >)", "Variable Name"); = Enter the column name that you want to use to compare, the comparison operator that you want to use, and the variable name you want to compare it to. (Ex. JSimpleSQL.selectWhere("Name", "=", "JohnDoe").
JSimpleSQL.selectOrderBy("Column Name", "Order(ASC, DESC)"); = Enter the name of the column you want to use and the ordering arrangement you want to use. 
JSimpleSQL.selectOr(); = Enter if you want to an "OR" comparison between two sets of "WHERE" options. 
JSimpleSQL.selectRSExecute(); = Use to send the requested query and have it return in "ResultSet" format.
JSimpleSQL.selectJSONExecute(); = Use to send the requested query and have it return in "JSON" format.
JSimpleSQL.selectExecute(); = Use to send the requested query and have it return in "String" format.
JSimpleSQL.selectStringExecute(); = Same as the "JSimpleSQL.selectExecute();" function.Use to send the requested query and have it return in "String" format.
JSimpleSQL.fullStatement("Enter SQL code."); Use to directly enter any SQL code directly. 


# Examples of Functions Use
### Ex. 1
JSimpleSQL.columnToTable("SQLTable");
JSimpleSQL.addValues("JohnDoe");
JSimpleSQL.addToColumns("Name");
JSimpleSQL.insert();

### Ex. 2
JSimpleSQL.selectData("All");
JSimpleSQL.selectFrom("SQLTable");
System.out.println(JSimpleSQL.selectExecute());

### Ex. 3
JSimpleSQL.createDB("NewSQLTable");

### Ex. 4
JSimpleSQL.columnToTable("NewSQLTable");
JSimpleSQL.columnName("Count");
JSimpleSQL.columnType("int", 5);
JSimpleSQL.columnNull("y");
JSimpleSQL.columnComment("This is a comment for the Auto Increment");
JSimpleSQL.columnExtra("Auto_Increment");
JSimpleSQL.addValues();

### Ex. 5
JSimpleSQL.selectData("LastName, Count");
JSimpleSQL.selectFrom("SQLTable");
JSimpleSQL.selectWhere("Count", ">", 5);
JSimpleSQL.selectOr();
JSimpleSQL.selectWhere("LastName", ">", "B");
JSimpleSQL.selectOrderBy("LastName", "ASC");
ResultSet rs = JSimpleSQL.selectRSExecute();

### Ex. 6
JSimpleSQL.fullStatement("INSERT INTO sqltable01 (Name, Age) VALUES ("Dave", 23);");


# Contact Information for the Programmer
### Name: Akeem Adkins
### E-Mail: adkinsakeem@gmail.com
### GitHub: https://github.com/adkinsakeem/
