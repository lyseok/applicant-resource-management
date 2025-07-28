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
	public String prjNoticeListPage(Model model) {
		model.addAttribute("boardCss", true);
		return "member/project/announcement/prjNoticeList";
	}
	@GetMapping("/before")
	public String projectAnnouncementPage() {
		return "member/project/announcement/announcementList";
	}
	@GetMapping("/form")
	public String projectAnnouncementFormPage() {
		return "member/project/announcement/announcementForm";
	}
	@GetMapping("/detail")
	public String projectAnnouncementDetailPage() {
		return "member/project/announcement/announcementDetail";
	}
}
