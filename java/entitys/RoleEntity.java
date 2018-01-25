package entitys;

import java.lang.String;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="roles")
public class RoleEntity {
	
	@Id 
	private String rolename;
	
	public RoleEntity() {
	}   

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
   
}
