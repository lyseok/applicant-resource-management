package kr.or.ddit.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.project.PrjRcrtPsncntVO;

@Mapper
public interface PrjRcrtPsncntMapper {
	public List<PrjRcrtPsncntVO> selectPrjRcrtPsncntList();
	public PrjRcrtPsncntVO selectPrjRcrtPsncntByPk(PrjRcrtPsncntVO prjRcrtPsncnt);
	public int insertPrjRcrtPsncnt(PrjRcrtPsncntVO prjRcrtPsncnt);
	public int updatePrjRcrtPsncnt(PrjRcrtPsncntVO prjRcrtPsncnt);
	public int deletePrjRcrtPsncnt(PrjRcrtPsncntVO prjRcrtPsncnt);
}
