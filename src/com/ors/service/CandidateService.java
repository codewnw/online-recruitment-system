package com.ors.service;

import java.util.List;

import com.ors.model.Candidate;

public interface CandidateService {
	public int insert(Candidate candidate);

	boolean update(Candidate candidate);

	public int delete(Candidate candidate);

	Candidate getCandidateByUserName(String userName);

	public List<Candidate> getCandidates();
}
