package entitys;

import javax.persistence.*;

@Entity
@Table(name = "roles")
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

	@Override
	public boolean equals(Object other){
		return(other instanceof RoleEntity) && (rolename != null ) 
				? rolename==(((RoleEntity)other).rolename)
				:(other==this);
	}

	
}
