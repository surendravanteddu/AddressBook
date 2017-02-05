package org.suri.myWebservice.mobileApp;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/person", "root", "");
			return con;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	public void close(Connection con) {
		try{
			con.close();
		}catch(Exception ex){
			
		}
	}
    public static void main(String[] args) {
		Database d = new Database();
		System.out.println(d.getConnection());
	}
}
