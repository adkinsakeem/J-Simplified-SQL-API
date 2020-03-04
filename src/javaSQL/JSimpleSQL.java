package javaSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JSimpleSQL {
	
	static String HostName;
	static String DBName;
	static String UserName;
	static String Password;
	static Connection conn;
	static String currentToTable;
	static String currentFromTable;
	static boolean DefNum = false;

	static String[] defaultColumns = new String[]{"", "", "", "SQL_Latin1_General_CP1_CI_AS", "NOT NULL", "", "", "", "", "", ""};
	static String[] columns = new String[]{"", "", "", "SQL_Latin1_General_CP1_CI_AS", "NOT NULL", "", "", "", "", "", ""};
	//0.Name, 1.Type, 2.Type Length/Values, 3.Collation, 4.Null, 5.Default, 6.Comments, 7.Extra-2(AutoIncrement), 8.Attributes(Unsigned)
	

	protected static void hostname (String HName) {
		HostName = HName;
	}
	
	protected static void databasename (String databaseName) {
		DBName = databaseName;
	}
	
	protected static void username (String UName) {
		UserName = UName;
	}
	
	protected static void password (String PWord) {
		Password = PWord;
	}
	
	protected static void open() {
		try {
			conn = DriverManager.getConnection(HostName, UserName, Password);
		} catch (SQLException e) {
			try {
				conn = DriverManager.getConnection(HostName+"/"+DBName, UserName, Password);

			}catch (SQLException f){
				JSimpleSQLErrors.connectionError();
			}
		}
	}
		
//***Create The Database*****************************************************
//***************************************************************************
	
	protected static void createDB(String DBName) {createtheDB(DBName);}
	protected static void createDatabase(String DBName) {createtheDB(DBName);}
	protected static void makeDB(String DBName) {createtheDB(DBName);}
	protected static void makeDatabase(String DBName) {createtheDB(DBName);}
	
		protected static void createtheDB(String DBName) {
			try {
			Statement stmt = conn.createStatement();
				stmt.executeUpdate("CREATE DATABASE "+DBName);
			} catch (SQLException e) {
				JSimpleSQLErrors.createDatabaseError();
			}
			
		}
//***Use Database************************************************************
		
		protected static void useDB(String useDBName) {
			try {
				Statement stmt = conn.createStatement();
					stmt.executeUpdate("USE "+useDBName+";");
				} catch (SQLException e) {
					JSimpleSQLErrors.useDatabaseError();
				}
		}
//***Drop The Database******************************************************
		
	protected static void dropDB(String DBDrop) {droptheDB(DBDrop);}
	protected static void dropDatabase(String DBDrop) {droptheDB(DBDrop);}
	protected static void removeDB(String DBDrop) {droptheDB(DBDrop);}
	protected static void removeDatabase(String DBDrop) {droptheDB(DBDrop);}
	protected static void deleteDB(String DBDrop) {droptheDB(DBDrop);}
	protected static void deleteDatabase(String DBDrop) {droptheDB(DBDrop);}
	
	protected static void droptheDB(String DBDrop) {
		try {
			Statement stmt = conn.createStatement();
				stmt.executeUpdate("DROP DATABASE "+DBName);
			} catch (SQLException e) {
				JSimpleSQLErrors.dropDatabaseError();
			}
	}
//*************************************************************************
	
//***Create The Table******************************************************
	
	protected static void createTable(String tableName) {createtheTable(tableName);}
	protected static void makeTable(String tableName) {createtheTable(tableName);}
	
	protected static void createtheTable(String tableName) {
		try {
			Statement stmt = conn.createStatement();
				stmt.executeUpdate("CREATE TABLE "+DBName+" (FirstPlaceHolderColumnToDelete int)");
			} catch (SQLException e) {
				JSimpleSQLErrors.createTableError();
			}
	}
	
//***Drop The Table************************************************************
	
	protected static void dropTable(String tableName) {droptheTable(tableName);}
	protected static void removeTable(String tableName) {droptheTable(tableName);}
	protected static void deleteTable(String tableName) {droptheTable(tableName);}

	
	protected static void droptheTable(String tableName) {
		try {
			Statement stmt = conn.createStatement();
				stmt.executeUpdate("DROP TABLE "+DBName);
			} catch (SQLException e) {
				JSimpleSQLErrors.deleteTableError();
			}
	}
	
//***Get Table******************************************************************************
	
	protected static void columnToTable(String cToTable) {currentToTable = cToTable;}
	protected static void columnFromTable(String cFromTable) {currentFromTable = cFromTable;}
//***Add Columns****************************************************************************
	protected static void columnName(String cName) {if (cName.toUpperCase()=="FirstPlaceHolderColumnToDelete".toUpperCase())JSimpleSQLErrors.defaultColumnNameError();else columns[0]=cName;}
	
	protected static void columnType(String cType) {
		int par1 = cType.indexOf("(");
		int par2 = cType.indexOf(")");
		if(par1 != -1) {
		columns[1]=(cType.substring(0, par1));
		columns[2]=(cType.substring(par1, par2));
		}else {
			columns[1]=cType;
		}
	}
		
		protected static void columnType(String cType, int cLength) {columns[1]=cType; columns[2]=Integer.toString(cLength);}
		protected static void columnType(String cType, String cLength) {columns[1]=cType; columns[2]=cLength;}
		
		protected static void columnCollation(String cColl) {columns[3]=cColl;}
		protected static void columnNull(String cNull) {
			if(cNull.toUpperCase() == "Yes".toUpperCase() || cNull.toUpperCase() == "Y".toUpperCase())
				columns[4]="NULL";
			else if(cNull.toUpperCase() == "No".toUpperCase() || cNull.toUpperCase() == "N".toUpperCase() || cNull.toUpperCase() == "NotNull".toUpperCase())
				columns[4]="NOT NULL";
			else
				columns[4]=cNull;
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
			
			columns[5]=cDefault;
		}
		
		
		protected static void columnComments(String cComments) {columns[6]=cComments;}
		
		protected static void columnExtra(String cExtra) {
			if(cExtra.toUpperCase() == "AUTOINCREMENT" || cExtra.toUpperCase() == "YES" || cExtra.toUpperCase() == "Y" || cExtra.toUpperCase() == "AUTO_INCREMENT")
				columns[7]="AUTO_INCREMENT";
			else if(cExtra.toUpperCase() == "NULL")
				columns[4]="NULL";
			else if (cExtra.toUpperCase() == "NO" || cExtra.toUpperCase() == "N")
				columns[7]="";
			else
				columns[8]=cExtra.toUpperCase();
		}
		
		protected static void createColumn() {addTheColumn();}
		protected static void makeColumn() {addTheColumn();}
		protected static void addColumn() {addTheColumn();}

		
		protected static void addTheColumn() {
			String PrepString = "(ALTER TABLE ";
			PrepString += currentToTable + " ADD ";
			PrepString += columns[0] + " ";
			if(columns[2] == "")
				PrepString+=columns[1];
			else
				PrepString+=columns[1];
			//items with one value
			if(columns[1].toUpperCase() == "VARCHAR" || columns[2].toUpperCase() == "INT" || columns[2].toUpperCase() == "TINYINT" || columns[2].toUpperCase() == "SMALLINT" || columns[2].toUpperCase() == "MEDIUMINT" || columns[2].toUpperCase() == "BIGINT" || columns[2].toUpperCase() == "CHAR" || columns[2].toUpperCase() == "BINARY" || columns[2].toUpperCase() == "VARBINARY" || columns[2].toUpperCase() == "TEXT" || columns[2].toUpperCase() == "BLOB" || columns[2].toUpperCase() == "BIT" || columns[2].toUpperCase() == "INTEGER"  || (columns[2].toUpperCase() == "FLOAT" && !columns[2].contains(","))) {
				PrepString+="("+Integer.parseInt(columns[2])+")";
			}else if(columns[1].toUpperCase() == "FLOAT" || columns[1].toUpperCase() == "DOUBLE" || columns[1].toUpperCase() == "DOUBLE PRECISION" || columns[1].toUpperCase() == "DECIMAL" || columns[1].toUpperCase() == "DEC") {
				PrepString+="("+Integer.parseInt(columns[2].substring(0, columns[2].indexOf(",")))+","+Integer.parseInt(columns[2].substring(columns[2].indexOf(" ")));
			}else
				PrepString+="("+columns[2]+")";	
			
			PrepString+=" COLLATE "+columns[3];
			
			PrepString+= " "+columns[4];
			
			if(columns[5] != "") {
			if(DefNum == true)
			PrepString+=" DEFAULT "+Integer.parseInt(columns[5]);
			else {
				if(columns[5].toUpperCase() == "NULL")
					PrepString+=" DEFAULT NULL";
				else if(columns[5].toUpperCase() == "CURRENT_TIMESTAMP" || columns[5].toUpperCase() == "CURRENTTIMESTAMP" || columns[5].toUpperCase() == "TIMESTAMP")
					PrepString+=" DEFAULT CURRENT_TIMESTAMP";
				else
					PrepString+=" DEFAULT \""+columns[5]+"\"";
			}
			}
			if(columns[6] != "") {
				PrepString+=" COMMENT \""+columns[6]+"\"";
				
			}
			
			if(columns[7] != "AUTO_INCREMENT") {
				PrepString+=" AUTO_INCREMENT PRIMARY KEY";
			}
			
			if(columns[8] == "unsigned".toUpperCase())
				PrepString+=" UNSIGNED";
			
			PrepString+=";";
			
			try {
				PreparedStatement prepStatement = conn.prepareStatement("'"+PrepString+"'");
				prepStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Statement stmt;
			try {
				stmt = conn.createStatement();
			

			ResultSet rs = stmt.executeQuery("SELECT * FROM "+currentToTable+";");
			
			ResultSetMetaData JSimpleMetaData = rs.getMetaData();
			int columns = JSimpleMetaData.getColumnCount();
		    for (int x = 1; x <= columns; x++) {
		        if ("FirstPlaceHolderColumnToDelete".equals(JSimpleMetaData.getColumnName(x))) {
					PreparedStatement dropColStatement = conn.prepareStatement("DROP COLUMN FirstPlaceHolderColumnToDelete");
					dropColStatement.executeUpdate();
					break;
		        }
		    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			}
			
			
		}


	
	
	
}
