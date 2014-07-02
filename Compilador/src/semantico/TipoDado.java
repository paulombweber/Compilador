package semantico;

public enum TipoDado {

	INTEGER("integer"), 
	FLOAT("float"), 
	BOOLEAN("boolean"), 
	STRING("string");

	private final String id;

	private TipoDado(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public static TipoDado fromString(String id) {
		for (TipoDado tipo : TipoDado.values()) {
			if (tipo.getId().equalsIgnoreCase(id)) {
				return tipo;
			}
		}

		return null;
	}
}
