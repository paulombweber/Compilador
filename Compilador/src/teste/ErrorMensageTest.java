package teste;

import lexico.LexicalError;
import lexico.Lexico;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ErrorMensageTest {
	@Rule 
	public ExpectedException expected = ExpectedException.none();
	
	@Test
    public void testStringIncompleta() throws LexicalError {
		expected.expect(LexicalError.class);
		expected.expectMessage("Constante literal não finalizada");
		
		String string = "\"String incompleta";
    	Lexico lexico = new Lexico();
    	lexico.setInput(string);
    	lexico.nextToken();    	
    	
    }

}
