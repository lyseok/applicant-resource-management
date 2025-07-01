package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="fileNo")
public class FileVO implements Serializable{
	private String fileNo;
	private String realFile;
	private String fileName;
	private Integer fileSize;
	private String fileType;
}
