package kr.or.ddit.vo.project;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="tagNo")
public class PrjAnncBoardTagVO implements Serializable {
	private String tagNo;
	private String prjAnncNo;
	private String tagDate;
}
