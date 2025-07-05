package kr.or.ddit.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.project.ProjectVO;

@Mapper
public interface ProjectMapper {
	public List<ProjectVO> selectPrjRcrtPsncntList();
	public ProjectVO selectPrjRcrtPsncntByPk(String prjNo);
	public int insertPrjRcrtPsncnt(ProjectVO prjRcrtPsncnt);
	public int updatePrjRcrtPsncnt(ProjectVO prjRcrtPsncnt);
	public int deletePrjRcrtPsncnt(String prjNo);
}
