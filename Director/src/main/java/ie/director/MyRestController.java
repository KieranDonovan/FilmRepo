package ie.director;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ie.director.entities.Director;
import ie.director.entities.Film;
import ie.director.service.DirectorService;
import ie.director.service.FilmService;

@RestController // returns the data as Json objects
@RequestMapping("myapi") // all requests will include myapi 
public class MyRestController {

 @Autowired
 DirectorService directorService;
 
 @Autowired
 FilmService filmService;
 

 // Returns a json list of counties
 @GetMapping("/counties")
 List<Director> getDirectors()
 {
  return directorService.getAllDirectors();
 }
 
 // returns a json list of films
 @GetMapping("/films")
 List<Film> getFilms()
 {
  return filmService.getAllFilms();
 }
 
 // return a director or 404
 @GetMapping("/director/{directorId}")
 ResponseEntity<Director> getDirector(@PathVariable(name="directorId") int directorId)
 {
  Director director = directorService.findDirector(directorId);
  if (director == null)
   return new ResponseEntity<Director>(HttpStatus.NOT_FOUND);
  return new ResponseEntity<Director>(director, HttpStatus.FOUND);
 }

 // a delete request
 //@DeleteMapping("/director/{directorId}")
 //ResponseEntity<Director> deleteDirector(@PathVariable(name="directorId") int directorId)
 //{  
  //boolean status = directorService.deleteDirector(directorId);
  //if (status)
   //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  //return new ResponseEntity<>(HttpStatus.NOT_FOUND);  
 //}
} 
 
