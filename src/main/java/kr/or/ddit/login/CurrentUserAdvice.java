package kr.or.ddit.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.or.ddit.security.auth.UsersVOWrapper;
import kr.or.ddit.vo.common.AdminVO;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.common.MemberVO;
import kr.or.ddit.vo.common.UsersVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CurrentUserAdvice {

//    @ModelAttribute("currentUser")
//    public UsersVO currentUser(Authentication authentication) {
//        if (authentication == null
//         || !(authentication.getPrincipal() instanceof UsersVOWrapper)) {
//            return null;
//        }
//        // getRealUser() → MemberVO 또는 CompanyVO 등 상세 타입 반환
//        log.info("{}", ((UsersVOWrapper) authentication.getPrincipal()).getRealUser());
//        return ((UsersVOWrapper) authentication.getPrincipal()).getRealUser();
//    }
    
    @ModelAttribute
    public void addUserInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof UsersVOWrapper) {
            UsersVO user = ((UsersVOWrapper) principal).getRealUser();
            if (user instanceof CompanyVO) {
                model.addAttribute("userType", "company");
                model.addAttribute("comName", ((CompanyVO) user).getComName());
            } else if (user instanceof AdminVO) {
                model.addAttribute("userType", "admin");
            } else if (user instanceof MemberVO) {
                model.addAttribute("userType", "member");
                model.addAttribute("memberName", ((MemberVO) user).getMemName());
                model.addAttribute("memberInfo", ((MemberVO) user));
            } else {
                model.addAttribute("userType", "user");
            }
        }
    }
}