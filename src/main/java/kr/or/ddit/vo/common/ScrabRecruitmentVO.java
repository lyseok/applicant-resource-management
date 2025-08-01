package kr.or.ddit.vo.common;

import java.io.Serializable;

import kr.or.ddit.vo.recruitment.RecruitmentNoticeVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"userId", "recruitmentNo"})
public class ScrabRecruitmentVO implements Serializable{
	private String userId;
	private String recruitmentNo;
	private String scrabRecruitmentDate;
	
	private RecruitmentNoticeVO recruitment;
}
