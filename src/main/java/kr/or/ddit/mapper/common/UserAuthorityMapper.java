package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.UserAuthorityVO;

@Mapper
public interface UserAuthorityMapper {
	public List<UserAuthorityVO> selectUserAuthorityList();
	public UserAuthorityVO selectUserAuthorityByPk();
	public int insertUserAuthority();
	public int updateUserAuthority();
	public int deleteUserAuthority();
}
