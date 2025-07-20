package kr.or.ddit.rest.prjmem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.rest.prjmem.service.ProjectMemberService;
import kr.or.ddit.vo.project.PrjMemVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/projects")
@Slf4j
@RequiredArgsConstructor
public class PrjMemRestController {
	private final ProjectMemberService projectMemberService;

    // 프로젝트 멤버 목록 조회
    @GetMapping("/{prjNo}/members")
    public List<PrjMemVO> getProjectMembers(@PathVariable String prjNo) {
        return projectMemberService.getProjectMembers(prjNo);
    }
	@GetMapping("/{prjNo}/members/{userId}")
	public List<PrjMemVO> getPrjMemListApi() {
		return null;
	}
	
	@PostMapping("/{prjNo}/members")
    public ResponseEntity<PrjMemVO> addProjectMember(
            @PathVariable String prjNo,
            @RequestBody PrjMemVO prjMemVO) {
        prjMemVO.setPrjNo(prjNo);
        PrjMemVO result = projectMemberService.addProjectMember(prjMemVO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
	
	@PutMapping("/{prjNo}/members/{userId}")
	public ResponseEntity<PrjMemVO> updateProjectMemberAuthority(
	        @PathVariable String prjNo,
	        @PathVariable String userId,
	        @RequestBody PrjMemVO param) {

	    param.setPrjNo(prjNo);
	    param.setUserId(userId);

	    PrjMemVO updated = projectMemberService.updateProjectMemberAuthority(param);
	    return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping("/{prjNo}/members/{userId}")
	public ResponseEntity<?> deleteProjectMember(@PathVariable String prjNo, @PathVariable String userId) {
	    PrjMemVO prjMemVO = new PrjMemVO();
	    prjMemVO.setPrjNo(prjNo);
	    prjMemVO.setUserId(userId);

	    boolean deleted = projectMemberService.deleteProjectMember(prjMemVO);

	    if (deleted) {
	        return ResponseEntity.ok(Map.of(
	            "message", "멤버가 성공적으로 제거되었습니다.",
	            "userId", userId
	        ));
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Map.of(
	                    "error", "NOT_FOUND",
	                    "message", "해당 멤버가 존재하지 않거나 이미 삭제되었습니다.",
	                    "userId", userId
	                ));
	    }
	}
	
}
