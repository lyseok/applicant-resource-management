package kr.or.ddit.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.common.ScrabUserVO;

@Mapper
public interface ScrabUserMapper {
	
	public List<ScrabUserVO> selectScrabUserList();

	public ScrabUserVO selectScrabUserByPk(ScrabUserVO vo);
	
	public int insertScrabUser(ScrabUserVO vo);
	
	public int updateScrabUser(ScrabUserVO vo);
	
	public int deleteScrabUser(ScrabUserVO vo);
	
    // 관심 인재 리스트 조회
    public List<String> selectSavedTalentList(@Param("companyId") String companyId);

    // 관심 인재 추가
    public void insertTalentUsers(@Param("companyId") String companyId,
                           @Param("userList") List<String> userList);

    // 관심 인재 삭제
    public void deleteTalentUsers(@Param("companyId") String companyId,
                           @Param("userList") List<String> userList);
}
