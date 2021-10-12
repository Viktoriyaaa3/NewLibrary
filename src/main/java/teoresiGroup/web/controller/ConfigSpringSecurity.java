package teoresiGroup.web.controller;


import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

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
    
    

    public void configure(AuthenticationManagerBuilder auth) throws Exception{
       // auth.userDetailsService(user()).passwordEncoder(pass());
    
		@SuppressWarnings("deprecation")
		UserBuilder user=User.withDefaultPasswordEncoder();
    	auth.inMemoryAuthentication().withUser(user.username("vik").password("vik").roles("USER"))
    	.withUser(user.username("prova").password("prova").roles("OPERATORE", "USER"))
    	.withUser(user.username("admin").password("admin").roles("USER", "OPERATORE", "ADMIN"));
    	
    	
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login/log").loginProcessingUrl("/login/controllo/")
		.failureUrl("/cliente/add").permitAll()
		.and()
		.logout().permitAll();
		//.and()
		//.logout()
		//.permitAll();
		//.and().authorizeRequests().antMatchers("/operatore/**").hasRole("OPERATORE");
		//super.configure(http);
		/*loginPage("/showMyLoginPage).loginProcessingUrl("/authenticateTheUser")*/
	}
    
    

}