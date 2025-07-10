package kr.or.ddit.admin.common.users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.UserMapper;
import kr.or.ddit.vo.common.UsersVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminUsersServiceImpl implements AdminUsersService {
	
	private final UserMapper mapper;

	@Override
	public List<UsersVO> readUsersList() {
		return mapper.selectUsersList();
	}

	@Override
	public Optional<UsersVO> searchUserById(String userId) {
		return Optional.ofNullable(mapper.selectUserById(userId));
	}

	@Override
	public void createUser(UsersVO user) {
		mapper.insertUser(user);
	}

	@Override
	public void modifyUser(UsersVO user) {
		mapper.updateUser(user);
	}

	@Override
	public void removeUser(String userId) {
		mapper.deleteUser(userId);
	}

	@Override
	public void existsById(String userId) {
		mapper.existsById(userId);
	}

	@Override
	public Optional<UsersVO> searchMemberByMail(String email) {
		return Optional.ofNullable(mapper.selectMemberByMail(email));
	}

}
