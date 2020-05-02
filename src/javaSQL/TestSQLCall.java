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
		// JSimpleSQL.columnToTable("SQLTable01");
		// JSimpleSQL.columnName("Count1");
		// JSimpleSQL.columnType("int", 5);
		// JSimpleSQL.columnNull("y");
		// JSimpleSQL.columnComment("This is a comment for the Auto Increment");
		// JSimpleSQL.columnExtra("Auto_Increment");
		// JSimpleSQL.addValues();
		// JSimpleSQL.insertValues();
		JSimpleSQL.columnToTable("SQLTable01");
		// JSimpleSQL.addValues("Akeem");
		// JSimpleSQL.addToColumns("Name");
		// JSimpleSQL.insert();
		JSimpleSQL.selectData("Name, Count");
		JSimpleSQL.selectFrom("SQLTable01");
		JSimpleSQL.selectWhere("Name", "Equals", "Akeem");
		JSimpleSQL.selectOrderBy("Name", "ASC");
		JSimpleSQL.selectOr();
		JSimpleSQL.selectWhere("Count1", "Equals", "20");
		// ResultSet rs = JSimpleSQL.selectRSExecute();
		System.out.println(JSimpleSQL.selectExecute());

		// System.out.println("Got It" + JSimpleSQL.selectExecute());

		// JSimpleSQL.addData(); either one value or array of values
		JSimpleSQL.fullStatement("INSERT INTO sqltable01 (Name, Count1) VALUES (\"Dave\", 231);");

		// JSimpleSQL.createColumn();

	}

}
