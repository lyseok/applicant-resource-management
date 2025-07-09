package kr.or.ddit.company.recruitment.position.service;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.recruitment.RecruitmentPositionMapper;
import kr.or.ddit.vo.recruitment.RecruitmentPositionVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruitPositionServiceImpl implements RecruitPositionService {

	private final RecruitmentPositionMapper mapper;

	@Override
	public void createRecruitPosition(RecruitmentPositionVO recruitPosition) {
		mapper.insertRecruitmentPositon(recruitPosition);

	}

	@Override
	public void modifyRecruitPosition(RecruitmentPositionVO recruitPosition) {
		mapper.updateRecruitmentPositon(recruitPosition);
	}

	@Override
	public void deleteRecruitPosition(String RecruitPositionNo) {
		mapper.deleteRecruitmentPositon(RecruitPositionNo);
	}

}
