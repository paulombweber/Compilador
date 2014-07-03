package teste;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import lexico.LexicalError;
import lexico.Lexico;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import semantico.SemanticError;
import semantico.Semantico;
import sintatico.Sintatico;
import sintatico.SyntaticError;

public class SemanticTest {
	
	@Rule 
	public ExpectedException expected = ExpectedException.none();	
	
	private Semantico compilar(String codigo) throws LexicalError, SyntaticError, SemanticError {
		Lexico lexico = new Lexico();
		Sintatico sintatico = new Sintatico();
		Semantico semantico = new Semantico();
		lexico.setInput(codigo);
		sintatico.parse(lexico, semantico);
		return semantico;
	}
	
	private String gerarCodigoObjeto(Semantico semantico, String nomePrograma) {
		return semantico.getCodigo(nomePrograma);		
	}
	
	private File gerarExecutavel(File dir, String codigo, String nome) throws IOException, InterruptedException {		
		File ilFile = new File(dir, nome + ".il");
		FileOutputStream fos = new FileOutputStream(ilFile);
		try {
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			try {
				bos.write(codigo.getBytes());
				bos.flush();
			} finally {
				bos.close();
			}
		} finally {
			fos.close();
		}
		
		File exeFile = new File(dir, nome + ".exe");
		
		Process process = Runtime.getRuntime().exec("C:\\Windows\\Microsoft.NET\\Framework\\v4.0.30319\\ilasm.exe /OUTPUT=\"" + 
				exeFile.getCanonicalPath() + "\" \"" + ilFile.getCanonicalPath() + "\"");
		
		int exitCode = process.waitFor();
		if (exitCode != 0) {
			throw new RuntimeException("Erro ao compilar o programa: " + ilFile.getCanonicalPath() + ".\n" + codigo);
		}
		
		return exeFile;
	}
	
	private Process executar(File executavel) throws IOException, InterruptedException {
		Process processo = Runtime.getRuntime().exec(executavel.getCanonicalPath());
		Thread.sleep(1000);
		return processo;
	}
	
	private void assertSaida(String saida, String teste) {
		Assert.assertTrue("Inicio da sa�da esperada: " + teste + "\nSa�da encontrada: " + saida, saida.startsWith(teste));
	}
	
	@Test
	public void test01() throws LexicalError, SyntaticError, SemanticError, IOException, InterruptedException {
		StringBuilder builder = new StringBuilder();
		builder.append("main ");
		builder.append("global integer area, lado; ");
		builder.append("scan ( lado ); ");
		builder.append("area = lado * lado; ");
		builder.append("println ( area ); ");
		builder.append("end");
		
		String nome = "test01";
		Semantico semantico = compilar(builder.toString());
		String codigo = gerarCodigoObjeto(semantico, nome);
		File dir = new File(".");
		File exe = gerarExecutavel(dir, codigo, nome);
		Process processo = executar(exe);
		try {
			OutputStream out = processo.getOutputStream();
			InputStream in = processo.getInputStream();
			out.write("4\n".getBytes());
			out.flush();
			byte[] bytes = new byte[1024];
			in.read(bytes);
			String area = new String(bytes);
			Assert.assertTrue(area.startsWith("16"));
		} finally {
			processo.destroy();
		}
	}
	
	@Test
	public void test02() throws LexicalError, SyntaticError, SemanticError, IOException, InterruptedException {
		StringBuilder builder = new StringBuilder();
		builder.append("main ");
		builder.append("global integer lado, area = 0; ");
		builder.append("scan ( lado ); ");
		builder.append("if ( lado > 1 ) ");
		builder.append("area = lado * lado; ");
		builder.append("print ( area ); ");
		builder.append("else ");
		builder.append("print (\"Valor inv�lido\"); ");
		builder.append("end; ");
		builder.append("end");
		
		String nome = "test02";
		Semantico semantico = compilar(builder.toString());
		String codigo = gerarCodigoObjeto(semantico, nome);
		File dir = new File(".");
		File exe = gerarExecutavel(dir, codigo, nome);
		Process processo = executar(exe);
		try {
			OutputStream out = processo.getOutputStream();
			InputStream in = processo.getInputStream();
			out.write("5\n".getBytes());
			out.flush();
			byte[] bytes = new byte[1024];
			in.read(bytes);
			String area = new String(bytes);
			Assert.assertTrue(area.startsWith("5"));
		} finally {
			processo.destroy();
		}
	}
	
