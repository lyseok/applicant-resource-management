package kr.or.ddit.vo.common;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="induClassNo")
public class InduClassCodeVO implements Serializable{
	private String induClassNo;
	private String induClassName;
	
	private InduCodeVO induCodeVO;
	private List<InduCodeVO> indoCodeList;
}
