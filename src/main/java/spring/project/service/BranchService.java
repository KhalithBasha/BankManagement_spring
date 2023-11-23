package spring.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import spring.project.Config.ResponseStructure;
import spring.project.dao.BankDao;
import spring.project.dao.BranchDao;
import spring.project.dto.Bank;
import spring.project.dto.Branch;

@Service
public class BranchService {
	@Autowired
	BranchDao bndao;
	
	@Autowired
	BankDao bdao;
	
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(Branch b, int bId) 
	{
		ResponseStructure<Branch> rs = new ResponseStructure<>();
		Bank exBank =bdao.findBank(bId);
		Branch savedBranch = bndao.saveBranch(b);
		exBank.getListbranch().add(savedBranch);
		savedBranch.setBank(exBank);
		bndao.updateBranch(savedBranch, savedBranch.getId());
		rs.setData(savedBranch);
		rs.setMsg("Branch Has Been Saved");
		rs.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Branch>>(rs,HttpStatus.CREATED );
	}
	
	public ResponseEntity<ResponseStructure<Branch>> findBranch(int id) 
	{
		ResponseStructure<Branch> rs = new ResponseStructure<>();
		if (bndao.findBranch(id)!=null) {
		rs.setData(bndao.findBranch(id));
		rs.setMsg("Branch Has Been Found");
		rs.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<Branch>>(rs,HttpStatus.FOUND );
		}
		return null ;//Branch not found
	}
	
	public ResponseEntity<ResponseStructure<Branch>> updateBranch(Branch b , int id) 
	{
		ResponseStructure<Branch> rs = new ResponseStructure<>();
		if (bndao.findBranch(id)!=null) {
		rs.setData(bndao.updateBranch(b,id));
		rs.setMsg("Branch Has Been Updated");
		rs.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Branch>>(rs,HttpStatus.OK );
		}
		return null ;//Branch not found 
		
	}
	
	public ResponseEntity<ResponseStructure<Branch>> deleteBranch( int id) 
	{
		ResponseStructure<Branch> rs = new ResponseStructure<>();
		if (bndao.findBranch(id)!=null) {
			rs.setData(bndao.deleteBranch(id));
			rs.setMsg("Branch Has Been Deleted");
			rs.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Branch>>(rs,HttpStatus.OK );
		}
		return null ;//Branch not found 
		
	}
	
	public ResponseEntity<ResponseStructure<List<Branch>>>findAllBranch() {
		ResponseStructure<List<Branch>> rs = new ResponseStructure<>();
		if (!bndao.getAllList().isEmpty()) {
			rs.setData(bndao.getAllList());
			rs.setMsg("Branch Found");
			rs.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Branch>>>(rs,HttpStatus.FOUND );
		}
		 return null ;//Branch not found
	}
	
	public ResponseEntity<ResponseStructure<Branch>>  assignBank(int bId,int bhId) 
	{
		ResponseStructure<Branch> rs = new ResponseStructure<>();
		if (bndao.findBranch(bhId)!=null) {
			if (bdao.findBank(bId)!=null) {
				Branch bh = bndao.findBranch(bhId);
				bh.setBank(bdao.findBank(bId));
				rs.setData(bndao.updateBranch(bh,bhId));
				rs.setStatus(HttpStatus.OK.value());
				rs.setMsg("Bank Has Been Assigned");
				return new ResponseEntity<ResponseStructure<Branch>>(rs,HttpStatus.OK ); 
			}
			return null; //Bank not found 
		}
		return null; //Branch not found
	}
}
