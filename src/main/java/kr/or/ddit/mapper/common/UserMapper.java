package kr.or.ddit.mapper.common;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.UsersVO;

@Mapper
public interface UserMapper {
	public int insertUser(UsersVO user);
	public int existsById(String id);
}
