package teoresiGroup.web.service.Implem;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import teoresiGroup.web.Repository.LibroRepo;
import teoresiGroup.web.model.LibriModel;
import teoresiGroup.web.service.Interfacce.LibroService;


@Service
public class LibroServiceImpl implements LibroService {
	private final static Logger log= Logger.getLogger(LibroServiceImpl.class.getName());
	@Autowired
	private LibroRepo libroRepo;
	@PersistenceContext
	private EntityManager em;
	
	
	
	
	
	public LibroRepo getLibroRepo() {
		return libroRepo;
	}
	public void setLibroRepo(LibroRepo libroRepo) {
		this.libroRepo=libroRepo;
	}

	

	@Override
	@Transactional
	public void add(LibriModel l) {
		log.info("Sono nel metodo per aggiungere dati al libro. Classe ServiceImpl");
		log.info("Vedo cosa mi dice la l" + l);
		libroRepo.add(l);
		
	}

	@Override
	@Transactional
	public LibriModel getById(int id) {
		log.info("Sono nel metodo find by ID");
		log.info("vedo che id mi è arrivato: " + id);
		return libroRepo.getById(id);
	}
	@Override
	@Transactional
	public void update(LibriModel l) {
		log.info("Sono nel metodo update");
		libroRepo.update(l);
		
	}
	
	
	@Override
	@Transactional
	public void delete(int id) {
		libroRepo.delete(id);
		
	}
	@Override
	public List<LibriModel> getAll() {
		
		return libroRepo.getAll();
	}
	

	
	
	@Override
	public LibriModel getByName(String autore) {
		return libroRepo.getByName(autore);
	}

	@Override
	public LibriModel getByCognome(String cognome) {
		return libroRepo.getByCognome(cognome);
	}

	@Override
	public Optional<LibriModel> findOne(int id) {
		return libroRepo.findOne(id);
	}


	@Override
	public String dammiNome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LibriModel> ByNome(String nome) {
		return libroRepo.ByNome(nome);
	}

	@Override
	public List<LibriModel> ByPassAndUsername(String autore, String titolo) {
		return libroRepo.ByPassAndUsername(autore, titolo);
	}
	
	/*@Override
	@Transactional
	public void delete(LibriModel l) {
		libroRepo.delete(l);
		
	}*/
	@Override
	public String getLibro() {
		// TODO Auto-generated method stub
		return null;
	}
	/*@Override
	public List<Libri> getAll() {
		log.info("Sono nel metodo per prendere tutti i dati del libro");
		return libroRepo.getAll();
	
	}*/
	
	
	


}
