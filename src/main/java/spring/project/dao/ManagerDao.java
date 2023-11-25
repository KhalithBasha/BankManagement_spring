package spring.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.project.dto.Manager;
import spring.project.repo.ManagerRepo;
@Repository
public class ManagerDao {
	@Autowired
	ManagerRepo repo;
	
	public Manager saveManager(Manager m) {
		return repo.save(m);
	}
	
	public Manager findManager(int id) {
		Optional<Manager> opManager = repo.findById(id);
		if (opManager.isPresent()) {
			return opManager.get();
		}
		return null;
	}
	
	public Manager deleteManager(int id) {
		Manager exManager = findManager(id);
		if (exManager!=null) {
			repo.delete(exManager);
			return exManager;
		}
		return null;
	}
	
	public Manager updateManager(Manager m, int id) {
		Manager exManager = findManager(id);
		if (exManager!=null) {
			if (m.getName()==null) {
				m.setName(exManager.getName());
			}
			if (m.getPassword()==null) {
				m.setPassword(exManager.getPassword());
			}
			if (m.getAddress()==null) {
				m.setAddress(exManager.getAddress());
			}
			if (m.getBranch()==null) {
				m.setBranch(exManager.getBranch());
			}
			
			m.setId(id);;
			return repo.save(m);
		}
		return null;
	}
	
	public List<Manager> getAllProducts() {
		return repo.findAll();
	}
	
	public Manager loginManager(String name,String password) {
		Manager exManager = repo.loginManager(name);
		if (exManager.getName()!=null) {
			if (exManager.getPassword().equals(password)) {
				return exManager;
			}
		}return null;
	}

}
