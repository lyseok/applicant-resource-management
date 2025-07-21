package kr.or.ddit.mapper.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.resume.ResumeVO;

@Mapper
public interface ResumeMapper {
	// 전체 리스트 조회
	public List<ResumeVO> selectResumeList(String userId);
	// 단건 조회
	public ResumeVO selectResumeDetail(ResumeVO resumeVO);
	// 등록
	public int insertResume(ResumeVO resumeVO);
	// 수정
	public int updateResume(ResumeVO resumeVO);
	// 논리적 삭제
	public int updateResumeDelete(ResumeVO resumeVO);
	// 삭제
	public int deleteResume(String ResumeNo);

	// 자기소개서 조회
	public ResumeVO selectIntroductionWithResume(ResumeVO resumeVO);
	
	//추가
	public List<ResumeVO> selectResumeWithCareers(String userId);
	
	// 해당회원이 작성한 이력서 갯수 구해옴 -> name님의 이력서 n 으로 사용
	public int selectUserResumeNoCount(String userId);
	
	// 이력서 제목으로 검색조회
	public List<ResumeVO> selectResumeSearch(ResumeVO resumeVO);
	
	
}
