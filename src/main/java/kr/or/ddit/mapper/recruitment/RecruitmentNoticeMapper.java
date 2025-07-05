package kr.or.ddit.mapper.recruitment;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;

@Mapper
public interface RecruitmentNoticeMapper {
 public List<RecruitmentNoticeVO> readRecruitmentNoticeList();
  
 public Optional<RecruitmentNoticeVO> selectLiveRecruitment(String recruitmentNo);
 // 최신순으로 보기
 
 public List<RecruitmentNoticeVO> salaryRecruitment(String recruitmentNo);
 // 연봉순으로 보기
 
 public Optional<RecruitmentNoticeVO> hotRecruitment(String recruitmentNo);
 
 public RecruitmentNoticeVO selectliveRecruitmentDetail(String recruitmentNo);
 // 공고 자세히보기
 
 public int insertRecruitmentNotice(RecruitmentNoticeVO recruitmentNoticeVO);
 
 public int updateRecruitmentNotice(RecruitmentNoticeVO recruitmentNoticeVO);
 
}
