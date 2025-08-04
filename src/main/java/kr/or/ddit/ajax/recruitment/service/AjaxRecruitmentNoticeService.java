package kr.or.ddit.ajax.recruitment.service;

import java.util.Map;

public interface AjaxRecruitmentNoticeService {
	public Map<String, Object> searchRecruitmentNoticeList(Map<String, Object> params);
	public Map<String, Object> selectMainPRecruitmentNoticeList();
	public Map<String, Object> selectMainMiddleRecruitmentNoticeList();
	public Map<String, Object> selectMainBottomRecruitmentNoticeList();
}
