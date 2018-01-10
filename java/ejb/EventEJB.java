package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;
import entitys.EventEntity;

@Stateless
public class EventEJB {
	@PersistenceContext(name="projectx")
	private EntityManager em;
	
	public List<EventEntity> findAll() {
		TypedQuery<EventEntity> q = em.createQuery("SELECT e FROM EventEntity as e",EventEntity.class);
		return q.getResultList();
	}

	public EventEntity findById(int id) {
		TypedQuery<EventEntity> q = em.createQuery("SELECT e FROM EventEntity as e WHERE e.eventid = :eventid",EventEntity.class);
		q.setParameter("eventid",id);
		return q.getSingleResult();
	}

	public EventEntity merge(EventEntity event){
		return em.merge(event);
	}
	
	public void delete(int id){
		EventEntity event = findById(id);
		em.remove(event);
	}
}
