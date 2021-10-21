package teoresiGroup.web.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
	USER, OPERATORE, ADMIN;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return name();
	}

}
