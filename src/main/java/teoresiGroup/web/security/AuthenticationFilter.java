package teoresiGroup.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if(!request.getMethod().equals("post")) {
			throw new AuthenticationServiceException("Autenticazione non è possibile" +  request.getMethod());
		}
		
		UsernamePasswordAuthenticationToken authToken= getAuthRequest(request);
		
		setDetails(request, authToken);
		
		return this.getAuthenticationManager().authenticate(authToken);
	}

	private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request) {
		String username =request.getParameter("username");
		String password=obtainPassword(request);
		
		username= (username== null)?"": username;
		password=(password==null)?"":password;
		
		String UserWithFid= username.trim();
		return new UsernamePasswordAuthenticationToken(UserWithFid, password);
		
	}
	
}
