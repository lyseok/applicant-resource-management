package kr.or.ddit.admin.common.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.admin.common.users.service.AdminUsersService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.utils.ErrorsUtils;
import kr.or.ddit.vo.common.UsersVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/common/users")
@RequiredArgsConstructor
public class AdminUsersController {

	private final AdminUsersService service;
	
	// 등록 폼으로 이동
	@GetMapping("/form")
	public String formUI(Model model) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "admin/common/users/userForm";
	}
	
	// 회원 목록조회
	@GetMapping("/list")
	public String usersList(
		@RequestParam(required = false) String userRole
		, @RequestParam(required = false) String userId
		, Model model
	) {
		List<UsersVO> userList = service.readUsersList(userRole, userId);
		model.addAttribute("userList", userList);
		model.addAttribute("userRole", userRole);
		model.addAttribute("userId", userId);
		
		log.info("🔥 넘어온 userRole: {}", userRole);
		log.info("🔥 넘어온 userId: {}", userId);
		
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);		
		return "admin/common/users/userList";
	}

	/*
	private ErrorsUtils errorsUtils;
	
	//에러 있을 시만 주입
	@Autowired(required = false)  
	public void setErrorsUtils(ErrorsUtils errorsUtils) {
		this.errorsUtils = errorsUtils;
	}
	
	static final String MODELNAME = "user";
	
	@ModelAttribute(MODELNAME)
	public UsersVO user() {
		return new UsersVO();
	}
	
	// 회원 아이디로 단건조회
	@GetMapping("/detail")
	public String userDetail(
		@RequestParam(value = "userId", required = false) String userId
		, Model model
	) {
		UsersVO user = null;
	    if (userId != null) {  //userId가 있을때만 메서드 실행
	    	user = service.searchUserById(userId).orElse(null);
	    }
		//service가 널일 일은 없음, userId는 없으면 false 처리됨
		//동기 컨트롤러니까 jsp에서 if jstl로 처리, 비동기는 (!result)로 처리
		model.addAttribute("user", user);

		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "admin/common/users/userDetail";
	}
	
	// 수정 폼으로 이동
	@GetMapping("/form/edit")
	public String editForm(String userId, Model model) {  
		if(!model.containsAttribute(MODELNAME)) {  //모델에 user가 없으면, db에서 가져옴
			UsersVO user = service.searchUserById(userId).get();
			model.addAttribute(MODELNAME, user);
		}
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "admin/common/users/userForm";
	}
	
	// 폼 입력 데이터 처리
	@PostMapping("/form/insert")
	public String userForm(
		@Validated(InsertGroup.class) @ModelAttribute(MODELNAME) UsersVO user
		, BindingResult errors
		, RedirectAttributes redirectAttributes
		, Model model
	) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		String lvn;
		if(!errors.hasErrors()) {
			service.createUser(user);
			lvn = "admin/common/users/userDetail";
		}else {
			String errorsName = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
			redirectAttributes.addFlashAttribute(MODELNAME, user);
			redirectAttributes.addFlashAttribute(errorsName, errors);
			lvn = "admin/common/users/userForm";
		}
		return lvn;
	}
	
	// 폼 수정 데이터 처리
	@PostMapping("/form/edit")
	public String userEdit(
		String userId
		, @Validated(UpdateGroup.class) @ModelAttribute(MODELNAME) UsersVO user
		, BindingResult errors
		, RedirectAttributes redirectAttributes
		, Model model
	) {
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		String lvn;
		if(!errors.hasErrors()) {
			service.modifyUser(user);
			lvn = "admin/common/users/userDetail";
		}else {
			redirectAttributes.addFlashAttribute(MODELNAME, user);
			redirectAttributes.addFlashAttribute("errors", errorsUtils.errorsToMap(errors));
			lvn = "admin/common/users/userForm";
		}
		return lvn;
	}
	
	// 회원 중복체크
	@GetMapping("detail/check")
	public String userCheck(String userId, Model model) {
		service.existsById(userId);
		
		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "admin/common/users/userDetail";
	}
	
	// 회원 이메일로 단건조회
	@GetMapping("/detail/social")
	public String socialUserDetail(
		@RequestParam(value = "email", required = false) String email
		, Model model
	) {
		UsersVO user = null;
	    if (email != null) {
	    	user = service.searchMemberByMail(email).orElse(null);
	    }
		//service가 널일 일은 없음, email은 없으면 false 처리됨
		//동기 컨트롤러니까 jsp에서 if jstl로 처리, 비동기는 (!result)로 처리
		model.addAttribute("user", user);

		model.addAttribute("boardCss", true);
		model.addAttribute("searchBar", true);
		return "admin/common/users/userDetail";
	}
	*/
}
