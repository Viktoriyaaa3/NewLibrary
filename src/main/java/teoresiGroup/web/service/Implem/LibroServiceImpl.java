package teoresiGroup.web.service.Implem;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teoresiGroup.web.Repository.LibroRepo;
import teoresiGroup.web.model.LibriModel;
import teoresiGroup.web.service.Interfacce.LibroService;


@Service
public class LibroServiceImpl implements LibroService {
	@Autowired
	private LibroRepo libroRepo;

	@Override
	public String getLibro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(LibriModel l) {
		// TODO Auto-generated method stub
		
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

}
