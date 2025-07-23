package kr.or.ddit.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.project.ProjectVO;

@Mapper
public interface ProjectMapper {
	public List<ProjectVO> selectProjectList();
	
	public List<ProjectVO> selectMyProjectList(String userId);
	
	public ProjectVO selectProjectDetail(String prjNo);
	
	public int updateProject(ProjectVO project);
	public int createProject(ProjectVO project);
}
