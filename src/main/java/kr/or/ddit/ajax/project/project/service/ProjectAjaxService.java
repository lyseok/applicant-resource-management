package kr.or.ddit.ajax.project.project.service;

import java.util.List;

import kr.or.ddit.vo.project.ProjectVO;

public interface ProjectAjaxService {
	public List<ProjectVO> readProjectList();
	public List<ProjectVO> readProjectUserIdList(String userId);	
	public ProjectVO readProjectByPk(String prjNo);
	public int createProject(ProjectVO project);
	public int modifyProject(ProjectVO project);
	public int removeProject(String prjNo);
}
