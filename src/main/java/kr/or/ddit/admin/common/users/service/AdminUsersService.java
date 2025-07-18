package kr.or.ddit.admin.common.users.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.common.UsersVO;

public interface AdminUsersService {

	public List<UsersVO> readUsersList(String userRole, String userId);
	public Optional<UsersVO> searchUserById(String userId);  //아이디로 회원 찾기
	public void createUser(UsersVO user);
	public void modifyUser(UsersVO user);
	public void removeUser(String userId);
	public void existsById(String userId);  //해당 아이디의 회원이 있는지(중복확인, 1개 나와야 가입가능)
	public Optional<UsersVO> searchMemberByMail(String email);  //이메일로 회원 찾기
}
