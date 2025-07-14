package kr.or.ddit.mapper.recruitment;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.dto.MyRecruitExamDTO;
import kr.or.ddit.vo.recruitment.RecruitmentExamVO;

@Mapper
public interface RecruitmentExamMapper {
	public List<RecruitmentExamVO> selectRecruitExamList();
	public RecruitmentExamVO selectRecruitExam(String recruitExamNo);
	public int insertRecruitExam(RecruitmentExamVO vo);
	public int updateRecruitExam(RecruitmentExamVO vo);
	public int deleteRecruitExam(String recruitExamNo);
	
	public List<MyRecruitExamDTO> selectMyRecruitExams(String userId);
	public RecruitmentExamVO selectRecruitExamQuestionWithOptionByNo(String recruitExamNo);
	public int selectCutlineByExamNo(String recruitExamNo);
	public int updateStepApplicationYN(Map<String, Object> params);
	
}
