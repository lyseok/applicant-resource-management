package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.SocialMemberVO;

@Mapper
public interface SocialMemberMapper {
	public SocialMemberVO selectSocialMemberById(String id);
	public List<SocialMemberVO> selectSocialMemberList();
	public int insertSocialMember();
	public int updateSocialMember();
	public int deleteSocialMember();
}
