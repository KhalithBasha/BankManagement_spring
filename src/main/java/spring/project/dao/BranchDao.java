package spring.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.project.dto.Branch;
import spring.project.repo.BranchRepo;
@Repository
public class BranchDao {
	@Autowired
	BranchRepo repo;
	
	public Branch saveBranch(Branch b) {
		return repo.save(b);
	}
	
	public Branch findBranch(int id) {
		Optional<Branch> opBranch = repo.findById(id);
		if (opBranch.isPresent()) {
			return opBranch.get();
		}
		return null;
	}
	
	public Branch deleteBranch(int id) {
		Branch exBranch = findBranch(id);
		if (exBranch!=null) {
			repo.delete(exBranch);
			return exBranch;
		}
		return null;
	}
	
	public Branch updateBranch(Branch b, int id) {
		Branch exBranch = findBranch(id);
		if (exBranch!=null) {
			if (b.getBname()==null) {
				b.setBname(exBranch.getBname());
			}
			if (b.getIfsc()==null) {
				b.setIfsc(exBranch.getIfsc());
			}
			
			b.setId(id);;
			return repo.save(b);
		}
		return null;
	}
	
	public List<Branch> getAllList() {
		return repo.findAll();
	}

}
