package ie.director.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.director.entities.Director;
import ie.director.forms.NewDirectorForm;
import ie.director.service.DirectorService;

@Controller
public class NewDirectorControllers {
	
	@Autowired
	DirectorService directorService;
	
	@GetMapping("/newdirector")
	public String addADirector(Model model)
	{
		model.addAttribute("newDirectorForm", new NewDirectorForm());
		return "newDirector";
	}
	
	@PostMapping("/newdirector")
	public String addADirectorPost(@Valid NewDirectorForm newDirectorForm, BindingResult bindingResult, RedirectAttributes redirectAttributes)
	{
		if (bindingResult.hasErrors())
			return "newdirector";
		Director director = directorService.save(newDirectorForm.getNewDirectorName());
		if(director == null)
		{
			redirectAttributes.addFlashAttribute("duplicateDirectorName", newDirectorForm.getNewDirectorName());
			return "redirect:newdirector";
		}
		return "redirect:director/" + director.getDirectorId();
	}

}
