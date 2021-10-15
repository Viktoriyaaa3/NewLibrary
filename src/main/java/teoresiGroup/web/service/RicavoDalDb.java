package teoresiGroup.web.service;

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

@Service("servizioUtenti")
public class RicavoDalDb  implements UserDetailsService{

	@Autowired
	private UtentiService utentiService;
	
	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//serve per username
		String[] Username = StringUtils.split(username);
		if(Username== null || Username.length!=1) {
			throw new UsernameNotFoundException("Questo username non è presente");
		}
		String usernam= Username[0];
		
		UtentiModel ut= utentiService.selezionaPerUsername(username);
		if(ut==null) {
			throw new UsernameNotFoundException(usernam + " Utente non trovato");
		}
		
		
		UserBuilder builder=null;
		builder=User.withUsername(ut.getUsername());
		
		builder.disabled((ut.getAbilitato().equals("si")?false:true));// rconverto nel boolean a folase o true
		builder.password(ut.getPassword());
		
		String[] ruolo=null;
		//manca la stringa che gestisce i ruoli
		
		builder.authorities(ruolo);// creare la tabella relazionale ruoli utenti
		//per ora ruolo non esiste
		
		return builder.build();
	}
	
	
	

}
