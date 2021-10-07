package teoresiGroup.web.Repository.RepoImpl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import teoresiGroup.web.Repository.LibroRepo;
import teoresiGroup.web.model.LibriModel;

@Repository
@Transactional
public class LibroImpl /*extends AbstractDao<LibriModel, Integer>*/ implements LibroRepo{
	private static final Logger log= Logger.getLogger(LibroImpl.class.getName());
	
	@PersistenceContext
	private EntityManager em;
	@Autowired
	public LibroRepo libroRepo;
	
	/*jdbc non mi serve. da eliminare tutti i metodi con jdbc*/
	

	@Override
	@Transactional
	public void add(LibriModel l) {
		log.info("sono nem metodo add di LibroImpl");
		log.info("Vedo dato che arriva qua: " + l);
		em.persist(l);
		
	}

	@Override
	public LibriModel getById(int id) {
	return	em.find(LibriModel.class,id);
		
	}
	@Override
	@Transactional
	public void update(LibriModel l) {
		em.merge(l);
		
	}
	
	@Override
	@Transactional
	public void delete(int id) {
		em.remove(getById(id));
		
	}
	@Override
	public List<LibriModel> getAll() {
		Query q = em.createQuery("SELECT p FROM LibriModel p");
		return q.getResultList();
	}


	@Override
	public LibriModel getByName(String autore) {
		CriteriaBuilder queryBuilder= em.getCriteriaBuilder();
		CriteriaQuery<LibriModel> query= queryBuilder.createQuery(LibriModel.class);
		Root<LibriModel> rec= query.from(LibriModel.class);
		 query.select(rec).where(queryBuilder.equal(rec.get("autore"), autore));
		 
		 LibriModel ut=em.createQuery(query).getSingleResult();
		 /*serve per ripulire EntityManager*/
		 em.clear();
		
		 return ut;
	}

	@Override
	public LibriModel getByCognome(String cognome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<LibriModel> findOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public String dammiNome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LibriModel> ByNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LibriModel> ByPassAndUsername(String autore, String titolo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLibro() {
		// TODO Auto-generated method stub
		return null;
	}

	


	
	



}
