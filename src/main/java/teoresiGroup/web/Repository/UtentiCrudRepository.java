package teoresiGroup.web.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import teoresiGroup.web.model.UtentiModel;

@Repository
public interface UtentiCrudRepository extends CrudRepository<UtentiModel, Integer>{

}
