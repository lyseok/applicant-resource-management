package kr.or.ddit.ats.test.testService;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.EmploymentTestMapper;
import kr.or.ddit.vo.EmploymentTestVO;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class EmploymentTestServiceImpl implements EmploymentTestService {
	private final EmploymentTestMapper mapper;
	
	@Override
	public List<EmploymentTestVO> readEmploymentList() {
		return mapper.selectListEmploymentTest();
	}

}
