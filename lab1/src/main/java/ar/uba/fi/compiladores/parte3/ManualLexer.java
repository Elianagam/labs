package ar.uba.fi.compiladores.parte3;

import java.util.List;
import java.util.regex.Matcher;

import ar.uba.fi.compiladores.parte1.RegexLibrary;

/**
 * Clase que implementa un algoritmo de lexeo a partir de un lenguage.
 */
public class ManualLexer<S,T>{
    private LanguageAutomata<S,T> language;
    RegexLibrary regexLibrary = new RegexLibrary();
    public ManualLexer(LanguageAutomata<S,T> language){
        this.language=language;
    }
    /**
     * extracts a token from a string and returns the next state of the LanguageAutomata and the length of the extracted token.
     * @param program
     * @return
     */
    public SingleTokenExtraction<S> extractToken(String program){
        this.language.eofTokenType();

        S actualState = this.language.getInitialState();
        S finalState = null;
        for (int i = 0; i < program.length(); i++) {
            actualState = this.language.transition(actualState, program.charAt(i));
            if (this.language.isFinal(actualState)) {
                finalState = actualState;
            }
            if (actualState == this.language.getDeadState()){
                return new SingleTokenExtraction<S>(finalState, i);    
            }
        }
        
        return null;
    }
    public List<Token<T>> lex(String program){
        return null;
    }

}