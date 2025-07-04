package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.Oauth2TokenVO;

@Mapper
public interface Oauth2TokenMapper {
	public Oauth2TokenVO selectOauth2TokenById(String userId);
	public List<Oauth2TokenVO> selectOauth2TokenList();
	public int insertOauth2Token(Oauth2TokenVO oauth2Token);
	public int updateOauth2Token(Oauth2TokenVO oauth2Token);
	public int deleteOauth2Token(String userId);
}
