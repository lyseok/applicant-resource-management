package kr.or.ddit.mapper.recruitment;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.dto.RecruitmentNoticeDTO;
import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;

@Mapper
public interface RecruitmentNoticeMapper {
 public List<RecruitmentNoticeVO> readRecruitmentNoticeList();
 
 public List<Map<String, Object>> selectMyRecruitNotice(String userId); 
 
 public List<Map<String, Object>> selectApplicatedNotice(String userId);
  
 public Optional<RecruitmentNoticeVO> realTimeRecruitment();
 // 최신순으로 보기
 
 public List<RecruitmentNoticeVO> salaryRecruitment();
 // 연봉순으로 보기
 
 public Optional<RecruitmentNoticeVO> hotRecruitment(String recruitmentNo);
 
 public RecruitmentNoticeVO selectliveRecruitmentDetail(String recruitmentNo);
 // 공고 자세히보기
 
 public int insertRecruitmentNotice(RecruitmentNoticeVO recruitmentNoticeVO);
 
 public int updateRecruitmentNotice(RecruitmentNoticeVO recruitmentNoticeVO);
 
 public int deleteRecruitmentNotice(String recruitmentNo);
 
 public int updateRecruitDeadLine(String recruitmentNo);
 
 public List<RecruitmentNoticeVO> selectRecruitNoticeByUserId(String userId);
 
 public int countRecruitmentNotice(Map<String, Object> params);
 
 public List<RecruitmentNoticeDTO> selectMainPRecruitNoticeDtoList();
 public List<RecruitmentNoticeDTO> selectMainMiddleRecruitNoticeDtoList();
 public List<RecruitmentNoticeDTO> selectMainBottomRecruitNoticeDtoList();
 public List<RecruitmentNoticeDTO> searchRecruitNoticeDtoList(Map<String, Object> params);
 
 
 public List<Map<String, Object>> selectTopFiveJobNotice(String userId);
}
