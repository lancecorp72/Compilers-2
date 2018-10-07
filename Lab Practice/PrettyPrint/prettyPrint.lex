/*Definitions*/

%{
#include<stdlib.h>
#include "y.tab.h"

%}

/*Special words along with numbers and operators recognized here*/
%%
^D					{ return yytext[0]; }
^R					{ return yytext[0]; }
and					{ return AND; }
">="				{ return GE; }
"<="				{ return LE; }
">"					{ return GT; }
"<"					{ return LT; }
"->"				{ return ARROW; }
[A-Z]+				{ strcpy(yylval.str,yytext);return PRM; }
[a-z]+				{ strcpy(yylval.str,yytext);return VAR; }
[0-9]+				{ strcpy(yylval.str,yytext);return NUMBER; }
[-+|{}()\[\],=\n]		{ return yytext[0]; }
[ \t]*				;
.					{
						char s[30];
						sprintf(s,"Unexpected Character %c",yytext[0]);
						yyerror(s);
					}
%%

