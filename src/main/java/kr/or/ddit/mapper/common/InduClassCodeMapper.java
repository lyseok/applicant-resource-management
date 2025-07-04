package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.InduClassCodeVO;

@Mapper
public interface InduClassCodeMapper {
	public List<InduClassCodeVO> selectInduClassCodeList();
	public InduClassCodeVO selectInduClassCodeBuPk(String no);
	public int insertInduClassCode(InduClassCodeVO vo);
	public int updateInduClassCode(InduClassCodeVO vo);
	public int deleteInduClassCode(String induClassNo);
}
