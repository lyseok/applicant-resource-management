package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="tagNo")
public class TagVO implements Serializable{
	private String tagNo;
	private String tagName;
}
