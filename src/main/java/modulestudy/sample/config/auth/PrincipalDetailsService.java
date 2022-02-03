package modulestudy.sample.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

import modulestudy.sample.model.User;
import modulestudy.sample.repository.UserRepository;

//security 설정에서 loginProcessingUrl 을 통해 요청이 들어올 때 자동으로 UserDetails 타입으로  IoC되어있는 loadby~함수 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//username == login page [name=username] section
		System.out.println(" ::: " + username + " ::: ");
		User userEntity = userRepository.findByUsername(username);
		if (userEntity != null) {
			return new PrincipalDetails(userEntity);
		}
		return null;
	}
}
