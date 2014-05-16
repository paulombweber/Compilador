package lexico;

public class LexicalError extends AnalysisError
{

	private int state;
	
    public LexicalError(String msg, int position, int line, int state)
	 {
        super(msg, line, position);
        this.state = state;
    }



	public int getState() {
		return state;
	}
    
    
}
