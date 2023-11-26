package spring.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.project.Config.ResponseStructure;
import spring.project.dto.Account;
import spring.project.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	AccountService acs;
	@PutMapping
	public ResponseEntity<ResponseStructure<Account>> updateAccount(@RequestBody Account a,@RequestParam int id){
		return acs.updateAccount(a, id);
	}
	@PutMapping("/changeAtype")
	public ResponseEntity<ResponseStructure<Account>> changeAccountype(@RequestParam int id,@RequestParam int type){
		return acs.changeAcctype(id, type);
	}
	
}
