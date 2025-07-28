package kr.or.ddit.mapper.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.InduCodeVO;

@Mapper
public interface InduCodeMapper {
	public List<InduCodeVO> selectInduCodeList();
	public List<Map<String, Object>> selectInduCodeAndClassCode();
	public InduCodeVO selectInduCodeByPk(String induNo);
	public int insertInduCode(InduCodeVO induCode);
	public int updateInduCode(InduCodeVO induCode);
	public int deleteInduCode(String induNo);
}
