package pl.drzazga.jpa.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {

	@ManyToOne
	private User user;
	
	@NotBlank
	private String name;

	public Task(String name) {
		this.name = name;
	}
	
	protected Task() {}
	
	public String getName() {
		return name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
