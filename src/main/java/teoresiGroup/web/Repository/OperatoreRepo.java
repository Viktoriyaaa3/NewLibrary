package teoresiGroup.web.Repository;

import java.util.List;


import teoresiGroup.web.model.OperatoreModel;

public interface OperatoreRepo {
	
	public void add(OperatoreModel l);
	OperatoreModel  getById(int id);
	void update(OperatoreModel  l);
	//void delete(LibriModel l);
	void delete(int id);
	public List<OperatoreModel > getAll();
	OperatoreModel getByName(String nome);

}
