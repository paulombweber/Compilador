package sintatico;

import lexico.AnalysisError;

public class SemanticError extends AnalysisError
{
    public SemanticError(String msg, int line, int position)
	 {
        super(msg, line, position);
    }

    public SemanticError(String msg)
    {
        super(msg);
    }
}
