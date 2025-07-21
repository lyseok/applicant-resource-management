package kr.or.ddit.company.recruitment.exam.service;

import java.util.List;

import kr.or.ddit.vo.recruitment.RecruitmentExamVO;

public interface RecruitExamService {
	public void copyCompanyExamToRecruit(String processNo, RecruitmentExamVO exam);
}
