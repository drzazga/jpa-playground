package pl.drzazga.jpa.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;

import pl.drzazga.jpa.core.PersistenceManager;

public class ProjectTest {

	@Test
	public void Project_WhenPersist_ShouldHaveId() {
		Project project = new Project();
		project.setProjectName("Spacecraft");
		project.setBudget(new BigDecimal(100_000_00));
		project.setStart(LocalDate.now());
		project.setEnd(LocalDate.now().plusMonths(1));
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
