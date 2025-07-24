package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.AdminVO;

@Mapper
public interface AdminMapper {	
	
	public List<AdminVO> selectAdminList();
	public AdminVO selectAdminById(String userId);
	public int insertAdmin(AdminVO admin);
	public int updateAdmin(AdminVO admin);
	public int deleteAdmin(String userId);
}
