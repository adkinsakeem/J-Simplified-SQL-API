package javaSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class JSimpleSQL {

	static String HostName;
	static String DBName;
	static String UserName;
	static String Password;
	static Connection conn;
	static String currentToTable;
	static String currentFromTable;
	static boolean DefNum = false;

	static String[] defaultColumns = new String[] { "", "", "", "", "NOT NULL", "", "", "", "", "", "" };
	static String[] columns = new String[] { "", "", "", "", "NOT NULL", "", "", "", "", "", "" };
	// 0.Name, 1.Type, 2.Type Length/Values, 3.Collation, 4.Null, 5.Default,
	// 6.Comments, 7.Extra-2(AutoIncrement), 8.Attributes(Unsigned)

	protected static void hostname(String HName) {
		HostName = HName;
	}

	protected static void databasename(String databaseName) {
		DBName = databaseName;
	}

	protected static void username(String UName) {
		UserName = UName;
	}

	protected static void password(String PWord) {
		Password = PWord;
	}

	protected static void open() {
		try {
			conn = DriverManager.getConnection(HostName, UserName, Password);
		} catch (SQLException e) {
			try {
				conn = DriverManager.getConnection(HostName + "/" + DBName, UserName, Password);

			} catch (SQLException f) {
				JSimpleSQLErrors.connectionError();
			}
		}
	}

	// ***Create The Database*****************************************************
	// ***************************************************************************

	protected static void createDB(String DBName) {
		createtheDB(DBName);
	}

	protected static void createDatabase(String DBName) {
		createtheDB(DBName);
	}

	protected static void makeDB(String DBName) {
		createtheDB(DBName);
	}

	protected static void makeDatabase(String DBName) {
		createtheDB(DBName);
	}

	protected static void createtheDB(String DBName) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("CREATE DATABASE " + DBName);
		} catch (SQLException e) {
			JSimpleSQLErrors.createDatabaseError();
		}

	}

	protected static void testFunction() {
		try {
			Statement stmt = conn.createStatement();
			String testString = "ALTER TABLE sqlapitest ADD Email varchar(255);";
			System.out.println("Stmt: " + testString);
			stmt.executeUpdate(testString);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	// ***Use Database************************************************************

	protected static void useDB(String useDBName) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("USE " + useDBName + ";");
		} catch (SQLException e) {
			JSimpleSQLErrors.useDatabaseError();
		}
	}
	// ***Drop The Database******************************************************

	protected static void dropDB(String DBDrop) {
		droptheDB(DBDrop);
	}

	protected static void dropDatabase(String DBDrop) {
		droptheDB(DBDrop);
	}

	protected static void removeDB(String DBDrop) {
		droptheDB(DBDrop);
	}

	protected static void removeDatabase(String DBDrop) {
		droptheDB(DBDrop);
	}

	protected static void deleteDB(String DBDrop) {
		droptheDB(DBDrop);
	}

	protected static void deleteDatabase(String DBDrop) {
		droptheDB(DBDrop);
	}

	protected static void droptheDB(String DBDrop) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("DROP DATABASE " + DBName);
		} catch (SQLException e) {
			JSimpleSQLErrors.dropDatabaseError();
		}
	}
	// *************************************************************************

	// ***Create The Table******************************************************

	protected static void createTable(String tableName) {
		createtheTable(tableName);
	}

	protected static void makeTable(String tableName) {
		createtheTable(tableName);
	}

	protected static void createtheTable(String tableName) {
		try {
			System.out.println("Test01");
			Statement stmt = conn.createStatement();
			System.out.println("Test02");
			stmt.executeUpdate("CREATE TABLE " + tableName + " (FirstPlaceHolderColumnToDelete int);");
			System.out.println("Test03");
		} catch (SQLException e) {
			e.printStackTrace();
			JSimpleSQLErrors.createTableError();
		}
	}

	// ***Drop The Table************************************************************

	protected static void dropTable(String tableName) {
		droptheTable(tableName);
	}

	protected static void removeTable(String tableName) {
		droptheTable(tableName);
	}

	protected static void deleteTable(String tableName) {
		droptheTable(tableName);
	}

	protected static void droptheTable(String tableName) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("DROP TABLE " + DBName);
		} catch (SQLException e) {
			JSimpleSQLErrors.deleteTableError();
		}
	}

	// ***Get
	// Table***********************************************************************

	protected static void columnToTable(String cToTable) {
		currentToTable = cToTable;
	}

	protected static void columnFromTable(String cFromTable) {
		currentFromTable = cFromTable;
	}

	// ***Add
	// Columns****************************************************************************
	protected static void columnName(String cName) {
		if (cName.toUpperCase().equals("FirstPlaceHolderColumnToDelete".toUpperCase())) {
			JSimpleSQLErrors.defaultColumnNameError();
		} else {
			columns[0] = cName;
		}
	}

	protected static void columnType(String cType) {
		int par1 = cType.indexOf("(");
		int par2 = cType.indexOf(")");
		if (par1 != -1) {
			columns[1] = (cType.substring(0, par1));
			columns[2] = (cType.substring(par1, par2));
		} else {
			columns[1] = cType;
		}
	}

	protected static void columnType(String cType, int cLength) {
		columns[1] = cType;
		columns[2] = Integer.toString(cLength);
	}

	protected static void columnType(String cType, String cLength) {
		columns[1] = cType;
		columns[2] = cLength;
	}

	/*
	 * protected static void columnCollation(String cColl) { columns[3] = cColl; }
	 */

	protected static void columnNull(String cNull) {
		if (cNull.toUpperCase().equals("Yes".toUpperCase()) || cNull.toUpperCase().equals("Y".toUpperCase())) {
			columns[4] = "NULL";
		} else if (cNull.toUpperCase().equals("No".toUpperCase()) || cNull.toUpperCase().equals("N".toUpperCase())
				|| cNull.toUpperCase().equals("NotNull".toUpperCase())) {
			columns[4] = "NOT NULL";
		} else {
			columns[4] = cNull;
		}
	}

	protected static void columnDefault(String cDefault) {
		DefNum = false;
		columnGetDefault(cDefault, DefNum);
	}

	protected static void columnDefault(int cDefault) {
		DefNum = true;
		columnGetDefault(Integer.toString(cDefault), DefNum);
	}

	protected static void columnGetDefault(String cDefault, boolean DefNum) {

		columns[5] = cDefault;
	}

	protected static void columnComment(String cComments) {
		columns[6] = cComments;
	}

	protected static void columnExtra(String cExtra) {
		System.out.println("cExtra: " + cExtra);
		if (cExtra.toUpperCase().equals("AUTOINCREMENT") || cExtra.toUpperCase().equals("YES")
				|| cExtra.toUpperCase().equals("Y") || cExtra.toUpperCase().equals("AUTO_INCREMENT")) {
			columns[7] = "AUTO_INCREMENT";
		} else if (cExtra.toUpperCase().equals("NULL")) {
			columns[4] = "NULL";
		} else if (cExtra.toUpperCase().equals("NO") || cExtra.toUpperCase().equals("N")) {
			columns[7] = "";
		} else {
			columns[8] = cExtra.toUpperCase();
		}
	}

	protected static void createColumn() {
		addTheColumn();
	}

	protected static void makeColumn() {
		addTheColumn();
	}

	protected static void addColumn() {
		addTheColumn();
	}

	protected static void addTheColumn() {
		System.out.println("\"Current: " + currentToTable);
		System.out.println("Array: " + columns[0]);

		String PrepString = "ALTER TABLE ";
		PrepString = PrepString + currentToTable + " ADD ";
		PrepString += columns[0] + " ";
		if (columns[2].equals("")) {
			PrepString += columns[1];
		} else {
			PrepString += columns[1];
		}
		// items with one value
		if (columns[1].toUpperCase().equals("VARCHAR") || columns[2].toUpperCase().equals("INT")
				|| columns[2].toUpperCase().equals("TINYINT") || columns[2].toUpperCase().equals("SMALLINT")
				|| columns[2].toUpperCase().equals("MEDIUMINT") || columns[2].toUpperCase().equals("BIGINT")
				|| columns[2].toUpperCase().equals("CHAR") || columns[2].toUpperCase().equals("BINARY")
				|| columns[2].toUpperCase().equals("VARBINARY") || columns[2].toUpperCase().equals("TEXT")
				|| columns[2].toUpperCase().equals("BLOB") || columns[2].toUpperCase().equals("BIT")
				|| columns[2].toUpperCase().equals("INTEGER")
				|| (columns[2].toUpperCase().equals("FLOAT") && !columns[2].contains(","))) {
			PrepString += "(" + Integer.parseInt(columns[2]) + ")";
		} else if (columns[1].toUpperCase().equals("FLOAT") || columns[1].toUpperCase().equals("DOUBLE")
				|| columns[1].toUpperCase().equals("DOUBLE PRECISION") || columns[1].toUpperCase().equals("DECIMAL")
				|| columns[1].toUpperCase().equals("DEC")) {
			PrepString += "(" + Integer.parseInt(columns[2].substring(0, columns[2].indexOf(","))) + ","
					+ Integer.parseInt(columns[2].substring(columns[2].indexOf(" ")));
		} else {
			PrepString += "(" + columns[2] + ")";
		}

		// PrepString += " COLLATE " + columns[3];

		if (columns[4].toUpperCase().equals("NULL") || columns[4].toUpperCase().equals("Y".toUpperCase())
				|| columns[4].toUpperCase().equals("YES".toUpperCase())) {
			PrepString += " NULL";
		}

		if (columns[5] != "") {
			if (DefNum == true) {
				PrepString += " DEFAULT " + Integer.parseInt(columns[5]);
			} else {
				if (columns[5].toUpperCase().equals("NULL")) {
					PrepString += " DEFAULT NULL";
				} else if (columns[5].toUpperCase().equals("CURRENT_TIMESTAMP")
						|| columns[5].toUpperCase().equals("CURRENTTIMESTAMP")
						|| columns[5].toUpperCase().equals("TIMESTAMP")) {
					PrepString += " DEFAULT CURRENT_TIMESTAMP";
				} else {
					PrepString += " DEFAULT \"" + columns[5] + "\"";
				}
			}
		}
		if (columns[6] != "") {
			PrepString += " COMMENT \"" + columns[6] + "\"";

		}
		System.out.println("Auto I: " + columns[7]);
		if (columns[7].equals("AUTO_INCREMENT")) {
			PrepString += " AUTO_INCREMENT, ADD PRIMARY KEY " + "(`" + columns[0] + "`)";
		}

		if (columns[8].equals("unsigned".toUpperCase())) {
			PrepString += " UNSIGNED";
		}

		PrepString += ";";

		try {
			Statement stmt = conn.createStatement();
			System.out.println("Stmt: " + PrepString);
			stmt.executeUpdate(PrepString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Come here to use if statements to check various errors
		}

		try {
			Statement defaultStmt = conn.createStatement();

			ResultSet rs = defaultStmt.executeQuery("SELECT * FROM " + currentToTable + ";");

			ResultSetMetaData JSimpleMetaData = rs.getMetaData();
			int columns = JSimpleMetaData.getColumnCount();
			for (int x = 1; x <= columns; x++) {
				if ("FirstPlaceHolderColumnToDelete".equals(JSimpleMetaData.getColumnName(x))) {
					defaultStmt = conn.createStatement();
					defaultStmt.executeUpdate(
							"ALTER TABLE " + currentToTable + " DROP COLUMN FirstPlaceHolderColumnToDelete;");
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
