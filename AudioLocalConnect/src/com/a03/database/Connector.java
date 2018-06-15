package com.a03.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Connector {
	private Connection c = null;
	public Connection getConnection(){
		if(c != null){
			return c;
		}
		try {
			return connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private static Connection connect() throws Exception{


		Connection connection = null;

		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			String url = "jdbc:sqlserver://tataiserver.database.windows.net:1433;database=Tatai;user=playerConnect;password=Cool1234;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
			// Set connection properties.
			// get connection
			//DriverManager.setLoginTimeout(10);
			connection = DriverManager.getConnection(url);

		}
		catch (SQLException e)
		{
			throw new SQLException("Failed to create connection to database.", e);
		}
		if (connection != null) 
		{ 

		}else{
			System.out.print("Failed to connect");
		}

		return connection;

	}
	public void attemptedMultiplayer(String gameId, String token) {
		Connection c = null;
		try {
			c = getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (c != null) 
		{ 
			try
			{
				String username = null;
				Statement s = c.createStatement();
				
				ResultSet rs = s.executeQuery("SELECT username FROM token_info WHERE token = '" + token + "'");
				rs.next();
				username = rs.getString("username");
				s.execute("UPDATE MULTIPLAYER_LEVEL SET ATTEMPT = ATTEMPT + 1 WHERE GAME_ID = '" + gameId + "' AND USERNAME = '" + username +"' AND "
						+ "LEVEL = (SELECT MAX(LEVEL) FROM MULTIPLAYER_LEVEL WHERE GAME_ID = '" +  gameId + "')");
			}catch (SQLException e)
			{

				try {
					throw new SQLException("Error with attempted function.", e);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}



			}
		}
	}
	public void attempted(String token) {
		Connection c = null;
		try {
			c = getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (c != null) 
		{ 
			try
			{
				CallableStatement s = c.prepareCall("{call attempt_increment (?, ?)}");
				s.setString(1, token);
				s.registerOutParameter(2, java.sql.Types.INTEGER);

				s.execute();

			}
			catch (SQLException e)
			{

				try {
					throw new SQLException("Error with attempted function.", e);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}       
		}
	}
	public void passMultiplayer(String gameId, String token) {
		Connection c = null;
		try {
			c = getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (c != null) 
		{ 
			try
			{
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery("SELECT username FROM token_info WHERE token = '" + token + "'");
				rs.next();
				String uname = rs.getString("username");

				s.execute("UPDATE MULTIPLAYER_LEVEL SET CORRECT = 1 WHERE LEVEL = "
						+ "(SELECT MAX(LEVEL) FROM MULTIPLAYER_LEVEL WHERE GAME_ID = '" + gameId +  "' AND USERNAME = ' " + 
						uname + "')");
			}
			catch (SQLException e)
			{

				try {
					throw new SQLException("Error with attempted function.", e);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}       
		}
	}

	public void pass(String token) {
		Connection c = null;
		try {
			c = getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (c != null) 
		{ 
			try
			{
				CallableStatement s = c.prepareCall("{call pass (?)}");
				s.setString(1, token);
				s.execute();

			}
			catch (SQLException e)
			{

				try {
					throw new SQLException("Error with attempted function.", e);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}       
		}
	}

}
