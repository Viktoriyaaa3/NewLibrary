package teoresiGroup.web.service.Implem;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import teoresiGroup.web.Repository.UtentiCrudRepository;
import teoresiGroup.web.Repository.UtentiRepo;
import teoresiGroup.web.model.UtentiModel;
import teoresiGroup.web.service.Interfacce.UtentiService;

@Service
@Transactional
public class UtentiServiceImpl implements UtentiService {
	private final static Logger log= Logger.getLogger(UtentiServiceImpl.class.getName());
	@Autowired
	private UtentiRepo utentiRepo;
	@PersistenceContext
	private EntityManager em;
	//@Autowired
	//private UtentiCrudRepository crudRepo;
	//@Autowired
	//private BCryptPasswordEncoder bcpe;

	public UtentiRepo getUtentiRepo() {
		return utentiRepo;
	}
	public void setUtentiRepo(UtentiRepo utentiRepo) {
		this.utentiRepo = utentiRepo;
	}
	@Override
	public String getUtente() {
	
		return null;
	}
	
	/*@Transactional
	public void add(UtentiModel u) {
		//UtentiModel u= new UtentiModel();
		crudRepo.save(u);
	}*/
	/*UtentiModel utente= null;
	try {u.setPassword(u.getPassword());
		
	}catch(Exception e) {
		log.info(e.getMessage());
		
		
	}
	utentiRepo.add(u);
	*/
	@Override
	public UtentiModel getById(int id) {
		
		return utentiRepo.getById(id);
	}

	@Override
	public UtentiModel getByName(String nome) {
		
		return utentiRepo.getByName(nome);
	}

	@Override
	public UtentiModel getByCognome(String cognome) {
		return utentiRepo.getByCognome(cognome);
		
	}

	@Override
	public Optional<UtentiModel> findOne(int id) {
		
		return utentiRepo.findOne(id);
	}

	

	@Override
	public String dammiNome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UtentiModel> ByNome(String nome) {
		
		return utentiRepo.ByNome(nome);
	}

	@Override
	public List<UtentiModel> ByPassAndUsername(String password, String username) {
		log.info("UtentiService usenrame e password" + password + " " + username);
		return  utentiRepo.ByPassAndUsername(password, username);
		
	}
	@Override
	public List<UtentiModel> getAll() {
		
		return utentiRepo.getAll();
	}
	/*@Override
	public User registerNewUserAccount(UserDto accountDto) throws EmailExistsException {
	 
	    if (emailExist(accountDto.getEmail())) {
	        throw new EmailExistsException
	          ("There is an account with that email adress: " + accountDto.getEmail());
	    }
	    User user = new User();

	    user.setFirstName(accountDto.getFirstName());
	    user.setLastName(accountDto.getLastName());
	    user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
	    user.setEmail(accountDto.getEmail());

	    user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
	    return repository.save(user);
	}
*/
	
	@Override
	public UtentiModel selezionaPerUsername(String username) {
		log.info("UtentiService seleziona per username" +  " " + username);
		return utentiRepo.selezionaPerUsername(username);
	}
	@Override
	public List<UtentiModel> cerca(List<UtentiModel> utenti, Predicate<UtentiModel> predicato) {
		
		return utentiRepo.cerca(utenti, predicato);
	}
	
	/*	
	public LibriModel getByName(String autore) {
		CriteriaBuilder queryBuilder= em.getCriteriaBuilder();
		CriteriaQuery<LibriModel> query= queryBuilder.createQuery(LibriModel.class);
		Root<LibriModel> rec= query.from(LibriModel.class);
		 query.select(rec).where(queryBuilder.equal(rec.get("autore"), autore));
		 
		 LibriModel ut=em.createQuery(query).getSingleResult();
	
		 em.clear();
		
		 return ut;
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
