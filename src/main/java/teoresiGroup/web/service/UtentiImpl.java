package teoresiGroup.web.service;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import teoresiGroup.web.Repository.UtentiRepo;
import teoresiGroup.web.model.Utente;
import teoresiGroup.web.model.UtentiModel;

public class UtentiImpl implements UtentiRepo{
	private static final Logger log= Logger.getLogger(UtentiImpl.class.getName());
@PersistenceContext
private EntityManager em;
@Autowired
public UtentiRepo utentiRepo;

private JdbcTemplate conn;
	@Override
	@Transactional
	public void add(UtentiModel u) {
		log.info("Sono nel metodo add di UtentiImpl");
		em.persist(u);
		log.info(u);
	}
	public UtentiImpl(DataSource ds) {
		conn= new JdbcTemplate(ds);
	}
	@Override
	public UtentiModel getById(int id) {
		
		return em.find(UtentiModel.class, id);
	}
	@Override
	@Transactional
	public UtentiModel getByName(String nome) {
		
		return em.find(UtentiModel.class, nome);
	}
	@Override
	public UtentiModel getByCognome(String cognome) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<UtentiModel> findOne(int id) {
		return utentiRepo.findOne(id);
		
	}
	@Override
	public String getUtente() {
		//conn.
		return null;
	}
	@Override
	public void insert(Utente u) {
		String sql="INSERT INTO Utenti(nome, cognome, codFiscale, telefono, email) VALUES(?,?,?,?,?)";
		conn.update(sql, u.getNome(), u.getCognome(), u.getCodFiscale(),u.getTelefono(), u.getEmail() );
		
	}
	@Override
	public String dammiNome() {
		String sql="SELECT nome FROM Utenti";
		String name= conn.queryForObject(sql, String.class);
		return name;
	}

}
