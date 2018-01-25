package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;
import entitys.RoleEntity;

@Stateless
public class RoleEJB {
	@PersistenceContext(name="projectx")
	private EntityManager em;
	
	public List<RoleEntity>findAll(){
		TypedQuery<RoleEntity> q = em.createQuery("SELECT r FROM RoleEntity AS r",RoleEntity.class);
	return q.getResultList();
	}
	
	public RoleEntity findById(String id){
		TypedQuery<RoleEntity> q = em.createQuery("SELECT r FROM RoleEntity AS r WHERE r.rolename = :id",RoleEntity.class);
		q.setParameter("id", id);
		return q.getSingleResult();
	}
	
	public RoleEntity merge(RoleEntity role){
		return em.merge(role);
	}
	
	public void delete(String id) {
		RoleEntity role = findById(id);
		em.remove(role);
	}
	
}
