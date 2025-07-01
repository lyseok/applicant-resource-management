package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="forbiddenWordNo")
public class ForbiddenWordVO implements Serializable {
	private String forbiddenWordNo;
	private String word;
}
