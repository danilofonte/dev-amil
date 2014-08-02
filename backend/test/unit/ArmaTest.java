package unit;

import models.Arma;

import org.junit.Before;
import org.junit.Test;

import utils.DatabaseCleaner;
import builder.ArmaBuilder;
import exceptions.ValidationException;

public class ArmaTest {

	public static class SalvarAtualizarRemoverArma extends DefaultUnitTest {

		private static Arma arma1, arma2;

		@Before
		public void before() {

			DatabaseCleaner.cleanAll();
			commitTransaction();

			arma1 = new ArmaBuilder().padrao().build();
			arma2 = new ArmaBuilder().padrao().build();

		}

		@Test
		public void deveSalvar() {

			assertNotNull(arma1.save());

		}

		@Test
		public void deveAtualizar() {

			arma1.save();

			String nomeAntigo = arma1.nome;

			assertNotNull(arma1.update("BAZUKA MK9", 5L));

			assertFalse(nomeAntigo.equals(arma1.nome));
		}

		@Test(expected = ValidationException.class)
		public void naoDeveSalvarComNomeJaExistente() {

			arma1.save();

			arma2.save();

		}

		@Test(expected = ValidationException.class)
		public void naoDeveAtualizarComNomeEmBranco() {

			arma1.save();

			arma1.update(null, null);

		}
		
		@Test(expected=ValidationException.class)
		public void naoDeveAtualizarComComMunicaoNula() {
			
			arma1.save();
			
			arma1.update("FACA",null);
			
		}
		
	}

}
