package ie.director.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ie.director.entities.Director;
import ie.director.entities.Film;

public interface FilmDao extends JpaRepository<Film,Integer> {
	// Get all the films from the film side of the relationship
		List<Film> findAllByFilmDirector_DirectorId(int directorId);
		
		boolean existsByFilmNameAndFilmDirector(String filmName, Director director);
		
}
