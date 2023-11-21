package spring.project.Config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStructure<T> {
	private int status;
	private String msg;
	private T data;
}
