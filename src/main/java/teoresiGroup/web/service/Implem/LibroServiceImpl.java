package teoresiGroup.web.service.Implem;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import teoresiGroup.web.Repository.LibroRepo;
import teoresiGroup.web.model.Libri;
import teoresiGroup.web.model.LibriModel;
import teoresiGroup.web.service.Interfacce.LibroService;


@Service
public class LibroServiceImpl implements LibroService {
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
	public String getLibro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void add(LibriModel l) {
		libroRepo.add(l);
		
	}

	@Override
	public LibriModel getById(int id) {
		return libroRepo.getById(id);
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
	@Transactional
	public void insert(LibriModel u) {
		 libroRepo.insert(u);
		
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
	@Override
	@Transactional
	public void update(LibriModel l) {
		libroRepo.update(l);
		
	}
	@Override
	@Transactional
	public void delete(LibriModel l) {
		libroRepo.delete(l);
		
	}
	@Override
	public List<Libri> getAll() {
		return libroRepo.getAll();
	
	}
	
	


}
