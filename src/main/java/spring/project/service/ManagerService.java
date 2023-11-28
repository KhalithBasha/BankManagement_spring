package spring.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import spring.project.Config.ResponseStructure;
import spring.project.Exception.ManagerNotFound;
import spring.project.dao.BranchDao;
import spring.project.dao.ManagerDao;
import spring.project.dto.Branch;
import spring.project.dto.Manager;
import spring.project.repo.ManagerRepo;

@Service
public class ManagerService {
	@Autowired
	ManagerRepo mrepo;
	@Autowired
	ManagerDao mdao;
	@Autowired
	BranchDao bdao;
	
	public ResponseEntity<ResponseStructure<Manager>> saveManager(Manager m ,int bhId) {
		ResponseStructure<Manager> rs = new ResponseStructure<>();
		Manager m1 = mdao.saveManager(m);
		Branch exBr = bdao.findBranch(bhId);
		m1.setBranch(exBr);
		exBr.setMng(m1);
		mdao.updateManager(m1, m1.getId());
		rs.setData(m1);
		rs.setMsg("Manager Has Been Saved");
		rs.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Manager>>(rs,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Manager>> findManager(int id) 
	{
		ResponseStructure<Manager> rs = new ResponseStructure<>();
		if (mdao.findManager(id)!=null) {
		rs.setData(mdao.findManager(id));
		rs.setMsg("Manager Has Been Found");
		rs.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<Manager>>(rs,HttpStatus.FOUND );
		}
		throw new ManagerNotFound("No Manager Present With the given id");
	}
	
	public ResponseEntity<ResponseStructure<Manager>> updateManager(Manager m , int id) 
	{
		ResponseStructure<Manager> rs = new ResponseStructure<>();
		if (mdao.findManager(id)!=null) {
		rs.setData(mdao.updateManager(m,id));
		rs.setMsg("Manager Has Been Updated");
		rs.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Manager>>(rs,HttpStatus.OK );
		}
		throw new ManagerNotFound("No Manager Present With the given id");
		
	}
	
	public ResponseEntity<ResponseStructure<Manager>> deleteManager( int id) 
	{
		ResponseStructure<Manager> rs = new ResponseStructure<>();
		if (mdao.findManager(id)!=null) {
			Branch br =mdao.findManager(id).getBranch();
			br.setMng(null);
			Manager m=mdao.findManager(id);
			m.setBranch(null);
			mdao.updateManager(m, id);
			rs.setData(mdao.deleteManager(id));
			rs.setMsg("Manager Has Been Deleted");
			rs.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Manager>>(rs,HttpStatus.OK );
		}
		throw new ManagerNotFound("No Manager Present With the given id"); 
		
	}
	
	public ResponseEntity<ResponseStructure<Manager>> loginManager(String name,String password ){
		ResponseStructure<Manager> rs = new ResponseStructure<>();
		Manager m = mrepo.loginManager(name);
		if (m.getName()!=null) {
			if (m.getPassword().equals(password)) {
				rs.setData(m);
				rs.setMsg("Login Successful");
				rs.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<Manager>>(rs,HttpStatus.OK );
			}else {
				rs.setMsg("Login Failed Due to Invalid Password");
				rs.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
				return new ResponseEntity<ResponseStructure<Manager>>(rs,HttpStatus.NOT_ACCEPTABLE);
			}
		}
		rs.setMsg("Login Failed Due to Manager Name Not matching");
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<Manager>>(rs,HttpStatus.NOT_FOUND );
	}
}