package ar.uba.fi.compiladores.parte6;
import ar.uba.fi.compiladores.parte4.Numbersoup.TokenTypes;
import ar.uba.fi.compiladores.parte5.LexerException;

%%

%public
%class Numbersoup
%type TokenTypes
%unicode
%throws LexerException

%%

[0-1]*    { return TokenTypes.BIN; }
[0-9]*    { return TokenTypes.DEC; }
[0-9A-F]*    { return TokenTypes.HEX; }
[ \f\t]*    { }
[0-9A-F]*x[0-1]* { return TokenTypes.BINHEX; }
[0-9A-F]*x[0-9]* { throw new LexerException(); }
\w          { throw new LexerException(); }
