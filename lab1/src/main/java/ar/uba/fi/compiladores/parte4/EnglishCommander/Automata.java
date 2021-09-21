package ar.uba.fi.compiladores.parte4.EnglishCommander;

import java.util.regex.Pattern;
import java.util.stream.Stream;

import ar.uba.fi.compiladores.parte3.LanguageAutomata;

public class Automata implements LanguageAutomata<State,TokenTypes> {

    @Override
    public State transition(State state, char character) {
        Pattern p = Pattern.compile("[A-Z]");
        switch(state){
            case INITIAL: switch(character) {
                case 'D': return State.D;
                case ' ': return State.DEAD;
                default : 
                    if (p.matcher(String.valueOf(character)).matches()) {
                        return State.WORD;
                    } else { return State.ERROR; }
                }
            case D: switch(character) {
                case 'O': return State.DO;
                case ' ': return State.DEAD;
                default: 
                    if (p.matcher(String.valueOf(character)).matches()) {
                        return State.WORD;
                    } else { return State.ERROR; }
                }
            case DO: switch(character) {
                case 'N': return State.DON;
                case ' ': return State.DEAD;
                default: 
                    if (p.matcher(String.valueOf(character)).matches()) {
                        return State.WORD;
                    } else { return State.ERROR; }
                }
            case DON: switch(character) {
                case 'E': return State.DONE;
                case ' ': return State.DEAD;
                default: 
                    if (p.matcher(String.valueOf(character)).matches()) {
                        return State.WORD;
                    } else { return State.ERROR; }
                }
            case DONE:
            case WORD: switch(character) {
                case ' ': return State.DEAD;
                default: 
                    if (p.matcher(String.valueOf(character)).matches()) {
                        return State.WORD;
                    } else { return State.ERROR; }
            }
            case ERROR:
                if (p.matcher(String.valueOf(character)).matches()) {
                    return State.WORD;
                } else { return State.ERROR; }
            default: return State.ERROR;
        }
    }

    @Override
    public boolean isFinal(State state) {
        return Stream.of(
            State.DO,
            State.DON,
            State.DONE,
            State.WORD,
            State.DEAD,
            State.ERROR
        ).anyMatch(finalState -> finalState.equals(state));
    }

    @Override
    public State getDeadState() {
        return State.DEAD;
    }

    @Override
    public TokenTypes stateToTokenType(State state) {
        switch (state) {
            case DO:return TokenTypes.DO;
            case DON: return TokenTypes.DON;
            case DONE: return TokenTypes.DONE;
            case WORD: return TokenTypes.WORD;
            default: return null;
        }
    }

    @Override
    public TokenTypes eofTokenType() {
        return TokenTypes.EOF;
    }

    @Override
    public State getInitialState() {
        return State.INITIAL;
    }

    @Override
    public State getErrorState() {
        return State.ERROR;
    }
    
}
