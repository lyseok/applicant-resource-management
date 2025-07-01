package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="businessTypeNo")
public class BusinessTypeCodeVO implements Serializable{
	private String businessTypeNo;
	private String induNo;
	private String businessTypeName;
}
