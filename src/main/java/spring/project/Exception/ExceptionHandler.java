package spring.project.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import spring.project.Config.ResponseStructure;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> BankNotFound(BankNotFound ex) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData("Bank Not Present");
		structure.setMsg(ex.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> BranchNotFound(BranchNotFound ex) {
		ResponseStructure<String> str = new ResponseStructure<>();
		str.setData("Branch Not present");
		str.setStatus(HttpStatus.NOT_FOUND.value());
		str.setMsg(ex.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(str,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ManagerNotFound(ManagerNotFound ex) {
		ResponseStructure<String> str = new ResponseStructure<>();
		str.setData("Manager Not Present");
		str.setMsg(ex.getMsg());
		str.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(str,HttpStatus.NOT_FOUND);
	}
}
