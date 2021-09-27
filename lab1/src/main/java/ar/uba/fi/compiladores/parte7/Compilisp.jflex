package ar.uba.fi.compiladores.parte7;
import ar.uba.fi.compiladores.parte5.LexerException;
import ar.uba.fi.compiladores.parte3.Token;
import ar.uba.fi.compiladores.parte7.CompilispTokenTypes;

%%

%public
%class Compilisp
%type Token<CompilispTokenTypes>
%yylexthrow LexerException
%unicode

%{
      StringBuffer string = new StringBuffer();
%}

LineTerminator = \n
InputCharacter = [^\n]
/* comments */
EndOfLineComment     = "// " {InputCharacter}* {LineTerminator}?

%%

{EndOfLineComment}   {}
0|[1-9][0-9]*      { return new Token<CompilispTokenTypes>(CompilispTokenTypes.NUMBER, String.valueOf(yytext())); }
"("      { return new Token<CompilispTokenTypes>(CompilispTokenTypes.LEFT_PAREN, String.valueOf(yytext())); }
")"      { return new Token<CompilispTokenTypes>(CompilispTokenTypes.RIGHT_PAREN, String.valueOf(yytext())); }
[a-z]*      { return new Token<CompilispTokenTypes>(CompilispTokenTypes.NAME, String.valueOf(yytext())); }
-|\+      { return new Token<CompilispTokenTypes>(CompilispTokenTypes.NAME, String.valueOf(yytext())); }

\"[\sa-z0-9\\]*\"  { String text = yytext().substring(1, text.length()-1);
                    return new Token<CompilispTokenTypes>(CompilispTokenTypes.NAME, text);
                  }

[ \n\t]    {}
[^] {throw new LexerException();}