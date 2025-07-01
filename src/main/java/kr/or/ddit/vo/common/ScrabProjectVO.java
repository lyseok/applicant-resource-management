package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "userId")
public class ScrabProjectVO implements Serializable{
	private String companyId;
	private String projectNo;
	private String scrabProjectDate;
}
