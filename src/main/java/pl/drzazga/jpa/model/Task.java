package pl.drzazga.jpa.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Project project;
	
	private String name;

	protected Task() {
	}
	
	public Task(String name) {
		this.name = Objects.requireNonNull(name);
	}
	
	public Long getId() {
		return id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getName() {
		return name;
	}
}
