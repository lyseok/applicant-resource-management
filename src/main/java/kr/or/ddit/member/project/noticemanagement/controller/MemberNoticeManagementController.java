package kr.or.ddit.member.project.noticemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/notice_management")
public class MemberNoticeManagementController {
	@GetMapping
	public String noticeManagementPage() {
		return "member/project/noticeManagement/noticeList";
	}
	@GetMapping("/detail")
	public String noticeDetailPage() {
		return "member/project/noticeManagement/noticeDetail"; 
	}
	@GetMapping("/form")
	public String noticeFormPage() {
		return "member/project/announcement/announcementForm";
	}
}
