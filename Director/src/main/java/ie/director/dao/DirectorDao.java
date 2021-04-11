package ie.director.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ie.director.entities.Director;

public interface DirectorDao extends JpaRepository<Director, Integer> {
	Director findDirectorByDirectorName(String directorName);
	Director findByDirectorName(String directorName);			// same as above
	Director findByDirectorId(int directorId);			
	int findDirectorIdByDirectorName(String directorName);
	boolean existsByDirectorName(String directorName);
	
	List<Director> findAllByOrderByDirectorNameAsc();
	List<Director> findAllByOrderByDirectorNameDesc();
	List<Director> findByDirectorNameContainsAllIgnoreCase(String pattern);
	
	// Serves as an example of JPQL because could be written using names: findDirectorNameByDirectorId()
		// Find name of a particular director by its id
		@Query("SELECT c.directorName FROM Director c where c.directorId = :id") 
		String findNameOfDirectorById(@Param("id") int id);
		
		// Serves as an example of JPQL
		@Query(value = "SELECT * FROM director WHERE director.directorName LIKE :letter%", nativeQuery = true)
		List<Director> findAllStartingWith(@Param("letter") char letter);
		
		
		@Modifying
		@Transactional
		@Query("UPDATE Director c SET c.directorName = :newName WHERE c.directorId = :directorId")
		void changeDirectorName(@Param("newName") String newName, @Param("directorId") int directorId);
		
		// JOIN FETCH is a JPQL operator
		// Find all the director and its films given a directorId 
		@Query("SELECT c FROM Director c LEFT JOIN FETCH c.directorFilms t WHERE c.directorId = :directorId")
		Director findDirectorAndFilmsByDirectorId(int directorId);
		
		// Find all distinct directors and their films. 
		@Query("SELECT DISTINCT c FROM Director c JOIN FETCH c.directorFilms ")
		List<Director> findAllDirectorsAndFilms();
}
