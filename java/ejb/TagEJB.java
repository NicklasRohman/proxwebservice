package ejb;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entitys.TagEntity;

@Stateless
@DeclareRoles({"admin"})
public class TagEJB {
	@PersistenceContext(name="projectx")
	private EntityManager em;
	
	
	public List<TagEntity> findAll() {
		TypedQuery<TagEntity> q = em.createQuery("SELECT t FROM TagEntity as t",TagEntity.class);
		return q.getResultList();
	}

	public TagEntity findById(int id) {
		TypedQuery<TagEntity> q = em.createQuery("SELECT t FROM TagEntity as t WHERE t.tagid = :id",TagEntity.class);
		q.setParameter("id",id);
		return q.getSingleResult();
	}

	public TagEntity merge(TagEntity event){
		return em.merge(event);
	}
	
	public void delete(int id){
		TagEntity event = findById(id);
		em.remove(event);
	}

	
}
