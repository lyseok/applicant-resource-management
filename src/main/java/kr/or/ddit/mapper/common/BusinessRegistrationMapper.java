package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.BusinessregistrationVO;

@Mapper
public interface BusinessRegistrationMapper {
	public BusinessregistrationVO selectBusinessRegistrationById();
	public List<BusinessregistrationVO> selectBusinessRegistrationList();
	
	
}
