package teoresiGroup.web.RepoitoryConCrudRepository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import teoresiGroup.web.model.UtentiModel;

@Repository
public interface UtentiCrudRepository extends CrudRepository<UtentiModel, Integer>{
	List<UtentiModel> findAll();
}
