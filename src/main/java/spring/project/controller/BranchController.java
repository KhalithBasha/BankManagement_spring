package spring.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import spring.project.Config.ResponseStructure;
import spring.project.dto.Branch;
import spring.project.service.BranchService;

public class BranchController {
	@Autowired
	BranchService ser;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(@RequestBody Branch Bh) 
	{
		return ser.saveBranch(Bh);
		
	}
	@DeleteMapping
	public  ResponseEntity<ResponseStructure<Branch>> deleteBook(@RequestParam int id) 
	{
		return ser.deleteBranch(id);
	}
	@PutMapping
	public  ResponseEntity<ResponseStructure<Branch>> updateBranch(@RequestBody Branch b, @RequestParam int id) 
	{
		return ser.updateBranch(b,id);
		
	}
	@GetMapping
	public  ResponseEntity<ResponseStructure<Branch>> findBranch(@RequestParam int id) 
	{
		return ser.findBranch(id);
	}
	@PutMapping("/assignbank")
	public  ResponseEntity<ResponseStructure<Branch>> assignBank( @RequestParam int bId ,@RequestParam int bhId ) 
	{
		return ser.assignBank(bhId, bId);
		
	}
}
