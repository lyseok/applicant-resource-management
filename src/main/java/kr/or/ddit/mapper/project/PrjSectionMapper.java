package kr.or.ddit.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.project.PrjSectionVO;

@Mapper
public interface PrjSectionMapper {
	public List<PrjSectionVO> selectPrjSectionList();
	public PrjSectionVO selectPrjSectionByPk(String SectNo);
	public int insertPrjSection(PrjSectionVO prjSection);
	public int updatePrjSection(PrjSectionVO prjSection);
	public int deletePrjSection(String SectNo);
}
