
%code requires{
#include<string>
#include<stdlib.h>
using namespace std;
void yyerror(const char*);
}

%{
#include<iostream>
using namespace std;
int yylex();
void yyset_in(FILE*);
char brd[9][9];
void prtBrd();
FILE* out;
%}

%union { int num; char c; }
%start Start
%token Digit Rank Piece
%type<num> Digit Rank
%type<c> Piece AnyPiece
%define parse.error verbose

%%

Start 		: 
			| Start Turn

Turn		: AnyPiece Rank Digit '-' AnyPiece Rank Digit		{ brd[$3][$2]=' '; brd[$7][$6]=$1; }
			| AnyPiece Rank Digit 'x' AnyPiece Rank Digit		{ brd[$3][$2]=' '; brd[$7][$6]=$1; }
			;

AnyPiece	: Piece		{ $$=$1; }
			|			{ $$='P'; }
			;
%%

int main()
{
	FILE* in;
	in=fopen("input.txt","r");
	yyset_in(in);
	for(int i=1;i<=8;i++)
		brd[2][i]=brd[7][i]='P';
	for(int i=3;i<=6;i++)
		for(int j=1;j<=8;j++)
			brd[i][j]=' ';
	brd[1][1]=brd[1][8]=brd[8][1]=brd[8][8]='R';
	brd[1][2]=brd[1][7]=brd[8][2]=brd[8][7]='N';
	brd[1][3]=brd[1][6]=brd[8][3]=brd[8][6]='B';
	brd[1][4]=brd[8][4]='Q';
	brd[1][5]=brd[8][5]='K';
	yyparse();
	prtBrd();
	fclose(in);
}
void yyerror(const char* s) { cout<<s<<endl; }

void prtBrd()
{
	cout<<" \ta\tb\tc\td\te\tf\tg\th\n\n";
	for(int i=1;i<=8;i++)
	{
		cout<<i<<"\t";
		for(int j=1;j<=8;j++)
			cout<<brd[i][j]<<"\t";
		cout<<endl<<endl;
	}
}
