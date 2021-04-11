package ie.director;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import ie.director.dao.DirectorDao;
import ie.director.entities.Director;

@SpringBootTest(classes = DirectorApplication.class)
@ActiveProfiles("test")
public class DAOTests {
	
	@Autowired 
	DirectorDao directorDao;
	
	@Test
	public void testSave()
	{
		Director d = new Director("John");
		directorDao.save(d);
		assertEquals(1, directorDao.count());
	}

}
