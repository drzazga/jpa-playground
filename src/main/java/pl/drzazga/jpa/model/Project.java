package pl.drzazga.jpa.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Project extends BaseEntity {

	private String name;
	
	private LocalDate start;
	
	private LocalDate end;
	
	private BigDecimal budget;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<User> users;
	
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Task> tasks;

	protected Project() {
	}
	
	public Project(String name) {
		this.name = Objects.requireNonNull(name);
	}
	
	public String getName() {
		return name;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		if (budget.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Budget shouldn't be below 0!");
		}
		this.budget = budget;
	}

	public LocalDate getStart() {
		return start;
	}

	public LocalDate getEnd() {
		return end;
	}
	
	public void setDateRange(LocalDate start, LocalDate end) {
		if (start == null) {
			throw new IllegalArgumentException("Start date shouldn't be null!");
		} else if (end != null && start.isAfter(end)) {
			throw new IllegalArgumentException("Start date should be before end date!");
		}
		
		this.start = start;
		this.end = end;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
}
