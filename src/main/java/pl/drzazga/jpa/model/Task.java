package pl.drzazga.jpa.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {

	@ManyToOne
	private Project project;
	
	private String name;

	protected Task() {
	}
	
	public Task(String name) {
		this.name = Objects.requireNonNull(name);
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
