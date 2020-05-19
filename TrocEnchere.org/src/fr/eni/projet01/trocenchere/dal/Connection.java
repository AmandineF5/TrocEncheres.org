
// LESLIE ET JANET

package fr.eni.projet01.trocenchere.dal;

import java.sql.DriverManager;
import java.sql.SQLException;





public class Connection {
	private static java.sql.Connection connection;
	
	public static java.sql.Connection getConnection() {
		if (connection == null) {
			try {
				String url = Settings.getProperty("url");
				connection = DriverManager.getConnection(url, Settings.getProperty("user"), Settings.getProperty("password"));
				connection.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return connection;
	}

	public static void closeConnection() {
		try {
			if (connection!=null) {
				connection.close();				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			connection= null;
		}
	}
	
}
