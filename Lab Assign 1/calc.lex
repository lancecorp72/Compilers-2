/*Lexer to generate Token Stream for Calculator*/
/*Definitions*/

%{
#include<stdlib.h>
#include "y.tab.h"

%}

/*Special words along with numbers and operators recognized here*/
%%
^opt		{ return Opt; }
^exit		{ exit(1); }
^ans		{ return Ans; }
^fn			{ return Fn; }
^CE			{ yylval.num=0; return Number; }
[0-9]+		{
				yylval.num = atoi(yytext);
				return Number;
			}
[a-z]		{
				yylval.num = yytext[0]-'a';
				return Var;
			}
[ \t]* 	;
[-+*/()%=\n]	{ return yytext[0]; }
.			{
				char s[30];
				sprintf(s,"Unexpected Character %c",yytext[0]);
				yyerror(s);
			}
%%

