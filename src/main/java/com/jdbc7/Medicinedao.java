package com.jdbc7;

import java.sql.Connection;
import java.sql.DriverManager;

public class Medicinedao {
Connection con=null;
	
	//method to get connection to db
	public void dbconnection()throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineMedicalStore","root","123456");
}
}