package javaSQL;

public class TestSQLCall {

	public static void main(String[] args) {

		JSimpleSQL.hostname("jdbc:mysql://localhost/");
		// JSimpleSQL.databasename("sqlapitest");
		JSimpleSQL.username("root");
		JSimpleSQL.password("");
		JSimpleSQL.open();

		// JSimpleSQL.createDB("sqlAPITest2");
		JSimpleSQL.useDB("sqlapitest");
		// JSimpleSQL.createTable("SQLTable01");
		// JSimpleSQL.testFunction();
		JSimpleSQL.columnToTable("SQLTable01");
		JSimpleSQL.columnName("Count");
		JSimpleSQL.columnType("int", 5);
		// JSimpleSQL.columnNull("y");
		JSimpleSQL.columnComment("This is a comment for the Auto Increment");
		JSimpleSQL.columnExtra("Auto_Increment");

		JSimpleSQL.createColumn();

	}

}
