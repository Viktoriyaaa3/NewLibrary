package teoresiGroup.web.service.Interfacce;

import java.util.List;
import java.util.Optional;

import teoresiGroup.web.model.Utente;
import teoresiGroup.web.model.UtentiModel;

public interface UtentiService {
	public String getUtente();
	void add(UtentiModel u);
	UtentiModel getById(int id);
	UtentiModel getByName(String nome);
	UtentiModel getByCognome(String cognome);
	Optional<UtentiModel> findOne(int id);
	public void insert(Utente u);
	public String dammiNome();
	List<UtentiModel>ByNome(String nome);
	List<UtentiModel> ByPassAndUsername(String password, String username);

}
