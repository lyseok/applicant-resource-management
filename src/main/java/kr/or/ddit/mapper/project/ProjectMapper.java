package kr.or.ddit.mapper.project;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.project.ProjectVO;

@Mapper
public interface ProjectMapper {
	public List<ProjectVO> selectProjectList();
	
	public List<ProjectVO> selectMyProjectList(String userId);
	
	public ProjectVO selectProjectDetail(String prjNo);
	
	public Map<String, Object> selectPrjectData(String userId);
	
	public int updateProject(ProjectVO project);
	public int createProject(ProjectVO project);
}
