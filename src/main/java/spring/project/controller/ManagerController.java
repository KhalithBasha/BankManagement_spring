package spring.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.project.Config.ResponseStructure;
import spring.project.dto.Manager;
import spring.project.service.ManagerService;

@RestController
@RequestMapping("/manager")
public class ManagerController {
	@Autowired
	ManagerService ser;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Manager>> saveManager(@RequestBody Manager m,@RequestParam int bid) 
	{
		return ser.saveManager(m, bid);
		
	}
	@GetMapping
	public  ResponseEntity<ResponseStructure<Manager>> findManager(@RequestParam int id) 
	{
		return ser.findManager(id);
	}
	@DeleteMapping
	public  ResponseEntity<ResponseStructure<Manager>> deleteBook(@RequestParam int id) 
	{
		return ser.deleteManager(id);
	}
	@PutMapping
	public  ResponseEntity<ResponseStructure<Manager>> updateManager(@RequestBody Manager m, @RequestParam int id) 
	{
		return ser.updateManager(m,id);
		
	}
	@GetMapping("/login")
	public  ResponseEntity<ResponseStructure<Manager>> loginManager( @RequestParam String name, @RequestParam String password) 
	{
		return ser.loginManager(name, password);
		
	}
	
}
