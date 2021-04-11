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
import ie.director.entities.Film;
import ie.director.forms.NewFilmForm;
import ie.director.service.DirectorService;
import ie.director.service.FilmService;

@Controller
public class NewFilmControllers {
	
	@Autowired
	DirectorService directorService;
	
	@Autowired
	FilmService filmService;
	
	@GetMapping("newfilm")
	public String addNewFilm(Model model)
	{
		model.addAttribute("newFilmForm", new NewFilmForm());
		model.addAttribute("directors", directorService.getAllDirectorsAlphabetically());
		return "newfilm";
	}
	
	@PostMapping("/newfilm")
	public String addNewFilm(Model model, @Valid NewFilmForm newFilmForm, BindingResult binding, RedirectAttributes redirectAttributes)
	{
		if (binding.hasErrors())
		{
			model.addAttribute("directors", directorService.getAllDirectorsAlphabetically());
			return "newfilm";
		}
		Director director = directorService.findDirector(newFilmForm.getDirectorId());
		Film newFilm = filmService.save(newFilmForm.getNewFilmName(), director);
		if(newFilm == null)
		{
			redirectAttributes.addFlashAttribute("duplicateFilm", newFilmForm.getNewFilmName());
			redirectAttributes.addFlashAttribute("duplicateDirector", director.getDirectorName());
			return "redirect:newfilm";
		}
		//return "redirect:film?filmid=" + newFilm.getFilmId();
		//return "redirect:film/" + newFilm.getFilmId();
		return "redirect:filmsbydirector/" + director.getDirectorId();
	}


}
