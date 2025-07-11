package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.ScrabCompanyVO;

@Mapper
public interface ScrabCompanyMapper {

	public ScrabCompanyVO selectScrabCompanyByPk(String companyId);  //해당 유저는 로그인된 principal로, 해당 기업은 companyId 검색으로
	public List<ScrabCompanyVO> selectScrabCompanyList();  //해당 유저의 관심기업 목록 전체
	public int insertScrabCompany(ScrabCompanyVO vo);
	public int updateScrabCompany(ScrabCompanyVO vo);
	public int deleteScrabCompany(ScrabCompanyVO vo);

}
