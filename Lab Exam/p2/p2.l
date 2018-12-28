/*Definitions*/

%{
#include "y.tab.h"
%}

/*Special words along with numbers and operators recognized here*/
%%
">="					{ return GE; }
"<="					{ return LE; }
[A-Za-z][A-Za-z0-9]*	{ yylval.str=new string(yytext);return Var; }
[0-9]+					{ yylval.str=new string(yytext);return Num; }
[-+*/\[\]{},=:]			{ return yytext[0]; }
[ \t\n]*				;
.						{
							char s[30];
							sprintf(s,"Unexpected Character %c",yytext[0]);
							yyerror(s);
						}
%%
int yywrap() { return 1; }
