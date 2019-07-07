package com.ors.presentation.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ors.model.Company;
import com.ors.model.Login;
import com.ors.service.impl.CompanyServiceImpl;
import com.ors.service.impl.LoginServiceImpl;
import com.ors.service.services.CompanyService;
import com.ors.service.services.LoginService;

@WebServlet("/RegisterCompanyServlet")
public class RegisterCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterCompanyServlet() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String compName = request.getParameter("compname");
		String address = request.getParameter("addr");
		String postInComp = request.getParameter("postincomp");
		String criteria = request.getParameter("criteria");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		int contact = Integer.parseInt(request.getParameter("contact"));
		int cutOff = Integer.parseInt(request.getParameter("cutoff"));

		Company company = new Company(compName, address, postInComp, criteria, userName, email, contact, cutOff);

		CompanyService companyService = new CompanyServiceImpl();

		int registeredUserCount = companyService.registerCompany(company);

		int loginInsertCount = 0;
		if (registeredUserCount == 1) {

			Login login = new Login();
			login.setUserName(userName);
			login.setPassword(password);
			login.setUserType("comp");

			LoginService loginService = new LoginServiceImpl();
			loginInsertCount = loginService.insertLogin(login);
		}

		if (loginInsertCount == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("username", company.getUserName());
			response.sendRedirect("company/CompanyHomePage.jsp");
		} else {
			response.sendRedirect("index.jsp");
		}
	}

}
