package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.sql.Date;

public class DBConnection {
	private Connection connection;
	
	public DBConnection(){
		
	}
	
	public boolean connectToDB(String host, String database, String user, String pwd){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionCommand = "jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+pwd;
			setConnection(DriverManager.getConnection(connectionCommand));
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
}
