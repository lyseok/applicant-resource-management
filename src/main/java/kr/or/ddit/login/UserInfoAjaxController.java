package kr.or.ddit.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.security.auth.UsersVOWrapper;
import kr.or.ddit.vo.common.AdminVO;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.common.MemberVO;
import kr.or.ddit.vo.common.UsersVO;

@RestController
@RequestMapping("/ajax/userinfo")
public class UserInfoAjaxController {
    @GetMapping
    public Map<String, Object> getCurrentUserInfo(Authentication authentication) {
        Map<String, Object> map = new HashMap<>();
        if (authentication != null && authentication.getPrincipal() instanceof UsersVOWrapper) {
            UsersVO user = ((UsersVOWrapper) authentication.getPrincipal()).getRealUser();
            if (user instanceof CompanyVO) {
                map.put("userType", "company");
                map.put("userName", ((CompanyVO) user).getComName());
                map.put("userImg", ((CompanyVO) user).getComLogo());
            } else if (user instanceof AdminVO) {
                map.put("userType", "admin");
                map.put("userName", "관리자");
            } else if (user instanceof MemberVO) {
                map.put("userType", "member");
                map.put("userName", ((MemberVO) user).getMemName());
                map.put("userImg", ((MemberVO) user).getMemImg());
            } else {
                map.put("userType", "user");
            }
        }
        return map;
    }
}
