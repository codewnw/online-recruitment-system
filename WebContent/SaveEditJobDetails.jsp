<%@page import="com.ors.dao.util.ConnectionProvider,java.sql.*;"%>
<jsp:useBean id="sejd" class="com.ors.bean.SaveEditJobDetails" />
<jsp:setProperty name="sejd" property="*" />
<%
	String c1=(String) session.getAttribute("username");
	String name=null;
	try{
		Connection con=ConnectionProvider.getConnection();
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("SELECT * FROM COMPANYREGISTER WHERE USERNAME='"+c1+"'");
		while(rs.next()){
			name=rs.getString(2);
		}}
		catch(Exception e){
			out.print("ExceptonFrom: file.SaveEditJobDetails- "+e);
		}
		if(sejd.upJobDetails(name)){
			response.sendRedirect("ViewJobDetails.jsp");}
			else{
				response.sendRedirect("EditJob.jsp");
			}
		
%>