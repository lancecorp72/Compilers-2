yacc -d p2.y
lex p2.l
g++ y.tab.c lex.yy.c
./a.out

