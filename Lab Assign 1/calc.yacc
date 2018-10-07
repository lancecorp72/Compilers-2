/*Parser to generate Token Stream for Calculator*/
/*Definitions*/

%{
#include<stdio.h>
int yylex(void);
int ans=0;
int mem[26];
/*for(int i=0;i<26;i++)
	mem[i]=0;*/
%}

/*Prerequisites for Parsing*/

%union { int num; }
%start Input
%token Number Ans Opt Fn Any Var
%type <num> Exp Number Var
%left '+' '-'
%left '*' '/' '%'

/*Rules for different patterns in Token Stream*/

%%

Input	: { printf("\tCalculator for Integers\nType 'fn' for Calculator Functions\nType 'opt' for Calculator Options\n"); }
		| Input Line '\n'
		;

Line	: Exp	{ printf("ans = %d\n",$1); ans=$1; }
		| Var '=' Exp	{ mem[$1]=$3; }
		| Opt 	{ printf("Use 'ans' to access the last calculated answer\n\
Use 'exit' to terminate the program\n\
Variables from 'a' to 'z' can store expression results. Use '=' to initialize them\n"); }
		| Fn	{ printf("'+' Add\n'-' Subtract\n'*' Multiply\n'/' Divide\n'%%' Modulus\n'CE' Clear Entry (Resets ans)\n"); }
		|
		;

Exp		: '(' Exp ')'		{ $$ = $2; }
		| Exp '%' Exp		{
								if($3<=0)
							  	{
									yyerror("Invalid divisor for Modulus");
									$$=0;
								}
								else
									$$ = $1%$3;
							}
		| Exp '/' Exp		{
								if($3==0)
							  	{
									yyerror("Division by 0 not allowed");
									$$=0;
								}
								else
									$$ = $1/$3;
							}
		| Exp '*' Exp		{ $$ = $1*$3; }
		| Exp '+' Exp		{ $$ = $1+$3; }
		| Exp '-' Exp		{ $$ = $1-$3; }
		| '-' Exp			{ $$ = -$2; }
		| Ans				{ $$ = ans; }
		| Var				{ $$ = mem[$1]; }
		| Number
		;
%%

/*Functions to call parsing and write errors to output device*/

int main() { yyparse(); return 0; }
int yywrap() { return 1; }
void yyerror(char *c) { fprintf (stderr,"%s\n",c); }
