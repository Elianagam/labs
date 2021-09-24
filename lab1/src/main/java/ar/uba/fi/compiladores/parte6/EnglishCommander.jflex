package ar.uba.fi.compiladores.parte6;
import ar.uba.fi.compiladores.parte4.EnglishCommander.TokenTypes;
import ar.uba.fi.compiladores.parte5.LexerException;

%%

%public
%class EnglishCommander
%type TokenTypes
%unicode
%throws LexerException

%%

DO    { return TokenTypes.DO; }
DON    { return TokenTypes.DON; }
DONE    { return TokenTypes.DONE; }
[A-Z]*    { return TokenTypes.WORD; }
[ \t\f]     { } //ignorar
[\dA-Z]*          { throw new LexerException(); }
