package ie.director;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import ie.director.entities.Director;
import ie.director.entities.Film;

@Configuration
public class RestRepoConfig implements RepositoryRestConfigurer {
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config)
	{
		config.exposeIdsFor(Director.class);
		config.exposeIdsFor(Film.class);
	}

}
