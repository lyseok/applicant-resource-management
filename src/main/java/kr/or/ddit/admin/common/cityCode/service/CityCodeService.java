package kr.or.ddit.admin.common.cityCode.service;

import java.util.List;

import kr.or.ddit.vo.common.CityCodeVO;
import kr.or.ddit.vo.common.DistrictCodeVO;

public interface CityCodeService {

	public List<CityCodeVO> readCityCode();
	
	public List<DistrictCodeVO> readDistrictCodeByCity(String cityCodeNo);
}
