package kr.or.ddit.vo.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true) 
public class AdminVO extends UsersVO implements Serializable{
	private String isAdmin;
}
