package teoresiGroup.web.Repository.RepoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import javax.transaction.Transactional;
//import teoresiGroup.web.Repository.AbstractDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Expression;

import teoresiGroup.web.Repository.UtentiCrudRepository;
import teoresiGroup.web.Repository.UtentiRepo;

import teoresiGroup.web.model.UtentiModel;
import teoresiGroup.web.service.Interfacce.UtentiService;

@Repository
@Transactional
public class UtentiImpl implements UtentiRepo {
	private static final Logger log= Logger.getLogger(UtentiImpl.class.getName());
@PersistenceContext
private EntityManager em;
@Autowired
public UtentiRepo utentiRepo;

private JdbcTemplate conn;
//@Autowired
//public UtentiCrudRepository crudRepo;
/*@Override
	@Transactional
	public void add(UtentiModel u) {
		log.info("Sono nel metodo add di UtentiImpl");
		em.persist(u);
		log.info(u);
	}*/
/*@Transactional
public void add(UtentiModel u) {
try {
	u.setNome(u.getNome());
	u.setCognome(u.getCognome());
	u.setDataNascita(u.getDataNascita());
	crudRepo.save(u);
}catch(Exception e) {
	log.info("non è stato possibile salvare nuovo utente" + e.getStackTrace());
	
}}*/

	public UtentiImpl(DataSource ds) {
		conn= new JdbcTemplate(ds);
	}
	@Override
	public UtentiModel getById(int id) {
		
		return em.find(UtentiModel.class, id);
	}
	@Override
	//@Transactional
	public UtentiModel getByName(String nome) {
		CriteriaBuilder queryBuilder= em.getCriteriaBuilder();
		CriteriaQuery<UtentiModel> query= queryBuilder.createQuery(UtentiModel.class);
		Root<UtentiModel> rec= query.from(UtentiModel.class);
		 query.select(rec).where(queryBuilder.equal(rec.get("nome"), nome));
		 
		 UtentiModel ut=em.createQuery(query).getSingleResult();
		 /*serve per ripulire EntityManager*/
		 em.clear();
		
		 return ut;
		
		//return em.find(UtentiModel.class, nome);
		
	}
	@Override
	public List<UtentiModel> ByNome(String nome) {
		CriteriaBuilder queryBuilder= em.getCriteriaBuilder();
			CriteriaQuery<UtentiModel> query= queryBuilder.createQuery(UtentiModel.class);
	
			String toSearch= "%" + nome + "%";
			
			Root<UtentiModel> rec= query.from(UtentiModel.class);
			/*Ricerca "viceversa", nome-cognome, cognome-nome*/
			Expression<String> exp= queryBuilder.concat(rec.<String>get("nome"), " ");
			exp=queryBuilder.concat(exp, rec.<String>get("cognome"));
			
			Expression<String> exp2= queryBuilder.concat(rec.<String>get("cognome"), " ");
			exp2=queryBuilder.concat(exp2, rec.<String>get("nome"));
			
			
			Predicate p=queryBuilder.or(queryBuilder.like(exp, toSearch), queryBuilder.like(exp2, toSearch));
			
			query.select(rec).where(p);
			
			List<UtentiModel> ut=em.createQuery(query).getResultList();
			em.clear();
			
			return ut;
	}
	@Override
	public UtentiModel getByCognome(String cognome) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<UtentiModel> findOne(int id) {
		return null;//utentiRepo.findOne(id);
		
	}
	@Override
	public String getUtente() {
		//conn.
		return null;
	}
	
	@Override
	public String dammiNome() {
		String sql="SELECT nome FROM Utenti";
		String name= conn.queryForObject(sql, String.class);
		return name;
	}
	@Override
	public List<UtentiModel> ByPassAndUsername(String password, String username) {
		log.info("UtentiImpl ByPassAndUsername" + password + " " + username);
		CriteriaBuilder queryBuilder= em.getCriteriaBuilder();
		CriteriaQuery<UtentiModel> query= queryBuilder.createQuery(UtentiModel.class);

		String toSearch= "%" + username + "%";
		
		Root<UtentiModel> rec= query.from(UtentiModel.class);
		
		Expression<String> exp= queryBuilder.concat(rec.<String>get("username"), " ");
		exp=queryBuilder.concat(exp, rec.<String>get("password"));
		
		Expression<String> exp2= queryBuilder.concat(rec.<String>get("password"), " ");
		exp2=queryBuilder.concat(exp2, rec.<String>get("username"));
		
		
		Predicate p=queryBuilder.or(queryBuilder.like(exp, toSearch), queryBuilder.like(exp2, toSearch));
		
		query.select(rec).where(p);
		
		List<UtentiModel> ut=em.createQuery(query).getResultList();
		
		return ut;
	}
	@Override
	public UtentiModel selezionaPerUsername(String username) {
	/*	CriteriaBuilder queryBuilder= em.getCriteriaBuilder();
		CriteriaQuery<UtentiModel> query= queryBuilder.createQuery(UtentiModel.class);
		Root<UtentiModel> rec= query.from(UtentiModel.class);
		 query.select(rec).where(queryBuilder.equal(rec.get("username"), username));
		 
		 UtentiModel ut=em.createQuery(query)).getSingleResult();
	
		 em.clear();
		return ut;*/
		
		UtentiModel ut;
		String jpql ="SELECT a FROM Utenti a WHERE a.username=:username";
		ut=(UtentiModel) em.createQuery(jpql).setParameter("username", username).getSingleResult();
        return ut;
	}
	@Override
	public List<UtentiModel> getAll() {
		Query q=em.createQuery("Select p FROM UtentiModel p");
		return q.getResultList();
	}
	@Override
	public List<UtentiModel> cerca(List<UtentiModel> utenti, java.util.function.Predicate<UtentiModel> predicato) {
		List<UtentiModel> utentiTrovati= new ArrayList<UtentiModel>();
		for(UtentiModel u:utenti) {
			if(predicato.test(u)){
				utentiTrovati.get(u.getId());
			}
		}
		return null;
	}


	
	
	
	
	
	
	
	
	
	
	
	
}
