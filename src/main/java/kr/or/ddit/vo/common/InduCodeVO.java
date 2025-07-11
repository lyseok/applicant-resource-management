package kr.or.ddit.vo.common;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="induNo")
public class InduCodeVO implements Serializable{
	private String induNo;
	private String induClassNo;
	private String induName;
	
	private List<BusinessTypeCodeVO> businessTypeCodeVO;
}
