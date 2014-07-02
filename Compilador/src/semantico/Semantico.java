package semantico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import comum.Constants;
import comum.Token;
import comum.Variavel;

public class Semantico implements Constants {

	private Stack<TipoDado> pilha = new Stack<>();
	private StringBuilder codigo = new StringBuilder();
	private List<String> ids = new ArrayList<>();
	private Map<String, Variavel> variaveis = new HashMap<>();
	private TipoDado tipoTemp;
	private Stack<String> ifs = new Stack<>();

	private static final String CMD_ADD = "add";
	private static final String CMD_SUB = "sub";
	private static final String CMD_MUL = "mul";
	private static final String CMD_DIV = "div";
	private static final String CMD_INT = "ldc.i8 ";
	private static final String CMD_FLOAT = "ldc.r8 ";
	private static final String CMD_STRING = "ldstr ";
	private static final String CMD_MENOR = "clt";
	private static final String CMD_MAIOR = "cgt";
	private static final String CMD_IGUAL = "ceq";
	private static final String CMD_TRUE = "ldc.i4.1";
	private static final String CMD_FALSE = "ldc.i4.0";
	private static final String CMD_OR = "or";
	private static final String CMD_AND = "and";
	private static final String CMD_XOR = "xor";
	private static final String CMD_NOT = CMD_TRUE + "\n" + CMD_XOR;
	private static final String CMD_WRITE_INTEGER = "call void [mscorlib]System.Console::Write(int64)";
	private static final String CMD_WRITE_FLOAT = "call void [mscorlib]System.Console::Write(float64)";
	private static final String CMD_WRITE_BOOLEAN = "call void [mscorlib]System.Console::Write(bool)";
	private static final String CMD_WRITE_STRING = "call void [mscorlib]System.Console::Write(string)";
	private static final String CMD_VAR_INT = "int64";
	private static final String CMD_VAR_FLOAT = "float64";
	private static final String CMD_VAR_BOOL = "bool";
	private static final String CMD_VAR_STRING = "string";
	private static final String CMD_BRFALSE = "brfalse ";
	private static final String CMD_BR = "br ";	

	private static final String NOME_PROGRAMA = "<#NOME_PROGRAMA#>";
	
	public String getCodigo(String nomePrograma) {
		return codigo.toString().replaceAll(NOME_PROGRAMA, nomePrograma);
	}

