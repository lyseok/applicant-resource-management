package kr.or.ddit.company.recruitment.process.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.company.recruitment.exam.service.RecruitExamService;
import kr.or.ddit.company.recruitment.process.dto.ProcessEntry;
import kr.or.ddit.mapper.recruitment.InterviewMapper;
import kr.or.ddit.mapper.recruitment.RecruitProcessMapper;
import kr.or.ddit.vo.recruitment.InterviewVO;
import kr.or.ddit.vo.recruitment.RecruitProcessVO;
import kr.or.ddit.vo.recruitment.RecruitmentExamVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruitProcessServiceImpl implements RecruitProcessService {
		
	private final RecruitProcessMapper mapper;
	private final InterviewMapper interviewMapper;
	private final RecruitExamService service;

	@Override
	@Transactional
	public void createRecruitProcess(String recruitmentNo, List<ProcessEntry> entries) {
		
		for(ProcessEntry entry : entries) {
			RecruitProcessVO process = entry.getProcess();
			process.setRecruitmentNo(recruitmentNo);
			mapper.insertRecruitProcess(process);
			
			String processNo = process.getRecruitProcessNo();
			String type = process.getRecruitProcessType();
			
			switch(type) {
				case "1" ->{
					
				}
				case "2" ->{
					InterviewVO interview = entry.getInterview();
					interview.setProcessNo(processNo);
					interviewMapper.insertInterview(interview);
				}
				case "3" ->{
					String companyExamNo = entry.getCompanyExamNo();
					RecruitmentExamVO exam = entry.getExam();
					service.copyCompanyExamToRecruit(processNo, companyExamNo, exam);
				}
				default -> throw new IllegalArgumentException();
			}
		}
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
