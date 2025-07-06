package kr.or.ddit.ajax.common.codegroup.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.ajax.common.codegroup.service.CmnCodeGroupAjaxService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.common.CmnCodeGroupVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/ajax/cmncodegroup")
@RestController
@RequiredArgsConstructor
public class CmnCodeGroupAjaxController {
	private final CmnCodeGroupAjaxService service;
	
	@Autowired
	private ErrorsUtils errorsUtils;
	
	@GetMapping
	public List<CmnCodeGroupVO> getCmnCodeGroupList() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName(); // 아이디
	    log.info("🔐 요청자: {}", username);
		return service.readCmnCodeGroupList();
	}
	
	@GetMapping("/{no}")
	public CmnCodeGroupVO getCmnCodeGroup(@PathVariable String no) {
		return service.readCmnCodeGroupByPk(no);
	}
	
	@PostMapping
	public Map<String, Object> createCmnCodeGroup(
		@Validated(InsertGroup.class) @RequestBody CmnCodeGroupVO cmnCodeGroupVO, BindingResult errors
	){
		if(service.readCmnCodeGroupByPk(cmnCodeGroupVO.getCodeGroupNo()) != null) {
			return Map.of("errors", Map.of("codeGroupNo", "이미 존재하는 그룹명입니다."));
		}
		log.info("==========> {}", cmnCodeGroupVO);
		Map<String, Object> resp = new HashMap<>();
		if(!errors.hasErrors()) {
			service.createCmnCodeGroup(cmnCodeGroupVO);
			resp.put("ok", true);
		} else {
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			resp.put("errors", customErrors);
		}
		return resp;
	}
	
	@PutMapping("/{no}")
	public Map<String, Object> editCmnCodeGroup(
		@PathVariable String no
		, @Validated(UpdateGroup.class) @RequestBody CmnCodeGroupVO cmnCodeGroupVO, BindingResult errors
	){
		Map<String, Object> resp = new HashMap<>();
		if(!errors.hasErrors()) {
			service.modifyCmnCodeGroup(cmnCodeGroupVO);
			resp.put("ok", true);
		} else {
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			resp.put("errors", customErrors);
		}
		return resp;
	}
	
	@DeleteMapping("/{no}")
	public Map<String, Object> deleteCmnCodeGroup(@PathVariable String no) {
		Map<String, Object> resp = new HashMap<>();
		if(service.readCmnCodeGroupByPk(no) == null) {
			resp.put("errors", Map.of("codeGroupNo",  "존재하지 않는 그룹명입니다."));
			return resp;
		}
		service.removeCmnCodeGroup(no);
		resp.put("ok", true);
		return resp;
	}
}
