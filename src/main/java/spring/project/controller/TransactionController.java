package spring.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.project.Config.ResponseStructure;
import spring.project.dto.Transaction;
import spring.project.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	@Autowired
	TransactionService ts;
	@PutMapping
	public ResponseEntity<ResponseStructure<Transaction>> transAcc(@RequestParam int accfrom,@RequestParam String pass,@RequestParam int amount,@RequestParam int accto){
		return ts.sendMoney(accfrom, pass, amount, accto);
	}
}
