package kr.or.ddit.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.project.PrjMemVO;

@Mapper
public interface PrjMemMapper {
	public List<PrjMemVO> selectPrjRcrtPsncntList();
	public PrjMemVO selectPrjRcrtPsncntByPk(PrjMemVO prjMem);
	public int insertPrjRcrtPsncnt(PrjMemVO prjMem);
	public int updatePrjRcrtPsncnt(PrjMemVO prjMem);
	public int deletePrjRcrtPsncnt(PrjMemVO prjMem);
}
