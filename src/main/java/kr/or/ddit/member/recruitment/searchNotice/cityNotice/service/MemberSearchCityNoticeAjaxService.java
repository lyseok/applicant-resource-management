package kr.or.ddit.member.recruitment.searchNotice.cityNotice.service;

import java.util.List;

import kr.or.ddit.vo.common.BusinessTypeCodeVO;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;

public interface MemberSearchCityNoticeAjaxService {
	
	public List<RecruitmentNoticeVO> readRecruitList();  //전체 공고 리스트, 검색은 쿼리스트링으로
	public BusinessTypeCodeVO readBusinessTypeCode(String no);
}
