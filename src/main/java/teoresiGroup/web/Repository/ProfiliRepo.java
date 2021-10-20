package teoresiGroup.web.Repository;

import java.util.List;

import teoresiGroup.web.model.Profili;

public interface ProfiliRepo
{
	Profili SelById(int id);
	
	Profili SelByUsername(String username);
	
	void Salva(Profili profilo);

	void Elimina(Profili profilo);
	
	void Aggiorna(Profili profilo);
}