package teoresiGroup.web.RepoitoryConCrudRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import teoresiGroup.web.model.LibriModel;

@Repository
public interface LibriCrudRepository extends CrudRepository<LibriModel, Integer> {

}
