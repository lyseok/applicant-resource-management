package kr.or.ddit.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.project.PrjAplcntVO;

@Mapper
public interface PrjAplcntMapper {
	public List<PrjAplcntVO> selectPrjRcrtPsncntList();
	public PrjAplcntVO selectPrjRcrtPsncntByPk(String prjAplcntNo);
	public String duplicationPrjRcrtPsncnt(PrjAplcntVO vo);
	public int insertPrjRcrtPsncnt(PrjAplcntVO prjAplcnt);
	public int updateStatusCode(PrjAplcntVO prjAplcnt);
}
