package teoresiGroup.web.security;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import javax.sql.DataSource;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	private static final Logger log= Logger.getLogger(SecurityConfig.class.getName());
	@Autowired 
	private DataSource dataSource;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	};


	/*@Override
	public void configure(final AuthenticationManagerBuilder auth) throws Exception
	{
		auth
			.userDetailsService(userDetailsService())
			.passwordEncoder(passwordEncoder());
	}*/
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	 auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
     .dataSource(dataSource)
     .usersByUsernameQuery("SELECT username, password, enabled FROM Utenti WHERE username=?")
     .authoritiesByUsernameQuery("SELECT u.username, ur.roles FROM Utenti u INNER JOIN user_role ur on u.id = ur.user_id WHERE u.username=?")
 ;

		log.info("VEDO SE SONO ENTRATA NEL METODO JDCB PER L'AUTENTICAZIONE");
	}
	
	@Bean UserDetailsService userDettaglio(DataSource dataSource) {
		log.info("sono in usrDetailService "+dataSource);
		return new JdbcUserDetailsManager(dataSource);
	}
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorize->{
		authorize.antMatchers("/","/login/**","/cliente/**").permitAll();});
//.authorizeRequests().anyRequest().authenticated()
//.and()
//.formLogin().loginPage("/login/controllo").loginProcessingUrl("/doLogin").permitAll()
//.and()

//.httpBasic()
//.and()
//.logout()
//.logoutUrl("/perform_logout")
//.deleteCookies("JSESSIONID")
//.and()
//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

	}

	
	
}
