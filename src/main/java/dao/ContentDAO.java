package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ContentDAO {
	public Connection getConnection() {
		// 1. driver
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kic24", "1234");
			return conn;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// 2. connection
		catch (SQLException e) {
			e.printStackTrace();
		} // end of tryCatch
		
		return null;
		
	} // end of geConnection()

}
