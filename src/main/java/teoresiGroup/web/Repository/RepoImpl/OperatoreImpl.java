package teoresiGroup.web.Repository.RepoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import teoresiGroup.web.Repository.OperatoreRepo;
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

}
