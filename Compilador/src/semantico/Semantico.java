package semantico;

import java.util.Stack;

import comum.Constants;
import comum.Token;

public class Semantico implements Constants {
	
	private Stack<Integer> pilha = new Stack<>();
	private StringBuilder codigo = new StringBuilder();

	private static final int TIPO_INTEGER = 5;
	private static final int TIPO_FLOAT = 6;

	private static final String CMD_ADD = "add";
	private static final String CMD_SUB = "sub";
	private static final String CMD_MUL = "mul";
	private static final String CMD_DIV = "div";
	private static final String CMD_INT = "ldc.i8 ";
	private static final String CMD_FLOAT = "ldc.r8 ";

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
			}
		} catch (Exception e) {
			throw new SemanticError(e.getMessage(), token.getLine(), token.getPosition());
		}
	}

	private void adiciona(String comando) {
		codigo.append(comando + "\n");
	}

	private void acao01() {
		int tipo1 = pilha.pop();
		int tipo2 = pilha.pop();

		if ((tipo1 == TIPO_FLOAT) || (tipo2 == TIPO_FLOAT)) {
			pilha.push(TIPO_FLOAT);
		} else {
			pilha.push(TIPO_INTEGER);
		}

		adiciona(CMD_ADD);
	}

	private void acao02() {
		int tipo1 = pilha.pop();
		int tipo2 = pilha.pop();

		if ((tipo1 == TIPO_FLOAT) || (tipo2 == TIPO_FLOAT)) {
			pilha.push(TIPO_FLOAT);
		} else {
			pilha.push(TIPO_INTEGER);
		}

		adiciona(CMD_SUB);
	}

	private void acao03() {
		int tipo1 = pilha.pop();
		int tipo2 = pilha.pop();

		if ((tipo1 == TIPO_FLOAT) || (tipo2 == TIPO_FLOAT)) {
			pilha.push(TIPO_FLOAT);
		} else {
			pilha.push(TIPO_INTEGER);
		}

		adiciona(CMD_MUL);
	}

	private void acao04() throws SemanticError {
		int tipo1 = pilha.pop();
		int tipo2 = pilha.pop();

		if (tipo1 == tipo2) {
			pilha.push(tipo1);
			adiciona(CMD_DIV);
		} else {
			throw new SemanticError("Dados incompatíveis para divisão.");
		}
	}
	
	private void acao05(Token token) {
		pilha.push(TIPO_INTEGER);
		
		adiciona(CMD_INT + token.getLexeme());
	}
	
	private void acao06(Token token) {
		pilha.push(TIPO_FLOAT);
		
		adiciona(CMD_FLOAT + token.getLexeme());
	}	
}
