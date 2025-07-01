package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "userId")
public class UsersVO implements Serializable{
	private String userId;
	private String userPassword;
	private String userAuthority;
	private String userWithdrawDate;
	private String userStatus;
	private String userEnabled;
}
