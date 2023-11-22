package spring.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.project.dto.Transaction;
import spring.project.repo.TransactionRepo;
@Repository
public class TransactionDao {
	@Autowired
	TransactionRepo repo;
	
	public Transaction saveTransaction(Transaction t) {
		return repo.save(t);
	}
	
	public Transaction findTransaction(int id) {
		Optional<Transaction> opTransaction = repo.findById(id);
		if (opTransaction.isPresent()) {
			return opTransaction.get();
		}
		return null;
	}
	
	public Transaction deleteTransaction(int id) {
		Transaction exTransaction = findTransaction(id);
		if (exTransaction!=null) {
			repo.delete(exTransaction);
			return exTransaction;
		}
		return null;
	}
	
	public Transaction updateTransaction(Transaction t, int id) {
		Transaction exTransaction = findTransaction(id);
		if (exTransaction!=null) {
			if (t.getTstatus()==null) {
				t.setTstatus(exTransaction.getTstatus());
			}
			
			t.setId(id);;
			return repo.save(t);
		}
		return null;
	}
	
	public List<Transaction> getAllProducts() {
		return repo.findAll();
	}

}
