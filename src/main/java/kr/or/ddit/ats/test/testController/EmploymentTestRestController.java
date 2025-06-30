package kr.or.ddit.ats.test.testController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.ats.test.testService.EmploymentTestService;
import kr.or.ddit.vo.EmploymentTestVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/rest/employment")
@RequiredArgsConstructor
public class EmploymentTestRestController {
	
	private final EmploymentTestService service;
	
	@GetMapping()
	public List<EmploymentTestVO> list(){
		log.info(" service.readEmploymentList() : {}",  service.readEmploymentList());
		return service.readEmploymentList();
	}
}
