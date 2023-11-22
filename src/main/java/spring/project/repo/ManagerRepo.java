package spring.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.project.dto.Manager;

public interface ManagerRepo extends JpaRepository<Manager,Integer>{

}
