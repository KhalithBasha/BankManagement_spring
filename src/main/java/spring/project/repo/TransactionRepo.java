package spring.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.project.dto.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction,Integer> {

}
