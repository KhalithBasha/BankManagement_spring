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
		ResponseStructure<Bank> rs = new ResponseStructure<>();
		rs.setData(dao.saveBank(b));
		rs.setMsg("Bank Has Been Saved");
		rs.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Bank>>(rs,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Bank>> findBank(int id) {
		ResponseStructure<Bank> rs = new ResponseStructure<>();
		if (dao.findBank(id)!=null) {
			rs.setData(dao.findBank(id));
			rs.setMsg("Bank is Present");
			rs.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Bank>>(rs,HttpStatus.FOUND);
		}
		throw new BankNotFound("No Bank Present With the given id");
	}
	
	
	public ResponseEntity<ResponseStructure<Bank>> updateBank(Bank b,int id) {
		ResponseStructure<Bank> rs = new ResponseStructure<>();
		
		if (dao.findBank(id)!= null) {
			rs.setData(dao.updateBank(b, id));
			rs.setMsg("Bank is Updated Succesfully");
			rs.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Bank>>(rs,HttpStatus.OK);
		}
		throw new BankNotFound("No Bank Present With the given id");
	}
}
