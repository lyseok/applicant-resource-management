package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.ComExamQuestionsVO;

@Mapper
public interface ComExamQuestionsMapper {
	public List<ComExamQuestionsVO> selectComExamQuestList();
	public ComExamQuestionsVO selectComExamQuest(String comQuestionsNo);
	public int insertComExamQuest(ComExamQuestionsVO vo);
	public int updateComExamQuest(ComExamQuestionsVO vo);
	public int deleteComExamQuest(String comQuestionsNo);
	
	
	public List<ComExamQuestionsVO> selectByQuestionExamNo(String examNo);
	public int updateDeleteDateComExamQuestion(String comQuestionNo);
}
