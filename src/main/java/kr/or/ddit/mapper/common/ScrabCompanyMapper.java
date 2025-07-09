package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.ScrabCompanyVO;

@Mapper
public interface ScrabCompanyMapper {

	public List<ScrabCompanyVO> selectScrabCompanyList();

	public ScrabCompanyVO selectScrabCompanyByPk(ScrabCompanyVO vo);

	public int insertScrabCompany(ScrabCompanyVO vo);

	public int updateScrabCompany(ScrabCompanyVO vo);

	public int deleteScrabCompany(ScrabCompanyVO vo);

}
