package kr.or.ddit.login;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.or.ddit.security.auth.UsersVOWrapper;
import kr.or.ddit.vo.common.UsersVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CurrentUserAdvice {

    @ModelAttribute("currentUser")
    public UsersVO currentUser(Authentication authentication) {
        if (authentication == null
         || !(authentication.getPrincipal() instanceof UsersVOWrapper)) {
            return null;
        }
        // getRealUser() → MemberVO 또는 CompanyVO 등 상세 타입 반환
        log.info("{}", ((UsersVOWrapper) authentication.getPrincipal()).getRealUser());
        return ((UsersVOWrapper) authentication.getPrincipal()).getRealUser();
    }
}