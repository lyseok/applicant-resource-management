package kr.or.ddit.vo.common;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true) 
public class AdminVO extends UsersVO implements Serializable{
	@NotBlank
	private String userId;
	private String isAdmin;
}
