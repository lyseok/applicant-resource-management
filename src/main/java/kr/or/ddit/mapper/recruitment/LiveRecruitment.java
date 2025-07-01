package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;

@Mapper
public interface LiveRecruitment {
 public List<RecruitmentNoticeVO> selectLiveRecruitment();
}
