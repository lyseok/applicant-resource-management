package kr.or.ddit.member.project.announcement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board/project")
public class MemberProjectAnnouncementController {
	@GetMapping
	public String projectAnnouncementPage(Model model) {
		return "member/project/announcement/announcementList";
	}
	@GetMapping("/form")
	public String projectAnnouncementFormPage(Model model) {
		return "member/project/announcement/announcementForm";
	}
	@GetMapping("/detail")
	public String projectAnnouncementDetailPage(Model model) {
		return "member/project/announcement/announcementDetail";
	}
}
