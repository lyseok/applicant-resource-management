package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "companyId")
public class ScrabUserVO implements Serializable{
	private String companyId;
	private String userId;
	private String scrabUserDate;
}
