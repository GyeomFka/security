package modulestudy.sample.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import modulestudy.sample.model.User;

// security -> /login url 을 받아 로그인을 진행
// 로그인 진행이 완료 후 security session을 만들어준다.(Security ContextHolder)
// Object -> Authentication 객체만 건웅
// Authentication 내부 User 정보가 있어야 함
// User타입 -> UserDetail 타입의 객체
// Security Session -> Authentication -> UserDetails(this 객체 PrincipalDetails)
public class PrincipalDetails implements UserDetails {

	private User user;

	public PrincipalDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //해당 User의 권한 return
		Collection<GrantedAuthority> collect = new ArrayList<>();

		collect.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
