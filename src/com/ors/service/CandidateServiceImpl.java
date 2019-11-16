package com.ors.service;

import java.util.List;

import com.ors.dao.CandidateDao;
import com.ors.dao.CandidateDaoImpl;
import com.ors.model.Candidate;

public class CandidateServiceImpl implements CandidateService {

	private CandidateDao candidateDao;

	public CandidateServiceImpl() {
		candidateDao = new CandidateDaoImpl();
	}

	@Override
	public boolean update(Candidate candidate) {
		return candidateDao.update(candidate);
	}

	@Override
	public int insert(Candidate candidate) {
		return candidateDao.insert(candidate);
	}

	@Override
	public int delete(Candidate candidate) {
		return candidateDao.delete(candidate);
	}

	@Override
	public Candidate getCandidateByUserName(String userName) {
		return candidateDao.getCandidateByUserName(userName);
	}

	@Override
	public List<Candidate> getCandidates() {
		return candidateDao.getCandidates();
	}

}
