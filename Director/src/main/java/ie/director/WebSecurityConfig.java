package ie.director;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	 protected void configure(HttpSecurity http) throws Exception {
	  http.authorizeRequests()
	   .antMatchers("/css/**", "/", "/directors", "/filmsbydirector/**").permitAll()
	   .antMatchers("/newdirector").hasRole("ADMIN")
	   .antMatchers("/newdirector").hasAnyRole("ADMIN")
	   .anyRequest().authenticated()
	   .and()
	   .formLogin().and().httpBasic();
	 }
	 
	 //had problems with personal login page so stopped using it

 @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // Create our own instance of Spring's user details but creating some UserDetails objects and 
    // creating an in-memory user details manager from the. 
    @Bean
    @Override
    protected UserDetailsService userDetailsService() 
    {
        String encodedPassword = passwordEncoder().encode("password");  
        UserDetails user1 = User.withUsername("user").password(encodedPassword).roles("USER").build();
        UserDetails user2 = User.withUsername("admin").password(encodedPassword).roles("ADMIN").build();       
        return new InMemoryUserDetailsManager(user1, user2);
    }
}


