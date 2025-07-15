package kr.or.ddit.company.recruitment.exam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.mapper.recruitment.ComExamOptionMapper;
import kr.or.ddit.mapper.recruitment.ComExamQuestionsMapper;
import kr.or.ddit.mapper.recruitment.CompanyExamMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentExamMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentExamOptionMapper;
import kr.or.ddit.mapper.recruitment.RecruitmentExamQuestionsMapper;
import kr.or.ddit.vo.recruitment.ComExamOptionVO;
import kr.or.ddit.vo.recruitment.ComExamQuestionsVO;
import kr.or.ddit.vo.recruitment.CompanyExamVO;
import kr.or.ddit.vo.recruitment.RecruitmentExamOptionVO;
import kr.or.ddit.vo.recruitment.RecruitmentExamQuestionsVO;
import kr.or.ddit.vo.recruitment.RecruitmentExamVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruitExamServiceImpl implements RecruitExamService {
	
	private final CompanyExamMapper comExamMapper;
	private final ComExamQuestionsMapper comExamQuestMapper;
	private final ComExamOptionMapper comExamOptionMapper;
	
	private final RecruitmentExamMapper recruitExamMapper;
	private final RecruitmentExamQuestionsMapper recruitQuestMapper;
	private final RecruitmentExamOptionMapper recruitOptionMapper;

	@Override
	@Transactional
	public void copyCompanyExamToRecruit(String processNo, RecruitmentExamVO exam) {
		
		CompanyExamVO comExam = comExamMapper.selectCompanyExam(exam.getComExamNo());
		exam.setProcessNo(processNo);
		exam.setRecruitExamName(comExam.getComExamName());
		
		String time = exam.getRecruitExamStartDate();
		String formatted = time.replace("T", " ");
		exam.setRecruitExamStartDate(formatted);
		
		recruitExamMapper.insertRecruitExam(exam);
		
		String rercruitExamNo = exam.getRecruitExamNo();
		
		List<ComExamQuestionsVO> questions = comExamQuestMapper.selectByQuestionExamNo(exam.getComExamNo());
		for(ComExamQuestionsVO quest : questions) {
			RecruitmentExamQuestionsVO recruitQuest = new RecruitmentExamQuestionsVO();
			recruitQuest.setRecruitExamNo(rercruitExamNo);
			recruitQuest.setRecruitExamQuestContent(quest.getComExamContents());
			recruitQuestMapper.insertRecrExamQuest(recruitQuest);
			String recruitExamQuestNo = recruitQuest.getRecruitExamQuestNo();
			
			List<ComExamOptionVO> options = comExamOptionMapper.selectByQuestionNo(quest.getComQuestionsNo());
			for(ComExamOptionVO option : options) {
				RecruitmentExamOptionVO recruitOption = new RecruitmentExamOptionVO();
				recruitOption.setRecruitExamQuestNo(recruitExamQuestNo);
				recruitOption.setRecruitExamOptionContent(option.getComOptionContent());
				recruitOption.setRecruitExamOptionCorrectYn(option.getComOptionCorrectYn());
				recruitOptionMapper.insertRecrExamOption(recruitOption);
			}
		}		
	}

}
