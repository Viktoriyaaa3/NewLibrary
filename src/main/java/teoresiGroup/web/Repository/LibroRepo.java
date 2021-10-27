package teoresiGroup.web.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import teoresiGroup.web.model.LibriModel;

public interface LibroRepo{
	
	
	public void add(LibriModel l);
	LibriModel getById(int id);
	void update(LibriModel l);
	void delete(int id);
	public List<LibriModel> getAll();
	LibriModel getByName(String nome);
	
	public String getLibro();
	
	/*Questi non li sto utilizzando */
	LibriModel getByCognome(String cognome);
	Optional<LibriModel> findOne(int id);
	
	public String dammiNome();
	List<LibriModel>ByNome(String nome);
	List<LibriModel> ByPassAndUsername(String autore, String titolo);
	
	
	
	



}
