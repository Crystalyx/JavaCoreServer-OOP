package com.faceless.sql;

import java.sql.*;

public class Database
{
	private final String     DB_URL   = "jdbc:mysql://localhost?login=root&password=link";
	private final String     username = "root";
	private final String     password = "link";
	private       Connection connection;
	private       Statement  statement;

	public int executeUpdate(String stmt)
	{
		try
		{
			return statement.executeUpdate(stmt);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return -1;
	}

	public ResultSet executeQuery(String stmt)
	{
		try
		{
			return statement.executeQuery(stmt);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public boolean databaseExist(Connection connection) throws SQLException
	{
		return connection.prepareStatement("SHOW DATABASES LIKE 'VMDB';").executeUpdate() == 1;
	}

	public void connect() throws ClassNotFoundException
	{
		//Тут можно взаимодействовать с ней, добавлять таблицы, заполнять их, получать значения и тд
		//STEP 2: Register JDBC driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//STEP 3: Open a connection
		System.out.println("Connecting to database...");
		try
		{
			boolean           dbExisted = false;
			Connection        conn      = DriverManager.getConnection(DB_URL, username, password);
			PreparedStatement stmt      = conn.prepareStatement("CREATE DATABASE IF NOT EXISTS VMDB;");
			stmt.execute();
			statement = conn.createStatement();
			connection = conn;
			System.out.println("Database created successfully...");

			statement.executeUpdate("USE VMDB;");
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (id INT AUTO_INCREMENT PRIMARY KEY, login VARCHAR(255), password VARCHAR(255));");
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS vms (id INT AUTO_INCREMENT PRIMARY KEY,\n" +
									" owner VARCHAR(255),\n" +
									" vmname VARCHAR(255),\n" +
									" cpuvendor VARCHAR(255),\n" +
									" cpufrequency VARCHAR(255),\n" +
									" cpucorecount INT,\n" +
									" ramvolume INT,\n" +
									" hddvolume INT,\n" +
									" monitor BOOL,\n" +
									"os VARCHAR(255));");
		}
		catch (Exception se)
		{
			//Handle errors for JDBC
			se.printStackTrace();
		}//Handle errors for Class.forName
	}
}