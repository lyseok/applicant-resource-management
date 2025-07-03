package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.ScrabUserVO;

@Mapper
public interface ScrabUserMapper {
	
	public List<ScrabUserVO> selectScrabUserList();

	public ScrabUserVO selectScrabUserByPk(ScrabUserVO vo);
	
	public int insertScrabUser(ScrabUserVO vo);
	
	public int updateScrabUser(ScrabUserVO vo);
	
	public int deleteScrabUser(ScrabUserVO vo);
}
