package com.ta.toko.module.login.web;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ta.toko.entity.Role;
import com.ta.toko.entity.User;
import com.ta.toko.module.role.RoleService;
import com.ta.toko.module.user.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	private boolean isloaded = false;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
//		creatUser();
		if (error != null) {
			model.addAttribute("error", "The username and password is incorrect!");
		}

		return "login/login";
	}
	


	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		return "redirect:/login?logout";
	}

	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String accessDenied() {
		return "errors/access_denied";
	}

	private void creatUser() {
		if (!isloaded) {
			createRole();

			User userAdmin = new User();
			userAdmin.setEmail("test@tes.com");
			userAdmin.setFirstName("Admin");
			userAdmin.setLastName("Admin");
			// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			userAdmin.setPassword("admin");
			userAdmin.setUsername("admin");
			Role admin = roleService.getAll().get(0);
			Set<Role> roles = new HashSet<>();
			roles.add(admin);
			userAdmin.setUserRoles(roles);

			userService.save(userAdmin);

			User userUser = new User();
			userUser.setEmail("test@tes.com");
			userUser.setFirstName("Admin");
			userUser.setLastName("Admin");
			// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			userUser.setPassword("admin");
			userUser.setUsername("user");
			// Role admin = roleService.getAll().get(0);
			// Set<Role>
			roles = new HashSet<>();
			roles.add(roleService.findByName("ROLE_USER"));
			userUser.setUserRoles(roles);

			userService.save(userUser);
			isloaded = true;
		}
	}

	private void createRole() {
		Role admin = new Role();
		admin.setName("ROLE_ADMIN");

		roleService.save(admin);
		Role user = new Role();
		user.setName("ROLE_USER");

		roleService.save(user);
	}
}
