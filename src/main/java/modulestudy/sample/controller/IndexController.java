package modulestudy.sample.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import modulestudy.sample.model.User;
import modulestudy.sample.repository.UserRepository;

@RequiredArgsConstructor
@Controller //return view
public class IndexController {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;

	@GetMapping({"", "/"})
	public String index() {
		return "index";
	}

	@GetMapping("/loginForm") // {url : /login} security가 해당 주소를 낚아챔 -> SecurityConfig 파일 생성 후 작동안함.
	public String login() {
		return "loginForm";
	}

	@GetMapping("/joinForm")
	public String join() {
		return "joinForm";
	}

	@PostMapping("/join")
	public String joinProc(User user) {
		System.out.println(" ::: param user : " + user.toString() + " ::: ");
		user.setRole("ROLE_USER");
		//패스워드가 암호화 되지 않으면 security로그인이 불건웅
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);

		userRepository.save(user);
		return "redirect:/loginForm";
	}

	@GetMapping("/user")
	public String user() {
		return "user";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}

	@GetMapping("/manager")
	public String manager() {
		return "manager";
	}
}
