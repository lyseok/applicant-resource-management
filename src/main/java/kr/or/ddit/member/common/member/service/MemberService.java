package kr.or.ddit.member.common.member.service;

import kr.or.ddit.vo.common.MemberVO;
import kr.or.ddit.vo.common.UsersVO;

public interface MemberService {
	public void registerMember(MemberVO member);
	public int idDuplicateCheck(String userId);
}
