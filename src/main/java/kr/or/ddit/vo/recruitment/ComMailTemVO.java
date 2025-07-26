package kr.or.ddit.vo.recruitment;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"temNo", "userId"})
public class ComMailTemVO implements Serializable{
	private String temNo;
	private String userId;
	private String ComName;
	private String ComMail;
	
	private String temTitle;
	private String temContent;
	private String createDate;
	private String deleteDate;
}
