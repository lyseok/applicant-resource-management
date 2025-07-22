package kr.or.ddit.rest.user.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.or.ddit.dto.UserDTO;
import kr.or.ddit.mapper.common.MemberMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRestServiceImpl implements UserRestService {
	private final MemberMapper memberMapper;
	
	@Override
	public UserDTO readUserWithProjects() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		boolean isUser = authentication.getAuthorities().stream()
			    .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"));
		
		if(isUser) {
			return memberMapper.selectUserWithProjects(username);
		} else {
			return memberMapper.selectCompanyWithProjects(username);
		}
		
	}

}
