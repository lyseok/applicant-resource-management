package kr.or.ddit.mapper.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.resume.AwardVO;

@Mapper
public interface AwardMapper {
	// 목록 조회
	public List<AwardVO> selectAwardList();
	// 단건 조회
	public AwardVO selectAwardDetail(String no);
	// 등록
	public int insertAward(AwardVO vo);
	// 수정
	public int updateAward(AwardVO vo);
	// 삭제
	public int deleteAward(String no);
}
