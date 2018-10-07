yacc -d calc.yacc
lex calc.lex
gcc -w y.tab.c lex.yy.c

