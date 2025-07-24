package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.ScrabRecruitmentVO;

@Mapper
public interface ScrabRecruitmentMapper {

	public List<ScrabRecruitmentVO> selectScrabRecruitmentList();

	public List<ScrabRecruitmentVO> selectMyScrabRecruitmentList(String userId);

	public int insertScrabRecruitment(ScrabRecruitmentVO vo);

	public int deleteScrabRecruitment(ScrabRecruitmentVO vo);
}
