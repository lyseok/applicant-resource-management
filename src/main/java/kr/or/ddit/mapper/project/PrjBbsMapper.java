package kr.or.ddit.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.project.PrjBbsVO;

@Mapper
public interface PrjBbsMapper {
	public List<PrjBbsVO> selectPrjBbsList();
	public PrjBbsVO selectPrjBbsByPk(String PrjPostNo);
	public int insertPrjBbs(PrjBbsVO PrjBbs);
	public int updatePrjBbs(PrjBbsVO PrjBbs);
	public int deletePrjBbs(String PrjPostNo);
	
	public List<PrjBbsVO> selectProjectPosts(@Param("prjNo") String prjNo, @Param("page") int page, @Param("size") int size);
	public PrjBbsVO selectPrjBbsWithComments(String prjPostNo);
}
