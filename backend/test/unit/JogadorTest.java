package unit;

import models.Jogador;

import org.junit.Before;
import org.junit.Test;

import exceptions.ValidationException;
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
			
			assertNotNull(jogador1.save());
			
		}
		
		@Test
		public void deveAtualizar() {
			
			jogador1.save();
				
			String nomeAntigo = jogador1.nome;
			
			assertNotNull(jogador1.update("Danilo fonte"));
			
			assertFalse(nomeAntigo.equals(jogador1.nome));
		}
		
		@Test(expected=ValidationException.class)
		public void naoDeveSalvarComNomeJaExistente(){
			
			jogador1.save();
			
			jogador2.save();
			
		}
		
		@Test(expected=ValidationException.class)
		public void naoDeveAtualizarComNomeEmBranco() {
			
			jogador1.save();
			
			jogador1.update(null);
			
		}
		
	}
	
}
