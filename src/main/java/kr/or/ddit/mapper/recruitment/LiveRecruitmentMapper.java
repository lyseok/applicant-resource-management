package kr.or.ddit.mapper.recruitment;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;

@Mapper
public interface LiveRecruitmentMapper {
 public List<RecruitmentNoticeVO> readRecruitmentNoticeList();
  
 public Optional<RecruitmentNoticeVO> readRecruitmentNotice(String recruitmentNo);
 // 최신순으로 보기
 
 public Optional<RecruitmentNoticeVO> salaryRecruitment(String recruitmentNo);
 // 연봉순으로 보기
 
 public RecruitmentNoticeVO selectliveRecruitmentDetail(String recruitmentNo);
 // 공고 자세히보기
 
}
