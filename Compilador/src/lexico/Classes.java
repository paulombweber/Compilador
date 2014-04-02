package lexico;

public enum Classes {
	;

	public static String get(int id) {
		return values[id];
	}
	
	private static String[] values = new String[]{
	"EPSILON", "DOLLAR", "identificador", "constanteInteger", "constanteFloat", "constanteString",
	"símbolo especial", "símbolo especial", "símbolo especial", "símbolo especial", "símbolo especial",
	"símbolo especial", "símbolo especial", "símbolo especial", "símbolo especial", "símbolo especial",
	"símbolo especial", "símbolo especial", "símbolo especial", "símbolo especial", "símbolo especial",
	"símbolo especial", "símbolo especial", "símbolo especial",
	"palavra reservada", "palavra reservada", "palavra reservada", "palavra reservada", "palavra reservada",
	"palavra reservada", "palavra reservada", "palavra reservada", "palavra reservada", "palavra reservada",
	"palavra reservada", "palavra reservada", "palavra reservada", "palavra reservada", "palavra reservada", "palavra reservada"};
}
