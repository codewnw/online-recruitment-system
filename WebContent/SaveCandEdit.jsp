<jsp:useBean id="sce" class="com.ors.bean.SaveCandEdit" />
<jsp:setProperty name="sce" property="*" />
<% 
	String c1=(String) session.getAttribute("username");
	if(sce.editInsert(c1))
	{
		System.out.println("JSP Report:  jsp.SaveCandEdit- Valuse are successfully inserted.");
		response.sendRedirect("CandidateHomePage.jsp");
	}
	else
	{
		System.out.println("JSP Report:  jsp.SaveCandEdit- Valuse did't insert.");
		response.sendRedirect("index.jsp");
	}

%>








