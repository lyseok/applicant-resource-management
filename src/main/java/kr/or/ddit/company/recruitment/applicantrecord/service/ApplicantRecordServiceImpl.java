package kr.or.ddit.company.recruitment.applicantrecord.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.exception.DataUpdateException;
import kr.or.ddit.mapper.recruitment.ApplicantRecordMapper;
import kr.or.ddit.mapper.recruitment.PasserMapper;
import kr.or.ddit.mapper.recruitment.RecruitProcessMapper;
import kr.or.ddit.vo.recruitment.ApplicantRecordVO;
import kr.or.ddit.vo.recruitment.PasserVO;
import kr.or.ddit.vo.recruitment.RecruitProcessVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicantRecordServiceImpl implements ApplicantRecordService {
	
	private final ApplicantRecordMapper applMapper;
	private final RecruitProcessMapper processMapper;
	private final PasserMapper passMapper;

	@Override
	public List<Map<String, Object>> getApplicantsByRecruitment(String recruitmentNo) {
		
		return applMapper.getApplicantByRecruitment(recruitmentNo);
	}

	@Transactional
	@Override
	public void updateNextStep(ApplicantRecordVO vo) {
		int cnt = applMapper.updateApplicantPass(vo);
		
		if(cnt == 0) {
			throw new DataUpdateException("데이터 수정에 실패했습니다");
		}else {
			RecruitProcessVO processVo = new RecruitProcessVO();
			processVo.setRecruitmentNo(vo.getRecruitmentNo());
			processVo.setRecruitProcessStep(vo.getRecruitProcessStep()+1);
			processVo = processMapper.selectNextStep(processVo);
			
			if(vo.getRecruitProcessFinal().equals("N")) {				
				ApplicantRecordVO applVo = new ApplicantRecordVO();
				applVo.setRecruitProcessNo(processVo.getRecruitProcessNo());
				applVo.setApplicantId(vo.getApplicantId());
				applVo.setApplicantName(vo.getApplicantName());
			}else {
				PasserVO pass = new PasserVO();
				pass.setApplicantId(vo.getApplicantId());
				passMapper.insertPasser(pass);
			}
			
		}
	}

}
