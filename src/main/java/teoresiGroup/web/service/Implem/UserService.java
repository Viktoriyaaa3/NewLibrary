package teoresiGroup.web.service.Implem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import teoresiGroup.web.Repository.UtentiRepo;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UtentiRepo utentiRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return utentiRepo.getByName(username);
	}

}
