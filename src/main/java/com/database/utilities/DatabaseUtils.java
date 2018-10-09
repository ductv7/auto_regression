package com.database.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.selenium.base.*;
import com.selenium.common.BasicProperties;
import com.selenium.common.Global;

public class DatabaseUtils extends BasePage{
	
	BasicProperties bp = new BasicProperties();
	String url = bp.load(Global.FileEnv).getProperty("DB_STRING");
	String username = bp.load(Global.FileEnv).getProperty("DB_USER");
	String password = bp.load(Global.FileEnv).getProperty("DB_PWD");
	String driver;
	ResultSet rs;
	
	protected static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal();
	
	protected DatabaseUtils(String url, String username, String password){
		
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public String getDriver(){
		
		return driver;
	}
	
	public synchronized Connection setUpConnection(){
		
		try {
			
			Class.forName(driver);
			connectionThreadLocal.set(DriverManager.getConnection(url, username, password));
			
		}catch (SQLException | ClassNotFoundException sql){
			
			sql.getMessage();
		}
		
		return connectionThreadLocal.get();
	}
	
}
