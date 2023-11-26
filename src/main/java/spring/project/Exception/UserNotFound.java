package spring.project.Exception;

import lombok.Getter;

@Getter
public class UserNotFound extends RuntimeException{
	String msg;

	public UserNotFound(String msg) {
		super();
		this.msg = msg;
	}
	
}
