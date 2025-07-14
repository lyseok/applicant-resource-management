package kr.or.ddit.admin.common.jobcode.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.admin.common.jobcode.service.JobCodeServiceImpl;
import kr.or.ddit.vo.common.JobVO;
import kr.or.ddit.vo.common.TopJobVO;
import lombok.RequiredArgsConstructor;

@RequestMapping("/ajax/admin/jobCode")
@RestController
@RequiredArgsConstructor
public class JobCodeAjaxController {
	private final JobCodeServiceImpl service;

	@GetMapping
	public List<TopJobVO> getTopJobCodeList(){
		return service.readTopJobList();
	}
	
	@GetMapping("/{topJobCode}")
	public List<JobVO> getJobCodeListByTopJob(@PathVariable String topJobCode){
		return service.readJobListByTobJob(topJobCode);
	}
}
