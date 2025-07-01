package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "userId")
public class SocialMemberVO implements Serializable{
	private String userId;
	private String socialMemId;
	private String socialId;
}
