package kr.or.ddit.mapper.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.dto.UserDTO;
import kr.or.ddit.vo.common.MemberVO;

@Mapper
public interface MemberMapper {
	public MemberVO selectMemberById(String userId);
	public List<MemberVO> selectMemberList();
	public int insertMember(MemberVO member);
	public int updateMember(MemberVO member);
	public int deleteMember(String userId);
	public Map<String, Object> selectMyPageInfo(String userId);
	
	public UserDTO selectUserWithProjects(String userId);
	public UserDTO selectCompanyWithProjects(String userId);
}


