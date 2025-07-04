package kr.or.ddit.mapper.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.resume.SupportVO;

@Mapper
public interface SupportMapper {
	// 목록 조회
	public List<SupportVO> selectSupportList();
	// 단건 조회
	public SupportVO selectSupportDetail(String no);
	// 등록
	public int insertSupport(SupportVO vo);
	// 수정
	public int updateSupport(SupportVO vo);
	// 삭제
	public int deleteSupport(String no);
}
