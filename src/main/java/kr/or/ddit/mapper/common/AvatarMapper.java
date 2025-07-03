package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.AvatarVO;
@Mapper
public interface AvatarMapper {
	public AvatarVO selectAvatarById(String id);
	public List<AvatarVO> selectAvatarList();
	public int insertAvatar();
	public int updateAvatar();
	public int deleteAvatar();
}
