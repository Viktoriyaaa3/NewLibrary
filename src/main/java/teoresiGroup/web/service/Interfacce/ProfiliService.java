package teoresiGroup.web.service.Interfacce;

import java.util.List;

import teoresiGroup.web.model.Profili;

public interface ProfiliService {
Profili SelById(int id);
	
Profili SelByUsername(String username);
	
	void Salva(Profili profilo);

	void Elimina(Profili profilo);
	
	void Aggiorna(Profili profilo);

}
