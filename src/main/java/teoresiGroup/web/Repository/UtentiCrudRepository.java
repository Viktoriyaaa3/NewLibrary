package teoresiGroup.web.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import teoresiGroup.web.model.UtentiModel;

@Repository
public interface UtentiCrudRepository extends CrudRepository<UtentiModel, Integer>{
	
	
	List<UtentiModel> findByNome(String nome);
	
   
	List<UtentiModel> findByUsernameAndPassword( String username,String password);
	List<UtentiModel> getByUsernameAndPassword(String username, String password);
List<UtentiModel> findByUsername(String username);
	
	//List<UtentiModel> searchByUsernameAndByPassword(String username, String password);
}
