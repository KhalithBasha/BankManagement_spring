package spring.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.project.dto.Account;
import spring.project.repo.AccountRepo;
@Repository
public class AccountDao {
	@Autowired
	AccountRepo repo;
	
	public Account saveAccount(Account a) {
		return repo.save(a);
	}
	
	public Account findAccount(int id) {
		Optional<Account> opAccount = repo.findById(id);
		if (opAccount.isPresent()) {
			return opAccount.get();
		}
		return null;
	}
	
	public Account deleteAccount(int id) {
		Account exAccount = findAccount(id);
		if (exAccount!=null) {
			repo.delete(exAccount);
			return exAccount;
		}
		return null;
	}
	
	public Account updateAccount(Account a, int id) {
		Account exAccount = findAccount(id);
		if (exAccount!=null) {
			if (a.getPassword()==null) {
				a.setPassword(exAccount.getPassword());
			}
			if (a.getAtype()==null) {
				a.setAtype(exAccount.getAtype());
			}
			a.setId(id);;
			return repo.save(a);
		}
		return null;
	}
	
	public List<Account> getAllProducts() {
		return repo.findAll();
	}
	
}
