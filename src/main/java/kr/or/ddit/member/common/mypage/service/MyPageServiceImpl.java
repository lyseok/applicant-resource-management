package kr.or.ddit.member.common.mypage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.common.MemberMapper;
import kr.or.ddit.mapper.project.ProjectMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {
	
	private final MemberMapper memberMapper;
	private final ProjectMapper prjMapper;

	@Override
	public Map<String, Object> readMyPageInfo() {
		// 1. 사용자 기본 정보 (없으면 빈 Map)
        Map<String, Object> userInfo = Optional.ofNullable(memberMapper.selectMyPageInfo(getUserId()))
                .orElseGet(HashMap::new);

        // 2. 프로젝트 정보 (없으면 기본값)
        Map<String, Object> projectInfo = Optional.ofNullable(prjMapper.selectPrjectData(getUserId()))
                .orElseGet(() -> Map.of(
                        "total", 0,
                        "ongoing", 0,
                        "completed", 0,
                        "myPostings", 0
                ));

        // 4. 통합 결과
        Map<String, Object> result = new HashMap<>();
        result.put("user", userInfo);
        result.put("projects", projectInfo);

        return result;
	}

	@Override
	public Map<String, Object> selectPrjectData() {
		Map<String, Object> map = new HashMap<>();
		map = prjMapper.selectPrjectData(getUserId());
		return map;
	}
	
	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

}
