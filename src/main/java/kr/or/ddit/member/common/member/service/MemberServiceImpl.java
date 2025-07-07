package kr.or.ddit.member.common.member.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.mapper.common.MemberMapper;
import kr.or.ddit.mapper.common.UserMapper;
import kr.or.ddit.member.common.exception.PKDuplicatedException;
import kr.or.ddit.vo.common.MemberVO;
import kr.or.ddit.vo.common.UsersVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
	
	private final UserMapper userMapper;
	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	private File imagesFolder;
	
	private void processImage(MemberVO member){
		MultipartFile memberImage = member.getMemberImage();
		if(memberImage!=null && !memberImage.isEmpty()) {
			// 메타데이터(저장명) 분리
			String saveName = UUID.randomUUID().toString();
			member.setMemImg(saveName);
			// binary 데이터 저장,  저장위치 :  FileInfo.properties, FileInfoContextConfig
			File saveFile = new File(imagesFolder, saveName);
			try {
				memberImage.transferTo(saveFile);
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	

	@Override
	@Transactional
	public void registerMember(MemberVO member) {
		log.info("{}", member);
		String encoded = passwordEncoder.encode(member.getUserPassword());
		member.setUserPassword(encoded);
		
		userMapper.insertUser(member);
		log.info("바뀐 후 {}", member);
		memberMapper.insertMember(member);
	}


	@Override
	public int idDuplicateCheck(String userId) {
		return userMapper.existsById(userId);
	}


}
