package sintatico;

import lexico.AnalysisError;

public class SyntaticError extends AnalysisError
{
	
	public SyntaticError(final String msg, final int line, final int position)
	{
		super(msg, line, position);
	}
	
	public SyntaticError(final String msg)
	{
		super(msg);
	}
}
