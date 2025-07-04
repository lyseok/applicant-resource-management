package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "authNo")
public class UserAuthorityVO implements Serializable{
	private String authNo;
	private String userId;
	private String userRole;
}
