package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.RecruitmentSkillVO;

@Mapper
public interface RecruitmentSkillmapper {
	public List<RecruitmentSkillVO> selectRecruitmentSkillList();
	public RecruitmentSkillVO selectRecruitmentSkill(String recruitmentSkillCode);
	public int insertRecruitmetnSkill(RecruitmentSkillVO vo);
	public int updateRecruitmetnSkill(RecruitmentSkillVO vo);
	public int deleteRecruitmetnSkill(String recruitmentSkillCode);
}
