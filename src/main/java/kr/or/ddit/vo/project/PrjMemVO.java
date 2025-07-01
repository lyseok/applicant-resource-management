package kr.or.ddit.vo.project;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="prjNo")
public class PrjMemVO implements Serializable {
	private String prjNo;
	private String userId;
	private String authorityCode;
	private String deleteDate;
}
