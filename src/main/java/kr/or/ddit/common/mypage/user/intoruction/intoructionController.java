package kr.or.ddit.common.mypage.user.intoruction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/intoruction/")
public class intoructionController {
	
	// 리스트 가져오기
	@GetMapping("/list")
	public String getIntoruction() {
		return "common/mypage/user/intoruction/intoructionList";
	}
}
