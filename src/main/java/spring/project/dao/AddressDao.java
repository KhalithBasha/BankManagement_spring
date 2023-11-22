package spring.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.project.dto.Address;
import spring.project.repo.AddressRepo;
@Repository
public class AddressDao {
	@Autowired
	AddressRepo repo;
	
	public Address saveAddress(Address a) {
		return repo.save(a);
	}
	
	public Address findAddress(int id) {
		Optional<Address> opAddress = repo.findById(id);
		if (opAddress.isPresent()) {
			return opAddress.get();
		}
		return null;
	}
	
	public Address deleteAddress(int id) {
		Address exAddress = findAddress(id);
		if (exAddress!=null) {
			repo.delete(exAddress);
			return exAddress;
		}
		return null;
	}
	
	public Address updateAddress(Address a, int id) {
		Address exAddress = findAddress(id);
		if (exAddress!=null) {
			if (a.getStreet()==null) {
				a.setStreet(exAddress.getStreet());
			}
			if (a.getCity()==null) {
				a.setCity(exAddress.getCity());
			}
			if (a.getState()==null) {
				a.setState(exAddress.getState());
			}
			if (a.getPincode()==0) {
				a.setPincode(exAddress.getPincode());
			}
			if (a.getContact()==0) {
				a.setContact(exAddress.getContact());
			}
			a.setId(id);;
			return repo.save(a);
		}
		return null;
	}
	
	public List<Address> getAllProducts() {
		return repo.findAll();
	}
	
	
}
