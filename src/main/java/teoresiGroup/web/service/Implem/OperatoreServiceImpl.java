package teoresiGroup.web.service.Implem;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import teoresiGroup.web.Repository.OperatoreRepo;
import teoresiGroup.web.model.OperatoreModel;
import teoresiGroup.web.service.Interfacce.OperatoreService;

public class OperatoreServiceImpl implements OperatoreService{
	
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private OperatoreRepo operatoreRepo;

	@Override
	@Transactional
	public void add(OperatoreModel l) {
		operatoreRepo.add(l);
		
	}

	@Override
	@Transactional
	public OperatoreModel getById(int id) {
		return operatoreRepo.getById(id);
	}

	@Override
	@Transactional
	public void update(OperatoreModel l) {
		operatoreRepo.update(l);
		
	}

	@Override
	@Transactional
	public void delete(int id) {
		operatoreRepo.delete(id);
		
	}

	@Override
	public List<OperatoreModel> getAll() {
		
		return operatoreRepo.getAll();
	}

	@Override
	public OperatoreModel getByName(String nome) {
		
		return operatoreRepo.getByName(nome);
	}

}
