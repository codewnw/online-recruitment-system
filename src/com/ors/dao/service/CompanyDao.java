package com.ors.dao.service;

import java.util.List;

import com.ors.model.Company;
import com.ors.model.Exam;
import com.ors.model.Job;

public interface CompanyDao {
	public int registerCompany(Company company);
	public int updateCompany(Company company);
	public int createJob(Job job);
	
	
	/**
	 * Job
	 */
	public int deletJobById(long jobId, String companyId);
	public int updateJobById(Job job);
	
	/**
	 * Exam
	 */
	public int addExam(Exam exam);
	public List<Exam> getExamsByJobIdAndCompanyId(long jobId, String comapanyId);
	public int deleteExamByExamIdAdnCompanyId(Long examId, String companyId);
	public int editExam(Exam exam);
	public List<Long> getJobIdsOfExamsForCompany(String comapanyId);
	
}
