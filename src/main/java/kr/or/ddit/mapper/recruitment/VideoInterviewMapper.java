package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.VideoInterviewVO;

@Mapper
public interface VideoInterviewMapper {
	public List<VideoInterviewVO> selectVideoInterviewList();
	public VideoInterviewVO selectVideoInterview(String VideoInterviewNo);
	public int insertVideoInterview(VideoInterviewVO vo);
	public int updateVideoInterview(VideoInterviewVO vo);
	public int deleteVideoInterview(String VideoInterviewNo);
}
