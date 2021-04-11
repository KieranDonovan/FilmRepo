package ie.director;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ie.director.service.DirectorService;

@SpringBootTest(classes = DirectorApplication.class)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServiceTests {
	
	@Autowired
	DirectorService directorService;
	
	@Test
	@Order(1)
	public void testSave()
	{
		directorService.save("DirectorTest");
		assertEquals(1, directorService.numberOfDirectors());
	}
	
	@Test
	@Order(2)
	public void testSaveDuplicate()
	{
		directorService.save("DirectorTest");
		assertEquals(1, directorService.numberOfDirectors());
	}
	

	
}

