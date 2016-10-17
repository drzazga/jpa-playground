package pl.drzazga.jpa.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;

import pl.drzazga.jpa.core.PersistenceManager;

public class ProjectTest {
	
	@Test(expected = NullPointerException.class)
	public void NewProject_WhenSetNameAsNull_ThrowsExceptions() {
		new Project(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void NewProject_WhenSetStartDateAfterEndDate_ThrowsExceptions() {
		Project project = new Project("Spacecraft");
		project.setDateRange(LocalDate.now().plusMonths(1), LocalDate.now());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void NewProject_WhenSetStartDateAsNull_ThrowsExceptions() {
		Project project = new Project("Spacecraft");
		project.setDateRange(null, LocalDate.now().plusMonths(1));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void NewProject_WhenSetBudgetBelowZero_ThrowsExceptions() {
		Project project = new Project("Spacecraft");
		project.setBudget(BigDecimal.valueOf(-1L));
	}
	
	@Test
	public void Project_WhenPersist_ShouldHaveId() {
		Project project = new Project("Spacecraft");
		project.setBudget(new BigDecimal(100_000_00));
		project.setDateRange(LocalDate.now(), LocalDate.now().plusMonths(1));
		persist(project);
		Assert.assertNotNull(project.getId());
	}
	
	private void persist(Project project) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		em.persist(project);
		em.getTransaction().commit();
		em.close();
	}
}
