package unit;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import play.data.validation.Validation;
import play.db.jpa.JPA;
import play.mvc.Http;
import play.test.UnitTest;

public class DefaultUnitTest extends UnitTest {

	private static final String TEST_SESSION_ID = "TEST_SESSION_ID";

	@Test
	public void doNothing() {
		assertTrue(true);
	}

	@Before
	public void enableFilters() {
		((Session) JPA.em().getDelegate()).enableFilter("ativosGeoCar")
				.setParameter("ativo", true);
		((Session) JPA.em().getDelegate()).enableFilter("ativosAnalise")
				.setParameter("ativo", true);
	}

	@Before
	public void cleanValidationErros() {

		Validation.current().clear();
	}

	@SuppressWarnings("deprecation")
	public void assertSameDay(Date date1, Date date2) {

		assertEquals(date1.getDay(), date2.getDay());
		assertEquals(date1.getMonth(), date2.getMonth());
		assertEquals(date1.getYear(), date2.getYear());
	}

	public void assertNotEmpty(Collection collection) {

		assertNotNull(collection);
		assertFalse(collection.isEmpty());
	}

	public static void commitTransaction() {

		if (JPA.isInsideTransaction()) {
			JPA.em().getTransaction().commit();
			JPA.em().getTransaction().begin();
		}
	}

}
