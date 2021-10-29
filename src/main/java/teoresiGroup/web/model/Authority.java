package teoresiGroup.web.model;

import java.util.Set;
//import lombok.NoArgsConstructor
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

//@NoArgsConstructor
@Entity
@Table(name="authorities")
public class Authority {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	 @Enumerated(EnumType.STRING)
	 private Role name;

	public Authority() {
		
	}

	public Authority(Role name) {
		
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Role getName() {
		return name;
	}

	public void setName(Role name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Authority [id=" + id + ", name=" + name + "]";
	}

	
	 
	
	
	
	
	
	
	
	
	
	
	
	 
	
/*	
private String role;
@ManyToMany(mappedBy="authorities")
private Set<User> users;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public Set<User> getUsers() {
	return users;
}
public void setUsers(Set<User> users) {
	this.users = users;
}
public Authority(Integer id, String role, Set<User> users) {
	super();
	this.id = id;
	this.role = role;
	this.users = users;
}
public Authority() {
	super();
}*/

}
