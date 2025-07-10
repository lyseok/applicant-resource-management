package kr.or.ddit.company.recruitment.interview.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.or.ddit.common.exception.VideoInterviewCreateException;
import kr.or.ddit.vo.recruitment.ApplicantRecordVO;
import kr.or.ddit.vo.recruitment.VideoInterviewVO;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


@Slf4j
@Component
public class GooroomeeLogic {
	private static Gson gson = new Gson();
	
	/**
	 * 채팅방 생성하는 메서드
	 * @param videoInterview
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static void createGooroomeeChatRoom(VideoInterviewVO videoInterview) throws Exception {
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
	    
	    
	    Map<String, Object> map = gson.fromJson(resp, new TypeToken<Map<String, Object>>(){}.getType());
	    
	    log.info("resultCode : {}", map);
	    
	    if("GRM_200".equals(map.get("resultCode"))) {
	    	Map<String, Object> data = (Map<String, Object>) map.get("data");
	    	Map<String, Object> room = (Map<String, Object>) data.get("room");
	    	String roomId = String.valueOf(room.get("roomId"));
	    	videoInterview.setRoomId(roomId);
	    } else {
	    	throw new VideoInterviewCreateException(roomTitle + " 화상면접 등록 실패");
	    }
	}
	
	/**
	 * 지원자 접속 URL 생성 메서드
	 * @param applicantRecordVO
	 * @param roomId
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static void getApplicantUrl(ApplicantRecordVO applicantRecordVO, String roomId) throws Exception  {
		Map<String, String> inputData = new HashMap<String, String>();
		inputData.put("roleId", "participant");
		inputData.put("apiUserId", applicantRecordVO.getApplicantId());
		inputData.put("roomId", roomId );
		inputData.put("username", applicantRecordVO.getApplicantName());
		
	    Map<String, Object> res = getVideoInterviewURL(inputData);
	    
	    if("GRM_200".equals(res.get("resultCode"))) {
	    	Map<String, Object> data = (Map<String, Object>) res.get("data");
	    	String url = String.valueOf(data.get("url"));
	    	applicantRecordVO.setInterviewUrl(url);
	    	log.info("지원자 접속 URL 생성 {}",applicantRecordVO);
	    } else {
	    	throw new VideoInterviewCreateException(applicantRecordVO.getApplicantName() + " 화상면접자 등록 실패");
	    }
	}
	
	/**
	 * 면접관 접속 URL생성 메서드
	 * @param userId
	 * @param roomId
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static void getInterviewerUrl(VideoInterviewVO videoInterviewVO) throws Exception  {
		Map<String, String> inputData = new HashMap<String, String>();
		inputData.put("roleId", "emcee");
		inputData.put("apiUserId", videoInterviewVO.getInterviewNo());
		inputData.put("roomId", videoInterviewVO.getRoomId());
		inputData.put("username", "면접관");
		
		Map<String, Object> res = getVideoInterviewURL(inputData);
		
		if("GRM_200".equals(res.get("resultCode"))) {
			Map<String, Object> data = (Map<String, Object>) res.get("data");
			String url = String.valueOf(data.get("url"));
			videoInterviewVO.setCompanyInterviewUrl(url);
			log.info("면접관 접속 URL 생성 {}", videoInterviewVO);
		} else {
			throw new VideoInterviewCreateException(" 화상면접자 등록 실패");
		}
	}
	
	/**
	 * 화상채팅방 접속 URL생성 요청 메서드
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")	
	public static Map<String, Object> getVideoInterviewURL(Map<String, String> data) throws Exception {
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		okhttp3.RequestBody body = okhttp3.RequestBody.create(
				mediaType, "roleId=" + data.get("roleId") 
							+ "&apiUserId=" + data.get("apiUserId")
							+ "&ignorePasswd=false" 
							+ "&roomId=" + data.get("roomId") 
							+ "&username=" + data.get("username")
			);
		Request request = new Request.Builder()
		  .url("https://openapi.gooroomee.com/api/v1/room/user/otp/url")
		  .post(body)
		  .addHeader("accept", "application/json")
		  .addHeader("content-type", "application/x-www-form-urlencoded")
		  .addHeader("X-GRM-AuthToken", "12056163501988613cf51b7b51cdd8140bb172761d02211a8b")
		  .build();

		Response response = client.newCall(request).execute();
		String resp = response.body().string();
		return gson.fromJson(resp, new TypeToken<Map<String, Object>>(){}.getType());
	}
	
	/**
	 * 화상채팅방 삭제 요청 메서
	 * @param room
	 * @return
	 * @throws IOException
	 */
	public static Map<String, Object> deleteRoom(String room) throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("https://openapi.gooroomee.com/api/v1/room/" + room)
		  .delete(null)
		  .addHeader("accept", "application/json")
		  .addHeader("X-GRM-AuthToken", "12056163501988613cf51b7b51cdd8140bb172761d02211a8b")
		  .build();

	    Response response = client.newCall(request).execute();
		String resp = response.body().string();
		return gson.fromJson(resp, new TypeToken<Map<String, Object>>(){}.getType());
	}
}
