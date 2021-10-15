package teoresiGroup.web.controller;


import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableWebSecurity
public class ConfigSpringSecurity  extends WebSecurityConfigurerAdapter {



    final static Logger logger=Logger.getLogger(ConfigSpringSecurity.class.getName());
/*
    @Bean
    public BCryptPasswordEncoder pass(){
        //per criptare le password
        return new BCryptPasswordEncoder();
    }*/
/*
    @Bean
    public UserDetailsService user(){
        //qua specifico gli utenti che potranno accedere alla app
        UserBuilder users = User.builder();//con user buildere istanzio/creo nuovi utenti
        InMemoryUserDetailsManager manager= new InMemoryUserDetailsManager();
//untente 1
        manager.createUser(
                users.username("vik").password(new BCryptPasswordEncoder().encode("vik"))
                        .roles("USER").build() );
//utente 2
        manager.createUser(
                users.username("prova").password(new BCryptPasswordEncoder().encode("prova"))
                        .roles("USER","OPERATORE").build() );*/
 //utente 3
   /*     manager.createUser(
                users.username("Vik").password(new BCryptPasswordEncoder().encode("Abc1234"))
                        .roles("USER", "OPERATORE", "ADMIN").build() );*/
/*return manager;
    }*/

  /*  @Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() 
	{
	    StrictHttpFirewall firewall = new StrictHttpFirewall();
	    firewall.setAllowUrlEncodedSlash(true);
	    firewall.setAllowSemicolon(true);
	    
	    return firewall;
	}
    
    
    */
    @Autowired
    private DataSource securityDataSource;
    @Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	};
	@Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() 
	{
	    StrictHttpFirewall firewall = new StrictHttpFirewall();
	    firewall.setAllowUrlEncodedSlash(true);
	    firewall.setAllowSemicolon(true);
	    
	    return firewall;
	}
	@Override
	public void configure(WebSecurity web) throws Exception 
	{
	  super.configure(web);
	  
	  web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
	 
	}
	
    
    @Autowired 
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
       // auth.userDetailsService(user()).passwordEncoder(pass());
    
	auth.jdbcAuthentication().dataSource(securityDataSource);
    	
    }

    @Override
   	protected void configure(HttpSecurity http) throws Exception {
   		http
   		.authorizeRequests()//.anyRequest().authenticated()
   		.antMatchers("/").permitAll()
   		.and()
   		//.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
   		.formLogin()
   		.loginPage("/login/log").loginProcessingUrl("/login/controllo/").permitAll();
   		
   	
   		//.and()
   		//.logout()
   		//.permitAll();
   		//.and().authorizeRequests().antMatchers("/operatore/**").hasRole("OPERATORE");
   		//super.configure(http);
   		/*loginPage("/showMyLoginPage).loginProcessingUrl("/authenticateTheUser")*/
   	}
  /*  public AuthenticationFilter authenticationFilter() 
			throws Exception 
	{
		
		 AuthenticationFilter filter = new AuthenticationFilter();
		 
		 filter.setAuthenticationManager(authenticationManagerBean());
		 filter.setAuthenticationFailureHandler(failureHandler());
		 filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
		 filter.setRememberMeServices(customRememberMeServices());
		 
		 
		 return filter;
		 
	}
	public SimpleUrlAuthenticationFailureHandler failureHandler() 
	{ 
        return new SimpleUrlAuthenticationFailureHandler("/login/form?error"); 
    } 
	
	@Bean
	public SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler() 
	{
        SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
		auth.setTargetUrlParameter("targetUrl");
		
		return auth;
	}
	
	@Bean
	public PersistentTokenBasedRememberMeServices customRememberMeServices()
	{
		String Key = "ricordamiKey";
		
		PersistentTokenBasedRememberMeServices rememberMeServices = 
      			new CustomRememberMeServices(Key, userDetailsService, persistentTokenRepository);
		
		rememberMeServices.setCookieName("ricordami");
      	rememberMeServices.setTokenValiditySeconds(60 * 60 * 4);
      	rememberMeServices.setParameter("ricordami");
      	rememberMeServices.setUseSecureCookie(false); //todo Abilitare con l'SSL
      	
      	return rememberMeServices;
		
		
	}*/
	
    

}