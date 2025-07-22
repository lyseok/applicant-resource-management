package kr.or.ddit.rest.project.service;

import java.util.List;

import kr.or.ddit.vo.project.ProjectVO;

public interface ProjectService {
	public List<ProjectVO> readMyProjectList();
	public ProjectVO readProjectDetail(String prjNo);
	public ProjectVO modifyProject(String prjNo, ProjectVO param);
}
