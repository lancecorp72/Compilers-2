/*Definitions*/
%code requires{
#include<string>
using namespace std;
void yyerror(const char *c);
}

%{
#include<iostream>
#include<vector>
using namespace std;
FILE* out;
void yyset_in(FILE*);
int yylex();
int m=0,n=0;
vector<string> cons,vars;
%}


%union { string *str; }
%start Start
%token Var LE GE Num
%type<str> Var Opr Num
%left '+' '-'
%left '*' '/'
%define parse.error verbose

/*Rules for different patterns in Token Stream*/

%%

Start		:
			|  Start '{' '[' VarList ']' ':' FormList '}'
			;

VarList		: VarList ',' Var	{ vars.push_back(*$3); n++; }
			| Var				{ vars.push_back(*$1); n++; }
			;

FormList	: FormList ',' Form
			| Form
			;

Form		: Exp Opr Exp
			;

Exp			: Exp '+' Exp
			| Exp '-' Exp
			| Exp '*' Exp
			| Exp '/' Exp
			| '-' Exp
			;
			
			;

Opr			: GE			{ $$=new string(">=")); }
			| LE			{ $$=new string("<=")); }
			| '='			{ $$=new string("=")); }
			;
%%

/*Functions to call parsing and write errors to output device*/

int main()
{
	FILE* in;
	in=fopen("input.txt","r");
	yyset_in(in);
	yyparse();
	fclose(in);
}
int yywrap() { return 1; }
void yyerror(const char *c) { fprintf (stderr,"%s\n",c); }
