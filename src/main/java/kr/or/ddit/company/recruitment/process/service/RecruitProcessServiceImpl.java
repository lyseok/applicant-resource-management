package kr.or.ddit.company.recruitment.process.service;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.recruitment.RecruitProcessMapper;
import kr.or.ddit.vo.recruitment.RecruitProcessVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruitProcessServiceImpl implements RecruitProcessService {
		
	private RecruitProcessMapper mapper;

	@Override
	public void createRecruitProcess(RecruitProcessVO recruitProcess) {
		mapper.insertRecruitProcess(recruitProcess);

	}

	@Override
	public void modifyRecruitProcess(RecruitProcessVO recruitProcess) {
		mapper.updateRecruitProcess(recruitProcess);

	}

	@Override
	public void removeRecruitProcess(String recruitProcessNo) {
		mapper.deleteRecruitProcess(recruitProcessNo);

	}

}
