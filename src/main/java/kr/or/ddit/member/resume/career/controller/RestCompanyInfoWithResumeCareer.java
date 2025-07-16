package kr.or.ddit.member.resume.career.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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
		return service.readCompanyInfoWithCareer();
	}
}
