/*Definitions*/
%code requires{
void yyerror(const char *c);
}

%{
#include<stdio.h>
#include<string.h>
int yylex(void);
%}

/*Prerequisites for Parsing*/

%union { int num; char str[100]; }
%start Start
%token PRM VAR AND ARROW LE LT GE GT NUMBER
%type<str> PrmList PRM VarList VAR CondList Cond LimitList Limit LE LT GE GT NUMBER Exp AlpNum Opr
%left '+' '-'
%define parse.error verbose

/*Rules for different patterns in Token Stream*/

%%

Start		: 
			| Start Set '\n'
			| Start Relation '\n'
			;

Set			: 'D' '=' '[' PrmList ']' ARROW '{' VarList '|' LimitList '}'
			{ 
				printf("Set\n");
				printf("Dims: %s\n",$8);
				printf("Params: %s\n",$4);
				printf("Constraints:\n\t%s\n",$10);
			}
			;

Relation	: 'R' '=' '[' PrmList ']' ARROW '{' '(' VarList ')' ARROW '(' VarList ')' '|' CondList '}'
			{
				printf("Relation\n");
				printf("Dims: %s, %s\n",$9,$13);
				printf("Params: %s\n",$4);
				printf("Constraints:\n\t%s\n",$16);
			}
			| 'R' '=' '[' PrmList ']' ARROW '{' VarList ARROW VarList '|' CondList '}'
			{
				printf("Relation\n");
				printf("Dims: %s, %s\n",$8,$10);
				printf("Params: %s\n",$4);
				printf("Constraints:\n\t%s\n",$12);
			}
			;

PrmList		: PrmList ',' PRM		{ strcpy($$,$1);strcat($$,", ");strcat($$,$3); }
			| PRM					{ strcpy($$,$1); }
			;

VarList		: VarList ',' VAR		{ strcpy($$,$1);strcat($$,", ");strcat($$,$3); }
			| VAR					{ strcpy($$,$1); }
			;

LimitList	: LimitList ',' Limit	{ strcpy($$,$1);strcat($$,"\n\t");strcat($$,$3); }
			| Limit					{ strcpy($$,$1); }
			;

CondList	: CondList AND Cond		{ strcpy($$,$1);strcat($$,"\n\t");strcat($$,$3); }
			| CondList AND Limit	{ strcpy($$,$1);strcat($$,"\n\t");strcat($$,$3); }
			| Cond					{ strcpy($$,$1); }
			| Limit					{ strcpy($$,$1); }
			;

Limit		: AlpNum Opr VAR Opr AlpNum		{ strcpy($$,$1);strcat($$,$2);strcat($$,$3);strcat($$,$4);strcat($$,$5); }
			;

Cond		: Exp '=' Exp			{ strcpy($$,$1);strcat($$,"=");strcat($$,$3); }
			;

Exp			: Exp '+' Exp			{ strcpy($$,$1);strcat($$,"+");strcat($$,$3); }
			| Exp '-' Exp			{ strcpy($$,$1);strcat($$,"-");strcat($$,$3); }
			| '-' Exp				{ strcpy($$,"-");strcat($$,$2); }
			| AlpNum				{ strcpy($$,$1); }
			;

AlpNum		: NUMBER			{ strcpy($$,$1); }
			| PRM				{ strcpy($$,$1); }
			| VAR				{ strcpy($$,$1); }
			;

Opr			: GE				{ strcpy($$,">="); }
			| GT				{ strcpy($$,">"); }
			| LE				{ strcpy($$,"<="); }
			| LT				{ strcpy($$,"<"); }
			;
%%

/*Functions to call parsing and write errors to output device*/

int main() { yyparse(); return 0; }
int yywrap() { return 1; }
void yyerror(const char *c) { fprintf (stderr,"%s\n",c); }
