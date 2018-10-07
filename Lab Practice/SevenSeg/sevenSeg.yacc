/*Definitions*/
%code requires{
#include<string>
using namespace std;
void yyerror(const char*);
}

%{
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<iostream>
#pragma GCC diagnostic push
#pragma GCC diagnostic ignored "-Wformat-overflow"
int yylex();
void yyset_in(FILE*);
FILE *f;
int input;
char temp[100];
%}

/*Prerequisites for Parsing*/

%union
{
	char str[1000];
	std::string *s;
}
%start Start
%token Digit Init Select Delay ReadInt VarDec VarUse If Else Int
%type<str> IfElse VarDec VarUse OptInp ReadInp
%type<s> Digit Number SelBlock
%define parse.error verbose

/*Rules for different patterns in Token Stream*/

%%

Start		:	Init '\n' OptInp
				{
					f = fopen("output.c","w");
					fprintf(f,"#include<stdio.h>\n#include<stdlib.h>\n#include<seven_segment.h>\nint main()\n{\n\t\
init();\n%s\twhile(1)\n\t{\n",$3);
				}
				SelSeries
				{
					fprintf(f,"\t}\n}\n");
					fclose(f);
				}
			;

OptInp		:	{ $$[0]='\0'; }
			|	OptInp VarDec Int '\n' 		{ sprintf($$,"\tint %s;\n",$2); }
			;

SelSeries	:	SelSeries ReadInp SelBlock 		{ fprintf(f,"%s%s\n",$2,$3->c_str()); }
			|	ReadInp SelBlock				{ fprintf(f,"%s%s\n",$1,$2->c_str()); }
			;

SelBlock	:	Select ':' Number '\n' IfElse '\n' Delay ':' Number '\n' 
				{ $$ = new string("\t\tselect(" + *$3 + ");\n\t\t" + string($5) + "\n\t\tdelay(" + *$9 + ");"); }
			;

ReadInp		:	VarUse ReadInt '\n' ReadInp		{ sprintf($$,"\t\t%s = readInt();\n",$1); }
			|	{ $$[0]='\0'; }
			;
			
IfElse		:	If VarUse '=' Number ':' '\n' IfElse '\n' Else ':' '\n' Number
				{ sprintf($$,"if(%s==%s)\n\t\t{\n\t\t\t%s\n\t\t}\n\t\t\
else\n\t\t{\n\t\t\twrite(strtol(\"%s\"));\n\t\t}",$2,$4->c_str(),$7,$12->c_str()); }
			|	Number				{ sprintf($$,"write(strtol(\"%s\"));",$1->c_str()); }
			;

Number		:	Number Digit		{ (*$$) = (*$1) + (*$2); }
			|	Digit				{ $$ = new string(*$1); }
			;
%%

/*Functions to call parsing and write errors to output device*/

int main()
{
	FILE *fin;
	if (!(fin = fopen("input.txt", "r")))
    {
        yyerror("Cannot open Input File");
        return -1;
    }
    yyset_in(fin);
	yyparse();
	return 0;
}
void yyerror(const char *c) { fprintf (stderr,"%s\n",c); }
#pragma GCC diagnostic pop
