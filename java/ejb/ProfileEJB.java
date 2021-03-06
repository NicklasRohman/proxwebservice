package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;
import entitys.ProfileEntity;

@Stateless
//@DeclareRoles({"admin"})
public class ProfileEJB {
	@PersistenceContext(name="projectx")
	private EntityManager em;
	
//	@RolesAllowed({"admin"})
	public List<ProfileEntity> findAll(){
		TypedQuery<ProfileEntity> q = em.createQuery("SELECT p FROM ProfileEntity AS p", ProfileEntity.class);
	return q.getResultList();
	}
	
//	@RolesAllowed({"admin"})
	public ProfileEntity findById(String id){
		TypedQuery<ProfileEntity> q = em.createQuery("SELECT p FROM ProfileEntity AS p WHERE p.emails = :id", ProfileEntity.class);
		q.setParameter("id", id);
		return q.getSingleResult();
	}
	
//	@RolesAllowed({"admin"})
	public ProfileEntity merge(ProfileEntity profile){
		return em.merge(profile);
	}
	
//	@RolesAllowed({"admin"})
	public void delete(String id){
		ProfileEntity profile = findById(id);
		em.remove(profile);
	}
}
