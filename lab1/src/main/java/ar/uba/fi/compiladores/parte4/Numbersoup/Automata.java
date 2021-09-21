package ar.uba.fi.compiladores.parte4.Numbersoup;

import java.util.stream.Stream;

import ar.uba.fi.compiladores.parte1.RegexLibrary;
import ar.uba.fi.compiladores.parte3.LanguageAutomata;

public class Automata implements LanguageAutomata<State,TokenTypes> {

    RegexLibrary regexLibrary = new RegexLibrary();

    boolean matchDecimal(String s){
        return regexLibrary.getDecimalsRegex().matcher(s).matches();
    }

    boolean matchHexa(String s){
        return regexLibrary.getHexaRegex().matcher(s).matches();
    }

    @Override
    public State transition(State state, char character) {
        String charStr = String.valueOf(character);
        switch(state){
            case INITIAL:
            case BIN: switch(character) {
                case ' ': return State.DEAD;
                case '0':
                case '1': return State.BIN;
                default: 
                    if (this.matchDecimal(charStr)) { return State.DEC; } 
                    if (this.matchHexa(charStr)) { return State.HEX; } 
                    else { return State.ERROR; }
            }
            case DEC: switch(character) {
                case ' ': return State.DEAD;
                case '0': return State.DEC;
                default: 
                    if (this.matchDecimal(charStr)) { return State.DEC; } 
                    else if (this.matchHexa(charStr)) { return State.HEX; } 
                    else { return State.ERROR; }
            }
            case HEX: switch(character) {
                case ' ': return State.DEAD;
                case '0': return State.HEX;
                case 'x': return State.X;
                default: 
                    if (this.matchHexa(charStr)) { return State.HEX; } 
                    else { return State.ERROR; }
            }
            case BINHEX:
            case X: switch(character) {
                case ' ': return State.DEAD;
                case '0':
                case '1': return State.BINHEX;
                default: return State.ERROR;
            }
            default: return State.ERROR;
        }
    }

    @Override
    public boolean isFinal(State state) {
        return Stream.of(
            State.BIN,
            State.HEX,
            State.DEC,
            State.BINHEX,
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
            case BIN: return TokenTypes.BIN;
            case HEX: return TokenTypes.HEX;
            case DEC: return TokenTypes.DEC;
            case BINHEX: return TokenTypes.BINHEX;
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
