package kr.or.ddit.company.recruitment.mailtemplate.service;

import java.util.List;

import kr.or.ddit.vo.recruitment.ComMailTemVO;

public interface ComMailTemService {
	public List<ComMailTemVO> readComMailTemList();
	public ComMailTemVO readComMailTem(ComMailTemVO comMailTem);
	public int createComMailTem(ComMailTemVO comMailTem);
	public int modifyComMailTem(ComMailTemVO comMailTem);
	public int removeComMailTem(String comMailTem);

}
