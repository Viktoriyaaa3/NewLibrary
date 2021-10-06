package teoresiGroup.web.Repository.RepoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import teoresiGroup.web.Repository.OperatoreRepo;
import teoresiGroup.web.model.LibriModel;
import teoresiGroup.web.model.OperatoreModel;

public class OperatoreImpl implements OperatoreRepo {
	private static final Logger log= Logger.getLogger(OperatoreImpl.class.getName());
	@PersistenceContext
	private EntityManager em;
	
	
	
	//@Autowired
	//private OperatoreRepo operatoreRepo;
	@Override
	@Transactional
	public void add(OperatoreModel l) {
		log.info("sono nem metodo add di OperaotreImpl");
		log.info("Vedo dato che arriva qua: " + l);
		em.persist(l);
		
	}

	@Override
	public OperatoreModel  getById(int id) {
	return	em.find(OperatoreModel .class,id);
		
	}
	@Override
	@Transactional
	public void update(OperatoreModel l) {
		em.merge(l);
		
	}
	
	@Override
	@Transactional
	public void delete(int id) {
		em.remove(getById(id));
		
	}
	@Override
	public List<OperatoreModel > getAll() {
		Query q = em.createQuery("SELECT p FROM LibriModel p");
		return q.getResultList();
	}

	@Override
	public OperatoreModel getByName(String nome) {
		CriteriaBuilder queryBuilder= em.getCriteriaBuilder();
		CriteriaQuery<OperatoreModel> query= queryBuilder.createQuery(OperatoreModel.class);
		Root<OperatoreModel> rec= query.from(OperatoreModel.class);
		 query.select(rec).where(queryBuilder.equal(rec.get("nome"), nome));
		 
		 OperatoreModel ut=em.createQuery(query).getSingleResult();
		 /*serve per ripulire EntityManager*/
		 em.clear();
		
		 return ut;
	}

}
