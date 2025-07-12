package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of={"userId", "companyId"})
public class ScrabCompanyVO implements Serializable{
	private String userId;
	private String companyId;
	private String scrabCompanyDate;
	
	private transient UsersVO users;
	private transient CompanyVO company;
}
