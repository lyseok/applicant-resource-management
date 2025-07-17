package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.RecruitProcessVO;

@Mapper
public interface RecruitProcessMapper {
	// 리스트 조회
	public List<RecruitProcessVO> selectRecruitprocessList();
	// 단건 조회
	public RecruitProcessVO selectRecruitprocess(String recruitProcessNo);
	// 채용 공고 no로 찾기
	public RecruitProcessVO selectProcessByRecruit(String recruitmentNo);
	// 삽입
	public int insertRecruitProcess(RecruitProcessVO processVO);
	// 업데이트
	public int updateRecruitProcess(RecruitProcessVO processVO);
	// 삭제(없음)
	public int deleteRecruitProcess(String recruitProcessNo);
	// 다음 단계 찾기
	public RecruitProcessVO selectNextStep(RecruitProcessVO vo);
}
