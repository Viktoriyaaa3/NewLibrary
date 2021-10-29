package teoresiGroup.web.service.Interfacce;

import java.util.List;


import teoresiGroup.web.model.OperatoreModel;

public interface OperatoreService {
	public void add(OperatoreModel l);
	OperatoreModel  getById(int id);
	void update(OperatoreModel  l);
	//void delete(LibriModel l);
	void delete(int id);
	public List<OperatoreModel > getAll();
	OperatoreModel getByName(String nome);

}
