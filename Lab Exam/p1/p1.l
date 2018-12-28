/*Definitions*/

%{
#include "y.tab.h"
%}

/*Special words along with numbers and operators recognized here*/
%%
[ \t\n]				;
[-+*/%&@<>$#()]		{ return yytext[0]; }
[ihbc]				{ return yytext[0]; }
[0-9]				{ yylval.num=yytext[0]-'0'; return Digit; }
[\"][^"]*[\"]			{ yylval.str=new string(yytext); return String; }
.					{
						char s[30];
						sprintf(s,"Unexpected Character %c",yytext[0]);
						yyerror(s);
					}
%%
int yywrap() { return 1; }
