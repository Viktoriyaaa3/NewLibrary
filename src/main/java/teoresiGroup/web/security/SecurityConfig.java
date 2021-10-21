package teoresiGroup.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorize->{
			authorize.antMatchers("/").permitAll();
		})
		.authorizeRequests().anyRequest().authenticated()
		.and().formLogin().and().httpBasic();
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("ab").password("ab").roles("ADMIN")
		.and()
		.withUser("bc").password("bc").roles("USER");
		
	}

/*
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails a= User.withDefaultPasswordEncoder()
				.username("a").password("a").roles("A").build();
		
		UserDetails b= User.withDefaultPasswordEncoder()
				.username("b").password("b").roles("B").build();
		
		
		return new InMemoryUserDetailsManager(a,b);
	}
	*/
	

}
