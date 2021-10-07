package teoresiGroup.web;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableWebSecurity
public class ConfigSpringSecurity  extends WebSecurityConfigurerAdapter {



    final static Logger logger=Logger.getLogger(ConfigSpringSecurity.class.getName());

    @Bean
    public BCryptPasswordEncoder pass(){
        //per criptare le password
        return new BCryptPasswordEncoder();
    }

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
       /* manager.createUser(
                users.username("Viky").password(new BCryptPasswordEncoder().encode("Abc123"))
                        .roles("USER","OPERATORE").build() );
 //utente 3
        manager.createUser(
                users.username("Vik").password(new BCryptPasswordEncoder().encode("Abc1234"))
                        .roles("USER", "OPERATORE", "ADMIN").build() );*/
return manager;
    }

    @Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() 
	{
	    StrictHttpFirewall firewall = new StrictHttpFirewall();
	    firewall.setAllowUrlEncodedSlash(true);
	    firewall.setAllowSemicolon(true);
	    
	    return firewall;
	}

    public void configure(final AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(user()).passwordEncoder(pass());
    }
   // url amministrativi a cui uò accedere l'amministratore
    private static final String[] ADMIN_Matcher={
            "/book/lb", "/book/libro",  "/libri/delete",  "/libri/aggiungi"
            /*url a cui potrà accedere solo l'admin*/
    };
private static final String[] OPERATOR_Matchers={
        "/operatori/**","/libri/add/**",  "/libri/delete/**",  "/libri/update/**", "/operatore/**"
};

protected void configure(final HttpSecurity http) throws Exception{
    //specifico le autorizzazioni

    http.authorizeRequests()
            .antMatchers("/login/**").permitAll() 
            .antMatchers("/book/all/**").permitAll()//specifico le parti a cui tutti possono accedere
            .antMatchers("/").hasAnyRole("ANONYMOUS", "USER") // permetto a tutti di accedere alla root della app
            .antMatchers(ADMIN_Matcher).access("hasRole('ADMIN')")
            .antMatchers("/libri/**").hasRole("USER")
            .and()
            .formLogin()//configuro il form di login
            .loginPage("/login/controllo")//specifico l'url con il quale accedo alla login page
            .loginProcessingUrl("/login")//specifico url di autenticazione
            .failureUrl("/login/**")//url se il login non va a buon fine DA CREARE
            .usernameParameter("username")
            .passwordParameter("password")//terminata config del login
            .and()
            .exceptionHandling()//gestisco le ecezzizioni
            .accessDeniedPage("/login/**")
            .and()
            .logout()//per eseguire il logout
            .logoutUrl("/login/**");//indico url di logout
           // .and().csrf().disable(); //da aggiungere in seguito per evitare attacchi hacker
/*http.authorizeRequests().antMatchers("/login/**").permitAll()
    .antMatchers("/login/**").permitAll()
        .antMatchers("/untitled_war/hi/**")
        .hasAnyRole("Anonimo","USER")
        .antMatchers(ADMIN_Matcher).access("hasRole('Admin')")
        .antMatchers("/clienti/**").hasRole("Admin")
        .and()
        .formLogin().loginPage("/untitled_war/hi/untitled_war/login")
        .loginProcessingUrl("/login")
        .failureUrl("/login/form?error")
        .usernameParameter("userame").passwordParameter("password")
        .and()
        .exceptionHandling().accessDeniedPage("/login/form?forbidden")
        .and()
        .logout()
        .logoutUrl("/login/form^logout")
        .and()
        .logout().logoutUrl("/login/form^logout");*/
       // .and().csrf().disable()
}}




