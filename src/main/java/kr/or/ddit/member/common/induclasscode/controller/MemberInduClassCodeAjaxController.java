package kr.or.ddit.member.common.induclasscode.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.common.induclasscode.service.MemberInduClassCodeAjaxService;
import kr.or.ddit.vo.common.InduClassCodeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/ajax/member/induclasscode")
@RestController
@RequiredArgsConstructor
public class MemberInduClassCodeAjaxController {

	private final MemberInduClassCodeAjaxService service;

	@GetMapping
	public List<InduClassCodeVO> getInduClassCodeList() {
		return service.readInduClassCodeList();
	}
	
	@GetMapping("/{no}")
	public InduClassCodeVO getInduClassCode(@PathVariable String no) {
		return service.readInduClassCodeBuPk(no);
	}
}
