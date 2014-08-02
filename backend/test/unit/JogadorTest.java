package unit;

import models.Jogador;

import org.junit.Before;
import org.junit.Test;

import utils.DatabaseCleaner;
import builder.JogadorBuilder;

public class JogadorTest {

	public static class SalvarAtualizarRemoverJogador extends DefaultUnitTest {
		
		private static Jogador jogador1,jogador2;
		
		@Before
		public void before() {
			
			DatabaseCleaner.cleanAll();
			commitTransaction();
			
			jogador1 = new JogadorBuilder().padrao().build();
			jogador2 = new JogadorBuilder().padrao().build();		
			
		}
		
		@Test
		public void deveSalvar() {
			
			
			
		}
		
	}
	
}
