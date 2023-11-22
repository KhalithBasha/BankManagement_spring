package spring.project.Exception;

import lombok.Getter;

@Getter
public class BankNotFound extends RuntimeException{
	String msg;

	public BankNotFound(String msg) {
		this.msg = msg;
	}
}
