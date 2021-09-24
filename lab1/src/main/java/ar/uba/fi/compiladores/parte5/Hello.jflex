package ar.uba.fi.compiladores.parte5;

%%

%public
%class Hello
%type HelloToken
%unicode
%throws LexerException


%%

hello    { return HelloToken.HELLO; }
world    { return HelloToken.WORLD; }
[0-9]*   { return HelloToken.NUMBER; }
[a-z]    { throw new LexerException(); }
[ \t\f]  { } //ignorar