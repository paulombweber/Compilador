package comum;

public class Token
{
    private int id;
    private String lexeme;
    private int line;
    private int column;
    private int position;

    public Token(int id, String lexeme, int line, int column, int position)
    {
        this.id = id;
        this.lexeme = lexeme;
        this.line = line;
        this.column = column;
        this.position = position;
    }

    public final int getId()
    {
        return id;
    }

    public final String getLexeme()
    {
        return lexeme;
    }

    public final int getLine()
    {
        return line;
    }
    
    public final int getColumn() {
    	return column;
    }
    
    public final int getPosition() {
    	return position;
    }

    public String toString()
    {
        return id+" ( "+lexeme+" ) @ "+ getLine() + ":" + getColumn() + ":" + getPosition();
    };
}
