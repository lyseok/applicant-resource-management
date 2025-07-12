package kr.or.ddit.member.recruitment.recruitmentExam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.recruitment.RecruitmentExamMapper;
import kr.or.ddit.vo.recruitment.RecruitmentExamVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruitmentExamServiceImpl implements RecruitmentExamService {
	private final RecruitmentExamMapper recruitmentExamMapper;

	@Override
	public List<RecruitmentExamVO> readMyRecruitExams(String userId) {
		return recruitmentExamMapper.selectMyRecruitExams(userId);
	}

	@Override
	public List<RecruitmentExamVO> readRecruitExamQuestionWithOptionByNo(String recruitExamNo) {
		return recruitmentExamMapper.selectRecruitExamQuestionWithOptionByNo(recruitExamNo);
	}

}
