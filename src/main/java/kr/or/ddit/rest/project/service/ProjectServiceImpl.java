package kr.or.ddit.rest.project.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.project.ProjectMapper;
import kr.or.ddit.vo.project.ProjectVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
	private final ProjectMapper projectMapper;
	
	@Override
	public List<ProjectVO> readMyProjectList() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		return projectMapper.selectMyProjectList(username);
	}

	@Override
	public ProjectVO readProjectDetail(String prjNo) {
		return projectMapper.selectProjectDetail(prjNo);
	}
	
	public ProjectVO modifyProject(String prjNo, ProjectVO param) {
        ProjectVO origin = projectMapper.selectProjectDetail(prjNo);
        if (origin == null) return null;

        origin.setProjectName(param.getProjectName());
        origin.setProjectContents(param.getProjectContents());
        origin.setProjectStatus(param.getProjectStatus());
        origin.setProjectColor(param.getProjectColor());

        projectMapper.updateProject(origin);

        return projectMapper.selectProjectDetail(prjNo);
    }
	

}
