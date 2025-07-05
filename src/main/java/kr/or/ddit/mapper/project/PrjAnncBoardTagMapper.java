package kr.or.ddit.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.project.PrjAnncBoardTagVO;

@Mapper
public interface PrjAnncBoardTagMapper {
	public List<PrjAnncBoardTagVO> selectPrjAnncBoardTagList();
	public PrjAnncBoardTagVO selectprjAnncBoardTagByPk(PrjAnncBoardTagVO prjAnncBoardTag);
	public List<PrjAnncBoardTagVO> searchPrjAnncBoardTagList(String prjAnncNo);
	public int insertPrjAnncBoardTag(PrjAnncBoardTagVO prjAnncBoardTag);
	public int deletePrjAnncBoardTag(PrjAnncBoardTagVO prjAnncBoardTag);
}
