package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.DistrictCodeVO;

@Mapper
public interface DistrictCodeMapper {

	public List<DistrictCodeVO> selectDistrictCodeList();

	public DistrictCodeVO selectDistrictCodeByPk(DistrictCodeVO vo);

	public int insertDistrictCode(DistrictCodeVO vo);

	public int updateDistrictCode(DistrictCodeVO vo);

	public int deleteDistrictCode(DistrictCodeVO vo);

}
