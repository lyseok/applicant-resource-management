package kr.or.ddit.vo.common;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "userId")
public class UsersVO implements Serializable{
	@NotBlank
	private String userId;
	@NotBlank
	private String userPassword;
	private String userRole;
	private String userWithdrawDate;
	private boolean userStatus;
	private String userEnabled;
}
