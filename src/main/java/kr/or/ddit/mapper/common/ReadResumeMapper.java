package kr.or.ddit.mapper.common;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.ReadResumeVO;

@Mapper
public interface ReadResumeMapper {

	public int insertReadResume(ReadResumeVO vo);
}
