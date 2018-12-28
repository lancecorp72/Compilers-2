
%code requires{
#include<string>
#include<stdlib.h>
using namespace std;
void yyerror(const char*);
}

%{
#include<iostream>
#include<stack>
using namespace std;
int yylex();
void yyset_in(FILE*);
int nest=0;
string indent();
FILE* out;
%}

%union { int num;string *str; }
%start Start
%token Digit String
%type<num> Digit Number
%type<str> String
%define parse.error verbose

%%

Start 		: 
			| Start Fn
			;
			
Fn			: String			{ fprintf(out,"%scout<<%s<<endl;\n",$1->c_str(),indent().c_str()); }
			| '&' Number		{ fprintf(out,"%sary[aryPtr]=%d;\n",indent().c_str(),$2); }
			| '&'				{ fprintf(out,"%sary[aryPtr]=stk.top();\n%sstk.pop();\n",indent().c_str(),indent().c_str()); }
			| '@' Number		{ fprintf(out,"%sstk.push(%d);\n",indent().c_str(),$2); }
			| '@'				{ fprintf(out,"%sstk.push(ary[aryPtr]);\n",indent().c_str()); }
			| '<'				{ fprintf(out,"%saryPtr = (aryPtr-1)%%65535;\n",indent().c_str()); }
			| '>'				{ fprintf(out,"%saryPtr = (aryPtr+1)%%65535;\n",indent().c_str()); }
			| '$' 'i'			{ fprintf(out,"%scout<<ary[aryPtr]<<endl;\n",indent().c_str()); }
			| '$' 'h'			{ fprintf(out,"%scout<<hex<<ary[aryPtr]<<dec<<endl;\n",indent().c_str()); }
			| '$' 'b'			{ fprintf(out,"%scout<<bitset<16>(ary[aryPtr])<<endl;\n",indent().c_str()); }
			| '$' 'c' Number	{ fprintf(out,"%scout<<'%c';\n",indent().c_str(),$3); }
			| '#' Number		{ fprintf(out,"%ssleep(%d);\n",indent().c_str(),$2); }
			| '+' Number		{ fprintf(out,"%sary[aryPtr]=(ary[aryPtr]+%d)%%65536;\n",indent().c_str(),$2); }
			| '+'				{ fprintf(out,"%sif(!stk.empty()) {\n\t%sary[aryPtr]=(ary[aryPtr]+stk.top())%%65536;\n\t%sstk.pop();\n%s}\n",indent().c_str(),indent().c_str(),indent().c_str(),indent().c_str()); }
			| '-' Number		{ fprintf(out,"%sary[aryPtr]=(ary[aryPtr]-%d)%%65536;\n",indent().c_str(),$2); }
			| '-'				{ fprintf(out,"%sif(!stk.empty()) {\n\t%sary[aryPtr]=(ary[aryPtr]-stk.top())%%65536;\n\t%sstk.pop();\n%s}\n",indent().c_str(),indent().c_str(),indent().c_str(),indent().c_str()); }
			| '*' Number		{ fprintf(out,"%sary[aryPtr]=(ary[aryPtr]*%d)%%65536;\n",indent().c_str(),$2); }
			| '*'				{ fprintf(out,"%sif(!stk.empty()) {\n\t%sary[aryPtr]=(ary[aryPtr]*stk.top())%%65536;\n\t%sstk.pop();\n%s}\n",indent().c_str(),indent().c_str(),indent().c_str(),indent().c_str()); }
			| '/' Number		{ fprintf(out,"%sary[aryPtr]=(ary[aryPtr]/%d)%%65536;\n",indent().c_str(),$2); }
			| '/'				{ fprintf(out,"%sif(!stk.empty()) {\n\t%sary[aryPtr]=(ary[aryPtr]/stk.top())%%65536;\n\t%sstk.pop();\n%s}\n",indent().c_str(),indent().c_str(),indent().c_str(),indent().c_str()); }
			| '%' Number		{ fprintf(out,"%sary[aryPtr]=(ary[aryPtr]%%%d)%%65536;\n",indent().c_str(),$2); }
			| '%'				{ fprintf(out,"%sif(!stk.empty()) {\n\t%sary[aryPtr]=(ary[aryPtr]%%stk.top())%%65536;\n\t%sstk.pop();\n%s}\n",indent().c_str(),indent().c_str(),indent().c_str(),indent().c_str()); }
			| '(' Number		{ fprintf(out,"%swhile(ary[aryPtr]!=%d) {\n",indent().c_str(),$2);nest++; }
			| '('				{ nest++;fprintf(out,"%swhile(ary[aryPtr]!=0) {\n",indent().c_str()); }
			| ')'				{ nest--;fprintf(out,"%s}\n",indent().c_str()); }
			;

Number		: Number Digit		{ $$=$1*10+$2; }
			| Digit				{ $$=$1; }
			;
%%

int main()
{
	FILE* in;
	in=fopen("input.txt","r");
	yyset_in(in);
	out=stdout;
	fprintf(out,"#include<iostream>\n#include<stack>\n#include <unistd.h>\n#include<bitset>\nusing namespace std;\n\n\
int main()\n{\n\tint ary[256];\n\tstack<int> stk;\n\tint aryPtr=0;\n");
	nest=1;
	yyparse();
	fprintf(out,"\treturn 0;\n}\n");
	fclose(out);
	fclose(in);
}
void yyerror(const char* s) { cout<<s<<endl; }

string indent()
{
	string s;
	for(int i=0;i<nest;i++)
		s+="\t";
	return s;
}

