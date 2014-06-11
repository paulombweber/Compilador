package semantico;

import java.util.Stack;

import comum.Constants;
import comum.Token;

public class Semantico implements Constants {
	
	private Stack<TipoDado> pilha = new Stack<>();
	private StringBuilder codigo = new StringBuilder();

	private static final String CMD_ADD = "add";
	private static final String CMD_SUB = "sub";
	private static final String CMD_MUL = "mul";
	private static final String CMD_DIV = "div";
	private static final String CMD_INT = "ldc.i8 ";
	private static final String CMD_FLOAT = "ldc.r8 ";
	private static final String CMD_MENOR = "clt";
	private static final String CMD_MAIOR = "cgt";
	private static final String CMD_IGUAL = "ceq";
	private static final String CMD_TRUE = "ldc.i4.1";
	private static final String CMD_FALSE = "ldc.i4.0";
	private static final String CMD_XOR = "xor";
	private static final String CMD_WRITE_INTEGER = "call void [mscorlib]System.Console::Write(int64)";
	private static final String CMD_WRITE_FLOAT = "call void [mscorlib]System.Console::Write(float64)";
	private static final String CMD_WRITE_BOOLEAN = "call void [mscorlib]System.Console::Write(bool)";

	public void executeAction(int action, Token token) throws SemanticError {
		System.out.println("Ação #" + action + ", Token: " + token + ", Lexema: " + (token == null ? "null" : token.getLexeme()));

		try {
			switch (action) {
				case 1:
					acao01();
					break;
				case 2:
					acao02();
					break;
				case 3:
					acao03();
					break;
				case 4:
					acao04();
					break;
				case 5: 
					acao05(token);
					break;
				case 6:
					acao06(token);
					break;					
				case 7:
					acao07();
					break;					
				case 8:
					acao08();
					break;
				case 9:
					acao09();
					break;
				case 10:
					acao10();
					break;
				case 11:
					acao11();
					break;
				case 12:
					acao12();
					break;
				case 13:
					acao13();
					break;
				case 14:
					acao14();
					break;
				case 15:
					acao15();
					break;
				case 16:
					acao16();
					break;
				case 17:
					acao17();
					break;
				case 18:
					acao18();
					break;
				case 19:
					acao19();
					break;
				case 20:
					acao20();
					break;
				case 21:
					acao21();
					break;
				case 22:
					acao22();
					break;
				case 23:
					acao23();
					break;
			}
		} catch (Exception e) {
			throw new SemanticError(e.getMessage(), token.getLine(), token.getPosition());
		}
	}

	private void adiciona(String comando) {
		codigo.append(comando + "\n");
	}

	private void acao01() {
		TipoDado tipo1 = pilha.pop();
		TipoDado tipo2 = pilha.pop();

		if ((tipo1 == TipoDado.FLOAT) || (tipo2 == TipoDado.FLOAT)) {
			pilha.push(TipoDado.FLOAT);
		} else {
			pilha.push(TipoDado.INTEGER);
		}

		adiciona(CMD_ADD);
	}

	private void acao02() {
		TipoDado tipo1 = pilha.pop();
		TipoDado tipo2 = pilha.pop();

		if ((tipo1 == TipoDado.FLOAT) || (tipo2 == TipoDado.FLOAT)) {
			pilha.push(TipoDado.FLOAT);
		} else {
			pilha.push(TipoDado.INTEGER);
		}

		adiciona(CMD_SUB);
	}

	private void acao03() {
		TipoDado tipo1 = pilha.pop();
		TipoDado tipo2 = pilha.pop();

		if ((tipo1 == TipoDado.FLOAT) || (tipo2 == TipoDado.FLOAT)) {
			pilha.push(TipoDado.FLOAT);
		} else {
			pilha.push(TipoDado.INTEGER);
		}

		adiciona(CMD_MUL);
	}

	private void acao04() throws SemanticError {
		TipoDado tipo1 = pilha.pop();
		TipoDado tipo2 = pilha.pop();

		if (tipo1 == tipo2) {
			pilha.push(tipo1);
			adiciona(CMD_DIV);
		} else {
			throw new SemanticError("Dados incompatíveis para divisão.");
		}
	}
	
	private void acao05(Token token) {
		pilha.push(TipoDado.INTEGER);
		
		adiciona(CMD_INT + token.getLexeme());
	}
	
	private void acao06(Token token) {
		pilha.push(TipoDado.FLOAT);
		
		adiciona(CMD_FLOAT + token.getLexeme());
	}
	
	private void acao07() throws SemanticError {
		TipoDado tipo = pilha.peek();
		
		if ((tipo == TipoDado.INTEGER) || (tipo == TipoDado.FLOAT)) {
			adiciona(CMD_INT + "-1");
			adiciona(CMD_MUL);
		} else {
			throw new SemanticError("Dados incompatíveis."); 
		}				
	}
	
	private void acao08() throws SemanticError {
		TipoDado tipo1 = pilha.pop();
		TipoDado tipo2 = pilha.pop();
		
		if (tipo1 == tipo2) {
			pilha.push(TipoDado.BOOLEAN);
			adiciona(CMD_MENOR);
		} else {
			throw new SemanticError("Dados incompatíveis.");
		}		
	}
	
	private void acao09() throws SemanticError {
		TipoDado tipo1 = pilha.pop();
		TipoDado tipo2 = pilha.pop();
		
		if (tipo1 == tipo2) {
			pilha.push(TipoDado.BOOLEAN);
			adiciona(CMD_MAIOR);
		} else {
			throw new SemanticError("Dados incompatíveis.");
		}		
	}
	
	private void acao10() throws SemanticError {
		TipoDado tipo1 = pilha.pop();
		TipoDado tipo2 = pilha.pop();
		
		if (tipo1 == tipo2) {
			pilha.push(TipoDado.BOOLEAN);
			adiciona(CMD_IGUAL);
		} else {
			throw new SemanticError("Dados incompatíveis.");
		}		
	}
	
	private void acao11() {
		pilha.add(TipoDado.BOOLEAN);
		adiciona(CMD_TRUE);
	}
	
	private void acao12() {
		pilha.add(TipoDado.BOOLEAN);
		adiciona(CMD_FALSE);
	}	
	
	private void acao13() throws SemanticError {
		TipoDado tipo = pilha.pop();
		
		if (tipo == TipoDado.BOOLEAN) {
			pilha.push(TipoDado.BOOLEAN);
			adiciona(CMD_TRUE);
			adiciona(CMD_XOR);
		} else {
			throw new SemanticError("Dados incompatíveis.");
		}
	}
	
	private void acao14() {
		TipoDado tipo = pilha.pop();
		
		switch (tipo) {
			case INTEGER: 
				adiciona(CMD_WRITE_INTEGER);
				break;
			case FLOAT:
				adiciona(CMD_WRITE_FLOAT);
				break;
			case BOOLEAN:
				adiciona(CMD_WRITE_BOOLEAN);
				break;
		}
	}
	
	private void acao15() {
		//TODO
	}
	
	private void acao16() {
		//TODO
	}
	
	private void acao17() {
		//TODO
	}
	
	private void acao18() {
		//TODO
	}
	
	private void acao19() {
		//TODO
	}
	
	private void acao20() {
		//TODO
	}
	
	private void acao21() {
		//TODO
	}
	
	private void acao22() {
		//TODO
	}
	
	private void acao23() {
		//TODO
	}
}
