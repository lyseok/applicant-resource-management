package kr.or.ddit.company.recruitment.process.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ProcessWrapper {
	private String recruitmentNoticeNo;
	
	@NotEmpty
	@Valid
	private List<ProcessEntry> entries;
}
