package kr.or.ddit.company.common.company.controller;

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

import kr.or.ddit.company.common.company.service.CompanyService;
import kr.or.ddit.security.oauth2.UserNotRegisteredException;
import kr.or.ddit.vo.common.CompanyVO;

@Controller
@RequestMapping("/companysignup")
public class CompanyRegisterController {

	@Autowired
	private CompanyService service;
	private static final String MODELNAME = "company";
	
	@ModelAttribute(MODELNAME)
	public CompanyVO company(
		@SessionAttribute(name=WebAttributes.AUTHENTICATION_EXCEPTION, required = false) UserNotRegisteredException lastException
	) {
		CompanyVO company = new CompanyVO();
		if(lastException != null) {			
			OidcUser unRegisteredUser = lastException.getUnRegisteredUser();
			String oidcName = unRegisteredUser.getName();
			company.setUserId(oidcName);
		}else {
			
		}
		return company;
	}
	
	@GetMapping
	public String formUI() {
		return "join/joinFormCorp";
	}
	
	
	@PostMapping
	public String formProcess(
		@SessionAttribute(name=WebAttributes.AUTHENTICATION_EXCEPTION, required = false) UserNotRegisteredException lastException
		,@Validated @ModelAttribute(MODELNAME) CompanyVO company
		, BindingResult errors
		, RedirectAttributes redirectAttributes
	) {
		String lvn;
		if(!errors.hasErrors()) {
			
				service.registerCompany(company);
				lvn = "redirect:/login";
		}else {
			redirectAttributes.addFlashAttribute("company", company);
			String errorName = BindingResult.MODEL_KEY_PREFIX+MODELNAME;
			redirectAttributes.addFlashAttribute(errorName, errors);
			lvn = "redirect:/companysignup";
		}
		return lvn;
	}

}
