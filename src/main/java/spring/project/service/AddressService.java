package spring.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import spring.project.Config.ResponseStructure;
import spring.project.Exception.BranchNotFound;
import spring.project.Exception.ManagerNotFound;
import spring.project.Exception.UserNotFound;
import spring.project.dao.AddressDao;
import spring.project.dao.BranchDao;
import spring.project.dao.ManagerDao;
import spring.project.dao.UserDao;
import spring.project.dto.Address;
import spring.project.dto.Branch;
import spring.project.dto.Manager;
import spring.project.dto.User;

@Service
public class AddressService {
	@Autowired
	AddressDao dao;
	@Autowired
	UserDao udao;
	@Autowired
	BranchDao bdao;
	@Autowired
	ManagerDao mdao;
	
	public ResponseEntity<ResponseStructure<User>> createUserAdrs(Address adrs,int uid){
		ResponseStructure<User> rs = new ResponseStructure<>();
		if (udao.findUser(uid)!=null) {
			User u = udao.findUser(uid);
			u.setAdrs(dao.saveAddress(adrs));
			rs.setData(udao.updateUser(u, uid));
			rs.setMsg("Address is Assigned To User");
			rs.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<User>>(rs,HttpStatus.CREATED);
		}
		throw  new UserNotFound("No User Present With the given id");
	}
	
	public ResponseEntity<ResponseStructure<Branch>> createBranchAdrs(Address adrs,int bid){
		ResponseStructure<Branch> rs = new ResponseStructure<>();
		if (bdao.findBranch(bid)!=null) {
			Branch b = bdao.findBranch(bid);
			Address a =  dao.saveAddress(adrs);
			b.setAdrs(adrs);
			rs.setData(bdao.updateBranch(b, bid));
			rs.setMsg("Address is Assigned To Branch");
			rs.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Branch>>(rs,HttpStatus.CREATED);
		}
		throw  new BranchNotFound("No Branch Present With the given id");
	}
	
	public ResponseEntity<ResponseStructure<Manager>> createManagerAdrs(Address adrs,int mid){
		ResponseStructure<Manager> rs = new ResponseStructure<>();
		if (mdao.findManager(mid)!=null) {
			Manager m = mdao.findManager(mid);
			Address a =  dao.saveAddress(adrs);
			m.setAddress(a);
			rs.setData(mdao.updateManager(m, mid));
			rs.setMsg("Address is Assigned To Manager");
			rs.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Manager>>(rs,HttpStatus.CREATED);
		}
		throw  new ManagerNotFound("No Manager Present With the given id");
	}
}
