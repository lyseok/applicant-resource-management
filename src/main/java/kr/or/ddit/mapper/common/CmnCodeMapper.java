package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.CmnCodeVO;

@Mapper
public interface CmnCodeMapper {
	public List<CmnCodeVO> selectAll();
	public CmnCodeVO selectCmnCodeByPk(String codeDetailNo);
	public List<CmnCodeVO> selectCmnCodeListByUc(String upperCodeNo);
	public int insertCmnCode(CmnCodeVO cmnCod);
	public int updateCmnCode(CmnCodeVO cmnCod);
	public int deleteCmnCode(String CODE_GRcodeDetailNoOUP_NO);
}
