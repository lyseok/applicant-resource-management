package kr.or.ddit.mapper.common;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.common.EmailAuthVO;

@Mapper
public interface EmailAuthMapper {
	int upsertAuthCode(EmailAuthVO auth);
	int verifyAuthCode(EmailAuthVO auth);
}
