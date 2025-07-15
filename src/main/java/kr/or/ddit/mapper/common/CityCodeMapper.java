package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.CityCodeVO;

@Mapper
public interface CityCodeMapper {
	public List<CityCodeVO> selectCityCodeList();
	
	public List<CityCodeVO> selectRealCityCodeList();

	public CityCodeVO selectCityCodeByPk(CityCodeVO vo);
	
	public int insertCityCode(CityCodeVO vo);
	
	public int updateCityCode(CityCodeVO vo);
	
	public int deleteCityCode(CityCodeVO vo);
}
