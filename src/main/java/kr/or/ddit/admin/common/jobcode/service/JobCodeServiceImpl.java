package kr.or.ddit.admin.common.jobcode.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.JobMapper;
import kr.or.ddit.mapper.common.TopJobMapper;
import kr.or.ddit.vo.common.JobVO;
import kr.or.ddit.vo.common.TopJobVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobCodeServiceImpl implements JobCodeService {
	
	private final TopJobMapper topMapper;
	private final JobMapper jobMapper;

	@Override
	public List<TopJobVO> readTopJobList() {
		return topMapper.selectRealTopJobList();
	}

	@Override
	public List<JobVO> readJobListByTobJob(String topJobCode) {
		return jobMapper.selectJobByTopJob(topJobCode);
	}

}
