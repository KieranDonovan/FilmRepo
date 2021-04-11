package ie.director.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ie.director.dao.FilmDao;
import ie.director.entities.Director;
import ie.director.entities.Film;

@Service
public class FilmServiceImpl implements FilmService {
	
	@Autowired
	FilmDao filmDao;
	
	@Override
	public Film save(String filmName, Director filmDirector) {
		if (filmDao.existsByFilmNameAndFilmDirector(filmName, filmDirector))
		{
			return null;
		}
		Film newFilm = new Film(filmName, filmDirector);
		return filmDao.save(newFilm);	
	}

	@Override
	public List<Film> getAllFilms() {
		return filmDao.findAll();
	}

	@Override
	public List<Film> getAllFilmsForADirector(int directorId) {
		return filmDao.findAllByFilmDirector_DirectorId(directorId);
	}
}
