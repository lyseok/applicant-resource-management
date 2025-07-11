package kr.or.ddit.vo.common;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="cityCodeNo")
public class CityCodeVO implements Serializable{
	private String cityCodeNo;
	private String cityName;
	
	private List<DistrictCodeVO> districtCodeVO;
}
