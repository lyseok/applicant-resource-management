package kr.or.ddit.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.project.ProjectVO;

@Mapper
public interface ProjectMapper {
	public List<ProjectVO> selectProjectList();
	public List<ProjectVO> selectProjectUserIdList(String userId);	
	public ProjectVO selectProjectByPk(String prjNo);
	public int insertProject(ProjectVO project);
	public int updateProject(ProjectVO project);
	public int deleteProject(String prjNo);
}
