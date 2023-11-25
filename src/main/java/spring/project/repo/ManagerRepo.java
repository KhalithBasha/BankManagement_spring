package spring.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.project.dto.Manager;

public interface ManagerRepo extends JpaRepository<Manager,Integer>{
	
	@Query("select m from Manager m where m.name =?1")
	public Manager loginManager(String name);
}
