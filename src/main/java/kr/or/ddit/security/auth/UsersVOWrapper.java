package kr.or.ddit.security.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import kr.or.ddit.vo.ExMemberVO;
import kr.or.ddit.vo.common.UsersVO;

public class UsersVOWrapper extends User  implements RealUserWrapper<UsersVO> {

	private final UsersVO realUser;
	public UsersVOWrapper(UsersVO realUser) {
		super(
			realUser.getUserId()
			, realUser.getUserPassword()
			, ! realUser.isUserStatus()
			, true
			, true
			, true
			, AuthorityUtils.createAuthorityList(realUser.getUserAuthority())
		);
		this.realUser = realUser;
	}

	@Override
	public UsersVO getRealUser() {
		return realUser;
	}

}
