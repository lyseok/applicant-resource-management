package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.ForbiddenWordVO;
@Mapper
public interface ForbiddenWordMapper {
	public List<ForbiddenWordVO> selectForbiddenWordList();

	public ForbiddenWordVO selectForbiddenWordByPk(ForbiddenWordVO vo);
	
	public int insertForbiddenWord(ForbiddenWordVO vo);
	
	public int updateForbiddenWord(ForbiddenWordVO vo);
	
	public int deleteForbiddenWord(ForbiddenWordVO vo);
}
