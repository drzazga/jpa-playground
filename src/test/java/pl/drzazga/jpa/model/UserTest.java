package pl.drzazga.jpa.model;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import org.junit.Test;

import pl.drzazga.jpa.core.PersistenceManager;

public class UserTest {

	@Test
	public void testPersistPerformance() {
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			persist(new User("email@email.com", "password"));
		}
		long t2 = System.currentTimeMillis();
		System.out.println(String.format("Total time: %d", t2 - t1));
	}
	
	@Test
	public void UserWithRequiresData_WhenPersist_NoError() {
		persist(new User("email@email.com", "password"));
	}
	
	@Test(expected = RollbackException.class)
	public void UserWithEmptyEmail_WhenPersist_ThrowsException() {
		persist(new User("", "password"));
	}
	
	@Test(expected = RollbackException.class)
	public void UserWithEmptyPassword_WhenPersist_ThrowsException() {
		persist(new User("email@email.com", ""));
	}
	
	@Test(expected = RollbackException.class)
	public void UserWithWrongEmail_WhenPersist_ThrowsException() {
		persist(new User("email", "password"));
	}
	
	private void persist(User user) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}
	
	private void merge(User user) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
		em.close();
	}
}
