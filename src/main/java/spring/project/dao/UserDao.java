package spring.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.project.dto.User;
import spring.project.repo.UserRepo;
@Repository
public class UserDao {
	@Autowired
	UserRepo repo;
	
	public User saveUser(User u) {
		return repo.save(u);
	}
	
	public User findUser(int id) {
		Optional<User> opUser = repo.findById(id);
		if (opUser.isPresent()) {
			return opUser.get();
		}
		return null;
	}
	
	public User deleteUser(int id) {
		User exUser = findUser(id);
		if (exUser!=null) {
			repo.delete(exUser);
			return exUser;
		}
		return null;
	}
	
	public User updateUser(User u, int id) {
		User exUser = findUser(id);
		if (exUser!=null) {
			if (u.getName()==null) {
				u.setName(exUser.getName());
			}
			if (u.getAcc()==null) {
				u.setAcc(exUser.getAcc());
			}
			if (u.getAdrs()==null) {
				u.setAdrs(exUser.getAdrs());
			}
			if (u.getBranch()==null) {
				u.setBranch(exUser.getBranch());
			}
			
			u.setId(id);;
			return repo.save(u);
		}
		return null;
	}
	
	public List<User> getAllProducts() {
		return repo.findAll();
	}

}
