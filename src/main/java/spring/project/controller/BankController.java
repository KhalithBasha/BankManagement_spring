package spring.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.project.Config.ResponseStructure;
import spring.project.dto.Bank;
import spring.project.service.BankService;

@RestController
@RequestMapping("/bank")
public class BankController {
	@Autowired
	BankService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Bank>> saveBank(@RequestBody Bank b) {
		return service.saveBank(b);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Bank>> findBank(@RequestParam int id) {
		return service.findBank(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Bank>> updateBank(@RequestBody Bank b,@RequestParam int id) {
		return service.updateBank(b, id);
	}
	
	@PutMapping("/assignbranch")
	public  ResponseEntity<ResponseStructure<Bank>> assignBranch( @RequestParam int Id ,@RequestParam int bhId ) 
	{
		return service.assignBranch(Id, bhId);
		
	}
	
	
}
