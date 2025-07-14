package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="codeDetailNo")
public class CmnCodeVO implements Serializable {
	private String codeDetailNo;//=memType
	private String codeGroupNo;
	private String upperCodeNo;
	private String codeName;
	private Integer sortOrder;
	private String useYn;
	private String crateDate;
	private String updateDate;
}
