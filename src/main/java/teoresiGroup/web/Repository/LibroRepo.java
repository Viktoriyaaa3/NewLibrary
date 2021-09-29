package teoresiGroup.web.Repository;

import java.util.List;
import java.util.Optional;

import teoresiGroup.web.model.LibriModel;

public interface LibroRepo {
	
	public String getLibro();
	public void add(LibriModel l);
	LibriModel getById(int id);
	LibriModel getByName(String nome);
	LibriModel getByCognome(String cognome);
	Optional<LibriModel> findOne(int id);
	public void insert(LibriModel u);
	public String dammiNome();
	List<LibriModel>ByNome(String nome);
	List<LibriModel> ByPassAndUsername(String autore, String titolo);



}
