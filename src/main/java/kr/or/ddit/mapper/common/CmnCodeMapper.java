package kr.or.ddit.mapper.common;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.CmnCodeVO;

@Mapper
public interface CmnCodeMapper {
	public CmnCodeVO selectCmnCodeByPk(String codeDetailNo);
	public int insertCmnCode(CmnCodeVO cmnCod);
	public int updateCmnCode(CmnCodeVO cmnCod);
	public int deleteCmnCode(String CODE_GRcodeDetailNoOUP_NO);
}
