package kr.or.ddit.admin.project.project.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.admin.project.project.service.AdminProjectAjaxService;
import kr.or.ddit.vo.project.ProjectVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/admin/project")
@RequiredArgsConstructor
public class AdminProjectController {
	private final AdminProjectAjaxService service;

	@GetMapping("/list")
	public String getProjectList() {
		return "/admin/project/projectList";
	}

	@GetMapping("/{userId}")
	public List<ProjectVO> getProjectListByUserId(@PathVariable String userId) {
		return service.readProjectUserIdList(userId);
	}

	@GetMapping("/detail/{prjNo}")
	public ProjectVO getProjectByPk(@PathVariable String prjNo) {
		return service.readProjectByPk(prjNo);
	}

}
