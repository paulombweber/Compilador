package teste;

import lexico.LexicalError;
import lexico.Lexico;

import org.junit.Assert;
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
		executeAnalysis(program, "Era esperado main, encontrado: identificador (programa)");
	}
	
	/**
	 * Está falhando, necessário verificar o motivo, acredito que um programa
	 * vazio seria assim
	 */
	@Test
	public void testProgramaTokenSucess() {
		String program = "main end";
		executeAnalysis(program, "");
	}
	
	@Test
	public void testFator1Invalido() {
		String program = "main print(x x ++); end";
		executeAnalysis(program, "Era esperado expressão, encontrado: identificador (x)");
	}
	
	@Test
	public void testFatorInvalido() {
		String program = "main print(x ++); end";
		executeAnalysis(program, "Era esperado expressão, encontrado: )");
	}
	
	@Test
	public void testTermoInvalido() {
		String program = "main print(x * (); end";
		executeAnalysis(program, "Era esperado expressão, encontrado: )");
	}
	
	@Test
	public void testListaExpressoesInvalido() {
		String program = "main integer xpto(integer a) end xpto(a; end";
		executeAnalysis(program, "Era esperado expressão, encontrado: ;");
	}
	
	@Test
	public void testParametrosReaisInvalido() {
		String program = "main integer xpto(integer a) end xpto(,); end";
		executeAnalysis(program, "Era esperado expressão, encontrado: ,");
	}
	
	@Test
	public void testFinalDeArquivoInvalido() {
		String program = "main integer xpto(integer a) end xpto(xpto(a))";
		executeAnalysis(program, "Era esperado \";\", encontrado: final de arquivo");
	}
	
	@Test
	public void testAtribuicaoModulo1Invalido() {
		String program = "main do a + 1; while(true); end";
		executeAnalysis(program, "Era esperado \"=\" ou \"(\", encontrado: +");
	}
	
	@Test
	public void testScan() {
		String program = "main print (\"digite um valor inteiro:\"); scan lado); area = lado * lado; println (area); end";
		executeAnalysis(program, "Era esperado \"(\", encontrado: identificador (lado)");
	}
	
	private void executeAnalysis(final String program, final String message){
		try {
			lexico.setInput(program);
			sintatico.parse(lexico, semantico);
			Assert.assertTrue(message.isEmpty());
		} catch (SyntaticError e) {
			Assert.assertEquals(message, e.getMessage());
		} catch (LexicalError | SemanticError e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
}
