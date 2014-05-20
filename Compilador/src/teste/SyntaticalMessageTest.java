package teste;

import junit.framework.Assert;
import lexico.LexicalError;
import lexico.Lexico;

import org.junit.BeforeClass;
import org.junit.Test;

import sintatico.SemanticError;
import sintatico.Semantico;
import sintatico.Sintatico;
import sintatico.SyntaticError;

public class SyntaticalMessageTest {
	
	private static Lexico		lexico;
	private static Sintatico	sintatico;
	private static Semantico	semantico;
	
	@BeforeClass
	public static void beforeClass() {
		lexico = new Lexico();
		sintatico = new Sintatico();
		semantico = new Semantico();
	}
	
	/**
	 * Testa um programa que não iniciar por main
	 */
	@Test
	public void testMainTokenFailed() {
		String program = "programa end";
		executeAnalysis(program, "Era esperado main, encontrado: programa");
	}
	
	/**
	 * Testa um programa 'vazio'
	 */
	@Test
	public void testMainTokenSucess() {
		String program = "main end";
		executeAnalysis(program, "");
	}
	
	@Test
	public void testVariaveisGlobaisVazio() {
		String program = "main global end";
		executeAnalysis(program, "Era esperado tipo, encontrado: end");
	}
	
	@Test
	public void testVariaveisGlobaisComTipoInvalido() {
		String program = "main global xpto end";
		executeAnalysis(program, "Era esperado tipo, encontrado: xpto");
	}
	
	@Test
	public void testVariaveisGlobaisSemIdentificador() {
		String program = "main global integer end";
		executeAnalysis(program, "Era esperado identificador, encontrado: end");
	}
	
	@Test
	public void testVariaveisGlobaisCom1Variavel() {
		String program = "main global integer xpto; end";
		executeAnalysis(program, "");
	}
	
	@Test
	public void testVariaveisGlobaisComMaisVariaveis() {
		String program = "main global integer xpto, cachorro; end";
		executeAnalysis(program, "");
	}
	
	@Test
	public void testVariaveisGlobaisComMaisVariaveisEValor() {
		String program = "main global integer xpto, cachorro = 5; end";
		executeAnalysis(program, "");
	}
	
	@Test
	public void testVariaveisGlobaisComMaisVariaveisEIdentificadorInvalido() {
		String program = "main global integer xpto, integer = 5; end";
		executeAnalysis(program, "Era esperado identificador, encontrado: integer");
	}
	
	/**
	 * Deveria funcionar atribuir uma string para um integer?
	 */
	@Test
	public void testVariaveisGlobaisComMaisVariaveisEValorInvalido() {
		String program = "main global integer xpto, cachorro = \"a\"; end";
		executeAnalysis(program, "");
	}
	
	@Test
	public void testModulo() {
		String program = "main integer metodo (integer xpto) return xpto end end";
		executeAnalysis(program, "");
	}
	
	private void executeAnalysis(final String program, final String expectedMessage) {
		try {
			lexico.setInput(program);
			sintatico.parse(lexico, semantico);
			Assert.assertEquals("Deveria ter gerado uma exceção", "", expectedMessage);
		} catch (SyntaticError e) {
			Assert.assertEquals(expectedMessage, e.getMessage());
		} catch (LexicalError | SemanticError e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
}
