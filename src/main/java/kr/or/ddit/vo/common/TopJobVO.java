package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="topJobCode")
public class TopJobVO implements Serializable {
	private String topJobCode;
	private String topJobName;
}
