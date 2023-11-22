package spring.project.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import spring.project.Config.ResponseStructure;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> productNotFound(BankNotFound ex) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData("Product Not Found");
		structure.setMsg(ex.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
}
