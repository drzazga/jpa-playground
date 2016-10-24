package pl.drzazga.jpa.model;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.RollbackException;

import org.junit.Assert;
import org.junit.Test;

import pl.drzazga.jpa.core.PersistenceManager;

public class UserTest {

	@Test
	public void test() {
		User user = new User("test0@drzazga.pl");
		persist(user);
		Assert.assertNotNull(user.getId());
	}
	
	@Test
	public void RegularUser_WhenGetByEmail_GetsOne() {
		String email = "test2@drzazga.pl";
		User user1 = new User(email);
		persist(user1);
		Optional<User> user2 = getByEmail(email);
		Assert.assertTrue(user2.isPresent());
	}
	
	@Test(expected = OptimisticLockException.class)
	public void TwoTheSameUsers_WhenMergeOneByOne_ThrowsOptimisticLockException() {
		String email = "test3@drzazga.pl";
		User user = new User(email);
		persist(user);
		
		User user2 = getByEmail(email).orElse(new User());
		User user3 = getByEmail(email).orElse(new User());
		
		user2.setFirstName("John");
		user3.setFirstName("George");
		
		merge(user2);
		merge(user3);
	}
	
	@Test(expected = RollbackException.class)
	public void UsersWithTheSameEmail_WhenPersist_ThorwsConstraintViolationException() {
		User user1 = new User("test@drzazga.pl");
		User user2 = new User("test@drzazga.pl");
		
		persist(user1);
		persist(user2);
	}
	
	/*@Test
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
	*/
	
	public Optional<User> getByEmail(String email) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		Optional<User> user = em.createNamedQuery("byEmail", User.class)
								.setParameter("email", email)
								.getResultList()
								.stream()
								.findFirst();
		em.getTransaction().commit();
		em.close();
		return user;
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
