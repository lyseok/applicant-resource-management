package kr.or.ddit.mapper.recruitment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.recruitment.ComMailTemVO;

@Mapper
public interface ComMailTemMapper {
	public List<ComMailTemVO> selectComMailTemList(String comId);
	public ComMailTemVO selectComMailTem(ComMailTemVO comMailTem);
	public int insertComMailTem(ComMailTemVO comMailTem);
	public int updateComMailTem(ComMailTemVO comMailTem);
	public int deleteComMailTem(ComMailTemVO comMailTem);
}
