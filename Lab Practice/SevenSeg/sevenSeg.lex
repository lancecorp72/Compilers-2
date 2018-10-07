/*Definitions*/

%{
#include<stdlib.h>
#include "y.tab.h"
%}

/*Special words along with numbers and operators recognized here*/
%%
[ \t]+				;
^[\n]				;
"#".*[\n]			;
"INIT"				{ return Init; }
"Select"			{ return Select; }
"Delay"				{ return Delay; }
"readInt"			{ return ReadInt; }
"int"				{ return Int; }
"if"				{ return If; }
"Else"				{ return Else; }
[a-z]+[ \t]*[:]		{ strcpy(yylval.str,strtok(yytext," \t:")); return VarDec; }
[a-z]+[ \t]*[=]		{ strcpy(yylval.str,strtok(yytext," \t=")); return VarUse; }
[0-9]				{ yylval.s = new string(yytext); return Digit; }
[:=\n]				{ return yytext[0]; }
.					{
						char s[30];
						sprintf(s,"Unexpected Character %c",yytext[0]);
						yyerror(s);
						exit(1);
					}
%%
int yywrap() { return 1; }
