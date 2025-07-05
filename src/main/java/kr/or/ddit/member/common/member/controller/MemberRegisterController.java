package kr.or.ddit.member.common.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.member.common.exception.PKDuplicatedException;
import kr.or.ddit.member.common.member.service.MemberService;
import kr.or.ddit.security.oauth2.UserNotRegisteredException;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.common.MemberVO;
import kr.or.ddit.vo.common.UsersVO;

@Controller
@RequestMapping("/signup")
public class MemberRegisterController {
	
	@Autowired
	private MemberService service;
	private static final String MODELNAME = "member";
	
	@ModelAttribute(MODELNAME)
	public UsersVO user(
		@SessionAttribute(name=WebAttributes.AUTHENTICATION_EXCEPTION, required = false) UserNotRegisteredException lastException
	) {
		MemberVO member = new MemberVO();
		if(lastException != null) {			
			OidcUser unRegisteredUser = lastException.getUnRegisteredUser();
			String oidcName = unRegisteredUser.getName();
			member.setUserId(oidcName);
		}else {
			
		}
		return member;
	}
	
	@GetMapping
	public String formUI() {
		return "/member/common/memberForm";
	}
	
	
	@PostMapping
	public String formProcess(
		@SessionAttribute(name=WebAttributes.AUTHENTICATION_EXCEPTION, required = false) UserNotRegisteredException lastException
		,@Validated @ModelAttribute(MODELNAME) MemberVO member
		, BindingResult errors
		, RedirectAttributes redirectAttributes
	) {
		String lvn;
		if(!errors.hasErrors()) {
			try {
				service.registerMember(member, member);
				lvn = "redirect:/";
			} catch (PKDuplicatedException e) {
				redirectAttributes.addFlashAttribute("member", member);
				redirectAttributes.addFlashAttribute("message", "아이디 중복");
				// 아이디 중복
				lvn = "redirect:/member/common/memberForm";
			}	
		}else {
			redirectAttributes.addFlashAttribute("member", member);
			String errorName = BindingResult.MODEL_KEY_PREFIX+MODELNAME;
			redirectAttributes.addFlashAttribute(errorName, errors);
			lvn = "redirect:/member/common/memberForm";
		}
		return lvn;
	}
}
