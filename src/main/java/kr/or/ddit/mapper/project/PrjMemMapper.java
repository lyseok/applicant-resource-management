package kr.or.ddit.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.project.PrjMemVO;

@Mapper
public interface PrjMemMapper {
	public List<PrjMemVO> selectPrjRcrtPsncntList();
	public PrjMemVO selectPrjRcrtPsncntByPk(PrjMemVO prjMem);
	public int insertPrjRcrtPsncnt(PrjMemVO prjMem);
	public int updatePrjRcrtPsncnt(PrjMemVO prjMem);
	public int deletePrjRcrtPsncnt(PrjMemVO prjMem);
	
	public List<PrjMemVO> selectProjectMembers(@Param("prjNo") String prjNo);
	public int insertProjectMember(PrjMemVO prjMemVO);
    public PrjMemVO selectProjectMember(PrjMemVO param);
    public int updateProjectMemberAuthority(PrjMemVO param);
    public int deleteProjectMember(PrjMemVO vo);
}
