package ie.director;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import ie.director.dao.DirectorDao;
import ie.director.entities.Director;
import ie.director.entities.Film;
import ie.director.service.DirectorService;
import ie.director.service.FilmService;

@Profile("mysql")
@Component
public class DirectorApplicationMySQL implements CommandLineRunner {
	
	@Autowired
	DirectorService directorService;	
	@Autowired
	FilmService filmService;

	@Override
	public void run(String... args) throws Exception {
		// Saving Directors
		Director director1 = directorService.save("Director1");
		Director director2 =directorService.save("Director2");
		Director director3 =directorService.save("Director3");
		Director director4 =directorService.save("Director4");
		
		// Saving Films to Directors
		filmService.save("Film1", director1 );
		filmService.save("Film2",director1 );
		filmService.save("Film3",director1 );
		
		filmService.save("Film4", director2);
		filmService.save("Film5", director2);
		filmService.save("Film6", director2);
		
		filmService.save("Film7", director3);
		filmService.save("Film8", director3);
		filmService.save("Film9", director3);
		
		filmService.save("Film10", director4);
		filmService.save("Film11", director4);
		filmService.save("Film12", director4);
		
		System.out.println("\n===========================================================\n");
		System.out.println("There are " + directorService.numberOfDirectors() + " directors.");
		
		// Using the method which Spring creates based upon the signature in the interface. 
		System.out.println("\n===========================================================\nThe directors are:");
		List<Director> directors = directorService.getAllDirectorsAlphabetically();
		for(Director director: directors)
			System.out.println(director.getDirectorName());
		
		System.out.println("\n===========================================================");
		Director director = directorService.getDirectorById(3);	
		System.out.println("The director with ID = 1 is " + director.getDirectorName());
		
		System.out.println("The name of the director with ID = 2 is " + directorService.getDirectorNameWithId(2));
	
		System.out.println("\n===========================================================");
		List<Film> films = filmService.getAllFilms();
		for(Film t: films)
			System.out.println(t.getFilmName() + " in " + t.getFilmDirector().getDirectorName());

		int directorId = 3;
		director = directorService.getDirectorById(directorId);
		System.out.println("\n===========================================");
		System.out.println("Films in " + director.getDirectorName());
		System.out.println("===========================================");
		
		
		List<Film> filmsInCork = filmService.getAllFilmsForADirector(directorId);
		for(Film t: filmsInCork)
			System.out.println(t.getFilmName());
		
		
		director = directorService.getDirectorAndFilmsByDirectorId(directorId);
		
		System.out.println("\n===========================================");
		System.out.println("Films in " + director.getDirectorName());
		System.out.println("===========================================");
		
		films = director.getDirectorFilms();
		
		for(Film t: films)
			System.out.println(t.getFilmName());	
		
		System.out.println("\n===========================================");
		
		List<Director> allDirectorsAndFilms = directorService.getAllDirectorsAndTheirFilms();
		for(Director c: allDirectorsAndFilms)
		{
			System.out.println(c.getDirectorName());
			films = c.getDirectorFilms();
			for(Film t: films)
				System.out.println("\t" + t.getFilmName());	
		}
		
		System.out.println("\n\nDeleting Director3 (id = 3) and all their films, remaning films and directors:");
		
		directorService.deleteDirector(3);
		
		films = filmService.getAllFilms();
		for(Film t: films)
				System.out.println(">>> " + t.getFilmName() + " by " + t.getFilmDirector().getDirectorName());	
		
	}

}
