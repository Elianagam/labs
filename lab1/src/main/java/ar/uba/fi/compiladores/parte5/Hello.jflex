package ar.uba.fi.compiladores.parte5;

%%

%public
%class Hello
%type HelloToken
%unicode

%yylexthrow "LexerException"

%%

hello    { return HelloToken.HELLO; }
world    { return HelloToken.WORLD; }
[0-9]*   { return HelloToken.NUMBER; }
[a-z]    { throw new LexerException("Invalid Token")); }
[ \t\f]  { } //ignorar