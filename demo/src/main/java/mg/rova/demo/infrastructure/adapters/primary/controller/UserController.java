package mg.rova.demo.infrastructure.adapters.primary.controller;

import mg.rova.demo.domain.ports.primary.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mg.rova.demo.domain.entities.Role;
import mg.rova.demo.domain.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder encoder;

	@GetMapping("/signup")
	public ModelAndView signup() {
		final ModelAndView modelAndView = new ModelAndView("signup");
		modelAndView.addObject("user", new User());
		modelAndView.addObject("roles", Role.values());
		return modelAndView;
	}

	@PostMapping("/create")
	public String createUser(@ModelAttribute("user") User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userService.create(user);
		return "redirect:/";
	}

}
