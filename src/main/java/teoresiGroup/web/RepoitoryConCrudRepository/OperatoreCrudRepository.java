package teoresiGroup.web.RepoitoryConCrudRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import teoresiGroup.web.model.OperatoreModel;

@Repository
public interface OperatoreCrudRepository extends CrudRepository<OperatoreModel, Integer> {

}
