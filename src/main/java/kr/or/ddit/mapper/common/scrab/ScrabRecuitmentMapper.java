package kr.or.ddit.mapper.common.scrab;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.ScrabRecuitmentVO;

@Mapper
public interface ScrabRecuitmentMapper {

	public List<ScrabRecuitmentVO> ScrabRecuitmentList(String userId);
	
//	public ScrabRecuitmentVO selectScrabRecuitment(String userId);
	
	public int insertScrabRecuitment(ScrabRecuitmentVO ScrabRecuitment);
}
