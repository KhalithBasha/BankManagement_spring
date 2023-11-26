package spring.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.project.Config.ResponseStructure;
import spring.project.dto.Address;
import spring.project.dto.Branch;
import spring.project.dto.Manager;
import spring.project.dto.User;
import spring.project.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	AddressService ads;
	@PostMapping("/user")
	public ResponseEntity<ResponseStructure<User>> saveUserAdrs(@RequestBody Address a,@RequestParam int id) {
		return ads.createUserAdrs(a, id);
	}
	@PostMapping("/branch")
	public ResponseEntity<ResponseStructure<Branch>> saveBranchAdrs(@RequestBody Address a,@RequestParam int id) {
		return ads.createBranchAdrs(a, id);
	}@PostMapping("/manager")
	public ResponseEntity<ResponseStructure<Manager>> saveManagerAdrs(@RequestBody Address a,@RequestParam int id) {
		return ads.createManagerAdrs(a, id);
	}
}
