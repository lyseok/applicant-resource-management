package kr.or.ddit.mapper.community;

import java.util.List;


import kr.or.ddit.vo.community.PassInformationVO;

public interface PassInformationMapper {
	public List<PassInformationMapper> selectPassInfromationList();
	public PassInformationVO selectPassInformationByPk(String passInformationNo);
	public int insertPassInformation(PassInformationVO passInfromation);
	public int updatePassInformation(PassInformationVO passInfromation);
	public int deletePassInformation(String passInformationNo);
}
