package teoresiGroup.web.service.Implem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import teoresiGroup.web.Repository.ProfiliRepo;
import teoresiGroup.web.model.Profili;
import teoresiGroup.web.service.Interfacce.ProfiliService;

@Service
public class ProfiliServiceImpl implements ProfiliService{
private static final Logger log= Logger.getLogger(ProfiliServiceImpl.class.getName());
	@Autowired
	private ProfiliRepo profiliRepo;
	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public Profili SelById(int id) {
		return profiliRepo.SelById(id);
	}

	@Override
	@Transactional
	public void Salva(Profili profilo) {
		profiliRepo.Salva(profilo);
		
	}

	@Override
	@Transactional
	public void Elimina(Profili profilo) {
		profiliRepo.Elimina(profilo);
		
	}

	@Override
	@Transactional
	public void Aggiorna(Profili profilo) {
		profiliRepo.Aggiorna(profilo);
		
	}

	@Override
	public Profili SelByUsername(String username) {
		
		return profiliRepo.SelByUsername(username);
	}

}
