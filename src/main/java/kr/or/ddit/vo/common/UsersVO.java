package kr.or.ddit.vo.common;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "userId")
public class UsersVO implements Serializable{
	@NotBlank
	@Size(min = 4, max = 20, message = "아이디는 4~20자여야 합니다.")
	@Pattern(regexp = "^[a-zA-Z0-9._-]{4,20}$", message = "아이디는 영문, 숫자, '.', '_', '-'만 사용할 수 있습니다.")
	private String userId;
	@NotBlank
	@Size(min = 8, max = 16, message="")
	@Pattern(
			 regexp = "^[a-zA-Z0-9~!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]{8,16}$",
			 message=""
	)
	private String userPassword;
	private String userRole;
	private String userWithdrawDate;
	private boolean userStatus;
	private String userEnabled;
	
}
