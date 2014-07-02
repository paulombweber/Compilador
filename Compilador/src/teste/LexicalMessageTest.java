package teste;

import lexico.LexicalError;
import lexico.Lexico;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LexicalMessageTest {
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
	
	@Test
	public void test01() throws LexicalError {
		expected.expect(LexicalError.class);
		expected.expectMessage("Símbolo inválido");
		
		StringBuilder builder = new StringBuilder();
		builder.append("main ");
		builder.append(" global integer área; ");
		builder.append(" ");
		builder.append(" scan lado ); ");
		builder.append(" area = lado * lado ; ");
		builder.append(" println ( area ); ");
		builder.append("end ");			
		
    	Lexico lexico = new Lexico();
    	lexico.setInput(builder.toString());
    	while (lexico.nextToken() != null) {
    		
    	}    	    			
	}

}
