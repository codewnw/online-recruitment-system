/*	****	****	****	-> 	Document   : Login
							->	Created On : January 14, 2012
							->	Programmer : Online Recruitment Project Team
																				****	****	****	*/
	
package com.ors.bean;
import java.sql.*;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;

import com.ors.dao.util.ConnectionProvider; 



public class Login extends HttpServlet
{ 
	public String status=null;
	Connection con=null;
	boolean flag=false;
	
	public boolean login(String s1,  String s2)
	{
		String un=s1;
		String pass=s2;
		try
		{	
			if(con==null){
				con = ConnectionProvider.getConnection();
				System.out.println("            ...!!!...            ");
				System.out.println("JavaReport: com.ors.bean.Login- Connection has been created.");
			}
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM LOGIN");
			while(rs.next()){
				if(un.equals(rs.getString(1))&&pass.equals(rs.getString(2))){
					status=rs.getString(3);
					flag=true;
				}
			}
			System.out.println("JavaReport: com.ors.bean.Login- Stutus has been fetched.");
			
				
		}
		catch(Exception e){
			System.out.println("ExceptionFrom: Login- "+e);
		}
	return flag;
	}
}









