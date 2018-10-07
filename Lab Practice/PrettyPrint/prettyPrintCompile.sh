yacc -d prettyPrint.yacc
lex prettyPrint.lex
gcc y.tab.c lex.yy.c

