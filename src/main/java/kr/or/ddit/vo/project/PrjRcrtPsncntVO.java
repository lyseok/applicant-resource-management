package kr.or.ddit.vo.project;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="rcrtPsncntNo")
public class PrjRcrtPsncntVO implements Serializable {
	private String rcrtPsncntNo;
	private String prjAnncNo;
	private String jobCode;
	private Integer rcrtPsncnt;
}
