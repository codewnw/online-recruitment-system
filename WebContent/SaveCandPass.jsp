<jsp:useBean id="scp" class="com.ors.bean.ChangeCandPass" />
<jsp:setProperty name="scp" property="*" />
<% 
	if(scp.changePass()){
		response.sendRedirect("CandidateHomePage.jsp");
	}
	else{
		response.sendRedirect("ChangeCandPass.jsp");
	}
%>