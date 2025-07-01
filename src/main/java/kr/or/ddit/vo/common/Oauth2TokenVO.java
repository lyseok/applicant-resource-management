package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "socialMemId")
public class Oauth2TokenVO implements Serializable {
	private String socialMemId;
	private String socialMemUser;
	private String socialMemTokenType;
	private String socialMemToken;
	private String tokenCreate;
	private String tokenFinish;
	private String tokenRefresh;
	private String tokenRefreshTime;
}
