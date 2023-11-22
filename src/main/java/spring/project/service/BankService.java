package spring.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import spring.project.Config.ResponseStructure;
import spring.project.Exception.BankNotFound;
import spring.project.dao.BankDao;
import spring.project.dto.Bank;

@Service
public class BankService {
	@Autowired
	BankDao dao;
	
	public ResponseEntity<ResponseStructure<Bank>> saveBank(Bank b) {
		ResponseStructure<Bank> structure = new ResponseStructure<>();
		structure.setData(dao.saveBank(b));
		structure.setMsg("Bank Has Been Saved");
		structure.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Bank>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Bank>> findBank(int id) {
		ResponseStructure<Bank> structure = new ResponseStructure<>();
		if (dao.findBank(id)!=null) {
			structure.setData(dao.findBank(id));
			structure.setMsg("Bank is Present");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Bank>>(structure,HttpStatus.FOUND);
		}
		throw new BankNotFound("No Bank Present With the given id");
	}
	
	
	public ResponseEntity<ResponseStructure<Bank>> updateBank(Bank b,int id) {
		ResponseStructure<Bank> structure = new ResponseStructure<>();
		
		if (dao.findBank(id)!= null) {
			structure.setData(dao.updateBank(b, id));
			structure.setMsg("Bank is Updated Succesfully");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Bank>>(structure,HttpStatus.OK);
		}
		throw new BankNotFound("No Bank Present With the given id");
	}
}
