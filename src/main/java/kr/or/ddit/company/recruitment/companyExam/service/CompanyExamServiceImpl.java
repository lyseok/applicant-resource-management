package kr.or.ddit.company.recruitment.companyExam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.mapper.recruitment.ComExamOptionMapper;
import kr.or.ddit.mapper.recruitment.ComExamQuestionsMapper;
import kr.or.ddit.mapper.recruitment.CompanyExamMapper;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.recruitment.ComExamOptionVO;
import kr.or.ddit.vo.recruitment.ComExamQuestionsVO;
import kr.or.ddit.vo.recruitment.CompanyExamVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyExamServiceImpl implements CompanyExamService {
	private final CompanyExamMapper companyExamMapper;
	private final ComExamQuestionsMapper comExamQuestionsMapper;
	private final ComExamOptionMapper comExamOptionMapper;
	private final CompanyMapper companyMapper;
	
	@Override
	public List<CompanyExamVO> readCompanyExamListById(String userId) {
		return companyExamMapper.selectCompanyExamListById(userId);
	}

	@Transactional
	@Override
	public void createCompanyExam(CompanyExamVO companyExam) {	
		companyExamMapper.insertCompanyExam(companyExam);
	}

	@Transactional
	@Override
	public void createCompanyExamQuestions(ComExamQuestionsVO comExamQuestion) {
		comExamQuestionsMapper.insertComExamQuest(comExamQuestion);
		
	}


	@Override
	public void createCompanyExamOptions(ComExamOptionVO comExamOption) {
		comExamOptionMapper.insertComExamOption(comExamOption);
		
	}

	@Override
	public CompanyVO readCompanyById(String userId) {
		return companyMapper.selectCompanyById(userId);
		
	}

	@Transactional
	@Override
	public boolean removeCompanyExam(String examNo) {
		
		companyExamMapper.deleteComExamOptionByExamNo(examNo);
		companyExamMapper.deleteComExamQuestByExamNo(examNo);
		return companyExamMapper.deleteCompanyExam(examNo) > 0;
	}

}
