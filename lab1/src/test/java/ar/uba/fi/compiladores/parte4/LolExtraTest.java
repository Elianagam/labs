package ar.uba.fi.compiladores.parte4;

import org.junit.Test;
//import org.junit.jupiter.api.Test;

import ar.uba.fi.compiladores.parte3.BadTokenException;
import ar.uba.fi.compiladores.parte3.ManualLexer;
import ar.uba.fi.compiladores.parte4.Lol.Automata;
import ar.uba.fi.compiladores.parte4.Lol.State;
import ar.uba.fi.compiladores.parte4.Lol.TokenTypes;

public class LolExtraTest {
    Automata language = new Automata();
    ManualLexer<State,TokenTypes> lexer = new ManualLexer<State,TokenTypes>(language);

    @Test(expected=BadTokenException.class)
    public void testDiferentLol() throws BadTokenException{
        lexer.lex(" PELILILILI PELOLOLOLO");
    }
}
