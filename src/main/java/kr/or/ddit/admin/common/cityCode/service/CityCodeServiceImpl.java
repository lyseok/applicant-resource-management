package kr.or.ddit.admin.common.cityCode.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.CityCodeMapper;
import kr.or.ddit.mapper.common.DistrictCodeMapper;
import kr.or.ddit.vo.common.CityCodeVO;
import kr.or.ddit.vo.common.DistrictCodeVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityCodeServiceImpl implements CityCodeService {

	private final CityCodeMapper cityMapper;
	private final DistrictCodeMapper districtMapper;
	
	public List<CityCodeVO> readCityCode(){
		return cityMapper.selectRealCityCodeList();
	};
	
	public List<DistrictCodeVO> readDistrictCodeByCity(String cityCodeNo){
		return districtMapper.selectDistrictCodeListByCity(cityCodeNo);
	};
}
