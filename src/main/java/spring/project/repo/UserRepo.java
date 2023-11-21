package spring.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.project.dto.User;

public interface UserRepo extends JpaRepository<User,Integer> {

}
