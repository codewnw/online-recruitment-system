<jsp:useBean id="complogval" class="com.ors.bean.CompLogValues"/>
<jsp:useBean id="compregval" class="com.ors.bean.CompRejValues"/>
<jsp:setProperty name="complogval" property="*"/>
<jsp:setProperty name="compregval" property="*"/>
<%
	if(complogval.compLogInsert()){ 
		if(compregval.compRegInsert())
			response.sendRedirect("Login.jsp");
		else
			response.sendRedirect("index.jsp");
	}
	else{
		response.sendRedirect("index.jsp");
	}
	
%>
