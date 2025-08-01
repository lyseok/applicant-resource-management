package kr.or.ddit.company.recruitment.companyExam.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.mapper.common.CompanyMapper;
import kr.or.ddit.mapper.recruitment.ComExamOptionMapper;
import kr.or.ddit.mapper.recruitment.ComExamQuestionsMapper;
import kr.or.ddit.mapper.recruitment.CompanyExamMapper;
import kr.or.ddit.vo.common.CompanyVO;
import kr.or.ddit.vo.recruitment.ComExamOptionVO;
import kr.or.ddit.vo.recruitment.ComExamQuestionsVO;
import kr.or.ddit.vo.recruitment.CompanyExamVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyExamServiceImpl implements CompanyExamService {
	private final CompanyExamMapper companyExamMapper;
	private final ComExamQuestionsMapper comExamQuestionsMapper;
	private final ComExamOptionMapper comExamOptionMapper;
	private final CompanyMapper companyMapper;
	
	@Override
	public List<CompanyExamVO> readCompanyExamListById(String userId) {
		return companyExamMapper.selectCompanyExamListById(userId);
	}

	@Transactional
	@Override
	public void createCompanyExam(CompanyExamVO companyExam) {
	    companyExamMapper.insertCompanyExam(companyExam);
	    String examNo = companyExam.getComExamNo();

	    if (companyExam.getQuestionList() != null) {
	        for (ComExamQuestionsVO q : companyExam.getQuestionList()) {

	            // 삭제된 문제는 저장 안 함
	            if (q.getComExamQuestDelDate() != null && !q.getComExamQuestDelDate().isEmpty()) {
	                continue;
	            }

	            q.setComExamNo(examNo);
	            comExamQuestionsMapper.insertComExamQuest(q);
	            String questionNo = q.getComQuestionsNo();

	            if (q.getOptionList() != null) {
	                for (ComExamOptionVO o : q.getOptionList()) {

	                    // 삭제된 보기 저장 안 함
	                    if (o.getComOptionDelDate() != null && !o.getComOptionDelDate().isEmpty()) {
	                        continue;
	                    }

	                    o.setComQuestionsNo(questionNo);
	                    comExamOptionMapper.insertComExamOption(o);
	                }
	            }
	        }
	    }
	}




	@Override
	public CompanyVO readCompanyById(String userId) {
		return companyMapper.selectCompanyById(userId);
	}

	
	@Override
	public CompanyExamVO readCompanyExamWithQuestionAndOption(String examNo) {
		return companyExamMapper.selectCompanyExamWithQuestionAndOption(examNo);
	}


	@Override
	public boolean editExamDeleteDate(String examNo) {
		return companyExamMapper.updateExamDeleteDate(examNo);
	}

	@Transactional
	@Override
	public boolean editCompanyExamInfo(CompanyExamVO companyExam) {
	    String examNo = companyExam.getComExamNo();
	    if (companyExamMapper.updateCompanyExam(companyExam) == 0) return false;

	    List<ComExamQuestionsVO> existingQuestions = comExamQuestionsMapper.selectByQuestionExamNo(examNo);
	    Set<String> incomingQNo = new HashSet<>();

	    for (ComExamQuestionsVO q : companyExam.getQuestionList()) {
	        q.setComExamNo(examNo);
	        String qNo = q.getComQuestionsNo();

	        // 삭제된 문제는 삭제 처리
	        if (q.getComExamQuestDelDate() != null && !q.getComExamQuestDelDate().isEmpty()) {
	            if (qNo != null) {
	                comExamQuestionsMapper.updateDeleteDateComExamQuestion(qNo);
	                comExamOptionMapper.updateDeleteDateByQuestionNo(qNo);
	            }
	            continue;
	        }

	        // 새 문제 / 기존 문제 업데이트
	        if (qNo == null) {
	            comExamQuestionsMapper.insertComExamQuest(q);
	            qNo = q.getComQuestionsNo();
	        } else {
	            comExamQuestionsMapper.updateComExamQuest(q);
	        }
	        incomingQNo.add(qNo);

	        // 옵션 처리
	        List<ComExamOptionVO> existingOptions = comExamOptionMapper.selectByQuestionNo(qNo);
	        Set<String> incomingONo = new HashSet<>();

	        for (ComExamOptionVO o : q.getOptionList()) {
	            o.setComQuestionsNo(qNo);

	            // 삭제된 옵션은 삭제 처리
	            if (o.getComOptionDelDate() != null && !o.getComOptionDelDate().isEmpty()) {
	                if (o.getComOptionNo() != null) {
	                    comExamOptionMapper.updateDeleteDateComExamOption(o.getComOptionNo());
	                }
	                continue;
	            }

	            // 새 옵션 / 기존 옵션 업데이트
	            if (o.getComOptionNo() == null) {
	                comExamOptionMapper.insertComExamOption(o);
	            } else {
	                comExamOptionMapper.updateComExamOption(o);
	            }

	            if (o.getComOptionNo() != null) {
	                incomingONo.add(o.getComOptionNo());
	            }
	        }

	        // 기존 옵션 중 요청에 없는 것은 삭제 처리
	        for (ComExamOptionVO existO : existingOptions) {
	            if (!incomingONo.contains(existO.getComOptionNo())) {
	                comExamOptionMapper.updateDeleteDateComExamOption(existO.getComOptionNo());
	            }
	        }
	    }

	    // 기존 질문 중 요청에 없는 것은 삭제 처리
	    for (ComExamQuestionsVO existQ : existingQuestions) {
	        if (!incomingQNo.contains(existQ.getComQuestionsNo())) {
	            comExamOptionMapper.updateDeleteDateByQuestionNo(existQ.getComQuestionsNo());
	            comExamQuestionsMapper.updateDeleteDateComExamQuestion(existQ.getComQuestionsNo());
	        }
	    }

	    return true;
	}


}
