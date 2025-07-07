package kr.or.ddit.admin.common.email.utils;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailUtil {

	private final JavaMailSender sender;
	
	@Value("${mail.from}")
	private String from;
	
	public static String generateCode() {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<6; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
	
	public void sendEmail(String to, String subject, String text) throws MessagingException {
		MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, false, "UTF-8");
		
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text, false);
		helper.setFrom(from);
		
		sender.send(msg);
	}
}
