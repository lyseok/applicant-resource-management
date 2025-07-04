package kr.or.ddit.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.project.PrjBbsCommentVO;

@Mapper
public interface PrjBbsCommentMapper {
	public List<PrjBbsCommentVO> selectPrjRcrtPsncntList();
	public PrjBbsCommentVO selectPrjRcrtPsncntByPk(String commentNo);
	public int insertPrjRcrtPsncnt(PrjBbsCommentVO prjBbsComment);
	public int updatePrjRcrtPsncnt(PrjBbsCommentVO prjBbsComment);
	public int deletePrjRcrtPsncnt(String commentNo);
}
