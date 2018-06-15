package com.a03.server;

import java.sql.*;

import com.a03.login.LoginImpl;
import com.a03.login.LoginImplService;

/**
 * This contains methods that communicate with the server to register, login and
 * authenticate users.
 * @author Matt
 *
 */
public class LoginHandler {
	Session session = Session.getInstance();
			
	public LoginHandler(){

	}

	public void register(String username, String userPswd) throws Exception{
		LoginImplService serv = new LoginImplService();
		LoginImpl impl = serv.getLoginImplPort();
		impl.register(username, userPswd);
		

	}

	public boolean login(String username, String password) throws Exception {

		LoginImplService serv = new LoginImplService();
		LoginImpl impl = serv.getLoginImplPort();
		String token =  impl.login(username, password);
		
		session.setToken(token);
		
		
		return (token != null);
	}


	public boolean checkUsernameConflict(String name) throws SQLException{
		LoginImplService serv = new LoginImplService();
		LoginImpl impl = serv.getLoginImplPort();
		return impl.isUsernameConflict(name);
		
	}







}

