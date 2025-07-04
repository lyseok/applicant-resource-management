package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.RecruitmentEducationVO;

@Mapper
public interface RecruitmentEducationMapper {
	public List<RecruitmentEducationVO> selectRecruitmentEduList();
	public RecruitmentEducationVO selectRecruitmentEdu(String RecruitmentEducationCode);
	public int insertRecruitmentEducation(RecruitmentEducationVO vo);
	public int updateRecruitmentEducation(RecruitmentEducationVO vo);
	public int deleteRecruitmentEducation(String RecruitmentEducationCode);
}
