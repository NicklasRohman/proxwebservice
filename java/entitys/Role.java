package entitys;

import java.lang.String;
import java.util.List;

import javax.persistence.*;


@Entity(name="roles")
public class Role {
	
	@Id 
	private String rolename;
	
	@ManyToMany(mappedBy = "roles")
	private List<ProfileEntity> emails;	
	
	public Role() {
	}   

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public List<ProfileEntity> getEmails() {
		return emails;
	}

	public void setEmails(List<ProfileEntity> emails) {
		this.emails = emails;
	}
   
}
