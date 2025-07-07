package kr.or.ddit.admin.common.email.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import kr.or.ddit.admin.common.email.utils.EmailUtil;
import kr.or.ddit.mapper.common.EmailAuthMapper;
import kr.or.ddit.vo.common.EmailAuthVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailAuthService {
	
	private final EmailUtil emailUtil;
	private final EmailAuthMapper mapper;
	
	public void sendAuthCode(String email) throws MessagingException {
		String code = EmailUtil.generateCode();
		emailUtil.sendEmail(email, "[띹잡] 이메일 인증코드", "인증번호는: " + code);
		
		EmailAuthVO auth = new EmailAuthVO();
		auth.setEmail(email);
		auth.setAuthCode(code);
		auth.setExpireTime(LocalDateTime.now().plusMinutes(3));
		
		mapper.upsertAuthCode(auth);
	}
	
	public boolean verifyCode(String email, String code) {
		EmailAuthVO auth = new EmailAuthVO();
		auth.setEmail(email);
		auth.setAuthCode(code);
		return mapper.verifyAuthCode(auth)>0;
	}

}
