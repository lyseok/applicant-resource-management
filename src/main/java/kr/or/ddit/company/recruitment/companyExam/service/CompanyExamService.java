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
	
	public boolean editExamDeleteDate(String examNo);
	
	public boolean editCompanyExam(CompanyExamVO companyExam);
	
	public CompanyExamVO readCompanyExamWithQuestionAndOption(String examNo);

}
