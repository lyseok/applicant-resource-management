package kr.or.ddit.member.common.mypage.introduction.service;

import java.util.List;

import kr.or.ddit.dto.PaginationDTO;
import kr.or.ddit.vo.resume.IntroductionVO;

// 자소서를 작성한 사람만 cud 할 수 있어야 함
public interface introductionService {
	public List<IntroductionVO> readIntroductionList(String userId);
	public IntroductionVO readIntroductionDetail(String no);
	public int createIntroduction(IntroductionVO vo);
	public int editIntroduction(IntroductionVO vo);
	public int removeIntroduction(IntroductionVO vo);
	public List<IntroductionVO> readIntroductionSearch(String name);
	
	
	public int getTotalCount(String userId);
	public List<IntroductionVO> getIntroductionPagingList(String userId, int offset, int limit);

	// 자소서 검색 - 페이징 처리
	public int getSearchCount(String userId, String keyword);
	public List<IntroductionVO> searchIntroductionList(String userId, String keyword, int offset, int pageSize);

}
