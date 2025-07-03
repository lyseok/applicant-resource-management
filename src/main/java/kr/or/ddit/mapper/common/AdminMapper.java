package kr.or.ddit.mapper.common;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.AdminVO;

@Mapper
public interface AdminMapper {
	public AdminVO selectAdminById(String userId);
}
