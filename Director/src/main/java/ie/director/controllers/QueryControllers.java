package ie.director.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ie.director.entities.Director;
import ie.director.service.DirectorService;
import ie.director.service.FilmService;

@Controller
public class QueryControllers {
	
	@Autowired
	DirectorService directorService;
	
	@Autowired
	FilmService filmService;
	
	@GetMapping("director/{directorId}")
	public String getDirectorByDirectorId(@PathVariable("directorId") int directorId, Model model)
	{
		Director director = directorService.findDirector(directorId);
		if(director == null) {
			model.addAttribute("directorId", directorId);
			return "notfounderror";
		}
		model.addAttribute("director",director);
		return "director";
	}
	
	@GetMapping("filmsbydirector//{directorId}")
	public String showFilmsByDirecor(@PathVariable(name="directorId") int directorId, Model model)
	{
		Director director = directorService.getDirectorAndFilmsByDirectorId(directorId);
		if (director == null) {
			model.addAttribute("directorId", directorId);
			return "notfounderror";
		}
		model.addAttribute("director",director);
		return "filmsbydirector";
	}
	
	@GetMapping("/directors")
	public String showDirectors(Model model)
	{
		List<Director> directors = directorService.getAllDirectors();
		model.addAttribute("directors", directors);
		return "directors";
	}
	
	@GetMapping("/director/delete/{directorId}")
	public String deleteDirector(@PathVariable(name="directorId") int directorId, Model model)
	{
		if (!directorService.existsById(directorId))
		{
			model.addAttribute("directorId", directorId);
			return "notfounderror";
		}
		directorService.deleteDirector(directorId);	
		return "redirect:/directors";
	}
	
	@DeleteMapping("/director/{directorId}")
	public String deleteMappingDirector(@PathVariable(name="directorId") int directorId, Model model)
	{
		if (!directorService.existsById(directorId))
		{
			model.addAttribute("directorId", directorId);
			return "notfounderror";
		}
		directorService.deleteDirector(directorId);	
		return "redirect:/directors";
	}
	
}
