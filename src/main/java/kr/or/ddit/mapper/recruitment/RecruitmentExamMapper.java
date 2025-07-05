package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.RecruitmentExamVO;

@Mapper
public interface RecruitmentExamMapper {
	public List<RecruitmentExamVO> selectRecruitExamList();
	public RecruitmentExamVO selectRecruitExam(String recruitExamNo);
	public int insertRecruitExam(RecruitmentExamVO vo);
	public int updateRecruitExam(RecruitmentExamVO vo);
	public int deleteRecruitExam(String recruitExamNo);
}
