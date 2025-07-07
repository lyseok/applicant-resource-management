package kr.or.ddit.vo.common;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "email")
public class EmailAuthVO implements Serializable{
	private String email;
	private String authCode;
	private LocalDateTime expireTime;
}
