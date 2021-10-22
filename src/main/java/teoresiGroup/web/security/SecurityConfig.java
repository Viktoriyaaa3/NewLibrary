package teoresiGroup.web.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired 
	private DataSource dataSource;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	};
	//@SuppressWarnings("deprecation")
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	//	UserBuilder users=User.withDefaultPasswordEncoder();
		
		/*auth.inMemoryAuthentication().withUser(users.username("vik")
				.password("vik").roles("EMPLOYEE"))
		.withUser(users.username("john")
				.password("test123").roles("EMPLOYEE"))
		.withUser(users.username("admin")
				.password("admin").roles("ADMIN"));*/
		
		
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder());
	
		
		
	}
	/*@Bean
	protected UserDetailsService userDetailsService() {
		
		UserDetails admin= User.withDefaultPasswordEncoder().username("vik")
				.password("vik").roles("ADMIN").build();
		UserDetails user= User.withDefaultPasswordEncoder().username("user")
				.password("user").roles("USER").build();
		UserDetails operatore= User.withDefaultPasswordEncoder().username("oper")
				.password("oper").roles("OPERATORE").build();
		return new InMemoryUserDetailsManager(admin, user, operatore);
		
	}*/

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorize->{
		authorize.antMatchers("/","/login/**").permitAll();})
.authorizeRequests().anyRequest().authenticated()
.and()
.formLogin()
.and()
.httpBasic()
.and()
.logout()
.logoutUrl("/perform_logout")
.deleteCookies("JSESSIONID");
//.logoutSuccessHandler(logoutSuccessHandler());
		
		//super.configure(http);
	}

	
	
}
