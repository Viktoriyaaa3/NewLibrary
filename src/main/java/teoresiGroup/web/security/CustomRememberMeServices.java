package teoresiGroup.web.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

public class CustomRememberMeServices extends PersistentTokenBasedRememberMeServices{

	public CustomRememberMeServices(String key, UserDetailsService userDetailsService,
			PersistentTokenRepository tokenRepository) {
		super(key, userDetailsService, tokenRepository);
		
	}

	protected boolean rememberMeRequest(HttpServletRequest request, String parameter) {
		String isRegularLogin= request.getParameter("isRegualrLogin");
		
		if(isRegularLogin!=null && "true".equals(isRegularLogin)) {
			return super.rememberMeRequested(request, parameter);
		}else
		{
			return true;
		}
		
	}

}
