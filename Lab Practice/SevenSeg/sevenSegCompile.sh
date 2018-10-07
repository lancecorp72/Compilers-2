yacc -d sevenSeg.yacc
lex sevenSeg.lex
g++ y.tab.c lex.yy.c

