package com.a03.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.a03.game.MaoriNumbers;
import com.a03.multiplayer.NextMultiplayerLevelImpl;
import com.a03.multiplayer.NextMultiplayerLevelImplService;


import com.a03.recieveaudio.RecieveMultiplayerImpl;
import com.a03.recieveaudio.RecieveMultiplayerImplService;

public class Session {


	private static Session instance = null;
	private String token;
	private String _user = "";
	private static Connection c = null;
	private String currentGameId = null;

	public static Session getInstance(){
		if(instance == null){
			try {
				c = connect();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			instance = new Session();

		}
		return instance;
	}

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

	public void setUser(String user){
		_user = user;
	}

	public void setToken(String t) {
		token = t;
	}

	public String getUser(){
		return _user;
	}
	private static Connection connect() throws Exception{


		Connection connection = null;

		try
		{
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

	
	public boolean checkMultiplayerAnswer(byte[] b, List<MaoriNumbers> m ) {
		System.out.println("getting answer");
		RecieveMultiplayerImplService serv = new RecieveMultiplayerImplService();
		RecieveMultiplayerImpl r = serv.getRecieveMultiplayerImplPort();
		return r.recieveAudio(b, m, token, "0f9875b4-4372-45dc-9d1a-955d89083177");
	}





	
	public String nextMultiplayerLevel(int i) {
		NextMultiplayerLevelImplService s = new NextMultiplayerLevelImplService();
		NextMultiplayerLevelImpl impl =  s.getNextMultiplayerLevelImplPort();
		return impl.nextMultiplayerLevel(_user,"0f9875b4-4372-45dc-9d1a-955d89083177");
	
		
	}
}
