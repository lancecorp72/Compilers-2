/*Definitions*/

%{
#include "y.tab.h"
%}

/*Special words along with numbers and operators recognized here*/
%%
[ \t\n]				;
[-x]				{ return yytext[0]; }
[abcdefgh]			{ yylval.num=yytext[0]-'a'+1; return Rank; }
[KQRBN]				{ yylval.c=yytext[0]; return Piece; }
[1-8]				{ yylval.num=yytext[0]-'0'; return Digit; }
[0-9]+[ \t]*"."		;
.					{
						char s[30];
						sprintf(s,"Unexpected Character %c",yytext[0]);
						yyerror(s);
					}
%%
int yywrap() { return 1; }
