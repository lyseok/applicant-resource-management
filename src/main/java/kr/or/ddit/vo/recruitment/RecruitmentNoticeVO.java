package kr.or.ddit.vo.recruitment;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "recruitmentNo")
public class RecruitmentNoticeVO implements Serializable{

	@NotBlank(groups = UpdateGroup.class)
	private String recruitmentNo;
	@NotBlank
	private String userId;
	@NotBlank
	private String recruitmentTitle;
	@NotBlank
	private String jobCode;
	private String jobCodeName;
	@NotBlank
	private String yearCode;
	private String yearCodeName;
	@NotBlank
	private String recContent;
	private String preferential;
	@NotBlank
	private String cityCode;
	private String cityCodeName;
	@NotBlank
	private String districtCode;
	private String districtCodeName;
	private String recPositionNumber;
	@NotBlank
	private String recruitmentChargerTel;
	@NotBlank
	private String recruitmentSalary;
	private String welfare;
	@NotBlank
	private String recruitmentDesk;
	private String recruitmentImg;
	@NotBlank
	private String recruitmentStartdate;
	@NotBlank
	private String recruitmentReceiptStart;
	@NotBlank
	private String recruitmentFinishDate;
	private String recruitmentDelDate;
	
	private List<RecruitmentPositionVO> positionList;
	RecruitmentEducationVO education;
	private List<RecruitmentSkillVO> skillList;
}
