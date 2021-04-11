package ie.director.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexControllers {

	@GetMapping("/")
	//localhost:8080?myname=Kieran&myemail=kieran@cit.ie
	public String loadIndexGet(
			@RequestParam(name = "myname", required = false, defaultValue = "") String name,
			@RequestParam(name = "myemail", required = false, defaultValue = "") String email, 
			Model model)
	{
		model.addAttribute("name", name);
		model.addAttribute("email", email);
		return "index";
	}
	
	
	@GetMapping("/details/{myname}/{myemail}")
	//localhost:8080/Kieran/kieran@mycit.ie
	public String loadIndexGetPath(
			@PathVariable("myname") String name, 
			@PathVariable("myemail") String email, 
			Model model)
	{
		model.addAttribute("name", name);
		model.addAttribute("email", email);
		return "index";
	}
	
	@PostMapping("/")
	public String loadIndexPost()
	{
		return "index";
	}
}
