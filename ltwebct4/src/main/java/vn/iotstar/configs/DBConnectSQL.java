package vn.iotstar.configs;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class DBConnectSQL {
	private final String serverName = "DESKTOP-1MKJ06O\\SQLEXPRESS";
	private final String dbName = "LTWeb";
	private final String portNumber = "1433";
		private final String userID = "sa";
	private final String password = "12345";
	
	Connection conn = null;
	public Connection getConnection() throws Exception {
			String	url = "jdbc:sqlserver://" + serverName  + ";databaseName=" + dbName;		
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");			
			return DriverManager.getConnection(url, userID, password);	
	}

	public static void main(String[] args) {
		try {
			System.out.println(new DBConnectSQL().getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}