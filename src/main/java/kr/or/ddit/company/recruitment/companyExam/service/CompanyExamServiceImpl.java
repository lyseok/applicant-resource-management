package kr.or.ddit.company.recruitment.companyExam.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
		String examNo = companyExam.getComExamNo();
		
		for (ComExamQuestionsVO q: companyExam.getQuestionList()) {
			q.setComExamNo(examNo);
			comExamQuestionsMapper.insertComExamQuest(q);
			String questionNo = q.getComQuestionsNo();
			
			for (ComExamOptionVO o : q.getOptionList()) {
				o.setComQuestionsNo(questionNo);
				comExamOptionMapper.insertComExamOption(o);
			}
		}
	}



	@Override
	public CompanyVO readCompanyById(String userId) {
		return companyMapper.selectCompanyById(userId);
	}

	
	@Override
	public CompanyExamVO readCompanyExamWithQuestionAndOption(String examNo) {
		return companyExamMapper.selectCompanyExamWithQuestionAndOption(examNo);
	}


	@Override
	public boolean editExamDeleteDate(String examNo) {
		return companyExamMapper.updateExamDeleteDate(examNo);
	}

	@Transactional
	@Override
	public boolean editCompanyExamInfo(CompanyExamVO companyExam) {
		String examNo = companyExam.getComExamNo();
		
		int update = companyExamMapper.updateCompanyExam(companyExam);
		if(update == 0) return false;
		
		
		List<ComExamQuestionsVO> existingQuestions = comExamQuestionsMapper.selectByQuestionExamNo(examNo);
		Set<String> incomingQNo = new HashSet<>();
		
		for(ComExamQuestionsVO q : companyExam.getQuestionList()) {
			q.setComExamNo(examNo);
			
			if(q.getComQuestionsNo() == null) {
				comExamQuestionsMapper.insertComExamQuest(q);
			}else {
				comExamQuestionsMapper.updateComExamQuest(q);
			}
			
			String questionNo = q.getComQuestionsNo();
			incomingQNo.add(questionNo);
			
			
			List<ComExamOptionVO> existingOptions = comExamOptionMapper.selectByQuestionNo(questionNo);
		
		
			
			Set<String> incomingONo = new HashSet<>();
			
			for(ComExamOptionVO o : q.getOptionList()) {
				o.setComQuestionsNo(questionNo);
				
				if(o.getComOptionNo() == null) {
					comExamOptionMapper.insertComExamOption(o);
				}else {
					comExamOptionMapper.updateComExamOption(o);
				}
				incomingONo.add(o.getComOptionNo());				
			}
			
			
			for(ComExamOptionVO existO : existingOptions) {
				if (!incomingONo.contains(existO.getComOptionNo())) {
					comExamOptionMapper.updateDeleteDateComExamOption(existO.getComOptionNo());
				}
			}
			
			
			for(ComExamQuestionsVO existQ : existingQuestions ) {
				if(!incomingQNo.contains(existQ.getComQuestionsNo())){
					comExamOptionMapper.updateDeleteDateByQuestionNo(existQ.getComQuestionsNo());
					comExamQuestionsMapper.updateDeleteDateComExamQuestion(existQ.getComQuestionsNo());
				}
			}
			
			
		}
		return true;
	}

	

}
