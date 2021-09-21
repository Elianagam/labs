package ar.uba.fi.compiladores.parte4.Lol;

import java.util.stream.Stream;

import ar.uba.fi.compiladores.parte3.LanguageAutomata;

public class Automata implements LanguageAutomata<State,TokenTypes> {

    @Override
    public State transition(State state, char character) {
        switch(state){
            case INITIAL: switch(character) {
                case 'P': return State.P;
                case 'L': return State.L;
                case 'R': return State.R;
                case ' ': return State.DEAD;
                default: return State.ERROR;
            }
            case P: switch(character) {
                case 'A': return State.LAL;
                case 'E': return State.LEL;
                case ' ': return State.DEAD;
                default: return State.ERROR;
            }
            case R: switch(character) {
                case 'A': return State.LAL;
                case 'I': return State.LIL;
                case ' ': return State.DEAD;
                default: return State.ERROR;
            }
            case L: switch(character) {
                case 'O': return State.LOL;
                case 'I': return State.LIL;
                case ' ': return State.DEAD;
                default: return State.ERROR;
            }
            case LAL: switch(character) {
                case 'P': return State.LAA;
                case 'R': return State.LAA;
                case ' ': return State.DEAD;
                default: return State.ERROR;
            }
            case LEL: switch(character) {
                case 'P': return State.LEE;
                case ' ': return State.DEAD;
                default: return State.ERROR;
            }
            case LIL: switch(character) {
                case 'L': return State.LII;
                case 'R': return State.LII;
                case ' ': return State.DEAD;
                default: return State.ERROR;
            }
            case LOL: switch(character) {
                case 'L': return State.LOO;
                case ' ': return State.DEAD;
                default: return State.ERROR;
            }
            case LAA: switch(character) {
                case 'A': return State.LAL;
                case ' ': return State.DEAD;
                default: return State.ERROR;
            }
            case LEE: switch(character) {
                case 'E': return State.LEL;
                case ' ': return State.DEAD;
                default: return State.ERROR;
            }
            case LII: switch(character) {
                case 'I': return State.LIL;
                case ' ': return State.DEAD;
                default: return State.ERROR;
            }
            case LOO: switch(character) {
                case 'O': return State.LOL;
                case ' ': return State.DEAD;
                default: return State.ERROR;
            }
            default: return State.ERROR;
        }
    }

    @Override
    public boolean isFinal(State state) {
        return Stream.of(
            State.LAL,
            State.LIL,
            State.LOL,
            State.LEL,
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
            case LAL: return TokenTypes.LAL;
            case LEL: return TokenTypes.LEL;
            case LOL: return TokenTypes.LOL;
            case LIL: return TokenTypes.LIL;
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
