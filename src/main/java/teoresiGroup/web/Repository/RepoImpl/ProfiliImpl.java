package teoresiGroup.web.Repository.RepoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import teoresiGroup.web.Repository.ProfiliRepo;
import teoresiGroup.web.model.LibriModel;
import teoresiGroup.web.model.Profili;

@Repository
@Transactional
public class ProfiliImpl implements ProfiliRepo{
	private final Logger log= Logger.getLogger(ProfiliImpl.class.getName());
	@PersistenceContext
	private EntityManager em;
	@Autowired
	public ProfiliRepo profiliRepo;
	@Override
	public Profili SelById(int id) {
		return em.find(Profili.class, id);
		
	}

	@Override
	@Transactional
	public void Salva(Profili profilo) {
		em.persist(profilo);
		
	}

	@Override
	@Transactional
	public void Elimina(Profili profilo) {
		em.remove(profilo);
		
	}

	@Override
	@Transactional
	public void Aggiorna(Profili profilo) {
		em.merge(profilo);
		
	}



	@Override
	public Profili SelByUsername(String username) {
		CriteriaBuilder queryBuilder= em.getCriteriaBuilder();
		CriteriaQuery<Profili> query= queryBuilder.createQuery(Profili.class);
		Root<Profili> rec= query.from(Profili.class);
		 query.select(rec).where(queryBuilder.equal(rec.get("username"), username));
		 
		 Profili ut=em.createQuery(query).getSingleResult();
		 
		 em.clear();
		
		 return ut;
		
	}

}
