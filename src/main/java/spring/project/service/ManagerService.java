package spring.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import spring.project.Config.ResponseStructure;
import spring.project.dao.BranchDao;
import spring.project.dao.ManagerDao;
import spring.project.dto.Branch;
import spring.project.dto.Manager;

@Service
public class ManagerService {
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
		rs.setMsg("Bank Has Been Saved");
		rs.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Manager>>(rs,HttpStatus.CREATED);
	}
}
