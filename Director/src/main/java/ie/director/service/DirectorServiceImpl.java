package ie.director.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ie.director.dao.DirectorDao;
import ie.director.entities.Director;
import ie.director.entities.Film;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DirectorServiceImpl implements DirectorService {
	
	@Autowired
	DirectorDao directorDao;
	
	@Override
	public Director save(String directorName) {
		if (directorDao.existsByDirectorName(directorName))
		{
			log.error("Attempt to add a director which already exists in the database");
			return null;
		}
		return directorDao.save(new Director(directorName));
	}

	@Override
    public Director findDirector(int directorId) {
		Optional<Director> optional = directorDao.findById(directorId);
			
		if (optional.isPresent())
			return optional.get(); // note the use of .get()
		
		return null;
	}
	
	@Override
	public boolean changeDirectorName(String newName, int directorId) {
		Optional<Director> optional = directorDao.findById(directorId);
		if (directorDao.findByDirectorName(newName) == null && optional.isPresent())
		{
			directorDao.changeDirectorName(newName, directorId);
			return true;
		}
		return false;
	}

	
	@Override
	public List<Director> getAllDirectors() {
		return directorDao.findAll();
	}

	@Override
	public long numberOfDirectors() {
		return directorDao.count();
	}

	

	@Override
	public List<Director> getAllDirectorsAlphabetically() {
		return directorDao.findAllByOrderByDirectorNameAsc();
	}

	
	@Override
	public List<Director> getAllDirectorsWithLetters(String letters) {
		return directorDao.findByDirectorNameContainsAllIgnoreCase(letters);
	}

	@Override
	public String getDirectorNameWithId(int id) {
		return directorDao.findNameOfDirectorById(id);
	}

	@Override
	public Director getDirectorById(int id) {
		Optional<Director> optional = directorDao.findById(id);
		if (optional.isPresent())
			return optional.get();
		return null;
	}

	@Override
	public boolean exists(String directorName) {
		return directorDao.existsByDirectorName(directorName);
	}
	
	@Override
	public boolean existsById(int directorId) {
		return directorDao.existsById(directorId);
	}

	@Override
	public Director getDirectorAndFilmsByDirectorId(int directorId) {
		return directorDao.findDirectorAndFilmsByDirectorId(directorId);
	}

	@Override
	public List<Director> getAllDirectorsAndTheirFilms() {
		return directorDao.findAllDirectorsAndFilms();
	}

	@Override
	public void deleteDirector(int directorId) {
		directorDao.deleteById(directorId);
	}

}
