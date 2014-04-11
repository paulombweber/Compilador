package lexico;

public class LexicalError extends AnalysisError
{
	private int line;
	private int state;
	
    public LexicalError(String msg, int position, int line, int state)
	 {
        super(msg, position);
        this.line = line;
        this.state = state;
    }

	public int getLine() {
		return line;
	}

	public int getState() {
		return state;
	}
    
    
}
