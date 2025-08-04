package kr.or.ddit.member.common.mypage.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.vo.common.MemberVO;

public interface MyPageService {

	public Map<String, Object> readMyPageInfo();
	public Map<String, Object> selectPrjectData();
	public int updateMainResume(String resumeNo);
	public int updateMember(MemberVO vo, MultipartFile memberImage);
}
