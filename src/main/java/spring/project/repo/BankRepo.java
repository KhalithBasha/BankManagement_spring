package spring.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.project.dto.Bank;

public interface BankRepo extends JpaRepository<Bank,Integer>{

}