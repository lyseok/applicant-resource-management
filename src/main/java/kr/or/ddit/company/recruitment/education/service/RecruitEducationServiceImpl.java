package kr.or.ddit.company.recruitment.education.service;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.recruitment.RecruitmentEducationMapper;
import kr.or.ddit.vo.recruitment.RecruitmentEducationVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruitEducationServiceImpl implements RecruitEducationService {

	private final RecruitmentEducationMapper mapper;
	
	@Override
	public void createRecruitEducation(RecruitmentEducationVO recruitEdu) {
		mapper.insertRecruitmentEducation(recruitEdu);
	}

	@Override
	public void modifyRecruitEducation(RecruitmentEducationVO recruitEdu) {
		mapper.updateRecruitmentEducation(recruitEdu);

	}

	@Override
	public void deleteRecruitEducation(String recruitEduNo) {
		mapper.deleteRecruitmentEducation(recruitEduNo);

	}

}
