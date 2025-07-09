package kr.or.ddit.ajax.code.service;

import java.util.List;

import kr.or.ddit.vo.common.BusinessTypeCodeVO;
import kr.or.ddit.vo.common.CityCodeVO;
import kr.or.ddit.vo.common.CmnCodeGroupVO;
import kr.or.ddit.vo.common.DistrictCodeVO;
import kr.or.ddit.vo.common.InduClassCodeVO;
import kr.or.ddit.vo.common.InduCodeVO;
import kr.or.ddit.vo.common.JobVO;
import kr.or.ddit.vo.common.TopJobVO;

public interface CodeService {
	public List<TopJobVO> readTopJobList();
	public List<JobVO> readJobList();
	public List<CityCodeVO> readCityCodeList();
	public List<DistrictCodeVO> readDistrictCodeList();
	public List<InduClassCodeVO> readInduClassCodeList();
	public List<InduCodeVO> readInduCodeList();
	public List<BusinessTypeCodeVO> readBusinessTypeCodeList();
	public CmnCodeGroupVO readCmnCodeGroupByPk(String codeGroupNo);
}
