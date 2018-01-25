package com.ta.toko.module.user.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ta.toko.module.login.CustomUserDetails;
import com.ta.toko.module.user.UserService;
import com.ta.toko.module.user.model.UserProfile;

@Controller
public class UserController {

	@Autowired
	private UserProfileValidator profileValidator;
	@Autowired
	private UserService userService;

	@ModelAttribute("user")
	public CustomUserDetails getUser() {
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return user;
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String showProfile(Model model) {
		model.addAttribute("userProfile", new UserProfile(getUser().getUsername()));
		return "user/profile";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String submitProfile(@Valid @ModelAttribute("userProfile") UserProfile profile, BindingResult result,
			Model model) {
		System.out.println("username: " + profile.getUsername());
		if (profile.getUsername() == null) {
			profile.setUsername(getUser().getUsername());
		}
		System.out.println("username: " + profile.getUsername());
		profileValidator.validate(profile, result);
		if (result.hasErrors()) {
			model.addAttribute("userProfile", profile);
			return "user/profile";
		}

		userService.updateProfile(profile);
		model.addAttribute("updated", true);
		model.addAttribute("userProfile", profile);
		return "user/profile";
	}
}
