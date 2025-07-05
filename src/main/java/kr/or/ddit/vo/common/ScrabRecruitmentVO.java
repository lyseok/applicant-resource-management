package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"userId", "recuitmentNo"})
public class ScrabRecruitmentVO implements Serializable{
	private String userId;
	private String recruitmentNo;
	private String scrabRecruitmentDate;
}
