package kr.or.ddit.mapper.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.resume.introductionQuestionVO;

@Mapper
public interface introductionQuestionMapper {
	public List<introductionQuestionVO> readIntroductionQuestionList(String introductionNo);
	public introductionQuestionVO readIntroductionQuestionDetail(String questionNo);
	public int createIntroductionQuestion(introductionQuestionVO vo);
	public int editIntroductionQuestion(introductionQuestionVO vo);
	public int removeIntroductionQuestion(String introductionNo);
}
