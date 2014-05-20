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
	
	@Test
	public void testProgramaTokenFailed() {
		String program = "programa xpto";
		try {
			executeAnalysis(program);
			Assert.fail("Uma mensagem de erro deveria ter sido exibida");
		} catch (SyntaticError e) {
			Assert.assertEquals("Era esperado main, encontrado: programa", e.getMessage());
		} catch (LexicalError | SemanticError e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * Está falhando, necessário verificar o motivo, acredito que um programa
	 * vazio seria assim
	 */
	@Test
	public void testProgramaTokenSucess() {
		String program = "main xptoc end";
		try {
			executeAnalysis(program);
		} catch (SyntaticError | LexicalError | SemanticError e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	private void executeAnalysis(final String program) throws LexicalError, SyntaticError, SemanticError {
		lexico.setInput(program);
		sintatico.parse(lexico, semantico);
	}
}
