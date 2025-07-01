package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="codeGroupNo")
public class CmnCodeGroupVO implements Serializable {
	private String codeGroupNo;
	private String codeGroupName;
	private String description;
	private String useYn;
	private String crateDate;
	private String updateDate;
}
