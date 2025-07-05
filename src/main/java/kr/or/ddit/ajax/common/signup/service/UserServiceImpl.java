package kr.or.ddit.ajax.common.signup.service;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.UserMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserMapper usermapper;

	@Override
	public int idDuplicateCheck(String userId) {
		return usermapper.existsById(userId);
	}

}
