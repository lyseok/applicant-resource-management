package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "avatarId")
public class AvatarVO implements Serializable{
	private String avatarId;
	private String userId;
	private String topJobCode;
	private String avatarNn;
	private String yearCode;
}
