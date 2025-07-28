package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.dto.PassIntroductionDetailDTO;
import kr.or.ddit.vo.recruitment.PassIntroductionVO;

@Mapper
public interface PassIntroductionMapper {
	public List<PassIntroductionVO> selectPassIntroductionList();
	public PassIntroductionVO selectPassIntroduction(String passIntroNo);
	public int insertPassIntroduction(PassIntroductionVO vo);
	public int deletePassIntroduction(String passIntroNo);
	
	public List<PassIntroductionDetailDTO> selectPassIntroductionDetailInfo(String comId);
	
}
