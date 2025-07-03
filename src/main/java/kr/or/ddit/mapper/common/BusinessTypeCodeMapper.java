package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.BusinessTypeCodeVO;

@Mapper
public interface BusinessTypeCodeMapper {
	public List<BusinessTypeCodeVO> selectBusinessTypeCodeList();
	public BusinessTypeCodeVO selectBusinessTypeCode(String no);
	public int insertBusinessTypeCode(BusinessTypeCodeVO businessTypeCode);
	public int updateBusinessTypeCode(BusinessTypeCodeVO businessTypeCode);
	public int deleteBusinessTypeCode(String no);
}
