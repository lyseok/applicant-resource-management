package kr.or.ddit.company.common.companyManagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.or.ddit.company.common.companyManagement.service.CompanyManagementService;
import kr.or.ddit.dto.CompanyInfoDTO;
import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.common.CompanyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ajax/company/company_management")
public class CompanyManagementAjaxController {
	private final CompanyManagementService companyManagementService;
	private final ErrorsUtils errorsUtils; 
	
	@GetMapping
	public ResponseEntity<CompanyVO> companyDetail() {
		CompanyVO company = companyManagementService.readCompanyManagementById(getUserId());
		log.info("com : {}======", company);
		return ResponseEntity.ok(company);
		
	}
	
	@PutMapping("/edit")
	public ResponseEntity<?> editCompany(
		@Valid @RequestBody CompanyInfoDTO company
		,BindingResult bindingResult
	){
		log.info("{}---------", company);
		
		if (bindingResult.hasErrors()) {
			MultiValueMap<String, String> errors = errorsUtils.errorsToMap(bindingResult);
			return ResponseEntity.badRequest().body(errors);
		}
		
		int updateCount = companyManagementService.editCompanyInfo(company);
		if(updateCount > 0) {
			return ResponseEntity.ok("ok");
		}else {
			return ResponseEntity.status(400).body("업데이트 실패");
		}
		
		
	}
	
	
	

	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	return authentication.getName();
	}
	
}
