package spring.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.project.dto.Branch;

public interface BranchRepo extends JpaRepository<Branch,Integer>{

}
