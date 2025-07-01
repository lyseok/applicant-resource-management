package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "userId")
public class MemberVO implements Serializable {
	private String userId;
	private String memName;
	private String memEmail;
	private String memBir;
	private String memTel;
	private String memAdd1;
	private String memAdd2;
	private String memImg;
}