	@Test
	public void test03() throws LexicalError, SyntaticError, SemanticError, IOException, InterruptedException {
		StringBuilder builder = new StringBuilder();
		builder.append("main ");
		builder.append("global integer lado, area = 0; ");
		builder.append("scan ( lado ); ");
		builder.append("if ( lado > 1 ) ");
		builder.append("area = lado * lado; ");
		builder.append("print ( area ); ");
		builder.append("else ");
		builder.append("print (\"Valor inv�lido\"); ");
		builder.append("end; ");
		builder.append("end");
		
		String nome = "test03";
		Semantico semantico = compilar(builder.toString());
		String codigo = gerarCodigoObjeto(semantico, nome);
		File dir = new File(".");
		File exe = gerarExecutavel(dir, codigo, nome);
		Process processo = executar(exe);
		try {
			OutputStream out = processo.getOutputStream();
			InputStream in = processo.getInputStream();
			out.write("1\n".getBytes());
			out.flush();
			byte[] bytes = new byte[1024];
			in.read(bytes);
			String area = new String(bytes);
			Assert.assertTrue(area.startsWith("Valor inv�lido"));
		} finally {
			processo.destroy();
		}
	}		
	
	@Test
	public void test04() throws LexicalError, SyntaticError, SemanticError, IOException, InterruptedException {
		expected.expect(SemanticError.class);
		expected.expectMessage("Identificador (lado) n�o declarado.");
		
		StringBuilder builder = new StringBuilder();
		builder.append("main ");
		builder.append(" global integer area; ");
		builder.append(" ");
		builder.append(" scan ( lado ); ");
		builder.append(" area = lado * lado ; ");
		builder.append(" println ( area ); ");
		builder.append("end");
		
		compilar(builder.toString());
	}
	
	@Test
	public void test05() throws LexicalError, SyntaticError, SemanticError, IOException, InterruptedException {
		StringBuilder builder = new StringBuilder();
		builder.append("main ");
		builder.append("if ( 1 > 0 ) ");
		builder.append("print ( \"true\" ); ");
		builder.append("else ");
		builder.append("print (\"false\"); ");
		builder.append("end; ");
		builder.append("print ( \"end\" ); ");
		builder.append("end");
		
		String nome = "test05";
		Semantico semantico = compilar(builder.toString());
		String codigo = gerarCodigoObjeto(semantico, nome);
		File dir = new File(".");
		File exe = gerarExecutavel(dir, codigo, nome);
		Process processo = executar(exe);
		try {
			InputStream in = processo.getInputStream();
			byte[] bytes = new byte[1024];
			in.read(bytes);
			String saida = new String(bytes);
			assertSaida(saida, "trueend");
		} finally {
			processo.destroy();
		}
	}
	
	@Test
	public void test06() throws LexicalError, SyntaticError, SemanticError, IOException, InterruptedException {
		StringBuilder builder = new StringBuilder();
		builder.append("main ");
		builder.append("if ( 1 > 2 ) ");
		builder.append("print ( \"true\" ); ");
		builder.append("else ");
		builder.append("print (\"false\"); ");
		builder.append("end; ");
		builder.append("print ( \"end\" ); ");
		builder.append("end");
		
		String nome = "test06";
		Semantico semantico = compilar(builder.toString());
		String codigo = gerarCodigoObjeto(semantico, nome);
		File dir = new File(".");
		File exe = gerarExecutavel(dir, codigo, nome);
		Process processo = executar(exe);
		try {
			InputStream in = processo.getInputStream();
			byte[] bytes = new byte[1024];
			in.read(bytes);
			String saida = new String(bytes);
			Assert.assertTrue(saida.startsWith("falseend"));
		} finally {
			processo.destroy();
		}
	}		
	
