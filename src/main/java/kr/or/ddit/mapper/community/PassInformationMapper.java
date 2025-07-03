package kr.or.ddit.mapper.community;

import java.util.List;


import kr.or.ddit.vo.community.PassInformationVO;

public interface PassInformationMapper {
	public List<PassInformationMapper> selectPassInfromationList();
	public PassInformationMapper selectPassInformation(String pInfoNo);
	public int insertPassInformation(PassInformationVO passInfromation);
}
