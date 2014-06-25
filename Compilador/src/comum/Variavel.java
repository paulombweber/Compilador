package comum;

import semantico.TipoDado;

public class Variavel {
	
	private TipoDado	tipo;
	private String		nome;
	
	public Variavel(TipoDado tipo, String nome) {
		this.tipo = tipo;
		this.nome = nome;
	}
	
	public TipoDado getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoDado tipo) {
		this.tipo = tipo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}