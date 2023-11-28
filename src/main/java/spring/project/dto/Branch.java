package spring.project.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Getter
@Setter	
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String ifsc;
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<User> listuser;
	@ManyToOne(cascade = CascadeType.ALL)
	private Bank bank;
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Address adrs;
	@OneToOne
	private Manager mng;
	
}
