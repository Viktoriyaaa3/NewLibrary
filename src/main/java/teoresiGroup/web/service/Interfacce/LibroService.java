package teoresiGroup.web.service.Interfacce;

import java.util.List;
import java.util.Optional;


import teoresiGroup.web.model.LibriModel;

public interface LibroService {
	
	
	public void add(LibriModel l);
	LibriModel getById(int id);
	void update(LibriModel l);
	public List<LibriModel> getAll();
	void delete(int id);
	
	
	LibriModel getByName(String nome);
	LibriModel getByCognome(String cognome);
	Optional<LibriModel> findOne(int id);
	
	public String dammiNome();
	List<LibriModel>ByNome(String nome);
	List<LibriModel> ByPassAndUsername(String autore, String titolo);

	void delete(LibriModel l);
	public String getLibro();

}
