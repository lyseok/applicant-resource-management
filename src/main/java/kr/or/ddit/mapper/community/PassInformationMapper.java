package kr.or.ddit.mapper.community;

import java.util.List;


import kr.or.ddit.vo.community.PassInformationVO;

public interface PassInformationMapper {
	
public List<PassInformationMapper> selectInterviewInfromationList();
	
	public PassInformationMapper selectInterviewInformationVO();
	
	public int insertPassInformation(PassInformationVO passInfromation);
}