	@Test
	public void test07() throws LexicalError, SyntaticError, SemanticError, IOException, InterruptedException {
		StringBuilder builder = new StringBuilder();
		builder.append("main ");
		builder.append("if ( 1 > 0 ) ");
		builder.append("print ( \"true\" ); ");
		builder.append("end; ");
		builder.append("print ( \"end\" ); ");
		builder.append("end");
		
		String nome = "test07";
		Semantico semantico = compilar(builder.toString());
		String codigo = gerarCodigoObjeto(semantico, nome);
		File dir = new File(".");
		File exe = gerarExecutavel(dir, codigo, nome);
		Process processo = executar(exe);
		try {
			InputStream in = processo.getInputStream();
			byte[] bytes = new byte[1024];
			in.read(bytes);
			String saida = new String(bytes);
			assertSaida(saida, "trueend");
		} finally {
			processo.destroy();
		}
	}
	
	@Test
	public void test08() throws LexicalError, SyntaticError, SemanticError, IOException, InterruptedException {
		StringBuilder builder = new StringBuilder();
		builder.append("main ");
		builder.append("if ( 1 > 2 ) ");
		builder.append("print ( \"true\" ); ");
		builder.append("end; ");
		builder.append("print ( \"false\" ); ");
		builder.append("print ( \"end\" ); ");
		builder.append("end");
		
		String nome = "test08";
		Semantico semantico = compilar(builder.toString());
		String codigo = gerarCodigoObjeto(semantico, nome);
		File dir = new File(".");
		File exe = gerarExecutavel(dir, codigo, nome);
		Process processo = executar(exe);
		try {
			InputStream in = processo.getInputStream();
			byte[] bytes = new byte[1024];
			in.read(bytes);
			String saida = new String(bytes);
			Assert.assertTrue(saida.startsWith("falseend"));
		} finally {
			processo.destroy();
		}
	}
	
	@Test
	public void test09() throws LexicalError, SyntaticError, SemanticError, IOException, InterruptedException {
		StringBuilder builder = new StringBuilder();
		builder.append("main ");
		builder.append("println ( 1, 2, 3,5, \"a\", \"abc\" ); ");
		builder.append("print ( 1,5 + 1,5 ); ");
		builder.append("print ( \" \", 1,5 + 2 ); ");
		builder.append("end");
		
		String nome = "test09";
		Semantico semantico = compilar(builder.toString());
		String codigo = gerarCodigoObjeto(semantico, nome);
		File dir = new File(".");
		File exe = gerarExecutavel(dir, codigo, nome);
		Process processo = executar(exe);
		try {
			InputStream in = processo.getInputStream();			
			byte[] bytes = new byte[1024];
			String saida = "";
			Thread.sleep(5000);
			
			while (in.read(bytes) > 0) {				
				saida += new String(bytes);
				bytes = new byte[1024];
				Thread.sleep(1000);
			}
			
			assertSaida(saida, "123,5aabc\n3 3,5");
		} finally {
			processo.destroy();
		}
	}	
	
	@Test
	public void test10() throws LexicalError, SyntaticError, SemanticError, IOException, InterruptedException {
		StringBuilder builder = new StringBuilder();
		builder.append("main ");
		builder.append("global integer area = 0; ");
		builder.append("do ");
		builder.append("print (area); ");
		builder.append("area = area + 1; ");
		builder.append("while (area < 5); ");
		builder.append("end");
		
		String nome = "test10";
		Semantico semantico = compilar(builder.toString());
		String codigo = gerarCodigoObjeto(semantico, nome);
		File dir = new File(".");
		File exe = gerarExecutavel(dir, codigo, nome);
		Process processo = executar(exe);
		try {
			InputStream in = processo.getInputStream();			
			byte[] bytes = new byte[1024];
			String saida = "";
			Thread.sleep(5000);
			
			while (in.read(bytes) > 0) {				
				saida += new String(bytes);
				bytes = new byte[1024];
				Thread.sleep(1000);
			}
			
			assertSaida(saida, "01234");
		} finally {
			processo.destroy();
		}
	}
	

}
