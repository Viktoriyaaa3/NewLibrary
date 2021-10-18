package teoresiGroup.web.security;


import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import teoresiGroup.web.Repository.PersistentToken;
import teoresiGroup.web.service.RicavoDalDb;
//import teoresiGroup.web.service.Implem.UserService;




@Configuration
@EnableWebSecurity
//@PropertySource("classpath:NewLibrary.properties")
public class ConfigSpringSecurity  extends WebSecurityConfigurerAdapter {



    final static Logger logger=Logger.getLogger(ConfigSpringSecurity.class.getName());

    @Autowired
    private DataSource securityDataSource;
  //  @Autowired
    //private UserService us;
    @Autowired
    private UserDetailsService ricavo;
    
    @Autowired
   // @Qualifier("persistentTokenRepo")
    private PersistentTokenRepository  persistentTokenRepository;
    /*
	 @Autowired 
	 private UserService userService;*/
    
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
	
    
   
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
    	auth.authenticationProvider(authenticationProvider());//usare questo
    	
    //	auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    	
       // auth.userDetailsService(user()).passwordEncoder(pass());
    
	/*auth.jdbcAuthentication().dataSource(securityDataSource)
	.passwordEncoder(passwordEncoder()).usersByUsernameQuery("select username, password, enabled from Utenti where username=?")
	.authoritiesByUsernameQuery("select u.useername, ur.roles from Username u inner join user_role ur on u.id=ur.user_id"
			+ "where u.username=?");*/
    	//auth.userDetailsService(us).passwordEncoder(passwordEncoder());
    	
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(ricavo);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
    	
    	return authenticationProvider;
	}
	private AuthenticationFilter authenticationFilter() throws Exception{
		AuthenticationFilter filter= new AuthenticationFilter();
		
		filter.setAuthenticationManager(authenticationManagerBean());
		filter.setAuthenticationFailureHandler(failureHandler());
		filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
		filter.setRememberMeServices(customRememberMeServices());
		return filter;
	}
	public SimpleUrlAuthenticationFailureHandler failureHandler() {
		/*QUA INSERIRE URL DELLA PAGINA DI ERRORE*/
		return new SimpleUrlAuthenticationFailureHandler("/login/controllo");
	}
	@Bean
	public SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler() {
		 SavedRequestAwareAuthenticationSuccessHandler auth= new  SavedRequestAwareAuthenticationSuccessHandler();
		 auth.setTargetUrlParameter("targetUrl");//inserire url di successo
	return auth;
	}
	@Bean
	public PersistentTokenBasedRememberMeServices customRememberMeServices() {
		
		String Key= "ricordamiKey";
		PersistentTokenBasedRememberMeServices rememberMeServices= new
				CustomRememberMeServices(Key, userDetailsService(), persistentTokenRepository);
		rememberMeServices.setCookieName("ricordami");
		rememberMeServices.setTokenValiditySeconds(60 * 60 *4);//4 ore
		rememberMeServices.setParameter("ricordami");
		rememberMeServices.setUseSecureCookie(false);
		
		return rememberMeServices;
	}
	
	/*@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		tokenRepositoryImpl.setDataSource(securityDataSource);
		return tokenRepositoryImpl;
		
	}*/
	@Override
   	protected void configure(HttpSecurity http) throws Exception {
   		http
   		.authorizeRequests()//.anyRequest().authenticated()
   		.antMatchers("/").permitAll()
   		//.antMatchers("/book/**").hasRole("EMPLOYEE")
   		.and()
   		.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
   		.formLogin()
   		.loginPage("/login/log").loginProcessingUrl("/login/controllo/").permitAll()
   		
   		;
   		
   	
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
    

}