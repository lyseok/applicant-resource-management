package kr.or.ddit.admin.project.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.project.ProjectMapper;
import kr.or.ddit.vo.project.ProjectVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminProjectAjaxServiceImpl implements AdminProjectAjaxService{
	private final ProjectMapper mapper;
	
	@Override
	public List<ProjectVO> readProjectList() {
		return mapper.selectProjectList();
	}

	@Override
	public List<ProjectVO> readProjectUserIdList(String userId) {
//		return mapper.selectProjectUserIdList(userId);
		return null;
	}

	@Override
	public ProjectVO readProjectByPk(String prjNo) {
//		return mapper.selectProjectByPk(prjNo);
		return null;
	}

	@Override
	public int createProject(ProjectVO project) {
//		return mapper.insertProject(project);
		return 0;
	}

	@Override
	public int modifyProject(ProjectVO project) {
		return mapper.updateProject(project);
	}

	@Override
	public int removeProject(String prjNo) {
//		return mapper.deleteProject(prjNo);
		return 0;
	}

}
