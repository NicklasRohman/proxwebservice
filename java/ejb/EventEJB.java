package ejb;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.*;

import org.eclipse.persistence.internal.libraries.asm.tree.TryCatchBlockNode;

import entitys.EventEntity;

@Stateless
@DeclareRoles({"admin","users"})
public class EventEJB {
	@PersistenceContext(name="projectx")
	private EntityManager em;
	
//	@RolesAllowed({"admin"})
	public List<EventEntity> findAll() {
		TypedQuery<EventEntity> q = em.createQuery("SELECT e FROM EventEntity as e",EventEntity.class);
		return q.getResultList();
	}
	
//	@RolesAllowed({"admin"})
	public EventEntity findById(int id) {
		TypedQuery<EventEntity> q = em.createQuery("SELECT e FROM EventEntity as e WHERE e.eventid = :eventid",EventEntity.class);
		q.setParameter("eventid",id);
		return q.getSingleResult();
	}

//	@RolesAllowed({"admin"})
	public EventEntity merge(EventEntity event){
		return em.merge(event);
	}
	
	
//	@RolesAllowed({"admin"})
	public void delete(int id){
		EventEntity event = findById(id);
		em.remove(event);
	}
}
