
package spring.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import spring.project.Config.ResponseStructure;
import spring.project.Exception.ManagerNotFound;
import spring.project.Exception.UserNotFound;
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
		throw new ManagerNotFound("Name Or Password is Invalid");
	}
	
	public ResponseEntity<ResponseStructure<User>> findUser(int id){
		ResponseStructure<User> rs= new ResponseStructure<>();
		if (dao.findUser(id)!=null) {
			rs.setData(dao.findUser(id));
			rs.setMsg("The User is Present ");
			rs.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(rs,HttpStatus.FOUND);
		}
		throw new UserNotFound("No User Present with the given id");
	}
	
	public ResponseEntity<ResponseStructure<User>> deleteUser(int id){
		ResponseStructure<User> rs = new ResponseStructure<>();
		if (dao.findUser(id)!=null) {
			User u = dao.findUser(id);
			Branch b = u.getBranch();
			u.setBranch(null);
			b.getListuser().remove(u);
			dao.updateUser(u, id);
			rs.setData(dao.deleteUser(id));
			rs.setMsg("The User Deleted Successfully");
			rs.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(rs,HttpStatus.OK);
		}
		throw new UserNotFound("No User Present with the given id");
	}
}
