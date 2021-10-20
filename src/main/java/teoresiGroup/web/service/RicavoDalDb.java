package teoresiGroup.web.service;

import org.apache.log4j.Logger;
import org.apache.maven.shared.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import teoresiGroup.web.model.UtentiModel;
import teoresiGroup.web.service.Interfacce.UtentiService;

@Service("userDetailsService")
public class RicavoDalDb  implements UserDetailsService{
	private static final Logger log= Logger.getLogger(RicavoDalDb.class.getName());

	@Autowired
	private UtentiService utentiService;
	
	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String[] Username = StringUtils.split(username);
		if(Username== null || Username.length!=1) {
			log.info(Username +  " nel mwtodo LoadUserByUsername");
			throw new UsernameNotFoundException("Questo username non è presente");
			
		}
		String usernam= Username[0];
		log.info("vedo che mi arriva nella stringa username[0] : " +usernam);
		UtentiModel ut= utentiService.selezionaPerUsername(username);
		log.info("vedo che mi arriva nella stringa username[0] : " +ut.getRuolo() + " " + ut.getAuthorities());
		if(ut==null) {
			throw new UsernameNotFoundException(usernam + " Utente non trovato");
		}
		
		
		UserBuilder builder=null;
		builder=User.withUsername(ut.getUsername());
		
		builder.disabled((ut.getAbilitato().equals(1)?false:true));// rconverto nel boolean a folase o true
		builder.password(ut.getPassword());
		
		String profili=ut.getRuolo();//.getProfili().stream().map(a->"ROLE_"+ a.getTipo())
			//	.toArray(String[]::new);
				
		/*string[]*/
		builder.authorities(profili);
		
		return builder.build();
	}

}
