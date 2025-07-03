package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.ScrabProjectVO;
@Mapper
public interface ScrabProjectMapper {
	
	public List<ScrabProjectVO> selectScrabProjectList();

	public ScrabProjectVO selectScrabProjectByPk(ScrabProjectVO vo);

	public int insertScrabProject(ScrabProjectVO vo);

	public int updateScrabProject(ScrabProjectVO vo);

	public int deleteScrabProject(ScrabProjectVO vo);
}
