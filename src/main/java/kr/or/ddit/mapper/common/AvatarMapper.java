package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.AvatarVO;
@Mapper
public interface AvatarMapper {
	public AvatarVO selectAvatarById(String avatarId);
	public List<AvatarVO> selectAvatarList();
	public int insertAvatar(AvatarVO avatar);
	public int updateAvatar(AvatarVO avatar);
	public int deleteAvatar(String avatarId);
}
