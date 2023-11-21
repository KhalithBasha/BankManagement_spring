package spring.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.project.dto.Account;

public interface AccountRepo extends JpaRepository<Account,Integer>{

}
