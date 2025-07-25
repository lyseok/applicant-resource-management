package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.BusinessregistrationVO;

@Mapper
public interface BusinessRegistrationMapper {
	public BusinessregistrationVO selectBusinessRegistrationByPk(String brNumber);
	public List<BusinessregistrationVO> selectBusinessRegistrationList();
	public int duplicatedBusinessregistration(String brNumber);
	public int insertBusinessregistration(BusinessregistrationVO businessregistration);
	public int updateBusinessregistration(BusinessregistrationVO businessregistration);
	public int deleteBusinessregistration(String brNumber);
}
