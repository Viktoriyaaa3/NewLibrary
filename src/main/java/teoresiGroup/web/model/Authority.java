package teoresiGroup.web.model;

import java.util.Set;
//import lombok.NoArgsConstructor
import javax.persistence.Entity;
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
}

}
