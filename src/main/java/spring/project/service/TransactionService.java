package spring.project.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ViewNameMethodReturnValueHandler;

import spring.project.Config.ResponseStructure;
import spring.project.dao.AccountDao;
import spring.project.dao.TransactionDao;
import spring.project.dto.Account;
import spring.project.dto.Transaction;
import spring.project.dto.TransactionType;
import spring.project.dto.TranstionStatus;

@Service
public class TransactionService {
	
	@Autowired
	TransactionDao dao;
	@Autowired
	AccountDao adao;
	
	public ResponseEntity<ResponseStructure<Transaction>> sendMoney(int accf,String pass,int amount,int acct){
		ResponseStructure<Transaction> rs = new ResponseStructure<>();
		Account from = adao.findAccount(accf);
		Account to = adao.findAccount(acct);
		if (from!=null) {
			if (from.getPassword().equals(pass)) {
				if (to!=null) {
					if (from!=to) {
						if (amount>0) {
							if (from.getBalance() >= amount) {
								if (from.getBalance()-100 >= amount) {
									Transaction t= new Transaction();
									t.setAmount(amount);
									t.setToAccount(acct);
									t.setDateTime(LocalDateTime.now());
									t.setTtype(TransactionType.Debited);
									t.setTstatus(TranstionStatus.success);
									from.setBalance(from.getBalance()-amount);
									Transaction saved1= dao.saveTransaction(t);
									from.getTrans().add(saved1);
									adao.updateAccount(from, from.getId());
									
									Transaction t2 = new Transaction();
									t2.setAmount(amount);
									t2.setToAccount(accf);
									t2.setDateTime(LocalDateTime.now());
									t2.setTtype(TransactionType.Credited);
									t2.setTstatus(TranstionStatus.success);
									to.setBalance(from.getBalance()+amount);
									Transaction saved2= dao.saveTransaction(t2);
									to.getTrans().add(saved2);
									adao.updateAccount(to, to.getId());
									
									rs.setData(t);
									rs.setMsg("Transaction Completed");
									rs.setStatus(HttpStatus.OK.value());
									return new ResponseEntity<ResponseStructure<Transaction>>(rs,HttpStatus.OK);
								} else {
									return null;
								}
							} else {
								Transaction t= new Transaction();
								t.setAmount(amount);
								t.setToAccount(acct);
								t.setDateTime(LocalDateTime.now());
								t.setTtype(TransactionType.Debited);
								t.setTstatus(TranstionStatus.failed);
								Transaction saved1= dao.saveTransaction(t);
								from.getTrans().add(saved1);
								adao.updateAccount(from, from.getId());
								rs.setData(t);
								rs.setMsg("Transaction Failed");
								rs.setStatus(HttpStatus.BAD_REQUEST.value());
								return new ResponseEntity<ResponseStructure<Transaction>>(rs,HttpStatus.BAD_REQUEST);
							}
						} else {
							return null;
						}
					} else {
						return null;
					}
				} else {
					return null;
				}
			} else {
				return null;
			}
		}else {
			return null;
		}
	}
}
