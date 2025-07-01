package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="districtCodeNo")
public class DistrictCodeVO implements Serializable{
	private String districtCodeNo;
	private String cityCodeNo;
	private String districtName;
}
