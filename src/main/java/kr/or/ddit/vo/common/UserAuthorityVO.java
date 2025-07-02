package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "userId")
public class UserAuthorityVO implements Serializable{
	private String userId;
	private String userAuthority;
}
