package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.RecruitmentPositionVO;

@Mapper
public interface RecruitmentPositionMapper {
	public List<RecruitmentPositionVO> selectRecruitmentPositonList();
	public RecruitmentPositionVO selectRecruitmentPosition(String RecruitmentPositionNo);
	public int insertRecruitmentPositon(RecruitmentPositionVO vo);
	public int updateRecruitmentPositon(RecruitmentPositionVO vo);
	public int deleteRecruitmentPositon(String RecruitmentPositionNo);
}
