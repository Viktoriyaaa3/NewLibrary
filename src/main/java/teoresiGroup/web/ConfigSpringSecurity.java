package teoresiGroup.web;

import org.springframework.context.annotation.Configuration;
/*
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
                users.username("Viktoria").password(new BCryptPasswordEncoder().encode("Abc12"))
                        .roles("USER").build() );
//utente 2
        manager.createUser(
                users.username("Viky").password(new BCryptPasswordEncoder().encode("Abc123"))
                        .roles("USER","OPERATORE").build() );
 //utente 3
        manager.createUser(
                users.username("Vik").password(new BCryptPasswordEncoder().encode("Abc1234"))
                        .roles("USER", "OPERATORE", "ADMIN").build() );
return manager;
    }



    public void configure(final AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(user()).passwordEncoder(pass());
    }
   // url amministrativi
    private static final String[] ADMIN_Matcher={
            "/admin/**", "/libri/add/**",  "/libri/delete/**",  "/libri/update/**", "/operatore/**"
            /*url a cui potrà accedere solo l'admin*/
/*    };
private static final String[] OPERATOR_Matchers={
        "/operatori/**","/libri/add/**",  "/libri/delete/**",  "/libri/update/**", "/operatore/**"
};*/


//protected void configure(final HttpSecurity http) throws Exception{
    //specifico le autorizzazioni

  /*  http.authorizeRequests()
            .antMatchers("/login/**").permitAll() //specifico le parti a cui tutti possono accedere
            .antMatchers("/hi/ciao").hasAnyRole("ANONIMUS", "USER") // permetto a tutti di accedere alla root della app
            .antMatchers(ADMIN_Matcher).access("hasRole('ADMIN')")
            .antMatchers("/libri/**").hasRole("USER")
            .and()
            .formLogin()//configuro il form di login
            .loginPage("/login/form")//specifico l'url con il quale accedo alla login page
            .loginProcessingUrl("/login")//specifico url di autenticazione
            .failureUrl("/login/form?error")//url se il login non va a buon fine DA CREARE
            .usernameParameter("username")
            .passwordParameter("password")//terminata config del login
            .and()
            .exceptionHandling()//gestisco le ecezzizioni
            .accessDeniedPage("/login/form?forbidden")
            .and()
            .logout()//per eseguire il logout
            .logoutUrl("/login/form?logout");//indico url di logout*/
           // .and().csrf().disable(); //da aggiungere in seguito per evitare attacchi hacker
/*http.authorizeRequests().antMatchers("/untitled_war/hi/**").permitAll()
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
        .logout().logoutUrl("/login/form^logout");
       // .and().csrf().disable()*/
//}}




