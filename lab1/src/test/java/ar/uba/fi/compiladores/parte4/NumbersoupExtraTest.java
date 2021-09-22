package ar.uba.fi.compiladores.parte4;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ar.uba.fi.compiladores.parte3.BadTokenException;
import ar.uba.fi.compiladores.parte3.ManualLexer;
import ar.uba.fi.compiladores.parte3.Token;
import ar.uba.fi.compiladores.parte4.Numbersoup.Automata;
import ar.uba.fi.compiladores.parte4.Numbersoup.State;
import ar.uba.fi.compiladores.parte4.Numbersoup.TokenTypes;

public class NumbersoupExtraTest {
    Automata language = new Automata();
    ManualLexer<State,TokenTypes> lexer = new ManualLexer<State,TokenTypes>(language);

    @Test 
    public void testOtherTokensAsDecimal() throws BadTokenException{
        List<Token<TokenTypes>> expected = Arrays.asList(
            new Token<>(TokenTypes.DEC,"21001010101001"),
            new Token<>(TokenTypes.DEC,"100000004"),
            new Token<>(TokenTypes.EOF,null)
        );
        assertEquals(expected, lexer.lex("21001010101001 100000004"));
    }

    @Test 
    public void testOtherTokensAsHex() throws BadTokenException{
        List<Token<TokenTypes>> expected = Arrays.asList(
            new Token<>(TokenTypes.HEX,"F801"),
            new Token<>(TokenTypes.HEX,"0A0101"),
            new Token<>(TokenTypes.HEX,"5CC50101"),
            new Token<>(TokenTypes.EOF,null)
        );
        assertEquals(expected, lexer.lex(" F801 0A0101 5CC50101"));
    }

    @Test 
    public void testOtherTokensAsBinHex() throws BadTokenException{
        List<Token<TokenTypes>> expected = Arrays.asList(
            new Token<>(TokenTypes.BINHEX,"0AFx010"),
            new Token<>(TokenTypes.DEC,"345x010"),
            new Token<>(TokenTypes.EOF,null)
        );
        assertEquals(expected, lexer.lex(" 0AFx010 345x010 "));
    }
    
}
