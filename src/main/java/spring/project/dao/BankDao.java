package spring.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import spring.project.dto.Bank;
import spring.project.repo.BankRepo;

public class BankDao {
	@Autowired
	BankRepo repo;
	
	public Bank saveBank(Bank b) {
		return repo.save(b);
	}
	
	public Bank findBank(int id) {
		Optional<Bank> opBank = repo.findById(id);
		if (opBank.isPresent()) {
			return opBank.get();
		}
		return null;
	}
	
	public Bank deleteBank(int id) {
		Bank exBank = findBank(id);
		if (exBank!=null) {
			repo.delete(exBank);
			return exBank;
		}
		return null;
	}
	
	public Bank updateBank(Bank b, int id) {
		Bank exBank = findBank(id);
		if (exBank!=null) {
			if (b.getName()==null) {
				b.setName(exBank.getName());
			}
			
			b.setId(id);;
			return repo.save(b);
		}
		return null;
	}
	
	public List<Bank> getAllProducts() {
		return repo.findAll();
	}
}
