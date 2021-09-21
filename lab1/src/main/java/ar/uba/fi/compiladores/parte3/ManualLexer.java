package ar.uba.fi.compiladores.parte3;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa un algoritmo de lexeo a partir de un lenguage.
 */
public class ManualLexer<S,T>{
    private LanguageAutomata<S,T> language;
    public ManualLexer(LanguageAutomata<S,T> language){
        this.language=language;
    }
    /**
     * extracts a token from a string and returns the next state of the LanguageAutomata and the length of the extracted token.
     * @param program
     * @return
     */
    public SingleTokenExtraction<S> extractToken(String program){
        S actualState = this.language.getInitialState();
        S finalState = this.language.getDeadState();
        int indexState = 0; 
        for (int i = 0; i < program.length(); i++) {
            actualState = this.language.transition(actualState, program.charAt(i));
            if (actualState == this.language.getDeadState()){
                return new SingleTokenExtraction<S>(finalState, indexState);
            }
            if (this.language.isFinal(actualState)) {
                finalState = actualState;
                indexState = i;
            }
        }
        return new SingleTokenExtraction<S>(actualState, indexState);
    }

    public List<Token<T>> lex(String program) throws BadTokenException{
        List<Token<T>> tokens = new ArrayList<>();
        while (!program.isEmpty()) {
            SingleTokenExtraction<S> token = extractToken(program);
            if (token.getFinalState() == this.language.getDeadState()) {
                program = program.substring(token.getLastFinalIndex()+1, program.length());
                continue;
            } else if (token.getFinalState() == this.language.getErrorState()) {
                throw new BadTokenException(program);
            } else {
                String subprogram = program.substring(0, token.getLastFinalIndex()+1);
                program = program.substring(token.getLastFinalIndex()+1, program.length());
    
                T tFromS = this.language.stateToTokenType(token.getFinalState());
                tokens.add(new Token<T>(tFromS, subprogram));
            }
        }
        tokens.add(new Token<T>(this.language.eofTokenType(), null));
        return tokens;
    }

}