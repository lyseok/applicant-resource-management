package kr.or.ddit.vo.recruitment;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.common.CompanyVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "recruitViewNo")
public class RecruitViewVO implements Serializable{
	@NotBlank(groups = UpdateGroup.class)
	private String recruitViewNo;
	@NotBlank(message="회원 id는 필수입력값입니다.")
	private String userId;
	@NotBlank(message="채용공고 번호는 필수입력값입니다.")
	private String recruitmentNo;
	@NotBlank(message="조회시간은 필수입력값입니다.")
	private String viewAt;
}
