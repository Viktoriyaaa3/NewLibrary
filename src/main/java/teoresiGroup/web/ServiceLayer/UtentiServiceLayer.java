package teoresiGroup.web.ServiceLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teoresiGroup.web.RepoitoryConCrudRepository.UtentiCrudRepository;
import teoresiGroup.web.model.UtentiModel;


@Service
public class UtentiServiceLayer {
	
	@Autowired
	private UtentiCrudRepository utentiRepo;
List <UtentiModel> prova=null;

//prova=utentiRepo.
	//public List<UtentiModel>
	public UtentiModel save(UtentiModel ut) {
		ut=utentiRepo.save(null);
		
		return ut;
	}
	
}
