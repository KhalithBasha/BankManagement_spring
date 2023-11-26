package spring.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.project.Config.ResponseStructure;
import spring.project.dto.User;
import spring.project.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User u,@RequestParam int Acctype,@RequestParam String mname,@RequestParam String mpassword ) {
		return service.createUser(u, Acctype, mname, mpassword);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<User>> findUser(@RequestParam int id){
		return service.findUser(id);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<User>> deleteUser(@RequestParam int id){
		return service.deleteUser(id);
	}
}
