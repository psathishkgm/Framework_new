package com.databaseconnectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Reporter;
import org.testng.annotations.Test;
import com.framework.pages.BaseClass;

public class Database extends BaseClass {
	
	@Test
	
	public void test1() throws ClassNotFoundException, SQLException {
		
		logger = report.createTest("Database Connection");
		Reporter.log("Database Connect",true);
		Class.forName("com.mysql.jdbc.Driver");
		Reporter.log("Driver loaded",true);
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/selenium", "root", "selenium");
		Reporter.log("Connection given",true);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("Select * from database");
		while(rs.next())//next is first element by default
		{
			String sele = rs.getString("Selenium");
			System.out.println(sele);
		}
	}
}
