package kr.or.ddit.vo.community;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of={"commentNo", "avatarId"})
public class InCommentVO implements Serializable {
	private String commentNo;
	private String avatarId;
}
