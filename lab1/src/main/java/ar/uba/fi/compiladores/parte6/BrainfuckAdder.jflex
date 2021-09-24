package ar.uba.fi.compiladores.parte6;
import ar.uba.fi.compiladores.parte3.BrainfuckAdder.BrainfuckAdderTokens;
import ar.uba.fi.compiladores.parte5.LexerException;

%%

%public
%class BrainfuckAdder
%type BrainfuckAdderTokens
%unicode
%throws LexerException

%%

\+{1}    { return BrainfuckAdderTokens.ADDITION; }
-{1}    { return BrainfuckAdderTokens.DIFFERENCE; }
[\+-]*    { return BrainfuckAdderTokens.INTEGER; }
[\w\d]*    { throw new LexerException(); }
[ \t\f\|]     { } //ignorar