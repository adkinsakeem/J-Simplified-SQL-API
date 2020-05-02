package javaSQL;

public class JSimpleSQLErrors {

	protected static void connectionError() {
		System.out.println(
				"There was an error connecting to the Database. Check your hostname, database name, username, or password.");
	}

	protected static void createDatabaseError() {
		System.out.println(
				"There was an issue creating your Database. Make sure the database name isn't already used. Also, check your connection information.");
	}

	protected static void useDatabaseError() {
		System.out.println(
				"There was an issue selecting your Database. Make sure the database exists. Also, check your connection information.");
	}

	protected static void dropDatabaseError() {
		System.out.println(
				"There was an issue dropping your Database. Make sure the database exists. Also, check your connection information.");
	}

	protected static void createTableError() {
		System.out.println(
				"There was an issue creating your Table. Make sure the table name isn't already used. Also, check your connection information.");
	}

	protected static void deleteTableError() {
		System.out.println(
				"There was an issue dropping your Table. Make sure the table exists. Also, check your connection information.");
	}

	protected static void defaultColumnNameError() {
		System.out.println("This column name is Reserved Word specific for this API. Please choose another name.");
	}

	protected static void fullCodeError() {
		System.out.println("There is an error with your SQL code. Please check the syntax of your code.");
	}

	protected static void addValuesError() {
		System.out.println(
				"There is an error with the inserted values. Make sure the values are the correct variable type. Also, make sure the number of values entered equal the number of columns accepting values.");
	}

	protected static void selectStringError() {
		System.out.println(
				"There is an error with the data you entered. Check your variables, the variable types, and the column names and make sure they are correct.");
	}

	protected static void addTheColumnError() {
		System.out.println(
				"There was an error with the column you are trying to add. Make sure you are entering the correct data in your query.");
	}

	protected static void DropPlaceholderColumnError() {
		System.out.println("An error occurred when attempting to drop the temporary (place holder) column.");

	}

	protected static void ResultSetError() {
		System.out.println(
				"An error with retrieving the data from the database. Check the information entered in your query.");

	}

}
