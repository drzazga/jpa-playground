package pl.drzazga.jpa.model;

import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "users")
@DynamicInsert
@DynamicUpdate
public class User extends BaseEntity {

	@NotBlank
	@Email
	private String email;
	
	@Size(min = 3)
	private String password;

	@Embedded
	private Address address;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@OneToMany(mappedBy = "user")
	private Set<Task> tasks;
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	protected User() {}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public Stream<Task> getTasks() {
		return tasks == null ? Stream.empty() : tasks.stream();
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
}
