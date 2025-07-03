package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.MemberVO;

@Mapper
public interface MemberMapper {
	public MemberVO selectMemberById(String userId);
	public List<MemberVO> selectMemberList();
	public int insertMember(MemberVO member);
	public int updateMember(MemberVO member);
	public int deleteMember(String userId);
}
