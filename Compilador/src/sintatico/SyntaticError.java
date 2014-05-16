package sintatico;

import lexico.AnalysisError;

public class SyntaticError extends AnalysisError
{
    public SyntaticError(String msg, int line, int position)
	 {
        super(msg, line, position);
    }

    public SyntaticError(String msg)
    {
        super(msg);
    }
}
