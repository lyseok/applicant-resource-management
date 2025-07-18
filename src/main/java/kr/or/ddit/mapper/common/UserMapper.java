package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.UsersVO;

@Mapper
public interface UserMapper {
	public List<UsersVO> selectUsersList(String userRole, String userId);
	public UsersVO selectUserById(String userId);
	public int insertUser(UsersVO user);
	public int updateUser(UsersVO user);
	public int deleteUser(String userId);
	public int existsById(String userId);
	public UsersVO selectMemberByMail(String email);
}
