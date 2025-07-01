package kr.or.ddit.mapper.common;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.MemberVO;

@Mapper
public interface MemberMapper2 {
	public int insertMember(MemberVO member);
}
