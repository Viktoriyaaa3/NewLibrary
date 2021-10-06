package teoresiGroup.web;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
//import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import teoresiGroup.web.Repository.LibroRepo;
import teoresiGroup.web.Repository.OperatoreRepo;
import teoresiGroup.web.Repository.UtentiRepo;
import teoresiGroup.web.Repository.RepoImpl.LibroImpl;
import teoresiGroup.web.Repository.RepoImpl.OperatoreImpl;
import teoresiGroup.web.Repository.RepoImpl.UtentiImpl;
import teoresiGroup.web.service.Implem.LibroServiceImpl;
import teoresiGroup.web.service.Implem.OperatoreServiceImpl;
import teoresiGroup.web.service.Implem.UtentiServiceImpl;
import teoresiGroup.web.service.Interfacce.LibroService;
import teoresiGroup.web.service.Interfacce.OperatoreService;
import teoresiGroup.web.service.Interfacce.UtentiService;

import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = " teoresiGroup.web.controller")
@PropertySource("classpath:NewLibrary.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "teoresiGroup.web.Repository", entityManagerFactoryRef = "emf", transactionManagerRef = "tmf")
public class WebConfig implements WebMvcConfigurer/*extends WebMvcConfigurerAdapter implements ApplicationContextAware */{
	  @Autowired
	    private Environment env;
	
	
	 @Autowired
	   private ApplicationContext applicationContext;

	   /*
	    * STEP 1 - Create SpringResourceTemplateResolver
	    * */
	   @Bean
	   public SpringResourceTemplateResolver templateResolver() {
	      SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	      templateResolver.setApplicationContext(applicationContext);
	      templateResolver.setPrefix("WEB-INF/views/");
	      templateResolver.setSuffix(".html");
	      return templateResolver;
	   }

	   /*
	    * STEP 2 - Create SpringTemplateEngine
	    * */
	   @Bean
	   public SpringTemplateEngine templateEngine() {
	      SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	      templateEngine.setTemplateResolver(templateResolver());
	      templateEngine.setEnableSpringELCompiler(true);
	      /*Questo pezzo per lavorare con date in th*/
	      templateEngine.addDialect(new Java8TimeDialect());
	      return templateEngine;
	   }

	   /*
	    * STEP 3 - Register ThymeleafViewResolver
	    * */
	   public void configureViewResolvers(ViewResolverRegistry registry) {
	      ThymeleafViewResolver resolver = new ThymeleafViewResolver();
	      resolver.setTemplateEngine(templateEngine());
	      registry.viewResolver(resolver);
	   }
	   @Bean(name = "dataSource")
	    public DataSource getDataSource(){
	        DriverManagerDataSource db= new DriverManagerDataSource();
	        db.setDriverClassName(env.getRequiredProperty("NewLibrary.db.driver"));
	        db.setUrl(env.getRequiredProperty("NewLibrary.db.url"));
	        db.setUsername(env.getRequiredProperty("NewLibrary.db.username"));
	        db.setPassword(env.getRequiredProperty("NewLibrary.db.password"));
	        return db;
	    }
	    @Bean(name="emf")
	    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(){
	        HibernateJpaVendorAdapter adapter=new HibernateJpaVendorAdapter();
	        adapter.setDatabase(Database.MYSQL);
	        adapter.setGenerateDdl(true);

	        //serve per creare un entity manager di jpa
	        LocalContainerEntityManagerFactoryBean factory= new LocalContainerEntityManagerFactoryBean();
	        factory.setDataSource(getDataSource());
	        factory.setJpaVendorAdapter(adapter);
	        factory.setPackagesToScan(getClass().getPackage().getName());//classi che utilizzano entity manager
	        return factory;
	    }

	    @Bean(name="tmf")
	    public PlatformTransactionManager getTransactionManager(){
	       // JpaTransactionManager jtm= new JpaTransactionManager(getEntityManager().getObject());
	       
	    	return new JpaTransactionManager(getEntityManagerFactory().getObject());

	    }
	    
	    @Bean
	    public UtentiRepo getUtenteService() {
	    	return new UtentiImpl(getDataSource());
	    }
	    
	    
	    @Bean
	    public LibroRepo getLibroService() {
	    	return new LibroImpl();
	    }
	    @Bean
	    public UtentiService getUtentiService() {
	    	return new UtentiServiceImpl();
	    	
	    }
	    @Bean
	    public LibroService getLibroServic() {
	    	return new LibroServiceImpl();
	    }
	    @Bean
	    public OperatoreRepo getOp() {
	    	return new OperatoreImpl();
	    }
	    @Bean
	    public OperatoreService getOpService() {
	    	return new OperatoreServiceImpl();
	    }
	    
}
