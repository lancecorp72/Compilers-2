yacc -d p3.y
lex p3.l
g++ y.tab.c lex.yy.c
./a.out

