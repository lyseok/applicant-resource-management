package kr.or.ddit.company.recruitment.skill.service;

import kr.or.ddit.vo.recruitment.RecruitmentSkillVO;

public interface RecruitSkillService {

	public void createRecruitSkill(RecruitmentSkillVO recruitSkill);
	public void modifyRecruitSkill(RecruitmentSkillVO recruitSkill);
	public void deleteRecruitSkill(String recruitSkillNo);
}
