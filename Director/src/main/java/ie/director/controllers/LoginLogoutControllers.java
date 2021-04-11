package ie.director.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class LoginLogoutControllers {
	
	@GetMapping("/login")
	 public String login() {
	  return "login";
	 }
	 
	 @GetMapping("/403")
	 public String notAuthorised()
	 {
	  return "403";
	 }
	 
	 @GetMapping("/logout")
	 public String logout() {
	  return "logout";
	 }

}
