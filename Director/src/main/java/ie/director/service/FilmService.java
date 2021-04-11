package ie.director.service;

import java.util.List;
import ie.director.entities.Director;
import ie.director.entities.Film;

public interface FilmService {
	
	List<Film> getAllFilms();
	List<Film> getAllFilmsForADirector(int directorId);
	Film save(String filmName, Director filmDirector);

}
