package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.RecruitmentExamQuestionsVO;

@Mapper
public interface RecruimentExamQuestionsMapper {
	public List<RecruitmentExamQuestionsVO> selectRecrExamQuestList();
	public RecruitmentExamQuestionsVO selectRecrExamQuest(String recruitExamQuestNo);
	public int insertRecrExamQuest(RecruitmentExamQuestionsVO vo);
	public int updateRecrExamQuest(RecruitmentExamQuestionsVO vo);
	public int deleteRecrExamQuest(String recruitExamQuestNo);
}
