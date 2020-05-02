package javaSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.simple.JSONObject;

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
	static ArrayList<String> arrayData = new ArrayList<>();
	static ArrayList<String> arrayTable = new ArrayList<>();
	static ArrayList<String> arrayFrom = new ArrayList<>();
	static ArrayList<String> arrayWhere = new ArrayList<>();
	static ArrayList<String> arrayBy = new ArrayList<>();
	static ArrayList<String> arrayPrep = new ArrayList<>();
	static ArrayList<String> arrayFromColumn = new ArrayList<>();
	static ArrayList<String> arrayFromSymbol = new ArrayList<>();
	static ArrayList<String> arrayFromVariable = new ArrayList<>();
	static ArrayList<String> arrayFromOrderColumn = new ArrayList<>();
	static ArrayList<String> arrayFromOrderSort = new ArrayList<>();

	static String arrayAddData;
	static String arrayAddColumn;

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
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("CREATE TABLE " + tableName + " (FirstPlaceHolderColumnToDelete int);");
		} catch (SQLException e) {
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
		if (columns[7].equals("AUTO_INCREMENT")) {
			PrepString += " AUTO_INCREMENT, ADD PRIMARY KEY " + "(`" + columns[0] + "`)";
		}

		if (columns[8].equals("unsigned".toUpperCase())) {
			PrepString += " UNSIGNED";
		}

		PrepString += ";";
		columns = defaultColumns;

		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(PrepString);
		} catch (SQLException e) {
			JSimpleSQLErrors.addTheColumnError();

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
			JSimpleSQLErrors.DropPlaceholderColumnError();
		}

	}

	protected static void fullStatement(String fullCode) {

		try {
			Statement fullSQLStatement = conn.createStatement();
			fullSQLStatement.execute(fullCode);

		} catch (SQLException e) {
			JSimpleSQLErrors.fullCodeError();
		}

	}

	// ***Select Options ****************************************************
	// **********************************************************************

	protected static void selectData(String searchData) {
		if (searchData.toUpperCase() == "ALL") {
			arrayData.add("*");
		} else {
			arrayData.add(searchData);
		}
	}

	protected static void getData(String searchData) {
		arrayData.add(searchData);
	}

	protected static void selectTable(String table) {
		arrayTable.add(table);
	}

	protected static void selectFrom(String from) {
		arrayFrom.add(from);
	}

	protected static void selectWhere(String where) {
		arrayWhere.add(where);
	}

	protected static void selectBy(String by) {
		arrayBy.add(by);
	}

	protected static void selectWhere(String Column, String Symbol, String Variable) {
		arrayFromColumn.add(Column);
		arrayFromVariable.add(Variable);

		if (Symbol.equals("=") || Symbol.toUpperCase().equals("EQUALS") || Symbol.toUpperCase().equals("EQUAL")) {
			arrayFromSymbol.add("=");
		} else if (Symbol.equals(">") || Symbol.toUpperCase().equals("GREATER")
				|| Symbol.toUpperCase().equals("GREATERTHAN") || Symbol.toUpperCase().equals("GREATER THAN")
				|| Symbol.toUpperCase().equals("GREATER-THAN") || Symbol.toUpperCase().equals("GREATER_THAN")) {
			arrayFromSymbol.add(">");
		} else if (Symbol.equals("<") || Symbol.toUpperCase().equals("LESS") || Symbol.toUpperCase().equals("LESSTHAN")
				|| Symbol.toUpperCase().equals("LESS THAN") || Symbol.toUpperCase().equals("LESS-THAN")
				|| Symbol.toUpperCase().equals("LESS")) {
			arrayFromSymbol.add("<");
		} else if (Symbol.equals(">=") || Symbol.toUpperCase().equals("> =")
				|| Symbol.toUpperCase().equals("GREATER THAN OR EQUAL")
				|| Symbol.toUpperCase().equals("GREATERTHANOREQUAL")
				|| Symbol.toUpperCase().equals("GREATER-THAN-OR-EQUAL")
				|| Symbol.toUpperCase().equals("GREATER_THAN_OR_EQUAL")
				|| Symbol.toUpperCase().equals("GREATERTHANEQUAL") || Symbol.toUpperCase().equals("GREATER_THAN_EQUAL")
				|| Symbol.toUpperCase().equals("GREATER-THAN-EQUAL")
				|| Symbol.toUpperCase().equals("GREATER THAN EQUAL")) {
			arrayFromSymbol.add(">=");
		} else if (Symbol.equals("<=") || Symbol.toUpperCase().equals("< =")
				|| Symbol.toUpperCase().equals("LESS THAN OR EQUAL") || Symbol.toUpperCase().equals("LESSTHANOREQUAL")
				|| Symbol.toUpperCase().equals("LESS-THAN-OR-EQUAL")
				|| Symbol.toUpperCase().equals("LESS_THAN_OR_EQUAL") || Symbol.toUpperCase().equals("LESSTHANEQUAL")
				|| Symbol.toUpperCase().equals("LESS_THAN_EQUAL") || Symbol.toUpperCase().equals("LESS-THAN-EQUAL")
				|| Symbol.toUpperCase().equals("LESS THAN EQUAL")) {
			arrayFromSymbol.add("<=");
		} else if (Symbol.equals("<>") || Symbol.toUpperCase().equals("< >") || Symbol.toUpperCase().equals("!=")
				|| Symbol.toUpperCase().equals("! =") || Symbol.toUpperCase().equals("NOT EQUAL")
				|| Symbol.toUpperCase().equals("NOTEQUAL") || Symbol.toUpperCase().equals("NOT-EQUAL")
				|| Symbol.toUpperCase().equals("NOT_EQUAL") || Symbol.toUpperCase().equals("NOT EQUAL TO")
				|| Symbol.toUpperCase().equals("NOTEQUALTO") || Symbol.toUpperCase().equals("NOT-EQUAL-TO")
				|| Symbol.toUpperCase().equals("NOT_EQUAL_TO")) {
			arrayFromSymbol.add("<>");
		} else if (Symbol.toUpperCase().equals("BETWEEN") || Symbol.toUpperCase().equals("BETWEEN")
				|| Symbol.toUpperCase().equals("IN BETWEEN") || Symbol.toUpperCase().equals("INBETWEEN")
				|| Symbol.toUpperCase().equals("IN-BETWEEN") || Symbol.toUpperCase().equals("IN_BETWEEN")) {
			arrayFromSymbol.add("BETWEEN");
		} else if (Symbol.toUpperCase().equals("LIKE") || Symbol.toUpperCase().equals("IS LIKE")
				|| Symbol.toUpperCase().equals("ISLIKE") || Symbol.toUpperCase().equals("IS_LIKE")
				|| Symbol.toUpperCase().equals("IS-LIKE")) {
			arrayFromSymbol.add("LIKE");
		} else if (Symbol.toUpperCase().equals("IN") || Symbol.toUpperCase().equals("WITHIN")) {
			arrayFromSymbol.add("IN");
		}
	}

	protected static void selectOrderBy(String Column, String Sort) {
		arrayFromOrderColumn.add(Column);
		OrderBySelect(Sort);
	}

	protected static void selectOrder(String Column, String Sort) {
		arrayFromOrderColumn.add(Column);
		OrderBySelect(Sort);
	}

	protected static void OrderBySelect(String Sort) {
		if (Sort.toUpperCase() == "DESC" || Sort.toUpperCase() == "DEC" || Sort.toUpperCase() == "DES"
				|| Sort.toUpperCase() == "DESCEND" || Sort.toUpperCase() == "DESCENDING"
				|| Sort.toUpperCase() == "DESCENDING ORDER" || Sort.toUpperCase() == "DESCENDINGORDER"
				|| Sort.toUpperCase() == "DESCENDING-ORDER" || Sort.toUpperCase() == "DESCENDING_ORDER"
				|| Sort.toUpperCase() == "DESC ORDER" || Sort.toUpperCase() == "DESCORDER"
				|| Sort.toUpperCase() == "DESC-ORDER" || Sort.toUpperCase() == "DESC_ORDER") {
			arrayFromOrderSort.add("DESC");
		} else if (Sort.toUpperCase() == "ASC" || Sort.toUpperCase() == "ASCEND" || Sort.toUpperCase() == "ASCENDING"
				|| Sort.toUpperCase() == "ASCENDING ORDER" || Sort.toUpperCase() == "ASCENDINGORDER"
				|| Sort.toUpperCase() == "ASCENDING-ORDER" || Sort.toUpperCase() == "ASCENDING_ORDER"
				|| Sort.toUpperCase() == "ASC ORDER" || Sort.toUpperCase() == "ASCORDER"
				|| Sort.toUpperCase() == "ASC-ORDER" || Sort.toUpperCase() == "ASC_ORDER") {
			arrayFromOrderSort.add("ASC");
		}

	}

	protected static void selectOr() {
		arrayFromColumn.add(null);
		arrayFromSymbol.add(null);
		arrayFromVariable.add(null);
	}

	protected static ResultSet selectRSExecute() {
		return (getExecuteInfo());
	}

	protected static JSONObject selectJSONExecute() {
		return getJson();
	}

	protected static String selectExecute() {
		return getJson().toString();
	}

	protected static String selectStringExecute() {
		return getJson().toString();
	}

	@SuppressWarnings("unchecked")
	protected static JSONObject getJson() {
		JSONObject JSONSQLObj = new JSONObject();
		JSONObject JSONSQLInnerObj = new JSONObject();
		ResultSet rs = getExecuteInfo();
		ArrayList<String> SQLColumns = new ArrayList<>();
		ArrayList<Integer> SQLColumnType = new ArrayList<>();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNum = rsmd.getColumnCount();
			for (int x = 1; x <= columnsNum; x++) {
				SQLColumns.add(rsmd.getColumnName(x));
				SQLColumnType.add(rsmd.getColumnType(x));
			}
			int count = 0;
			while (rs.next()) {
				for (int x = 0; x < columnsNum; x++) {
					switch (SQLColumnType.get(x)) {
					case -7:
						JSONSQLInnerObj.put(SQLColumns.get(x), rs.getBytes(SQLColumns.get(x)));
						break;
					case -6:
						JSONSQLInnerObj.put(SQLColumns.get(x), rs.getInt(SQLColumns.get(x)));
						break;
					case -5:
						JSONSQLInnerObj.put(SQLColumns.get(x), rs.getInt(SQLColumns.get(x)));
						break;
					case -4:
						// LONGVERBINARY JSONSQLInnerObj.put(SQLColumns.get(x),
						// rs.getInt(SQLColumns.get(x)));
						break;
					case -3:
						// VARBINARY JSONSQLInnerObj.put(SQLColumns.get(x),
						// rs.getInt(SQLColumns.get(x)));
						break;
					case -2:
						// BINARY JSONSQLInnerObj.put(SQLColumns.get(x), rs.getInt(SQLColumns.get(x)));
						break;
					case -1:
						JSONSQLInnerObj.put(SQLColumns.get(x), rs.getString(SQLColumns.get(x)));
						break;
					case -0:
						JSONSQLInnerObj.put(SQLColumns.get(x), null);
						break;
					case 1:
						JSONSQLInnerObj.put(SQLColumns.get(x), rs.getCharacterStream(SQLColumns.get(x)));
						break;
					case 2:
						JSONSQLInnerObj.put(SQLColumns.get(x), rs.getInt(SQLColumns.get(x)));
						break;
					case 3:
						JSONSQLInnerObj.put(SQLColumns.get(x), rs.getFloat(SQLColumns.get(x)));
						break;
					case 4:
						JSONSQLInnerObj.put(SQLColumns.get(x), rs.getInt(SQLColumns.get(x)));
						break;
					case 5:
						JSONSQLInnerObj.put(SQLColumns.get(x), rs.getInt(SQLColumns.get(x)));
						break;
					case 6:
						JSONSQLInnerObj.put(SQLColumns.get(x), rs.getFloat(SQLColumns.get(x)));
						break;
					case 7:
						// JSONSQLInnerObj.put(SQLColumns.get(x), rs.getInt(SQLColumns.get(x)));
						break;
					case 8:
						JSONSQLInnerObj.put(SQLColumns.get(x), rs.getDouble(SQLColumns.get(x)));
						break;
					case 12:
						JSONSQLInnerObj.put(SQLColumns.get(x), rs.getString(SQLColumns.get(x)));
						break;
					case 91:
						JSONSQLInnerObj.put(SQLColumns.get(x), rs.getDate(SQLColumns.get(x)));
						break;
					case 92:
						JSONSQLInnerObj.put(SQLColumns.get(x), rs.getTime(SQLColumns.get(x)));
						break;
					case 93:
						JSONSQLInnerObj.put(SQLColumns.get(x), rs.getTimestamp(SQLColumns.get(x)));
						break;
					default:
						JSONSQLInnerObj.put(SQLColumns.get(x), rs.getString(SQLColumns.get(x)));
						break;
					}
				}

				JSONSQLObj.put(count, JSONSQLInnerObj);
				count++;
			}
		} catch (SQLException e) {
			JSimpleSQLErrors.selectStringError();
		}
		return JSONSQLObj;
	}

	protected static ResultSet getExecuteInfo() {
		ResultSet rs = null;
		StringBuilder selectBuilder = new StringBuilder();
		String comma = "";
		selectBuilder.append("SELECT ");

		for (String dataLoop : arrayData) {
			selectBuilder.append(comma);
			comma = ", ";
			selectBuilder.append(dataLoop);
		}
		if (!arrayFrom.isEmpty()) {
			selectBuilder.append(" FROM ");
			comma = "";
			for (String tableLoop : arrayFrom) {
				selectBuilder.append(comma);
				comma = ", ";
				selectBuilder.append(tableLoop);
			}
		}

		if (!arrayFromColumn.isEmpty()) {
			String andVar = "";
			selectBuilder.append(" WHERE (");

			for (int x = 0; x < arrayFromColumn.size(); x++) {
				if (arrayFromColumn.get(x) == null && arrayFromSymbol.get(x) == null
						&& arrayFromVariable.get(x) == null) {
					selectBuilder.append(") OR (");
					andVar = "";
				} else {
					selectBuilder.append(andVar);
					selectBuilder.append(arrayFromColumn.get(x) + " " + arrayFromSymbol.get(x) + " '"
							+ arrayFromVariable.get(x) + "'");
					andVar = " AND ";
				}
			}
			selectBuilder.append(")");
		}

		if (!arrayFromOrderColumn.isEmpty()) {
			selectBuilder.append(" ORDER BY ");
			comma = "";
			for (int x = 0; x < arrayFromOrderColumn.size(); x++) {
				selectBuilder.append(comma);
				selectBuilder.append(arrayFromOrderColumn.get(x) + " " + arrayFromOrderSort.get(x));
				comma = ", ";
			}
		}
		selectBuilder.append(";");
		clearSelectArrays();

		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(selectBuilder.toString());

		} catch (SQLException e) {
			JSimpleSQLErrors.ResultSetError();
		}
		return rs;

	}

	protected static void clearSelectArrays() {
		arrayData.clear();
		arrayTable.clear();
		arrayFrom.clear();
		arrayWhere.clear();
		arrayBy.clear();
		arrayFromColumn.clear();
		arrayFromVariable.clear();
		arrayFromSymbol.clear();
		arrayFromOrderColumn.clear();
		arrayFromOrderSort.clear();

	}

	// ***Add Values to Database***************************************************
	// *********************************************************************
	protected static void addValues(int... data) {
		ArrayList<String> toData = new ArrayList<>();
		for (int tempData : data) {
			toData.add(Integer.toString(tempData));
		}
		sendData(toData, "Integer");
	}

	protected static void addValues(String... data) {
		ArrayList<String> toData = new ArrayList<>();
		for (String tempData : data) {
			toData.add(tempData);
		}

		sendData(toData, "String");
	}

	protected static void addValues(double... data) {
		ArrayList<String> toData = new ArrayList<>();
		for (double tempData : data) {
			toData.add(Double.toString(tempData));
		}
		sendData(toData, "Double");
	}

	protected static void addValues(float... data) {
		ArrayList<String> toData = new ArrayList<>();
		for (float tempData : data) {
			toData.add(Float.toString(tempData));
		}
		sendData(toData, "Float");
	}

	protected static void addValues(Long... data) {
		ArrayList<String> toData = new ArrayList<>();
		for (long tempData : data) {
			toData.add(Long.toString(tempData));
		}
		sendData(toData, "Long");
	}

	protected static void addValues(boolean... data) {
		ArrayList<String> toData = new ArrayList<>();
		for (boolean tempData : data) {
			toData.add(Boolean.toString(tempData));
		}
		sendData(toData, "Boolean");
	}

	protected static void addValues(byte... data) {
		ArrayList<String> toData = new ArrayList<>();
		for (byte tempData : data) {
			toData.add(Byte.toString(tempData));
		}
		sendData(toData, "Byte");
	}

	protected static void addValues(char... data) {
		ArrayList<String> toData = new ArrayList<>();
		for (char tempData : data) {
			toData.add(Character.toString(tempData));
		}
		sendData(toData, "Char");
	}

	protected static void addValues(short... data) {
		ArrayList<String> toData = new ArrayList<>();
		for (short tempData : data) {
			toData.add(Short.toString(tempData));
		}
		sendData(toData, "Short");
	}

	protected static void sendData(ArrayList<String> data, String variable) {
		String comma = "";
		arrayAddData = "(";
		for (String getData : data) {
			arrayAddData += comma;
			if (variable == "String") {
				arrayAddData += "\"" + getData + "\"";
			} else {
				arrayAddData += getData;
			}
			comma += ", ";
		}
		arrayAddData += ")";

	}

	protected static void addToColumns(String... columnName) {
		ArrayList<String> sendColumnName = new ArrayList<>();
		for (String tempData : columnName) {
			sendColumnName.add(tempData);
		}

		sendAddToColumn(sendColumnName);
	}

	protected static void sendAddToColumn(ArrayList<String> columnName) {
		String comma = "";
		arrayAddColumn = "(";
		for (String getData : columnName) {
			arrayAddColumn += comma;
			arrayAddColumn += getData;
			comma += ", ";
		}
		arrayAddColumn += ")";
	}

	protected static void insertValues() {
		insertAndAddValues();
	}

	protected static void insertData() {
		insertAndAddValues();
	}

	protected static void insert() {
		insertAndAddValues();
	}

	protected static void insertAndAddValues() {
		String Query = "INSERT INTO " + currentToTable + " " + arrayAddColumn + " VALUES " + arrayAddData + ";";

		try {
			PreparedStatement preparedStmt = conn.prepareStatement(Query);
			preparedStmt.execute();
		} catch (SQLException e) {
			JSimpleSQLErrors.addValuesError();
		}
	}

}
