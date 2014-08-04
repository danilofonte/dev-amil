package utils;

import play.db.jpa.JPA;

public class TransactionUtil {

	public static void commitTransaction() {

		if (JPA.isInsideTransaction()) {
			JPA.em().getTransaction().commit();
			JPA.em().getTransaction().begin();
		}
	}

}
