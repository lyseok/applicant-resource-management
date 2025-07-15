package kr.or.ddit.ajax.code.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.BusinessTypeCodeMapper;
import kr.or.ddit.mapper.common.CityCodeMapper;
import kr.or.ddit.mapper.common.CmnCodeGroupMapper;
import kr.or.ddit.mapper.common.CmnCodeMapper;
import kr.or.ddit.mapper.common.DistrictCodeMapper;
import kr.or.ddit.mapper.common.InduClassCodeMapper;
import kr.or.ddit.mapper.common.InduCodeMapper;
import kr.or.ddit.mapper.common.JobMapper;
import kr.or.ddit.mapper.common.TopJobMapper;
import kr.or.ddit.vo.common.BusinessTypeCodeVO;
import kr.or.ddit.vo.common.CityCodeVO;
import kr.or.ddit.vo.common.CmnCodeGroupVO;
import kr.or.ddit.vo.common.CmnCodeVO;
import kr.or.ddit.vo.common.DistrictCodeVO;
import kr.or.ddit.vo.common.InduClassCodeVO;
import kr.or.ddit.vo.common.InduCodeVO;
import kr.or.ddit.vo.common.JobVO;
import kr.or.ddit.vo.common.TopJobVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService {
	public final TopJobMapper topJobMapper;
	public final JobMapper jobMapper;
	public final InduClassCodeMapper induClassCodeMapper;
	public final InduCodeMapper indCodeMapper;
	public final BusinessTypeCodeMapper businessTypeCodeMapper;
	public final CityCodeMapper cityCodeMapper;
	public final DistrictCodeMapper districtCodeMapper;
	public final CmnCodeGroupMapper cmnCodeGroupMapper;
	public final CmnCodeMapper cmnCodeMapper;

	@Override
	public List<TopJobVO> readTopJobList() {
		return topJobMapper.selectTopJobList();
	}

	@Override
	public List<JobVO> readJobList() {
		return jobMapper.selectJobList();
	}

	@Override
	public List<CityCodeVO> readCityCodeList() {
		return cityCodeMapper.selectCityCodeList();
	}

	@Override
	public List<DistrictCodeVO> readDistrictCodeList() {
		return districtCodeMapper.selectDistrictCodeList();
	}

	@Override
	public List<InduClassCodeVO> readInduClassCodeList() {
		return induClassCodeMapper.selectInduClassCodeList();
	}

	@Override
	public List<InduCodeVO> readInduCodeList() {
		return indCodeMapper.selectInduCodeList();
	}

	@Override
	public List<BusinessTypeCodeVO> readBusinessTypeCodeList() {
		return businessTypeCodeMapper.selectBusinessTypeCodeList();
	}

	@Override
	public CmnCodeGroupVO readCmnCodeGroupByPk(String codeGroupNo) {
		return cmnCodeGroupMapper.selectCmnCodeGroupByPk(codeGroupNo);
	}

	@Override
	public List<CmnCodeVO> readCmnCodeListByUc(String upperCodeNo) {
		return cmnCodeMapper.selectCmnCodeListByUc(upperCodeNo);
	}

	


}