	public void executeAction(int action, Token token) throws SemanticError {
		System.out.println("Ação #" + action + ", Token: " + token
				+ ", Lexema: " + (token == null ? "null" : token.getLexeme()));

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
				acao20(token);
				break;
			case 21:
				acao21();
				break;
			case 22:
				acao22(token);
				break;
			case 23:
				acao23();
				break;
			case 24:
				acao24(token);
				break;
			case 25:
				acao25(token);
				break;
			case 26:
				acao26();
				break;
			case 27:
				acao27();
				break;
			case 28:
				acao28(token);
				break;
			case 29:
				acao29();
				break;
			case 30:
				acao30();
				break;
			case 31:
				acao31();
				break;
			case 32:
				acao32();
				break;
			case 33:
				acao33();
				break;
			case 34:
				acao34();
				break;
			case 35:
				acao35();
				break;				
			}
		} catch (Exception e) {
			throw new SemanticError(e.getMessage(), token.getLine(),
					token.getPosition());
		}
	}

	private void adiciona(String comando) {
		codigo.append(comando + "\n");
	}
	
	private String retornaTipo(TipoDado tipo){
		switch (tipo) {
		case INTEGER:
			return CMD_VAR_INT;
		case FLOAT:
			return CMD_VAR_FLOAT;
		case BOOLEAN:
			return CMD_VAR_BOOL;
		case STRING:
			return CMD_VAR_STRING;
		}
		return null;
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
			adiciona(CMD_NOT);
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
		case STRING:
			adiciona(CMD_WRITE_STRING);
			break;
		}
	}

	private void acao15() {
		adiciona(".assembly extern mscorlib{}");
		adiciona(".assembly " + NOME_PROGRAMA + "{}");
		adiciona(".module " + NOME_PROGRAMA + ".exe");
		adiciona("");
		adiciona(".class public " + NOME_PROGRAMA + " {");
		adiciona("  .method public static void _principal ()");
		adiciona("  {");
		adiciona("     .entrypoint");
	}

	private void acao16() {
		adiciona("     ret");
		adiciona("  }");
		adiciona("}");
	}

	private void acao17() {
		adiciona(CMD_STRING + "\"\\n\"");
		adiciona(CMD_WRITE_STRING);
	}

	private void acao18() {
		adiciona(CMD_OR);
	}

	private void acao19() {
		adiciona(CMD_AND);
	}

	private String operador;

	private void acao20(Token token) {
		operador = token.getLexeme().trim();
	}

	private void acao21() throws SemanticError {
		TipoDado tipo1 = pilha.pop();
		TipoDado tipo2 = pilha.pop();

		if (tipo1 == tipo2) {
			switch (operador) {
			case "==":
				adiciona(CMD_IGUAL);
				break;
			case "!=":
				adiciona(CMD_IGUAL);
				adiciona(CMD_NOT);
				break;
			case "<":
				adiciona(CMD_MENOR);
				break;
			case "<=":
				adiciona(CMD_MAIOR);
				adiciona(CMD_FALSE);
				adiciona(CMD_IGUAL);
				break;
			case ">":
				adiciona(CMD_MAIOR);
				break;
			case ">=":
				adiciona(CMD_MENOR);
				adiciona(CMD_FALSE);
				adiciona(CMD_IGUAL);
				break;
			default:
				throw new SemanticError("Operador inválido.");
			}
			pilha.push(TipoDado.BOOLEAN);
		} else {
			throw new SemanticError("Dados incompatíveis.");
		}
	}

	private void acao22(Token token) {
		pilha.push(TipoDado.STRING);
		adiciona(CMD_STRING + token.getLexeme());
	}

	private void acao23() {
		// TODO
	}

	private void acao24(Token token) {
		tipoTemp = TipoDado.fromString(token.getLexeme());
	}

	private void acao25(Token token) {
		if (!ids.contains(token.getLexeme())) {
			ids.add(token.getLexeme());
		}
	}

	private void acao26() throws SemanticError {
		for (String id : ids) {
			if (!variaveis.containsKey(id)) {
				variaveis.put(id, new Variavel(tipoTemp, id));
			} else {
				throw new SemanticError("Identificador (" + id
						+ ") foi redeclarado.");
			}

			String xMsg = "";
			xMsg = retornaTipo(tipoTemp);
			adiciona(".locals (" + xMsg + " " + id + ")");
		}
		
		ids.clear();
		tipoTemp = null;
	}

	private void acao27() throws SemanticError {
		/**
		 * Para i = 1 ate lista.size
		 * id = lista.retira
		 * Se não tabelaSimbolos.existe(ID)
		 *   Erro semantico
		 * fim
		 * Verificar se não é id de "programa"
		 * Tipo = TabelaSimbolo.RecuperaTipo(id)  
		 * codigo.adiciona("Call string.....readline()")
		 * codigo.adiciona("Call string.....Parse(String)")//Só é necessario se não for String
		 * codigo.adicina(stloc id)
		 * fim
		 */
		for (String id : ids) {
			if (!variaveis.containsKey(id)) {
				throw new SemanticError("Identificador (" + id
						+ ") não declarado.");
			}
			String tipo = retornaTipo(variaveis.get(id).getTipo());
			adiciona("call string [mscorlib]System.Console::ReadLine()");			
			if(tipo != "string"){
				String lib;
				switch (variaveis.get(id).getTipo()) {
					case INTEGER:
						lib = "Int64";
						break;
					case FLOAT:
						lib = "Double";
						break;
					default:
						lib = tipo;
				}		
				
				adiciona("call "+ tipo +" [mscorlib]System."+ lib +"::Parse(string)");	
			}
			adiciona("stloc " + id);
		}
		
		ids.clear();
	}

	private void acao28(Token token) throws SemanticError {
		/**
		 * id = token.getLexema
		 * Se não TabelaSimbolo.existe(id)
		 *   Erro semantico
		 * fim
		 * \\Verifica se não é id de "Programa"
		 * Tipo = TabelaSimbolo.recuperaTipo(id)
		 * pilha.empilha(tipo)
		 * codigo.adiciona(ldLoc id)
		 */
		String id = token.getLexeme();
		if(!variaveis.containsKey(id)){
			throw new SemanticError("Identificado " + id
					+ " foi redeclarado.");
		}
		pilha.push(variaveis.get(id).getTipo());
		adiciona("ldloc " + id);
		
	}

	private void acao29() throws SemanticError {
		/**
		 * id = lista.retira
		 * Se não tabelaSimbolo.existe(id)
		 *   erro semantico
		 * fim
		 * \\Verifica se não é id de "Programa"
		 *\\ Tipo da expressao
		 *   tipo1=pilha.desenpilha
		 *\\tipo id
		 *  tipo2 = tabelaSimbolo.recuperaTipo(id)
		 *  
		 *  Se tipo1 != tipo2
		 *    erro semantico
		 *  fim
		 *  codigo.adiciona(stloc id).  
		 *   
		 */
		for(String id: ids){
			if(!variaveis.containsKey(id)){
				throw new SemanticError("Identificado " + id
						+ " foi redeclarado.");
			}
			TipoDado tipo1 = pilha.pop();
			TipoDado tipo2 = variaveis.get(id).getTipo();
			if(tipo1 != tipo2){
				throw new SemanticError("Tipo incompativel " + tipo1
						+ " com " + tipo2);
			}
			adiciona("stloc " + id);
		}
	}

	private void acao30() {
		// TODO
	}

	private void acao31() throws SemanticError {
		TipoDado tipo = pilha.pop();
		if (tipo != TipoDado.BOOLEAN){
			throw new SemanticError("Tipo incompativel, esperado: " + TipoDado.BOOLEAN + ", encontrado: " + tipo);
		}
		int first = ifs.size() * 2 + 1;
		String label1 = "L" + first;
		String label2 = "L" + first++;
		ifs.push(label1);
		ifs.push(label2);
		adiciona(CMD_BRFALSE + label1);
	}
	
	private void acao32() {
		String label = ifs.pop();
		adiciona(label + ": ");
	}
	
	private void acao33() {
		String label = ifs.pop();
		adiciona(CMD_BR + label);
		adiciona(label + ": ");
	}
	
	private void acao34() {
		// TODO
	}
	
	private void acao35() {
		// TODO
	}
}
