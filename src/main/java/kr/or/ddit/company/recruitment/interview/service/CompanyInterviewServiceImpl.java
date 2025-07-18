package kr.or.ddit.company.recruitment.interview.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.exception.DataInsertException;
import kr.or.ddit.common.exception.DataUpdateException;
import kr.or.ddit.common.exception.VideoInterviewCreateException;
import kr.or.ddit.company.recruitment.interviewquestion.service.InterviewQuestionService;
import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.dto.VideoInterviewSaveDTO;
import kr.or.ddit.mapper.recruitment.ApplicantRecordMapper;
import kr.or.ddit.mapper.recruitment.InterviewMapper;
import kr.or.ddit.mapper.recruitment.VideoInterviewMapper;
import kr.or.ddit.vo.recruitment.ApplicantRecordVO;
import kr.or.ddit.vo.recruitment.InterviewQuestionVO;
import kr.or.ddit.vo.recruitment.InterviewVO;
import kr.or.ddit.vo.recruitment.RecruitProcessVO;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import kr.or.ddit.vo.recruitment.VideoInterviewVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyInterviewServiceImpl implements CompanyInterviewService{
	private final InterviewMapper interviewMapper;
	private final VideoInterviewMapper videoInterviewMapper;
	private final ApplicantRecordMapper applicantRecordMapper;
	
	private final CodeMapProvider codeMapProvider;
	
	private final InterviewQuestionService interviewQuestionService;
	
	@Override
	public List<InterviewVO> readInterviewList() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		List<InterviewVO> interviewList = interviewMapper.selectInterviewListByCompany(username);
		for(InterviewVO inteVo : interviewList) {
			setCodeName(inteVo);
		}
		return interviewList;
	}

	@Override
	public InterviewVO readInterview(String interviewNo) {
		InterviewVO inteVo = interviewMapper.selectInterview(interviewNo);
		setCodeName(inteVo);
		
		return inteVo;
	}

	@Override
	public void createVideoInterviewLogic(VideoInterviewSaveDTO dto) {
		VideoInterviewVO videoInterviewVO = new VideoInterviewVO();
		videoInterviewVO.setInterviewNo(dto.getInterviewNo());
		videoInterviewVO.setRoomTitle(dto.getRoomTitle());
		videoInterviewVO.setMaxJoinCount(dto.getMaxJoinCount());
		videoInterviewVO.setStartDate(dto.getStartDate());
		videoInterviewVO.setEndDate(dto.getEndDate());
		
		try {
			GooroomeeLogic.createGooroomeeChatRoom(videoInterviewVO); // 화상채팅방 생성
			GooroomeeLogic.getInterviewerUrl(videoInterviewVO); // 면접관 URL생성
			
			// 화상면접이 이미 있는지 (insert인지 update인지 결정)
			VideoInterviewVO exist = videoInterviewMapper.selectVideoInterview(videoInterviewVO.getInterviewNo());
			log.info("exist {}", exist);
			int cnt;
		    if(exist == null) {
		    	cnt = videoInterviewMapper.insertVideoInterview(videoInterviewVO);
		    } else {
		    	GooroomeeLogic.deleteRoom(exist.getRoomId());
		    	cnt = videoInterviewMapper.updateVideoInterview(videoInterviewVO);
		    }
		    
			if(1 > cnt) {
				throw new VideoInterviewCreateException(videoInterviewVO.getRoomTitle() + " 화상면접 등록 실패");
			}
		} catch (Exception e) {
			throw new VideoInterviewCreateException("화상면접 등록 실패");
		}
		
		try {
			for(ApplicantRecordVO vo : dto.getApplicantRecordList()) {
				GooroomeeLogic.getApplicantUrl(vo, videoInterviewVO.getRoomId()); // 면접자 URL생성
				if(1 > applicantRecordMapper.updateInterviewURL(vo)) {
					throw new VideoInterviewCreateException("면접자 등록 실패");					
				}
			}
		} catch (Exception e) {
			throw new VideoInterviewCreateException("면접자 등록 실패");
		}
	}
	
	@Override
	public void createInterview(InterviewVO vo) {
	    // 1. PK로 select해서 존재여부 확인
	    InterviewVO dbInterview = interviewMapper.selectInterviewByNo(vo.getInterviewNo());

	    if (dbInterview != null) {
	        // 이미 있으면 update
	        int cnt = interviewMapper.updateInterview(vo);
	        if (cnt == 0) {
	            throw new DataUpdateException("면접 정보 수정에 실패했습니다.");
	        }
	        for(InterviewQuestionVO iQVo : vo.getInterviewQuestionList()) {
	        	log.info("{}", iQVo);
	        	interviewQuestionService.createInterviewQuestion(iQVo);	        	
	        }
	    } else {
	        // 없으면 insert
	        int cnt = interviewMapper.insertInterview(vo);
	        if (cnt == 0) {
	            throw new DataInsertException("면접 정보 등록에 실패했습니다.");
	        }
	    }
	}	

	@Override
	public int modifyInterview(VideoInterviewSaveDTO dto) {
//		return mapper.updateInterview(vo);
		return 0;
	}

	@Override
	public int removeInterview(String inteviewNo) {
		return interviewMapper.deleteInterview(inteviewNo);
	}
	
	/**
	 * code를 codeName에 코드 이름을 set해주는 함수
	 * @param inteVo
	 */
	private void setCodeName(InterviewVO inteVo) {
		RecruitmentNoticeVO notiVo = inteVo.getRecruitProcess().getRecruitmentNotice();
		RecruitProcessVO reprVo = inteVo.getRecruitProcess();
		
		reprVo.setRecruitProcessTypeName(codeMapProvider.getCodeName(reprVo.getRecruitProcessType()));
		
		String district = codeMapProvider.getDistrictName(notiVo.getDistrictCode());
		notiVo.setDistrictCodeName(district);
		
		String job = codeMapProvider.getJobName(notiVo.getJobCode());
		notiVo.setJobCodeName(job);
		
		String city = codeMapProvider.getCityName(notiVo.getCityCode());
		notiVo.setCityCodeName(city);
		
		String year = codeMapProvider.getCodeName(notiVo.getYearCode());
		notiVo.setYearCodeName(year);
	}


	
}
