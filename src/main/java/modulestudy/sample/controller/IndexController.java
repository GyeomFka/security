package modulestudy.sample.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		String encPassword = encoder.encode(user.getPassword());
		user.setPassword(encPassword);

		userRepository.save(user);
		return "redirect:/loginForm";
	}

	@GetMapping("/user")
	public @ResponseBody String user() {
		return "user";
	}

	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin";
	}

	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "manager";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/info")
	public @ResponseBody String info() {
		return "개인정보";
	}

	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") //data method 실행 직전에 실행이 된다
	@GetMapping("/data")
	public @ResponseBody String data() {
		return "데이터";
	}
}
