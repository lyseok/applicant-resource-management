//package kr.or.ddit.member.common.tool.spelling.controller;
//
//import java.util.Map;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import kr.or.ddit.member.common.tool.spelling.service.SpellingCheckService;
//import lombok.RequiredArgsConstructor;
//
//@Controller
//@RequiredArgsConstructor
//public class SpellingCheckController {
//	
//	private final SpellingCheckService service;
//	
//	@GetMapping("/Spelling")
//	public String inputForm() {
//		
//		return "member/tool/spelling/SpellingCheck";
//
//	}
//	
//	@ResponseBody
//	@PostMapping("/spell/check")
//	public Map<String, Boolean> ajaxCheck(@RequestParam("text") String text) {
//	    return service.checkSentence(text);
//	}
//	
//	
//	
//}
