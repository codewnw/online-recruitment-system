<%@page import="java.io.*,java.sql.*,com.ors.dao.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Online Recruitment System</title>

<style type="text/css">
<!--
.body {
	margin: 0%;
}

a:link {
	color: #0066CC;
	text-decoration: underline;
}

a:visited {
	text-decoration: underline;
	color: #663399;
}

a:hover {
	text-decoration: none;
	color: #996666;
}

a:active {
	text-decoration: underline;
	color: #FF9999;
}

.style5 {
	color: #006633;
	font-weight: bold;
	font-size: 18px;
}

.style7 {
	color: #CC0000
}

.style8 {
	color: #000099
}

.style11 {
	color: #CC0000;
	font-weight: bold;
}

.style12 {
	color: #663399;
	font-weight: bold;
}
-->
</style>
</head>

<body style="margin: 0px">
	<table width="100%" height="472" border="1" align="left">
		<tr>
			<td height="45" colspan="2" bgcolor="#666734"><jsp:include
					page="/admin/AdminHeader.jsp" /></td>
		</tr>
		<tr>
			<td width="2%" bgcolor="#666734"><jsp:include
					page="/admin/Adminmenu.jsp" /></td>
			<td width="98%" valign="top" bgcolor="#FFFFFF"><table
					width="100%" height="109" border="1" align="center">
					<tr>
						<td height="31" colspan="16" bordercolor="#663300"
							bgcolor="#99BBB7"><div align="center"
								class="style8 style63 style5">Candidate Details</div></td>
					</tr>
					<tr>
						<td width="88" height="33" bordercolor="#663300" bgcolor="#99BBB7"><div
								align="center">
								<span class="style11">Username</span>
							</div></td>
						<td width="162" bordercolor="#663300" bgcolor="#99BBB7"><div
								align="center" class="style64 style7">
								<strong> Name</strong>
							</div></td>
						<td width="123" bordercolor="#663300" bgcolor="#99BBB7"><div
								align="center" class="style64 style7">
								<strong>Gender</strong>
							</div></td>
						<td width="117" bordercolor="#663300" bgcolor="#99BBB7"><div
								align="center" class="style64 style7">
								<strong>DOB</strong>
							</div></td>
						<td width="176" bordercolor="#663300" bgcolor="#99BBB7"><div
								align="center" class="style64 style7">
								<strong>Is Experienced</strong>
							</div></td>
						<td width="112" bordercolor="#663300" bgcolor="#99BBB7"><div
								align="center" class="style64 style7">
								<strong>View</strong>
							</div></td>
						<td width="77" bordercolor="#663300" bgcolor="#99BBB7"><div
								align="center" class="style64 style7">
								<strong>Delete</strong>
							</div></td>
						
					</tr>
					<%
						try {
							Connection con = ConnectionProvider.getConnection();
							Statement st = con.createStatement();
							ResultSet rs = st.executeQuery("select * from ORS_CANDIDATE");
							String uid = "", name = "", gender = "", dob = "", isExperienced = "";
							while (rs.next()) {
								uid = rs.getString(1);
								name = rs.getString(2);
								gender = rs.getString(3);
								dob = rs.getString(4);
								isExperienced = rs.getBoolean(5)+"";
					%>
					<tr>
						<td height="35" bordercolor="#663300" bgcolor="#99BBB7"><div
								align="center"><%=uid%></div></td>
						<td bordercolor="#663300" bgcolor="#99BBB7"><div
								align="center"><%=name%></div></td>
						<td bordercolor="#663300" bgcolor="#99BBB7"><div
								align="center"><%=gender%></div></td>
						<td bordercolor="#663300" bgcolor="#99BBB7"><div
								align="center"><%=dob%></div></td>
						<td bordercolor="#663300" bgcolor="#99BBB7"><div
								align="center"><%=isExperienced%></div></td>
						<td bordercolor="#663300" bgcolor="#99BBB7"><div
								align="center">
								<a href="Resume.jsp?uid=<%=uid%>"><strong>View</strong></a>
							</div></td>
							<td bordercolor="#663300" bgcolor="#99BBB7"><div
								align="center">
								<a href="Delete.jsp?uid=<%=uid%>" class="style12">Delete</a>
							</div></td>
					</tr>
					<%
						}
						} catch (Exception e) {
							out.println(e);
						}
					%>
				</table></td>
		</tr>
	</table>
</body>
</html>