package kr.or.ddit.company.recruitment.companyExam.service;

import java.util.List;

import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.recruitment.ComExamOptionVO;
import kr.or.ddit.vo.recruitment.ComExamQuestionsVO;
import kr.or.ddit.vo.recruitment.CompanyExamVO;

public interface CompanyExamService {
	public List<CompanyExamVO> readCompanyExamListById(String userId);
	
	public CompanyVO readCompanyById(String userId);
	
	public void createCompanyExam(CompanyExamVO companyExam);
	
	public void createCompanyExamQuestions(ComExamQuestionsVO comExamQuestion);
	
	public void createCompanyExamOptions(ComExamOptionVO comExamOption);
	
	public boolean removeCompanyExam(String examNo);
}
