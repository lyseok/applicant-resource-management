package kr.or.ddit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.EmploymentTestVO;

@Mapper
public interface EmploymentTestMapper {
	public List<EmploymentTestVO> selectListEmploymentTest();
}
