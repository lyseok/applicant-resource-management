package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.SocialMemberVO;

@Mapper
public interface SocialMemberMapper {
	public SocialMemberVO selectSocialMemberById(SocialMemberVO vo);
	public List<SocialMemberVO> selectSocialMemberList();
	public int insertSocialMember(SocialMemberVO socialMember);
	public int updateSocialMember(SocialMemberVO socialMember);
	public int deleteSocialMember(SocialMemberVO vo);
}
