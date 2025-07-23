package kr.or.ddit.mapper.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.resume.IntroductionQuestionVO;

@Mapper
public interface introductionQuestionMapper {
	public List<IntroductionQuestionVO> readIntroductionQuestionList(String introductionNo);
	public IntroductionQuestionVO readIntroductionQuestionDetail(String questionNo);
	public int createIntroductionQuestion(IntroductionQuestionVO vo);
	public int editIntroductionQuestion(IntroductionQuestionVO vo);
	public int removeIntroductionQuestion(String introductionNo);
}
