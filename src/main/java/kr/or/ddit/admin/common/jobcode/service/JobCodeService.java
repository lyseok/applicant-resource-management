package kr.or.ddit.admin.common.jobcode.service;

import java.util.List;

import kr.or.ddit.vo.common.JobVO;
import kr.or.ddit.vo.common.TopJobVO;

public interface JobCodeService {

	public List<TopJobVO> readTopJobList();
	
	public List<JobVO> readJobListByTobJob(String topJobCode);
}
