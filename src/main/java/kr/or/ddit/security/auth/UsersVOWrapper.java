package kr.or.ddit.security.auth;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import kr.or.ddit.vo.common.UsersVO;

public class UsersVOWrapper extends User implements RealUserWrapper<UsersVO> {

	private final UsersVO realUser;
	
	public UsersVOWrapper(UsersVO realUser) {
		super(
			realUser.getUserId()
			, realUser.getUserPassword()
			, ! realUser.isUserStatus()
			, true
			, true
			, true
			, AuthorityUtils.createAuthorityList(realUser.getUserRole())
		);
		this.realUser = realUser;
	}

	@Override
	public UsersVO getRealUser() {
		return realUser;
	}

}
