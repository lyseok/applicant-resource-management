package kr.or.ddit.member.recruitment.searchNotice.cityNotice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.recruitment.searchNotice.cityNotice.service.MemberSearchCityNoticeAjaxService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.common.BusinessTypeCodeVO;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ajax/member/recruit/city")
public class MemberSearchCityNoticeAjaxController {

	private final MemberSearchCityNoticeAjaxService service;
	private final ErrorsUtils errorsUtils;
	
	@GetMapping("/list")
	public List<RecruitmentNoticeVO> getRecruitList() {
		List<RecruitmentNoticeVO> list = service.readRecruitList();
		return list;
	}
	
	@GetMapping("/business/{businessTypeNo}")
	public BusinessTypeCodeVO getBusinessTypeCodeVO(@PathVariable String businessTypeNo) {
		BusinessTypeCodeVO vo = service.readBusinessTypeCode(businessTypeNo);
		return vo;
	}
	
}
