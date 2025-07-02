package kr.or.ddit.security.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.or.ddit.mapper.MemberMapper;
import kr.or.ddit.mapper.common.UserMapper;
import kr.or.ddit.vo.ExMemberVO;
import kr.or.ddit.vo.common.UsersVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserMapper mapper;	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsersVO realUser = mapper.selectUser(username);
		
		if(realUser==null) {
			throw new UsernameNotFoundException(String.format("%s 회원 없음", username));
		}
		
		return new UsersVOWrapper(realUser);
	}

}