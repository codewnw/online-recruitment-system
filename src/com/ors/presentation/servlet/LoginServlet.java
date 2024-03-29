package com.ors.presentation.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ors.dao.impl.CandidateDaoImpl;
import com.ors.dao.service.CandidateDao;
import com.ors.model.Login;
import com.ors.service.impl.LoginServiceImpl;
import com.ors.service.services.LoginService;
import com.sun.org.apache.xml.internal.security.c14n.CanonicalizerSpi;

@WebServlet(urlPatterns = { "/LoginServlet", "/UpdatePassword" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestedURI = request.getRequestURI();

		if (requestedURI.contains("LoginServlet")) {

			String userName = request.getParameter("username");
			String password = request.getParameter("password");

			Login login = new Login();
			login.setUserName(userName);
			login.setPassword(password);

			LoginService loginService = new LoginServiceImpl();
			boolean isValidUser = loginService.checkLogin(login);

			if (isValidUser) {

				HttpSession session = request.getSession();
				session.setAttribute("username", userName);

				String userType = loginService.getUserType(userName);

				if (userType.equals("admin")) {
					request.getRequestDispatcher("admin/AdminHomePage.jsp").forward(request, response);
				} else if (userType.equals("comp")) {
					session.setAttribute("user", userName);
					request.getRequestDispatcher("company/CompanyHomePage.jsp").forward(request, response);
				} else if (userType.equals("cand")) {
					CandidateDao candidateDao = new CandidateDaoImpl();
					session.setAttribute("user", candidateDao.getCandidateByUserName(userName));
					request.getRequestDispatcher("candidate/CandidateHomePage.jsp").forward(request, response);
				} else {
					response.sendRedirect("index.jsp");
				}
			} else {
				response.sendRedirect("common/Login.jsp");
			}

		} else if (requestedURI.contains("UpdatePassword")) {
			String userName = request.getParameter("username");
			String oldPassword = request.getParameter("oldPassword");
			String password = request.getParameter("password");
			System.out.println(userName + " " + oldPassword + " " + password);
			LoginService loginService = new LoginServiceImpl();
			int updateCount = loginService.updatePassword(userName, oldPassword, password);
			System.out.println(updateCount);
			if (updateCount > 0) {

				HttpSession session = request.getSession();
				session.setAttribute("username", userName);

				String userType = loginService.getUserType(userName);
				System.out.println(userType);
				if (userType.equals("admin")) {
					request.getRequestDispatcher("admin/AdminHomePage.jsp").forward(request, response);
				} else if (userType.equals("comp")) {
					session.setAttribute("user", userName);
					request.getRequestDispatcher("company/CompanyHomePage.jsp").forward(request, response);
				} else if (userType.equals("cand")) {
					CandidateDao candidateDao = new CandidateDaoImpl();
					session.setAttribute("user", candidateDao.getCandidateByUserName(userName));
					request.getRequestDispatcher("candidate/CandidateHomePage.jsp").forward(request, response);
				} else {
					response.sendRedirect("index.jsp");
				}
			}
		}
	}

}
