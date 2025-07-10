package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.ComExamOptionVO;

@Mapper
public interface ComExamOptionMapper {
	public List<ComExamOptionVO> selectComExamOptionList();
	public ComExamOptionVO selectComExamOption(String ComOptionNo);
	public int insertComExamOption(ComExamOptionVO vo);
	public int updateComExamOption(ComExamOptionVO vo);
	public int deleteComExamOption(String ComOptionNo);
	
	public List<ComExamOptionVO> selectByQuestionNo(String questionNo);
	
	
	public int updateDeleteDateComExamOption(String comOptionNo);
	public int updateDeleteDateByQuestionNo(String comQuestionNo);
} 
