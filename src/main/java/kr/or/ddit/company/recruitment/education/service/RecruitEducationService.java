package kr.or.ddit.company.recruitment.education.service;

import kr.or.ddit.vo.recruitment.RecruitmentEducationVO;

public interface RecruitEducationService {
	public void createRecruitEducation(RecruitmentEducationVO recruitEdu);
	public void modifyRecruitEducation(RecruitmentEducationVO recruitEdu);
	public void deleteRecruitEducation(String recruitEduNo);
}
