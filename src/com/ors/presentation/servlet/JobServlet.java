package com.ors.presentation.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ors.model.Exam;
import com.ors.model.Job;
import com.ors.service.impl.CompanyServiceImpl;
import com.ors.service.services.CompanyService;

@WebServlet("/JobServlet/*")
public class JobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JobServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getPathInfo());
		HttpSession session = request.getSession(false);
		if (session.getAttribute("username") == null) {
			response.sendRedirect("login.jsp");
		}
		String requestUri = request.getRequestURI();
		System.out.println("Request URI: " + requestUri);
		if (requestUri.contains("delete")) {
			System.out.println("I am deleting.");
			CompanyService companyService = new CompanyServiceImpl();
			int i = companyService.deletJobById(Long.parseLong(request.getParameter("jobId")),
					(String) session.getAttribute("username"));
			if (i == 1) {
				response.sendRedirect(request.getContextPath() + "/company/ViewJobDetails.jsp");
			} else {
				response.sendRedirect(request.getContextPath() + "/company/index.jsp");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("username") == null) {
			response.sendRedirect("login.jsp");
		} else {
			String requestUri = request.getRequestURI();
			System.out.println("Request URI: " + requestUri + " " + session.getAttribute("username"));
			if (requestUri.contains("add")) {
				String companyId = (String) session.getAttribute("username");
				long jobId = Long.parseLong(request.getParameter("jid"));
				String post = request.getParameter("post");
				String criteria = request.getParameter("criteria");
				int vacancies = Integer.parseInt(request.getParameter("vacancies"));
				long salary = Long.parseLong(request.getParameter("salary"));
				String expiryDate = request.getParameter("lastdate");

				Job job = new Job(jobId, post, criteria, vacancies, salary, expiryDate, companyId);
				CompanyService companyService = new CompanyServiceImpl();
				int createJob = companyService.createJob(job);

				if (createJob == 1) {
//					RequestDispatcher rd = request.getRequestDispatcher("/CompanyHomePage.jsp");
//					rd.forward(request, response);
					response.sendRedirect(request.getContextPath() + "/company/CompanyHomePage.jsp");
				} else {
//					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
//					rd.forward(request, response);
					response.sendRedirect(request.getContextPath() + "/index.jsp");
				}
			} else if (requestUri.contains("edit")) {
				System.out.println("I am editing.");
				String companyId = (String) session.getAttribute("username");
				long jobId = Long.parseLong(request.getParameter("jid"));
				String post = request.getParameter("post");
				String criteria = request.getParameter("criteria");
				int vacancies = Integer.parseInt(request.getParameter("vacancies"));
				long salary = Long.parseLong(request.getParameter("salary"));
				String expiryDate = request.getParameter("lastdate");

				Job job = new Job(jobId, post, criteria, vacancies, salary, expiryDate, companyId);
				CompanyService companyService = new CompanyServiceImpl();
				int editJob = companyService.updateJobById(job);
				if (editJob == 1) {
//					RequestDispatcher rd = request.getRequestDispatcher(request.getContextPath() + "/ViewJobDetails.jsp");
//					rd.forward(request, response);
					response.sendRedirect(request.getContextPath() + "/company/ViewJobDetails.jsp");
				} else {
//					RequestDispatcher rd = request.getRequestDispatcher(request.getContextPath() + "/CompanyHomePage.jsp");
//					rd.forward(request, response);
					response.sendRedirect(request.getContextPath() + "/company/CompanyHomePage.jsp");
				}
			}
		}

	}

}
