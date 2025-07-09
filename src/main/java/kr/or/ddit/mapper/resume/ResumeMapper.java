package kr.or.ddit.mapper.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.resume.ResumeVO;

@Mapper
public interface ResumeMapper {
	// 전체 리스트 조회
	public List<ResumeVO> selectResumeList(String userId);
	// 단건 조회
	public ResumeVO selectResumeDetail(ResumeVO ResumeVO);
	// 등록
	public int insertResume(ResumeVO ResumeVO);
	// 수정
	public int updateResume(ResumeVO ResumeVO);
	// 삭제
	public int deleteResume(String ResumeNo);
	
	//추가
	public List<ResumeVO> selectResumeWithCareers(String userId);
	
}
