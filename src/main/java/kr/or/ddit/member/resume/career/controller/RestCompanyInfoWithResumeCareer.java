package kr.or.ddit.member.resume.career.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.resume.career.service.CareerService;
import kr.or.ddit.vo.common.CompanyVO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ajax/career/company")
@RequiredArgsConstructor
public class RestCompanyInfoWithResumeCareer {
	private final CareerService service; 
	
	@GetMapping("/list")
	public List<CompanyVO> getCompanyInfo() {
		return service.readCompanyInfoWithCareerList();
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<?> getCompanyInfo(
		@PathVariable String id
	) {
		CompanyVO company = service.readCompanyInfoWithCareer(id);
		if (company != null) {
	        return ResponseEntity.ok(Map.of(
	            "success", true,
	            "company", company
	        ));
	    } else {
	        return ResponseEntity.ok(Map.of(
	            "success", false,
	            "message", "회사 정보를 찾을 수 없습니다."
	        ));
	    }

	}
}
