package kr.or.ddit.company.codegroup.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.admin.common.codegroup.service.AdminCmnCodeGroupAjaxService;
import kr.or.ddit.vo.common.CmnCodeGroupVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/ajax/company/cmncodegroup")
@RestController
@RequiredArgsConstructor
public class CompanyCmnCodeGroupAjaxController {
	private final AdminCmnCodeGroupAjaxService service;
	@GetMapping
	public List<CmnCodeGroupVO> getCmnCodeGroupList() {
		return service.readCmnCodeGroupList();
	}
	
	@GetMapping("/{no}")
	public CmnCodeGroupVO getCmnCodeGroup(@PathVariable String no) {
		return service.readCmnCodeGroupByPk(no);
	}
}
