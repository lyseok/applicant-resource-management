package kr.or.ddit.company.recruitment.interview.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.or.ddit.company.recruitment.interview.dto.VideoInterviewSaveDTO;
import kr.or.ddit.conf.CodeMapProvider;
import kr.or.ddit.mapper.recruitment.InterviewMapper;
import kr.or.ddit.vo.recruitment.InterviewVO;
import kr.or.ddit.vo.recruitment.RecruitProcessVO;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import kr.or.ddit.vo.recruitment.VideoInterviewVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyInterviewServiceImpl implements CompanyInterviewService{
	private final InterviewMapper mapper;
	private final CodeMapProvider codeMapProvider;
	
	@Override
	public List<InterviewVO> readInterviewList() {
		List<InterviewVO> interviewList = mapper.selectInterviewList();
		for(InterviewVO inteVo : interviewList) {
			setCodeName(inteVo);
		}
		return interviewList;
	}

	@Override
	public InterviewVO readInterview(String interviewNo) {
		InterviewVO inteVo = mapper.selectInterview(interviewNo);
		setCodeName(inteVo);
		
		return inteVo;
	}

	@Override
	public int createInterview(VideoInterviewSaveDTO dto) {
		VideoInterviewVO videoInterviewVO = new VideoInterviewVO();
		videoInterviewVO.setInterviewNo(dto.getInterviewNo());
		videoInterviewVO.setRoomTitle(dto.getRoomTitle());
		videoInterviewVO.setMaxJoinCount(dto.getMaxJoinCount());
		videoInterviewVO.setStartDate(dto.getStartDate());
		videoInterviewVO.setEndDate(dto.getEndDate());
		
		
		
		//		return mapper.insertInterview(vo);
		return 0;
	}

	@Override
	public int modifyInterview(VideoInterviewSaveDTO dto) {
//		return mapper.updateInterview(vo);
		return 0;
	}

	@Override
	public int removeInterview(String inteviewNo) {
		return mapper.deleteInterview(inteviewNo);
	}
	
	// 채팅방 생성
	@SuppressWarnings("deprecation")
	public void createRoom(VideoInterviewVO videoInterview) throws Exception {
	    String roomTitle = videoInterview.getRoomTitle();
	    int maxJoinCount = videoInterview.getMaxJoinCount();
	    String startDate = videoInterview.getStartDate();
	    String endDate = videoInterview.getEndDate();
	    
	    // 방 생성 요청
	    OkHttpClient client = new OkHttpClient();
	    MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
	    
	    okhttp3.RequestBody body = okhttp3.RequestBody.create(
	        mediaType,
	        "callType=P2P&liveMode=false"
	        + "&maxJoinCount=" + maxJoinCount 
	        + "&liveMaxJoinCount=100&layoutType=4&"
	        + "roomTitle=" + roomTitle 
	        + "startDate" + startDate
	        + "endDate" + endDate
	        + "&durationMinutes=3000"
	    );

	    Request request = new Request.Builder()
	        .url("https://openapi.gooroomee.com/api/v1/room")
	        .post(body)
	        .addHeader("accept", "application/json")
	        .addHeader("content-type", "application/x-www-form-urlencoded")
	        .addHeader("X-GRM-AuthToken", "12056163501988613cf51b7b51cdd8140bb172761d02211a8b")
	        .build();

	    Response response = client.newCall(request).execute();
	    String resp = response.body().string();
	    Gson gson = new Gson();
	    
	    Map<String, Object> map = gson.fromJson(resp, new TypeToken<Map<String, Object>>(){}.getType());
	    
	    log.info("resp : {}", map);
	    if("GRM_200".equals(map.get("resultCode"))) {
	    	videoInterview.setRoomId(String.valueOf(map.get("roomId")));
	    } else {
	    	
	    }
	    		
	    
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
