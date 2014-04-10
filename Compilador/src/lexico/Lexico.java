package lexico;

import java.util.Stack;

public class Lexico implements Constants
{
    private int position; // sempre ser� a pr�xima posi��o
    private int line;
    private int column;
    private Stack<Integer> endColumnsLinePostion; // guarda o n�mero da ultima coluna de cada linha
    private String input;
    
    public Lexico() {
    	endColumnsLinePostion = new Stack<Integer>();
    }

    public void setInput(String input)
    {
        this.input = input;
        position = 0;
        line = 1;
        column = 1;        
    }    
    
    public void setPosition(int newPosition) {
    	if (newPosition == position) {
    		input.charAt(position - 1);
    	} else if (newPosition > position) {
    		int count = newPosition - position;
    		
    		for (int i = 0; i < count; i++) {
    			nextChar();
    		}
    	} else {
    		int count = position - newPosition;
    		
    		for (int i = 0; i < count; i++) {
    			previousChar();
    		}
    	}
    }
    
    public int getPosition(){
    	return position;
    }    

    public Token nextToken() throws LexicalError
    {
        if ( ! hasInput() )
            return null;

        int start = position;
        int startColumn = column;

        int state = 0;
        int lastState = 0;
        int endState = -1;
        int end = -1;

        while (hasInput())
        {
            lastState = state;
            state = nextState(nextChar(), state);

            if (state < 0)
                break;
            else
            {
                if (tokenForState(state) >= 0)
                {
                    endState = state;
                    end = position;            
                }
            }
        }
        if (endState < 0 || (endState != state && tokenForState(lastState) == -2))
            throw new LexicalError(SCANNER_ERROR[lastState], lastState);

        setPosition(end);

        int token = tokenForState(endState);

        if (token == 0)
            return nextToken();
        else
        {
            String lexeme = input.substring(start, end);
            token = lookupToken(token, lexeme);            
            return new Token(token, lexeme, line, startColumn, position);
        }
    }
    
    private int nextState(char c, int state)
    {
        int next = SCANNER_TABLE[state][c];
        return next;
    }

    private int tokenForState(int state)
    {
        if (state < 0 || state >= TOKEN_STATE.length)
            return -1;

        return TOKEN_STATE[state];
    }

    public int lookupToken(int base, String key)
    {
        int start = SPECIAL_CASES_INDEXES[base];
        int end   = SPECIAL_CASES_INDEXES[base+1]-1;

        while (start <= end)
        {
            int half = (start+end)/2;
            int comp = SPECIAL_CASES_KEYS[half].compareTo(key);

            if (comp == 0)
                return SPECIAL_CASES_VALUES[half];
            else if (comp < 0)
                start = half+1;
            else  //(comp > 0)
                end = half-1;
        }

        return base;
    }

    private boolean hasInput()
    {
        return position < input.length();
    }      
    
    private char previousChar() {
    	if (position > 0) {
    		if (column == 1) {
    			line--;
    			column = endColumnsLinePostion.pop();
    		} else {
    			column--;
    		}
    		
    		position--;
    		return input.charAt(position - 1);
    	} else {
    		return (char) -1;
    	}
    }
    
    private char nextChar()
    {
        if (hasInput()) {
        	char c = input.charAt(position);
        	
    		if (c == '\n') {
    			line++;
    			endColumnsLinePostion.push(column);
    			column = 1;
    		} else {
    			column++;
    		}
        	
    		position++;
        	return c;        	
        }
        else
            return (char) -1;
    }
}
