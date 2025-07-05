package kr.or.ddit.vo.resume;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "resumeNo")
public class ResumeVO implements Serializable{

	private String resumeNo;
	private String userId;
	private String userName;
	private String photo;
	private String birth;
	private String email;
	private String tel;
	private String address;
	private String veteranReason;
	private String updateDate;
	private String resumeSubmitYn;
	private String resumeDeleteDate;

}
