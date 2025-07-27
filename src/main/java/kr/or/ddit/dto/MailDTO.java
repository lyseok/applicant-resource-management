package kr.or.ddit.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class MailDTO implements Serializable {
	private String job;
	private String template;
	private String userId;
	private String comId;
}
