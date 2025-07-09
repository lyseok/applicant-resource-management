package kr.or.ddit.mapper.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.resume.AwardVO;
import kr.or.ddit.vo.resume.ResumeVO;

@Mapper
public interface AwardMapper {
	// 목록 조회
	public List<AwardVO> selectAwardList(String no);
	// 단건 조회
	public AwardVO selectAwardDetail(ResumeVO vo);
	// 등록
	public void insertAward(AwardVO vo);
	// 수정
	public void updateAward(AwardVO vo);
	// 삭제
	public void deleteAward(String no);
}
