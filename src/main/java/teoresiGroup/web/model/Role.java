package teoresiGroup.web.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role /*implements GrantedAuthority*/{
	USER, OPERATORE, EMPLOYEE, ADMIN;

	/*@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return name();
	}*/

}
