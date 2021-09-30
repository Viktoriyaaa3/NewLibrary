package teoresiGroup.web.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import teoresiGroup.web.Repository.AbstractDao;
import teoresiGroup.web.Repository.LibroRepo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Expression;

import teoresiGroup.web.Repository.UtentiRepo;
import teoresiGroup.web.model.LibriModel;
import teoresiGroup.web.model.Utente;
import teoresiGroup.web.model.UtentiModel;

@Repository
public class LibroImpl extends AbstractDao<LibriModel, Integer> implements LibroRepo{
	private static final Logger log= Logger.getLogger(LibroImpl.class.getName());
	
	@PersistenceContext
	private EntityManager em;
	@Autowired
	public LibroRepo libroRepo;
	private JdbcTemplate conn;

	

	public LibroImpl(DataSource dataSource) {
		conn=new JdbcTemplate(dataSource);
	}

	@Override
	@Transactional
	public void add(LibriModel l) {
		em.persist(l);
		
	}

	@Override
	public LibriModel getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LibriModel getByName(String nome) {
		// TODO Auto-generated method stub
		return null;
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
	public void insert(LibriModel u) {
		// TODO Auto-generated method stub
		
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
