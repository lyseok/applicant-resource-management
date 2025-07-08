package kr.or.ddit.vo.recruitment;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "recruitmentNo")
public class RecruitmentNoticeVO implements Serializable{

	private String recruitmentNo;
	private String userId;
	private String recruitmentTitle;
	private String jobCode;
	private String yearCode;
	private String recContent;
	private String preferential;
	private String cityCode;
	private String districtCode;
	private String recPositionNumber;
	private String recruitmentChargerTel;
	private String recruitmentSalary;
	private String welfare;
	private String recruitmentDesk;
	private String recruitmentImg;
	private String recruitmentStartdate;
	private String recruitmentReceiptStart;
	private String recruitmentFinishDate;
	private String recruitmentDelDate;
	
	private List<RecruitmentPositionVO> positionList;
	private List<RecruitmentEducationVO> educationList;
	private List<RecruitmentSkillVO> skillList;
}
