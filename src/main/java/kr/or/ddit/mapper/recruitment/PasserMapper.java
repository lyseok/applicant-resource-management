package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.PasserVO;

@Mapper
public interface PasserMapper {
	public List<PasserVO> selectPasserList();
	public PasserVO selectPasser(String passerNo);
	public int insertPasser(PasserVO vo);
	public int updatePasser(PasserVO vo);
	public int updateAlarm(String passerNo);
	public int deletePasser(String passerNo);
	public PasserVO selectDuplicatePasser(PasserVO vo);
	public List<PasserVO> selectpasserByRecruit(String recruitmentNo);
}
