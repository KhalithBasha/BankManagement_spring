package spring.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import spring.project.Config.ResponseStructure;
import spring.project.dao.AccountDao;
import spring.project.dto.Account;
import spring.project.dto.AccountType;

@Service
public class AccountService {
	@Autowired
	AccountDao dao;
	
	public ResponseEntity<ResponseStructure<Account>> updateAccount(Account a,int id){
		ResponseStructure<Account> rs = new ResponseStructure<>();
		if (dao.findAccount(id)!=null) {
			Account exA = dao.findAccount(id);
			exA.setNumber(a.getNumber());
			exA.setBalance(a.getBalance());
			exA.setPassword(a.getPassword());
			rs.setData(dao.updateAccount(a, id));
			rs.setMsg("Account Updated Successfully");
			rs.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Account>>(rs,HttpStatus.OK);
		}
		return null;// Account not found
	}
	
	public ResponseEntity<ResponseStructure<Account>> changeAcctype(int aid, int atype){
		ResponseStructure<Account> rs = new ResponseStructure<>();
		if (dao.findAccount(aid)!=null) {
			Account a = dao.findAccount(aid);
			if (atype==1) {
				if (a.getAtype()!=AccountType.Saving_Account) {
					a.setAtype(AccountType.Saving_Account);
					rs.setData(dao.updateAccount(a, aid));
					rs.setMsg("Changed Successfully");
					rs.setStatus(HttpStatus.OK.value());
				}else {
					rs.setMsg("You can't Changes same type ");
					rs.setStatus(HttpStatus.NOT_MODIFIED.value());
					return new ResponseEntity<ResponseStructure<Account>>(rs,HttpStatus.NOT_MODIFIED);
				}
			}else {
				if (a.getAtype()!=AccountType.Current_Account) {
					a.setAtype(AccountType.Current_Account);
					rs.setMsg("Changed Successfully");
					rs.setStatus(HttpStatus.OK.value());
				}else {
					rs.setMsg("You can't Changes same type ");
					rs.setStatus(HttpStatus.NOT_MODIFIED.value());
					return new ResponseEntity<ResponseStructure<Account>>(rs,HttpStatus.NOT_MODIFIED);
				}
			}
		}
		return new ResponseEntity<ResponseStructure<Account>>(rs,HttpStatus.OK);
	}
	
}
