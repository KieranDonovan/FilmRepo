package ie.director.service;

import java.util.List;
import ie.director.entities.Director;

public interface DirectorService {
	Director save(String directorName);
	List<Director> getAllDirectors();
	List<Director> getAllDirectorsAndTheirFilms();
	List<Director> getAllDirectorsAlphabetically();
	void deleteDirector(int directorId);
	long numberOfDirectors();
	Director findDirector(int directorId);
	boolean changeDirectorName(String newName, int directorId);
	List<Director> getAllDirectorsWithLetters(String letters);
	String getDirectorNameWithId(int id);
	Director getDirectorById(int id);
	boolean exists(String directorName);
	boolean existsById(int directorId);
	Director getDirectorAndFilmsByDirectorId(int directorId);

}
