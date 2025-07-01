package kr.or.ddit.vo.community;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of={"commuPostNo", "avatarId"})
public class InBoardVO implements Serializable {
	private String commuPostNo;
	private String avatarId;
}
