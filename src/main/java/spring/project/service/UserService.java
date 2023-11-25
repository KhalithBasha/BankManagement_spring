package spring.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import spring.project.Config.ResponseStructure;
import spring.project.dao.ManagerDao;
import spring.project.dao.UserDao;
import spring.project.dto.Account;
import spring.project.dto.AccountType;
import spring.project.dto.Branch;
import spring.project.dto.Manager;
import spring.project.dto.User;

@Service
public class UserService {
	@Autowired
	ManagerDao mdao;
	@Autowired
	UserDao dao;
	
	public ResponseEntity<ResponseStructure<User>> createUser(User u,int Acctype,String mname,String mpass){
		ResponseStructure<User> rs = new ResponseStructure<>();
		Manager m = mdao.loginManager(mname, mpass);
		Branch b = m.getBranch();
		if (m!=null) {
			Account a = new Account();
			if (Acctype==1) {
				a.setAtype(AccountType.Current_Account);
			}
			else if(Acctype==2){
				a.setAtype(AccountType.Bussiness_Account);
			}
			else {
				a.setAtype(AccountType.Saving_Account);
			}
			u.setAcc(a);
			u.setBranch(b);
			a.setUser(u);
			User savedUser = dao.saveUser(u);
			b.getListuser().add(savedUser);
			mdao.updateManager(m, m.getId());
			rs.setData(savedUser);
			rs.setMsg("User Saved Successfully");
			rs.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<User>>(rs,HttpStatus.CREATED);
		}
		rs.setMsg("User Not Saved");
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<User>>(rs,HttpStatus.NOT_FOUND);
	}
}
