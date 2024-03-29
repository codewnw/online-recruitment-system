package com.ors.presentation.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ors.dao.util.ConnectionProvider;
import com.ors.model.Exam;
import com.ors.service.impl.CompanyServiceImpl;
import com.ors.service.services.CompanyService;

/**
 * Servlet implementation class ExamServlet
 */
@WebServlet("/ExamServlet/*")
public class ExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExamServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("username") == null){
			response.sendRedirect("login.jsp");
		}
		String requestUri = request.getRequestURI();
		System.out.println("Request URI: "+requestUri);
		if(requestUri.contains("create")){
			CompanyService companyService = new CompanyServiceImpl();
			List<Long> jobIds = companyService.getJobIdsOfExamsForCompany((String)session.getAttribute("username"));
			request.setAttribute("jobIds", jobIds);
			
			request.getRequestDispatcher("../company/ExamCreation.jsp").forward(request, response);
		}
		else if(requestUri.contains("view")){
			CompanyService companyService = new CompanyServiceImpl();
			List<Long> jobIds = companyService.getJobIdsOfExamsForCompany((String)session.getAttribute("username"));
			request.setAttribute("jobIds", jobIds);
			List<Exam> exams = companyService.getExamsByJobIdAndCompanyId(0, (String)session.getAttribute("username"));
			request.setAttribute("exams", exams);
			request.getRequestDispatcher("../company/ExamView.jsp").forward(request, response);
		}
		else if(requestUri.contains("delete")){
			CompanyService companyService = new CompanyServiceImpl();
			companyService.deleteExamByExamIdAdnCompanyId(Long.parseLong(request.getParameter("examId")), (String)session.getAttribute("username"));
			List<Long> jobIds = companyService.getJobIdsOfExamsForCompany((String)session.getAttribute("username"));
			request.setAttribute("jobIds", jobIds);
			List<Exam> exams = companyService.getExamsByJobIdAndCompanyId(0, (String)session.getAttribute("username"));
			request.setAttribute("exams", exams);
			request.getRequestDispatcher("../company/ExamView.jsp").forward(request, response);
		}
		else if(requestUri.contains("show-edit")){
			String jobId = request.getParameter("jobId");
			String examId = request.getParameter("examId");
			response.sendRedirect(request.getContextPath() + "/company/EditExam.jsp?jobId="+jobId+"&examId="+examId);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("username") == null){
			response.sendRedirect("login .jsp");
		}
		
		String requestUri = request.getRequestURI();
		System.out.println("Request URI: "+requestUri);
		
		if(requestUri.contains("add")){
			CompanyService companyService = new CompanyServiceImpl();
			String post = "";
			
				Connection con;
				try {
					con = ConnectionProvider.getConnection();
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT POST FROM JOBDETAIL WHERE JOB_ID = "+Long.parseLong(request.getParameter("jobId"))+" AND NAME = '"+(String)session.getAttribute("username")+"'");
				rs.next();
				post = rs.getString(1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			
			
			
			
			Exam exam = new Exam(Long.parseLong(request.getParameter("jobId")), 
					post, 
					Long.parseLong(request.getParameter("eid")), 
					request.getParameter("ename"), 
					Double.parseDouble(request.getParameter("coff")), 
					(String)session.getAttribute("username"));
			System.out.println(exam);
			int i = companyService.addExam(exam);
			System.out.println(i);
			List<Exam> exams = companyService.getExamsByJobIdAndCompanyId(exam.getJobId(),(String)session.getAttribute("username"));
			request.setAttribute("exams", exams);

//			response.sendRedirect(request.getContextPath() + "/ExamView.jsp");
			response.sendRedirect(request.getContextPath() + "/ExamServlet/view");
//						request.getRequestDispatcher("../../ExamView.jsp").forward(request, response);
		}
		else if(requestUri.contains("edit")){
			Exam exam = new Exam(Long.parseLong(request.getParameter("jobId")), 
					request.getParameter("post"), 
					Long.parseLong(request.getParameter("examId")), 
							request.getParameter("examName"), 
					Double.parseDouble(request.getParameter("cutOff")), 
					(String)session.getAttribute("username"));
			
			CompanyService companyService = new CompanyServiceImpl();
			companyService.editExam(exam);
			
			List<Long> jobIds = companyService.getJobIdsOfExamsForCompany((String)session.getAttribute("username"));
			request.setAttribute("jobIds", jobIds);
			List<Exam> exams = companyService.getExamsByJobIdAndCompanyId(0, (String)session.getAttribute("username"));
			request.setAttribute("exams", exams);
			request.getRequestDispatcher("../company/ExamView.jsp").forward(request, response);
		}
	}

}
