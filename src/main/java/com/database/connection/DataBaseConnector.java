package com.database.connection;

/**
 * @author Tran Viet Duc
 * 
 * @version 0.1
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import com.selenium.common.*;


public class DataBaseConnector {

	private static String connectionUrl;
	private static String dbusername;
	private static String dbpassword;

	// Should be defined as jdbc:mysql://host:port/database name

	public static String executeSQLQuery(String sqlQuery) {

		BasicProperties bp = new BasicProperties();

		connectionUrl = bp.load(Global.FileEnv).getProperty("DB_STRING");
		dbusername = bp.load(Global.FileEnv).getProperty("DB_USER");
		dbpassword = bp.load(Global.FileEnv).getProperty("DB_PWD");
		Connection connection;
		String resultValue = "";
		ResultSet rs;

		// To connect with mysql database

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(connectionUrl, dbusername,
					dbpassword);
			if (connection != null) {
				System.out.println("Connected to the database...");
			} else {
				System.out.println("Database connection failed to " + connectionUrl
						+ " Environment");
			}
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlQuery);

			try {
				while (rs.next()) {
					resultValue = rs.getString(1).toString();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException err) {
				System.out
						.println("No Records obtained for this specific query");
				err.printStackTrace();
			}
			connection.close();

		} catch (SQLException sqlEx) {
			System.out.println("SQL Exception:" + sqlEx.getStackTrace());
		}
		return resultValue;
	}

	public static ArrayList<String> executeSQLQuery_List(String sqlQuery) {
		String connectionUrl = "";
		Connection connection;
		ArrayList<String> resultValue = new ArrayList<String>();
		ResultSet resultSet;

		// To connect with QA Database

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(connectionUrl, dbusername,
					dbpassword);
			if (connection != null) {
				System.out.println("Connected to the database");
			} else {
				System.out.println("Failed to connect to " + connectionUrl
						+ " database");
			}
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQuery);

			try {
				while (resultSet.next()) {
					int columnCount = resultSet.getMetaData().getColumnCount();
					StringBuilder stringBuilder = new StringBuilder();
					for (int iCounter = 1; iCounter <= columnCount; iCounter++) {
						stringBuilder.append(resultSet.getString(iCounter)
								.trim() + " ");
					}
					String reqValue = stringBuilder.substring(0,
							stringBuilder.length() - 1);
					resultValue.add(reqValue);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException ex) {
				System.out.println("No Records found for this specific query"
						+ ex.getStackTrace());
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException ex) {
						System.out.println("SQL Exception:"
								+ ex.getStackTrace());
					}
				}
			}

		} catch (SQLException sqlEx) {
			System.out.println("SQL Exception:" + sqlEx.getStackTrace());
		}
		return resultValue;
	}

}
