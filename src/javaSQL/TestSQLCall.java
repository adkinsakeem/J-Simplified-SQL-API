package javaSQL;

public class TestSQLCall {

	public static void main(String[] args) {
		
		JSimpleSQL.hostname("jdbc:mysql://localhost/");
		JSimpleSQL.databasename("sqlapitest");
		JSimpleSQL.username("root");
		JSimpleSQL.password("");
		JSimpleSQL.open();
		
		//JSimpleSQL.createDB("sqlAPITest2");
		JSimpleSQL.useDB("sqlAPITest2");
		//JSimpleSQL.createTable("SQLTable01");
		JSimpleSQL.columnToTable("sqlAPITest2");
		JSimpleSQL.columnName("Name");
		JSimpleSQL.columnType("varChar", 50);
		JSimpleSQL.columnNull("y");
		JSimpleSQL.createColumn();
		

	}

}
