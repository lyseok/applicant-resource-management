package kr.or.ddit.company.recruitment.skill.service;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.recruitment.RecruitmentSkillmapper;
import kr.or.ddit.vo.recruitment.RecruitmentSkillVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruitSkillServiceImpl implements RecruitSkillService {

	private final RecruitmentSkillmapper mapper;

	@Override
	public void createRecruitSkill(RecruitmentSkillVO recruitSkill) {
		mapper.insertRecruitmetnSkill(recruitSkill);
	}

	@Override
	public void modifyRecruitSkill(RecruitmentSkillVO recruitSkill) {
		mapper.updateRecruitmetnSkill(recruitSkill);
	}

	@Override
	public void deleteRecruitSkill(String recruitSkillNo) {
		mapper.deleteRecruitmetnSkill(recruitSkillNo);
	}

}
