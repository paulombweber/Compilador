package lexico;

public class AnalysisError extends Exception
{
    private int position;
	private int line;

    public AnalysisError(String msg, int line, int position)
    {
        super(msg);
        this.line = line;
        this.position = position;
    }

    public AnalysisError(String msg)
    {
        super(msg);
        this.position = -1;
    }

    public int getPosition()
    {
        return position;
    }
    
	public int getLine() {
		return line;
	}    

    public String toString()
    {
        return super.toString() + ", @ "+position;
    }
       
}
