package ar.uba.fi.compiladores.parte6;
import ar.uba.fi.compiladores.parte4.Lol.TokenTypes;
import ar.uba.fi.compiladores.parte5.LexerException;

%%

%public
%class Lol
%type TokenTypes
%unicode
%throws LexerException

%%

[PA|RA]*    { return TokenTypes.LAL; }
[LI|RI]*    { return TokenTypes.LIL; }
[LO]*       { return TokenTypes.LOL; }
[PE]*       { return TokenTypes.LEL; }
[ \t\f]     { } //ignorar
[\w\d\S]*          { throw new LexerException(); }
