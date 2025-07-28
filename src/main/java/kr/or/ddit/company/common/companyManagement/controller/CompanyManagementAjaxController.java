package kr.or.ddit.company.common.companyManagement.controller;

import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import kr.or.ddit.common.file.S3Uploader;
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
	private final S3Uploader s3Uploader;
	
	@GetMapping
	public ResponseEntity<CompanyVO> companyDetail() {
		CompanyVO company = companyManagementService.readCompanyManagementById(getUserId());
		log.info("com : {}======", company);
		return ResponseEntity.ok(company);
		
	}
	
	@PostMapping(value = "/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> editCompany(
		@RequestPart("company") @Valid CompanyInfoDTO company
		,BindingResult bindingResult
		,@RequestPart(value = "comLogoFile",         required = false) MultipartFile comLogoFile
	    ,@RequestPart(value = "comBackgroundImgFile",required = false) MultipartFile comBackgroundImgFile
	    ,@RequestPart(value = "extraFiles",          required = false) List<MultipartFile> extraFiles
	){
		log.info("{}---------", company);
		
		if (bindingResult.hasErrors()) {
			MultiValueMap<String, String> errors = errorsUtils.errorsToMap(bindingResult);
			return ResponseEntity.badRequest().body(errors);
		}
		
		int updateCount = companyManagementService.editCompanyInfo(company, comLogoFile, comBackgroundImgFile, extraFiles);
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
