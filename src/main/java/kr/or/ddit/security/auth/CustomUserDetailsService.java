package kr.or.ddit.security.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.AdminMapper;
import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.mapper.common.MemberMapper;
import kr.or.ddit.mapper.common.UserMapper;
import kr.or.ddit.vo.common.UsersVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

//	private final UserMapper mapper;	
	private final UserMapper userMapper;
	private final MemberMapper memberMapper;
	private final CompanyMapper companyMapper;
	private final AdminMapper adminMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsersVO realUser = userMapper.selectUserById(username);
		
//		if(realUser==null) {
//			throw new UsernameNotFoundException(String.format("%s 회원 없음", username));
//		}

        // 1) 공통 사용자 정보 조회
        UsersVO base = userMapper.selectUserById(username);
        if (base == null) {
            throw new UsernameNotFoundException(username + " 회원 없음");
        }
        
     // 2) ROLE 에 따라 상세 VO 선택
        String role = base.getUserRole();
        UsersVO detailed;
        switch (role) {
            case "ROLE_USER":
                detailed = memberMapper.selectMemberById(username);
                break;
            case "ROLE_COMPANY":
                detailed = companyMapper.selectCompanyById(username);
                break;
            case "ROLE_ADMIN":
                detailed = adminMapper.selectAdminById(username);
                break;
            default:
                // ROLE_SOCIAL 등, 공통 VO만 있으면 base 그대로
                detailed = base;
        }
		
		return new UsersVOWrapper(detailed);
	}

}