package kr.or.ddit.company.project.project.controller;

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
@RequestMapping("/ajax/company/project")
@RequiredArgsConstructor
public class CompanyProjectAjaxController {
	private final AdminProjectAjaxService service;

	@GetMapping
	public List<ProjectVO> getProjectList(){
		return service.readProjectList();
	}
	
	@GetMapping("/{userId}")
	public List<ProjectVO> getProjectListByUserId(@PathVariable String userId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	    String role = "";
	    for (GrantedAuthority authority : authorities) {
	        role = authority.getAuthority(); // ex: "ROLE_ADMIN", "ROLE_USER"
	    }
	    
	    if(role.contains("ROLE_USER")) {
	    	String username = authentication.getName();
	    	return service.readProjectUserIdList(username);	    	
	    }
	    
		return service.readProjectUserIdList(userId);
	}
	
	@GetMapping("/detail/{prjNo}")
	public ProjectVO getProjectByPk(@PathVariable String prjNo) {
		return service.readProjectByPk(prjNo);
	}
	
}
