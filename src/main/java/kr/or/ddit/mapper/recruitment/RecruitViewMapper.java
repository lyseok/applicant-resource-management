package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.dto.RecruitViewDTO;
import kr.or.ddit.vo.recruitment.RecruitViewVO;

@Mapper
public interface RecruitViewMapper {
	public List<RecruitViewDTO> selectRecruitViewList(String id);
	public int insertRecruitView(RecruitViewVO vo);
}
