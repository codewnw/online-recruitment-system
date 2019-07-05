package com.ors.presentation.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ors.constant.Action;
import com.ors.model.Candidate;

@WebServlet("/CandidateServlet/*")
public class CandidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CandidateServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestUri = request.getRequestURI();
		String[] requestUriSplit = requestUri.split("/");
		String action = requestUriSplit[requestUriSplit.length - 1];

		System.out.println(">> " + this.getClass().getSimpleName() + " Action is " + action);

		switch (action) {

		case "Add":
			addCandidate();
			break;
		case "View":
			viewCandidate();
			break;
		case "Edit":
			editCandidate(request, response);
			break;

		default:
			viewCandidate();
			break;
		}
	}

	private void editCandidate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Candidate cand = new Candidate();
		cand.setName("Test");
		request.setAttribute("cand", cand);
		request.getRequestDispatcher("../candidate/edit-candidate-profile.jsp").forward(request, response);
	}

	private void viewCandidate() {
		// TODO Auto-generated method stub

	}

	private void addCandidate() {
		// TODO Auto-generated method stub

	}

}
