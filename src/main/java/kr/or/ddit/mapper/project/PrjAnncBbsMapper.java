package kr.or.ddit.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.project.PrjAnncBbsVO;

@Mapper
public interface PrjAnncBbsMapper {
	public List<PrjAnncBbsVO> selectPrjAnncBbsList();
	public PrjAnncBbsVO selectPrjAnncBbsByPk(String prjAnncNo);
	public int insertPrjAnncBbs(PrjAnncBbsVO prjAnncBbs);
	public int updatePrjAnncBbs(PrjAnncBbsVO prjAnncBbs);
	public int deletePrjAnncBbs(String prjAnncNo);	
}
