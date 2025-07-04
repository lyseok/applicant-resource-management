package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.RecruitmentExamOptionVO;

@Mapper
public interface RecruitmentExamOptionMapper {
	public List<RecruitmentExamOptionVO> selectRecrExamOptionList();
	public RecruitmentExamOptionVO selectRecrExamOption(String recruitOptionNo);
	public int insertRecrExamOption(RecruitmentExamOptionVO vo);
	public int updateRecrExamOption(RecruitmentExamOptionVO vo);
	public int deleteRecrExamOption(String recruitOptionNo);
}
